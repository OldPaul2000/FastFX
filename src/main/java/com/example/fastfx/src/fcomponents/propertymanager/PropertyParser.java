package com.example.fastfx.src.fcomponents.propertymanager;

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
    public static final String ONE_SIDE_BORDER_PROPERTY = "oneSideBorderProperty";
    public static final String BORDER_STYLE_PROPERTY = "borderStyle";
    public static final String SIZE_PROPERTY = "sizeProperty";
    public static final String NUMBER_PROPERTY = "numberProperty";

    private static final String INFINITE_DURATION = "inf";
    private static final String COLOR_REGEX = "#[a-fA-F0-9]{6}|#[a-fA-F0-9]{8}|[a-zA-Z]+";
    private static final String HEX_COLOR_REGEX = "#[a-fA-F0-9]{6}|#[a-fA-F0-9]{8}";
    private static final String SIZE_REGEX = "(-?(\\d+|\\d+\\.\\d+))(%?(px)?)?";
    private static final String ONLY_NUMBER_REGEX = "-?(\\d+|\\d+\\.\\d+)";
    private static final String DURATION_REGEX = "(\\d+|\\d+\\.\\d+)(s)";
    private static final String INTERPOLATOR_REGEX = "linear|ease-in|ease-out|ease-both|discrete";
    private static final String BORDER_STYLE_REGEX = "solid|dotted|dashed";
    private static final String CYCLE_COUNT_REGEX = "\\d+c|" + INFINITE_DURATION;

    private boolean valid;
    private int parametersCount;

    private String[] colorValues;
    private double[] bgOpacityValues;
    private String[] borderStyles;
    private double[] doubleValues;
    private String[] units;
    private long[] durations;
    private String[] interpolators;
    private int[] cyclesCount;

    private long borderColorDuration;
    private String borderColorInterpolator;
    private int borderColorCyclesCount;

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
        else if (property.equals(BORDER_PROPERTY)) {
            parseBorderProperty(values);
        }
        else if (property.equals(ONE_SIDE_BORDER_PROPERTY)){
            parseOneSideBorderProperty(values);
        }
        else if(property.equals(BORDER_STYLE_PROPERTY)){
            parseBorderStyle(values);
        }
        else if (property.equals(SIZE_PROPERTY)) {
            parseSizeProperty(values);
        }
        else if (property.equals(NUMBER_PROPERTY)) {
            parseNumberProperty(values);
        }
    }

    private void parseColorProperty (String[] values) {
        int indexAfterColorProp = 0;
        boolean durationFound = false;
        boolean interpolatorFound = false;
        for (int i = 0; i < values.length; i++) {
            String currEl = values[i];
            if (!currEl.equals(INFINITE_DURATION) && currEl.matches(COLOR_REGEX)) {
                indexAfterColorProp = 0;
                durationFound = false;
                interpolatorFound = false;
                parametersCount++;
                setColor(parametersCount, currEl);
                setBgOpacity(parametersCount, currEl);
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
        int indexAfterProperty = 0;
        boolean durationFound = false;
        boolean interpolatorFound = false;
        if (values[0].matches(COLOR_REGEX)) {
            setColor(1, values[0]);
            startCountingIndex++;
            for (int i = 1; i < values.length; i++) {
                String currEl = values[i];
                if (currEl.matches(DURATION_REGEX)) {
                    durationFound = true;
                    indexAfterProperty++;
                    startCountingIndex++;
                    setBorderColorDuration(currEl);
                } else if (currEl.matches(INTERPOLATOR_REGEX) && durationFound && indexAfterProperty == 1) {
                    interpolatorFound = true;
                    durationFound = false;
                    indexAfterProperty++;
                    startCountingIndex++;
                    setBorderColorInterpolator(currEl);
                } else if (currEl.matches(CYCLE_COUNT_REGEX) && (durationFound || interpolatorFound)) {
                    indexAfterProperty++;
                    startCountingIndex++;
                    durationFound = false;
                    interpolatorFound = false;
                    setBorderColorCyclesCount(currEl);
                } else if (BorderSetter.BORDER_STYLES.containsKey(currEl.toLowerCase()) || currEl.matches(SIZE_REGEX)) {
                    break;
                } else {
                    valid = false;
                    break;
                }
            }
        } else {
            valid = false;
        }
        if (BorderSetter.BORDER_STYLES.containsKey(values[startCountingIndex].toLowerCase())) {
            setBorderStyle(1, values[startCountingIndex].toLowerCase());
            startCountingIndex++;
        } else {
            setBorderStyle(1, "solid");
        }
        if (valid) {
            indexAfterProperty = 0;
            durationFound = false;
            interpolatorFound = false;
            for (int i = startCountingIndex; i < values.length; i++) {
                String currEl = values[i];
                if (currEl.matches(SIZE_REGEX)) {
                    indexAfterProperty = 0;
                    durationFound = false;
                    interpolatorFound = false;
                    parametersCount++;
                    if (parametersCount == 9) {
                        break;
                    }
                    setDoubleValue(parametersCount, currEl);
                    setUnit(parametersCount, currEl);
                } else if (parametersCount > 0) {
                    if (currEl.matches(DURATION_REGEX) && indexAfterProperty == 0) {
                        durationFound = true;
                        indexAfterProperty++;
                        setDuration(parametersCount, currEl);
                    } else if (currEl.matches(INTERPOLATOR_REGEX) && durationFound && indexAfterProperty == 1) {
                        interpolatorFound = true;
                        durationFound = false;
                        indexAfterProperty++;
                        setInterpolator(parametersCount, currEl);
                    } else if (currEl.matches(CYCLE_COUNT_REGEX) && (durationFound || interpolatorFound)) {
                        indexAfterProperty++;
                        setCycleCount(parametersCount, currEl);
                    } else {
                        valid = false;
                        break;
                    }
                }
                if (indexAfterProperty > 3) {
                    valid = false;
                    break;
                }
            }
        }
        if (parametersCount != 2 && parametersCount != 4 && parametersCount != 8) {
            valid = false;
        }
    }

    private void parseOneSideBorderProperty(String[] values){
        int startCountingIndex = 0;
        int indexAfterProperty = 0;
        boolean durationFound = false;
        boolean interpolatorFound = false;
        if (values[0].matches(COLOR_REGEX)) {
            setColor(1, values[0]);
            startCountingIndex++;
            for (int i = 1; i < values.length; i++) {
                String currEl = values[i];
                if (currEl.matches(DURATION_REGEX)) {
                    durationFound = true;
                    indexAfterProperty++;
                    startCountingIndex++;
                    setBorderColorDuration(currEl);
                } else if (currEl.matches(INTERPOLATOR_REGEX) && durationFound && indexAfterProperty == 1) {
                    interpolatorFound = true;
                    durationFound = false;
                    indexAfterProperty++;
                    startCountingIndex++;
                    setBorderColorInterpolator(currEl);
                } else if (currEl.matches(CYCLE_COUNT_REGEX) && (durationFound || interpolatorFound)) {
                    indexAfterProperty++;
                    startCountingIndex++;
                    durationFound = false;
                    interpolatorFound = false;
                    setBorderColorCyclesCount(currEl);
                } else if (BorderSetter.BORDER_STYLES.containsKey(currEl.toLowerCase()) || currEl.matches(SIZE_REGEX)) {
                    break;
                } else {
                    valid = false;
                    break;
                }
            }
        } else {
            valid = false;
        }
        if (BorderSetter.BORDER_STYLES.containsKey(values[startCountingIndex].toLowerCase())) {
            setBorderStyle(1, values[startCountingIndex].toLowerCase());
            startCountingIndex++;
        } else {
            setBorderStyle(1, "solid");
        }
        if (valid) {
            indexAfterProperty = 0;
            durationFound = false;
            interpolatorFound = false;
            for (int i = startCountingIndex; i < values.length; i++) {
                String currEl = values[i];
                if (currEl.matches(SIZE_REGEX)) {
                    indexAfterProperty = 0;
                    durationFound = false;
                    interpolatorFound = false;
                    parametersCount++;
                    if (parametersCount > 1) {
                        break;
                    }
                    setDoubleValue(parametersCount, currEl);
                    setUnit(parametersCount, currEl);
                } else if (parametersCount == 1) {
                    if (currEl.matches(DURATION_REGEX) && indexAfterProperty == 0) {
                        durationFound = true;
                        indexAfterProperty++;
                        setDuration(parametersCount, currEl);
                    } else if (currEl.matches(INTERPOLATOR_REGEX) && durationFound && indexAfterProperty == 1) {
                        interpolatorFound = true;
                        durationFound = false;
                        indexAfterProperty++;
                        setInterpolator(parametersCount, currEl);
                    } else if (currEl.matches(CYCLE_COUNT_REGEX) && (durationFound || interpolatorFound)) {
                        indexAfterProperty++;
                        setCycleCount(parametersCount, currEl);
                    } else {
                        valid = false;
                        break;
                    }
                }
                if (indexAfterProperty > 3) {
                    valid = false;
                    break;
                }
            }
        }
        if (parametersCount != 1) {
            valid = false;
        }
    }

    private void parseBorderStyle(String[] values){
        for(int i = 0; i < values.length; i++) {
            String currEl = values[i].toLowerCase();
            if (currEl.matches(BORDER_STYLE_REGEX)) {
                setBorderStyle(i + 1, currEl);
                parametersCount++;
            } else {
                valid = false;
                break;
            }
        }
        if(parametersCount == 0 || parametersCount == 3 || parametersCount > 4){
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
     * This method spreads the values inside the <strong>doubleValues array</strong>.<br>
     * If the <strong>parametersCount</strong> == 1 and the doubleValues[0] == 2<br>
     * then all values in the array will be 2.
     * If the <strong>parametersCount</strong> == 2 and the doubleValues[0] == 2 and doubleValues[1] == 3<br>
     * then doubleValues[2] will be 2 and doubleValues[3] will be 3.<br>
     * This method is helpful to avoid if checks when setting properties where there are multiple sides to style.<br>
     * For example for border insets.
     */
    public void spreadDoubleValues () {
        if(doubleValues.length == 4){
            if (parametersCount == 1) {
                doubleValues[1] = doubleValues[0];
                doubleValues[2] = doubleValues[0];
                doubleValues[3] = doubleValues[0];
            }
            else if (parametersCount == 2) {
                doubleValues[2] = doubleValues[0];
                doubleValues[3] = doubleValues[1];
            }
        }
        else if(doubleValues.length == 8){
            if(parametersCount == 2){
                doubleValues[4] = doubleValues[1];
                doubleValues[5] = doubleValues[1];
                doubleValues[6] = doubleValues[1];
                doubleValues[7] = doubleValues[1];
                doubleValues[1] = doubleValues[0];
                doubleValues[2] = doubleValues[0];
                doubleValues[3] = doubleValues[0];
            }
            else if(parametersCount == 4){
                doubleValues[4] = doubleValues[2];
                doubleValues[5] = doubleValues[3];
                doubleValues[6] = doubleValues[2];
                doubleValues[7] = doubleValues[3];
                doubleValues[2] = doubleValues[0];
                doubleValues[3] = doubleValues[1];
            }
        }
    }

    public void spreadUnits () {
        if(units.length == 4){
            if (parametersCount == 1) {
                units[1] = units[0];
                units[2] = units[0];
                units[3] = units[0];
            }
            else if (parametersCount == 2) {
                units[2] = units[0];
                units[3] = units[1];
            }
        }
        else if(units.length == 8){
            if(parametersCount == 2){
                units[4] = units[1];
                units[5] = units[1];
                units[6] = units[1];
                units[7] = units[1];
                units[1] = units[0];
                units[2] = units[0];
                units[3] = units[0];
            }
            else if(parametersCount == 4){
                units[4] = units[2];
                units[5] = units[3];
                units[6] = units[2];
                units[7] = units[3];
                units[2] = units[0];
                units[3] = units[1];
            }
        }
    }

    public void spreadColorValues () {
        if(doubleValues.length == 4){
            if (parametersCount == 1) {
                colorValues[1] = colorValues[0];
                colorValues[2] = colorValues[0];
                colorValues[3] = colorValues[0];
            }
            else if (parametersCount == 2) {
                colorValues[2] = colorValues[0];
                colorValues[3] = colorValues[1];
            }
        }
        // When parsing border style
        else if(doubleValues.length == 8){
            colorValues[1] = colorValues[0];
            colorValues[2] = colorValues[0];
            colorValues[3] = colorValues[0];
        }
    }

    public void spreadDurations () {
        if(durations.length == 4){
            if (parametersCount == 1) {
                durations[1] = durations[0];
                durations[2] = durations[0];
                durations[3] = durations[0];
            }
            else if (parametersCount == 2) {
                durations[2] = durations[0];
                durations[3] = durations[1];
            } 
        }
        else if(durations.length == 8){
            if(parametersCount == 2) {
                durations[4] = durations[1];
                durations[5] = durations[1];
                durations[6] = durations[1];
                durations[7] = durations[1];
                durations[1] = durations[0];
                durations[2] = durations[0];
                durations[3] = durations[0];
            }
            else if(parametersCount == 4){
                durations[4] = durations[2];
                durations[5] = durations[3];
                durations[6] = durations[2];
                durations[7] = durations[3];
                durations[2] = durations[0];
                durations[3] = durations[1];
            }
        }
    }

    public void spreadInterpolators () {
        if(interpolators.length == 4){
            if (parametersCount == 1) {
                interpolators[1] = interpolators[0];
                interpolators[2] = interpolators[0];
                interpolators[3] = interpolators[0];
            }
            else if (parametersCount == 2) {
                interpolators[2] = interpolators[0];
                interpolators[3] = interpolators[1];
            }
        }
        else if(interpolators.length == 8){
            if(parametersCount == 2){
                interpolators[4] = interpolators[1];
                interpolators[5] = interpolators[1];
                interpolators[6] = interpolators[1];
                interpolators[7] = interpolators[1];
                interpolators[1] = interpolators[0];
                interpolators[2] = interpolators[0];
                interpolators[3] = interpolators[0];
            }
            else if(parametersCount == 4){
                interpolators[4] = interpolators[2];
                interpolators[5] = interpolators[3];
                interpolators[6] = interpolators[2];
                interpolators[7] = interpolators[3];
                interpolators[2] = interpolators[0];
                interpolators[3] = interpolators[1];
            }
        }
    }

    public void spreadBorderStyles(){
        if(doubleValues.length == 4){
            if (parametersCount == 1) {
                borderStyles[1] = borderStyles[0];
                borderStyles[2] = borderStyles[0];
                borderStyles[3] = borderStyles[0];
            }
            else if (parametersCount == 2) {
                borderStyles[2] = borderStyles[0];
                borderStyles[3] = borderStyles[1];
            }
        }
        // When parsing border style
        else if(doubleValues.length == 8){
            borderStyles[1] = borderStyles[0];
            borderStyles[2] = borderStyles[0];
            borderStyles[3] = borderStyles[0];
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

    public double getBgOpacity(int index){
        return bgOpacityValues[index];
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
    public long getDuration (int index) {
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

    public long getBorderColorDuration () {
        return borderColorDuration;
    }

    public String getBorderColorInterpolator () {
        return borderColorInterpolator;
    }

    public int getBorderColorCyclesCount () {
        return borderColorCyclesCount;
    }

    private void setColor (int index, String value) {
        colorValues[index - 1] = value;
    }

    private void setBgOpacity(int index, String color){
        if(color.matches(HEX_COLOR_REGEX)){
            color = color.replace("#", "");
            if(color.length() == 6){
                bgOpacityValues[index - 1] = 1;
            }
            else{
                String hex = color.substring(6);
                double val = Integer.parseInt(hex, 16);
                val = val / 255;
                bgOpacityValues[index - 1] = val;
            }
        }
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
        durations[index - 1] = (long) (Double.parseDouble(value.replace("s", "")) * 1000);
    }

    private void setInterpolator (int index, String value) {
        interpolators[index - 1] = value;
    }

    private void setCycleCount (int index, String value) {
        if (value.equals(INFINITE_DURATION)) {
            value = "-1";
        } else {
            value = value.replace("c", "");
        }
        cyclesCount[index - 1] = Integer.parseInt(value);
    }

    private void setBorderColorDuration (String value) {
        borderColorDuration = (long) (Double.parseDouble(value.replace("s", "")) * 1000);
    }

    private void setBorderColorInterpolator (String value) {
        borderColorInterpolator = value;
    }

    private void setBorderColorCyclesCount (String value) {
        if (value.equals(INFINITE_DURATION)) {
            value = "-1";
        } else {
            value = value.replace("c", "");
        }
        borderColorCyclesCount = Integer.parseInt(value);
    }

    private void initProperties (String property) {
        this.valid = true;
        this.parametersCount = 0;
        this.colorValues = new String[4];
        this.bgOpacityValues = new double[4];
        this.borderStyles = new String[4];
        if (property.equals(BORDER_PROPERTY)) {
            this.doubleValues = new double[8];
            this.units = new String[8];
            this.durations = new long[8];
            this.interpolators = new String[]{FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR};
            this.cyclesCount = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        } else {
            this.doubleValues = new double[4];
            this.units = new String[4];
            this.durations = new long[4];
            this.interpolators = new String[]{FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR, FInterpolator.LINEAR};
            this.cyclesCount = new int[]{1, 1, 1, 1};
        }
        this.borderColorDuration = 0;
        this.borderColorInterpolator = "linear";
        this.borderColorCyclesCount = 0;
    }
}
