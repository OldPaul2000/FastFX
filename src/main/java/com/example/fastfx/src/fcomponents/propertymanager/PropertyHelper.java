package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.fcomponents.propertymanager.constants.FUnit;
import com.example.fastfx.src.fcomponents.propertymanager.constants.ListenerID;
import com.example.fastfx.src.fcomponents.propertymanager.constants.MainProps;
import com.example.fastfx.src.fcomponents.propertymanager.pendingtask.PendingTaskManager;
import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.FAnimations;
import com.example.fastfx.src.style.animations.StaticAnimation;
import com.example.fastfx.src.style.animations.StaticAnimations;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
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
    private boolean bgInsetsAnimationsStopped = false;
    private boolean brColorAnimationsStopped = false;
    private boolean brOpacityAnimationsStopped = false;
    private boolean brRadiusAnimationsStopped = false;
    private boolean brWidthAnimationsStopped = false;
    private boolean brInsetsAnimationsStopped = false;
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
        this.hoverAnimation = animation;
    }

    public void setComponentStaticClickAnimation(StaticAnimation clickAnimation){
        this.staticClickAnimation = clickAnimation;
    }

    public void setComponentStaticHoverAnimation(StaticAnimation animation){
        this.staticHoverAnimation = animation;
    }

    private void initMultipleSidesAnimations(){
        multipleSidesAnimations = new HashMap<>();
        String[] paddings = new String[]{FAnimations.TOP_PADDING, FAnimations.RIGHT_PADDING, FAnimations.BOTTOM_PADDING, FAnimations.LEFT_PADDING};
        multipleSidesAnimations.put(FAnimations.PADDING, paddings);
        String[] bgRadius = new String[]{FAnimations.BG_TL_RADIUS, FAnimations.BG_TR_RADIUS, FAnimations.BG_BR_RADIUS, FAnimations.BG_BL_RADIUS};
        multipleSidesAnimations.put(FAnimations.BG_RADIUS, bgRadius);
        String[] bgInsets = new String[]{FAnimations.BG_TOP_INSET, FAnimations.BG_RIGHT_INSET, FAnimations.BG_BOTTOM_INSET, FAnimations.BG_LEFT_INSET};
        multipleSidesAnimations.put(FAnimations.BG_INSETS, bgInsets);
        String[] brColors = new String[]{FAnimations.BR_TOP_COLOR, FAnimations.BR_RIGHT_COLOR, FAnimations.BR_BOTTOM_COLOR, FAnimations.BR_LEFT_COLOR};
        multipleSidesAnimations.put(FAnimations.BR_COLOR, brColors);
        String[] brOpacities = new String[]{FAnimations.BR_TOP_OPACITY, FAnimations.BR_RIGHT_OPACITY, FAnimations.BR_BOTTOM_OPACITY, FAnimations.BR_LEFT_OPACITY};
        multipleSidesAnimations.put(FAnimations.BR_OPACITY, brOpacities);
        String[] brRadius = new String[]{FAnimations.BR_TL_RADIUS, FAnimations.BR_TR_RADIUS, FAnimations.BR_BR_RADIUS, FAnimations.BR_BL_RADIUS};
        multipleSidesAnimations.put(FAnimations.BR_RADIUS, brRadius);
        String[] brWidths = new String[]{FAnimations.BR_TOP_WIDTH, FAnimations.BR_RIGHT_WIDTH, FAnimations.BR_BOTTOM_WIDTH, FAnimations.BR_LEFT_WIDTH};
        multipleSidesAnimations.put(FAnimations.BR_WIDTH, brWidths);
        String[] brInsets = new String[]{FAnimations.BR_TOP_INSET, FAnimations.BR_RIGHT_INSET, FAnimations.BR_BOTTOM_INSET, FAnimations.BR_LEFT_INSET};
        multipleSidesAnimations.put(FAnimations.BR_INSETS, brInsets);
    }

    private void initMultipleSidesProps(){
        multipleSidesProps = new HashMap<>();
        String[] paddings = new String[]{MainProps.TOP_PADDING, MainProps.RIGHT_PADDING, MainProps.BOTTOM_PADDING, MainProps.LEFT_PADDING};
        multipleSidesProps.put(MainProps.PADDING, paddings);
        String[] bgRadius = new String[]{MainProps.BG_TL_RADIUS, MainProps.BG_TR_RADIUS, MainProps.BG_BR_RADIUS, MainProps.BG_BL_RADIUS};
        multipleSidesProps.put(MainProps.BG_RADIUS, bgRadius);
        String[] bgInsets = new String[]{MainProps.BG_TOP_INSET, MainProps.BG_RIGHT_INSET, MainProps.BG_BOTTOM_INSET, MainProps.BG_LEFT_INSET};
        multipleSidesProps.put(MainProps.BG_INSETS, bgInsets);
        String[] brColors = new String[]{MainProps.BR_TOP_COLOR, MainProps.BR_RIGHT_COLOR, MainProps.BR_BOTTOM_COLOR, MainProps.BR_LEFT_COLOR};
        multipleSidesProps.put(MainProps.BR_COLOR, brColors);
        String[] brOpacities = new String[]{MainProps.BR_TOP_OPACITY, MainProps.BR_RIGHT_OPACITY, MainProps.BR_BOTTOM_OPACITY, MainProps.BR_LEFT_OPACITY};
        multipleSidesProps.put(MainProps.BR_OPACITY, brOpacities);
        String[] brRadius = new String[]{MainProps.BR_TL_RADIUS, MainProps.BR_TR_RADIUS, MainProps.BR_BR_RADIUS, MainProps.BR_BL_RADIUS};
        multipleSidesProps.put(MainProps.BR_RADIUS, brRadius);
        String[] brWidths = new String[]{MainProps.BR_TOP_WIDTH, MainProps.BR_RIGHT_WIDTH, MainProps.BR_BOTTOM_WIDTH, MainProps.BR_LEFT_WIDTH};
        multipleSidesProps.put(MainProps.BR_WIDTH, brWidths);
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
        if(multipleSidesAnimations.containsKey(fAnimationType) && multipleSidesProps.containsKey(prop)){
            String[] animations = multipleSidesAnimations.get(fAnimationType);
            String[] props = multipleSidesProps.get(prop);
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
                updateFourSidesSizeValuesInValuesManager(prop, parser.getDoubleValue(0), parser.getDoubleValue(1), parser.getDoubleValue(2), parser.getDoubleValue(3));
            }
        }
    }
    
    public void addBorderColorAnimation(boolean setColorWhenSettingBorder){
        String startColor1 = valuesManager.getString(MainProps.BR_TOP_COLOR);
        String startColor2 = valuesManager.getString(MainProps.BR_RIGHT_COLOR);
        String startColor3 = valuesManager.getString(MainProps.BR_BOTTOM_COLOR);
        String startColor4 = valuesManager.getString(MainProps.BR_LEFT_COLOR);
        String endColor1 = parser.getColorValue(0);
        String endColor2 = parser.getColorValue(1);
        String endColor3 = parser.getColorValue(2);
        String endColor4 = parser.getColorValue(3);
        long duration1;
        long duration2;
        long duration3;
        long duration4;
        String interpolator1;
        String interpolator2;
        String interpolator3;
        String interpolator4;
        if(setColorWhenSettingBorder){
             duration1 = parser.getBorderColorDuration();
             duration2 = parser.getBorderColorDuration();
             duration3 = parser.getBorderColorDuration();
             duration4 = parser.getBorderColorDuration();
             interpolator1 = parser.getBorderColorInterpolator();
             interpolator2 = parser.getBorderColorInterpolator();
             interpolator3 = parser.getBorderColorInterpolator();
             interpolator4 = parser.getBorderColorInterpolator();
        }
        else{
             duration1 = parser.getDuration(0);
             duration2 = parser.getDuration(1);
             duration3 = parser.getDuration(2);
             duration4 = parser.getDuration(3);
             interpolator1 = parser.getInterpolator(0);
             interpolator2 = parser.getInterpolator(1);
             interpolator3 = parser.getInterpolator(2);
             interpolator4 = parser.getInterpolator(3);
        }
        if(duration1 == 0){
            staticAnimation.addAnimation(StaticAnimations.BR_TOP_COLOR, startColor1, endColor1);
        }
        else{
            fAnimation.add(FAnimations.BR_TOP_COLOR, startColor1, Duration.ZERO, interpolator1);
            fAnimation.add(FAnimations.BR_TOP_COLOR, endColor1, Duration.millis(duration1), interpolator1);
        }
        if(duration2 == 0){
            staticAnimation.addAnimation(StaticAnimations.BR_RIGHT_COLOR, startColor2, endColor2);
        }
        else{
            fAnimation.add(FAnimations.BR_RIGHT_COLOR, startColor2, Duration.ZERO, interpolator2);
            fAnimation.add(FAnimations.BR_RIGHT_COLOR, endColor2, Duration.millis(duration2), interpolator2);
        }
        if(duration3 == 0){
            staticAnimation.addAnimation(StaticAnimations.BR_BOTTOM_COLOR, startColor3, endColor3);
        }
        else{
            fAnimation.add(FAnimations.BR_BOTTOM_COLOR, startColor3, Duration.ZERO, interpolator3);
            fAnimation.add(FAnimations.BR_BOTTOM_COLOR, endColor3, Duration.millis(duration3), interpolator3);
        }
        if(duration4 == 0){
            staticAnimation.addAnimation(StaticAnimations.BR_LEFT_COLOR, startColor4, endColor4);
        }
        else{
            fAnimation.add(FAnimations.BR_LEFT_COLOR, startColor4, Duration.ZERO, interpolator4);
            fAnimation.add(FAnimations.BR_LEFT_COLOR, endColor4, Duration.millis(duration4), interpolator4);
        }
        updateFourSidesStringValuesInValuesManager(MainProps.BR_COLOR, brEndColor1, brEndColor2, brEndColor3, brEndColor4);
    }

    public void addBorderOpacityAnimation(boolean setOpacityWhenSettingBorder){
        double startOpacity1 = valuesManager.getDouble(MainProps.BR_TOP_OPACITY);
        double startOpacity2 = valuesManager.getDouble(MainProps.BR_RIGHT_OPACITY);
        double startOpacity3 = valuesManager.getDouble(MainProps.BR_BOTTOM_OPACITY);
        double startOpacity4 = valuesManager.getDouble(MainProps.BR_LEFT_OPACITY);
        double endOpacity1 = parser.getDoubleValue(0);
        double endOpacity2 = parser.getDoubleValue(1);
        double endOpacity3 = parser.getDoubleValue(2);
        double endOpacity4 = parser.getDoubleValue(3);
        long duration1;
        long duration2;
        long duration3;
        long duration4;
        String interpolator1;
        String interpolator2;
        String interpolator3;
        String interpolator4;
        if(setOpacityWhenSettingBorder){
            duration1 = parser.getDuration(0);
            duration2 = parser.getDuration(1);
            duration3 = parser.getDuration(2);
            duration4 = parser.getDuration(3);
            interpolator1 = parser.getInterpolator(0);
            interpolator2 = parser.getInterpolator(1);
            interpolator3 = parser.getInterpolator(2);
            interpolator4 = parser.getInterpolator(3);
        }
        else{
            duration1 = parser.getDuration(0);
            duration2 = parser.getDuration(1);
            duration3 = parser.getDuration(2);
            duration4 = parser.getDuration(3);
            interpolator1 = parser.getInterpolator(0);
            interpolator2 = parser.getInterpolator(1);
            interpolator3 = parser.getInterpolator(2);
            interpolator4 = parser.getInterpolator(3);
        }
        if(duration1 == 0){
            staticAnimation.addAnimation(StaticAnimations.BR_TOP_OPACITY, startOpacity1, endOpacity1);
        }
        else{
            fAnimation.add(FAnimations.BR_TOP_OPACITY, startOpacity1, Duration.ZERO, interpolator1);
            fAnimation.add(FAnimations.BR_TOP_OPACITY, endOpacity1, Duration.millis(duration1), interpolator1);
        }
        if(duration2 == 0){
            staticAnimation.addAnimation(StaticAnimations.BR_RIGHT_OPACITY, startOpacity2, endOpacity2);
        }
        else{
            fAnimation.add(FAnimations.BR_RIGHT_OPACITY, startOpacity2, Duration.ZERO, interpolator2);
            fAnimation.add(FAnimations.BR_RIGHT_OPACITY, endOpacity2, Duration.millis(duration2), interpolator2);
        }
        if(duration3 == 0){
            staticAnimation.addAnimation(StaticAnimations.BR_BOTTOM_OPACITY, startOpacity3, endOpacity3);
        }
        else{
            fAnimation.add(FAnimations.BR_BOTTOM_OPACITY, startOpacity3, Duration.ZERO, interpolator3);
            fAnimation.add(FAnimations.BR_BOTTOM_OPACITY, endOpacity3, Duration.millis(duration3), interpolator3);
        }
        if(duration4 == 0){
            staticAnimation.addAnimation(StaticAnimations.BR_LEFT_OPACITY, startOpacity4, endOpacity4);
        }
        else{
            fAnimation.add(FAnimations.BR_LEFT_OPACITY, startOpacity4, Duration.ZERO, interpolator4);
            fAnimation.add(FAnimations.BR_LEFT_OPACITY, endOpacity4, Duration.millis(duration4), interpolator4);
        }
        updateFourSidesSizeValuesInValuesManager(MainProps.BR_OPACITY, brEndOpacity1, brEndOpacity2, brEndOpacity3, brEndOpacity4);
    }
    
    public void addBorderWidthAnimation(boolean setWidthWhenSettingBorder){
        // When this method is called when setting the border then the first 4 double values
        // from parser will be the values for border radius and the other 4 will be the
        // values for border width but when this method is called when setting only the border width
        // explicitly then the first 4 values will be for the border width.
        // This is determined by setting the setWidthWhenSettingBorder parameter to true or false.
        // The indexOffset is used to offset the index by 4.
        int indexOffset = 0;
        if(setWidthWhenSettingBorder) {
            indexOffset = 4;
        }
        double startValue1 = valuesManager.getDouble(MainProps.BR_TOP_WIDTH);
        double startValue2 = valuesManager.getDouble(MainProps.BR_RIGHT_WIDTH);
        double startValue3 = valuesManager.getDouble(MainProps.BR_BOTTOM_WIDTH);
        double startValue4 = valuesManager.getDouble(MainProps.BR_LEFT_WIDTH);
        double endValue1 = parser.getDoubleValue(indexOffset);
        double endValue2 = parser.getDoubleValue(1 + indexOffset);
        double endValue3 = parser.getDoubleValue(2 + indexOffset);
        double endValue4 = parser.getDoubleValue(3 + indexOffset);
        System.out.println("sv1: " + startValue1);
        System.out.println("sv2: " + startValue2);
        System.out.println("sv3: " + startValue3);
        System.out.println("sv4: " + startValue4);
        System.out.println("endv1: " + endValue1);
        System.out.println("endv2: " + endValue2);
        System.out.println("endv3: " + endValue3);
        System.out.println("endv4: " + endValue4);
        String unit1 = parser.getUnit(indexOffset);
        String unit2 = parser.getUnit(1 + indexOffset);
        String unit3 = parser.getUnit(2 + indexOffset);
        String unit4 = parser.getUnit(3 + indexOffset);
        long duration1 = parser.getDuration(indexOffset);
        long duration2 = parser.getDuration(1 + indexOffset);
        long duration3 = parser.getDuration(2 + indexOffset);
        long duration4 = parser.getDuration(3 + indexOffset);
        String interpolator1 = parser.getInterpolator(indexOffset);
        String interpolator2 = parser.getInterpolator(1 + indexOffset);
        String interpolator3 = parser.getInterpolator(2 + indexOffset);
        String interpolator4 = parser.getInterpolator(3 + indexOffset);
        if(unit1.equals(FUnit.PIXELS)
                && unit2.equals(FUnit.PIXELS)
                && unit3.equals(FUnit.PIXELS)
                && unit4.equals(FUnit.PIXELS)){
            if(duration1 == 0){
                staticAnimation.addAnimation(FAnimations.BR_TOP_WIDTH, startValue1, endValue1);
            }
            else{
                fAnimation.add(FAnimations.BR_TOP_WIDTH, startValue1, Duration.ZERO, interpolator1);
                fAnimation.add(FAnimations.BR_TOP_WIDTH, endValue1, Duration.millis(duration1), interpolator1);
            }
            if(duration2 == 0){
                staticAnimation.addAnimation(FAnimations.BR_RIGHT_WIDTH, startValue2, endValue2);
            }
            else{
                fAnimation.add(FAnimations.BR_RIGHT_WIDTH, startValue2, Duration.ZERO, interpolator2);
                fAnimation.add(FAnimations.BR_RIGHT_WIDTH, endValue2, Duration.millis(duration2), interpolator2);
            }
            if(duration3 == 0){
                staticAnimation.addAnimation(FAnimations.BR_BOTTOM_WIDTH, startValue3, endValue3);
            }
            else{
                fAnimation.add(FAnimations.BR_BOTTOM_WIDTH, startValue3, Duration.ZERO, interpolator3);
                fAnimation.add(FAnimations.BR_BOTTOM_WIDTH, endValue3, Duration.millis(duration3), interpolator3);
            }
            if(duration4 == 0){
                staticAnimation.addAnimation(FAnimations.BR_LEFT_WIDTH, startValue4, endValue4);
            }
            else{
                fAnimation.add(FAnimations.BR_LEFT_WIDTH, startValue4, Duration.ZERO, interpolator4);
                fAnimation.add(FAnimations.BR_LEFT_WIDTH, endValue4, Duration.millis(duration4), interpolator4);
            }
            updateFourSidesSizeValuesInValuesManager(MainProps.BR_WIDTH, endValue1, endValue2, endValue3, endValue4);
            updateFourSidesAnimationStartValue(clickAnimation, staticClickAnimation, FAnimations.BR_WIDTH, brWidthEndValue1, brWidthEndValue2, brWidthEndValue3, brWidthEndValue4);
            updateFourSidesAnimationStartValue(hoverAnimation, staticHoverAnimation, FAnimations.BR_WIDTH, brWidthEndValue1, brWidthEndValue2, brWidthEndValue3, brWidthEndValue4);
        }
    }

    public void addOneSideBorderStyleStaticAnimation(String animationType, String startValue, String endValue){
        if(animationType.equals(StaticAnimations.BR_TOP_STYLE)
                || animationType.equals(StaticAnimations.BR_RIGHT_STYLE)
                || animationType.equals(StaticAnimations.BR_BOTTOM_STYLE)
                || animationType.equals(StaticAnimations.BR_LEFT_STYLE)){
            staticAnimation.addAnimation(animationType, startValue, endValue);
        }
    }

    public void addBorderStyleStaticAnimation(){
        String startStyle1 = valuesManager.getString(MainProps.BR_TOP_STYLE);
        String startStyle2 = valuesManager.getString(MainProps.BR_RIGHT_STYLE);
        String startStyle3 = valuesManager.getString(MainProps.BR_BOTTOM_STYLE);
        String startStyle4 = valuesManager.getString(MainProps.BR_LEFT_STYLE);
        String endStyle1 = parser.getBorderStyle(0);
        String endStyle2 = parser.getBorderStyle(1);
        String endStyle3 = parser.getBorderStyle(2);
        String endStyle4 = parser.getBorderStyle(3);
        staticAnimation.addAnimation(StaticAnimations.BR_TOP_STYLE, startStyle1, endStyle1);
        staticAnimation.addAnimation(StaticAnimations.BR_RIGHT_STYLE, startStyle2, endStyle2);
        staticAnimation.addAnimation(StaticAnimations.BR_BOTTOM_STYLE, startStyle3, endStyle3);
        staticAnimation.addAnimation(StaticAnimations.BR_LEFT_STYLE, startStyle4, endStyle4);
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
                bgRadiusEndValue3 = bgRadiusDoubleValue1;
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
                bgRadiusEndValue4 = bgRadiusDoubleValue2;
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
        updateFourSidesSizeValuesInValuesManager(MainProps.BG_RADIUS, bgRadiusEndValue1, bgRadiusEndValue2, bgRadiusEndValue3, bgRadiusEndValue4);
        updateFourSidesAnimationStartValue(clickAnimation, staticClickAnimation, FAnimations.BG_RADIUS, bgRadiusEndValue1, bgRadiusEndValue2, bgRadiusEndValue3, bgRadiusEndValue4);
        updateFourSidesAnimationStartValue(hoverAnimation, staticHoverAnimation, FAnimations.BG_RADIUS, bgRadiusEndValue1, bgRadiusEndValue2, bgRadiusEndValue3, bgRadiusEndValue4);
    }

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
                paddingEndValue3 = paddingDoubleValue1;
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
                paddingEndValue4 = paddingDoubleValue2;
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
        updateFourSidesSizeValuesInValuesManager(MainProps.PADDING, paddingEndValue1, paddingEndValue2, paddingEndValue3, paddingEndValue4);
        updateFourSidesAnimationStartValue(clickAnimation, staticClickAnimation, FAnimations.PADDING, paddingEndValue1, paddingEndValue2, paddingEndValue3, paddingEndValue4);
        updateFourSidesAnimationStartValue(hoverAnimation, staticHoverAnimation, FAnimations.PADDING, paddingEndValue1, paddingEndValue2, paddingEndValue3, paddingEndValue4);
    }

    private int bgInsetsParametersCount;
    private double bgInsetsStartValue1;
    private double bgInsetsStartValue2;
    private double bgInsetsStartValue3;
    private double bgInsetsStartValue4;
    private double bgInsetsEndValue1;
    private double bgInsetsEndValue2;
    private double bgInsetsEndValue3;
    private double bgInsetsEndValue4;
    private double bgInsetsDoubleValue1;
    private double bgInsetsDoubleValue2;
    private double bgInsetsDoubleValue3;
    private double bgInsetsDoubleValue4;
    private String bgInsetsUnit1;
    private String bgInsetsUnit2;
    private String bgInsetsUnit3;
    private String bgInsetsUnit4;
    private long bgInsetsDuration1;
    private long bgInsetsDuration2;
    private long bgInsetsDuration3;
    private long bgInsetsDuration4;
    private String bgInsetsInterpolator1;
    private String bgInsetsInterpolator2;
    private String bgInsetsInterpolator3;
    private String bgInsetsInterpolator4;
    public void addBgInsetsProperty (boolean parentIsNull){
        bgInsetsParametersCount = parser.getParametersCount();
        bgInsetsStartValue1 = valuesManager.getDouble(MainProps.BG_TOP_INSET);
        bgInsetsStartValue2 = valuesManager.getDouble(MainProps.BG_RIGHT_INSET);
        bgInsetsStartValue3 = valuesManager.getDouble(MainProps.BG_BOTTOM_INSET);
        bgInsetsStartValue4 = valuesManager.getDouble(MainProps.BG_LEFT_INSET);
        bgInsetsEndValue1 = 0;
        bgInsetsEndValue2 = 0;
        bgInsetsEndValue3 = 0;
        bgInsetsEndValue4 = 0;
        bgInsetsDoubleValue1 = parser.getDoubleValue(0);
        bgInsetsDoubleValue2 = parser.getDoubleValue(1);
        bgInsetsDoubleValue3 = parser.getDoubleValue(2);
        bgInsetsDoubleValue4 = parser.getDoubleValue(3);
        bgInsetsUnit1 = parser.getUnit(0);
        bgInsetsUnit2 = parser.getUnit(1);
        bgInsetsUnit3 = parser.getUnit(2);
        bgInsetsUnit4 = parser.getUnit(3);
        bgInsetsDuration1 = parser.getDuration(0);
        bgInsetsDuration2 = parser.getDuration(1);
        bgInsetsDuration3 = parser.getDuration(2);
        bgInsetsDuration4 = parser.getDuration(3);
        bgInsetsInterpolator1 = parser.getInterpolator(0);
        bgInsetsInterpolator2 = parser.getInterpolator(1);
        bgInsetsInterpolator3 = parser.getInterpolator(2);
        bgInsetsInterpolator4 = parser.getInterpolator(3);
        listenersManager.remove(ListenerID.WIDTH_BG_RIGHT_LEFT_INSETS);
        listenersManager.remove(ListenerID.HEIGHT_BG_TOP_BOTTOM_INSETS);
        listenersManager.remove(ListenerID.HEIGHT_BG_TOP_INSET);
        listenersManager.remove(ListenerID.WIDTH_BG_RIGHT_INSET);
        listenersManager.remove(ListenerID.HEIGHT_BG_BOTTOM_INSET);
        listenersManager.remove(ListenerID.WIDTH_BG_LEFT_INSET);
        stopTempPaddingAnimations();
        if(!parentIsNull) {
            addBgInsetsProp();
        }
        else{
            pendingTaskManager.addTask(ListenerID.BG_INSETS, (e) -> {
                addBgInsetsProp();
            });
        }
    }

    private void addBgInsetsProp(){
        if(bgInsetsParametersCount == 1){
            bgInsetsDuration2 = bgInsetsDuration1;
            bgInsetsDuration3 = bgInsetsDuration1;
            bgInsetsDuration4 = bgInsetsDuration1;
            bgInsetsInterpolator2 = bgInsetsInterpolator1;
            bgInsetsInterpolator3 = bgInsetsInterpolator1;
            bgInsetsInterpolator4 = bgInsetsInterpolator1;
            if(bgInsetsUnit1.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue1 = component.getPrefHeight() * (bgInsetsDoubleValue1 / 100);
                bgInsetsEndValue2 = component.getPrefWidth() * (bgInsetsDoubleValue1 / 100);
                bgInsetsEndValue3 = bgInsetsEndValue1;
                bgInsetsEndValue4 = bgInsetsEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setRightLeftInsets(component, (double)newVal * (bgInsetsDoubleValue1 / 100));
                };
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setTopBottomInsets(component, (double)newVal * (bgInsetsDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_RIGHT_LEFT_INSETS, MainProps.WIDTH, widthListener);
                listenersManager.add(component, ListenerID.HEIGHT_BG_TOP_BOTTOM_INSETS, MainProps.HEIGHT, heightListener);
            }
            else if(bgInsetsUnit1.equals(FUnit.PIXELS)){
                bgInsetsEndValue1 = bgInsetsDoubleValue1;
                bgInsetsEndValue2 = bgInsetsEndValue1;
                bgInsetsEndValue3 = bgInsetsEndValue1;
                bgInsetsEndValue4 = bgInsetsEndValue1;
            }
        }
        else if(bgInsetsParametersCount == 2){
            bgInsetsDuration3 = bgInsetsDuration1;
            bgInsetsDuration4 = bgInsetsDuration2;
            bgInsetsInterpolator3 = bgInsetsInterpolator1;
            bgInsetsInterpolator4 = bgInsetsInterpolator2;
            if(bgInsetsUnit1.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue1 = component.getPrefHeight() * (bgInsetsDoubleValue1 / 100);
                bgInsetsEndValue3 = bgInsetsEndValue1;
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setTopBottomInsets(component, (double)newVal * (bgInsetsDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BG_TOP_BOTTOM_INSETS, MainProps.HEIGHT, heightListener);
            }
            else if(bgInsetsUnit1.equals(FUnit.PIXELS)){
                bgInsetsEndValue1 = bgInsetsDoubleValue1;
                bgInsetsEndValue3 = bgInsetsDoubleValue1;
            }
            if(bgInsetsUnit2.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue2 = component.getPrefWidth() * (bgInsetsDoubleValue2 / 100);
                bgInsetsEndValue4 = bgInsetsEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setRightLeftInsets(component, (double)newVal * (bgInsetsDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_RIGHT_LEFT_INSETS, MainProps.WIDTH, widthListener);
            }
            else if(bgInsetsUnit1.equals(FUnit.PIXELS)){
                bgInsetsEndValue2 = bgInsetsDoubleValue2;
                bgInsetsEndValue4 = bgInsetsDoubleValue2;
            }
        }
        else if(bgInsetsParametersCount == 4){
            if(bgInsetsUnit1.equals(FUnit.PERCENTAGE) && bgInsetsUnit3.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue1 = component.getPrefHeight() * (bgInsetsDoubleValue1 / 100);
                bgInsetsEndValue3 = component.getPrefHeight() * (bgInsetsDoubleValue3 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setTopBottomInsets(component, (double)newVal * (bgInsetsDoubleValue1 / 100), (double)newVal * (bgInsetsDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BG_TOP_BOTTOM_INSETS, MainProps.HEIGHT, heightListener);
            }
            else if(bgInsetsUnit1.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue1 = component.getPrefHeight() * (bgInsetsDoubleValue1 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setTopInset(component, (double)newVal * (bgInsetsDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BG_TOP_INSET, MainProps.HEIGHT, heightListener);
            }
            else if(bgInsetsUnit3.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue3 = component.getPrefHeight() * (bgInsetsDoubleValue3 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setBottomInset(component, (double)newVal * (bgInsetsDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BG_BOTTOM_INSET, MainProps.HEIGHT, heightListener);
            }
            if(bgInsetsUnit1.equals(FUnit.PIXELS)){
                bgInsetsEndValue1 = bgInsetsDoubleValue1;
            }
            if(bgInsetsUnit3.equals(FUnit.PIXELS)){
                bgInsetsEndValue3 = bgInsetsDoubleValue3;
            }
            if(bgInsetsUnit2.equals(FUnit.PERCENTAGE) && bgInsetsUnit4.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue2 = component.getPrefWidth() * (bgInsetsDoubleValue2 / 100);
                bgInsetsEndValue4 = component.getPrefWidth() * (bgInsetsDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setRightLeftInsets(component, (double)newVal * (bgInsetsDoubleValue2 / 100), (double)newVal * (bgInsetsDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_RIGHT_LEFT_INSETS, MainProps.WIDTH, widthListener);
            }
            else if(bgInsetsUnit2.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue2 = component.getPrefWidth() * (bgInsetsDoubleValue2 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setRightInset(component, (double)newVal * (bgInsetsDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_RIGHT_INSET, MainProps.WIDTH, widthListener);
            }
            else if(bgInsetsUnit4.equals(FUnit.PERCENTAGE)){
                bgInsetsEndValue4 = component.getPrefWidth() * (bgInsetsDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBgInsetsAnimations();
                    BackgroundSetter.setLeftInset(component, (double)newVal * (bgInsetsDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BG_LEFT_INSET, MainProps.WIDTH, widthListener);
            }
            if(bgInsetsUnit2.equals(FUnit.PIXELS)){
                bgInsetsEndValue2 = bgInsetsDoubleValue2;
            }
            if(bgInsetsUnit4.equals(FUnit.PIXELS)){
                bgInsetsEndValue4 = bgInsetsDoubleValue4;
            }
        }
        if(bgInsetsDuration1 > 0){
            addTempAnimation(component, FAnimations.BG_TOP_INSET, bgInsetsStartValue1, bgInsetsEndValue1, bgInsetsDoubleValue1, bgInsetsDuration1, bgInsetsInterpolator1);
        }
        else{
            BackgroundSetter.setTopInset(component, bgInsetsEndValue1);
        }
        if(bgInsetsDuration2 > 0){
            addTempAnimation(component, FAnimations.BG_RIGHT_INSET, bgInsetsStartValue2, bgInsetsEndValue2, bgInsetsDoubleValue2, bgInsetsDuration2, bgInsetsInterpolator2);
        }
        else{
            BackgroundSetter.setRightInset(component, bgInsetsEndValue2);
        }
        if(bgInsetsDuration3 > 0){
            addTempAnimation(component, FAnimations.BG_BOTTOM_INSET, bgInsetsStartValue3, bgInsetsEndValue3, bgInsetsDoubleValue3, bgInsetsDuration3, bgInsetsInterpolator3);
        }
        else{
            BackgroundSetter.setBottomInset(component, bgInsetsEndValue3);
        }
        if(bgInsetsDuration4 > 0){
            addTempAnimation(component, FAnimations.BG_LEFT_INSET, bgInsetsStartValue4, bgInsetsEndValue4, bgInsetsDoubleValue4, bgInsetsDuration4, bgInsetsInterpolator4);
        }
        else{
            BackgroundSetter.setLeftInset(component, bgInsetsEndValue4);
        }
        updateFourSidesSizeValuesInValuesManager(MainProps.BG_INSETS, bgInsetsEndValue1, bgInsetsEndValue2, bgInsetsEndValue3, bgInsetsEndValue4);
        updateFourSidesAnimationStartValue(clickAnimation, staticClickAnimation, FAnimations.BG_INSETS, bgInsetsEndValue1, bgInsetsEndValue2, bgInsetsEndValue3, bgInsetsEndValue4);
        updateFourSidesAnimationStartValue(hoverAnimation, staticHoverAnimation, FAnimations.BG_INSETS, bgInsetsEndValue1, bgInsetsEndValue2, bgInsetsEndValue3, bgInsetsEndValue4);
    }

    private String brStartColor1;
    private String brStartColor2;
    private String brStartColor3;
    private String brStartColor4;
    private String brEndColor1;
    private String brEndColor2;
    private String brEndColor3;
    private String brEndColor4;
    private long brColorDuration1;
    private long brColorDuration2;
    private long brColorDuration3;
    private long brColorDuration4;
    private String brColorInterpolator1;
    private String brColorInterpolator2;
    private String brColorInterpolator3;
    private String brColorInterpolator4;
    public void addBorderColorProperty(boolean setColorWhenSettingBorder){
        brStartColor1 = valuesManager.getString(MainProps.BR_TOP_COLOR);
        brStartColor2 = valuesManager.getString(MainProps.BR_RIGHT_COLOR);
        brStartColor3 = valuesManager.getString(MainProps.BR_BOTTOM_COLOR);
        brStartColor4 = valuesManager.getString(MainProps.BR_LEFT_COLOR);
        brEndColor1 = parser.getColorValue(0);
        brEndColor2 = parser.getColorValue(1);
        brEndColor3 = parser.getColorValue(2);
        brEndColor4 = parser.getColorValue(3);
        if(setColorWhenSettingBorder){
            brColorDuration1 = parser.getBorderColorDuration();
            brColorDuration2 = parser.getBorderColorDuration();
            brColorDuration3 = parser.getBorderColorDuration();
            brColorDuration4 = parser.getBorderColorDuration();
            brColorInterpolator1 = parser.getBorderColorInterpolator();
            brColorInterpolator2 = parser.getBorderColorInterpolator();
            brColorInterpolator3 = parser.getBorderColorInterpolator();
            brColorInterpolator4 = parser.getBorderColorInterpolator();
        }
        else{
            brColorDuration1 = parser.getDuration(0);
            brColorDuration2 = parser.getDuration(1);
            brColorDuration3 = parser.getDuration(2);
            brColorDuration4 = parser.getDuration(3);
            brColorInterpolator1 = parser.getInterpolator(0);
            brColorInterpolator2 = parser.getInterpolator(1);
            brColorInterpolator3 = parser.getInterpolator(2);
            brColorInterpolator4 = parser.getInterpolator(3);
        }
        stopTempBrColorAnimations();
        stopTempBrOpacityAnimations();
        addBorderColorProp();
    }
    
    private void addBorderColorProp(){
        if(brColorDuration1 > 0){
            addTempBrColorAnimation(component, FAnimations.BR_TOP_COLOR, brStartColor1, brEndColor1, brColorDuration1, brColorInterpolator1);
        }
        else{
            BorderSetter.setTopBorderColor(component, brEndColor1);
        }
        if(brColorDuration2 > 0){
            addTempBrColorAnimation(component, FAnimations.BR_RIGHT_COLOR, brStartColor2, brEndColor2, brColorDuration2, brColorInterpolator2);
        }
        else{
            BorderSetter.setRightBorderColor(component, brEndColor2);
        }
        if(brColorDuration3 > 0){
            addTempBrColorAnimation(component, FAnimations.BR_BOTTOM_COLOR, brStartColor3, brEndColor3, brColorDuration3, brColorInterpolator3);
        }
        else{
            BorderSetter.setBottomBorderColor(component, brEndColor3);
        }
        if(brColorDuration4 > 0){
            addTempBrColorAnimation(component, FAnimations.BR_LEFT_COLOR, brStartColor4, brEndColor4, brColorDuration4, brColorInterpolator4);
        }
        else{
            BorderSetter.setLeftBorderColor(component, brEndColor4);
        }
        BorderSetter.setTopBorderOpacity(component, valuesManager.getDouble(MainProps.BR_TOP_OPACITY));
//        BorderSetter.setRightBorderOpacity(component, valuesManager.getDouble(MainProps.BR_RIGHT_OPACITY));
//        BorderSetter.setBottomBorderOpacity(component, valuesManager.getDouble(MainProps.BR_BOTTOM_OPACITY));
//        BorderSetter.setLeftBorderOpacity(component, valuesManager.getDouble(MainProps.BR_LEFT_OPACITY));
        updateBorderColorsInValuesManager(brEndColor1, brEndColor2, brEndColor3, brEndColor4);
        updateBorderStartColorAnimation(clickAnimation, staticClickAnimation, brEndColor1, brEndColor2, brEndColor3, brEndColor4);
        updateBorderStartColorAnimation(hoverAnimation, staticHoverAnimation, brEndColor1, brEndColor2, brEndColor3, brEndColor4);
    }

    private double brStartOpacity1;
    private double brStartOpacity2;
    private double brStartOpacity3;
    private double brStartOpacity4;
    private double brEndOpacity1;
    private double brEndOpacity2;
    private double brEndOpacity3;
    private double brEndOpacity4;
    private long brOpacityDuration1;
    private long brOpacityDuration2;
    private long brOpacityDuration3;
    private long brOpacityDuration4;
    private String brOpacityInterpolator1;
    private String brOpacityInterpolator2;
    private String brOpacityInterpolator3;
    private String brOpacityInterpolator4;
    public void addBorderOpacityProperty(boolean setOpacityWhenSettingBorder){
        brStartOpacity1 = valuesManager.getDouble(MainProps.BR_TOP_OPACITY);
        brStartOpacity2 = valuesManager.getDouble(MainProps.BR_RIGHT_OPACITY);
        brStartOpacity3 = valuesManager.getDouble(MainProps.BR_BOTTOM_OPACITY);
        brStartOpacity4 = valuesManager.getDouble(MainProps.BR_LEFT_OPACITY);
        brEndOpacity1 = parser.getDoubleValue(0);
        brEndOpacity2 = parser.getDoubleValue(1);
        brEndOpacity3 = parser.getDoubleValue(2);
        brEndOpacity4 = parser.getDoubleValue(3);
        if(setOpacityWhenSettingBorder){
            brOpacityDuration1 = parser.getDuration(0);
            brOpacityDuration2 = parser.getDuration(1);
            brOpacityDuration3 = parser.getDuration(2);
            brOpacityDuration4 = parser.getDuration(3);
            brOpacityInterpolator1 = parser.getInterpolator(0);
            brOpacityInterpolator2 = parser.getInterpolator(1);
            brOpacityInterpolator3 = parser.getInterpolator(2);
            brOpacityInterpolator4 = parser.getInterpolator(3);
        }
        else{
            brOpacityDuration1 = parser.getDuration(0);
            brOpacityDuration2 = parser.getDuration(1);
            brOpacityDuration3 = parser.getDuration(2);
            brOpacityDuration4 = parser.getDuration(3);
            brOpacityInterpolator1 = parser.getInterpolator(0);
            brOpacityInterpolator2 = parser.getInterpolator(1);
            brOpacityInterpolator3 = parser.getInterpolator(2);
            brOpacityInterpolator4 = parser.getInterpolator(3);
        }
        stopTempBrOpacityAnimations();
        stopTempBrColorAnimations();
        addBorderOpacityProp();
    }

    private void addBorderOpacityProp(){
        if(brOpacityDuration1 > 0){
            addTempBrOpacityAnimation(component, FAnimations.BR_TOP_OPACITY, brStartOpacity1, brEndOpacity1, brOpacityDuration1, brOpacityInterpolator1);
        }
        else{
            BorderSetter.setTopBorderOpacity(component, brEndOpacity1);
        }
        if(brOpacityDuration2 > 0){
            addTempBrOpacityAnimation(component, FAnimations.BR_RIGHT_OPACITY, brStartOpacity2, brEndOpacity2, brOpacityDuration2, brOpacityInterpolator2);
        }
        else{
            BorderSetter.setRightBorderOpacity(component, brEndOpacity2);
        }
        if(brOpacityDuration3 > 0){
            addTempBrOpacityAnimation(component, FAnimations.BR_BOTTOM_OPACITY, brStartOpacity3, brEndOpacity3, brOpacityDuration3, brOpacityInterpolator3);
        }
        else{
            BorderSetter.setBottomBorderOpacity(component, brEndOpacity3);
        }
        if(brOpacityDuration4 > 0){
            addTempBrOpacityAnimation(component, FAnimations.BR_LEFT_OPACITY, brStartOpacity4, brEndOpacity4, brOpacityDuration4, brOpacityInterpolator4);
        }
        else{
            BorderSetter.setLeftBorderOpacity(component, brEndOpacity4);
        }
        updateFourSidesSizeValuesInValuesManager(MainProps.BR_OPACITY, brEndOpacity1, brEndOpacity2, brEndOpacity3, brEndOpacity4);
        updateBorderStartOpacityAnimation(clickAnimation, staticClickAnimation, brEndOpacity1, brEndOpacity2, brEndOpacity3, brEndOpacity4);
        updateBorderStartOpacityAnimation(hoverAnimation, staticHoverAnimation, brEndOpacity1, brEndOpacity2, brEndOpacity3, brEndOpacity4);
    }

    public void addBorderStyleProperty(){
        String brStyleValue1 = parser.getBorderStyle(0);
        String brStyleValue2 = parser.getBorderStyle(1);
        String brStyleValue3 = parser.getBorderStyle(2);
        String brStyleValue4 = parser.getBorderStyle(3);
        BorderSetter.setTopBorderStyle(component, brStyleValue1);
        BorderSetter.setRightBorderStyle(component, brStyleValue2);
        BorderSetter.setBottomBorderStyle(component, brStyleValue3);
        BorderSetter.setLeftBorderStyle(component, brStyleValue4);
        valuesManager.set(MainProps.BR_TOP_STYLE, brStyleValue1);
        valuesManager.set(MainProps.BR_RIGHT_STYLE, brStyleValue2);
        valuesManager.set(MainProps.BR_BOTTOM_STYLE, brStyleValue3);
        valuesManager.set(MainProps.BR_LEFT_STYLE, brStyleValue4);
    }

    private int brRadiusParametersCount;
    private double brRadiusStartValue1;
    private double brRadiusStartValue2;
    private double brRadiusStartValue3;
    private double brRadiusStartValue4;
    private double brRadiusEndValue1;
    private double brRadiusEndValue2;
    private double brRadiusEndValue3;
    private double brRadiusEndValue4;
    private double brRadiusDoubleValue1;
    private double brRadiusDoubleValue2;
    private double brRadiusDoubleValue3;
    private double brRadiusDoubleValue4;
    private String brRadiusUnit1;
    private String brRadiusUnit2;
    private String brRadiusUnit3;
    private String brRadiusUnit4;
    private long brRadiusDuration1;
    private long brRadiusDuration2;
    private long brRadiusDuration3;
    private long brRadiusDuration4;
    private String brRadiusInterpolator1;
    private String brRadiusInterpolator2;
    private String brRadiusInterpolator3;
    private String brRadiusInterpolator4;
    public void addBorderRadiusProperty(boolean parentIsNull){
        brRadiusParametersCount = parser.getParametersCount();
        brRadiusStartValue1 = valuesManager.getDouble(MainProps.BR_TL_RADIUS);
        brRadiusStartValue2 = valuesManager.getDouble(MainProps.BR_TR_RADIUS);
        brRadiusStartValue3 = valuesManager.getDouble(MainProps.BR_BR_RADIUS);
        brRadiusStartValue4 = valuesManager.getDouble(MainProps.BR_BL_RADIUS);
        brRadiusEndValue1 = 0;
        brRadiusEndValue2 = 0;
        brRadiusEndValue3 = 0;
        brRadiusEndValue4 = 0;
        brRadiusDoubleValue1 = parser.getDoubleValue(0);
        brRadiusDoubleValue2 = parser.getDoubleValue(1);
        brRadiusDoubleValue3 = parser.getDoubleValue(2);
        brRadiusDoubleValue4 = parser.getDoubleValue(3);
        brRadiusUnit1 = parser.getUnit(0);
        brRadiusUnit2 = parser.getUnit(1);
        brRadiusUnit3 = parser.getUnit(2);
        brRadiusUnit4 = parser.getUnit(3);
        brRadiusDuration1 = parser.getDuration(0);
        brRadiusDuration2 = parser.getDuration(1);
        brRadiusDuration3 = parser.getDuration(2);
        brRadiusDuration4 = parser.getDuration(3);
        brRadiusInterpolator1 = parser.getInterpolator(0);
        brRadiusInterpolator2 = parser.getInterpolator(1);
        brRadiusInterpolator3 = parser.getInterpolator(2);
        brRadiusInterpolator4 = parser.getInterpolator(3);
        listenersManager.remove(ListenerID.WIDTH_BR_TL_BR_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BR_TR_BL_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BR_TL_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BR_TR_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BR_BR_RADIUS);
        listenersManager.remove(ListenerID.WIDTH_BR_BL_RADIUS);
        stopTempBrRadiusAnimations();
        addBorderRadiusProp();
    }

    private void addBorderRadiusProp(){
        if(brRadiusParametersCount == 1){
            brRadiusDuration2 = brRadiusDuration1;
            brRadiusDuration3 = brRadiusDuration1;
            brRadiusDuration4 = brRadiusDuration1;
            brRadiusInterpolator2 = brRadiusInterpolator1;
            brRadiusInterpolator3 = brRadiusInterpolator1;
            brRadiusInterpolator4 = brRadiusInterpolator1;
            if(brRadiusUnit1.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue1 = component.getPrefWidth() * (brRadiusDoubleValue1 / 100);
                brRadiusEndValue2 = brRadiusEndValue1;
                brRadiusEndValue3 = brRadiusEndValue1;
                brRadiusEndValue4 = brRadiusEndValue1;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setBorderRadius(component, (double)newVal * (brRadiusDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(brRadiusUnit1.equals(FUnit.PIXELS)){
                brRadiusEndValue1 = brRadiusDoubleValue1;
                brRadiusEndValue2 = brRadiusEndValue1;
                brRadiusEndValue3 = brRadiusEndValue1;
                brRadiusEndValue4 = brRadiusEndValue1;
            }
        }
        else if(brRadiusParametersCount == 2){
            brRadiusDuration3 = brRadiusDuration1;
            brRadiusDuration4 = brRadiusDuration2;
            brRadiusInterpolator3 = brRadiusInterpolator1;
            brRadiusInterpolator4 = brRadiusInterpolator2;
            if(brRadiusUnit1.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue1 = component.getPrefWidth() * (brRadiusDoubleValue1 / 100);
                brRadiusEndValue3 = brRadiusEndValue1;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setTopLeftBorderRadius(component, (double)newVal * (brRadiusDoubleValue1 / 100));
                    BorderSetter.setBottomRightBorderRadius(component, (double)newVal * (brRadiusDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_TL_BR_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(brRadiusUnit1.equals(FUnit.PIXELS)){
                brRadiusEndValue1 = brRadiusDoubleValue1;
                brRadiusEndValue3 = brRadiusDoubleValue1;
            }
            if(brRadiusUnit2.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue2 = component.getPrefWidth() * (brRadiusDoubleValue2 / 100);
                brRadiusEndValue4 = brRadiusEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setTopRightBorderRadius(component, (double)newVal * (brRadiusDoubleValue2 / 100));
                    BorderSetter.setBottomLeftBorderRadius(component, (double)newVal * (brRadiusDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_TR_BL_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(brRadiusUnit1.equals(FUnit.PIXELS)){
                brRadiusEndValue2 = brRadiusDoubleValue2;
                brRadiusEndValue4 = brRadiusDoubleValue2;
            }
        }
        else if(brRadiusParametersCount == 4 || brRadiusParametersCount == 8){
            if(brRadiusUnit1.equals(FUnit.PERCENTAGE) && brRadiusUnit3.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue1 = component.getPrefWidth() * (brRadiusDoubleValue1 / 100);
                brRadiusEndValue3 = component.getPrefWidth() * (brRadiusDoubleValue3 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setTopLeftBorderRadius(component, (double)newVal * (brRadiusDoubleValue1 / 100));
                    BorderSetter.setBottomRightBorderRadius(component, (double)newVal * (brRadiusDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_TL_BR_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(brRadiusUnit1.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue1 = component.getPrefWidth() * (brRadiusDoubleValue1 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setTopLeftBorderRadius(component, (double)newVal * (brRadiusDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_TL_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(brRadiusUnit3.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue3 = component.getPrefWidth() * (brRadiusDoubleValue3 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setBottomRightBorderRadius(component, (double)newVal * (brRadiusDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_BR_RADIUS, MainProps.WIDTH, widthListener);
            }
            if(brRadiusUnit1.equals(FUnit.PIXELS)){
                brRadiusEndValue1 = brRadiusDoubleValue1;
            }
            if(brRadiusUnit3.equals(FUnit.PIXELS)){
                brRadiusEndValue3 = brRadiusDoubleValue3;
            }
            if(brRadiusUnit2.equals(FUnit.PERCENTAGE) && brRadiusUnit4.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue2 = component.getPrefWidth() * (brRadiusDoubleValue2 / 100);
                brRadiusEndValue4 = component.getPrefWidth() * (brRadiusDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setTopRightBorderRadius(component, (double)newVal * (brRadiusDoubleValue2 / 100));
                    BorderSetter.setBottomLeftBorderRadius(component, (double)newVal * (brRadiusDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_TR_BL_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(brRadiusUnit2.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue2 = component.getPrefWidth() * (brRadiusDoubleValue2 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setTopRightBorderRadius(component, (double)newVal * (brRadiusDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_TR_RADIUS, MainProps.WIDTH, widthListener);
            }
            else if(brRadiusUnit4.equals(FUnit.PERCENTAGE)){
                brRadiusEndValue4 = component.getPrefWidth() * (brRadiusDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrRadiusAnimations();
                    BorderSetter.setBottomLeftBorderRadius(component, (double)newVal * (brRadiusDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_BL_RADIUS, MainProps.WIDTH, widthListener);
            }
            if(brRadiusUnit2.equals(FUnit.PIXELS)){
                brRadiusEndValue2 = brRadiusDoubleValue2;
            }
            if(brRadiusUnit4.equals(FUnit.PIXELS)){
                brRadiusEndValue4 = brRadiusDoubleValue4;
            }
        }
        if(brRadiusDuration1 > 0){
            addTempAnimation(component, FAnimations.BR_TL_RADIUS, brRadiusStartValue1, brRadiusEndValue1, brRadiusDoubleValue1, brRadiusDuration1, brRadiusInterpolator1);
        }
        else{
            BorderSetter.setTopLeftBorderRadius(component, brRadiusEndValue1);
        }
        if(brRadiusDuration2 > 0){
            addTempAnimation(component, FAnimations.BR_TR_RADIUS, brRadiusStartValue2, brRadiusEndValue2, brRadiusDoubleValue2, brRadiusDuration2, brRadiusInterpolator2);
        }
        else{
            BorderSetter.setTopRightBorderRadius(component, brRadiusEndValue2);
        }
        if(brRadiusDuration3 > 0){
            addTempAnimation(component, FAnimations.BR_BR_RADIUS, brRadiusStartValue3, brRadiusEndValue3, brRadiusDoubleValue3, brRadiusDuration3, brRadiusInterpolator3);
        }
        else{
            BorderSetter.setBottomRightBorderRadius(component, brRadiusEndValue3);
        }
        if(brRadiusDuration4 > 0){
            addTempAnimation(component, FAnimations.BR_BL_RADIUS, brRadiusStartValue4, brRadiusEndValue4, brRadiusDoubleValue4, brRadiusDuration4, brRadiusInterpolator4);
        }
        else{
            BorderSetter.setBottomLeftBorderRadius(component, brRadiusEndValue4);
        }
        updateFourSidesSizeValuesInValuesManager(MainProps.BR_RADIUS, brRadiusEndValue1, brRadiusEndValue2, brRadiusEndValue3, brRadiusEndValue4);
        updateFourSidesAnimationStartValue(clickAnimation, staticClickAnimation, FAnimations.BR_RADIUS, brRadiusEndValue1, brRadiusEndValue2, brRadiusEndValue3, brRadiusEndValue4);
        updateFourSidesAnimationStartValue(hoverAnimation, staticHoverAnimation, FAnimations.BR_RADIUS, brRadiusEndValue1, brRadiusEndValue2, brRadiusEndValue3, brRadiusEndValue4);
    }

    private int brWidthParametersCount;
    private double brWidthStartValue1;
    private double brWidthStartValue2;
    private double brWidthStartValue3;
    private double brWidthStartValue4;
    private double brWidthEndValue1;
    private double brWidthEndValue2;
    private double brWidthEndValue3;
    private double brWidthEndValue4;
    private double brWidthDoubleValue1;
    private double brWidthDoubleValue2;
    private double brWidthDoubleValue3;
    private double brWidthDoubleValue4;
    private String brWidthUnit1;
    private String brWidthUnit2;
    private String brWidthUnit3;
    private String brWidthUnit4;
    private long brWidthDuration1;
    private long brWidthDuration2;
    private long brWidthDuration3;
    private long brWidthDuration4;
    private String brWidthInterpolator1;
    private String brWidthInterpolator2;
    private String brWidthInterpolator3;
    private String brWidthInterpolator4;
    public void addBorderWidthProperty(boolean parentIsNull, boolean setWidthWhenSettingBorder){
        // When this method is called when setting the border then the first 4 double values
        // from parser will be the values for border radius and the other 4 will be the
        // values for border width but when this method is called when setting only the border width
        // explicitly then the first 4 values will be for the border width.
        // This is determined by setting the setWidthWhenSettingBorder parameter to true or false.
        // The indexOffset is used to offset the index by 4.
        int indexOffset = 0;
        if(setWidthWhenSettingBorder) {
            indexOffset = 4;
        }
        brWidthParametersCount = parser.getParametersCount();
        brWidthStartValue1 = valuesManager.getDouble(MainProps.BR_TOP_WIDTH);
        brWidthStartValue2 = valuesManager.getDouble(MainProps.BR_RIGHT_WIDTH);
        brWidthStartValue3 = valuesManager.getDouble(MainProps.BR_BOTTOM_WIDTH);
        brWidthStartValue4 = valuesManager.getDouble(MainProps.BR_LEFT_WIDTH);
        brWidthEndValue1 = 0;
        brWidthEndValue2 = 0;
        brWidthEndValue3 = 0;
        brWidthEndValue4 = 0;
        brWidthDoubleValue1 = parser.getDoubleValue(indexOffset);
        brWidthDoubleValue2 = parser.getDoubleValue(1 + indexOffset);
        brWidthDoubleValue3 = parser.getDoubleValue(2 + indexOffset);
        brWidthDoubleValue4 = parser.getDoubleValue(3 + indexOffset);
        brWidthUnit1 = parser.getUnit(indexOffset);
        brWidthUnit2 = parser.getUnit(1 + indexOffset);
        brWidthUnit3 = parser.getUnit(2 + indexOffset);
        brWidthUnit4 = parser.getUnit(3 + indexOffset);
        brWidthDuration1 = parser.getDuration(indexOffset);
        brWidthDuration2 = parser.getDuration(1 + indexOffset);
        brWidthDuration3 = parser.getDuration(2 + indexOffset);
        brWidthDuration4 = parser.getDuration(3 + indexOffset);
        brWidthInterpolator1 = parser.getInterpolator(indexOffset);
        brWidthInterpolator2 = parser.getInterpolator(1 + indexOffset);
        brWidthInterpolator3 = parser.getInterpolator(2 + indexOffset);
        brWidthInterpolator4 = parser.getInterpolator(3 + indexOffset);
        listenersManager.remove(ListenerID.HEIGHT_BR_TOP_BOTTOM_WIDTH);
        listenersManager.remove(ListenerID.WIDTH_BR_RIGHT_LEFT_WIDTH);
        listenersManager.remove(ListenerID.HEIGHT_BR_TOP_WIDTH);
        listenersManager.remove(ListenerID.WIDTH_BR_RIGHT_WIDTH);
        listenersManager.remove(ListenerID.HEIGHT_BR_BOTTOM_WIDTH);
        listenersManager.remove(ListenerID.WIDTH_BR_LEFT_WIDTH);
        stopTempBrWidthAnimations();
        addBorderWidthProp();
    }

    private void addBorderWidthProp(){
        if(brWidthParametersCount == 1){
            brWidthDuration2 = brWidthDuration1;
            brWidthDuration3 = brWidthDuration1;
            brWidthDuration4 = brWidthDuration1;
            brWidthInterpolator2 = brWidthInterpolator1;
            brWidthInterpolator3 = brWidthInterpolator1;
            brWidthInterpolator4 = brWidthInterpolator1;
            if(brWidthUnit1.equals(FUnit.PERCENTAGE)){
                brWidthEndValue1 = component.getPrefHeight() * (brWidthDoubleValue1 / 100);
                brWidthEndValue2 = component.getPrefWidth() * (brWidthDoubleValue1 / 100);
                brWidthEndValue3 = brWidthEndValue1;
                brWidthEndValue4 = brWidthEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setRightLeftBorderWidth(component, (double)newVal * (brWidthDoubleValue1 / 100));
                };
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setTopBottomBorderWidth(component, (double)newVal * (brWidthDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RIGHT_LEFT_WIDTH, MainProps.WIDTH, widthListener);
                listenersManager.add(component, ListenerID.HEIGHT_BR_TOP_BOTTOM_WIDTH, MainProps.HEIGHT, heightListener);
            }
            else if(brWidthUnit1.equals(FUnit.PIXELS)){
                brWidthEndValue1 = brWidthDoubleValue1;
                brWidthEndValue2 = brWidthEndValue1;
                brWidthEndValue3 = brWidthEndValue1;
                brWidthEndValue4 = brWidthEndValue1;
            }
        }
        else if(brWidthParametersCount == 2){
            brWidthDuration3 = brWidthDuration1;
            brWidthDuration4 = brWidthDuration2;
            brWidthInterpolator3 = brWidthInterpolator1;
            brWidthInterpolator4 = brWidthInterpolator2;
            if(brWidthUnit1.equals(FUnit.PERCENTAGE)){
                brWidthEndValue1 = component.getPrefHeight() * (brWidthDoubleValue1 / 100);
                brWidthEndValue3 = brWidthEndValue1;
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setTopBottomBorderWidth(component, (double)newVal * (brWidthDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BR_TOP_BOTTOM_WIDTH, MainProps.HEIGHT, heightListener);
            }
            else if(brWidthUnit1.equals(FUnit.PIXELS)){
                brWidthEndValue1 = brWidthDoubleValue1;
                brWidthEndValue3 = brWidthDoubleValue1;
            }
            if(brWidthUnit2.equals(FUnit.PERCENTAGE)){
                brWidthEndValue2 = component.getPrefWidth() * (brWidthDoubleValue2 / 100);
                brWidthEndValue4 = brWidthEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setRightLeftBorderWidth(component, (double)newVal * (brWidthDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RIGHT_LEFT_WIDTH, MainProps.WIDTH, widthListener);
            }
            else if(brWidthUnit1.equals(FUnit.PIXELS)){
                brWidthEndValue2 = brWidthDoubleValue2;
                brWidthEndValue4 = brWidthDoubleValue2;
            }
        }
        else if(brWidthParametersCount == 4 || brWidthParametersCount == 8){
            if(brWidthUnit1.equals(FUnit.PERCENTAGE) && brWidthUnit3.equals(FUnit.PERCENTAGE)){
                brWidthEndValue1 = component.getPrefHeight() * (brWidthDoubleValue1 / 100);
                brWidthEndValue3 = component.getPrefHeight() * (brWidthDoubleValue3 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setTopBottomBorderWidth(component, (double)newVal * (brWidthDoubleValue1 / 100), (double)newVal * (brWidthDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BR_TOP_BOTTOM_WIDTH, MainProps.HEIGHT, heightListener);
            }
            else if(brWidthUnit1.equals(FUnit.PERCENTAGE)){
                brWidthEndValue1 = component.getPrefHeight() * (brWidthDoubleValue1 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setTopBorderWidth(component, (double)newVal * (brWidthDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BR_TOP_WIDTH, MainProps.HEIGHT, heightListener);
            }
            else if(brWidthUnit3.equals(FUnit.PERCENTAGE)){
                brWidthEndValue3 = component.getPrefHeight() * (brWidthDoubleValue3 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setBottomBorderWidth(component, (double)newVal * (brWidthDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BR_BOTTOM_WIDTH, MainProps.HEIGHT, heightListener);
            }
            if(brWidthUnit1.equals(FUnit.PIXELS)){
                brWidthEndValue1 = brWidthDoubleValue1;
            }
            if(brWidthUnit3.equals(FUnit.PIXELS)){
                brWidthEndValue3 = brWidthDoubleValue3;
            }
            if(brWidthUnit2.equals(FUnit.PERCENTAGE) && brWidthUnit4.equals(FUnit.PERCENTAGE)){
                brWidthEndValue2 = component.getPrefWidth() * (brWidthDoubleValue2 / 100);
                brWidthEndValue4 = component.getPrefWidth() * (brWidthDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setRightLeftBorderWidth(component, (double)newVal * (brWidthDoubleValue2 / 100), (double)newVal * (brWidthDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RIGHT_LEFT_WIDTH, MainProps.WIDTH, widthListener);
            }
            else if(brWidthUnit2.equals(FUnit.PERCENTAGE)){
                brWidthEndValue2 = component.getPrefWidth() * (brWidthDoubleValue2 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setRightBorderWidth(component, (double)newVal * (brWidthDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RIGHT_WIDTH, MainProps.WIDTH, widthListener);
            }
            else if(brWidthUnit4.equals(FUnit.PERCENTAGE)){
                brWidthEndValue4 = component.getPrefWidth() * (brWidthDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrWidthAnimations();
                    BorderSetter.setLeftBorderWidth(component, (double)newVal * (brWidthDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_LEFT_WIDTH, MainProps.WIDTH, widthListener);
            }
            if(brWidthUnit2.equals(FUnit.PIXELS)){
                brWidthEndValue2 = brWidthDoubleValue2;
            }
            if(brWidthUnit4.equals(FUnit.PIXELS)){
                brWidthEndValue4 = brWidthDoubleValue4;
            }
        }
        if(brWidthDuration1 > 0){
            addTempAnimation(component, FAnimations.BR_TOP_WIDTH, brWidthStartValue1, brWidthEndValue1, brWidthDoubleValue1, brWidthDuration1, brWidthInterpolator1);
        }
        else{
            BorderSetter.setTopBorderWidth(component, brWidthEndValue1);
        }
        if(brWidthDuration2 > 0){
            addTempAnimation(component, FAnimations.BR_RIGHT_WIDTH, brWidthStartValue2, brWidthEndValue2, brWidthDoubleValue2, brWidthDuration2, brWidthInterpolator2);
        }
        else{
            BorderSetter.setRightBorderWidth(component, brWidthEndValue2);
        }
        if(brWidthDuration3 > 0){
            addTempAnimation(component, FAnimations.BR_BOTTOM_WIDTH, brWidthStartValue3, brWidthEndValue3, brWidthDoubleValue3, brWidthDuration3, brWidthInterpolator3);
        }
        else{
            BorderSetter.setBottomBorderWidth(component, brWidthEndValue3);
        }
        if(brWidthDuration4 > 0){
            addTempAnimation(component, FAnimations.BR_LEFT_WIDTH, brWidthStartValue4, brWidthEndValue4, brWidthDoubleValue4, brWidthDuration4, brWidthInterpolator4);
        }
        else{
            BorderSetter.setLeftBorderWidth(component, brWidthEndValue4);
        }
        updateFourSidesSizeValuesInValuesManager(MainProps.BR_WIDTH, brWidthEndValue1, brWidthEndValue2, brWidthEndValue3, brWidthEndValue4);
        updateFourSidesAnimationStartValue(clickAnimation, staticClickAnimation, FAnimations.BR_WIDTH, brWidthEndValue1, brWidthEndValue2, brWidthEndValue3, brWidthEndValue4);
        updateFourSidesAnimationStartValue(hoverAnimation, staticHoverAnimation, FAnimations.BR_WIDTH, brWidthEndValue1, brWidthEndValue2, brWidthEndValue3, brWidthEndValue4);
    }

    private int brInsetsParametersCount;
    private double brInsetsStartValue1;
    private double brInsetsStartValue2;
    private double brInsetsStartValue3;
    private double brInsetsStartValue4;
    private double brInsetsEndValue1;
    private double brInsetsEndValue2;
    private double brInsetsEndValue3;
    private double brInsetsEndValue4;
    private double brInsetsDoubleValue1;
    private double brInsetsDoubleValue2;
    private double brInsetsDoubleValue3;
    private double brInsetsDoubleValue4;
    private String brInsetsUnit1;
    private String brInsetsUnit2;
    private String brInsetsUnit3;
    private String brInsetsUnit4;
    private long brInsetsDuration1;
    private long brInsetsDuration2;
    private long brInsetsDuration3;
    private long brInsetsDuration4;
    private String brInsetsInterpolator1;
    private String brInsetsInterpolator2;
    private String brInsetsInterpolator3;
    private String brInsetsInterpolator4;
    public void addBorderInsetsProperty(boolean parentIsNull){
        brInsetsParametersCount = parser.getParametersCount();
        brInsetsStartValue1 = valuesManager.getDouble(MainProps.BR_TOP_INSET);
        brInsetsStartValue2 = valuesManager.getDouble(MainProps.BR_RIGHT_INSET);
        brInsetsStartValue3 = valuesManager.getDouble(MainProps.BR_BOTTOM_INSET);
        brInsetsStartValue4 = valuesManager.getDouble(MainProps.BR_LEFT_INSET);
        brInsetsEndValue1 = 0;
        brInsetsEndValue2 = 0;
        brInsetsEndValue3 = 0;
        brInsetsEndValue4 = 0;
        brInsetsDoubleValue1 = parser.getDoubleValue(0);
        brInsetsDoubleValue2 = parser.getDoubleValue(1);
        brInsetsDoubleValue3 = parser.getDoubleValue(2);
        brInsetsDoubleValue4 = parser.getDoubleValue(3);
        brInsetsUnit1 = parser.getUnit(0);
        brInsetsUnit2 = parser.getUnit(1);
        brInsetsUnit3 = parser.getUnit(2);
        brInsetsUnit4 = parser.getUnit(3);
        brInsetsDuration1 = parser.getDuration(0);
        brInsetsDuration2 = parser.getDuration(1);
        brInsetsDuration3 = parser.getDuration(2);
        brInsetsDuration4 = parser.getDuration(3);
        brInsetsInterpolator1 = parser.getInterpolator(0);
        brInsetsInterpolator2 = parser.getInterpolator(1);
        brInsetsInterpolator3 = parser.getInterpolator(2);
        brInsetsInterpolator4 = parser.getInterpolator(3);
        listenersManager.remove(ListenerID.WIDTH_BR_RIGHT_LEFT_INSETS);
        listenersManager.remove(ListenerID.HEIGHT_BR_TOP_BOTTOM_INSETS);
        listenersManager.remove(ListenerID.HEIGHT_BR_TOP_INSET);
        listenersManager.remove(ListenerID.WIDTH_BR_RIGHT_INSET);
        listenersManager.remove(ListenerID.HEIGHT_BR_BOTTOM_INSET);
        listenersManager.remove(ListenerID.WIDTH_BR_LEFT_INSET);
        stopTempBrInsetsAnimations();
        addBorderInsetsProp();
    }

    private void addBorderInsetsProp(){
        if(brInsetsParametersCount == 1){
            brInsetsDuration2 = brInsetsDuration1;
            brInsetsDuration3 = brInsetsDuration1;
            brInsetsDuration4 = brInsetsDuration1;
            brInsetsInterpolator2 = brInsetsInterpolator1;
            brInsetsInterpolator3 = brInsetsInterpolator1;
            brInsetsInterpolator4 = brInsetsInterpolator1;
            if(brInsetsUnit1.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue1 = component.getPrefHeight() * (brInsetsDoubleValue1 / 100);
                brInsetsEndValue2 = component.getPrefWidth() * (brInsetsDoubleValue1 / 100);
                brInsetsEndValue3 = brInsetsEndValue1;
                brInsetsEndValue4 = brInsetsEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setRightLeftBorderInsets(component, (double)newVal * (brInsetsDoubleValue1 / 100));
                };
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setTopBottomBorderInsets(component, (double)newVal * (brInsetsDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RIGHT_LEFT_INSETS, MainProps.WIDTH, widthListener);
                listenersManager.add(component, ListenerID.HEIGHT_BR_TOP_BOTTOM_INSETS, MainProps.HEIGHT, heightListener);
            }
            else if(brInsetsUnit1.equals(FUnit.PIXELS)){
                brInsetsEndValue1 = brInsetsDoubleValue1;
                brInsetsEndValue2 = brInsetsEndValue1;
                brInsetsEndValue3 = brInsetsEndValue1;
                brInsetsEndValue4 = brInsetsEndValue1;
            }
        }
        else if(brInsetsParametersCount == 2){
            brInsetsDuration3 = brInsetsDuration1;
            brInsetsDuration4 = brInsetsDuration2;
            brInsetsInterpolator3 = brInsetsInterpolator1;
            brInsetsInterpolator4 = brInsetsInterpolator2;
            if(brInsetsUnit1.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue1 = component.getPrefHeight() * (brInsetsDoubleValue1 / 100);
                brInsetsEndValue3 = brInsetsEndValue1;
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setTopBottomBorderInsets(component, (double)newVal * (brInsetsDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BR_TOP_BOTTOM_INSETS, MainProps.HEIGHT, heightListener);
            }
            else if(brInsetsUnit1.equals(FUnit.PIXELS)){
                brInsetsEndValue1 = brInsetsDoubleValue1;
                brInsetsEndValue3 = brInsetsDoubleValue1;
            }
            if(brInsetsUnit2.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue2 = component.getPrefWidth() * (brInsetsDoubleValue2 / 100);
                brInsetsEndValue4 = brInsetsEndValue2;
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setRightLeftBorderInsets(component, (double)newVal * (brInsetsDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RIGHT_LEFT_INSETS, MainProps.WIDTH, widthListener);
            }
            else if(brInsetsUnit1.equals(FUnit.PIXELS)){
                brInsetsEndValue2 = brInsetsDoubleValue2;
                brInsetsEndValue4 = brInsetsDoubleValue2;
            }
        }
        else if(brInsetsParametersCount == 4){
            if(brInsetsUnit1.equals(FUnit.PERCENTAGE) && brInsetsUnit3.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue1 = component.getPrefHeight() * (brInsetsDoubleValue1 / 100);
                brInsetsEndValue3 = component.getPrefHeight() * (brInsetsDoubleValue3 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setTopBottomBorderInsets(component, (double)newVal * (brInsetsDoubleValue1 / 100), (double)newVal * (brInsetsDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BR_TOP_BOTTOM_INSETS, MainProps.HEIGHT, heightListener);
            }
            else if(brInsetsUnit1.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue1 = component.getPrefHeight() * (brInsetsDoubleValue1 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setTopBorderInset(component, (double)newVal * (brInsetsDoubleValue1 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BR_TOP_INSET, MainProps.HEIGHT, heightListener);
            }
            else if(brInsetsUnit3.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue3 = component.getPrefHeight() * (brInsetsDoubleValue3 / 100);
                ChangeListener<Number> heightListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setBottomBorderInset(component, (double)newVal * (brInsetsDoubleValue3 / 100));
                };
                listenersManager.add(component, ListenerID.HEIGHT_BR_BOTTOM_INSET, MainProps.HEIGHT, heightListener);
            }
            if(brInsetsUnit1.equals(FUnit.PIXELS)){
                brInsetsEndValue1 = brInsetsDoubleValue1;
            }
            if(brInsetsUnit3.equals(FUnit.PIXELS)){
                brInsetsEndValue3 = brInsetsDoubleValue3;
            }
            if(brInsetsUnit2.equals(FUnit.PERCENTAGE) && brInsetsUnit4.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue2 = component.getPrefWidth() * (brInsetsDoubleValue2 / 100);
                brInsetsEndValue4 = component.getPrefWidth() * (brInsetsDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setRightLeftBorderInsets(component, (double)newVal * (brInsetsDoubleValue2 / 100), (double)newVal * (brInsetsDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RIGHT_LEFT_INSETS, MainProps.WIDTH, widthListener);
            }
            else if(brInsetsUnit2.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue2 = component.getPrefWidth() * (brInsetsDoubleValue2 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setRightBorderInset(component, (double)newVal * (brInsetsDoubleValue2 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_RIGHT_INSET, MainProps.WIDTH, widthListener);
            }
            else if(brInsetsUnit4.equals(FUnit.PERCENTAGE)){
                brInsetsEndValue4 = component.getPrefWidth() * (brInsetsDoubleValue4 / 100);
                ChangeListener<Number> widthListener = (val, oldVal, newVal) -> {
                    stopTempBrInsetsAnimations();
                    BorderSetter.setLeftBorderInset(component, (double)newVal * (brInsetsDoubleValue4 / 100));
                };
                listenersManager.add(component, ListenerID.WIDTH_BR_LEFT_INSET, MainProps.WIDTH, widthListener);
            }
            if(brInsetsUnit2.equals(FUnit.PIXELS)){
                brInsetsEndValue2 = brInsetsDoubleValue2;
            }
            if(brInsetsUnit4.equals(FUnit.PIXELS)){
                brInsetsEndValue4 = brInsetsDoubleValue4;
            }
        }
        if(brInsetsDuration1 > 0){
            addTempAnimation(component, FAnimations.BR_TOP_INSET, brInsetsStartValue1, brInsetsEndValue1, brInsetsDoubleValue1, brInsetsDuration1, brInsetsInterpolator1);
        }
        else{
            BorderSetter.setTopBorderInset(component, brInsetsEndValue1);
        }
        if(brInsetsDuration2 > 0){
            addTempAnimation(component, FAnimations.BR_RIGHT_INSET, brInsetsStartValue2, brInsetsEndValue2, brInsetsDoubleValue2, brInsetsDuration2, brInsetsInterpolator2);
        }
        else{
            BorderSetter.setRightBorderInset(component, brInsetsEndValue2);
        }
        if(brInsetsDuration3 > 0){
            addTempAnimation(component, FAnimations.BR_BOTTOM_INSET, brInsetsStartValue3, brInsetsEndValue3, brInsetsDoubleValue3, brInsetsDuration3, brInsetsInterpolator3);
        }
        else{
            BorderSetter.setBottomBorderInset(component, brInsetsEndValue3);
        }
        if(brInsetsDuration4 > 0){
            addTempAnimation(component, FAnimations.BR_LEFT_INSET, brInsetsStartValue4, brInsetsEndValue4, brInsetsDoubleValue4, brInsetsDuration4, brInsetsInterpolator4);
        }
        else{
            BorderSetter.setLeftBorderInset(component, brInsetsEndValue4);
        }
        updateFourSidesSizeValuesInValuesManager(MainProps.BR_INSETS, brInsetsEndValue1, brInsetsEndValue2, brInsetsEndValue3, brInsetsEndValue4);
        updateFourSidesAnimationStartValue(clickAnimation, staticClickAnimation, FAnimations.BR_INSETS, brInsetsEndValue1, brInsetsEndValue2, brInsetsEndValue3, brInsetsEndValue4);
        updateFourSidesAnimationStartValue(hoverAnimation, staticHoverAnimation, FAnimations.BR_INSETS, brInsetsEndValue1, brInsetsEndValue2, brInsetsEndValue3, brInsetsEndValue4);
    }

    private void updateFourSidesAnimationStartValue(FAnimation fAnimation, StaticAnimation staticAnimation, String animationType, double val1, double val2, double val3, double val4){
        if(multipleSidesAnimations.containsKey(animationType)){
            String multipleSideAnim1 = multipleSidesAnimations.get(animationType)[0];
            String multipleSideAnim2 = multipleSidesAnimations.get(animationType)[1];
            String multipleSideAnim3 = multipleSidesAnimations.get(animationType)[2];
            String multipleSideAnim4 = multipleSidesAnimations.get(animationType)[3];
            updateAnimationStartValue(fAnimation, multipleSideAnim1, val1);
            updateAnimationStartValue(fAnimation, multipleSideAnim2, val2);
            updateAnimationStartValue(fAnimation, multipleSideAnim3, val3);
            updateAnimationStartValue(fAnimation, multipleSideAnim4, val4);
            updateStaticAnimationStartValue(staticAnimation, multipleSideAnim1, val1);
            updateStaticAnimationStartValue(staticAnimation, multipleSideAnim2, val1);
            updateStaticAnimationStartValue(staticAnimation, multipleSideAnim3, val1);
            updateStaticAnimationStartValue(staticAnimation, multipleSideAnim4, val1);
        }
    }
    
    private void updateBorderStartColorAnimation(FAnimation animation, StaticAnimation staticAnimation, String color1, String color2, String color3, String color4){
        String multipleSideAnim1 = multipleSidesAnimations.get(FAnimations.BR_COLOR)[0];
        String multipleSideAnim2 = multipleSidesAnimations.get(FAnimations.BR_COLOR)[1];
        String multipleSideAnim3 = multipleSidesAnimations.get(FAnimations.BR_COLOR)[2];
        String multipleSideAnim4 = multipleSidesAnimations.get(FAnimations.BR_COLOR)[3];
        updateAnimationStartStringValue(animation, multipleSideAnim1, color1);
        updateAnimationStartStringValue(animation, multipleSideAnim2, color2);
        updateAnimationStartStringValue(animation, multipleSideAnim3, color3);
        updateAnimationStartStringValue(animation, multipleSideAnim4, color4);
        updateStaticAnimationStartStringValue(staticAnimation, multipleSideAnim1, color1);
        updateStaticAnimationStartStringValue(staticAnimation, multipleSideAnim2, color2);
        updateStaticAnimationStartStringValue(staticAnimation, multipleSideAnim3, color3);
        updateStaticAnimationStartStringValue(staticAnimation, multipleSideAnim4, color4);
    }

    private void updateBorderStartOpacityAnimation(FAnimation animation, StaticAnimation staticAnimation, double opacity1, double opacity2, double opacity3, double opacity4){
        updateAnimationStartValue(animation, FAnimations.BR_TOP_OPACITY, opacity1);
        updateAnimationStartValue(animation, FAnimations.BR_RIGHT_OPACITY, opacity2);
        updateAnimationStartValue(animation, FAnimations.BR_BOTTOM_OPACITY, opacity3);
        updateAnimationStartValue(animation, FAnimations.BR_LEFT_OPACITY, opacity4);
        updateStaticAnimationStartValue(staticAnimation, FAnimations.BR_TOP_OPACITY, opacity1);
        updateStaticAnimationStartValue(staticAnimation, FAnimations.BR_RIGHT_OPACITY, opacity2);
        updateStaticAnimationStartValue(staticAnimation, FAnimations.BR_BOTTOM_OPACITY, opacity3);
        updateStaticAnimationStartValue(staticAnimation, FAnimations.BR_LEFT_OPACITY, opacity4);
    }

    public void updateBorderStyleStaticAnimationStartValue (StaticAnimation staticAnimation){
        if(staticAnimation != null){
            staticAnimation.updateStartStringValue(StaticAnimations.BR_TOP_STYLE, parser.getBorderStyle(0));
            staticAnimation.updateStartStringValue(StaticAnimations.BR_RIGHT_STYLE, parser.getBorderStyle(1));
            staticAnimation.updateStartStringValue(StaticAnimations.BR_BOTTOM_STYLE, parser.getBorderStyle(2));
            staticAnimation.updateStartStringValue(StaticAnimations.BR_LEFT_STYLE, parser.getBorderStyle(3));
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

    private void addTempBrColorAnimation (Region component, String animationType, String startColor, String endColor, long duration, String interpolator){
        FAnimation tempAnimation = new FAnimation(component);
        tempAnimations.put(animationType, tempAnimation);
        tempAnimation.add(animationType, startColor, Duration.ZERO, interpolator);
        tempAnimation.add(animationType, endColor, Duration.millis(duration), interpolator);
        valuesManager.set(animationType, endColor);
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
        tempAnimationHasBeenStopped.put(animationType, false);
    }

    private void addTempBrOpacityAnimation (Region component, String animationType, double startOpacity, double endOpacity, long duration, String interpolator){
        FAnimation tempAnimation = new FAnimation(component);
        tempAnimations.put(animationType, tempAnimation);
        tempAnimation.add(animationType, startOpacity, Duration.ZERO, interpolator);
        tempAnimation.add(animationType, endOpacity, Duration.millis(duration), interpolator);
        valuesManager.set(animationType, endOpacity);
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
        tempAnimationHasBeenStopped.put(animationType, false);
    }

    private void updateFourSidesSizeValuesInValuesManager (String mainProp, double value1, double value2, double value3, double value4){
        String[] props = multipleSidesProps.get(mainProp);
        if(props != null){
            valuesManager.set(props[0], value1);
            valuesManager.set(props[1], value2);
            valuesManager.set(props[2], value3);
            valuesManager.set(props[3], value4);
        }
    }

    private void updateFourSidesStringValuesInValuesManager (String mainProp, String value1, String value2, String value3, String value4){
        String[] props = multipleSidesProps.get(mainProp);
        if(props != null){
            valuesManager.set(props[0], value1);
            valuesManager.set(props[1], value2);
            valuesManager.set(props[2], value3);
            valuesManager.set(props[3], value4);
        }
    }
    
    private void updateBorderColorsInValuesManager (String topColor, String rightColor, String bottomColor, String leftColor){
        valuesManager.set(MainProps.BR_TOP_COLOR, topColor);
        valuesManager.set(MainProps.BR_RIGHT_COLOR, rightColor);
        valuesManager.set(MainProps.BR_BOTTOM_COLOR, bottomColor);
        valuesManager.set(MainProps.BR_LEFT_COLOR, leftColor);
    }

    private void updateBorderOpacitiesInValuesManager (double topOpacity, double rightOpacity, double bottomOpacity, double leftOpacity){
        valuesManager.set(MainProps.BR_TOP_OPACITY, topOpacity);
        valuesManager.set(MainProps.BR_RIGHT_OPACITY, rightOpacity);
        valuesManager.set(MainProps.BR_BOTTOM_OPACITY, bottomOpacity);
        valuesManager.set(MainProps.BR_LEFT_OPACITY, leftOpacity);
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

    private void stopTempBgInsetsAnimations(){
        if(!bgInsetsAnimationsStopped){
            stopTempAnimation(FAnimations.BG_TOP_INSET);
            stopTempAnimation(FAnimations.BG_RIGHT_INSET);
            stopTempAnimation(FAnimations.BG_BOTTOM_INSET);
            stopTempAnimation(FAnimations.BG_LEFT_INSET);
            bgInsetsAnimationsStopped = true;
        }
    }

    private void stopTempBrColorAnimations(){
        if(!brColorAnimationsStopped){
            stopTempAnimation(FAnimations.BR_TOP_COLOR);
            stopTempAnimation(FAnimations.BR_RIGHT_COLOR);
            stopTempAnimation(FAnimations.BR_BOTTOM_COLOR);
            stopTempAnimation(FAnimations.BR_LEFT_COLOR);
        }
    }

    private void stopTempBrOpacityAnimations(){
        if(!brOpacityAnimationsStopped){
            stopTempAnimation(FAnimations.BR_TOP_OPACITY);
            stopTempAnimation(FAnimations.BR_RIGHT_OPACITY);
            stopTempAnimation(FAnimations.BR_BOTTOM_OPACITY);
            stopTempAnimation(FAnimations.BR_LEFT_OPACITY);
            brOpacityAnimationsStopped = true;
        }
    }

    private void stopTempBrRadiusAnimations(){
        if(!brRadiusAnimationsStopped){
            stopTempAnimation(FAnimations.BR_TL_RADIUS);
            stopTempAnimation(FAnimations.BR_TR_RADIUS);
            stopTempAnimation(FAnimations.BR_BR_RADIUS);
            stopTempAnimation(FAnimations.BR_BL_RADIUS);
            brRadiusAnimationsStopped = true;
        }
    }

    private void stopTempBrWidthAnimations(){
        if(!brWidthAnimationsStopped){
            stopTempAnimation(FAnimations.BR_TOP_WIDTH);
            stopTempAnimation(FAnimations.BR_RIGHT_WIDTH);
            stopTempAnimation(FAnimations.BR_BOTTOM_WIDTH);
            stopTempAnimation(FAnimations.BR_LEFT_WIDTH);
            brWidthAnimationsStopped = true;
        }
    }

    private void stopTempBrInsetsAnimations(){
        if(!brInsetsAnimationsStopped){
            stopTempAnimation(FAnimations.BR_TOP_INSET);
            stopTempAnimation(FAnimations.BR_RIGHT_INSET);
            stopTempAnimation(FAnimations.BR_BOTTOM_INSET);
            stopTempAnimation(FAnimations.BR_LEFT_INSET);
            brInsetsAnimationsStopped = true;
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

    public void updateAnimationStartStringValue (FAnimation fAnimation, String animationType, String color){
        if(fAnimation != null){
            fAnimation.updateStartColor(animationType, color);
        }
    }

    public void updateAnimationEndStringValue(FAnimation fAnimation, String animationType, String color){
        if(fAnimation != null){
            fAnimation.updateEndColor(animationType, color);
        }
    }

    public void updateStaticAnimationStartStringValue (StaticAnimation staticAnimation, String animationType, String color){
        if(staticAnimation != null){
            staticAnimation.updateStartStringValue(animationType, color);
        }
    }

    public void updateStaticAnimationEndStringValue(StaticAnimation staticAnimation, String animationType, String color){
        if(staticAnimation != null){
            staticAnimation.updateEndStringValue(animationType, color);
        }
    }

    /**
     * Updates an existing FAnimation start value.
     *
     * @param fAnimation     the fAnimation type the value will be updated for(e.g. FAnimations.LAYOUT_X)
     * @param animationType    the <strong>FAnimation</strong> to be updated
     * @param newStartValue the new value the fAnimation will start from
     */
    public void updateAnimationStartValue (FAnimation fAnimation, String animationType, double newStartValue) {
        if(fAnimation != null){
            fAnimation.updateStartValue(animationType, newStartValue);
        }
    }

    public void updateAnimationEndValue(FAnimation fAnimation, String animationType, double value){
        if(fAnimation != null){
            fAnimation.updateEndValue(animationType, value);
        }
    }

    public void updateStaticAnimationStartValue(StaticAnimation staticAnimation, String animationType, double value){
        if(staticAnimation != null){
            staticAnimation.updateStartDoubleValue(animationType, value);
        }
    }

    public void updateStaticAnimationEndValue(StaticAnimation staticAnimation, String animationType, double value){
        if(staticAnimation != null){
            staticAnimation.updateEndDoubleValue(animationType, value);
        }
    }
}
