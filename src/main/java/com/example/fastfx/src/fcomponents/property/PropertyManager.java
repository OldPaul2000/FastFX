package com.example.fastfx.src.fcomponents.property;

import com.example.fastfx.src.fcomponents.flistener.ResizeListener;
import com.example.fastfx.src.fcomponents.pseudoclass.PseudoClass;
import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.FAnimations;
import com.example.fastfx.src.style.animations.StaticAnimation;
import com.example.fastfx.src.style.animations.StaticAnimations;
import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;

import java.util.*;

/**
 * Helps with the required logic to set the properties for the specific component.
 */
public class PropertyManager {
    
    private Region component;
    private Region parent;
    
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
    private String rawTop;
    private String rawRight;
    private String rawBottom;
    private String rawLeft;

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

    private ChangeListener<Number> resizeWidthListeners;
    private ChangeListener<Number> resizeHeightListeners;
    private Set<ResizeListener> resizeListeners;

    private Map<String, InvalidationListener> doublePropertiesListeners;
    private DelayedTaskManager delayedTaskManager;
    private PropertyParser parser;

    public PropertyManager (Region component) {
        this.component = component;
        this.parent = (Region) component.getParent();
        this.initialValues = new HashMap<>();
        initBgRadiusProperties();
        initBrRadiusProperties();
        initBrWidthProperties();
        resizeListeners = new HashSet<>();
        doublePropertiesListeners = new HashMap<>();
        delayedTaskManager = new DelayedTaskManager();
        parser = new PropertyParser();
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

    public void hover (PseudoClass hover) {
        if (hoverAnimation == null) {
            hoverAnimation = new FAnimation(component);
        }
        if (staticHoverAnimation == null){
            staticHoverAnimation = new StaticAnimation(component);
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
        component.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnterEvent);
        component.addEventHandler(MouseEvent.MOUSE_EXITED, mouseExitEvent);
    }

    public void click (PseudoClass click) {
        if (clickAnimation == null) {
            clickAnimation = new FAnimation(component);
        }
        if(staticClickAnimation == null){
            staticClickAnimation = new StaticAnimation(component);
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
        component.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEvent);
        component.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedEvent);
    }
    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    public void x (double value) {
        initialValues.put(MainProps.X, value + "");
        if (setPropertiesOnHover){
            if(hoverAnimation != null){
                hoverAnimation.removeAnimation(FAnimations.LAYOUT_X);
            }
            staticHoverAnimation.addAnimation(StaticAnimations.LAYOUT_X, component.getLayoutX() + "", value + "");
        }
        if (setPropertiesOnClick){
            if(clickAnimation != null){
                clickAnimation.removeAnimation(FAnimations.LAYOUT_X);
            }
            staticClickAnimation.addAnimation(StaticAnimations.LAYOUT_X, component.getLayoutX() + "", value + "");
        }
        else{
            component.setLayoutX(value);
        }
    }
    
    public void x (double value, String unit) {
        if (unit.equals(FUnit.PIXELS) || unit.isEmpty()) {
            component.setLayoutX(value);
        } else if (unit.equals(FUnit.PERCENTAGE)) {
            if (parent == null) {
                System.out.println("parent is null");
                delayedTaskManager.removeTaskById("x");
                delayedTaskManager.addTask("x", (p) -> {
                    System.out.println(p.getPrefWidth());
                    component.setLayoutX(p.getPrefWidth() * (value / 100));
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        component.setLayoutX((double)newVal * (value / 100));
                    };
                    addPropertyListener(p, "width", listener);
                });
            } else {
                component.setLayoutX(parent.getPrefWidth() * (value / 100));
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    component.setLayoutX((double)newVal * (value / 100));
                };
                addPropertyListener(parent, "width", listener);
                parent.prefWidthProperty().addListener(listener);
            }
        }
    }
    
    public void x (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneDimensionValue(values);
        if (validated) {
            this.rawX = rawValue;
            double value = PropertyValidatorFilter.getDoubleValue(values[0]);
            System.out.println("rawVla: " + rawValue);
            System.out.println("value: " + value);
            String unit = PropertyValidatorFilter.getUnit(values[0]);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                if(parent != null){
                    System.out.println("width: " + parent.getPrefWidth());
                    component.setLayoutX(parent.getPrefWidth() * (value / 100));
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        System.out.println("newVal: " + newVal);
                        component.setLayoutX((double)newVal * (value / 100));
                    };
                    addPropertyListener(parent, "width", listener);
                    parent.prefWidthProperty().addListener(listener);
                }
                else{

                }
            }
            if (setPropertiesOnHover) {
                staticHoverAnimation.removeAnimation(StaticAnimations.LAYOUT_X);
                initialValues.remove(MainProps.X);
                PropertyUtil.setOneDimensionAnimation(component.getLayoutX(), initialValues, MainProps.X, hoverAnimation, FAnimations.LAYOUT_X, value, duration);
            } else if (setPropertiesOnClick) {
                staticClickAnimation.removeAnimation(StaticAnimations.LAYOUT_X);
                initialValues.remove(MainProps.X);
                PropertyUtil.setOneDimensionAnimation(component.getLayoutX(), initialValues, MainProps.X, clickAnimation, FAnimations.LAYOUT_X, value, duration);
            } else {
                if (duration == 1) {
                    component.setLayoutX(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getLayoutX(), initialValues, MainProps.X, FAnimations.LAYOUT_X, value, duration);
                }
            }
        }
    }
    
    public double getX () {
        return component.getLayoutX();
    }

    public String getRawX () {
        return rawX;
    }

    public void y (double value) {
        initialValues.put(MainProps.Y, value + "");
        component.setLayoutY(value);
    }

    public void y (double value, String unit) {
        if (unit.equals(FUnit.PIXELS) || unit.isEmpty()) {
            component.setLayoutY(value);
        } else if (unit.equals(FUnit.PERCENTAGE)) {
            if (parent == null) {

            } else {
                component.layoutYProperty().bind(parent.prefHeightProperty().multiply(value / 100));
            }
        }
    }

    public void y (String rawValue) {
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
            if (unit.equals(FUnit.PERCENTAGE)) {

            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(component.getLayoutY(), initialValues, MainProps.Y, hoverAnimation, FAnimations.LAYOUT_Y, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(component.getLayoutY(), initialValues, MainProps.Y, clickAnimation, FAnimations.LAYOUT_Y, value, duration);
            } else {
                if (duration == 1) {
                    component.setLayoutY(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getLayoutY(), initialValues, MainProps.Y, FAnimations.LAYOUT_Y, value, duration);
                }
            }
        }
    }

    public double getY () {
        return component.getLayoutY();
    }

    public String getRawY () {
        return rawY;
    }

    
    public void width (double value) {
        initialValues.put(MainProps.WIDTH, value + "");
        component.setPrefWidth(value);
    }
    
    public void width (String rawValue) {
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
            if (unit.equals(FUnit.PERCENTAGE)) {

            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(component.getPrefWidth(), initialValues, MainProps.WIDTH, hoverAnimation, FAnimations.WIDTH, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(component.getLayoutY(), initialValues, MainProps.WIDTH, clickAnimation, FAnimations.WIDTH, value, duration);
            } else {
                if (duration == 1) {
                    component.setPrefWidth(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getPrefWidth(), initialValues, MainProps.WIDTH, FAnimations.WIDTH, value, duration);
                }
            }
        }
    }
    
    public String getRawWidth () {
        return rawWidth;
    }

    public void height (double value) {
        initialValues.put(MainProps.HEIGHT, value + "");
        component.setPrefHeight(value);
    }

    public void height (String rawValue) {
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
            if (unit.equals(FUnit.PERCENTAGE)) {

            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(component.getPrefHeight(), initialValues, MainProps.HEIGHT, hoverAnimation, FAnimations.HEIGHT, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(component.getPrefHeight(), initialValues, MainProps.HEIGHT, clickAnimation, FAnimations.HEIGHT, value, duration);
            } else {
                if (duration == 1) {
                    component.setPrefHeight(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getPrefHeight(), initialValues, MainProps.HEIGHT, FAnimations.HEIGHT, value, duration);
                }
            }
        }
    }

    public String getRawHeight () {
        return rawHeight;
    }

    public void scaleX (double value) {
        initialValues.put(MainProps.SCALE_X, value + "");
        component.setScaleX(value);
    }
    
    public void scaleX (String rawValue) {
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
                PropertyUtil.setOneDimensionAnimation(component.getScaleX(), initialValues, MainProps.SCALE_X, hoverAnimation, FAnimations.SCALE_X, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(component.getScaleX(), initialValues, MainProps.SCALE_X, clickAnimation, FAnimations.SCALE_X, value, duration);
            } else {
                if (duration == 1) {
                    component.setScaleX(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getScaleX(), initialValues, MainProps.SCALE_X, FAnimations.SCALE_X, value, duration);
                }
            }
        }
    }
    
    public String getRawScaleX () {
        return rawScaleX;
    }

    public void scaleY (double value) {
        initialValues.put(MainProps.SCALE_Y, value + "");
        component.setScaleY(value);
    }
    
    public void scaleY (String rawValue) {
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
                PropertyUtil.setOneDimensionAnimation(component.getScaleY(), initialValues, MainProps.SCALE_Y, hoverAnimation, FAnimations.SCALE_Y, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(component.getScaleY(), initialValues, MainProps.SCALE_Y, clickAnimation, FAnimations.SCALE_Y, value, duration);
            } else {
                if (duration == 1) {
                    component.setScaleY(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getScaleY(), initialValues, MainProps.SCALE_Y, FAnimations.SCALE_Y, value, duration);
                }
            }
        }
    }

    public String getRawScaleY () {
        return rawScaleY;
    }

    public void translateX (double value) {
        initialValues.put(MainProps.TRANSLATE_X, value + "");
        component.setTranslateX(value);
    }
    
    public void translateX (double value, String unit) {
        if (unit.equals(FUnit.PIXELS) || unit.isEmpty()) {
            component.setTranslateX(value);
        } else if (unit.equals(FUnit.PERCENTAGE)) {
            component.setTranslateX(component.getPrefWidth() * (value / 100));
        }
    }

    public void translateX (String rawValue) {
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
            if (unit.equals(FUnit.PERCENTAGE)) {
                value = component.getPrefWidth() * (value / 100);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(component.getTranslateX(), initialValues, MainProps.TRANSLATE_X, hoverAnimation, FAnimations.TRANSLATE_X, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(component.getTranslateX(), initialValues, MainProps.TRANSLATE_X, clickAnimation, FAnimations.TRANSLATE_X, value, duration);
            } else {
                if (duration == 1) {
                    component.setTranslateX(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getTranslateX(), initialValues, MainProps.TRANSLATE_X, FAnimations.TRANSLATE_X, value, duration);
                }
            }
        }
    }
    
    public String getRawTranslateX () {
        return rawTranslateX;
    }

    public void translateY (double value) {
        component.setTranslateY(value);
    }
    
    public void translateY (double value, String unit) {
        if (unit.equals(FUnit.PIXELS) || unit.isEmpty()) {
            component.setTranslateY(value);
        } else if (unit.equals(FUnit.PERCENTAGE)) {
            translateY(component.getPrefHeight() * (value / 100));
        }
    }
    
    public void translateY (String rawValue) {
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
            if (unit.equals(FUnit.PERCENTAGE)) {
                value = component.getPrefHeight() * (value / 100);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(component.getTranslateY(), initialValues, MainProps.TRANSLATE_Y, hoverAnimation, FAnimations.TRANSLATE_Y, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(component.getTranslateY(), initialValues, MainProps.TRANSLATE_Y, clickAnimation, FAnimations.TRANSLATE_Y, value, duration);
            } else {
                if (duration == 1) {
                    component.setTranslateY(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getTranslateY(), initialValues, MainProps.TRANSLATE_Y, FAnimations.TRANSLATE_Y, value, duration);
                }
            }
        }
    }

    public String getRawTranslateY () {
        return rawTranslateY;
    }
    
    public void rotate (double value) {
        component.setRotate(value);
    }

    public void rotate (String rawValue) {
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
                PropertyUtil.setOneDimensionAnimation(component.getRotate(), initialValues, MainProps.ROTATE, hoverAnimation, FAnimations.ROTATE, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(component.getRotate(), initialValues, MainProps.ROTATE, clickAnimation, FAnimations.ROTATE, value, duration);
            } else {
                if (duration == 1) {
                    component.setRotate(value);
                } else {
                    PropertyUtil.setTempAnimation(component, component.getTranslateY(), initialValues, MainProps.ROTATE, FAnimations.ROTATE, value, duration);
                }
            }
        }
    }

    public String getRawRotate () {
        return rawRotate;
    }
    
    public void padding (double padding) {
        component.setPadding(new Insets(padding, padding, padding, padding));
    }
    
    public void padding (double topBottom, double rightLeft) {
        component.setPadding(new Insets(topBottom, rightLeft, topBottom, rightLeft));
    }

    public void padding (double top, double right, double bottom, double left) {
        component.setPadding(new Insets(top, right, bottom, left));
    }
    
    public void padding (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateInsetsValues(values);
        if (validated) {
            this.rawPadding = rawValue;
            if (values.length == 1) {
                double insets = PropertyValidatorFilter.getDoubleValue(values[0]);
                component.setPadding(new Insets(insets));
            } else if (values.length == 2) {
                double topBottom = PropertyValidatorFilter.getDoubleValue(values[0]);
                double leftRight = PropertyValidatorFilter.getDoubleValue(values[1]);
                component.setPadding(new Insets(topBottom, leftRight, topBottom, leftRight));
            } else if (values.length == 4) {
                double top = PropertyValidatorFilter.getDoubleValue(values[0]);
                double right = PropertyValidatorFilter.getDoubleValue(values[1]);
                double bottom = PropertyValidatorFilter.getDoubleValue(values[2]);
                double left = PropertyValidatorFilter.getDoubleValue(values[3]);
                component.setPadding(new Insets(top, right, bottom, left));
            }
        }
    }

    public String getRawPadding () {
        return rawPadding;
    }
    
    public void margin (double margin) {
        if (this.margin == null) {
            this.margin = new Margin(margin, margin, margin, margin);
        }
    }
    
    public void margin (double topBottom, double rightLeft) {
        if (this.margin == null) {
            this.margin = new Margin(topBottom, rightLeft, topBottom, rightLeft);
        }
    }
    
    public void margin (double top, double right, double bottom, double left) {
        if (this.margin == null) {
            this.margin = new Margin(top, right, bottom, left);
        }
    }

    public void margin (String rawValue) {
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
    }
    
    public Margin getMargin(){
        return margin;
    }

    public String getRawMargin () {
        return rawMargin;
    }

    public void top(double value){
        if(parent != null){
            component.setLayoutY(value);
            parent.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
                component.setLayoutY(value);
            });
        }
        else{

        }
    }

    public void top(String rawValue){}

    public void right(double value){
        if(parent != null){
            component.setLayoutX(parent.getPrefWidth() - component.getPrefWidth() - value);
            parent.prefWidthProperty().addListener((prop, oldVal, newVal) -> {
                component.setLayoutX(parent.getPrefWidth() - component.getPrefWidth() - value);
            });
        }
        else{

        }
    }

    public void right(String rawValue){}

    public void bottom(double value){
        if(parent != null){
            component.setLayoutY(parent.getPrefHeight() - component.getPrefHeight() - value);
            parent.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
                component.setLayoutY(parent.getPrefHeight() - component.getPrefHeight() - value);
            });
        }
        else{

        }
    }

    public void bottom(String rawValue){}

    public void left(double value){
        if(parent != null){
            component.setLayoutX(value);
            parent.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
                component.setLayoutX(value);
            });
        }
        else{

        }
    }

    public void left(String rawValue){}
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
    public void bgColor (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateColorValue(values);
        if (validated) {
            this.rawBgColor = rawValue;
            String value = values[0];
            String currentColor = BackgroundSetter.getBackgroundHexColor(component);
            if (currentColor != null) {
                currentColor = "#" + currentColor;
            } else {
                currentColor = "#00000000";
                initialValues.put(MainProps.BG_COLOR, currentColor);
            }
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BG_COLOR, hoverAnimation, FAnimations.BG_COLOR, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BG_COLOR, clickAnimation, FAnimations.BG_COLOR, value, duration);
            } else {
                initialValues.put(MainProps.BG_COLOR, value);
                if (duration == 1) {
                    BackgroundSetter.setBackgroundColor(component, value);
                } else {
                    PropertyUtil.setTempColorAnimation(component, currentColor, initialValues, MainProps.BG_COLOR, FAnimations.BG_COLOR, value, duration);
                }
            }
        }
    }

    public String getBgColor () {
        return BackgroundSetter.getBackgroundHexColor(component);
    }

    public String getRawBgColor () {
        return rawBgColor;
    }
    
    public void bgOpacity (double opacity) {
        BackgroundSetter.setBackgroundOpacity(component, opacity);
    }
    
    public void bgOpacity (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOpacityValue(values);
        double value = PropertyValidatorFilter.getDoubleValue(values[0]);
        double currOpacity = BackgroundSetter.getBackgroundOpacity(component);
        if (validated && value >= 0 && value <= 1) {
            this.rawBgOpacity = rawValue;
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(currOpacity, initialValues, MainProps.BG_OPACITY, hoverAnimation, FAnimations.BG_OPACITY, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(currOpacity, initialValues, MainProps.BG_OPACITY, clickAnimation, FAnimations.BG_OPACITY, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setBackgroundOpacity(component, value);
                } else {
                    initialValues.put(MainProps.BG_OPACITY, value + "");
                    PropertyUtil.setTempAnimation(component, currOpacity, initialValues, MainProps.BG_OPACITY, FAnimations.BG_OPACITY, value, duration);
                }
            }
        }
    }

    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(component);
    }

    public String getRawBgOpacity () {
        return rawBgOpacity;
    }
    
    public void bgRadius (double radius) {
        BackgroundSetter.setBackgroundRadius(component, radius);
    }

    public void bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(component, topLeftBottomRight, topRightBottomLeft);
    }
    
    public void bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(component, topLeft, topRight, bottomRight, bottomLeft);
    }
    
    public void bgRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateMultipleDimensionValue(values);
        if (validated) {
            this.rawBgRadius = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterMultipleDimensionValues(values);
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
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
                if (unit.equals(FUnit.PERCENTAGE)) {
                    bgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                    bgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                        BackgroundSetter.setBackgroundRadius(component, (double) newVal);
                    });
                    value = (value / 100) * component.getPrefWidth();
                }
                if (setPropertiesOnHover) {
                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, MainProps.BG_RADIUS, hoverAnimation, FAnimations.BG_RADIUS, value, duration);
                } else if (setPropertiesOnClick) {
                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, MainProps.BG_RADIUS, clickAnimation, FAnimations.BG_RADIUS, value, duration);
                } else {
                    if (duration == 1) {
                        BackgroundSetter.setBackgroundRadius(component, value);
                    } else {
                        PropertyUtil.setTempAnimation(component, radii.getTopLeftVerticalRadius(), initialValues, MainProps.BG_RADIUS, FAnimations.BG_RADIUS, value, duration);
                    }
                }
            } else if (filteredValues.length == 4) {
                List<String> radiusAnimationSides = new ArrayList<>(List.of(FAnimations.BG_TL_RADIUS, FAnimations.BG_TR_RADIUS, FAnimations.BG_BR_RADIUS, FAnimations.BG_BL_RADIUS));
                List<Double> currRadiusValues = new ArrayList<>(List.of(radii.getTopLeftHorizontalRadius(), radii.getTopRightHorizontalRadius(), radii.getBottomRightHorizontalRadius(), radii.getBottomLeftHorizontalRadius()));
                List<String> bgRadiusInitialValues = new ArrayList<>(List.of(MainProps.BG_TL_RADIUS, MainProps.BG_TR_RADIUS, MainProps.BG_BR_RADIUS, MainProps.BG_BL_RADIUS));
                for (int i = 0; i < filteredValues.length; i++) {
                    String[] currValue = filteredValues[i].split("\\s+");
                    double value = PropertyValidatorFilter.getDoubleValue(currValue[0]);
                    String unit = PropertyValidatorFilter.getUnit(currValue[0]);
                    long duration = 1;
                    if (currValue.length == 2) {
                        duration = PropertyValidatorFilter.getDuration(currValue[1]);
                    }
                    if (unit.equals(FUnit.PERCENTAGE)) {
                        bgRadiusProperties.get(i).bind(component.prefWidthProperty().multiply(value / 100));
                        if (i == 0) {
                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BackgroundSetter.setTopLeftBackgroundRadius(component, (double) newVal);
                            });
                        }
                        if (i == 1) {
                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BackgroundSetter.setTopRightBackgroundRadius(component, (double) newVal);
                            });
                        }
                        if (i == 2) {
                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BackgroundSetter.setBottomRightBackgroundRadius(component, (double) newVal);
                            });
                        }
                        if (i == 3) {
                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BackgroundSetter.setBottomLeftBackgroundRadius(component, (double) newVal);
                            });
                        }
                        value = (value / 100) * component.getPrefWidth();
                    }
                    if (setPropertiesOnHover) {
                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), hoverAnimation, radiusAnimationSides.get(i), value, duration);
                    } else if (setPropertiesOnClick) {
                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), clickAnimation, radiusAnimationSides.get(i), value, duration);
                    } else {
                        if (duration == 1) {
                            if (i == 0) {
                                BackgroundSetter.setTopLeftBackgroundRadius(component, value);
                            }
                            if (i == 1) {
                                BackgroundSetter.setTopRightBackgroundRadius(component, value);
                            }
                            if (i == 2) {
                                BackgroundSetter.setBottomRightBackgroundRadius(component, value);
                            }
                            if (i == 3) {
                                BackgroundSetter.setBottomLeftBackgroundRadius(component, value);
                            }
                        } else {
                            PropertyUtil.setTempAnimation(component, currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), radiusAnimationSides.get(i), value, duration);
                        }
                    }
                }
            }
        }
    }
    
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(component);
    }
    
    public String getRawBgRadius () {
        return rawBgRadius;
    }
    
    public void topLeftBgRadius (double radius) {
        BackgroundSetter.setTopLeftBackgroundRadius(component, radius);
    }
    
    public void topLeftBgRadius (String rawValue) {
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
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
            if (radii != null) {
                tlBgRadius = radii.getTopLeftHorizontalRadius();
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                tlBgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                tlBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setTopLeftBackgroundRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(tlBgRadius, initialValues, MainProps.BG_TL_RADIUS, hoverAnimation, FAnimations.BG_TL_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(tlBgRadius, initialValues, MainProps.BG_TL_RADIUS, clickAnimation, FAnimations.BG_TL_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setTopLeftBackgroundRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, tlBgRadius, initialValues, MainProps.BG_TL_RADIUS, FAnimations.BG_TL_RADIUS, value, duration);
                }
            }
        }
    }
    
    public double getTopLeftBgRadius () {
        return 0;
    }

    public String getRawTopLeftBgRadius () {
        return rawTopLeftBgRadius;
    }

    public void topRightBgRadius (double radius) {
        BackgroundSetter.setTopRightBackgroundRadius(component, radius);
    }
    
    public void topRightBgRadius (String rawValue) {
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
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
            if (radii != null) {
                trBgRadius = radii.getTopRightHorizontalRadius();
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                trBgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                trBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setTopRightBackgroundRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(trBgRadius, initialValues, MainProps.BG_TR_RADIUS, hoverAnimation, FAnimations.BG_TR_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(trBgRadius, initialValues, MainProps.BG_TR_RADIUS, clickAnimation, FAnimations.BG_TR_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setTopRightBackgroundRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, trBgRadius, initialValues, MainProps.BG_TR_RADIUS, FAnimations.BG_TR_RADIUS, value, duration);
                }
            }
        }
    }

    public double getTopRightBgRadius () {
        return 0;
    }

    public String getRawTopRightBgRadius () {
        return rawTopRightBgRadius;
    }

    public void bottomRightBgRadius (double radius) {
        BackgroundSetter.setBottomRightBackgroundRadius(component, radius);
    }

    public void bottomRightBgRadius (String rawValue) {
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
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
            if (radii != null) {
                brBgRadius = radii.getBottomRightHorizontalRadius();
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                brBgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                brBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setBottomRightBackgroundRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(brBgRadius, initialValues, MainProps.BG_BR_RADIUS, hoverAnimation, FAnimations.BG_BR_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(brBgRadius, initialValues, MainProps.BG_BR_RADIUS, clickAnimation, FAnimations.BG_BR_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setBottomRightBackgroundRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, brBgRadius, initialValues, MainProps.BG_BR_RADIUS, FAnimations.BG_BR_RADIUS, value, duration);
                }
            }
        }
    }

    public double getBottomRightBgRadius () {
        return 0;
    }
    
    public String getRawBottomRightBgRadius () {
        return rawBottomRightBgRadius;
    }
    
    public void bottomLeftBgRadius (double radius) {
        BackgroundSetter.setBottomLeftBackgroundRadius(component, radius);
    }
    
    public void bottomLeftBgRadius (String rawValue) {
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
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
            if (radii != null) {
                blBgRadius = radii.getBottomLeftHorizontalRadius();
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                blBgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                blBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setBottomLeftBackgroundRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(blBgRadius, initialValues, MainProps.BG_BL_RADIUS, hoverAnimation, FAnimations.BG_BL_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(blBgRadius, initialValues, MainProps.BG_BL_RADIUS, clickAnimation, FAnimations.BG_BL_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BackgroundSetter.setBottomLeftBackgroundRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, blBgRadius, initialValues, MainProps.BG_BL_RADIUS, FAnimations.BG_BL_RADIUS, value, duration);
                }
            }
        }
    }

    public double getBottomLeftBgRadius () {
        return 0;
    }

    public String getRawBottomLeftBgRadius () {
        return rawBottomLeftBgRadius;
    }
    
    public void bgInsets (double insets) {
        BackgroundSetter.setInsets(component, insets);
    }
    
    public void bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(component, topBottom, rightLeft);
    }
    
    public void bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(component, top, right, bottom, left);
    }
    
    public void bgInsets (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateInsetsValues(values);
        if (validated) {
            this.rawBgInsets = rawValue;
            if (values.length == 1) {
                double insets = PropertyValidatorFilter.getDoubleValue(values[0]);
                BackgroundSetter.setInsets(component, insets);
            } else if (values.length == 2) {
                double topBottom = PropertyValidatorFilter.getDoubleValue(values[0]);
                double leftRight = PropertyValidatorFilter.getDoubleValue(values[1]);
                BackgroundSetter.setInsets(component, topBottom, leftRight);
            } else if (values.length == 4) {
                double top = PropertyValidatorFilter.getDoubleValue(values[0]);
                double right = PropertyValidatorFilter.getDoubleValue(values[1]);
                double bottom = PropertyValidatorFilter.getDoubleValue(values[2]);
                double left = PropertyValidatorFilter.getDoubleValue(values[3]);
                BackgroundSetter.setInsets(component, top, right, bottom, left);
            }
        }
    }
    
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(component);
    }
    
    public String getRawBgInsets () {
        return rawBgInsets;
    }
    
    public void topBgInset (double inset) {
        BackgroundSetter.setTopInset(component, inset);
    }
    
    public double getTopBgInset () {
        return 0;
    }

    public void rightBgInset (double inset) {
        BackgroundSetter.setRightInset(component, inset);
    }
    
    public double getRightBgInset () {
        return 0;
    }
    
    public void bottomBgInset (double inset) {
        BackgroundSetter.setBottomInset(component, inset);
    }

    public double getBottomBgInset () {
        return 0;
    }
    
    public void leftBgInset (double inset) {
        BackgroundSetter.setLeftInset(component, inset);
    }

    public double getLeftBgInset () {
        return 0;
    }
    // ******************************************
    //            Background properties         *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Border properties           *
    // ******************************************
    
    public void border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(component, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
    }
    
    public void border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(component, colorValue, borderStyle, radius, borderWidth);
    }
    
    public void border (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateBorderValue(values);
        if(validated){
            this.rawBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getBorderHexColor(component);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getTopBorderStyle(component);
            double radius = 0;
            double currentRadius = 0;
            String radiusUnit = FUnit.PIXELS;
            long radiusDuration = 1;
            double width = 0;
            double currentWidth = 0;
            String widthUnit = FUnit.PIXELS;
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = "#" + currentColor;
            }
            else{
                currentColor = "#00000000";
                initialValues.put(MainProps.BR_COLOR, currentColor);
            }
            if(filteredValues[0].contains(" ")){
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put(MainProps.BR_STYLE, currentStyle);
            }
            if(BorderSetter.getBorderRadius(component) != null){
                currentRadius = BorderSetter.getBorderRadius(component).getTopLeftHorizontalRadius();
            }
            if(BorderSetter.getBorderWidths(component) != null){
                currentWidth = BorderSetter.getBorderWidths(component).getTop();
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
            if(radiusUnit.equals(FUnit.PERCENTAGE)){
                brRadiusProperty.bind(component.prefWidthProperty().multiply(radius / 100));
                brRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBorderRadius(component, (double) newVal);
                });
                radius = (radius / 100) * component.getPrefWidth();
            }
            if(widthUnit.equals(FUnit.PERCENTAGE)){
                brWidthProperty.bind(component.prefWidthProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBorderWidth(component, (double) newVal);
                });
                width = (width / 100) * component.getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_COLOR, hoverAnimation, FAnimations.BR_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_STYLE, staticHoverAnimation, MainProps.BR_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentRadius, initialValues, MainProps.BR_RADIUS, hoverAnimation, FAnimations.BR_RADIUS, radius, radiusDuration);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_WIDTH, hoverAnimation, FAnimations.BR_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_COLOR, clickAnimation, FAnimations.BR_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_STYLE, staticClickAnimation, MainProps.BR_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentRadius, initialValues, MainProps.BR_RADIUS, clickAnimation, FAnimations.BR_RADIUS, radius, radiusDuration);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_WIDTH, clickAnimation, FAnimations.BR_WIDTH, width, widthDuration);
            }
            else{
                if(colorDuration == 1){
                    BorderSetter.setBorderColor(component, color);
                    initialValues.put(MainProps.BR_COLOR, color);
                }
                else{
                    PropertyUtil.setTempColorAnimation(component, currentColor, initialValues, MainProps.BR_COLOR, FAnimations.BR_COLOR, color, colorDuration);
                }
                BorderSetter.setBorderStyle(component, borderStyle);
                if(radiusDuration == 1){
                    BorderSetter.setBorderRadius(component, radius);
                }
                else{
                    PropertyUtil.setTempAnimation(component, currentRadius, initialValues, MainProps.BR_RADIUS, FAnimations.BR_RADIUS, radius, radiusDuration);
                }
                if(widthDuration == 1){
                    BorderSetter.setBorderWidth(component, width);
                }
                else{
                    PropertyUtil.setTempAnimation(component, currentWidth, initialValues, MainProps.BR_WIDTH, FAnimations.BR_WIDTH, width, widthDuration);
                }
            }
        }
    }
    
    public String getRawBorder () {
        return rawBorder;
    }

    public void topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(component, colorValue, borderStyle, borderWidth);
    }
    
    public void topBorder (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneSideBorderValue(values);
        if(validated){
            this.rawTopBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterOneSideBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getTopBorderColor(component);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getTopBorderStyle(component);
            double width = 0;
            double currentWidth = BorderSetter.getTopBorderWidth(component);
            String widthUnit = FUnit.PIXELS;
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = color;
            }
            else{
                currentColor = "#00000000";
                initialValues.put(MainProps.BR_TOP_COLOR, currentColor);
            }
            if(filteredValues[0].contains(" ")) {
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put(MainProps.BR_TOP_STYLE, currentStyle);
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
            if(widthUnit.equals(FUnit.PERCENTAGE)){
                brWidthProperty.bind(component.prefHeightProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setTopBorderWidth(component, (double) newVal);
                });
                width = (width / 100) * component.getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_TOP_COLOR, hoverAnimation, FAnimations.BR_TOP_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_TOP_STYLE, staticHoverAnimation, MainProps.BR_TOP_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_TOP_WIDTH, hoverAnimation, FAnimations.BR_TOP_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_TOP_COLOR, clickAnimation, FAnimations.BR_TOP_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_TOP_STYLE, staticClickAnimation, MainProps.BR_TOP_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_TOP_WIDTH, clickAnimation, FAnimations.BR_TOP_WIDTH, width, widthDuration);
            }
            else{
                PropertyUtil.setTempColorAnimation(component, currentColor, initialValues, MainProps.BR_TOP_COLOR, FAnimations.BR_TOP_COLOR, color, colorDuration);
                BorderSetter.setTopBorderStyle(component, borderStyle);
                if(widthDuration == 1){
                    BorderSetter.setTopBorderWidth(component, width);
                }
                else{
                    PropertyUtil.setTempAnimation(component, currentWidth, initialValues, MainProps.BR_TOP_WIDTH, FAnimations.BR_TOP_WIDTH, width, widthDuration);
                }
            }
        }
    }
    
    public String getRawTopBorder () {
        return rawTopBorder;
    }

    public void rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(component, colorValue, borderStyle, borderWidth);
    }
    
    public void rightBorder (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneSideBorderValue(values);
        if(validated){
            this.rawRightBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterOneSideBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getRightBorderColor(component);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getRightBorderStyle(component);
            double width = 0;
            double currentWidth = BorderSetter.getRightBorderWidth(component);
            String widthUnit = FUnit.PIXELS;
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = "#" + currentColor;
            }
            else{
                currentColor = "#00000000";
                initialValues.put(MainProps.BR_RIGHT_COLOR, currentColor);
            }
            if(filteredValues[0].contains(" ")) {
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put(MainProps.BR_RIGHT_STYLE, currentStyle);
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
            if(widthUnit.equals(FUnit.PERCENTAGE)){
                brWidthProperty.bind(component.prefWidthProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setRightBorderWidth(component, (double) newVal);
                });
                width = (width / 100) * component.getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_RIGHT_COLOR, hoverAnimation, FAnimations.BR_RIGHT_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_RIGHT_STYLE, staticHoverAnimation, MainProps.BR_RIGHT_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_RIGHT_WIDTH, hoverAnimation, FAnimations.BR_RIGHT_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_RIGHT_COLOR, clickAnimation, FAnimations.BR_RIGHT_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_RIGHT_STYLE, staticClickAnimation, MainProps.BR_RIGHT_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_RIGHT_WIDTH, clickAnimation, FAnimations.BR_RIGHT_WIDTH, width, widthDuration);
            }
            else{
                PropertyUtil.setTempColorAnimation(component, currentColor, initialValues, MainProps.BR_RIGHT_COLOR, FAnimations.BR_RIGHT_COLOR, color, colorDuration);
                BorderSetter.setRightBorderStyle(component, borderStyle);
                if(widthDuration == 1){
                    BorderSetter.setRightBorderWidth(component, width);
                }
                else{
                    PropertyUtil.setTempAnimation(component, currentWidth, initialValues, MainProps.BR_RIGHT_WIDTH, FAnimations.BR_RIGHT_WIDTH, width, widthDuration);
                }
            }
        }
    }
    
    public String getRawRightBorder () {
        return rawRightBorder;
    }

    public void bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(component, colorValue, borderStyle, borderWidth);
    }

    public void bottomBorder (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneSideBorderValue(values);
        if(validated){
            this.rawBottomBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterOneSideBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getBottomBorderColor(component);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getBottomBorderStyle(component);
            double width = 0;
            double currentWidth = BorderSetter.getBottomBorderWidth(component);
            String widthUnit = FUnit.PIXELS;
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = "#" + currentColor;
            }
            else{
                currentColor = "#00000000";
                initialValues.put(MainProps.BR_BOTTOM_COLOR, currentColor);
            }
            if(filteredValues[0].contains(" ")) {
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put(MainProps.BR_BOTTOM_STYLE, currentStyle);
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
            if(widthUnit.equals(FUnit.PERCENTAGE)){
                brWidthProperty.bind(component.prefHeightProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBottomBorderWidth(component, (double) newVal);
                });
                width = (width / 100) * component.getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_BOTTOM_COLOR, hoverAnimation, FAnimations.BR_BOTTOM_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_BOTTOM_STYLE, staticHoverAnimation, MainProps.BR_BOTTOM_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_BOTTOM_WIDTH, hoverAnimation, FAnimations.BR_BOTTOM_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_BOTTOM_COLOR, clickAnimation, FAnimations.BR_BOTTOM_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_BOTTOM_STYLE, staticClickAnimation, MainProps.BR_BOTTOM_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_BOTTOM_WIDTH, clickAnimation, FAnimations.BR_BOTTOM_WIDTH, width, widthDuration);
            }
            else{
                PropertyUtil.setTempColorAnimation(component, currentColor, initialValues, MainProps.BR_BOTTOM_COLOR, FAnimations.BR_BOTTOM_COLOR, color, colorDuration);
                BorderSetter.setBottomBorderStyle(component, borderStyle);
                if(widthDuration == 1){
                    BorderSetter.setBottomBorderWidth(component, width);
                }
                else{
                    PropertyUtil.setTempAnimation(component, currentWidth, initialValues, MainProps.BR_BOTTOM_WIDTH, FAnimations.BR_BOTTOM_WIDTH, width, widthDuration);
                }
            }
        }
    }

    public String getRawBottomBorder () {
        return rawBottomBorder;
    }

    public void leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(component, colorValue, borderStyle, borderWidth);
    }
    
    public void leftBorder (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOneSideBorderValue(values);
        if(validated){
            this.rawLeftBorder = rawValue;
            String[] filteredValues = PropertyValidatorFilter.filterOneSideBorderValue(values);
            String color = filteredValues[0].split("\\s+")[0];
            String currentColor = BorderSetter.getLeftBorderColor(component);
            long colorDuration = 1;
            String borderStyle = filteredValues[1];
            String currentStyle = BorderSetter.getLeftBorderStyle(component);
            double width = 0;
            double currentWidth = BorderSetter.getLeftBorderWidth(component);
            String widthUnit = FUnit.PIXELS;
            long widthDuration = 1;
            if(currentColor != null){
                currentColor = "#" + currentColor;
            }
            else{
                currentColor = "#00000000";
                initialValues.put(MainProps.BR_LEFT_COLOR, currentColor);
            }
            if(filteredValues[0].contains(" ")) {
                colorDuration = PropertyValidatorFilter.getDuration(filteredValues[0].split("\\s+")[1]);
            }
            if(currentStyle == null){
                currentStyle = "solid";
                initialValues.put(MainProps.BR_LEFT_STYLE, currentStyle);
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
            if(widthUnit.equals(FUnit.PERCENTAGE)){
                brWidthProperty.bind(component.prefWidthProperty().multiply(width / 100));
                brWidthProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setLeftBorderWidth(component, (double) newVal);
                });
                width = (width / 100) * component.getPrefWidth();
            }
            if(setPropertiesOnHover){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_LEFT_COLOR, hoverAnimation, FAnimations.BR_LEFT_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_LEFT_STYLE, staticHoverAnimation, MainProps.BR_LEFT_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_LEFT_WIDTH, hoverAnimation, FAnimations.BR_LEFT_WIDTH, width, widthDuration);
            }
            else if(setPropertiesOnClick){
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_LEFT_COLOR, clickAnimation, FAnimations.BR_LEFT_COLOR, color, colorDuration);
                PropertyUtil.setStaticAnimation(currentStyle, initialValues, MainProps.BR_LEFT_STYLE, staticClickAnimation, MainProps.BR_LEFT_STYLE, borderStyle);
                PropertyUtil.setOneDimensionAnimation(currentWidth, initialValues, MainProps.BR_LEFT_WIDTH, clickAnimation, FAnimations.BR_LEFT_WIDTH, width, widthDuration);
            }
            else{
                PropertyUtil.setTempColorAnimation(component, currentColor, initialValues, MainProps.BR_LEFT_COLOR, FAnimations.BR_LEFT_COLOR, color, colorDuration);
                BorderSetter.setLeftBorderStyle(component, borderStyle);
                if(widthDuration == 1){
                    BorderSetter.setLeftBorderWidth(component, width);
                }
                else{
                    PropertyUtil.setTempAnimation(component, currentWidth, initialValues, MainProps.BR_LEFT_WIDTH, FAnimations.BR_LEFT_WIDTH, width, widthDuration);
                }
            }
        }
    }

    public String getRawLeftBorder () {
        return rawLeftBorder;
    }
    
    public void borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(component, borderStyle);
    }

    public String getBorderStyle () {
        return "";
    }
    
    public void borderWidth (double width) {
        BorderSetter.setBorderWidth(component, width);
    }
    
    public void borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(component, topBottom, rightLeft, topBottom, rightLeft);
    }

    public void borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(component, top, right, bottom, left);
    }
    
    public void borderWidth (String borderWidth) {
        String[] values = borderWidth.split("\\s+");
    }
    
    public String getRawBorderWidth ()  {
        return rawBorderWidth;
    }
    
    public void borderColor (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateColorValue(values);
        if (validated) {
            this.rawBorderColor = rawValue;
            String value = values[0];
            String currentColor = "#" + BorderSetter.getBorderHexColor(component);
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_COLOR, hoverAnimation, FAnimations.BR_COLOR, value, duration);
            }
            else if (setPropertiesOnClick) {
                PropertyUtil.setColorAnimation(currentColor, initialValues, MainProps.BR_COLOR, clickAnimation, FAnimations.BR_COLOR, value, duration);
            }
            else {
                initialValues.put(MainProps.BR_COLOR, value);
                if (duration == 1) {
                    BorderSetter.setBorderColor(component, value);
                } else {
                    PropertyUtil.setTempColorAnimation(component, currentColor, initialValues, MainProps.BR_COLOR, FAnimations.BR_COLOR, value, duration);
                }
            }
        }
    }

    public String getBorderColor () {
        return "";
    }
    
    public String getRawBorderColor () {
        return rawBorderColor;
    }

    public void borderOpacity (double opacity) {
        BorderSetter.setBorderOpacity(component, opacity);
    }

    public void borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(component, topBottom, rightLeft);
    }
    
    public void borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(component, top, right, bottom, left);
    }

    public void borderOpacity (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateOpacityValue(values);
        double value = PropertyValidatorFilter.getDoubleValue(values[0]);
        double currOpacity = BorderSetter.getBorderOpacity(component);
        if (validated && value >= 0 && value <= 1) {
            this.rawBorderOpacity = rawValue;
            long duration = 1;
            if (values.length == 2) {
                duration = PropertyValidatorFilter.getDuration(values[1]);
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(currOpacity, initialValues, MainProps.BR_OPACITY, hoverAnimation, FAnimations.BR_OPACITY, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(currOpacity, initialValues, MainProps.BR_OPACITY, clickAnimation, FAnimations.BR_OPACITY, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setBorderOpacity(component, value);
                } else {
                    initialValues.put(MainProps.BR_OPACITY, value + "");
                    PropertyUtil.setTempAnimation(component, currOpacity, initialValues, MainProps.BR_OPACITY, FAnimations.BR_OPACITY, value, duration);
                }
            }
        }
    }

    public String getRawBorderOpacity () {
        return rawBorderOpacity;
    }

    public void borderRadius (double radius) {
        BorderSetter.setBorderRadius(component, radius);
    }

    public void borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(component, topLeft, topRight, bottomRight, bottomLeft);
    }

    public void borderRadius (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateMultipleDimensionValue(values);
        if (validated) {
            this.rawBorderRadius = rawValue;
            this.brRadiusProperty = new SimpleDoubleProperty();
            String[] filteredValues = PropertyValidatorFilter.filterMultipleDimensionValues(values);
            CornerRadii radii = BorderSetter.getBorderRadius(component);
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
                if (unit.equals(FUnit.PERCENTAGE)) {
                    brRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                    brRadiusProperty.addListener((valType, oldVal, newVal) -> {
                        BorderSetter.setBorderRadius(component, (double) newVal);
                    });
                    value = (value / 100) * component.getPrefWidth();
                }
                if (setPropertiesOnHover) {
                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, MainProps.BR_RADIUS, hoverAnimation, FAnimations.BR_RADIUS, value, duration);
                } else if (setPropertiesOnClick) {
                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, MainProps.BR_RADIUS, clickAnimation, FAnimations.BR_RADIUS, value, duration);
                } else {
                    if (duration == 1) {
                        BorderSetter.setBorderRadius(component, value);
                    } else {
                        PropertyUtil.setTempAnimation(component, radii.getTopLeftVerticalRadius(), initialValues, MainProps.BR_RADIUS, FAnimations.BR_RADIUS, value, duration);
                    }
                }
            } else if (filteredValues.length == 4) {
                List<String> radiusAnimationSides = new ArrayList<>(List.of(FAnimations.BR_TL_RADIUS, FAnimations.BR_TR_RADIUS, FAnimations.BR_BR_RADIUS, FAnimations.BR_BL_RADIUS));
                List<Double> currRadiusValues = new ArrayList<>(List.of(radii.getTopLeftHorizontalRadius(), radii.getTopRightHorizontalRadius(), radii.getBottomRightHorizontalRadius(), radii.getBottomLeftHorizontalRadius()));
                List<String> brRadiusInitialValues = new ArrayList<>(List.of(MainProps.BR_TL_RADIUS, MainProps.BR_TR_RADIUS, MainProps.BR_BR_RADIUS,  MainProps.BR_BL_RADIUS));
                for (int i = 0; i < filteredValues.length; i++) {
                    String[] currValue = filteredValues[i].split("\\s+");
                    double value = PropertyValidatorFilter.getDoubleValue(currValue[0]);
                    String unit = PropertyValidatorFilter.getUnit(currValue[0]);
                    long duration = 1;
                    if (currValue.length == 2) {
                        duration = PropertyValidatorFilter.getDuration(currValue[1]);
                    }
                    if (unit.equals(FUnit.PERCENTAGE)) {
                        brRadiusProperties.get(i).bind(component.prefWidthProperty().multiply(value / 100));
                        if (i == 0) {
                            brRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BorderSetter.setTopLeftBorderRadius(component, (double) newVal);
                            });
                        }
                        if (i == 1) {
                            brRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BorderSetter.setTopRightBorderRadius(component, (double) newVal);
                            });
                        }
                        if (i == 2) {
                            brRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BorderSetter.setBottomRightBorderRadius(component, (double) newVal);
                            });
                        }
                        if (i == 3) {
                            brRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
                                BorderSetter.setBottomLeftBorderRadius(component, (double) newVal);
                            });
                        }
                        value = (value / 100) * component.getPrefWidth();
                    }
                    if (setPropertiesOnHover) {
                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, brRadiusInitialValues.get(i), hoverAnimation, radiusAnimationSides.get(i), value, duration);
                    } else if (setPropertiesOnClick) {
                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, brRadiusInitialValues.get(i), clickAnimation, radiusAnimationSides.get(i), value, duration);
                    } else {
                        if (duration == 1) {
                            if (i == 0) {
                                BorderSetter.setTopLeftBorderRadius(component, value);
                            }
                            if (i == 1) {
                                BorderSetter.setTopRightBorderRadius(component, value);
                            }
                            if (i == 2) {
                                BorderSetter.setBottomRightBorderRadius(component, value);
                            }
                            if (i == 3) {
                                BorderSetter.setBottomLeftBorderRadius(component, value);
                            }
                        } else {
                            PropertyUtil.setTempAnimation(component, currRadiusValues.get(i), initialValues, brRadiusInitialValues.get(i), radiusAnimationSides.get(i), value, duration);
                        }
                    }
                }
            }
        }
    }

    public String getRawBorderRadius () {
        return rawBorderRadius;
    }

    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(component);
    }
    
    public void topLeftBorderRadius (double radius) {
        BorderSetter.setTopLeftBorderRadius(component, radius);
    }
    
    public void topLeftBorderRadius (String rawValue) {
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
            CornerRadii radii = BorderSetter.getBorderRadius(component);
            if (radii != null) {
                tlBrRadius = radii.getTopLeftHorizontalRadius();
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                tlBrRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                tlBrRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setTopLeftBorderRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(tlBrRadius, initialValues, MainProps.BR_TL_RADIUS, hoverAnimation, FAnimations.BR_TL_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(tlBrRadius, initialValues, MainProps.BR_TL_RADIUS, clickAnimation, FAnimations.BR_TL_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setTopLeftBorderRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, tlBrRadius, initialValues, MainProps.BR_TL_RADIUS, FAnimations.BR_TL_RADIUS, value, duration);
                }
            }
        }
    }

    public double getTopLeftBorderRadius () {
        return 0;
    }
    
    public String getRawTopLeftBorderRadius () {
        return rawTopLeftBorderRadius;
    }
    
    public void topRightBorderRadius (double radius) {
        BorderSetter.setTopRightBorderRadius(component, radius);
    }
    
    public void topRightBorderRadius (String rawValue) {
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
            CornerRadii radii = BorderSetter.getBorderRadius(component);
            if (radii != null) {
                trBrRadius = radii.getTopRightHorizontalRadius();
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                trBrRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                trBrRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setTopRightBorderRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(trBrRadius, initialValues, MainProps.BR_TR_RADIUS, hoverAnimation, FAnimations.BR_TR_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(trBrRadius, initialValues, MainProps.BR_TR_RADIUS, clickAnimation, FAnimations.BR_TR_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setTopRightBorderRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, trBrRadius, initialValues, MainProps.BR_TR_RADIUS, FAnimations.BR_TR_RADIUS, value, duration);
                }
            }
        }
    }

    public double getTopRightBorderRadius () {
        return 0;
    }
    
    public String getRawTopRightBorderRadius () {
        return rawTopRightBorderRadius;
    }
    
    public void bottomRightBorderRadius (double radius) {
        BorderSetter.setBottomRightBorderRadius(component, radius);
    }

    public void bottomRightBorderRadius (String rawValue) {
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
            CornerRadii radii = BorderSetter.getBorderRadius(component);
            if (radii != null) {
                brBrRadius = radii.getBottomRightHorizontalRadius();
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                brBrRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                brBrRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBottomRightBorderRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(brBrRadius, initialValues, MainProps.BR_BR_RADIUS, hoverAnimation, FAnimations.BR_BR_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(brBrRadius, initialValues, MainProps.BR_BR_RADIUS, clickAnimation, FAnimations.BR_BR_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setBottomRightBorderRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, brBrRadius, initialValues, MainProps.BR_BR_RADIUS, FAnimations.BR_BR_RADIUS, value, duration);
                }
            }
        }
    }

    public double getBottomRightBorderRadius () {
        return 0;
    }
    
    public String getRawBottomRightBorderRadius () {
        return rawBottomRightBorderRadius;
    }

    public void bottomLeftBorderRadius (double radius) {
        BorderSetter.setBottomLeftBorderRadius(component, radius);
    }
    
    public void bottomLeftBorderRadius (String rawValue) {
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
            CornerRadii radii = BorderSetter.getBorderRadius(component);
            if (radii != null) {
                blBrRadius = radii.getBottomLeftHorizontalRadius();
            }
            if (unit.equals(FUnit.PERCENTAGE)) {
                blBrRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                blBrRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BorderSetter.setBottomLeftBorderRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(blBrRadius, initialValues, MainProps.BR_BL_RADIUS, hoverAnimation, FAnimations.BR_BL_RADIUS, value, duration);
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(blBrRadius, initialValues, MainProps.BR_BL_RADIUS, clickAnimation, FAnimations.BR_BL_RADIUS, value, duration);
            } else {
                if (duration == 1) {
                    BorderSetter.setBottomLeftBorderRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, blBrRadius, initialValues, MainProps.BR_BL_RADIUS, FAnimations.BR_BL_RADIUS, value, duration);
                }
            }
        }
    }
    
    public double getBottomLeftBorderRadius () {
        return 0;
    }
    
    public String getRawBottomLeftBorderRadius () {
        return rawBottomLeftBorderRadius;
    }
    
    public void borderInsets (double inset) {
        BorderSetter.setBorderInsets(component, inset);
    }

    public void borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(component, topBottom, rightLeft, topBottom, rightLeft);
    }

    public void borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(component, top, right, bottom, left);
    }
    
    public void borderInsets (String rawValue) {
        String[] values = rawValue.split("\\s+");
        boolean validated = PropertyValidatorFilter.validateInsetsValues(values);
        if (validated) {
            this.rawBorderInsets = rawValue;
            if (values.length == 1) {
                double insets = PropertyValidatorFilter.getDoubleValue(values[0]);
                BorderSetter.setBorderInsets(component, insets);
            } else if (values.length == 2) {
                double topBottom = PropertyValidatorFilter.getDoubleValue(values[0]);
                double leftRight = PropertyValidatorFilter.getDoubleValue(values[1]);
                BorderSetter.setBorderInsets(component, topBottom, leftRight, topBottom, leftRight);
            } else if (values.length == 4) {
                double top = PropertyValidatorFilter.getDoubleValue(values[0]);
                double right = PropertyValidatorFilter.getDoubleValue(values[1]);
                double bottom = PropertyValidatorFilter.getDoubleValue(values[2]);
                double left = PropertyValidatorFilter.getDoubleValue(values[3]);
                BorderSetter.setBorderInsets(component, top, right, bottom, left);
            }
        }
    }

    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(component);
    }

    public String getRawBorderInsets () {
        return rawBorderInsets;
    }
    
    public void removeBorder () {
        BorderSetter.removeBorder(component);
        initialValues.remove("border");
    }

    public void removeTopBorder () {
        BorderSetter.removeTopBorder(component);
        initialValues.remove("topBorder");
    }
    
    public void removeRightBorder () {
        BorderSetter.removeRightBorder(component);
        initialValues.remove("rightBorder");
    }

    public void removeBottomBorder () {
        BorderSetter.removeBottomBorder(component);
        initialValues.remove("bottomBorder");
    }

    public void removeLeftBorder () {
        BorderSetter.removeLeftBorder(component);
        initialValues.remove("leftBorder");
    }
    // ******************************************
    //              Border properties           *
    // ******************************************
    // *********************************************************************************************
    // *********************************************************************************************
    // ******************************************
    //              Custom listeners            *
    // ******************************************
    public void resizeListener (ResizeListener resizeListener) {
        if (resizeWidthListeners == null || resizeHeightListeners == null) {
            resizeWidthListeners = (valType, oldVal, newVal) -> {
                notifyResizeListeners((double) newVal, component.getPrefHeight());
            };
            resizeHeightListeners = (valType, oldVal, newVal) -> {
                notifyResizeListeners(component.getPrefWidth(), (double) newVal);
            };
            component.prefWidthProperty().addListener(resizeWidthListeners);
            component.prefHeightProperty().addListener(resizeHeightListeners);
        }
        resizeListeners.add(resizeListener);
    }

    public void removeResizeListener (ResizeListener resizeListener) {
        if (resizeListeners != null) {
            resizeListeners.remove(resizeListener);
        }
        if (resizeListeners != null && resizeListeners.isEmpty()) {
            component.prefWidthProperty().removeListener(resizeWidthListeners);
            component.prefHeightProperty().removeListener(resizeHeightListeners);
            resizeWidthListeners = null;
            resizeHeightListeners = null;
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

    private void removePropertyListener(Region component, String propertyName){
        InvalidationListener listener = doublePropertiesListeners.get(propertyName);
        if(listener != null){
            if(propertyName.equals("width")){
                component.prefWidthProperty().removeListener(listener);
            }
            if(propertyName.equals("height")){
                component.prefHeightProperty().removeListener(listener);
            }
        }
        doublePropertiesListeners.remove(propertyName);
    }

    private void addPropertyListener(Region component, String propertyName, ChangeListener<Number> listener){
        if(propertyName.equals("width")){
            component.prefWidthProperty().addListener(listener);
        }
        if(propertyName.equals("height")){
            component.prefHeightProperty().addListener(listener);
        }
    }

    public void notifyParentWasSet(Region parent){
        delayedTaskManager.setParent(parent);
        delayedTaskManager.executeAllTasks();
        delayedTaskManager.removeAllTasks();
    }
}
