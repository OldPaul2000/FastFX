package com.example.fastfx.src.fcomponents.propertyutil;

import com.example.fastfx.src.style.animations.FAnimation;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.Map;

public class PropertyUtil {

    public static void setOneDimensionProperty(double initialValue, Map<String, String> initialValues, String property,
                                        FAnimation animation, String animationType, double value, long duration){
        if(initialValues.containsKey(property)){
            initialValue = Double.parseDouble(initialValues.get(property));
        }
        animation.add(animationType, initialValue, Duration.ZERO);
        animation.add(animationType, value, Duration.millis(duration));
    }

    public static void setColorProperty(String initialValue, Map<String, String> initialValues, String property,
                                               FAnimation animation, String animationType, String value, long duration){
        if(initialValues.containsKey(property)){
            initialValue = initialValues.get(property);
        }
        animation.add(animationType, initialValue, Duration.ZERO);
        animation.add(animationType, value, Duration.millis(duration));
    }

    public static void setTempAnimation(Region component, double initialValue, Map<String, String> initialValues, String property,
                                        String animationType, double value, long duration){
        FAnimation tempAnimation = new FAnimation(component);
        tempAnimation.add(animationType, initialValue, Duration.ZERO);
        tempAnimation.add(animationType, value, Duration.millis(duration));
        initialValues.put(property, value + "");
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
    }

    public static void setTempColorAnimation(Region component, String initialValue, Map<String, String> initialValues, String property,
                                        String animationType, String value, long duration){
        FAnimation tempAnimation = new FAnimation(component);
        tempAnimation.add(animationType, initialValue, Duration.ZERO);
        tempAnimation.add(animationType, value, Duration.millis(duration));
        initialValues.put(property, value);
        tempAnimation.destroyOnFinished();
        tempAnimation.play();
    }

}
