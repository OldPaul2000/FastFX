package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.style.animations.FAnimation;
import com.example.fastfx.src.style.animations.StaticAnimation;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.Map;

public class PropertyUtil {

    public static void setOneDimensionAnimation (double initialValue, Map<String, String> initialValues, String property,
                                                 FAnimation animation, String animationType, double value, long duration){
        if(initialValues.containsKey(property)){
            initialValue = Double.parseDouble(initialValues.get(property));
        }
        if(duration == 0){
            duration = 1;
        }
        animation.add(animationType, initialValue, Duration.ZERO);
        animation.add(animationType, value, Duration.millis(duration));
    }

    public static void setOneDimensionAnimation2 (double initialValue, Map<String, String> initialValues, String property,
                                                 FAnimation animation, String animationType, StaticAnimation staticAnimation, String staticAnimationType,
                                                  double value, long duration){
        if(initialValues.containsKey(property)){
            initialValue = Double.parseDouble(initialValues.get(property));
        }
        if(duration == 0){
            animation.removeAnimation(animationType);
            staticAnimation.addAnimation(staticAnimationType, initialValue, value);
        }
        else{
            staticAnimation.removeAnimation(staticAnimationType);
            animation.add(animationType, initialValue, Duration.ZERO);
            animation.add(animationType, value, Duration.millis(duration));
        }
    }

    public static void setTempAnimation(Region component, double initialValue, Map<String, String> initialValues, String property,
                                        String animationType, double value, long duration){
        FAnimation tempAnimation = new FAnimation(component);
        if(duration == 0){
            duration = 1;
        }
        tempAnimation.add(animationType, initialValue, Duration.ZERO);
        tempAnimation.add(animationType, value, Duration.millis(duration));
        initialValues.put(property, value + "");
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
    }

    public static void setColorAnimation (String initialValue, Map<String, String> initialValues, String property,
                                          FAnimation animation, String animationType, String value, long duration){
        if(initialValues.containsKey(property)){
            initialValue = initialValues.get(property);
        }
        if(duration == 0){
            duration = 1;
        }
        animation.add(animationType, initialValue, Duration.ZERO);
        animation.add(animationType, value, Duration.millis(duration));
    }

    public static void setTempColorAnimation(Region component, String initialValue, Map<String, String> initialValues, String property,
                                        String animationType, String value, long duration){
        FAnimation tempAnimation = new FAnimation(component);
        if(duration == 0){
            duration = 1;
        }
        tempAnimation.add(animationType, initialValue, Duration.ZERO);
        tempAnimation.add(animationType, value, Duration.millis(duration));
        initialValues.put(property, value);
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
    }

    public static void setStaticAnimation(String initialValue, Map<String, String> initialValues, String property,
                                          StaticAnimation animation, String animationType, String value){
        if(initialValues.containsKey(property)){
            initialValue = initialValues.get(property);
        }
        animation.addAnimation(animationType, initialValue, value);
    }
}
