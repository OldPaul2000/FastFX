package com.example.fastfx.src.fcomponents.property;

import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.StaticAnimation;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.Map;

public class PropertyAnimationHelper {

    public static final String NUMBER_ANIMATION = "number";
    public static final String COLOR_ANIMATION = "color";

    private Region component;
    private FAnimation animation;
    private StaticAnimation staticAnimation;
    private Map<String, String> initialValues;
    private String animationType;
    private String staticAnimationType;
    private String property;
    private String oldColor;
    private String newColor;
    private double oldValue;
    private double newValue;
    private long duration;
    private String interpolator;

    public PropertyAnimationHelper (Region component, Map<String, String> initialValues) {
        this.initialValues = initialValues;
        this.component = component;
    }

    public void setAnimations(FAnimation animation, StaticAnimation staticAnimation){
        this.animation = animation;
        this.staticAnimation = staticAnimation;
    }

    public void setStaticAnimation(StaticAnimation staticAnimation){
        this.staticAnimation = staticAnimation;
    }

    public void setAnimationsTypes(String animationType, String staticAnimationType){
        this.animationType = animationType;
        this.staticAnimationType = staticAnimationType;
    }

    public void setProperty(String property){
        this.property = property;
    }

    public void setColors(String initialColor, String newColor){
        this.oldColor = initialColor;
        this.newColor = newColor;
    }

    public void setValues(double initialValue, double newValue){
        this.oldValue = initialValue;
        this.newValue = newValue;
    }

    public void setDuration(long duration){
        this.duration = duration;
    }

    public void setInterpolator(String interpolator){
        this.interpolator = interpolator;
    }

    public void addAnimation (String type){
        if(initialValues.containsKey(property)){
            if(type.equals(NUMBER_ANIMATION)){
                oldValue = Double.parseDouble(initialValues.get(property));
            }
            else if (type.equals(COLOR_ANIMATION)) {
                oldColor = initialValues.get(property);
            }
        }
        if(type.equals(NUMBER_ANIMATION)){
            animation.removeAnimation(animationType);
            if(duration == 0){
                staticAnimation.addAnimation(staticAnimationType, oldValue, newValue);
            }
            else{
                staticAnimation.removeAnimation(staticAnimationType);
                animation.add(animationType, oldValue, Duration.ZERO, interpolator);
                animation.add(animationType, newValue, Duration.millis(duration), interpolator);
            }
        }
        else if(type.equals(COLOR_ANIMATION)){
            animation.removeAnimation(animationType);
            if(duration == 0){
                staticAnimation.addAnimation(staticAnimationType, oldColor, newColor);
            }
            else{
                staticAnimation.removeAnimation(staticAnimationType);
                animation.add(animationType, oldColor, Duration.ZERO, interpolator);
                animation.add(animationType, newColor, Duration.millis(duration), interpolator);
            }
        }
    }

    public void addTempAnimation (String type){
        if(initialValues.containsKey(property)){
            if(type.equals(NUMBER_ANIMATION)){
                oldColor = initialValues.get(property);
            }
            else if (type.equals(COLOR_ANIMATION)) {
                oldValue = Double.parseDouble(initialValues.get(property));
            }
        }
        FAnimation tempAnimation = new FAnimation(component);
        if(type.equals(NUMBER_ANIMATION)){
            tempAnimation.add(animationType, oldValue, Duration.ZERO, interpolator);
            tempAnimation.add(animationType, newValue, Duration.millis(duration), interpolator);
            initialValues.put(property, newValue + "");
        }
        else if(type.equals(COLOR_ANIMATION)){
            tempAnimation.add(animationType, oldColor, Duration.ZERO, interpolator);
            tempAnimation.add(animationType, newColor, Duration.millis(duration), interpolator);
            initialValues.put(property, newColor);
        }
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
    }

    public void addStaticAnimation (String type){
        animation.removeAnimation(animationType);
        String initValue = "";
        if(initialValues.containsKey(property)){
            initValue = initialValues.get(property);
        }
        if(type.equals(NUMBER_ANIMATION)){
            staticAnimation.addAnimation(animationType, Double.parseDouble(initValue), newValue);
        }
        else if(type.equals(COLOR_ANIMATION)) {
            staticAnimation.addAnimation(animationType, initValue, newColor);
        }
    }

    public void updateAnimation(){

    }
}
