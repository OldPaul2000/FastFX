package com.example.fastfx.src.fcomponents.property;

import com.example.fastfx.src.style.animations.FInterpolator;
import com.example.fastfx.src.utility.style.BorderSetter;

/**
 * This class helps parse the input for setting a property by filtering<br>
 * the String input and then setting the class fields from below.<br>
 * For example if we set x property(layoutX) and the input String is<br>
 * (300px 1s) the element layoutX will be set to 300px with a transition<br>
 * of 1 second.<br>
 * For this example the first element from <strong>doubleValues</strong> will have a value of 300,<br>
 * the first element from <strong>units</strong> will be px and<br>
 * the first element from <strong>durations</strong> will have a value of 1.<br><br>
 */
public class PropertyParser {
    public static final String COLOR_PROPERTY = "colorProperty";
    public static final String BORDER_PROPERTY = "borderProperty";
    public static final String SIZE_PROPERTY = "sizeProperty";
    public static final String NUMBER_PROPERTY = "numberProperty";

    private static final String COLOR_REGEX = "#[a-fA-F0-9]{6}|#[a-fA-F0-9]{8}|[a-zA-Z]+";
    private static final String SIZE_REGEX = "(-?(\\d+|\\d+\\.\\d+))(%?(px)?)?";
    private static final String ONLY_NUMBER_REGEX = "-?(\\d+|\\d+\\.\\d+)";
    private static final String DURATION_REGEX = "(\\d+|\\d+\\.\\d+)(s)";
    private static final String INTERPOLATOR_REGEX = "linear|ease-in|ease-out|ease-both";
    public static final String CYCLE_COUNT_REGEX = "\\d+c|inf";

    private boolean valid;
    private int parametersCount;

    private String[] colorValues;
    private String[] borderStyles;
    private double[] doubleValues;
    private String[] units;
    private double[] durations;
    private String[] interpolators;
    private int[] cyclesCount;

    /**
     * Parses the input value.<br>
     *
     * @param property specifies the property type
     * @param value    represents the input String which contains the values
     */
    public void parse (String property, String value) {
        initProperties(property);
        value = value.trim();
        String[] values = value.split("\\s+");
        if (property.equals(COLOR_PROPERTY)) {
            parseColorProperty(values);
        }
        if (property.equals(BORDER_PROPERTY)) {
            parseBorderProperty(values);
        }
        if (property.equals(SIZE_PROPERTY)) {
            parseSizeProperty(values);
        }
        if (property.equals(NUMBER_PROPERTY)) {
            parseNumberProperty(values);
        }
    }

    private void parseColorProperty (String[] values) {
        int indexAfterColorProp = 0;
        boolean durationFound = false;
        boolean interpolatorFound = false;
        for (int i = 0; i < values.length; i++) {
            String currEl = values[i];
            if (!currEl.equals("inf") && currEl.matches(COLOR_REGEX)) {
                indexAfterColorProp = 0;
                durationFound = false;
                interpolatorFound = false;
                parametersCount++;
                setColor(parametersCount, currEl);
            } else {
                if (currEl.matches(DURATION_REGEX) && indexAfterColorProp == 0) {
                    durationFound = true;
                    indexAfterColorProp++;
                    setDuration(parametersCount, currEl);
                } else if (currEl.matches(INTERPOLATOR_REGEX) && durationFound && indexAfterColorProp == 1) {
                    interpolatorFound = true;
                    durationFound = false;
                    indexAfterColorProp++;
                    setInterpolator(parametersCount, currEl);
                } else if (currEl.matches(CYCLE_COUNT_REGEX) && (durationFound || interpolatorFound)) {
                    indexAfterColorProp++;
                    setCycleCount(parametersCount, currEl);
                } else {
                    valid = false;
                    break;
                }
            }
            if (indexAfterColorProp > 3) {
                valid = false;
                break;
            }
        }
        if (parametersCount == 0 || parametersCount == 3 || parametersCount > 4) {
            valid = false;
        }
    }

    private void parseBorderProperty (String[] values) {
        int startCountingIndex = 0;
        if (values[0].matches(COLOR_REGEX)) {
            setColor(1, values[0]);
            startCountingIndex++;
        } else {
            valid = false;
        }
        if (BorderSetter.BORDER_STYLES.containsKey(values[1].toLowerCase())) {
            setBorderStyle(1, values[1].toLowerCase());
            startCountingIndex++;
        } else {
            setBorderStyle(0, "linear");
        }
        if (valid) {
            int indexAfterSizeProp = 0;
            boolean durationFound = false;
            boolean interpolatorFound = false;
            for (int i = startCountingIndex; i < values.length; i++) {
                String currEl = values[i];
                if (currEl.matches(SIZE_REGEX)) {
                    indexAfterSizeProp = 0;
                    durationFound = false;
                    interpolatorFound = false;
                    parametersCount++;
                    if (parametersCount == 9) {
                        break;
                    }
                    setDoubleValue(parametersCount, currEl);
                    setUnit(parametersCount, currEl);
                } else {
                    if (currEl.matches(DURATION_REGEX) && indexAfterSizeProp == 0) {
                        durationFound = true;
                        indexAfterSizeProp++;
                        setDuration(parametersCount, currEl);
                    } else if (currEl.matches(INTERPOLATOR_REGEX) && durationFound && indexAfterSizeProp == 1) {
                        interpolatorFound = true;
                        durationFound = false;
                        indexAfterSizeProp++;
                        setInterpolator(parametersCount, currEl);
                    } else if (currEl.matches(CYCLE_COUNT_REGEX) && (durationFound || interpolatorFound)) {
                        indexAfterSizeProp++;
                        setCycleCount(parametersCount, currEl);
                    } else {
                        valid = false;
                        break;
                    }
                }
                if (indexAfterSizeProp > 3) {
                    valid = false;
                    break;
                }
            }
        }
        if (parametersCount != 2 && parametersCount != 4 && parametersCount != 8) {
            valid = false;
        }
    }

    private void parseSizeProperty (String[] values) {
        int indexAfterSizeProp = 0;
        boolean durationFound = false;
        boolean interpolatorFound = false;
        for (int i = 0; i < values.length; i++) {
            String currEl = values[i];
            if (currEl.matches(SIZE_REGEX) && parametersCount < 4) {
                indexAfterSizeProp = 0;
                durationFound = false;
                interpolatorFound = false;
                parametersCount++;
                setDoubleValue(parametersCount, currEl);
                setUnit(parametersCount, currEl);
            } else {
                if (currEl.matches(DURATION_REGEX) && indexAfterSizeProp == 0) {
                    durationFound = true;
                    indexAfterSizeProp++;
                    setDuration(parametersCount, currEl);
                } else if (currEl.matches(INTERPOLATOR_REGEX) && durationFound && indexAfterSizeProp == 1) {
                    interpolatorFound = true;
                    durationFound = false;
                    indexAfterSizeProp++;
                    setInterpolator(parametersCount, currEl);
                } else if (currEl.matches(CYCLE_COUNT_REGEX) && (durationFound || interpolatorFound)) {
                    indexAfterSizeProp++;
                    setCycleCount(parametersCount, currEl);
                } else {
                    valid = false;
                    break;
                }
            }
            if (indexAfterSizeProp > 3) {
                valid = false;
                break;
            }
        }
        if (parametersCount == 0 || parametersCount == 3 || parametersCount > 4) {
            valid = false;
        }
    }

    private void parseNumberProperty (String[] values) {
        int indexAfterSizeProp = 0;
        boolean durationFound = false;
        boolean interpolatorFound = false;
        for (int i = 0; i < values.length; i++) {
            String currEl = values[i];
            if (currEl.matches(ONLY_NUMBER_REGEX)) {
                indexAfterSizeProp = 0;
                durationFound = false;
                interpolatorFound = false;
                parametersCount++;
                setDoubleValue(parametersCount, currEl);
            } else {
                if (currEl.matches(DURATION_REGEX) && indexAfterSizeProp == 0) {
                    durationFound = true;
                    indexAfterSizeProp++;
                    setDuration(parametersCount, currEl);
                } else if (currEl.matches(INTERPOLATOR_REGEX) && durationFound && indexAfterSizeProp == 1) {
                    interpolatorFound = true;
                    durationFound = false;
                    indexAfterSizeProp++;
                    setInterpolator(parametersCount, currEl);
                } else if (currEl.matches(CYCLE_COUNT_REGEX) && (durationFound || interpolatorFound)) {
                    indexAfterSizeProp++;
                    setCycleCount(parametersCount, currEl);
                } else {
                    valid = false;
                    break;
                }
            }
            if (indexAfterSizeProp > 3) {
                valid = false;
                break;
            }
        }
        if (parametersCount != 1) {
            valid = false;
        }
    }

    /**
     * Returns true if the property is valid, else returns false
     */
    public boolean isValid () {
        return valid;
    }

    public int getParametersCount () {
        return parametersCount;
    }

    // Color values
    public String getColorValue (int index) {
        return colorValues[index];
    }

    // Border style
    public String getBorderStyle (int index) {
        return borderStyles[index];
    }

    // Double values
    public double getDoubleValue (int index) {
        return doubleValues[index];
    }

    // Units
    public String getUnit (int index) {
        return units[index];
    }

    // Duration
    public double getDuration (int index) {
        return durations[index];
    }

    // Interpolator
    public String getInterpolator (int index) {
        return interpolators[index];
    }

    // Cycle count
    public int getCycleCount (int index) {
        return cyclesCount[index];
    }

    private void setColor (int index, String value) {
        colorValues[index - 1] = value;
    }

    private void setBorderStyle (int index, String borderStyle) {
        borderStyles[index - 1] = borderStyle;
    }

    private void setDoubleValue (int index, String value) {
        value = value.replaceAll("[a-zA-Z%]", "");
        doubleValues[index - 1] = Double.parseDouble(value);
    }

    private void setUnit (int index, String value) {
        value = value.replaceAll("[-\\d.]", "");
        if (value.isEmpty()) {
            units[index - 1] = "px";
        } else {
            units[index - 1] = value;
        }
    }

    private void setDuration (int index, String value) {
        durations[index - 1] = Double.parseDouble(value.replace("s", ""));
    }

    private void setInterpolator (int index, String value) {
        interpolators[index - 1] = value;
    }

    private void setCycleCount (int index, String value) {
        if (value.equals("inf")) {
            value = "-1";
        } else {
            value = value.replace("c", "");
        }
        cyclesCount[index - 1] = Integer.parseInt(value);
    }

    private void initProperties (String property) {
        this.valid = true;
        this.parametersCount = 0;
        this.colorValues = new String[4];
        this.borderStyles = new String[4];
        if (property.equals(BORDER_PROPERTY)) {
            this.doubleValues = new double[8];
            this.units = new String[8];
            this.durations = new double[8];
            this.interpolators = new String[]{FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR};
            this.cyclesCount = new int[8];
        } else {
            this.doubleValues = new double[4];
            this.units = new String[4];
            this.durations = new double[4];
            this.interpolators = new String[]{FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR};
            this.cyclesCount = new int[4];
        }
    }
}
