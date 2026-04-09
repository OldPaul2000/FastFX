package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.fcomponents.flistener.ResizeListener;
import com.example.fastfx.src.fcomponents.propertymanager.constants.FUnit;
import com.example.fastfx.src.fcomponents.propertymanager.constants.ListenerID;
import com.example.fastfx.src.fcomponents.propertymanager.constants.MainProps;
import com.example.fastfx.src.fcomponents.propertymanager.pendingtask.PendingTaskManager;
import com.example.fastfx.src.fcomponents.pseudoclass.PseudoClass;
import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.FAnimations;
import com.example.fastfx.src.style.animations.StaticAnimation;
import com.example.fastfx.src.style.animations.StaticAnimations;
import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;

import java.util.*;

public class PropertyManager2 {

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

    // For removal
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

    private PendingTaskManager pendingTaskManager;
    private PropertyParser parser;
    private PropertyHelper propertyHelper;
    private ValuesManager valuesManager;
    private ListenersManager listenersManager;

    public PropertyManager2 (Region component) {
        this.component = component;
        this.parent = (Region) component.getParent();
        this.initialValues = new HashMap<>();
        initBgRadiusProperties();
        initBrRadiusProperties();
        initBrWidthProperties();
        this.resizeListeners = new HashSet<>();
        this.parser = new PropertyParser();
        this.valuesManager = new ValuesManager();
        this.listenersManager = new ListenersManager();
        this.pendingTaskManager = new PendingTaskManager();
        this.propertyHelper = new PropertyHelper(component, parser, valuesManager, listenersManager, pendingTaskManager);
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
            propertyHelper.setComponentHoverAnimation(hoverAnimation);
        }
        if (staticHoverAnimation == null){
            staticHoverAnimation = new StaticAnimation(component);
            propertyHelper.setComponentStaticHoverAnimation(staticHoverAnimation);
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
            propertyHelper.setComponentClickAnimation(clickAnimation);
        }
        if(staticClickAnimation == null){
            staticClickAnimation = new StaticAnimation(component);
            propertyHelper.setComponentStaticClickAnimation(staticClickAnimation);
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

    /**
     * Sets the layoutX represented in pixels.
     */
    public void x (double value) {
        propertyHelper.setProperty(MainProps.X);
        propertyHelper.setStaticAnimationType(StaticAnimations.LAYOUT_X);
        propertyHelper.setValues(component.getLayoutX(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
            component.setLayoutX(value);
            pendingTaskManager.removeTaskById(ListenerID.WIDTH_X);
            listenersManager.remove(ListenerID.WIDTH_X);
            valuesManager.set(MainProps.X, value);
            propertyHelper.updateAnimationStartValue(FAnimations.LAYOUT_X, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.LAYOUT_X, hoverAnimation, value);
        }
    }

    /**
     * Sets the layoutX based on the introduced unit(pixels or percentage).
     */
    public void x (double value, String unit) {
        if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
            if (parent != null) {
                listenersManager.remove(ListenerID.WIDTH_X);
                component.setLayoutX(parent.getPrefWidth() * (value / 100));
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    component.setLayoutX((double)newVal * (value / 100));
                };
                listenersManager.add(parent, ListenerID.WIDTH_X, MainProps.WIDTH, listener);
            } else {
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_X);
                pendingTaskManager.addTask(ListenerID.WIDTH_X, (p) -> {
                    component.setLayoutX(p.getPrefWidth() * (value / 100));
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                        component.setLayoutX((double)newVal * (value / 100));
                    };
                    listenersManager.add(p, ListenerID.WIDTH_X, MainProps.WIDTH, listener);
                });
            }
        }
        else if(unit.equals(FUnit.PIXELS)){
            x(value);
        }
    }

    /**
     * Sets the layoutX based on the string input. The unit can be chosen,<br>
     * the duration if we want to animate the property, the interpolator and also<br>
     * if we want the animation to run infinitely or a specified number of cycles.<br>
     * Input example:<br>
     * 5px 1s or<br>
     * 5% 1s or<br>
     * 5px 0.5s ease-in etc.
     */
    public void x (String rawValue) {
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawX = rawValue;
            double value = parser.getDoubleValue(0);
            String unit = parser.getUnit(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
                if(parent != null){
                    listenersManager.remove(ListenerID.WIDTH_X);
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    double layoutXValue = parent.getPrefWidth() * (value / 100);
                    if(duration > 0) {
                        propertyHelper.setAnimationType(FAnimations.LAYOUT_X);
                        propertyHelper.setValues(component.getLayoutX(), layoutXValue);
                        propertyHelper.setDuration(duration);
                        propertyHelper.setInterpolator(interpolator);
                        propertyHelper.addTempAnimation(FAnimations.LAYOUT_X, PropertyHelper.NUMBER_ANIMATION);
                    }
                    else{
                        component.setLayoutX(layoutXValue);
                    }
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                        component.setLayoutX((double)newVal * (value / 100));
                    };
                    listenersManager.add(parent, ListenerID.WIDTH_X, MainProps.WIDTH, listener);
                    valuesManager.set(MainProps.X, layoutXValue);
                }
                else{
                    pendingTaskManager.removeTaskById(ListenerID.WIDTH_X);
                    pendingTaskManager.addTask(ListenerID.WIDTH_X, (p) -> {
                        propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                        double layoutXValue = p.getPrefWidth() * (value / 100);
                        if(duration > 0) {
                            propertyHelper.setAnimationType(FAnimations.LAYOUT_X);
                            propertyHelper.setValues(component.getLayoutX(), layoutXValue);
                            propertyHelper.setDuration(duration);
                            propertyHelper.setInterpolator(interpolator);
                            propertyHelper.addTempAnimation(FAnimations.LAYOUT_X, PropertyHelper.NUMBER_ANIMATION);
                        }
                        else{
                            component.setLayoutX(layoutXValue);
                        }
                        ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                            component.setLayoutX((double)newVal * (value / 100));
                        };
                        listenersManager.add(p, ListenerID.WIDTH_X, MainProps.WIDTH, listener);
                        valuesManager.set(MainProps.X, layoutXValue);
                    });
                }
            }
            else{
                propertyHelper.setProperty(MainProps.X);
                propertyHelper.setAnimationsTypes(FAnimations.LAYOUT_X, StaticAnimations.LAYOUT_X);
                propertyHelper.setValues(valuesManager.getDouble(MainProps.X), value);
                propertyHelper.setDuration(duration);
                propertyHelper.setInterpolator(interpolator);
                if (setPropertiesOnHover) {
                    propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else if (setPropertiesOnClick) {
                    propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    pendingTaskManager.removeTaskById(ListenerID.WIDTH_X);
                    listenersManager.remove(ListenerID.WIDTH_X);
                    if (duration == 0) {
                        component.setLayoutX(value);
                    } else {
                        propertyHelper.addTempAnimation(FAnimations.LAYOUT_X, PropertyHelper.NUMBER_ANIMATION);
                    }
                    valuesManager.set(MainProps.X, value);
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

    /**
     * Sets the layoutY represented in pixels.
     */
    public void y (double value) {
        propertyHelper.setProperty(MainProps.Y);
        propertyHelper.setStaticAnimationType(StaticAnimations.LAYOUT_Y);
        propertyHelper.setValues(component.getLayoutY(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
            component.setLayoutY(value);
            pendingTaskManager.removeTaskById(ListenerID.HEIGHT_Y);
            listenersManager.remove(ListenerID.HEIGHT_Y);
            valuesManager.set(MainProps.Y, value);
            propertyHelper.updateAnimationStartValue(FAnimations.LAYOUT_Y, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.LAYOUT_Y, hoverAnimation, value);
        }
    }

    /**
     * Sets the layoutY based on the introduced unit(pixels or percentage).
     */
    public void y (double value, String unit) {
        if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
            if (parent != null) {
                listenersManager.remove(ListenerID.HEIGHT_Y);
                component.setLayoutY(parent.getPrefHeight() * (value / 100));
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
                    component.setLayoutY((double)newVal * (value / 100));
                };
                listenersManager.add(parent, ListenerID.HEIGHT_Y, MainProps.HEIGHT, listener);
            } else {
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_Y);
                pendingTaskManager.addTask(ListenerID.HEIGHT_Y, (p) -> {
                    component.setLayoutY(p.getPrefHeight() * (value / 100));
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
                        component.setLayoutY((double)newVal * (value / 100));
                    };
                    listenersManager.add(p, ListenerID.HEIGHT_Y, MainProps.HEIGHT, listener);
                });
            }
        }
        else if(unit.equals(FUnit.PIXELS)){
            y(value);
        }
    }

    /**
     * Sets the layoutY based on the string input. The unit can be chosen,<br>
     * the duration if we want to animate the property, the interpolator and also<br>
     * if we want the animation to run infinitely or a specified number of cycles.<br>
     * Input example:<br>
     * 5px 1s or<br>
     * 5% 1s or<br>
     * 5px 0.5s ease-in etc.
     */
    public void y (String rawValue) {
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawY = rawValue;
            double value = parser.getDoubleValue(0);
            String unit = parser.getUnit(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
                if(parent != null){
                    listenersManager.remove(ListenerID.HEIGHT_Y);
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
                    double layoutYValue = parent.getPrefHeight() * (value / 100);
                    if(duration > 0) {
                        propertyHelper.setAnimationType(FAnimations.LAYOUT_Y);
                        propertyHelper.setValues(component.getLayoutY(), layoutYValue);
                        propertyHelper.setDuration(duration);
                        propertyHelper.setInterpolator(interpolator);
                        propertyHelper.addTempAnimation(FAnimations.LAYOUT_Y, PropertyHelper.NUMBER_ANIMATION);
                    }
                    else{
                        component.setLayoutY(layoutYValue);
                    }
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
                        component.setLayoutY((double)newVal * (value / 100));
                    };
                    listenersManager.add(parent, ListenerID.HEIGHT_Y, MainProps.HEIGHT, listener);
                    valuesManager.set(MainProps.Y, layoutYValue);
                }
                else{
                    pendingTaskManager.removeTaskById(ListenerID.HEIGHT_Y);
                    pendingTaskManager.addTask(ListenerID.HEIGHT_Y, (p) -> {
                        propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
                        double layoutYValue = p.getPrefHeight() * (value / 100);
                        if(duration > 0) {
                            propertyHelper.setAnimationType(FAnimations.LAYOUT_Y);
                            propertyHelper.setValues(component.getLayoutY(), layoutYValue);
                            propertyHelper.setDuration(duration);
                            propertyHelper.setInterpolator(interpolator);
                            propertyHelper.addTempAnimation(FAnimations.LAYOUT_Y, PropertyHelper.NUMBER_ANIMATION);
                        }
                        else{
                            component.setLayoutY(layoutYValue);
                        }
                        ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
                            component.setLayoutY((double)newVal * (value / 100));
                        };
                        listenersManager.add(p, ListenerID.HEIGHT_Y, MainProps.HEIGHT, listener);
                        valuesManager.set(MainProps.Y, layoutYValue);
                    });
                }
            }
            else{
                propertyHelper.setProperty(MainProps.Y);
                propertyHelper.setAnimationsTypes(FAnimations.LAYOUT_Y, StaticAnimations.LAYOUT_Y);
                propertyHelper.setValues(valuesManager.getDouble(MainProps.Y), value);
                propertyHelper.setDuration(duration);
                propertyHelper.setInterpolator(interpolator);
                if (setPropertiesOnHover) {
                    propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else if (setPropertiesOnClick) {
                    propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
                    pendingTaskManager.removeTaskById(ListenerID.HEIGHT_Y);
                    listenersManager.remove(ListenerID.HEIGHT_Y);
                    if (duration == 0) {
                        component.setLayoutY(value);
                    } else {
                        propertyHelper.addTempAnimation(FAnimations.LAYOUT_Y, PropertyHelper.NUMBER_ANIMATION);
                    }
                    valuesManager.set(MainProps.Y, value);
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

    /**
     * Sets the width represented in pixels.
     */
    public void width (double value) {
        propertyHelper.setProperty(MainProps.WIDTH);
        propertyHelper.setStaticAnimationType(StaticAnimations.WIDTH);
        propertyHelper.setValues(component.getPrefWidth(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.WIDTH);
            component.setPrefWidth(value);
            pendingTaskManager.removeTaskById(ListenerID.WIDTH_WIDTH);
            listenersManager.remove(ListenerID.WIDTH_WIDTH);
            valuesManager.set(MainProps.WIDTH, value);
            propertyHelper.updateAnimationStartValue(FAnimations.WIDTH, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.WIDTH, hoverAnimation, value);
        }
    }

    /**
     * Sets the width based on the introduced unit(pixels or percentage).
     */
    public void width(double value, String unit){
        if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
            propertyHelper.stopTempAnimation(FAnimations.WIDTH);
            if (parent != null) {
                listenersManager.remove(ListenerID.WIDTH_WIDTH);
                component.setPrefWidth(parent.getPrefWidth() * (value / 100));
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    propertyHelper.stopTempAnimation(FAnimations.WIDTH);
                    component.setPrefWidth((double)newVal * (value / 100));
                };
                listenersManager.add(parent, ListenerID.WIDTH_WIDTH, MainProps.WIDTH, listener);
            } else {
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_WIDTH);
                pendingTaskManager.addTask(ListenerID.WIDTH_WIDTH, (p) -> {
                    component.setPrefWidth(p.getPrefWidth() * (value / 100));
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        propertyHelper.stopTempAnimation(FAnimations.WIDTH);
                        component.setPrefWidth((double)newVal * (value / 100));
                    };
                    listenersManager.add(p, ListenerID.WIDTH_WIDTH, MainProps.WIDTH, listener);
                });
            }
        }
        else if(unit.equals(FUnit.PIXELS)){
            width(value);
        }
    }

    /**
     * Sets the prefWidth based on the string input. The unit can be chosen,<br>
     * the duration if we want to animate the property, the interpolator and also<br>
     * if we want the animation to run infinitely or a specified number of cycles.<br>
     * Input example:<br>
     * 5px 1s or<br>
     * 5% 1s or<br>
     * 5px 0.5s ease-in etc.
     */
    public void width (String rawValue) {
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawWidth = rawValue;
            double value = parser.getDoubleValue(0);
            String unit = parser.getUnit(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
                if(parent != null){
                    listenersManager.remove(ListenerID.WIDTH_WIDTH);
                    propertyHelper.stopTempAnimation(FAnimations.WIDTH);
                    double widthValue = parent.getPrefWidth() * (value / 100);
                    if(duration > 0) {
                        propertyHelper.setAnimationType(FAnimations.WIDTH);
                        propertyHelper.setValues(component.getPrefWidth(), widthValue);
                        propertyHelper.setDuration(duration);
                        propertyHelper.setInterpolator(interpolator);
                        propertyHelper.addTempAnimation(FAnimations.WIDTH, PropertyHelper.NUMBER_ANIMATION);
                    }
                    else{
                        component.setPrefWidth(widthValue);
                    }
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        propertyHelper.stopTempAnimation(FAnimations.WIDTH);
                        component.setPrefWidth((double)newVal * (value / 100));
                    };
                    listenersManager.add(parent, ListenerID.WIDTH_WIDTH, MainProps.WIDTH, listener);
                    valuesManager.set(MainProps.WIDTH, widthValue);
                }
                else{
                    pendingTaskManager.removeTaskById(ListenerID.WIDTH_WIDTH);
                    pendingTaskManager.addTask(ListenerID.WIDTH_WIDTH, (p) -> {
                        propertyHelper.stopTempAnimation(FAnimations.WIDTH);
                        double widthValue = p.getPrefWidth() * (value / 100);
                        if(duration > 0) {
                            propertyHelper.setAnimationType(FAnimations.WIDTH);
                            propertyHelper.setValues(component.getPrefWidth(), widthValue);
                            propertyHelper.setDuration(duration);
                            propertyHelper.setInterpolator(interpolator);
                            propertyHelper.addTempAnimation(FAnimations.WIDTH, PropertyHelper.NUMBER_ANIMATION);
                        }
                        else{
                            component.setPrefWidth(widthValue);
                        }
                        ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                            propertyHelper.stopTempAnimation(FAnimations.WIDTH);
                            component.setPrefWidth((double)newVal * (value / 100));
                        };
                        listenersManager.add(p, ListenerID.WIDTH_WIDTH, MainProps.WIDTH, listener);
                        valuesManager.set(MainProps.WIDTH, widthValue);
                    });
                }
            }
            else{
                propertyHelper.setProperty(MainProps.WIDTH);
                propertyHelper.setAnimationsTypes(FAnimations.WIDTH, StaticAnimations.WIDTH);
                propertyHelper.setValues(valuesManager.getDouble(MainProps.WIDTH), value);
                propertyHelper.setDuration(duration);
                propertyHelper.setInterpolator(interpolator);
                if (setPropertiesOnHover) {
                    propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else if (setPropertiesOnClick) {
                    propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else {
                    propertyHelper.stopTempAnimation(FAnimations.WIDTH);
                    pendingTaskManager.removeTaskById(ListenerID.WIDTH_WIDTH);
                    listenersManager.remove(ListenerID.WIDTH_WIDTH);
                    if (duration == 0) {
                        component.setPrefWidth(value);
                    } else {
                        propertyHelper.addTempAnimation(FAnimations.WIDTH, PropertyHelper.NUMBER_ANIMATION);
                    }
                    valuesManager.set(MainProps.WIDTH, value);
                }
            }
        }
    }

    public String getRawWidth () {
        return rawWidth;
    }

    /**
     * Sets the prefHeight represented in pixels.
     */
    public void height (double value) {
        propertyHelper.setProperty(MainProps.HEIGHT);
        propertyHelper.setStaticAnimationType(StaticAnimations.HEIGHT);
        propertyHelper.setValues(component.getPrefHeight(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
            component.setPrefHeight(value);
            pendingTaskManager.removeTaskById(ListenerID.HEIGHT_HEIGHT);
            listenersManager.remove(ListenerID.HEIGHT_HEIGHT);
            valuesManager.set(MainProps.HEIGHT, value);
            propertyHelper.updateAnimationStartValue(FAnimations.HEIGHT, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.HEIGHT, hoverAnimation, value);
        }
    }

    /**
     * Sets the prefHeight based on the introduced unit(pixels or percentage).
     */
    public void height(double value, String unit){
        if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
            propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
            if (parent != null) {
                listenersManager.remove(ListenerID.HEIGHT_HEIGHT);
                component.setPrefHeight(parent.getPrefHeight() * (value / 100));
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
                    component.setPrefHeight((double)newVal * (value / 100));
                };
                listenersManager.add(parent, ListenerID.HEIGHT_HEIGHT, MainProps.HEIGHT, listener);
            } else {
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_HEIGHT);
                pendingTaskManager.addTask(ListenerID.HEIGHT_HEIGHT, (p) -> {
                    component.setPrefHeight(p.getPrefHeight() * (value / 100));
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
                        component.setPrefHeight((double)newVal * (value / 100));
                    };
                    listenersManager.add(p, ListenerID.HEIGHT_HEIGHT, MainProps.HEIGHT, listener);
                });
            }
        }
        else if(unit.equals(FUnit.PIXELS)){
            height(value);
        }
    }

    /**
     * Sets the prefHeight based on the string input. The unit can be chosen,<br>
     * the duration if we want to animate the property, the interpolator and also<br>
     * if we want the animation to run infinitely or a specified number of cycles.<br>
     * Input example:<br>
     * 5px 1s or<br>
     * 5% 1s or<br>
     * 5px 0.5s ease-in etc.
     */
    public void height (String rawValue) {
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawHeight = rawValue;
            double value = parser.getDoubleValue(0);
            String unit = parser.getUnit(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
                if(parent != null){
                    listenersManager.remove(ListenerID.HEIGHT_HEIGHT);
                    propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
                    double heightValue = parent.getPrefHeight() * (value / 100);
                    if(duration > 0) {
                        propertyHelper.setAnimationType(FAnimations.HEIGHT);
                        propertyHelper.setValues(component.getPrefHeight(), heightValue);
                        propertyHelper.setDuration(duration);
                        propertyHelper.setInterpolator(interpolator);
                        propertyHelper.addTempAnimation(FAnimations.HEIGHT, PropertyHelper.NUMBER_ANIMATION);
                    }
                    else{
                        component.setPrefHeight(heightValue);
                    }
                    ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                        propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
                        component.setPrefHeight((double)newVal * (value / 100));
                    };
                    listenersManager.add(parent, ListenerID.HEIGHT_HEIGHT, MainProps.HEIGHT, listener);
                    valuesManager.set(MainProps.HEIGHT, heightValue);
                }
                else{
                    pendingTaskManager.removeTaskById(ListenerID.HEIGHT_HEIGHT);
                    pendingTaskManager.addTask(ListenerID.HEIGHT_HEIGHT, (p) -> {
                        propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
                        double heightValue = p.getPrefHeight() * (value / 100);
                        if(duration > 0) {
                            propertyHelper.setAnimationType(FAnimations.HEIGHT);
                            propertyHelper.setValues(component.getPrefHeight(), heightValue);
                            propertyHelper.setDuration(duration);
                            propertyHelper.setInterpolator(interpolator);
                            propertyHelper.addTempAnimation(FAnimations.HEIGHT, PropertyHelper.NUMBER_ANIMATION);
                        }
                        else {
                            component.setPrefHeight(heightValue);
                        }
                        ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                            propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
                            component.setPrefHeight((double)newVal * (value / 100));
                        };
                        listenersManager.add(p, ListenerID.HEIGHT_HEIGHT, MainProps.HEIGHT, listener);
                        valuesManager.set(MainProps.HEIGHT, heightValue);
                    });
                }
            }
            else{
                propertyHelper.setProperty(MainProps.HEIGHT);
                propertyHelper.setAnimationsTypes(FAnimations.HEIGHT, StaticAnimations.HEIGHT);
                propertyHelper.setValues(valuesManager.getDouble(MainProps.HEIGHT), value);
                propertyHelper.setDuration(duration);
                propertyHelper.setInterpolator(interpolator);
                if (setPropertiesOnHover) {
                    propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else if (setPropertiesOnClick) {
                    propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else {
                    propertyHelper.stopTempAnimation(FAnimations.HEIGHT);
                    pendingTaskManager.removeTaskById(ListenerID.HEIGHT_HEIGHT);
                    listenersManager.remove(ListenerID.HEIGHT_HEIGHT);
                    if (duration == 0) {
                        component.setPrefHeight(value);
                    } else {
                        propertyHelper.addTempAnimation(FAnimations.HEIGHT, PropertyHelper.NUMBER_ANIMATION);
                    }
                    valuesManager.set(MainProps.HEIGHT, value);
                }
            }
        }
    }

    public String getRawHeight () {
        return rawHeight;
    }

    public void scaleX (double value) {
        propertyHelper.setProperty(MainProps.SCALE_X);
        propertyHelper.setStaticAnimationType(StaticAnimations.SCALE_X);
        propertyHelper.setValues(component.getScaleX(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.SCALE_X);
            component.setScaleX(value);
            valuesManager.set(MainProps.SCALE_X, value);
            propertyHelper.updateAnimationStartValue(FAnimations.SCALE_X, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.SCALE_X, hoverAnimation, value);
        }
    }

    public void scaleX (String rawValue) {
        parser.parse(PropertyParser.NUMBER_PROPERTY, rawValue);
        if (parser.isValid()) {
            this.rawScaleX = rawValue;
            propertyHelper.setProperty(MainProps.SCALE_X);
            propertyHelper.setAnimationsTypes(FAnimations.SCALE_X, StaticAnimations.SCALE_X);
            propertyHelper.setValues(valuesManager.getDouble(MainProps.SCALE_X), parser.getDoubleValue(0));
            propertyHelper.setDuration(parser.getDuration(0));
            propertyHelper.setInterpolator(parser.getInterpolator(0));
            if (setPropertiesOnHover) {
                propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
            } else if (setPropertiesOnClick) {
                propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
            } else {
                propertyHelper.stopTempAnimation(FAnimations.SCALE_X);
                if (parser.getDuration(0) == 0) {
                    component.setScaleX(parser.getDoubleValue(0));
                } else {
                    propertyHelper.addTempAnimation(FAnimations.SCALE_X, PropertyHelper.NUMBER_ANIMATION);
                }
                valuesManager.set(MainProps.SCALE_X, parser.getDoubleValue(0));
            }
        }
    }

    public String getRawScaleX () {
        return rawScaleX;
    }

    public void scaleY (double value) {
        propertyHelper.setProperty(MainProps.SCALE_Y);
        propertyHelper.setStaticAnimationType(StaticAnimations.SCALE_Y);
        propertyHelper.setValues(component.getScaleY(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.SCALE_Y);
            component.setScaleY(value);
            valuesManager.set(MainProps.SCALE_Y, value);
            propertyHelper.updateAnimationStartValue(FAnimations.SCALE_Y, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.SCALE_Y, hoverAnimation, value);
        }
    }

    public void scaleY (String rawValue) {
        parser.parse(PropertyParser.NUMBER_PROPERTY, rawValue);
        if (parser.isValid()) {
            this.rawScaleY = rawValue;
            propertyHelper.setProperty(MainProps.SCALE_Y);
            propertyHelper.setAnimationsTypes(FAnimations.SCALE_Y, StaticAnimations.SCALE_Y);
            propertyHelper.setValues(valuesManager.getDouble(MainProps.SCALE_Y), parser.getDoubleValue(0));
            propertyHelper.setDuration(parser.getDuration(0));
            propertyHelper.setInterpolator(parser.getInterpolator(0));
            if (setPropertiesOnHover) {
                propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
            } else if (setPropertiesOnClick) {
                propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
            } else {
                propertyHelper.stopTempAnimation(FAnimations.SCALE_Y);
                if (parser.getDuration(0) == 0) {
                    component.setScaleY(parser.getDoubleValue(0));
                } else {
                    propertyHelper.addTempAnimation(FAnimations.SCALE_Y, PropertyHelper.NUMBER_ANIMATION);
                }
                valuesManager.set(MainProps.SCALE_Y, parser.getDoubleValue(0));
            }
        }
    }

    public String getRawScaleY () {
        return rawScaleY;
    }

    public void translateX (double value) {
        propertyHelper.setProperty(MainProps.TRANSLATE_X);
        propertyHelper.setStaticAnimationType(StaticAnimations.TRANSLATE_X);
        propertyHelper.setValues(component.getTranslateX(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_X);
            component.setTranslateX(value);
            listenersManager.remove(ListenerID.TRANSLATE_X);
            valuesManager.set(MainProps.TRANSLATE_X, value);
            propertyHelper.updateAnimationStartValue(FAnimations.TRANSLATE_X, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.TRANSLATE_X, hoverAnimation, value);
        }
    }

    public void translateX (double value, String unit) {
        if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
            propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_X);
            listenersManager.remove(ListenerID.TRANSLATE_X);
            component.setTranslateX(component.getPrefWidth() * (value / 100));
            ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_X);
                component.setTranslateX((double)newVal * (value / 100));
            };
            listenersManager.add(component, ListenerID.TRANSLATE_X, MainProps.WIDTH, listener);
        }
        else if(unit.equals(FUnit.PIXELS)){
            translateX(value);
        }
    }

    public void translateX (String rawValue) {
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawTranslateX = rawValue;
            double value = parser.getDoubleValue(0);
            String unit = parser.getUnit(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
                listenersManager.remove(ListenerID.TRANSLATE_X);
                propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_X);
                double translateXValue = component.getPrefWidth() * (value / 100);
                if(duration > 0) {
                    propertyHelper.setAnimationType(FAnimations.TRANSLATE_X);
                    propertyHelper.setValues(component.getTranslateX(), translateXValue);
                    propertyHelper.setDuration(duration);
                    propertyHelper.setInterpolator(interpolator);
                    propertyHelper.addTempAnimation(FAnimations.TRANSLATE_X, PropertyHelper.NUMBER_ANIMATION);
                }
                else{
                    component.setTranslateX(translateXValue);
                }
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_X);
                    component.setTranslateX((double)newVal * (value / 100));
                };
                listenersManager.add(component, ListenerID.TRANSLATE_X, MainProps.WIDTH, listener);
                valuesManager.set(MainProps.TRANSLATE_X, translateXValue);
            }
            else{
                propertyHelper.setProperty(MainProps.TRANSLATE_X);
                propertyHelper.setAnimationsTypes(FAnimations.TRANSLATE_X, StaticAnimations.TRANSLATE_X);
                propertyHelper.setValues(valuesManager.getDouble(MainProps.TRANSLATE_X), value);
                propertyHelper.setDuration(duration);
                propertyHelper.setInterpolator(interpolator);
                if (setPropertiesOnHover) {
                    propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else if (setPropertiesOnClick) {
                    propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else {
                    propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_X);
                    listenersManager.remove(ListenerID.TRANSLATE_X);
                    if (duration == 0) {
                        component.setTranslateX(value);
                    } else {
                        propertyHelper.addTempAnimation(FAnimations.TRANSLATE_X, PropertyHelper.NUMBER_ANIMATION);
                    }
                    valuesManager.set(MainProps.TRANSLATE_X, value);
                }
            }
        }
    }

    public String getRawTranslateX () {
        return rawTranslateX;
    }

    public void translateY (double value) {
        propertyHelper.setProperty(MainProps.TRANSLATE_Y);
        propertyHelper.setStaticAnimationType(StaticAnimations.TRANSLATE_Y);
        propertyHelper.setValues(component.getTranslateY(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_Y);
            component.setTranslateY(value);
            listenersManager.remove(ListenerID.TRANSLATE_Y);
            valuesManager.set(MainProps.TRANSLATE_Y, value);
            propertyHelper.updateAnimationStartValue(FAnimations.TRANSLATE_Y, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.TRANSLATE_Y, hoverAnimation, value);
        }
    }

    public void translateY (double value, String unit) {
        if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
            propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_Y);
            listenersManager.remove(ListenerID.TRANSLATE_Y);
            component.setTranslateY(component.getPrefHeight() * (value / 100));
            ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_Y);
                component.setTranslateY((double)newVal * (value / 100));
            };
            listenersManager.add(component, ListenerID.TRANSLATE_Y, MainProps.HEIGHT, listener);
        }
        else if(unit.equals(FUnit.PIXELS)){
            translateY(value);
        }
    }

    public void translateY (String rawValue) {
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawTranslateY = rawValue;
            double value = parser.getDoubleValue(0);
            String unit = parser.getUnit(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            if (unit.equals(FUnit.PERCENTAGE) && !setPropertiesOnHover && !setPropertiesOnClick) {
                listenersManager.remove(ListenerID.TRANSLATE_Y);
                propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_Y);
                double translateYValue = component.getPrefHeight() * (value / 100);
                if(duration > 0) {
                    propertyHelper.setAnimationType(FAnimations.TRANSLATE_Y);
                    propertyHelper.setValues(component.getTranslateY(), translateYValue);
                    propertyHelper.setDuration(duration);
                    propertyHelper.setInterpolator(interpolator);
                    propertyHelper.addTempAnimation(FAnimations.TRANSLATE_Y, PropertyHelper.NUMBER_ANIMATION);
                }
                else{
                    component.setTranslateY(translateYValue);
                }
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_Y);
                    component.setTranslateY((double)newVal * (value / 100));
                };
                listenersManager.add(component, ListenerID.TRANSLATE_Y, MainProps.HEIGHT, listener);
                valuesManager.set(MainProps.TRANSLATE_Y, translateYValue);
            }
            else{
                propertyHelper.setProperty(MainProps.TRANSLATE_Y);
                propertyHelper.setAnimationsTypes(FAnimations.TRANSLATE_Y, StaticAnimations.TRANSLATE_Y);
                propertyHelper.setValues(valuesManager.getDouble(MainProps.TRANSLATE_Y), value);
                propertyHelper.setDuration(duration);
                propertyHelper.setInterpolator(interpolator);
                if (setPropertiesOnHover) {
                    propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else if (setPropertiesOnClick) {
                    propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                    propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
                } else {
                    propertyHelper.stopTempAnimation(FAnimations.TRANSLATE_Y);
                    listenersManager.remove(ListenerID.TRANSLATE_Y);
                    if (duration == 0) {
                        component.setTranslateY(value);
                    } else {
                        propertyHelper.addTempAnimation(FAnimations.TRANSLATE_Y, PropertyHelper.NUMBER_ANIMATION);
                    }
                    valuesManager.set(MainProps.TRANSLATE_Y, value);
                }
            }
        }
    }

    public String getRawTranslateY () {
        return rawTranslateY;
    }

    public void rotate (double value) {
        propertyHelper.setProperty(MainProps.ROTATE);
        propertyHelper.setStaticAnimationType(StaticAnimations.ROTATE);
        propertyHelper.setValues(component.getRotate(), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.ROTATE);
            component.setRotate(value);
            valuesManager.set(MainProps.ROTATE, value);
            propertyHelper.updateAnimationStartValue(FAnimations.ROTATE, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.ROTATE, hoverAnimation, value);
        }
    }

    public void rotate (String rawValue) {
        parser.parse(PropertyParser.NUMBER_PROPERTY, rawValue);
        if (parser.isValid()) {
            this.rawRotate = rawValue;
            propertyHelper.setProperty(MainProps.ROTATE);
            propertyHelper.setAnimationsTypes(FAnimations.ROTATE, StaticAnimations.ROTATE);
            propertyHelper.setValues(valuesManager.getDouble(MainProps.ROTATE), parser.getDoubleValue(0));
            propertyHelper.setDuration(parser.getDuration(0));
            propertyHelper.setInterpolator(parser.getInterpolator(0));
            if (setPropertiesOnHover) {
                propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
            } else if (setPropertiesOnClick) {
                propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
            } else {
                propertyHelper.stopTempAnimation(FAnimations.ROTATE);
                if (parser.getDuration(0) == 0) {
                    component.setRotate(parser.getDoubleValue(0));
                } else {
                    propertyHelper.addTempAnimation(FAnimations.ROTATE, PropertyHelper.NUMBER_ANIMATION);
                }
                valuesManager.set(MainProps.ROTATE, parser.getDoubleValue(0));
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
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid()) {
            this.rawPadding = rawValue;
            if (setPropertiesOnHover){
                propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                propertyHelper.removeMultipleSidesAnimation(FAnimations.PADDING);
                propertyHelper.removeMultipleSidesStaticAnimation(StaticAnimations.PADDING);
                propertyHelper.addMultipleSidesSizeAnimation(FAnimations.PADDING, MainProps.PADDING);
            }
            else if(setPropertiesOnClick){
                propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                propertyHelper.removeMultipleSidesAnimation(FAnimations.PADDING);
                propertyHelper.removeMultipleSidesStaticAnimation(StaticAnimations.PADDING);
                propertyHelper.addMultipleSidesSizeAnimation(FAnimations.PADDING, MainProps.PADDING);
            }
            else{
                propertyHelper.addPaddingProperty(parent == null);
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
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid()) {
            this.rawMargin = rawValue;
            parser.spreadDoubleValues();
            this.margin = new Margin(parser.getDoubleValue(0), parser.getDoubleValue(1), parser.getDoubleValue(2), parser.getDoubleValue(3));
        }
    }

    public Margin getMargin(){
        return margin;
    }

    public String getRawMargin () {
        return rawMargin;
    }

    public void top(double value){
        if(!setPropertiesOnHover && !setPropertiesOnClick){
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
            pendingTaskManager.removeTaskById(ListenerID.HEIGHT_Y);
            pendingTaskManager.removeTaskById(ListenerID.HEIGHT_TOP);
            pendingTaskManager.removeTaskById(ListenerID.HEIGHT_BOTTOM);
            listenersManager.remove(ListenerID.HEIGHT_Y);
            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_PARENT);
            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_COMPONENT);
            component.setLayoutY(value);
            valuesManager.set(MainProps.Y, value);
        }
    }

    public void top(String rawValue){
        parser.parse(PropertyParser.NUMBER_PROPERTY, rawValue);
        if(parser.isValid() && !setPropertiesOnHover && !setPropertiesOnClick){
            double value = parser.getDoubleValue(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
            listenersManager.remove(ListenerID.HEIGHT_Y);
            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_PARENT);
            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_COMPONENT);
            if(parent != null){
                if(duration > 0) {
                    propertyHelper.setAnimationType(FAnimations.LAYOUT_Y);
                    propertyHelper.setValues(component.getLayoutY(), value);
                    propertyHelper.setDuration(duration);
                    propertyHelper.setInterpolator(interpolator);
                    propertyHelper.addTempAnimation(FAnimations.LAYOUT_Y, PropertyHelper.NUMBER_ANIMATION);
                }
                else{
                    component.setLayoutY(value);
                }
                valuesManager.set(MainProps.Y, value);
            }
            else{
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_Y);
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_TOP);
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_BOTTOM);
                pendingTaskManager.addTask(ListenerID.HEIGHT_TOP, (p) -> {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    if(duration > 0) {
                        propertyHelper.setAnimationType(FAnimations.LAYOUT_Y);
                        propertyHelper.setValues(component.getLayoutY(), value);
                        propertyHelper.setDuration(duration);
                        propertyHelper.setInterpolator(interpolator);
                        propertyHelper.addTempAnimation(FAnimations.LAYOUT_Y, PropertyHelper.NUMBER_ANIMATION);
                    }
                    else{
                        component.setLayoutY(value);
                    }
                    valuesManager.set(MainProps.Y, value);
                });
            }
        }
    }

    public void right(double value){
        if(!setPropertiesOnHover && !setPropertiesOnClick){
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
            listenersManager.remove(ListenerID.WIDTH_X);
            listenersManager.remove(ListenerID.WIDTH_RIGHT_PARENT);
            listenersManager.remove(ListenerID.WIDTH_RIGHT_COMPONENT);
            if(parent != null){
                double layoutXValue = parent.getPrefWidth() - component.getPrefWidth() - value;
                component.setLayoutX(layoutXValue);
                ChangeListener<Number> parentListener = (val, oldVal, newVal) -> {
                    double parentWidth = parent.getPrefWidth();
                    double componentWidth = component.getPrefWidth();
                    component.setLayoutX(parentWidth - componentWidth - value);
                };
                ChangeListener<Number> componentListener = (val, oldVal, newVal) -> {
                    component.setLayoutX(parent.getPrefWidth() - (double)newVal - value);
                };
                listenersManager.add(parent, ListenerID.WIDTH_RIGHT_PARENT, MainProps.WIDTH, parentListener);
                listenersManager.add(component, ListenerID.WIDTH_RIGHT_COMPONENT, MainProps.WIDTH, componentListener);
                valuesManager.set(MainProps.X, layoutXValue);
            }
            else{
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_X);
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_RIGHT);
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_LEFT);
                pendingTaskManager.addTask(ListenerID.WIDTH_RIGHT, (p) -> {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    component.setLayoutX(p.getPrefWidth() - component.getPrefWidth() - value);
                    ChangeListener<Number> parentListener = (val, oldVal, newVal) -> {
                        double parentWidth = p.getPrefWidth();
                        double componentWidth = component.getPrefWidth();
                        component.setLayoutX(parentWidth - componentWidth - value);
                    };
                    ChangeListener<Number> componentListener = (val, oldVal, newVal) -> {
                        component.setLayoutX(p.getPrefWidth() - (double)newVal - value);
                    };
                    listenersManager.add(p, ListenerID.WIDTH_RIGHT_PARENT, MainProps.WIDTH, parentListener);
                    listenersManager.add(component, ListenerID.WIDTH_RIGHT_COMPONENT, MainProps.WIDTH, componentListener);
                    valuesManager.set(MainProps.X, p.getPrefWidth() - component.getPrefWidth() - value);
                });
            }
        }
    }

    public void right(String rawValue){
        parser.parse(PropertyParser.NUMBER_PROPERTY, rawValue);
        if(parser.isValid() && !setPropertiesOnHover && !setPropertiesOnClick){
            double value = parser.getDoubleValue(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
            listenersManager.remove(ListenerID.WIDTH_X);
            listenersManager.remove(ListenerID.WIDTH_RIGHT_PARENT);
            listenersManager.remove(ListenerID.WIDTH_RIGHT_COMPONENT);
            if(parent != null){
                double layoutXValue = parent.getPrefWidth() - component.getPrefWidth() - value;
                if(duration > 0) {
                    propertyHelper.setAnimationType(FAnimations.LAYOUT_X);
                    propertyHelper.setValues(component.getLayoutX(), layoutXValue);
                    propertyHelper.setDuration(duration);
                    propertyHelper.setInterpolator(interpolator);
                    propertyHelper.addTempAnimation(FAnimations.LAYOUT_X, PropertyHelper.NUMBER_ANIMATION);
                }
                else{
                    component.setLayoutX(layoutXValue);
                }
                ChangeListener<Number> parentListener = (val, oldVal, newVal) -> {
                    double parentWidth = parent.getPrefWidth();
                    double componentWidth = component.getPrefWidth();
                    component.setLayoutX(parentWidth - componentWidth - value);
                };
                ChangeListener<Number> componentListener = (val, oldVal, newVal) -> {
                    component.setLayoutX(parent.getPrefWidth() - (double)newVal - value);
                };
                listenersManager.add(parent, ListenerID.WIDTH_RIGHT_PARENT, MainProps.WIDTH, parentListener);
                listenersManager.add(component, ListenerID.WIDTH_RIGHT_COMPONENT, MainProps.WIDTH, componentListener);
                valuesManager.set(MainProps.X, layoutXValue);
            }
            else{
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_X);
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_RIGHT);
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_LEFT);
                pendingTaskManager.addTask(ListenerID.WIDTH_RIGHT, (p) -> {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    double layoutXValue = p.getPrefWidth() - component.getPrefWidth() - value;
                    if(duration > 0) {
                        propertyHelper.setAnimationType(FAnimations.LAYOUT_X);
                        propertyHelper.setValues(component.getLayoutX(), layoutXValue);
                        propertyHelper.setDuration(duration);
                        propertyHelper.setInterpolator(interpolator);
                        propertyHelper.addTempAnimation(FAnimations.LAYOUT_X, PropertyHelper.NUMBER_ANIMATION);
                    }
                    else{
                        component.setLayoutX(layoutXValue);
                    }
                    ChangeListener<Number> parentListener = (val, oldVal, newVal) -> {
                        double parentWidth = p.getPrefWidth();
                        double componentWidth = component.getPrefWidth();
                        component.setLayoutX(parentWidth - componentWidth - value);
                    };
                    ChangeListener<Number> componentListener = (val, oldVal, newVal) -> {
                        component.setLayoutX(p.getPrefWidth() - (double)newVal - value);
                    };
                    listenersManager.add(parent, ListenerID.WIDTH_RIGHT_PARENT, MainProps.WIDTH, parentListener);
                    listenersManager.add(component, ListenerID.WIDTH_RIGHT_COMPONENT, MainProps.WIDTH, componentListener);
                    valuesManager.set(MainProps.X, p.getPrefWidth() - component.getPrefWidth() - value);
                    valuesManager.set(MainProps.X, layoutXValue);
                });
            }
        }
    }

    public void bottom(double value){
        if(!setPropertiesOnHover && !setPropertiesOnClick){
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
            listenersManager.remove(ListenerID.HEIGHT_Y);
            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_PARENT);
            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_COMPONENT);
            if(parent != null){
                double layoutYValue = parent.getPrefHeight() - component.getPrefHeight() - value;
                component.setLayoutY(layoutYValue);
                ChangeListener<Number> parentListener = (val, oldVal, newVal) -> {
                    double parentHeight = (double) newVal;
                    double componentHeight = component.getPrefHeight();
                    component.setLayoutY(parentHeight - componentHeight - value);
                };
                ChangeListener<Number> componentListener = (val, oldVal, newVal) -> {
                    double componentHeight = (double) newVal;
                    component.setLayoutY(parent.getPrefHeight() - componentHeight - value);
                };
                listenersManager.add(parent, ListenerID.HEIGHT_BOTTOM_PARENT, MainProps.HEIGHT, parentListener);
                listenersManager.add(component, ListenerID.HEIGHT_BOTTOM_COMPONENT, MainProps.HEIGHT, componentListener);
                valuesManager.set(MainProps.Y, layoutYValue);
            }
            else{
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_Y);
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_BOTTOM);
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_TOP);
                pendingTaskManager.addTask(ListenerID.HEIGHT_BOTTOM, (p) -> {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    component.setLayoutY(p.getPrefHeight() - component.getPrefHeight() - value);
                    ChangeListener<Number> parentListener = (val, oldVal, newVal) -> {
                        double parentHeight = p.getPrefHeight();
                        double componentHeight = component.getPrefHeight();
                        component.setLayoutY(parentHeight - componentHeight - value);
                    };
                    ChangeListener<Number> componentListener = (val, oldVal, newVal) -> {
                        component.setLayoutY(p.getPrefHeight() - (double)newVal - value);
                    };
                    listenersManager.add(p, ListenerID.HEIGHT_BOTTOM_PARENT, MainProps.HEIGHT, parentListener);
                    listenersManager.add(component, ListenerID.HEIGHT_BOTTOM_COMPONENT, MainProps.HEIGHT, componentListener);
                    valuesManager.set(MainProps.Y, p.getPrefHeight() - component.getPrefHeight() - value);
                });
            }
        }
    }

    public void bottom(String rawValue){
        parser.parse(PropertyParser.NUMBER_PROPERTY, rawValue);
        if(parser.isValid() && !setPropertiesOnHover && !setPropertiesOnClick){
            double value = parser.getDoubleValue(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_Y);
            listenersManager.remove(ListenerID.HEIGHT_Y);
            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_PARENT);
            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_COMPONENT);
            if(parent != null){
                double layoutYValue = parent.getPrefHeight() - component.getPrefHeight() - value;
                if(duration > 0) {
                    propertyHelper.setAnimationType(FAnimations.LAYOUT_Y);
                    propertyHelper.setValues(component.getLayoutY(), layoutYValue);
                    propertyHelper.setDuration(duration);
                    propertyHelper.setInterpolator(interpolator);
                    propertyHelper.addTempAnimation(FAnimations.LAYOUT_Y, PropertyHelper.NUMBER_ANIMATION);
                }
                else{
                    component.setLayoutY(layoutYValue);
                }
                ChangeListener<Number> parentListener = (val, oldVal, newVal) -> {
                    double parentHeight = parent.getPrefHeight();
                    double componentHeight = component.getPrefHeight();
                    component.setLayoutY(parentHeight - componentHeight - value);
                };
                ChangeListener<Number> componentListener = (val, oldVal, newVal) -> {
                    component.setLayoutY(parent.getPrefHeight() - (double)newVal - value);
                };
                listenersManager.add(parent, ListenerID.HEIGHT_BOTTOM_PARENT, MainProps.HEIGHT, parentListener);
                listenersManager.add(component, ListenerID.HEIGHT_BOTTOM_COMPONENT, MainProps.HEIGHT, componentListener);
                valuesManager.set(MainProps.Y, layoutYValue);
            }
            else{
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_Y);
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_BOTTOM);
                pendingTaskManager.removeTaskById(ListenerID.HEIGHT_TOP);
                pendingTaskManager.addTask(ListenerID.HEIGHT_BOTTOM, (p) -> {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    double layoutYValue = p.getPrefHeight() - component.getPrefHeight() - value;
                    if(duration > 0) {
                        propertyHelper.setAnimationType(FAnimations.LAYOUT_Y);
                        propertyHelper.setValues(component.getLayoutY(), layoutYValue);
                        propertyHelper.setDuration(duration);
                        propertyHelper.setInterpolator(interpolator);
                        propertyHelper.addTempAnimation(FAnimations.LAYOUT_Y, PropertyHelper.NUMBER_ANIMATION);
                    }
                    else{
                        component.setLayoutY(layoutYValue);
                    }
                    ChangeListener<Number> parentListener = (val, oldVal, newVal) -> {
                        double parentHeight = p.getPrefHeight();
                        double componentHeight = component.getPrefHeight();
                        component.setLayoutY(parentHeight - componentHeight - value);
                    };
                    ChangeListener<Number> componentListener = (val, oldVal, newVal) -> {
                        component.setLayoutY(p.getPrefHeight() - (double)newVal - value);
                    };
                    listenersManager.add(parent, ListenerID.HEIGHT_BOTTOM_PARENT, MainProps.HEIGHT, parentListener);
                    listenersManager.add(component, ListenerID.HEIGHT_BOTTOM_COMPONENT, MainProps.HEIGHT, componentListener);
                    valuesManager.set(MainProps.Y, p.getPrefHeight() - component.getPrefHeight() - value);
                    valuesManager.set(MainProps.Y, layoutYValue);
                });
            }
        }
    }

    public void left(double value){
        if(!setPropertiesOnHover && !setPropertiesOnClick){
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
            listenersManager.remove(ListenerID.WIDTH_X);
            listenersManager.remove(ListenerID.WIDTH_RIGHT_PARENT);
            listenersManager.remove(ListenerID.WIDTH_RIGHT_COMPONENT);
            component.setLayoutX(value);
            valuesManager.set(MainProps.X, value);
        }
    }

    public void left(String rawValue){
        parser.parse(PropertyParser.NUMBER_PROPERTY, rawValue);
        if(parser.isValid() && !setPropertiesOnHover && !setPropertiesOnClick){
            double value = parser.getDoubleValue(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
            listenersManager.remove(ListenerID.WIDTH_X);
            listenersManager.remove(ListenerID.WIDTH_RIGHT_PARENT);
            listenersManager.remove(ListenerID.WIDTH_RIGHT_COMPONENT);
            if(parent != null){
                if(duration > 0) {
                    propertyHelper.setAnimationType(FAnimations.LAYOUT_X);
                    propertyHelper.setValues(component.getLayoutX(), value);
                    propertyHelper.setDuration(duration);
                    propertyHelper.setInterpolator(interpolator);
                    propertyHelper.addTempAnimation(FAnimations.LAYOUT_X, PropertyHelper.NUMBER_ANIMATION);
                }
                else{
                    component.setLayoutX(value);
                }
                valuesManager.set(MainProps.X, value);
            }
            else{
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_X);
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_LEFT);
                pendingTaskManager.removeTaskById(ListenerID.WIDTH_RIGHT);
                pendingTaskManager.addTask(ListenerID.WIDTH_LEFT, (p) -> {
                    propertyHelper.stopTempAnimation(FAnimations.LAYOUT_X);
                    if(duration > 0) {
                        propertyHelper.setAnimationType(FAnimations.LAYOUT_X);
                        propertyHelper.setValues(component.getLayoutX(), value);
                        propertyHelper.setDuration(duration);
                        propertyHelper.setInterpolator(interpolator);
                        propertyHelper.addTempAnimation(FAnimations.LAYOUT_X, PropertyHelper.NUMBER_ANIMATION);
                    }
                    else{
                        component.setLayoutX(value);
                    }
                    valuesManager.set(MainProps.X, value);
                });
            }
        }
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
    public void bgColor (String rawValue) {
        parser.parse(PropertyParser.COLOR_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawBgColor = rawValue;
            String color = parser.getColorValue(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            propertyHelper.setProperty(MainProps.BG_COLOR);
            propertyHelper.setAnimationsTypes(FAnimations.BG_COLOR, StaticAnimations.BG_COLOR);
            propertyHelper.setColors(valuesManager.getString(MainProps.BG_COLOR), color);
            propertyHelper.setDuration(duration);
            propertyHelper.setInterpolator(interpolator);
            if(setPropertiesOnHover){
                propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                propertyHelper.addAnimation(PropertyHelper.COLOR_ANIMATION);
            }
            else if(setPropertiesOnClick){
                propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                propertyHelper.addAnimation(PropertyHelper.COLOR_ANIMATION);
            }
            else{
                propertyHelper.stopTempAnimation(FAnimations.BG_COLOR);
                propertyHelper.stopTempAnimation(FAnimations.BG_OPACITY);
                if(duration == 0){
                    BackgroundSetter.setBackgroundColor(component, color);
                }
                else{
                    propertyHelper.addTempAnimation(FAnimations.BG_COLOR, PropertyHelper.COLOR_ANIMATION);
                }
                BackgroundSetter.setBackgroundOpacity(component, valuesManager.getDouble(MainProps.BG_OPACITY));
                valuesManager.set(MainProps.BG_COLOR, color);
                propertyHelper.updateAnimationStartColor(hoverAnimation, FAnimations.BG_COLOR, color);
                propertyHelper.updateAnimationStartColor(clickAnimation, FAnimations.BG_COLOR, color);
            }
        }
    }

    public String getBgColor () {
        return BackgroundSetter.getBackgroundHexColor(component);
    }

    public String getRawBgColor () {
        return rawBgColor;
    }

    public void bgOpacity (double value) {
        propertyHelper.setProperty(MainProps.BG_OPACITY);
        propertyHelper.setStaticAnimationType(StaticAnimations.BG_OPACITY);
        propertyHelper.setValues(BackgroundSetter.getBackgroundOpacity(component), value);
        if (setPropertiesOnHover){
            propertyHelper.setStaticAnimation(staticHoverAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else if (setPropertiesOnClick){
            propertyHelper.setStaticAnimation(staticClickAnimation);
            propertyHelper.addStaticAnimation(PropertyHelper.NUMBER_ANIMATION);
        }
        else{
            propertyHelper.stopTempAnimation(FAnimations.BG_OPACITY);
            propertyHelper.stopTempAnimation(FAnimations.BG_COLOR);
            BackgroundSetter.setBackgroundColor(component, valuesManager.getString(MainProps.BG_COLOR));
            BackgroundSetter.setBackgroundOpacity(component, value);
            valuesManager.set(MainProps.BG_OPACITY, value);
            propertyHelper.updateAnimationStartValue(FAnimations.BG_OPACITY, clickAnimation, value);
            propertyHelper.updateAnimationStartValue(FAnimations.BG_OPACITY, hoverAnimation, value);
        }
    }

    public void bgOpacity(String rawValue){
        parser.parse(PropertyParser.NUMBER_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawBgOpacity = rawValue;
            double value = parser.getDoubleValue(0);
            long duration = parser.getDuration(0);
            String interpolator = parser.getInterpolator(0);
            propertyHelper.setProperty(MainProps.BG_OPACITY);
            propertyHelper.setAnimationsTypes(FAnimations.BG_OPACITY, StaticAnimations.BG_OPACITY);
            propertyHelper.setValues(valuesManager.getDouble(MainProps.BG_OPACITY), value);
            propertyHelper.setDuration(duration);
            propertyHelper.setInterpolator(interpolator);
            if(setPropertiesOnHover){
                propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
            }
            else if(setPropertiesOnClick){
                propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                propertyHelper.addAnimation(PropertyHelper.NUMBER_ANIMATION);
            }
            else{
                propertyHelper.stopTempAnimation(FAnimations.BG_OPACITY);
                propertyHelper.stopTempAnimation(FAnimations.BG_COLOR);
                BackgroundSetter.setBackgroundColor(component, valuesManager.getString(MainProps.BG_COLOR));
                if(duration == 0){
                    BackgroundSetter.setBackgroundOpacity(component, value);
                }
                else{
                    propertyHelper.addTempAnimation(FAnimations.BG_OPACITY, PropertyHelper.NUMBER_ANIMATION);
                }
                valuesManager.set(MainProps.BG_OPACITY, value);
                propertyHelper.updateAnimationStartValue(hoverAnimation, FAnimations.BG_OPACITY, value);
                propertyHelper.updateAnimationStartValue(clickAnimation, FAnimations.BG_OPACITY, value);
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

    public void bgRadius(String rawValue){
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid()) {
            this.rawBgRadius = rawValue;
            if (setPropertiesOnHover){
                propertyHelper.setAnimations(hoverAnimation, staticHoverAnimation);
                propertyHelper.removeMultipleSidesAnimation(FAnimations.BG_RADIUS);
                propertyHelper.removeMultipleSidesStaticAnimation(StaticAnimations.BG_RADIUS);
                propertyHelper.addMultipleSidesSizeAnimation(FAnimations.BG_RADIUS, MainProps.BG_RADIUS);
            }
            else if(setPropertiesOnClick){
                propertyHelper.setAnimations(clickAnimation, staticClickAnimation);
                propertyHelper.removeMultipleSidesAnimation(FAnimations.BG_RADIUS);
                propertyHelper.removeMultipleSidesStaticAnimation(StaticAnimations.BG_RADIUS);
                propertyHelper.addMultipleSidesSizeAnimation(FAnimations.BG_RADIUS, MainProps.BG_RADIUS);
            }
            else{
                propertyHelper.addBgRadiusProperty(parent == null);
            }
        }
    }

//    public void bgRadius (String rawValue) {
//        String[] values = rawValue.split("\\s+");
//        boolean validated = PropertyValidatorFilter.validateMultipleDimensionValue(values);
//        if (validated) {
//            this.rawBgRadius = rawValue;
//            String[] filteredValues = PropertyValidatorFilter.filterMultipleDimensionValues(values);
//            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
//            if (radii == null) {
//                radii = new CornerRadii(0, false);
//            }
//            if (filteredValues.length == 1) {
//                String[] currValue = filteredValues[0].split("\\s+");
//                double value = PropertyValidatorFilter.getDoubleValue(currValue[0]);
//                String unit = PropertyValidatorFilter.getUnit(currValue[0]);
//                long duration = 1;
//                if (currValue.length == 2) {
//                    duration = PropertyValidatorFilter.getDuration(currValue[1]);
//                }
//                if (unit.equals(FUnit.PERCENTAGE)) {
//                    bgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
//                    bgRadiusProperty.addListener((valType, oldVal, newVal) -> {
//                        BackgroundSetter.setBackgroundRadius(component, (double) newVal);
//                    });
//                    value = (value / 100) * component.getPrefWidth();
//                }
//                if (setPropertiesOnHover) {
//                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, MainProps.BG_RADIUS, hoverAnimation, FAnimations.BG_RADIUS, value, duration);
//                } else if (setPropertiesOnClick) {
//                    PropertyUtil.setOneDimensionAnimation(radii.getTopLeftHorizontalRadius(), initialValues, MainProps.BG_RADIUS, clickAnimation, FAnimations.BG_RADIUS, value, duration);
//                } else {
//                    if (duration == 1) {
//                        BackgroundSetter.setBackgroundRadius(component, value);
//                    } else {
//                        PropertyUtil.setTempAnimation(component, radii.getTopLeftVerticalRadius(), initialValues, MainProps.BG_RADIUS, FAnimations.BG_RADIUS, value, duration);
//                    }
//                }
//            } else if (filteredValues.length == 4) {
//                List<String> radiusAnimationSides = new ArrayList<>(List.of(FAnimations.BG_TL_RADIUS, FAnimations.BG_TR_RADIUS, FAnimations.BG_BR_RADIUS, FAnimations.BG_BL_RADIUS));
//                List<Double> currRadiusValues = new ArrayList<>(List.of(radii.getTopLeftHorizontalRadius(), radii.getTopRightHorizontalRadius(), radii.getBottomRightHorizontalRadius(), radii.getBottomLeftHorizontalRadius()));
//                List<String> bgRadiusInitialValues = new ArrayList<>(List.of(MainProps.BG_TL_RADIUS, MainProps.BG_TR_RADIUS, MainProps.BG_BR_RADIUS, MainProps.BG_BL_RADIUS));
//                for (int i = 0; i < filteredValues.length; i++) {
//                    String[] currValue = filteredValues[i].split("\\s+");
//                    double value = PropertyValidatorFilter.getDoubleValue(currValue[0]);
//                    String unit = PropertyValidatorFilter.getUnit(currValue[0]);
//                    long duration = 1;
//                    if (currValue.length == 2) {
//                        duration = PropertyValidatorFilter.getDuration(currValue[1]);
//                    }
//                    if (unit.equals(FUnit.PERCENTAGE)) {
//                        bgRadiusProperties.get(i).bind(component.prefWidthProperty().multiply(value / 100));
//                        if (i == 0) {
//                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
//                                BackgroundSetter.setTopLeftBackgroundRadius(component, (double) newVal);
//                            });
//                        }
//                        if (i == 1) {
//                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
//                                BackgroundSetter.setTopRightBackgroundRadius(component, (double) newVal);
//                            });
//                        }
//                        if (i == 2) {
//                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
//                                BackgroundSetter.setBottomRightBackgroundRadius(component, (double) newVal);
//                            });
//                        }
//                        if (i == 3) {
//                            bgRadiusProperties.get(i).addListener((valType, oldVal, newVal) -> {
//                                BackgroundSetter.setBottomLeftBackgroundRadius(component, (double) newVal);
//                            });
//                        }
//                        value = (value / 100) * component.getPrefWidth();
//                    }
//                    if (setPropertiesOnHover) {
//                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), hoverAnimation, radiusAnimationSides.get(i), value, duration);
//                    } else if (setPropertiesOnClick) {
//                        PropertyUtil.setOneDimensionAnimation(currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), clickAnimation, radiusAnimationSides.get(i), value, duration);
//                    } else {
//                        if (duration == 1) {
//                            if (i == 0) {
//                                BackgroundSetter.setTopLeftBackgroundRadius(component, value);
//                            }
//                            if (i == 1) {
//                                BackgroundSetter.setTopRightBackgroundRadius(component, value);
//                            }
//                            if (i == 2) {
//                                BackgroundSetter.setBottomRightBackgroundRadius(component, value);
//                            }
//                            if (i == 3) {
//                                BackgroundSetter.setBottomLeftBackgroundRadius(component, value);
//                            }
//                        } else {
//                            PropertyUtil.setTempAnimation(component, currRadiusValues.get(i), initialValues, bgRadiusInitialValues.get(i), radiusAnimationSides.get(i), value, duration);
//                        }
//                    }
//                }
//            }
//        }
//    }

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
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawTopLeftBgRadius = rawValue;
            double initRadius = 0;
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
            if (radii != null) {
                initRadius = radii.getTopLeftHorizontalRadius();
            }
            double value = parser.getDoubleValue(0);
            if (parser.getUnit(0).equals(FUnit.PERCENTAGE)) {
                tlBgRadiusProperty.bind(component.prefWidthProperty().multiply(parser.getDoubleValue(0) / 100));
                tlBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setTopLeftBackgroundRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(initRadius, initialValues, MainProps.BG_TL_RADIUS, hoverAnimation, FAnimations.BG_TL_RADIUS, value, parser.getDuration(0));
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(initRadius, initialValues, MainProps.BG_TL_RADIUS, clickAnimation, FAnimations.BG_TL_RADIUS, value, parser.getDuration(0));
            } else {
                if (parser.getDuration(0) == 0) {
                    BackgroundSetter.setTopLeftBackgroundRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, initRadius, initialValues, MainProps.BG_TL_RADIUS, FAnimations.BG_TL_RADIUS, value, parser.getDuration(0));
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
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawTopRightBgRadius = rawValue;
            double initRadius = 0;
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
            if (radii != null) {
                initRadius = radii.getTopRightHorizontalRadius();
            }
            double value = parser.getDoubleValue(0);
            if (parser.getUnit(0).equals(FUnit.PERCENTAGE)) {
                trBgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                trBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setTopRightBackgroundRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(initRadius, initialValues, MainProps.BG_TR_RADIUS, hoverAnimation, FAnimations.BG_TR_RADIUS, value, parser.getDuration(0));
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(initRadius, initialValues, MainProps.BG_TR_RADIUS, clickAnimation, FAnimations.BG_TR_RADIUS, value, parser.getDuration(0));
            } else {
                if (parser.getDuration(0) == 0) {
                    BackgroundSetter.setTopRightBackgroundRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, initRadius, initialValues, MainProps.BG_TR_RADIUS, FAnimations.BG_TR_RADIUS, value, parser.getDuration(0));
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
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawBottomRightBgRadius = rawValue;
            double initRadius = 0;
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
            if (radii != null) {
                initRadius = radii.getBottomRightHorizontalRadius();
            }
            double value = parser.getDoubleValue(0);
            if (parser.getUnit(0).equals(FUnit.PERCENTAGE)) {
                brBgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                brBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setBottomRightBackgroundRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(initRadius, initialValues, MainProps.BG_BR_RADIUS, hoverAnimation, FAnimations.BG_BR_RADIUS, value, parser.getDuration(0));
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(initRadius, initialValues, MainProps.BG_BR_RADIUS, clickAnimation, FAnimations.BG_BR_RADIUS, value, parser.getDuration(0));
            } else {
                if (parser.getDuration(0) == 0) {
                    BackgroundSetter.setBottomRightBackgroundRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, initRadius, initialValues, MainProps.BG_BR_RADIUS, FAnimations.BG_BR_RADIUS, value, parser.getDuration(0));
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
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid() && parser.getParametersCount() == 1) {
            this.rawBottomLeftBgRadius = rawValue;
            double initRadius = 0;
            CornerRadii radii = BackgroundSetter.getBackgroundRadius(component);
            if (radii != null) {
                initRadius = radii.getBottomLeftHorizontalRadius();
            }
            double value = parser.getDoubleValue(0);
            if (parser.getUnit(0).equals(FUnit.PERCENTAGE)) {
                blBgRadiusProperty.bind(component.prefWidthProperty().multiply(value / 100));
                blBgRadiusProperty.addListener((valType, oldVal, newVal) -> {
                    BackgroundSetter.setBottomLeftBackgroundRadius(component, (double) newVal);
                });
                value = (value / 100) * component.getPrefWidth();
            }
            if (setPropertiesOnHover) {
                PropertyUtil.setOneDimensionAnimation(initRadius, initialValues, MainProps.BG_BL_RADIUS, hoverAnimation, FAnimations.BG_BL_RADIUS, value, parser.getDuration(0));
            } else if (setPropertiesOnClick) {
                PropertyUtil.setOneDimensionAnimation(initRadius, initialValues, MainProps.BG_BL_RADIUS, clickAnimation, FAnimations.BG_BL_RADIUS, value, parser.getDuration(0));
            } else {
                if (parser.getDuration(0) == 0) {
                    BackgroundSetter.setBottomLeftBackgroundRadius(component, value);
                } else {
                    PropertyUtil.setTempAnimation(component, initRadius, initialValues, MainProps.BG_BL_RADIUS, FAnimations.BG_BL_RADIUS, value, parser.getDuration(0));
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
        parser.parse(PropertyParser.SIZE_PROPERTY, rawValue);
        if (parser.isValid()) {
            this.rawBgInsets = rawValue;
            parser.spreadDoubleValues();
            BackgroundSetter.setInsets(component, parser.getDoubleValue(0), parser.getDoubleValue(1), parser.getDoubleValue(2), parser.getDoubleValue(3));
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
    public void notifyParentWasSet(Region parent){
        this.parent = parent;
        pendingTaskManager.setParent(parent);
        pendingTaskManager.executeAllTasks();
        pendingTaskManager.removeAllTasks();
    }
}
