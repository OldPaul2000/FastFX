package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.StaticAnimation;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

/**
 * Helps manage the animations and makes the PropertyManager class cleaner.
 */
public class PropertyAnimationHelper {
    public static final String NUMBER_ANIMATION = "number";
    public static final String COLOR_ANIMATION = "color";

    private Region component;
    private FAnimation fAnimation;
    private Map<String, FAnimation> tempAnimations;
    private Map<FAnimation, Boolean> tempAnimationHasBeenStopped;
    private StaticAnimation staticAnimation;
    private ValuesManager valuesManager;
    private String animationType;
    private String staticAnimationType;
    private String property;
    private String startColor;
    private String endColor;
    private double startValue;
    private double endValue;
    private long duration;
    private String interpolator;

    public PropertyAnimationHelper (Region component, ValuesManager valuesManager) {
        this.tempAnimations = new HashMap<>();
        this.tempAnimationHasBeenStopped = new HashMap<>();
        this.valuesManager = valuesManager;
        this.component = component;
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
        if (valuesManager.contains(property)) {
            if (type.equals(NUMBER_ANIMATION)) {
                startValue = valuesManager.getDouble(property);
            } else if (type.equals(COLOR_ANIMATION)) {
                startColor = valuesManager.getString(property);
            }
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
        if (valuesManager.contains(property)) {
            if (type.equals(NUMBER_ANIMATION)) {
                startValue = valuesManager.getDouble(property);
            } else if (type.equals(COLOR_ANIMATION)) {
                startColor = valuesManager.getString(property);
            }
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
        tempAnimationHasBeenStopped.put(tempAnimation, false);
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
            if(tempAnimationHasBeenStopped.containsKey(tempAnimation) && !tempAnimationHasBeenStopped.get(tempAnimation)){
                tempAnimation.stop();
                tempAnimationHasBeenStopped.put(tempAnimation, true);
                tempAnimations.remove(animationId);
            }
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
}
