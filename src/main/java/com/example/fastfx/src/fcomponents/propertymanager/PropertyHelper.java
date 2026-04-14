package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.fcomponents.propertymanager.constants.FUnit;
import com.example.fastfx.src.fcomponents.propertymanager.constants.ListenerID;
import com.example.fastfx.src.fcomponents.propertymanager.constants.MainProps;
import com.example.fastfx.src.fcomponents.propertymanager.pendingtask.PendingTaskManager;
import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.FAnimations;
import com.example.fastfx.src.style.animations.StaticAnimation;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

/**
 * Helps manage the animations and makes the PropertyManager class cleaner.
 */
public class PropertyHelper {
    public static final String NUMBER_ANIMATION = "number";
    public static final String COLOR_ANIMATION = "color";

    private Region component;
    private ValuesManager valuesManager;
    private PropertyParser parser;
    private ListenersManager listenersManager;
    private PendingTaskManager pendingTaskManager;
    private FAnimation clickAnimation;
    private FAnimation hoverAnimation;
    private StaticAnimation staticClickAnimation;
    private StaticAnimation staticHoverAnimation;
    private FAnimation fAnimation;
    private Map<String, FAnimation> tempAnimations;
    private Map<String, Boolean> tempAnimationHasBeenStopped;
    private boolean paddingAnimationsStopped = false;
    private boolean bgRadiusAnimationsStopped = false;
    private StaticAnimation staticAnimation;
    private String animationType;
    private String staticAnimationType;
    private String property;
    private String startColor;
    private String endColor;
    private double startValue;
    private double endValue;
    private long duration;
    private String interpolator;
    private String[] properties;
    private String[] startColors;
    private String[] endColors;
    private double[] startValues;
    private double[] endValues;
    private long[] durations;
    private String[] interpolators;
    private Map<String, String[]> multipleSidesAnimations;
    private Map<String, String[]> multipleSidesProps;

    public PropertyHelper (Region component, PropertyParser parser, ValuesManager valuesManager, ListenersManager listenersManager, PendingTaskManager pendingTaskManager) {
        this.tempAnimations = new HashMap<>();
        this.tempAnimationHasBeenStopped = new HashMap<>();
        this.parser = parser;
        this.valuesManager = valuesManager;
        this.listenersManager = listenersManager;
        this.pendingTaskManager = pendingTaskManager;
        this.component = component;
        initMultipleSidesAnimations();
        initMultipleSidesProps();
    }
    
    public void setComponentClickAnimation(FAnimation animation){
        this.clickAnimation = animation;
    }

    public void setComponentHoverAnimation(FAnimation animation){
        this.clickAnimation = animation;
    }

    public void setComponentStaticClickAnimation(StaticAnimation clickAnimation){
        this.staticClickAnimation = clickAnimation;
    }

    public void setComponentStaticHoverAnimation(StaticAnimation animation){
        this.staticClickAnimation = animation;
    }

    private void initMultipleSidesAnimations(){
        multipleSidesAnimations = new HashMap<>();
        String[] paddings = new String[]{FAnimations.TOP_PADDING, FAnimations.RIGHT_PADDING, FAnimations.BOTTOM_PADDING, FAnimations.LEFT_PADDING};
        multipleSidesAnimations.put(FAnimations.PADDING, paddings);
        String[] bgRadius = new String[]{FAnimations.BG_TL_RADIUS, FAnimations.BG_TR_RADIUS, FAnimations.BG_BR_RADIUS, FAnimations.BG_BL_RADIUS};
        multipleSidesAnimations.put(FAnimations.BG_RADIUS, bgRadius);
        String[] brRadius = new String[]{FAnimations.BR_TL_RADIUS, FAnimations.BR_TR_RADIUS, FAnimations.BR_BR_RADIUS, FAnimations.BR_BL_RADIUS};
        multipleSidesAnimations.put(FAnimations.BR_RADIUS, brRadius);
        String[] brInsets = new String[]{FAnimations.BR_TOP_INSET, FAnimations.BR_RIGHT_INSET, FAnimations.BR_BOTTOM_INSET, FAnimations.BR_LEFT_INSET};
        multipleSidesAnimations.put(FAnimations.BR_INSETS, brInsets);
    }

    private void initMultipleSidesProps(){
        multipleSidesProps = new HashMap<>();
        String[] paddings = new String[]{MainProps.TOP_PADDING, MainProps.RIGHT_PADDING, MainProps.BOTTOM_PADDING, MainProps.LEFT_PADDING};
        multipleSidesProps.put(MainProps.PADDING, paddings);
        String[] bgRadius = new String[]{MainProps.BG_TL_RADIUS, MainProps.BG_TR_RADIUS, MainProps.BG_BR_RADIUS, MainProps.BG_BL_RADIUS};
        multipleSidesProps.put(MainProps.BG_RADIUS, bgRadius);
        String[] brRadius = new String[]{MainProps.BR_TL_RADIUS, MainProps.BR_TR_RADIUS, MainProps.BR_BR_RADIUS, MainProps.BR_BL_RADIUS};
        multipleSidesProps.put(MainProps.BR_RADIUS, brRadius);
        String[] brInsets = new String[]{MainProps.BR_TOP_INSET, MainProps.BR_RIGHT_INSET, MainProps.BR_BOTTOM_INSET, MainProps.BR_LEFT_INSET};
        multipleSidesProps.put(MainProps.BR_INSETS, brInsets);
    }

    /**
     * Sets the FAnimation and the StaticAnimation which will be configured.
     *
     * @param fAnimation the FAnimation animation
     * @param staticAnimation the StaticAnimation animation
     */
    public void setAnimations (FAnimation fAnimation, StaticAnimation staticAnimation) {
        this.fAnimation = fAnimation;
        this.staticAnimation = staticAnimation;
    }

    /**
     * Sets the FAnimation which will be configured.
     *
     * @param fAnimation the FAnimation animation
     */
    public void setAnimation(FAnimation fAnimation){
        this.fAnimation = fAnimation;
    }

    /**
     * Sets the StaticAnimation which will be configured.
     *
     * @param staticAnimation the StaticAnimation animation
     */
    public void setStaticAnimation (StaticAnimation staticAnimation) {
        this.staticAnimation = staticAnimation;
    }

    /**
     * Sets the animations types for FAnimation and StaticAnimation.<br>
     * (e.g. FAnimations.LAYOUT_X, StaticAnimations.LAYOUT_X)
     *
     * @param fAnimationType the animation type for FAnimation animation
     * @param staticAnimationType the animation type for StaticAnimation animation
     */
    public void setAnimationsTypes (String fAnimationType, String staticAnimationType) {
        this.animationType = fAnimationType;
        this.staticAnimationType = staticAnimationType;
    }

    /**
     * Sets the animation type for FAnimation.<br>
     * (e.g. FAnimations.LAYOUT_X)
     *
     * @param fAnimationType the animation type for FAnimation animation
     */
    public void setAnimationType(String fAnimationType){
        this.animationType = fAnimationType;
    }

    /**
     * Sets the animation type for FAnimation.<br>
     * (e.g. StaticAnimations.LAYOUT_X)
     *
     * @param animationType the animation type for StaticAnimation animation
     */
    public void setStaticAnimationType(String animationType){
        this.staticAnimationType = animationType;
    }

    /**
     * Sets the property which will be initialized or modified in ValuesManager.<br>
     * (e.g. MainProps.X)
     *
     * @param property property which will be used to extract its initial value from <strong>valuesManager</strong>
     *                 and also to be updated with the new value(e.g. MainProps.WIDTH)
     */
    public void setProperty (String property) {
        this.property = property;
    }

    /**
     * Sets the start color and the end color for color based animations.
     *
     * @param startColor the color the animation will start from
     * @param endColor the color the animation will end to
     */
    public void setColors (String startColor, String endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    /**
     * Sets the start value and the end value for number based animations.
     *
     * @param startValue the value the animation will start from
     * @param endValue the value the animation will end to
     */
    public void setValues (double startValue, double endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    /**
     * Sets the duration of the animation in milliseconds.
     *
     * @param duration the duration of the animation in milliseconds
     */
    public void setDuration (long duration) {
        this.duration = duration;
    }

    /**
     * Sets the interpolator for the animation.
     *
     * @param interpolator the interpolator of the animation
     */
    public void setInterpolator (String interpolator) {
        this.interpolator = interpolator;
    }

    public void setProperties(String prop1, String prop2){
        this.properties = new String[]{prop1, prop2};
    }

    public void setProperties(String prop1, String prop2, String prop3, String prop4){
        this.properties = new String[]{prop1, prop2, prop3, prop4};
    }

    public void setStartColors(String color1, String color2){
        this.startColors = new String[]{color1, color2};
    }

    public void setStartColors(String color1, String color2, String color3, String color4){
        this.startColors = new String[]{color1, color2, color3, color4};
    }

    public void setEndColors(String color1, String color2){
        this.endColors = new String[]{color1, color2};
    }

    public void setEndColors(String color1, String color2, String color3, String color4){
        this.endColors = new String[]{color1, color2, color3, color4};
    }

    public void setStartValues(double value1, double value2){
        this.startValues = new double[]{value1, value2};
    }

    public void setStartValues(double value1, double value2, double value3, double value4){
        this.startValues = new double[]{value1, value2, value3, value4};
    }

    public void setEndValues(double value1, double value2){
        this.endValues = new double[]{value1, value2};
    }

    public void setEndValues(double value1, double value2, double value3, double value4){
        this.endValues = new double[]{value1, value2, value3, value4};
    }
    
    public void setDurations(long duration1, long duration2){
        this.durations = new long[]{duration1, duration2};
    }

    public void setDurations(long duration1, long duration2, long duration3, long duration4){
        this.durations = new long[]{duration1, duration2, duration3, duration4};
    }

    public void setInterpolators(String interpolator1, String interpolator2){
        this.interpolators = new String[]{interpolator1, interpolator2};
    }

    public void setInterpolators(String interpolator1, String interpolator2, String interpolator3, String interpolator4){
        this.interpolators = new String[]{interpolator1, interpolator2, interpolator3, interpolator4};
    }
    
    /**
     * Adds a new property to animate to the FAnimation which was set in setAnimations method<br>
     * or setAnimation method.
     *
     * @param type specifies if the animation is a number based animation(layoutX, translateX etc.)
     *             or a color based animation(for animating the background color for example)
     */
    public void addAnimation (String type) {
        if (type.equals(NUMBER_ANIMATION)) {
            startValue = valuesManager.getDouble(property);
        } else if (type.equals(COLOR_ANIMATION)) {
            startColor = valuesManager.getString(property);
        }
        if (type.equals(NUMBER_ANIMATION)) {
            fAnimation.removeAnimation(animationType);
            if (duration == 0) {
                staticAnimation.addAnimation(staticAnimationType, startValue, endValue);
            } else {
                staticAnimation.removeAnimation(staticAnimationType);
                fAnimation.add(animationType, startValue, Duration.ZERO, interpolator);
                fAnimation.add(animationType, endValue, Duration.millis(duration), interpolator);
            }
        } else if (type.equals(COLOR_ANIMATION)) {
            fAnimation.removeAnimation(animationType);
            if (duration == 0) {
                staticAnimation.addAnimation(staticAnimationType, startColor, endColor);
            } else {
                staticAnimation.removeAnimation(staticAnimationType);
                fAnimation.add(animationType, startColor, Duration.ZERO, interpolator);
                fAnimation.add(animationType, endColor, Duration.millis(duration), interpolator);
            }
        }
    }

    /**
     * Creates a temporary animation and plays it right after.
     *
     * @param animationId the animation id
     * @param type specifies if the animation is a number based animation(layoutX, translateX etc.)
     *             or a color based animation(for animating the background color for example)
     */
    public void addTempAnimation (String animationId, String type) {
        if (type.equals(NUMBER_ANIMATION)) {
            startValue = valuesManager.getDouble(property);
        } else if (type.equals(COLOR_ANIMATION)) {
            startColor = valuesManager.getString(property);
        }
        FAnimation tempAnimation = new FAnimation(component);
        tempAnimations.put(animationId, tempAnimation);
        if (type.equals(NUMBER_ANIMATION)) {
            tempAnimation.add(animationType, startValue, Duration.ZERO, interpolator);
            tempAnimation.add(animationType, endValue, Duration.millis(duration), interpolator);
            valuesManager.set(property, endValue);
        } else if (type.equals(COLOR_ANIMATION)) {
            tempAnimation.add(animationType, startColor, Duration.ZERO, interpolator);
            tempAnimation.add(animationType, endColor, Duration.millis(duration), interpolator);
            valuesManager.set(property, endColor);
        }
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
        tempAnimationHasBeenStopped.put(animationId, false);
    }

    public void addMultipleSidesSizeAnimation(String fAnimationType, String prop){
        if(multipleSidesAnimations.containsKey(fAnimationType)){
            String[] animations = multipleSidesAnimations.get(fAnimationType);
            String[] props = multipleSidesProps.get(prop);
            parser.spreadDoubleValues();
            parser.spreadUnits();
            parser.spreadDurations();
            parser.spreadInterpolators();
            double startValue1 = valuesManager.getDouble(props[0]);
            double startValue2 = valuesManager.getDouble(props[1]);
            double startValue3 = valuesManager.getDouble(props[2]);
            double startValue4 = valuesManager.getDouble(props[3]);
            double endValue1 = parser.getDoubleValue(0);
            double endValue2 = parser.getDoubleValue(1);
            double endValue3 = parser.getDoubleValue(2);
            double endValue4 = parser.getDoubleValue(3);
            long duration1 = parser.getDuration(0);
            long duration2 = parser.getDuration(1);
            long duration3 = parser.getDuration(2);
            long duration4 = parser.getDuration(3);
            String interpolator1 = parser.getInterpolator(0);
            String interpolator2 = parser.getInterpolator(1);
            String interpolator3 = parser.getInterpolator(2);
            String interpolator4 = parser.getInterpolator(3);
            if(parser.getUnit(0).equals(FUnit.PIXELS)
                    && parser.getUnit(1).equals(FUnit.PIXELS)
                    && parser.getUnit(2).equals(FUnit.PIXELS)
                    && parser.getUnit(3).equals(FUnit.PIXELS)){
                if(duration1 == 0){
                    staticAnimation.addAnimation(animations[0], startValue1, endValue1);
                }
                else{
                    fAnimation.add(animations[0], startValue1, Duration.ZERO, interpolator1);
                    fAnimation.add(animations[0], endValue1, Duration.millis(duration1), interpolator1);
                }
                if(duration2 == 0){
                    staticAnimation.addAnimation(animations[1], startValue2, endValue2);
                }
                else{
                    fAnimation.add(animations[1], startValue2, Duration.ZERO, interpolator2);
                    fAnimation.add(animations[1], endValue2, Duration.millis(duration2), interpolator2);
                }
                if(duration3 == 0){
                    staticAnimation.addAnimation(animations[2], startValue3, endValue3);
                }
                else{
                    fAnimation.add(animations[2], startValue3, Duration.ZERO, interpolator3);
                    fAnimation.add(animations[2], endValue3, Duration.millis(duration3), interpolator3);
                }
                if(duration4 == 0){
                    staticAnimation.addAnimation(animations[3], startValue4, endValue4);
                }
                else{
                    fAnimation.add(animations[3], startValue4, Duration.ZERO, interpolator4);
                    fAnimation.add(animations[3], endValue4, Duration.millis(duration4), interpolator4);
                }
                valuesManager.set(props[0], parser.getDoubleValue(0));
                valuesManager.set(props[1], parser.getDoubleValue(1));
                valuesManager.set(props[2], parser.getDoubleValue(2));
                valuesManager.set(props[3], parser.getDoubleValue(3));
            }
        }
    }

//    public void addPaddingProperty (boolean parentIsNull){
//        int parametersCount = parser.getParametersCount();
//        double startValue1 = valuesManager.getDouble(MainProps.TOP_PADDING);
//        double startValue2 = valuesManager.getDouble(MainProps.RIGHT_PADDING);
//        double startValue3 = valuesManager.getDouble(MainProps.BOTTOM_PADDING);
//        double startValue4 = valuesManager.getDouble(MainProps.LEFT_PADDING);
//        double endValue1 = 0;
//        double endValue2 = 0;
//        double endValue3 = 0;
//        double endValue4 = 0;
//        double doubleValue1 = parser.getDoubleValue(0);
//        double doubleValue2 = parser.getDoubleValue(1);
//        double doubleValue3 = parser.getDoubleValue(2);
//        double doubleValue4 = parser.getDoubleValue(3);
//        String unit1 = parser.getUnit(0);
//        String unit2 = parser.getUnit(1);
//        String unit3 = parser.getUnit(2);
//        String unit4 = parser.getUnit(3);
//        long duration1 = parser.getDuration(0);
//        long duration2 = parser.getDuration(1);
//        long duration3 = parser.getDuration(2);
//        long duration4 = parser.getDuration(3);
//        String interpolator1 = parser.getInterpolator(0);
//        String interpolator2 = parser.getInterpolator(1);
//        String interpolator3 = parser.getInterpolator(2);
//        String interpolator4 = parser.getInterpolator(3);
//        if(!parentIsNull){
//            listenersManager.remove(ListenerID.WIDTH_HORIZONTAL_PADDING);
//            listenersManager.remove(ListenerID.HEIGHT_VERTICAL_PADDING);
//            listenersManager.remove(ListenerID.HEIGHT_TOP_PADDING);
//            listenersManager.remove(ListenerID.WIDTH_RIGHT_PADDING);
//            listenersManager.remove(ListenerID.HEIGHT_BOTTOM_PADDING);
//            listenersManager.remove(ListenerID.WIDTH_LEFT_PADDING);
//            stopTempPaddingAnimations();
//            if(parametersCount == 1){
//                duration2 = duration1;
//                duration3 = duration1;
//                duration4 = duration1;
//                interpolator2 = interpolator1;
//                interpolator3 = interpolator1;
//                interpolator4 = interpolator1;
//                if(unit1.equals(FUnit.PERCENTAGE)){
//                    endValue1 = component.getPrefHeight() * (doubleValue1 / 100);
//                    endValue2 = component.getPrefWidth() * (doubleValue1 / 100);
//                    endValue3 = endValue1;
//                    endValue4 = endValue2;
//                    ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setTopBottomPadding(component, (double)newVal * (doubleValue1 / 100));
//                    };
//                    ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setRightLeftPadding(component, (double)newVal * (doubleValue1 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.HEIGHT_VERTICAL_PADDING, MainProps.HEIGHT, heightListener);
//                    listenersManager.add(component, ListenerID.WIDTH_HORIZONTAL_PADDING, MainProps.WIDTH, widthListener);
//                }
//                else if(unit1.equals(FUnit.PIXELS)){
//                    endValue1 = doubleValue1;
//                    endValue2 = endValue1;
//                    endValue3 = endValue1;
//                    endValue4 = endValue1;
//                }
//            }
//            else if(parametersCount == 2){
//                duration3 = duration1;
//                duration4 = duration2;
//                interpolator3 = interpolator1;
//                interpolator4 = interpolator2;
//                if(unit1.equals(FUnit.PERCENTAGE)){
//                    endValue1 = component.getPrefHeight() * (doubleValue1 / 100);
//                    endValue3 = endValue1;
//                    ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setTopBottomPadding(component, (double)newVal * (doubleValue1 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.HEIGHT_VERTICAL_PADDING, MainProps.HEIGHT, heightListener);
//                }
//                else if(unit1.equals(FUnit.PIXELS)){
//                    endValue1 = doubleValue1;
//                    endValue3 = endValue1;
//                }
//                if(unit2.equals(FUnit.PERCENTAGE)){
//                    endValue2 = component.getPrefWidth() * (doubleValue2 / 100);
//                    endValue4 = endValue2;
//                    ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setRightLeftPadding(component, (double)newVal * (doubleValue2 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.WIDTH_HORIZONTAL_PADDING, MainProps.WIDTH, widthListener);
//                }
//                else if(unit1.equals(FUnit.PIXELS)){
//                    endValue2 = doubleValue2;
//                    endValue4 = endValue2;
//                }
//            }
//            else if(parametersCount == 4){
//                if(unit1.equals(FUnit.PERCENTAGE) && unit3.equals(FUnit.PERCENTAGE)){
//                    endValue1 = component.getPrefHeight() * (doubleValue1 / 100);
//                    endValue3 = component.getPrefHeight() * (doubleValue3 / 100);
//                    ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setTopPadding(component, (double)newVal * (doubleValue1 / 100));
//                        PaddingUtil.setBottomPadding(component, (double)newVal * (doubleValue3 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.HEIGHT_VERTICAL_PADDING, MainProps.HEIGHT, heightListener);
//                }
//                if(unit2.equals(FUnit.PERCENTAGE) && unit4.equals(FUnit.PERCENTAGE)){
//                    endValue2 = component.getPrefWidth() * (doubleValue2 / 100);
//                    endValue4 = component.getPrefWidth() * (doubleValue4 / 100);
//                    ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setRightPadding(component, (double)newVal * (doubleValue2 / 100));
//                        PaddingUtil.setLeftPadding(component, (double)newVal * (doubleValue4 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.WIDTH_HORIZONTAL_PADDING, MainProps.WIDTH, widthListener);
//                }
//                if(unit1.equals(FUnit.PERCENTAGE)){
//                    endValue1 = component.getPrefHeight() * (doubleValue1 / 100);
//                    ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setTopPadding(component, (double)newVal * (doubleValue1 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.HEIGHT_TOP_PADDING, MainProps.HEIGHT, heightListener);
//                }
//                else if(unit1.equals(FUnit.PIXELS)){
//                    endValue1 = doubleValue1;
//                }
//                if(unit2.equals(FUnit.PERCENTAGE)){
//                    endValue2 = component.getPrefWidth() * (doubleValue2 / 100);
//                    ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setRightPadding(component, (double)newVal * (doubleValue2 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.WIDTH_RIGHT_PADDING, MainProps.WIDTH, widthListener);
//                }
//                else if(unit2.equals(FUnit.PIXELS)){
//                    endValue2 = doubleValue2;
//                }
//                if(unit3.equals(FUnit.PERCENTAGE)){
//                    endValue3 = component.getPrefHeight() * (doubleValue3 / 100);
//                    ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setBottomPadding(component, (double)newVal * (doubleValue3 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.HEIGHT_BOTTOM_PADDING, MainProps.HEIGHT, heightListener);
//                }
//                else if(unit3.equals(FUnit.PIXELS)){
//                    endValue3 = doubleValue3;
//                }
//                if(unit4.equals(FUnit.PERCENTAGE)){
//                    endValue4 = component.getPrefWidth() * (doubleValue4 / 100);
//                    ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
//                        stopTempPaddingAnimations();
//                        PaddingUtil.setLeftPadding(component, (double)newVal * (doubleValue4 / 100));
//                    };
//                    listenersManager.add(component, ListenerID.WIDTH_LEFT_PADDING, MainProps.WIDTH, widthListener);
//                }
//                else if(unit4.equals(FUnit.PIXELS)){
//                    endValue4 = doubleValue4;
//                }
//            }
//            if(duration1 > 0){
//                FAnimation tempAnimation1 = new FAnimation(component);
//                tempAnimations.put(FAnimations.TOP_PADDING, tempAnimation1);
//                tempAnimation1.add(FAnimations.TOP_PADDING, startValue1, Duration.ZERO, interpolator1);
//                tempAnimation1.add(FAnimations.TOP_PADDING, endValue1, Duration.millis(duration1), interpolator1);
//                valuesManager.set(MainProps.TOP_PADDING, doubleValue1);
//                tempAnimation1.destroyOnFinished();
//                tempAnimation1.play();
//                tempAnimationHasBeenStopped.put(FAnimations.TOP_PADDING, false);
//            }
//            else{
//                PaddingUtil.setTopPadding(component, endValue1);
//            }
//            if(duration2 > 0){
//                FAnimation tempAnimation2 = new FAnimation(component);
//                tempAnimations.put(FAnimations.RIGHT_PADDING, tempAnimation2);
//                tempAnimation2.add(FAnimations.RIGHT_PADDING, startValue2, Duration.ZERO, interpolator2);
//                tempAnimation2.add(FAnimations.RIGHT_PADDING, endValue2, Duration.millis(duration2), interpolator2);
//                valuesManager.set(MainProps.RIGHT_PADDING, doubleValue2);
//                tempAnimation2.destroyOnFinished();
//                tempAnimation2.play();
//                tempAnimationHasBeenStopped.put(FAnimations.RIGHT_PADDING, false);
//            }
//            else{
//                PaddingUtil.setRightPadding(component, endValue2);
//            }
//            if(duration3 > 0){
//                FAnimation tempAnimation3 = new FAnimation(component);
//                tempAnimations.put(FAnimations.BOTTOM_PADDING, tempAnimation3);
//                tempAnimation3.add(FAnimations.BOTTOM_PADDING, startValue3, Duration.ZERO, interpolator3);
//                tempAnimation3.add(FAnimations.BOTTOM_PADDING, endValue3, Duration.millis(duration3), interpolator3);
//                valuesManager.set(MainProps.BOTTOM_PADDING, doubleValue3);
//                tempAnimation3.destroyOnFinished();
//                tempAnimation3.play();
//                tempAnimationHasBeenStopped.put(FAnimations.BOTTOM_PADDING, false);
//            }
//            else{
//                PaddingUtil.setBottomPadding(component, endValue3);
//            }
//            if(duration4 > 0){
//                FAnimation tempAnimation4 = new FAnimation(component);
//                tempAnimations.put(FAnimations.LEFT_PADDING, tempAnimation4);
//                tempAnimation4.add(FAnimations.LEFT_PADDING, startValue4, Duration.ZERO, interpolator4);
//                tempAnimation4.add(FAnimations.LEFT_PADDING, endValue4, Duration.millis(duration4), interpolator4);
//                valuesManager.set(MainProps.LEFT_PADDING, doubleValue4);
//                tempAnimation4.destroyOnFinished();
//                tempAnimation4.play();
//                tempAnimationHasBeenStopped.put(FAnimations.LEFT_PADDING, false);
//            }
//            else{
//                PaddingUtil.setLeftPadding(component, endValue4);
//            }
//            setFourSidesSizeValuesInValuesManager(MainProps.PADDING, endValue1, endValue2, endValue3, endValue4);
//        }
//        else{
//
//        }
//    }

    private int paddingParametersCount;
    private double paddingStartValue1;
    private double paddingStartValue2;
    private double paddingStartValue3;
    private double paddingStartValue4;
    private double paddingEndValue1;
    private double paddingEndValue2;
    private double paddingEndValue3;
    private double paddingEndValue4;
    private double paddingDoubleValue1;
    private double paddingDoubleValue2;
    private double paddingDoubleValue3;
    private double paddingDoubleValue4;
    private String paddingUnit1;
    private String paddingUnit2;
    private String paddingUnit3;
    private String paddingUnit4;
    private long paddingDuration1;
    private long paddingDuration2;
    private long paddingDuration3;
    private long paddingDuration4;
    private String paddingInterpolator1;
    private String paddingInterpolator2;
    private String paddingInterpolator3;
    private String paddingInterpolator4;
    public void addPaddingProperty (boolean parentIsNull){
        paddingParametersCount = parser.getParametersCount();
        paddingStartValue1 = valuesManager.getDouble(MainProps.TOP_PADDING);
        paddingStartValue2 = valuesManager.getDouble(MainProps.RIGHT_PADDING);
        paddingStartValue3 = valuesManager.getDouble(MainProps.BOTTOM_PADDING);
        paddingStartValue4 = valuesManager.getDouble(MainProps.LEFT_PADDING);
        paddingEndValue1 = 0;
        paddingEndValue2 = 0;
        paddingEndValue3 = 0;
        paddingEndValue4 = 0;
        paddingDoubleValue1 = parser.getDoubleValue(0);
        paddingDoubleValue2 = parser.getDoubleValue(1);
        paddingDoubleValue3 = parser.getDoubleValue(2);
        paddingDoubleValue4 = parser.getDoubleValue(3);
        paddingUnit1 = parser.getUnit(0);
        paddingUnit2 = parser.getUnit(1);
        paddingUnit3 = parser.getUnit(2);
        paddingUnit4 = parser.getUnit(3);
        paddingDuration1 = parser.getDuration(0);
        paddingDuration2 = parser.getDuration(1);
        paddingDuration3 = parser.getDuration(2);
        paddingDuration4 = parser.getDuration(3);
        paddingInterpolator1 = parser.getInterpolator(0);
        paddingInterpolator2 = parser.getInterpolator(1);
        paddingInterpolator3 = parser.getInterpolator(2);
        paddingInterpolator4 = parser.getInterpolator(3);
        listenersManager.remove(ListenerID.WIDTH_RIGHT_LEFT_PADDING);
        listenersManager.remove(ListenerID.HEIGHT_TOP_BOTTOM_PADDING);
        listenersManager.remove(ListenerID.HEIGHT_TOP_PADDING);
        listenersManager.remove(ListenerID.WIDTH_RIGHT_PADDING);
        listenersManager.remove(ListenerID.HEIGHT_BOTTOM_PADDING);
        listenersManager.remove(ListenerID.WIDTH_LEFT_PADDING);
        stopTempPaddingAnimations();
        if(!parentIsNull) {
            addPaddingProp();
        }
        else{
            pendingTaskManager.addTask(ListenerID.PADDING, (e) -> {
                addPaddingProp();
            });
        }
    }

    private void addPaddingProp(){
        if(paddingParametersCount == 1){
            paddingDuration2 = paddingDuration1;
            paddingDuration3 = paddingDuration1;
            paddingDuration4 = paddingDuration1;
            paddingInterpolator2 = paddingInterpolator1;
            paddingInterpolator3 = paddingInterpolator1;
            paddingInterpolator4 = paddingInterpolator1;
            if(paddingUnit1.equals(FUnit.PERCENTAGE)){
                paddingEndValue1 = component.getPrefHeight() * (paddingDoubleValue1 / 100);
                paddingEndValue2 = component.getPrefWidth() * (paddingDoubleValue1 / 100);
                paddingEndValue3 = paddingEndValue1;
                paddingEndValue4 = paddingEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setRightLeftPadding(component, (double)newVal * (paddingDoubleValue1 / 100));
                };
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setTopBottomPadding(component, (double)newVal * (paddingDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_RIGHT_LEFT_PADDING, MainProps.WIDTH, widthListener);
                listenersManager.add(component, ListenerID.HEIGHT_TOP_BOTTOM_PADDING, MainProps.HEIGHT, heightListener);
            }
            else if(paddingUnit1.equals(FUnit.PIXELS)){
                paddingEndValue1 = paddingDoubleValue1;
                paddingEndValue2 = paddingEndValue1;
                paddingEndValue3 = paddingEndValue1;
                paddingEndValue4 = paddingEndValue1;
            }
        }
        else if(paddingParametersCount == 2){
            paddingDuration3 = paddingDuration1;
            paddingDuration4 = paddingDuration2;
            paddingInterpolator3 = paddingInterpolator1;
            paddingInterpolator4 = paddingInterpolator2;
            if(paddingUnit1.equals(FUnit.PERCENTAGE)){
                paddingEndValue1 = component.getPrefHeight() * (paddingDoubleValue1 / 100);
                paddingEndValue3 = paddingEndValue1;
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setTopBottomPadding(component, (double)newVal * (paddingDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_TOP_BOTTOM_PADDING, MainProps.HEIGHT, heightListener);
            }
            else if(paddingUnit1.equals(FUnit.PIXELS)){
                paddingEndValue1 = paddingDoubleValue1;
                paddingEndValue3 = paddingEndValue1;
            }
            if(paddingUnit2.equals(FUnit.PERCENTAGE)){
                paddingEndValue2 = component.getPrefWidth() * (paddingDoubleValue2 / 100);
                paddingEndValue4 = paddingEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setRightLeftPadding(component, (double)newVal * (paddingDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_RIGHT_LEFT_PADDING, MainProps.WIDTH, widthListener);
            }
            else if(paddingUnit1.equals(FUnit.PIXELS)){
                paddingEndValue2 = paddingDoubleValue2;
                paddingEndValue4 = paddingEndValue2;
            }
        }
        else if(paddingParametersCount == 4){
            if(paddingUnit1.equals(FUnit.PERCENTAGE) && paddingUnit3.equals(FUnit.PERCENTAGE)){
                paddingEndValue1 = component.getPrefHeight() * (paddingDoubleValue1 / 100);
                paddingEndValue3 = component.getPrefHeight() * (paddingDoubleValue3 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setTopBottomPadding(component, (double)newVal * (paddingDoubleValue1 / 100), (double)newVal * (paddingDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_TOP_BOTTOM_PADDING, MainProps.HEIGHT, heightListener);
            }
            else if(paddingUnit1.equals(FUnit.PERCENTAGE)){
                paddingEndValue1 = component.getPrefHeight() * (paddingDoubleValue1 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setTopPadding(component, (double)newVal * (paddingDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_TOP_PADDING, MainProps.HEIGHT, heightListener);
            }
            else if(paddingUnit3.equals(FUnit.PERCENTAGE)){
                paddingEndValue3 = component.getPrefHeight() * (paddingDoubleValue3 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setBottomPadding(component, (double)newVal * (paddingDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BOTTOM_PADDING, MainProps.HEIGHT, heightListener);
            }
            if(paddingUnit1.equals(FUnit.PIXELS)){
                paddingEndValue1 = paddingDoubleValue1;
            }
            if(paddingUnit3.equals(FUnit.PIXELS)){
                paddingEndValue3 = paddingDoubleValue3;
            }
            if(paddingUnit2.equals(FUnit.PERCENTAGE) && paddingUnit4.equals(FUnit.PERCENTAGE)){
                paddingEndValue2 = component.getPrefWidth() * (paddingDoubleValue2 / 100);
                paddingEndValue4 = component.getPrefWidth() * (paddingDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setRightLeftPadding(component, (double)newVal * (paddingDoubleValue2 / 100), (double)newVal * (paddingDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_RIGHT_LEFT_PADDING, MainProps.WIDTH, widthListener);
            }
            else if(paddingUnit2.equals(FUnit.PERCENTAGE)){
                paddingEndValue2 = component.getPrefWidth() * (paddingDoubleValue2 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setRightPadding(component, (double)newVal * (paddingDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_RIGHT_PADDING, MainProps.WIDTH, widthListener);
            }
            else if(paddingUnit4.equals(FUnit.PERCENTAGE)){
                paddingEndValue4 = component.getPrefWidth() * (paddingDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempPaddingAnimations();
                    PaddingUtil.setLeftPadding(component, (double)newVal * (paddingDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_LEFT_PADDING, MainProps.WIDTH, widthListener);
            }
            if(paddingUnit2.equals(FUnit.PIXELS)){
                paddingEndValue2 = paddingDoubleValue2;
            }
            if(paddingUnit4.equals(FUnit.PIXELS)){
                paddingEndValue4 = paddingDoubleValue4;
            }
        }
        if(paddingDuration1 > 0){
            addTempAnimation(component, FAnimations.TOP_PADDING, paddingStartValue1, paddingEndValue1, paddingDoubleValue1, paddingDuration1, paddingInterpolator1);
        }
        else{
            PaddingUtil.setTopPadding(component, paddingEndValue1);
        }
        if(paddingDuration2 > 0){
            addTempAnimation(component, FAnimations.RIGHT_PADDING, paddingStartValue2, paddingEndValue2, paddingDoubleValue2, paddingDuration2, paddingInterpolator2);
        }
        else{
            PaddingUtil.setRightPadding(component, paddingEndValue2);
        }
        if(paddingDuration3 > 0){
            addTempAnimation(component, FAnimations.BOTTOM_PADDING, paddingStartValue3, paddingEndValue3, paddingDoubleValue3, paddingDuration3, paddingInterpolator3);
        }
        else{
            PaddingUtil.setBottomPadding(component, paddingEndValue3);
        }
        if(paddingDuration4 > 0){
            addTempAnimation(component, FAnimations.LEFT_PADDING, paddingStartValue4, paddingEndValue4, paddingDoubleValue4, paddingDuration4, paddingInterpolator4);
        }
        else{
            PaddingUtil.setLeftPadding(component, paddingEndValue4);
        }
        setFourSidesSizeValuesInValuesManager(MainProps.PADDING, paddingEndValue1, paddingEndValue2, paddingEndValue3, paddingEndValue4);
        updateFourSidesAnimationStartValue(clickAnimation, FAnimations.PADDING, paddingEndValue1, paddingEndValue2, paddingEndValue3, paddingEndValue4);
        updateFourSidesAnimationStartValue(hoverAnimation, FAnimations.PADDING, paddingEndValue1, paddingEndValue2, paddingEndValue3, paddingEndValue4);
    }

    private int bgRadiusParametersCount;
    private double bgRadiusStartValue1;
    private double bgRadiusStartValue2;
    private double bgRadiusStartValue3;
    private double bgRadiusStartValue4;
    private double bgRadiusEndValue1;
    private double bgRadiusEndValue2;
    private double bgRadiusEndValue3;
    private double bgRadiusEndValue4;
    private double bgRadiusDoubleValue1;
    private double bgRadiusDoubleValue2;
    private double bgRadiusDoubleValue3;
    private double bgRadiusDoubleValue4;
    private String bgRadiusUnit1;
    private String bgRadiusUnit2;
    private String bgRadiusUnit3;
    private String bgRadiusUnit4;
    private long bgRadiusDuration1;
    private long bgRadiusDuration2;
    private long bgRadiusDuration3;
    private long bgRadiusDuration4;
    private String bgRadiusInterpolator1;
    private String bgRadiusInterpolator2;
    private String bgRadiusInterpolator3;
    private String bgRadiusInterpolator4;
    public void addBgRadiusProperty (boolean parentIsNull){
        bgRadiusParametersCount = parser.getParametersCount();
        bgRadiusStartValue1 = valuesManager.getDouble(MainProps.BG_TL_RADIUS);
        bgRadiusStartValue2 = valuesManager.getDouble(MainProps.BG_TR_RADIUS);
        bgRadiusStartValue3 = valuesManager.getDouble(MainProps.BG_BR_RADIUS);
        bgRadiusStartValue4 = valuesManager.getDouble(MainProps.BG_BL_RADIUS);
        bgRadiusEndValue1 = 0;
        bgRadiusEndValue2 = 0;
        bgRadiusEndValue3 = 0;
        bgRadiusEndValue4 = 0;
        bgRadiusDoubleValue1 = parser.getDoubleValue(0);
        bgRadiusDoubleValue2 = parser.getDoubleValue(1);
        bgRadiusDoubleValue3 = parser.getDoubleValue(2);
        bgRadiusDoubleValue4 = parser.getDoubleValue(3);
        bgRadiusUnit1 = parser.getUnit(0);
        bgRadiusUnit2 = parser.getUnit(1);
        bgRadiusUnit3 = parser.getUnit(2);
        bgRadiusUnit4 = parser.getUnit(3);
        bgRadiusDuration1 = parser.getDuration(0);
        bgRadiusDuration2 = parser.getDuration(1);
        bgRadiusDuration3 = parser.getDuration(2);
        bgRadiusDuration4 = parser.getDuration(3);
        bgRadiusInterpolator1 = parser.getInterpolator(0);
        bgRadiusInterpolator2 = parser.getInterpolator(1);
        bgRadiusInterpolator3 = parser.getInterpolator(2);
        bgRadiusInterpolator4 = parser.getInterpolator(3);
        listenersManager.remove(ListenerID.WIDTH_BG_TL_BR_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BG_TR_BL_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BG_TL_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BG_TR_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BG_BR_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BG_BL_RADIUS);
        stopTempBgRadiusAnimations();
        if(!parentIsNull){
            addBgRadiusProp();
        }
        else{
            pendingTaskManager.addTask(ListenerID.WIDTH_BG_RADIUS, (parent) -> {
                addBgRadiusProp();
            });
        }
    }

    private void addBgRadiusProp(){
        if(bgRadiusParametersCount == 1){
            bgRadiusDuration2 = bgRadiusDuration1;
            bgRadiusDuration3 = bgRadiusDuration1;
            bgRadiusDuration4 = bgRadiusDuration1;
            bgRadiusInterpolator2 = bgRadiusInterpolator1;
            bgRadiusInterpolator3 = bgRadiusInterpolator1;
            bgRadiusInterpolator4 = bgRadiusInterpolator1;
            if(bgRadiusUnit1.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue1 = component.getPrefWidth() * (bgRadiusDoubleValue1 / 100);
                bgRadiusEndValue2 = bgRadiusEndValue1;
                bgRadiusEndValue3 = bgRadiusEndValue1;
                bgRadiusEndValue4 = bgRadiusEndValue1;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(bgRadiusUnit1.equals(FUnit.PIXELS)){
                bgRadiusEndValue1 = bgRadiusDoubleValue1;
                bgRadiusEndValue2 = bgRadiusEndValue1;
                bgRadiusEndValue3 = bgRadiusEndValue1;
                bgRadiusEndValue4 = bgRadiusEndValue1;
            }
        }
        else if(bgRadiusParametersCount == 2){
            bgRadiusDuration3 = bgRadiusDuration1;
            bgRadiusDuration4 = bgRadiusDuration2;
            bgRadiusInterpolator3 = bgRadiusInterpolator1;
            bgRadiusInterpolator4 = bgRadiusInterpolator2;
            if(bgRadiusUnit1.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue1 = component.getPrefWidth() * (bgRadiusDoubleValue1 / 100);
                bgRadiusEndValue3 = bgRadiusEndValue1;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setTopLeftBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue1 / 100));
                    BackgroundSetter.setBottomRightBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_TL_BR_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(bgRadiusUnit1.equals(FUnit.PIXELS)){
                bgRadiusEndValue1 = bgRadiusDoubleValue1;
                bgRadiusEndValue3 = bgRadiusEndValue1;
            }
            if(bgRadiusUnit2.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue2 = component.getPrefWidth() * (bgRadiusDoubleValue2 / 100);
                bgRadiusEndValue4 = bgRadiusEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setTopRightBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue2 / 100));
                    BackgroundSetter.setBottomLeftBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_TR_BL_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(bgRadiusUnit1.equals(FUnit.PIXELS)){
                bgRadiusEndValue2 = bgRadiusDoubleValue2;
                bgRadiusEndValue4 = bgRadiusEndValue2;
            }
        }
        else if(bgRadiusParametersCount == 4){
            if(bgRadiusUnit1.equals(FUnit.PERCENTAGE) && bgRadiusUnit3.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue1 = component.getPrefWidth() * (bgRadiusDoubleValue1 / 100);
                bgRadiusEndValue3 = component.getPrefWidth() * (bgRadiusDoubleValue3 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setTopLeftBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue1 / 100));
                    BackgroundSetter.setBottomRightBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_TL_BR_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(bgRadiusUnit1.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue1 = component.getPrefWidth() * (bgRadiusDoubleValue1 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setTopLeftBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_TL_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(bgRadiusUnit3.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue3 = component.getPrefWidth() * (bgRadiusDoubleValue3 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setBottomRightBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_BR_RADIUS, MainProps.WIDTH, widthListener);
            }
            if(bgRadiusUnit1.equals(FUnit.PIXELS)){
                bgRadiusEndValue1 = bgRadiusDoubleValue1;
            }
            if(bgRadiusUnit3.equals(FUnit.PIXELS)){
                bgRadiusEndValue3 = bgRadiusDoubleValue3;
            }
            if(bgRadiusUnit2.equals(FUnit.PERCENTAGE) && bgRadiusUnit4.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue2 = component.getPrefWidth() * (bgRadiusDoubleValue2 / 100);
                bgRadiusEndValue4 = component.getPrefWidth() * (bgRadiusDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setTopRightBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue2 / 100));
                    BackgroundSetter.setBottomLeftBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_TR_BL_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(bgRadiusUnit2.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue2 = component.getPrefWidth() * (bgRadiusDoubleValue2 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setTopRightBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_TR_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(bgRadiusUnit4.equals(FUnit.PERCENTAGE)){
                bgRadiusEndValue4 = component.getPrefWidth() * (bgRadiusDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgRadiusAnimations();
                    BackgroundSetter.setBottomLeftBackgroundRadius(component, (double)newVal * (bgRadiusDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_BL_RADIUS, MainProps.WIDTH, widthListener);
            }
            if(bgRadiusUnit2.equals(FUnit.PIXELS)){
                bgRadiusEndValue2 = bgRadiusDoubleValue2;
            }
            if(bgRadiusUnit4.equals(FUnit.PIXELS)){
                bgRadiusEndValue4 = bgRadiusDoubleValue4;
            }
        }
        if(bgRadiusDuration1 > 0){
            addTempAnimation(component, FAnimations.BG_TL_RADIUS, bgRadiusStartValue1, bgRadiusEndValue1, bgRadiusDoubleValue1, bgRadiusDuration1, bgRadiusInterpolator1);
        }
        else{
            BackgroundSetter.setTopLeftBackgroundRadius(component, bgRadiusEndValue1);
        }
        if(bgRadiusDuration2 > 0){
            addTempAnimation(component, FAnimations.BG_TR_RADIUS, bgRadiusStartValue2, bgRadiusEndValue2, bgRadiusDoubleValue2, bgRadiusDuration2, bgRadiusInterpolator2);
        }
        else{
            BackgroundSetter.setTopRightBackgroundRadius(component, bgRadiusEndValue2);
        }
        if(bgRadiusDuration3 > 0){
            addTempAnimation(component, FAnimations.BG_BR_RADIUS, bgRadiusStartValue3, bgRadiusEndValue3, bgRadiusDoubleValue3, bgRadiusDuration3, bgRadiusInterpolator3);
        }
        else{
            BackgroundSetter.setBottomRightBackgroundRadius(component, bgRadiusEndValue3);
        }
        if(bgRadiusDuration4 > 0){
            addTempAnimation(component, FAnimations.BG_BL_RADIUS, bgRadiusStartValue4, bgRadiusEndValue4, bgRadiusDoubleValue4, bgRadiusDuration4, bgRadiusInterpolator4);
        }
        else{
            BackgroundSetter.setBottomLeftBackgroundRadius(component, bgRadiusEndValue4);
        }
        System.out.println("bgRadiusEndValue1: " + bgRadiusEndValue1);
        System.out.println("bgRadiusEndValue2: " + bgRadiusEndValue2);
        System.out.println("bgRadiusEndValue3: " + bgRadiusEndValue3);
        System.out.println("bgRadiusEndValue4: " + bgRadiusEndValue4);
        setFourSidesSizeValuesInValuesManager(MainProps.BG_RADIUS, bgRadiusEndValue1, bgRadiusEndValue2, bgRadiusEndValue3, bgRadiusEndValue4);
        updateFourSidesAnimationStartValue(clickAnimation, FAnimations.BG_RADIUS, bgRadiusEndValue1, bgRadiusEndValue2, bgRadiusEndValue3, bgRadiusEndValue4);
        updateFourSidesAnimationStartValue(hoverAnimation, FAnimations.BG_RADIUS, bgRadiusEndValue1, bgRadiusEndValue2, bgRadiusEndValue3, bgRadiusEndValue4);
    }

    private void updateFourSidesAnimationStartValue(FAnimation animation, String animationType, double val1, double val2, double val3, double val4){
        if(multipleSidesAnimations.containsKey(animationType)){
            System.out.println("Update anim: " + animationType);
            System.out.println("Anim 1: " + multipleSidesAnimations.get(animationType)[0]);
            System.out.println("Anim 2: " + multipleSidesAnimations.get(animationType)[1]);
            System.out.println("Anim 3: " + multipleSidesAnimations.get(animationType)[2]);
            System.out.println("Anim 4: " + multipleSidesAnimations.get(animationType)[3]);
            updateAnimationStartValue(animation, multipleSidesAnimations.get(animationType)[0], val1);
            updateAnimationStartValue(animation, multipleSidesAnimations.get(animationType)[1], val2);
            updateAnimationStartValue(animation, multipleSidesAnimations.get(animationType)[2], val3);
            updateAnimationStartValue(animation, multipleSidesAnimations.get(animationType)[3], val4);
        }
    }

    private void addTempAnimation(Region component, String animationType, double startVal, double endVal, double doubleValue, long duration, String interpolator){
        FAnimation tempAnimation = new FAnimation(component);
        tempAnimations.put(animationType, tempAnimation);
        tempAnimation.add(animationType, startVal, Duration.ZERO, interpolator);
        tempAnimation.add(animationType, endVal, Duration.millis(duration), interpolator);
        valuesManager.set(animationType, doubleValue);
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
        tempAnimationHasBeenStopped.put(animationType, false);
    }

    private void setFourSidesSizeValuesInValuesManager (String mainProp, double value1, double value2, double value3, double value4){
        String[] props = multipleSidesProps.get(mainProp);
        valuesManager.set(props[0], value1);
        valuesManager.set(props[1], value2);
        valuesManager.set(props[2], value3);
        valuesManager.set(props[3], value4);
    }

    /**
     * Stops the temporary animation. It's used when setting the same property again(e.g. width)<br>
     * or when the property was initially set in percentage and was also animated and the listener<br>
     * from the parent was notified.<br>
     * e.g. x("30% 1s")<br>
     * The layoutX of the component will be set to 30% of the parent width with a 1-second transition.<br>
     * It will be stopped when x method will be called again before the animation ended or when the<br>
     * parent width is changed hence the component layoutX will be changed accordingly and the animation<br>
     * will be stopped.
     *
     * @param animationId the animation id
     */
    public void stopTempAnimation(String animationId){
        FAnimation tempAnimation = tempAnimations.get(animationId);
        if(tempAnimation != null){
            if(tempAnimationHasBeenStopped.containsKey(animationId) && !tempAnimationHasBeenStopped.get(animationId)){
                tempAnimation.stop();
                tempAnimationHasBeenStopped.put(animationId, true);
                tempAnimations.remove(animationId);
            }
        }
    }

    private void stopTempPaddingAnimations(){
        if(!paddingAnimationsStopped){
            stopTempAnimation(FAnimations.TOP_PADDING);
            stopTempAnimation(FAnimations.RIGHT_PADDING);
            stopTempAnimation(FAnimations.BOTTOM_PADDING);
            stopTempAnimation(FAnimations.LEFT_PADDING);
            paddingAnimationsStopped = true;
        }
    }

    private void stopTempBgRadiusAnimations(){
        if(!bgRadiusAnimationsStopped){
            stopTempAnimation(FAnimations.BG_TL_RADIUS);
            stopTempAnimation(FAnimations.BG_TR_RADIUS);
            stopTempAnimation(FAnimations.BG_BR_RADIUS);
            stopTempAnimation(FAnimations.BG_BL_RADIUS);
            bgRadiusAnimationsStopped = true;
        }
    }

    /**
     * Adds a new property to animate to the StaticAnimation which was set in setAnimations method<br>
     * or setStaticAnimation method.
     *
     * @param type specifies if the animation is a number based animation(layoutX, translateX etc.)
     *             or a color based animation(for animating the background color for example)
     */
    public void addStaticAnimation (String type) {
        if(fAnimation != null){
            fAnimation.removeAnimation(animationType);
        }
        if (valuesManager.contains(property)) {
            if (type.equals(NUMBER_ANIMATION)) {
                staticAnimation.addAnimation(staticAnimationType, valuesManager.getDouble(property), endValue);
            } else if (type.equals(COLOR_ANIMATION)) {
                staticAnimation.addAnimation(staticAnimationType, valuesManager.getString(property), endColor);
            }
        }
    }

    /**
     * Updates an existing FAnimation start value.
     *
     * @param fAnimation the <strong>FAnimation</strong> to be updated
     * @param animation the animation type the value will be updated for(e.g. FAnimations.LAYOUT_X)
     * @param newStartValue the new value the animation will start from
     */
    public void updateAnimationStartValue (String fAnimation, FAnimation animation, double newStartValue) {
        if(animation != null){
            animation.updateStartValue(fAnimation, newStartValue);
        }
    }

    public void removeMultipleSidesAnimation(String fAnimationType){
        if(fAnimation != null && multipleSidesAnimations.containsKey(fAnimationType)){
            String[] animations = multipleSidesAnimations.get(fAnimationType);
            fAnimation.removeAnimation(fAnimationType);
            fAnimation.removeAnimation(animations[0]);
            fAnimation.removeAnimation(animations[1]);
            fAnimation.removeAnimation(animations[2]);
            fAnimation.removeAnimation(animations[3]);
        }
    }

    public void removeMultipleSidesStaticAnimation(String staticAnimationType){
        if(staticAnimation != null && multipleSidesAnimations.containsKey(staticAnimationType)){
            String[] animations = multipleSidesAnimations.get(staticAnimationType);
            staticAnimation.removeAnimation(staticAnimationType);
            staticAnimation.removeAnimation(animations[0]);
            staticAnimation.removeAnimation(animations[1]);
            staticAnimation.removeAnimation(animations[2]);
            staticAnimation.removeAnimation(animations[3]);
        }
    }

    public void updateAnimationStartColor(FAnimation fAnimation, String animationType, String color){
        if(fAnimation != null){
            fAnimation.updateStartColor(animationType, color);
        }
    }

    public void updateAnimationEndColor(FAnimation fAnimation, String animationType, String color){
        if(fAnimation != null){
            fAnimation.updateEndColor(animationType, color);
        }
    }

    public void updateAnimationStartValue(FAnimation fAnimation, String animationType, double value){
        if(fAnimation != null){
            fAnimation.updateStartValue(animationType, value);
        }
    }

    public void updateAnimationEndValue(FAnimation fAnimation, String animationType, double value){
        if(fAnimation != null){
            fAnimation.updateEndValue(animationType, value);
        }
    }
}
