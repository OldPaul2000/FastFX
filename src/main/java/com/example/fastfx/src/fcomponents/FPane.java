package com.example.fastfx.src.fcomponents;

import com.example.fastfx.src.fcomponents.flistener.ResizeListener;
import com.example.fastfx.src.fcomponents.property.PropertyUtil;
import com.example.fastfx.src.fcomponents.property.PropertyValidatorFilter;
import com.example.fastfx.src.fcomponents.pseudoclass.PseudoClass;
import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.FAnimations;
import com.example.fastfx.src.style.animations.StaticAnimation;
import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.*;

public class FPane extends Pane implements FComponent {

    private String rawX;
    private String rawY;
    private String rawWidth;
    private String rawHeight;
    private String rawScaleX;
    private String rawScaleY;
    private String rawTranslateX;
    private String rawTranslateY;
    private String rawRotate;
    private String rawPadding;
    private Margin margin;
    private String rawMargin;

    private String bgColor;
    private String rawBgColor;
    private String rawBgOpacity;
    private String rawBgRadius;
    private String rawTopLeftBgRadius;
    private String rawTopRightBgRadius;
    private String rawBottomRightBgRadius;
    private String rawBottomLeftBgRadius;
    private String rawBgInsets;

    private String rawBorder;
    private String rawTopBorder;
    private String rawRightBorder;
    private String rawBottomBorder;
    private String rawLeftBorder;
    private String rawBorderWidth;
    private String rawBorderColor;
    private String rawBorderOpacity;
    private String rawBorderRadius;
    private String rawTopLeftBorderRadius;
    private String rawTopRightBorderRadius;
    private String rawBottomRightBorderRadius;
    private String rawBottomLeftBorderRadius;
    private String rawBorderInsets;

    private Map<String, String> initialValues;

    private FAnimation hoverAnimation;
    private FAnimation mouseEnterAnimation;
    private FAnimation mouseExitAnimation;
    private FAnimation clickAnimation;
    private FAnimation mousePressedAnimation;
    private FAnimation mouseReleasedAnimation;

    private StaticAnimation staticHoverAnimation;
    private StaticAnimation staticClickAnimation;

    private boolean setPropertiesOnHover = false;
    private EventHandler<MouseEvent> mouseEnterEvent;
    private EventHandler<MouseEvent> mouseExitEvent;

    private boolean setPropertiesOnClick = false;
    private EventHandler<MouseEvent> mousePressedEvent;
    private EventHandler<MouseEvent> mouseReleasedEvent;

    private DoubleProperty bgRadiusProperty;
    private DoubleProperty tlBgRadiusProperty;
    private DoubleProperty trBgRadiusProperty;
    private DoubleProperty brBgRadiusProperty;
    private DoubleProperty blBgRadiusProperty;
    private List<DoubleProperty> bgRadiusProperties;

    private DoubleProperty brRadiusProperty;
    private DoubleProperty tlBrRadiusProperty;
    private DoubleProperty trBrRadiusProperty;
    private DoubleProperty brBrRadiusProperty;
    private DoubleProperty blBrRadiusProperty;
    private List<DoubleProperty> brRadiusProperties;

    private DoubleProperty brWidthProperty;
    private DoubleProperty tlBrWidthProperty;
    private DoubleProperty trBrWidthProperty;
    private DoubleProperty brBrWidthProperty;
    private DoubleProperty blBrWidthProperty;
    private List<DoubleProperty> brWidthProperties;

    private ChangeListener<Number> resizeWidthListener;
    private ChangeListener<Number> resizeHeightListener;
    private Set<ResizeListener> resizeListeners;

    public FPane () {
        this.initialValues = new HashMap<>();
        initBgRadiusProperties();
        initBrRadiusProperties();
        initBrWidthProperties();
        resizeListeners = new HashSet<>();
    }

    private void initBgRadiusProperties () {
        bgRadiusProperty = new SimpleDoubleProperty();
        tlBgRadiusProperty = new SimpleDoubleProperty();
        trBgRadiusProperty = new SimpleDoubleProperty();
        brBgRadiusProperty = new SimpleDoubleProperty();
        blBgRadiusProperty = new SimpleDoubleProperty();
        bgRadiusProperties = new ArrayList<>(List.of(tlBgRadiusProperty, trBgRadiusProperty, brBgRadiusProperty, blBgRadiusProperty));
    }

    private void initBrRadiusProperties () {
        brRadiusProperty = new SimpleDoubleProperty();
        tlBrRadiusProperty = new SimpleDoubleProperty();
        trBrRadiusProperty = new SimpleDoubleProperty();
        brBrRadiusProperty = new SimpleDoubleProperty();
        blBrRadiusProperty = new SimpleDoubleProperty();
        brRadiusProperties = new ArrayList<>(List.of(tlBrRadiusProperty, trBrRadiusProperty, brBrRadiusProperty, blBrRadiusProperty));
    }

    private void initBrWidthProperties () {
        brWidthProperty = new SimpleDoubleProperty();
        tlBrWidthProperty = new SimpleDoubleProperty();
        trBrWidthProperty = new SimpleDoubleProperty();
        brBrWidthProperty = new SimpleDoubleProperty();
        blBrWidthProperty = new SimpleDoubleProperty();
        brWidthProperties = new ArrayList<>(List.of(tlBrWidthProperty, trBrWidthProperty, brBrWidthProperty, blBrWidthProperty));
    }

    public FPane hover (PseudoClass hover) {
        if (hoverAnimation == null) {
            hoverAnimation = new FAnimation(this);
        }
        if (staticHoverAnimation == null){
            staticHoverAnimation = new StaticAnimation(this);
        }
        setPropertiesOnClick = false;
        setPropertiesOnHover = true;
        hover.apply();
        setPropertiesOnHover = false;
        mouseEnterEvent = (e) -> {
            hoverAnimation.play();
            staticHoverAnimation.play();
        };
        mouseExitEvent = (e) -> {
            hoverAnimation.reverse();
            staticHoverAnimation.reverse();
        };
        addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnterEvent);
        addEventHandler(MouseEvent.MOUSE_EXITED, mouseExitEvent);
        return this;
    }

    public FPane click (PseudoClass click) {
        if (clickAnimation == null) {
            clickAnimation = new FAnimation(this);
        }
        if(staticClickAnimation == null){
            staticClickAnimation = new StaticAnimation(this);
        }
        setPropertiesOnHover = false;
        setPropertiesOnClick = true;
        click.apply();
        setPropertiesOnClick = false;
        mousePressedEvent = (e) -> {
            clickAnimation.play();
            staticClickAnimation.play();
        };
        mouseReleasedEvent = (e) -> {
            clickAnimation.reverse();
            staticClickAnimation.reverse();
        };
        addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEvent);
        addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedEvent);
        return this;
    }

    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************

    @Override
    public FPane x (double value) {
        initialValues.put("x", value + "");
        setLayoutX(value);
        return this;
    }

    @Override
    public FPane x (double value, String unit) {
        if (unit.equals("px") || unit.isEmpty()) {
            setLayoutX(value);
        } else if (unit.equals("%")) {
            Region parent = (Region) getParent();
            if (parent == null) {

            } else {
                layoutXProperty().bind(parent.prefWidthProperty().multiply(value / 100));
            }
        }
        return this;
    }

    @Override
    public FPane x (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawX = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (unit.equals("%")) {

            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getLayoutX(), initialValues, "x", hoverAnimation, FAnimations.LAYOUT_X, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getLayoutX(), initialValues, "x", clickAnimation, FAnimations.LAYOUT_X, value, duration);
            } else {
                if (duration == 1) {
                    setLayoutX(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getLayoutX(), initialValues, "x", FAnimations.LAYOUT_X, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public String getRawX () {
        return rawX;
    }

    @Override
    public FPane y (double value) {
        initialValues.put("y", value + "");
        setLayoutY(value);
        return this;
    }

    @Override
    public FPane y (double value, String unit) {
        if (unit.equals("px") || unit.isEmpty()) {
            setLayoutY(value);
        } else if (unit.equals("%")) {
            Region parent = (Region) getParent();
            if (parent == null) {

            } else {
                layoutYProperty().bind(parent.prefHeightProperty().multiply(value / 100));
            }
        }
        return this;
    }

    @Override
    public FPane y (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawY = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (unit.equals("%")) {

            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getLayoutY(), initialValues, "y", hoverAnimation, FAnimations.LAYOUT_Y, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getLayoutY(), initialValues, "y", clickAnimation, FAnimations.LAYOUT_Y, value, duration);
            } else {
                if (duration == 1) {
                    setLayoutY(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getLayoutY(), initialValues, "y", FAnimations.LAYOUT_Y, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public String getRawY () {
        return rawY;
    }

    @Override
    public FPane width (double value) {
        initialValues.put("width", value + "");
        setPrefWidth(value);
        return this;
    }

    @Override
    public FPane width (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawWidth = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (unit.equals("%")) {

            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getPrefWidth(), initialValues, "width", hoverAnimation, FAnimations.WIDTH, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getLayoutY(), initialValues, "width", clickAnimation, FAnimations.WIDTH, value, duration);
            } else {
                if (duration == 1) {
                    setPrefWidth(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getPrefWidth(), initialValues, "width", FAnimations.WIDTH, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawWidth () {
        return rawWidth;
    }

    @Override
    public FPane height (double value) {
        initialValues.put("height", value + "");
        setPrefHeight(value);
        return this;
    }

    @Override
    public FPane height (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawHeight = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (unit.equals("%")) {

            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getPrefHeight(), initialValues, "height", hoverAnimation, FAnimations.HEIGHT, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getPrefHeight(), initialValues, "height", clickAnimation, FAnimations.HEIGHT, value, duration);
            } else {
                if (duration == 1) {
                    setPrefHeight(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getPrefHeight(), initialValues, "height", FAnimations.HEIGHT, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawHeight () {
        return rawHeight;
    }

    @Override
    public FPane scaleX (double value) {
        initialValues.put("scaleX", value + "");
        setScaleX(value);
        return this;
    }

    @Override
    public FPane scaleX (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawScaleX = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getScaleX(), initialValues, "scaleX", hoverAnimation, FAnimations.SCALE_X, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getScaleX(), initialValues, "scaleX", clickAnimation, FAnimations.SCALE_X, value, duration);
            } else {
                if (duration == 1) {
                    setScaleX(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getScaleX(), initialValues, "scaleX", FAnimations.SCALE_X, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawScaleX () {
        return rawScaleX;
    }

    @Override
    public FPane scaleY (double value) {
        initialValues.put("width", value + "");
        setScaleY(value);
        return this;
    }

    @Override
    public FPane scaleY (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawScaleY = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getScaleY(), initialValues, "scaleY", hoverAnimation, FAnimations.SCALE_Y, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getScaleY(), initialValues, "scaleY", clickAnimation, FAnimations.SCALE_Y, value, duration);
            } else {
                if (duration == 1) {
                    setScaleY(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getScaleY(), initialValues, "scaleY", FAnimations.SCALE_Y, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawScaleY () {
        return rawScaleY;
    }

    @Override
    public FPane translateX (double value) {
        initialValues.put("translateX", value + "");
        setTranslateX(value);
        return this;
    }

    @Override
    public FPane translateX (double value, String unit) {
        if (unit.equals("px") || unit.isEmpty()) {
            setTranslateX(value);
        } else if (unit.equals("%")) {
            setTranslateX(getPrefWidth() * (value / 100));
        }
        return this;
    }

    @Override
    public FPane translateX (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawTranslateX = rawValue;
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (unit.equals("%")) {
                value = getPrefWidth() * (value / 100);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getTranslateX(), initialValues, "translateX", hoverAnimation, FAnimations.TRANSLATE_X, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getTranslateX(), initialValues, "translateX", clickAnimation, FAnimations.TRANSLATE_X, value, duration);
            } else {
                if (duration == 1) {
                    setTranslateX(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getTranslateX(), initialValues, "translateX", FAnimations.TRANSLATE_X, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawTranslateX () {
        return rawTranslateX;
    }

    @Override
    public FPane translateY (double value) {
        setTranslateY(value);
        return this;
    }

    @Override
    public FPane translateY (double value, String unit) {
        if (unit.equals("px") || unit.isEmpty()) {
            setTranslateY(value);
        } else if (unit.equals("%")) {
            translateY(getPrefHeight() * (value / 100));
        }
        return this;
    }

    @Override
    public FPane translateY (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawTranslateY = rawValue;
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (unit.equals("%")) {
                value = getPrefHeight() * (value / 100);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getTranslateY(), initialValues, "translateY", hoverAnimation, FAnimations.TRANSLATE_Y, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getTranslateY(), initialValues, "translateY", clickAnimation, FAnimations.TRANSLATE_Y, value, duration);
            } else {
                if (duration == 1) {
                    setTranslateY(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getTranslateY(), initialValues, "translateY", FAnimations.TRANSLATE_Y, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawTranslateY () {
        return rawTranslateY;
    }

    @Override
    public FPane rotate (double value) {
        setRotate(value);
        return this;
    }

    @Override
    public FPane rotate (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateRotateValue(values);
        if (validated) {
            this.rawRotate = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(getRotate(), initialValues, "rotate", hoverAnimation, FAnimations.ROTATE, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(getRotate(), initialValues, "rotate", clickAnimation, FAnimations.ROTATE, value, duration);
            } else {
                if (duration == 1) {
                    setRotate(value);
                } else {
                    PropertyUtil.setTempAnimation(this, getTranslateY(), initialValues, "rotate", FAnimations.ROTATE, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawRotate () {
        return rawRotate;
    }

    @Override
    public FPane padding (double padding) {
        setPadding(new Insets(padding, padding, padding, padding));
        return this;
    }

    @Override
    public FPane padding (double topBottom, double rightLeft) {
        setPadding(new Insets(topBottom, rightLeft, topBottom, rightLeft));
        return this;
    }

    @Override
    public FPane padding (double top, double right, double bottom, double left) {
        setPadding(new Insets(top, right, bottom, left));
        return this;
    }

    @Override
    public FPane padding (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateInsetsValues(values);
        if (validated) {
            this.rawPadding = rawValue;
            if (values.length == 1) {
                double insets = PropertyValidatorFilter.getDoubleValue(values[0]);
                setPadding(new Insets(insets));
            } else if (values.length == 2) {
                double topBottom = PropertyValidatorFilter.getDoubleValue(values[0]);
                double leftRight = PropertyValidatorFilter.getDoubleValue(values[1]);
                setPadding(new Insets(topBottom, leftRight, topBottom, leftRight));
            } else if (values.length == 4) {
                double top = PropertyValidatorFilter.getDoubleValue(values[0]);
                double right = PropertyValidatorFilter.getDoubleValue(values[1]);
                double bottom = PropertyValidatorFilter.getDoubleValue(values[2]);
                double left = PropertyValidatorFilter.getDoubleValue(values[3]);
                setPadding(new Insets(top, right, bottom, left));
            }
        }
        return this;
    }

    @Override
    public String getRawPadding () {
        return rawPadding;
    }

    @Override
    public FPane margin (double margin) {
        if (this.margin == null) {
            this.margin = new Margin(margin, margin, margin, margin);
        }
        return this;
    }

    @Override
    public FPane margin (double topBottom, double rightLeft) {
        if (this.margin == null) {
            this.margin = new Margin(topBottom, rightLeft, topBottom, rightLeft);
        }
        return this;
    }

    @Override
    public FPane margin (double top, double right, double bottom, double left) {
        if (this.margin == null) {
            this.margin = new Margin(top, right, bottom, left);
        }
        return this;
    }

    @Override
    public FPane margin (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateMarginValues(values);
        if (validated) {
            this.rawMargin = rawValue;
            if (values.length == 1) {
                double margin = PropertyValidatorFilter.getDoubleValue(values[0]);
                this.margin = new Margin(margin,margin,margin,margin);
            } else if (values.length == 2) {
                double topBottom = PropertyValidatorFilter.getDoubleValue(values[0]);
                double leftRight = PropertyValidatorFilter.getDoubleValue(values[1]);
                this.margin = new Margin(topBottom, leftRight, topBottom, leftRight);
            } else if (values.length == 4) {
                double top = PropertyValidatorFilter.getDoubleValue(values[0]);
                double right = PropertyValidatorFilter.getDoubleValue(values[1]);
                double bottom = PropertyValidatorFilter.getDoubleValue(values[2]);
                double left = PropertyValidatorFilter.getDoubleValue(values[3]);
                this.margin = new Margin(top, right, bottom, left);
            }
        }
        return this;
    }

    @Override
    public Margin getMargin(){
        return margin;
    }

    @Override
    public String getRawMargin () {
        return rawMargin;
    }

    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    // *********************************************************************************************
    // **********************************************************
    //                                                          *
    //                      Custom properties                   *
    //                                                          *
    // **********************************************************
    // ******************************************
    //            Background properties         *
    // ******************************************
    @Override
    public FPane bgColor (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateColorValue(values);
        if (validated) {
            this.rawBgColor = rawValue;
            String value = values[0];
            String currentColor = BackgroundSetter.getBackgroundHexColor(this);
            if (currentColor != null) {
                currentColor = "#" + currentColor;
            } else {
                currentColor = "#00000000";
                initialValues.put("bgColor", currentColor);
            }
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setColorAnimation(currentColor, initialValues, "bgColor", hoverAnimation, FAnimations.BG_COLOR, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setColorAnimation(currentColor, initialValues, "bgColor", clickAnimation, FAnimations.BG_COLOR, value, duration);
            } else {
                initialValues.put("bgColor", value);
                if (duration == 1) {
                    BackgroundSetter.setBackgroundColor(this, value);
                } else {
                    PropertyUtil.setTempColorAnimation(this, currentColor, initialValues, "bgColor", FAnimations.BG_COLOR, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getBgColor () {
        return bgColor;
    }

    @Override
    public String getRawBgColor () {
        return rawBgColor;
    }

    @Override
    public FPane bgOpacity (double opacity) {
        BackgroundSetter.setBackgroundOpacity(this, opacity);
        return this;
    }

    @Override
    public FPane bgOpacity (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOpacityValue(values);
        double value = PropertyValidatorFilter.getDoubleValue(values[0]);
        double currOpacity = BackgroundSetter.getBackgroundOpacity(this);
        if (validated && value >= 0 && value <= 1) {
            this.rawBgOpacity = rawValue;
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(currOpacity, initialValues, "bgOpacity", hoverAnimation, FAnimations.BG_OPACITY, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(currOpacity, initialValues, "bgOpacity", clickAnimation, FAnimations.BG_OPACITY, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setBackgroundOpacity(this, value);
                } else {
                    initialValues.put("bgOpacity", value + "");
                    PropertyUtil.setTempAnimation(this, currOpacity, initialValues, "bgOpacity", FAnimations.BG_OPACITY, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public String getRawBgOpacity () {
        return rawBgOpacity;
    }

    @Override
    public FPane bgRadius (double radius) {
        BackgroundSetter.setBackgroundRadius(this, radius);
        return this;
    }

    @Override
    public FPane bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(this, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FPane bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public FPane bgRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateMultipleDimensionValue(values);
        if (validated) {
            this.rawBgRadius = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterMultipleDimensionValues(values);
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(this);
            if (radii == null) {
                radii = new CornerRadii(0, false);
            }
            if (filteredValues.length == 1) {
                String[] currValue = filteredValues[0].split("\\s+");
                double value = PropertyValidatorFilter.getDoubleValue(currValue[0]);
                String unit = PropertyValidatorFilter.getUnit(currValue[0]);
                long duration = 1;
                if (currValue.length == 2) {
                    duration = PropertyValidatorFilter.getDuration(currValue[1]);
                }
                if (unit.equals("%")) {
                    bgRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                    bgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                        BackgroundSetter.setBackgroundRadius(this, (double) newVal);
                    });
                    value = (value / 100) * getPrefWidth();
                }
                if (setPropertiesOnHover) {
                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, "bgRadius", hoverAnimation, FAnimations.BG_RADIUS, value, duration);
                } else if (setPropertiesOnClick) {
                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, "bgRadius", clickAnimation, FAnimations.BG_RADIUS, value, duration);
                } else {
                    if (duration == 1) {
                        BackgroundSetter.setBackgroundRadius(this, value);
                    } else {
                        PropertyUtil.setTempAnimation(this, radii.getTopLeftVerticalRadius(), initialValues, "bgRadius", FAnimations.BG_RADIUS, value, duration);
                    }
                }
            } else if (filteredValues.length == 4) {
                List<String> radiusAnimationSides = new ArrayList<>(List.of(FAnimations.BG_TL_RADIUS, FAnimations.BG_TR_RADIUS, FAnimations.BG_BR_RADIUS, FAnimations.BG_BL_RADIUS));
                List<Double> currRadiusValues = new ArrayList<>(List.of(radii.getTopLeftHorizontalRadius(), radii.getTopRightHorizontalRadius(), radii.getBottomRightHorizontalRadius(), radii.getBottomLeftHorizontalRadius()));
                List<String> bgRadiusInitialValues = new ArrayList<>(List.of("tlBgRadius", "trBgRadius", "brBgRadius", "blBgRadius"));
                for (int i = 0; i < filteredValues.length; i++) {
                    String[] currValue = filteredValues[i].split("\\s+");
                    double value = PropertyValidatorFilter.getDoubleValue(currValue[0]);
                    String unit = PropertyValidatorFilter.getUnit(currValue[0]);
                    long duration = 1;
                    if (currValue.length == 2) {
                        duration = PropertyValidatorFilter.getDuration(currValue[1]);
                    }
                    if (unit.equals("%")) {
                        bgRadiusProperties.get(i).bind(prefWidthProperty().multiply(value / 100));
                        if (i == 0) {
                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BackgroundSetter.setTopLeftBackgroundRadius(this, (double) newVal);
                            });
                        }
                        if (i == 1) {
                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BackgroundSetter.setTopRightBackgroundRadius(this, (double) newVal);
                            });
                        }
                        if (i == 2) {
                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BackgroundSetter.setBottomRightBackgroundRadius(this, (double) newVal);
                            });
                        }
                        if (i == 3) {
                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BackgroundSetter.setBottomLeftBackgroundRadius(this, (double) newVal);
                            });
                        }
                        value = (value / 100) * this.getPrefWidth();
                    }
                    if (setPropertiesOnHover) {
                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), hoverAnimation, radiusAnimationSides.get(i), value, duration);
                    } else if (setPropertiesOnClick) {
                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), clickAnimation, radiusAnimationSides.get(i), value, duration);
                    } else {
                        if (duration == 1) {
                            if (i == 0) {
                                BackgroundSetter.setTopLeftBackgroundRadius(this, value);
                            }
                            if (i == 1) {
                                BackgroundSetter.setTopRightBackgroundRadius(this, value);
                            }
                            if (i == 2) {
                                BackgroundSetter.setBottomRightBackgroundRadius(this, value);
                            }
                            if (i == 3) {
                                BackgroundSetter.setBottomLeftBackgroundRadius(this, value);
                            }
                        } else {
                            PropertyUtil.setTempAnimation(this, currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), radiusAnimationSides.get(i), value, duration);
                        }
                    }
                }
            }
        }
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public String getRawBgRadius () {
        return rawBgRadius;
    }

    @Override
    public FPane topLeftBgRadius (double radius) {
        BackgroundSetter.setTopLeftBackgroundRadius(this, radius);
        return this;
    }

    @Override
    public FPane topLeftBgRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawTopLeftBgRadius = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            double tlBgRadius = 0;
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(this);
            if (radii != null) {
                tlBgRadius = radii.getTopLeftHorizontalRadius();
            }
            if (unit.equals("%")) {
                tlBgRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                tlBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setTopLeftBackgroundRadius(this, (double) newVal);
                });
                value = (value / 100) * getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(tlBgRadius, initialValues, "tlBgRadius", hoverAnimation, FAnimations.BG_TL_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(tlBgRadius, initialValues, "tlBgRadius", clickAnimation, FAnimations.BG_TL_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setTopLeftBackgroundRadius(this, value);
                } else {
                    PropertyUtil.setTempAnimation(this, tlBgRadius, initialValues, "tlBgRadius", FAnimations.BG_TL_RADIUS, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getTopLeftBgRadius () {
        return 0;
    }

    @Override
    public String getRawTopLeftBgRadius () {
        return rawTopLeftBgRadius;
    }

    @Override
    public FPane topRightBgRadius (double radius) {
        BackgroundSetter.setTopRightBackgroundRadius(this, radius);
        return this;
    }

    @Override
    public FPane topRightBgRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawTopRightBgRadius = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            double trBgRadius = 0;
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(this);
            if (radii != null) {
                trBgRadius = radii.getTopRightHorizontalRadius();
            }
            if (unit.equals("%")) {
                trBgRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                trBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setTopRightBackgroundRadius(this, (double) newVal);
                });
                value = (value / 100) * getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(trBgRadius, initialValues, "trBgRadius", hoverAnimation, FAnimations.BG_TR_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(trBgRadius, initialValues, "trBgRadius", clickAnimation, FAnimations.BG_TR_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setTopRightBackgroundRadius(this, value);
                } else {
                    PropertyUtil.setTempAnimation(this, trBgRadius, initialValues, "trBgRadius", FAnimations.BG_TR_RADIUS, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getTopRightBgRadius () {
        return 0;
    }

    @Override
    public String getRawTopRightBgRadius () {
        return rawTopRightBgRadius;
    }

    @Override
    public FPane bottomRightBgRadius (double radius) {
        BackgroundSetter.setBottomRightBackgroundRadius(this, radius);
        return this;
    }

    @Override
    public FPane bottomRightBgRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawBottomRightBgRadius = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            double brBgRadius = 0;
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(this);
            if (radii != null) {
                brBgRadius = radii.getBottomRightHorizontalRadius();
            }
            if (unit.equals("%")) {
                brBgRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                brBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setBottomRightBackgroundRadius(this, (double) newVal);
                });
                value = (value / 100) * getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(brBgRadius, initialValues, "brBgRadius", hoverAnimation, FAnimations.BG_BR_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(brBgRadius, initialValues, "brBgRadius", clickAnimation, FAnimations.BG_BR_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setBottomRightBackgroundRadius(this, value);
                } else {
                    PropertyUtil.setTempAnimation(this, brBgRadius, initialValues, "brBgRadius", FAnimations.BG_BR_RADIUS, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getBottomRightBgRadius () {
        return 0;
    }

    @Override
    public String getRawBottomRightBgRadius () {
        return rawBottomRightBgRadius;
    }

    @Override
    public FPane bottomLeftBgRadius (double radius) {
        BackgroundSetter.setBottomLeftBackgroundRadius(this, radius);
        return this;
    }

    @Override
    public FPane bottomLeftBgRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawBottomLeftBgRadius = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            double blBgRadius = 0;
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(this);
            if (radii != null) {
                blBgRadius = radii.getBottomLeftHorizontalRadius();
            }
            if (unit.equals("%")) {
                blBgRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                blBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setBottomLeftBackgroundRadius(this, (double) newVal);
                });
                value = (value / 100) * getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(blBgRadius, initialValues, "blBgRadius", hoverAnimation, FAnimations.BG_BL_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(blBgRadius, initialValues, "blBgRadius", clickAnimation, FAnimations.BG_BL_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setBottomLeftBackgroundRadius(this, value);
                } else {
                    PropertyUtil.setTempAnimation(this, blBgRadius, initialValues, "blBgRadius", FAnimations.BG_BL_RADIUS, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getBottomLeftBgRadius () {
        return 0;
    }

    @Override
    public String getRawBottomLeftBgRadius () {
        return rawBottomLeftBgRadius;
    }

    @Override
    public FPane bgInsets (double insets) {
        BackgroundSetter.setInsets(this, insets);
        return this;
    }

    @Override
    public FPane bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FPane bgInsets (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateInsetsValues(values);
        if (validated) {
            this.rawBgInsets = rawValue;
            if (values.length == 1) {
                double insets = PropertyValidatorFilter.getDoubleValue(values[0]);
                BackgroundSetter.setInsets(this, insets);
            } else if (values.length == 2) {
                double topBottom = PropertyValidatorFilter.getDoubleValue(values[0]);
                double leftRight = PropertyValidatorFilter.getDoubleValue(values[1]);
                BackgroundSetter.setInsets(this, topBottom, leftRight);
            } else if (values.length == 4) {
                double top = PropertyValidatorFilter.getDoubleValue(values[0]);
                double right = PropertyValidatorFilter.getDoubleValue(values[1]);
                double bottom = PropertyValidatorFilter.getDoubleValue(values[2]);
                double left = PropertyValidatorFilter.getDoubleValue(values[3]);
                BackgroundSetter.setInsets(this, top, right, bottom, left);
            }
        }
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public String getRawBgInsets () {
        return rawBgInsets;
    }

    @Override
    public FPane topInset (double inset) {
        BackgroundSetter.setTopInset(this, inset);
        return this;
    }

    @Override
    public double getTopInset () {
        return 0;
    }

    @Override
    public FPane rightInset (double inset) {
        BackgroundSetter.setRightInset(this, inset);
        return this;
    }

    @Override
    public double getRightInset () {
        return 0;
    }

    @Override
    public FPane bottomInset (double inset) {
        BackgroundSetter.setBottomInset(this, inset);
        return this;
    }

    @Override
    public double getBottomInset () {
        return 0;
    }

    @Override
    public FPane leftInset (double inset) {
        BackgroundSetter.setLeftInset(this, inset);
        return this;
    }

    @Override
    public double getLeftInset () {
        return 0;
    }

    // ******************************************
    //            Background properties         *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Border properties           *
    // ******************************************
    @Override
    public FPane border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(this, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FPane border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(this, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FPane border (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateBorderValue(values);
        if(validated){
            this.rawBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getBorderHexColor(this);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getTopBorderStyle(this);
            double radius = 0;
            double currentRadius = 0;
            String radiusUnit = "px";
            long radiusDuration = 1;
            double width = 0;
            double currentWidth = 0;
            String widthUnit = "px";
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = "#" + currentColor;
            }
            else{
                currentColor = "#00000000";
                initialValues.put("brColor", currentColor);
            }
            if(filteredValues[0].contains(" ")){
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put("brStyle", currentStyle);
            }
            if(BorderSetter.getBorderRadius(this) != null){
                currentRadius = BorderSetter.getBorderRadius(this).getTopLeftHorizontalRadius();
            }
            if(BorderSetter.getBorderWidths(this) != null){
                currentWidth = BorderSetter.getBorderWidths(this).getTop();
            }
            if(filteredValues.length > 2){
                String radiusProp = filteredValues[2].split("\\s+")[0];
                radius = PropertyValidatorFilter.getDoubleValue(radiusProp);
                radiusUnit = PropertyValidatorFilter.getUnit(radiusProp);
                if(filteredValues[2].contains(" ")){
                    radiusDuration = PropertyValidatorFilter.getDuration(filteredValues[2].split("\\s+")[1]);
                }
                String widthProp = filteredValues[3].split("\\s+")[0];
                width = PropertyValidatorFilter.getDoubleValue(widthProp);
                widthUnit = PropertyValidatorFilter.getUnit(widthProp);
                if(filteredValues[3].contains(" ")){
                    widthDuration = PropertyValidatorFilter.getDuration(filteredValues[3].split("\\s+")[1]);
                }
            }
            if(radiusUnit.equals("%")){
                brRadiusProperty.bind(prefWidthProperty().multiply(radius / 100));
                brRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBorderRadius(this, (double) newVal);
                });
                radius = (radius / 100) * getPrefWidth();
            }
            if(widthUnit.equals("%")){
                brWidthProperty.bind(prefWidthProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBorderWidth(this, (double) newVal);
                });
                width = (width / 100) * getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brColor", hoverAnimation, FAnimations.BR_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brStyle", staticHoverAnimation, "brStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentRadius, initialValues, "brRadius", hoverAnimation, FAnimations.BR_RADIUS, radius, radiusDuration);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brWidth", hoverAnimation, FAnimations.BR_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brColor", clickAnimation, FAnimations.BR_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brStyle", staticClickAnimation, "brStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentRadius, initialValues, "brRadius", clickAnimation, FAnimations.BR_RADIUS, radius, radiusDuration);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brWidth", clickAnimation, FAnimations.BR_WIDTH, width, widthDuration);
            }
            else{
                if(colorDuration == 1){
                    BorderSetter.setBorderColor(this, color);
                    initialValues.put("brColor", color);
                }
                else{
                    PropertyUtil.setTempColorAnimation(this, currentColor, initialValues, "brColor", FAnimations.BR_COLOR, color, colorDuration);
                }
                BorderSetter.setBorderStyle(this, borderStyle);
                if(radiusDuration == 1){
                    BorderSetter.setBorderRadius(this, radius);
                }
                else{
                    PropertyUtil.setTempAnimation(this, currentRadius, initialValues, "brRadius", FAnimations.BR_RADIUS, radius, radiusDuration);
                }
                if(widthDuration == 1){
                    BorderSetter.setBorderWidth(this, width);
                }
                else{
                    PropertyUtil.setTempAnimation(this, currentWidth, initialValues, "brWidth", FAnimations.BR_WIDTH, width, widthDuration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawBorder () {
        return rawBorder;
    }

    @Override
    public FPane topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane topBorder (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneSideBorderValue(values);
        if(validated){
            this.rawTopBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterOneSideBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getTopBorderColor(this);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getTopBorderStyle(this);
            double width = 0;
            double currentWidth = BorderSetter.getTopBorderWidth(this);
            String widthUnit = "px";
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = color;
            }
            else{
                currentColor = "#00000000";
                initialValues.put("brTopColor", currentColor);
            }
            if(filteredValues[0].contains(" ")) {
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put("brTopStyle", currentStyle);
            }
            if(filteredValues[1] != null){
                borderStyle = filteredValues[1];
            }
            if(filteredValues.length > 2){
                String widthProp = filteredValues[2].split("\\s+")[0];
                width = PropertyValidatorFilter.getDoubleValue(widthProp);
                widthUnit = PropertyValidatorFilter.getUnit(widthProp);
                if(filteredValues[2].contains(" ")){
                    widthDuration = PropertyValidatorFilter.getDuration(filteredValues[2].split("\\s+")[1]);
                }
            }
            if(widthUnit.equals("%")){
                brWidthProperty.bind(prefHeightProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setTopBorderWidth(this, (double) newVal);
                });
                width = (width / 100) * getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brTopColor", hoverAnimation, FAnimations.BR_TOP_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brTopStyle", staticHoverAnimation, "brTopStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brTopWidth", hoverAnimation, FAnimations.BR_TOP_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brTopColor", clickAnimation, FAnimations.BR_TOP_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brTopStyle", staticClickAnimation, "brTopStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brTopWidth", clickAnimation, FAnimations.BR_TOP_WIDTH, width, widthDuration);
            }
            else{
                PropertyUtil.setTempColorAnimation(this, currentColor, initialValues, "brTopColor", FAnimations.BR_TOP_COLOR, color, colorDuration);
                BorderSetter.setTopBorderStyle(this, borderStyle);
                if(widthDuration == 1){
                    BorderSetter.setTopBorderWidth(this, width);
                }
                else{
                    PropertyUtil.setTempAnimation(this, currentWidth, initialValues, "brTopWidth", FAnimations.BR_TOP_WIDTH, width, widthDuration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawTopBorder () {
        return rawTopBorder;
    }

    @Override
    public FPane rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane rightBorder (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneSideBorderValue(values);
        if(validated){
            this.rawRightBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterOneSideBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getRightBorderColor(this);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getRightBorderStyle(this);
            double width = 0;
            double currentWidth = BorderSetter.getRightBorderWidth(this);
            String widthUnit = "px";
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = "#" + currentColor;
            }
            else{
                currentColor = "#00000000";
                initialValues.put("brRightColor", currentColor);
            }
            if(filteredValues[0].contains(" ")) {
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put("brRightStyle", currentStyle);
            }
            if(filteredValues[1] != null){
                borderStyle = filteredValues[1];
            }
            if(filteredValues.length > 2){
                String widthProp = filteredValues[2].split("\\s+")[0];
                width = PropertyValidatorFilter.getDoubleValue(widthProp);
                widthUnit = PropertyValidatorFilter.getUnit(widthProp);
                if(filteredValues[2].contains(" ")){
                    widthDuration = PropertyValidatorFilter.getDuration(filteredValues[2].split("\\s+")[1]);
                }
            }
            if(widthUnit.equals("%")){
                brWidthProperty.bind(prefWidthProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setRightBorderWidth(this, (double) newVal);
                });
                width = (width / 100) * getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brRightColor", hoverAnimation, FAnimations.BR_RIGHT_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brRightStyle", staticHoverAnimation, "brRightStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brRightWidth", hoverAnimation, FAnimations.BR_RIGHT_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brRightColor", clickAnimation, FAnimations.BR_RIGHT_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brRightStyle", staticClickAnimation, "brRightStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brRightWidth", clickAnimation, FAnimations.BR_RIGHT_WIDTH, width, widthDuration);
            }
            else{
                PropertyUtil.setTempColorAnimation(this, currentColor, initialValues, "brRightColor", FAnimations.BR_RIGHT_COLOR, color, colorDuration);
                BorderSetter.setRightBorderStyle(this, borderStyle);
                if(widthDuration == 1){
                    BorderSetter.setRightBorderWidth(this, width);
                }
                else{
                    PropertyUtil.setTempAnimation(this, currentWidth, initialValues, "brRightWidth", FAnimations.BR_RIGHT_WIDTH, width, widthDuration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawRightBorder () {
        return rawRightBorder;
    }

    @Override
    public FPane bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane bottomBorder (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneSideBorderValue(values);
        if(validated){
            this.rawBottomBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterOneSideBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getBottomBorderColor(this);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getBottomBorderStyle(this);
            double width = 0;
            double currentWidth = BorderSetter.getBottomBorderWidth(this);
            String widthUnit = "px";
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = "#" + currentColor;
            }
            else{
                currentColor = "#00000000";
                initialValues.put("brBottomColor", currentColor);
            }
            if(filteredValues[0].contains(" ")) {
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put("brBottomStyle", currentStyle);
            }
            if(filteredValues[1] != null){
                borderStyle = filteredValues[1];
            }
            if(filteredValues.length > 2){
                String widthProp = filteredValues[2].split("\\s+")[0];
                width = PropertyValidatorFilter.getDoubleValue(widthProp);
                widthUnit = PropertyValidatorFilter.getUnit(widthProp);
                if(filteredValues[2].contains(" ")){
                    widthDuration = PropertyValidatorFilter.getDuration(filteredValues[2].split("\\s+")[1]);
                }
            }
            if(widthUnit.equals("%")){
                brWidthProperty.bind(prefHeightProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBottomBorderWidth(this, (double) newVal);
                });
                width = (width / 100) * getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brBottomColor", hoverAnimation, FAnimations.BR_BOTTOM_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brBottomStyle", staticHoverAnimation, "brBottomStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brBottomWidth", hoverAnimation, FAnimations.BR_BOTTOM_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brBottomColor", clickAnimation, FAnimations.BR_BOTTOM_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brBottomStyle", staticClickAnimation, "brBottomStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brBottomWidth", clickAnimation, FAnimations.BR_BOTTOM_WIDTH, width, widthDuration);
            }
            else{
                PropertyUtil.setTempColorAnimation(this, currentColor, initialValues, "brBottomColor", FAnimations.BR_BOTTOM_COLOR, color, colorDuration);
                BorderSetter.setBottomBorderStyle(this, borderStyle);
                if(widthDuration == 1){
                    BorderSetter.setBottomBorderWidth(this, width);
                }
                else{
                    PropertyUtil.setTempAnimation(this, currentWidth, initialValues, "brBottomWidth", FAnimations.BR_BOTTOM_WIDTH, width, widthDuration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawBottomBorder () {
        return rawBottomBorder;
    }

    @Override
    public FPane leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane leftBorder (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneSideBorderValue(values);
        if(validated){
            this.rawLeftBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterOneSideBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getLeftBorderColor(this);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getLeftBorderStyle(this);
            double width = 0;
            double currentWidth = BorderSetter.getLeftBorderWidth(this);
            String widthUnit = "px";
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = "#" + currentColor;
            }
            else{
                currentColor = "#00000000";
                initialValues.put("brLeftColor", currentColor);
            }
            if(filteredValues[0].contains(" ")) {
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put("brLeftStyle", currentStyle);
            }
            if(filteredValues[1] != null){
                borderStyle = filteredValues[1];
            }
            if(filteredValues.length > 2){
                String widthProp = filteredValues[2].split("\\s+")[0];
                width = PropertyValidatorFilter.getDoubleValue(widthProp);
                widthUnit = PropertyValidatorFilter.getUnit(widthProp);
                if(filteredValues[2].contains(" ")){
                    widthDuration = PropertyValidatorFilter.getDuration(filteredValues[2].split("\\s+")[1]);
                }
            }
            if(widthUnit.equals("%")){
                brWidthProperty.bind(prefWidthProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setLeftBorderWidth(this, (double) newVal);
                });
                width = (width / 100) * getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brLeftColor", hoverAnimation, FAnimations.BR_LEFT_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brLeftStyle", staticHoverAnimation, "brLeftStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brLeftWidth", hoverAnimation, FAnimations.BR_LEFT_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brLeftColor", clickAnimation, FAnimations.BR_LEFT_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, "brLeftStyle", staticClickAnimation, "brLeftStyle", borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, "brLeftWidth", clickAnimation, FAnimations.BR_LEFT_WIDTH, width, widthDuration);
            }
            else{
                PropertyUtil.setTempColorAnimation(this, currentColor, initialValues, "brLeftColor", FAnimations.BR_LEFT_COLOR, color, colorDuration);
                BorderSetter.setLeftBorderStyle(this, borderStyle);
                if(widthDuration == 1){
                    BorderSetter.setLeftBorderWidth(this, width);
                }
                else{
                    PropertyUtil.setTempAnimation(this, currentWidth, initialValues, "brLeftWidth", FAnimations.BR_LEFT_WIDTH, width, widthDuration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawLeftBorder () {
        return rawLeftBorder;
    }

    @Override
    public FPane borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public String getBorderStyle () {
        return "";
    }

    @Override
    public FPane borderWidth (double width) {
        BorderSetter.setBorderWidth(this, width);
        return this;
    }

    @Override
    public FPane borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FPane borderWidth (String borderWidth) {
        String[] values = borderWidth.split("\\s+");
        return this;
    }

    @Override
    public String getRawBorderWidth ()  {
        return rawBorderWidth;
    }

    @Override
    public FPane borderColor (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateColorValue(values);
        if (validated) {
            this.rawBorderColor = rawValue;
            String value = values[0];
            String currentColor = "#" + BorderSetter.getBorderHexColor(this);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brColor", hoverAnimation, FAnimations.BR_COLOR, value, duration);
            }
            else if (setPropertiesOnClick) {
                PropertyUtil.setColorAnimation(currentColor, initialValues, "brColor", clickAnimation, FAnimations.BR_COLOR, value, duration);
            }
            else {
                initialValues.put("brColor", value);
                if (duration == 1) {
                    BorderSetter.setBorderColor(this, value);
                } else {
                    PropertyUtil.setTempColorAnimation(this, currentColor, initialValues, "brColor", FAnimations.BR_COLOR, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getBorderColor () {
        return "";
    }

    @Override
    public String getRawBorderColor () {
        return rawBorderColor;
    }

    @Override
    public FPane borderOpacity (double opacity) {
        BorderSetter.setBorderOpacity(this, opacity);
        return this;
    }

    @Override
    public FPane borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FPane borderOpacity (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOpacityValue(values);
        double value = PropertyValidatorFilter.getDoubleValue(values[0]);
        double currOpacity = BorderSetter.getBorderOpacity(this);
        if (validated && value >= 0 && value <= 1) {
            this.rawBorderOpacity = rawValue;
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(currOpacity, initialValues, "brOpacity", hoverAnimation, FAnimations.BR_OPACITY, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(currOpacity, initialValues, "brOpacity", clickAnimation, FAnimations.BR_OPACITY, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setBorderOpacity(this, value);
                } else {
                    initialValues.put("brOpacity", value + "");
                    PropertyUtil.setTempAnimation(this, currOpacity, initialValues, "brOpacity", FAnimations.BR_OPACITY, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public String getRawBorderOpacity () {
        return rawBorderOpacity;
    }

    @Override
    public FPane borderRadius (double radius) {
        BorderSetter.setBorderRadius(this, radius);
        return this;
    }

    @Override
    public FPane borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public FPane borderRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateMultipleDimensionValue(values);
        if (validated) {
            this.rawBorderRadius = rawValue;
            this.brRadiusProperty = new SimpleDoubleProperty();
            String[] filteredValues = PropertyValidatorFilter.filterMultipleDimensionValues(values);
            CornerRadii radii = BorderSetter.getBorderRadius(this);
            if (radii == null) {
                radii = new CornerRadii(0, false);
            }
            if (filteredValues.length == 1) {
                String[] currValue = filteredValues[0].split("\\s+");
                double value = PropertyValidatorFilter.getDoubleValue(currValue[0]);
                String unit = PropertyValidatorFilter.getUnit(currValue[0]);
                long duration = 1;
                if (currValue.length == 2) {
                    duration = PropertyValidatorFilter.getDuration(currValue[1]);
                }
                if (unit.equals("%")) {
                    brRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                    brRadiusProperty.addListener((valType, oldVal, newVal) -> {
                        BorderSetter.setBorderRadius(this, (double) newVal);
                    });
                    value = (value / 100) * getPrefWidth();
                }
                if (setPropertiesOnHover) {
                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, "brRadius", hoverAnimation, FAnimations.BR_RADIUS, value, duration);
                } else if (setPropertiesOnClick) {
                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, "brRadius", clickAnimation, FAnimations.BR_RADIUS, value, duration);
                } else {
                    if (duration == 1) {
                        BorderSetter.setBorderRadius(this, value);
                    } else {
                        PropertyUtil.setTempAnimation(this, radii.getTopLeftVerticalRadius(), initialValues, "brRadius", FAnimations.BR_RADIUS, value, duration);
                    }
                }
            } else if (filteredValues.length == 4) {
                List<String> radiusAnimationSides = new ArrayList<>(List.of(FAnimations.BR_TL_RADIUS, FAnimations.BR_TR_RADIUS, FAnimations.BR_BR_RADIUS, FAnimations.BR_BL_RADIUS));
                List<Double> currRadiusValues = new ArrayList<>(List.of(radii.getTopLeftHorizontalRadius(), radii.getTopRightHorizontalRadius(), radii.getBottomRightHorizontalRadius(), radii.getBottomLeftHorizontalRadius()));
                List<String> brRadiusInitialValues = new ArrayList<>(List.of("tlBrRadius", "trBrRadius", "brBrRadius", "blBrRadius"));
                for (int i = 0; i < filteredValues.length; i++) {
                    String[] currValue = filteredValues[i].split("\\s+");
                    double value = PropertyValidatorFilter.getDoubleValue(currValue[0]);
                    String unit = PropertyValidatorFilter.getUnit(currValue[0]);
                    long duration = 1;
                    if (currValue.length == 2) {
                        duration = PropertyValidatorFilter.getDuration(currValue[1]);
                    }
                    if (unit.equals("%")) {
                        brRadiusProperties.get(i).bind(prefWidthProperty().multiply(value / 100));
                        if (i == 0) {
                            brRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BorderSetter.setTopLeftBorderRadius(this, (double) newVal);
                            });
                        }
                        if (i == 1) {
                            brRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BorderSetter.setTopRightBorderRadius(this, (double) newVal);
                            });
                        }
                        if (i == 2) {
                            brRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BorderSetter.setBottomRightBorderRadius(this, (double) newVal);
                            });
                        }
                        if (i == 3) {
                            brRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BorderSetter.setBottomLeftBorderRadius(this, (double) newVal);
                            });
                        }
                        value = (value / 100) * this.getPrefWidth();
                    }
                    if (setPropertiesOnHover) {
                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, brRadiusInitialValues.get(i), hoverAnimation, radiusAnimationSides.get(i), value, duration);
                    } else if (setPropertiesOnClick) {
                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, brRadiusInitialValues.get(i), clickAnimation, radiusAnimationSides.get(i), value, duration);
                    } else {
                        if (duration == 1) {
                            if (i == 0) {
                                BorderSetter.setTopLeftBorderRadius(this, value);
                            }
                            if (i == 1) {
                                BorderSetter.setTopRightBorderRadius(this, value);
                            }
                            if (i == 2) {
                                BorderSetter.setBottomRightBorderRadius(this, value);
                            }
                            if (i == 3) {
                                BorderSetter.setBottomLeftBorderRadius(this, value);
                            }
                        } else {
                            PropertyUtil.setTempAnimation(this, currRadiusValues.get(i), initialValues, brRadiusInitialValues.get(i), radiusAnimationSides.get(i), value, duration);
                        }
                    }
                }
            }
        }
        return this;
    }

    @Override
    public String getRawBorderRadius () {
        return rawBorderRadius;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public FPane topLeftBorderRadius (double radius) {
        BorderSetter.setTopLeftBorderRadius(this, radius);
        return this;
    }

    @Override
    public FPane topLeftBorderRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawTopLeftBorderRadius = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            double tlBrRadius = 0;
            CornerRadii radii = BorderSetter.getBorderRadius(this);
            if (radii != null) {
                tlBrRadius = radii.getTopLeftHorizontalRadius();
            }
            if (unit.equals("%")) {
                tlBrRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                tlBrRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setTopLeftBorderRadius(this, (double) newVal);
                });
                value = (value / 100) * getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(tlBrRadius, initialValues, "tlBrRadius", hoverAnimation, FAnimations.BR_TL_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(tlBrRadius, initialValues, "tlBrRadius", clickAnimation, FAnimations.BR_TL_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setTopLeftBorderRadius(this, value);
                } else {
                    PropertyUtil.setTempAnimation(this, tlBrRadius, initialValues, "tlBrRadius", FAnimations.BR_TL_RADIUS, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getTopLeftBorderRadius () {
        return 0;
    }

    @Override
    public String getRawTopLeftBorderRadius () {
        return rawTopLeftBorderRadius;
    }

    @Override
    public FPane topRightBorderRadius (double radius) {
        BorderSetter.setTopRightBorderRadius(this, radius);
        return this;
    }

    @Override
    public FPane topRightBorderRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawTopRightBorderRadius = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            double trBrRadius = 0;
            CornerRadii radii = BorderSetter.getBorderRadius(this);
            if (radii != null) {
                trBrRadius = radii.getTopRightHorizontalRadius();
            }
            if (unit.equals("%")) {
                trBrRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                trBrRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setTopRightBorderRadius(this, (double) newVal);
                });
                value = (value / 100) * getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(trBrRadius, initialValues, "trBrRadius", hoverAnimation, FAnimations.BR_TR_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(trBrRadius, initialValues, "trBrRadius", clickAnimation, FAnimations.BR_TR_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setTopRightBorderRadius(this, value);
                } else {
                    PropertyUtil.setTempAnimation(this, trBrRadius, initialValues, "trBrRadius", FAnimations.BR_TR_RADIUS, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getTopRightBorderRadius () {
        return 0;
    }

    @Override
    public String getRawTopRightBorderRadius () {
        return rawTopRightBorderRadius;
    }

    @Override
    public FPane bottomRightBorderRadius (double radius) {
        BorderSetter.setBottomRightBorderRadius(this, radius);
        return this;
    }

    @Override
    public FPane bottomRightBorderRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawBottomRightBorderRadius = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            double brBrRadius = 0;
            CornerRadii radii = BorderSetter.getBorderRadius(this);
            if (radii != null) {
                brBrRadius = radii.getBottomRightHorizontalRadius();
            }
            if (unit.equals("%")) {
                brBrRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                brBrRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBottomRightBorderRadius(this, (double) newVal);
                });
                value = (value / 100) * getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(brBrRadius, initialValues, "brBrRadius", hoverAnimation, FAnimations.BR_BR_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(brBrRadius, initialValues, "brBrRadius", clickAnimation, FAnimations.BR_BR_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setBottomRightBorderRadius(this, value);
                } else {
                    PropertyUtil.setTempAnimation(this, brBrRadius, initialValues, "brBrRadius", FAnimations.BR_BR_RADIUS, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getBottomRightBorderRadius () {
        return 0;
    }

    @Override
    public String getRawBottomRightBorderRadius () {
        return rawBottomRightBorderRadius;
    }

    @Override
    public FPane bottomLeftBorderRadius (double radius) {
        BorderSetter.setBottomLeftBorderRadius(this, radius);
        return this;
    }

    @Override
    public FPane bottomLeftBorderRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawBottomLeftBorderRadius = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            double blBrRadius = 0;
            CornerRadii radii = BorderSetter.getBorderRadius(this);
            if (radii != null) {
                blBrRadius = radii.getBottomLeftHorizontalRadius();
            }
            if (unit.equals("%")) {
                blBrRadiusProperty.bind(prefWidthProperty().multiply(value / 100));
                blBrRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBottomLeftBorderRadius(this, (double) newVal);
                });
                value = (value / 100) * getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(blBrRadius, initialValues, "blBrRadius", hoverAnimation, FAnimations.BR_BL_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(blBrRadius, initialValues, "blBrRadius", clickAnimation, FAnimations.BR_BL_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setBottomLeftBorderRadius(this, value);
                } else {
                    PropertyUtil.setTempAnimation(this, blBrRadius, initialValues, "blBrRadius", FAnimations.BR_BL_RADIUS, value, duration);
                }
            }
        }
        return this;
    }

    @Override
    public double getBottomLeftBorderRadius () {
        return 0;
    }

    @Override
    public String getRawBottomLeftBorderRadius () {
        return rawBottomLeftBorderRadius;
    }

    @Override
    public FPane borderInsets (double inset) {
        BorderSetter.setBorderInsets(this, inset);
        return this;
    }

    @Override
    public FPane borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FPane borderInsets (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateInsetsValues(values);
        if (validated) {
            this.rawBorderInsets = rawValue;
            if (values.length == 1) {
                double insets = PropertyValidatorFilter.getDoubleValue(values[0]);
                BorderSetter.setBorderInsets(this, insets);
            } else if (values.length == 2) {
                double topBottom = PropertyValidatorFilter.getDoubleValue(values[0]);
                double leftRight = PropertyValidatorFilter.getDoubleValue(values[1]);
                BorderSetter.setBorderInsets(this, topBottom, leftRight, topBottom, leftRight);
            } else if (values.length == 4) {
                double top = PropertyValidatorFilter.getDoubleValue(values[0]);
                double right = PropertyValidatorFilter.getDoubleValue(values[1]);
                double bottom = PropertyValidatorFilter.getDoubleValue(values[2]);
                double left = PropertyValidatorFilter.getDoubleValue(values[3]);
                BorderSetter.setBorderInsets(this, top, right, bottom, left);
            }
        }
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public String getRawBorderInsets () {
        return rawBorderInsets;
    }

    @Override
    public void removeBorder () {
        BorderSetter.removeBorder(this);
        initialValues.remove("border");
    }

    @Override
    public void removeTopBorder () {
        BorderSetter.removeTopBorder(this);
        initialValues.remove("topBorder");
    }

    @Override
    public void removeRightBorder () {
        BorderSetter.removeRightBorder(this);
        initialValues.remove("rightBorder");
    }

    @Override
    public void removeBottomBorder () {
        BorderSetter.removeBottomBorder(this);
        initialValues.remove("bottomBorder");
    }

    @Override
    public void removeLeftBorder () {
        BorderSetter.removeLeftBorder(this);
        initialValues.remove("leftBorder");
    }

    // ******************************************
    //              Border properties           *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //                UI properties             *
    // ******************************************
    public void add (Node node) {
        this.getChildren().add(node);
    }
    // ******************************************
    //                UI properties             *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Custom listeners            *
    // ******************************************
    public void resizeListener (ResizeListener resizeListener) {
        if (resizeWidthListener == null || resizeHeightListener == null) {
            resizeWidthListener = (valType, oldVal, newVal) -> {
                notifyResizeListeners((double) newVal, getPrefHeight());
            };
            resizeHeightListener = (valType, oldVal, newVal) -> {
                notifyResizeListeners(getPrefWidth(), (double) newVal);
            };
            prefWidthProperty().addListener(resizeWidthListener);
            prefHeightProperty().addListener(resizeHeightListener);
        }
        resizeListeners.add(resizeListener);
    }

    public void removeResizeListener (ResizeListener resizeListener) {
        if (resizeListeners != null) {
            resizeListeners.remove(resizeListener);
        }
        if (resizeListeners != null && resizeListeners.isEmpty()) {
            prefWidthProperty().removeListener(resizeWidthListener);
            prefHeightProperty().removeListener(resizeHeightListener);
            resizeWidthListener = null;
            resizeHeightListener = null;
        }
    }

    private void notifyResizeListeners (double width, double height) {
        if (resizeListeners != null && !resizeListeners.isEmpty()) {
            resizeListeners.forEach(resizeListener -> {
                resizeListener.apply(width, height);
            });
        }
    }
    // ******************************************
    //              Custom listeners            *
    // ******************************************
    // **********************************************************
    //                                                          *
    //                      Custom properties                   *
    //                                                          *
    // **********************************************************
}
