package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.StaticAnimation;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

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
    private String oldColor;
    private String newColor;
    private double oldValue;
    private double newValue;
    private long duration;
    private String interpolator;

    public PropertyAnimationHelper (Region component, ValuesManager valuesManager) {
        this.tempAnimations = new HashMap<>();
        this.tempAnimationHasBeenStopped = new HashMap<>();
        this.valuesManager = valuesManager;
        this.component = component;
    }

    public void setAnimations (FAnimation fAnimation, StaticAnimation staticAnimation) {
        this.fAnimation = fAnimation;
        this.staticAnimation = staticAnimation;
    }

    public void setAnimation(FAnimation fAnimation){
        this.fAnimation = fAnimation;
    }

    public void setStaticAnimation (StaticAnimation staticAnimation) {
        this.staticAnimation = staticAnimation;
    }

    public void setAnimationsTypes (String fAnimationType, String staticAnimationType) {
        this.animationType = fAnimationType;
        this.staticAnimationType = staticAnimationType;
    }

    public void setAnimationType(String fAnimationType){
        this.animationType = fAnimationType;
    }

    public void setStaticAnimationType(String animationType){
        this.staticAnimationType = animationType;
    }

    public void setProperty (String property) {
        this.property = property;
    }

    public void setColors (String initialColor, String newColor) {
        this.oldColor = initialColor;
        this.newColor = newColor;
    }

    public void setValues (double initialValue, double newValue) {
        this.oldValue = initialValue;
        this.newValue = newValue;
    }

    public void setDuration (long duration) {
        this.duration = duration;
    }

    public void setInterpolator (String interpolator) {
        this.interpolator = interpolator;
    }

    public void addAnimation (String type) {
        if (valuesManager.contains(property)) {
            if (type.equals(NUMBER_ANIMATION)) {
                oldValue = valuesManager.getDouble(property);
            } else if (type.equals(COLOR_ANIMATION)) {
                oldColor = valuesManager.getString(property);
            }
        }
        if (type.equals(NUMBER_ANIMATION)) {
            fAnimation.removeAnimation(animationType);
            if (duration == 0) {
                staticAnimation.addAnimation(staticAnimationType, oldValue, newValue);
            } else {
                staticAnimation.removeAnimation(staticAnimationType);
                fAnimation.add(animationType, oldValue, Duration.ZERO, interpolator);
                fAnimation.add(animationType, newValue, Duration.millis(duration), interpolator);
            }
        } else if (type.equals(COLOR_ANIMATION)) {
            fAnimation.removeAnimation(animationType);
            if (duration == 0) {
                staticAnimation.addAnimation(staticAnimationType, oldColor, newColor);
            } else {
                staticAnimation.removeAnimation(staticAnimationType);
                fAnimation.add(animationType, oldColor, Duration.ZERO, interpolator);
                fAnimation.add(animationType, newColor, Duration.millis(duration), interpolator);
            }
        }
    }

    public void addTempAnimation (String animationId, String type) {
        if (valuesManager.contains(property)) {
            if (type.equals(NUMBER_ANIMATION)) {
                oldValue = valuesManager.getDouble(property);
            } else if (type.equals(COLOR_ANIMATION)) {
                oldColor = valuesManager.getString(property);
            }
        }
        FAnimation tempAnimation = new FAnimation(component);
        tempAnimations.put(animationId, tempAnimation);
        if (type.equals(NUMBER_ANIMATION)) {
            tempAnimation.add(animationType, oldValue, Duration.ZERO, interpolator);
            tempAnimation.add(animationType, newValue, Duration.millis(duration), interpolator);
            valuesManager.set(property, newValue);
        } else if (type.equals(COLOR_ANIMATION)) {
            tempAnimation.add(animationType, oldColor, Duration.ZERO, interpolator);
            tempAnimation.add(animationType, newColor, Duration.millis(duration), interpolator);
            valuesManager.set(property, newColor);
        }
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
        tempAnimationHasBeenStopped.put(tempAnimation, false);
    }

    public void stopTempAnimation(String animationId){
        FAnimation tempAnimation = tempAnimations.get(animationId);
        if(tempAnimation != null){
            if(tempAnimationHasBeenStopped.containsKey(tempAnimation) && !tempAnimationHasBeenStopped.get(tempAnimation)){
                tempAnimation.stop();
                System.out.println("STOP");
                tempAnimationHasBeenStopped.put(tempAnimation, true);
                tempAnimations.remove(animationId);
            }
        }
    }

    public void addStaticAnimation (String type) {
        if(fAnimation != null){
            fAnimation.removeAnimation(animationType);
        }
        if (valuesManager.contains(property)) {
            if (type.equals(NUMBER_ANIMATION)) {
                staticAnimation.addAnimation(staticAnimationType, valuesManager.getDouble(property), newValue);
            } else if (type.equals(COLOR_ANIMATION)) {
                staticAnimation.addAnimation(staticAnimationType, valuesManager.getString(property), newColor);
            }
        }
    }

    public void updateAnimationStartValue (String fAnimation, FAnimation animation, double newStartValue) {
        if(animation != null){
            animation.updateStartValue(fAnimation, newStartValue);
        }
    }
}
