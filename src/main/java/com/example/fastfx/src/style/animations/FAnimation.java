package com.example.fastfx.src.style.animations;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.*;

/**
 * This class will be used for adding and removing every type of animation.
 *
 * @see FAnimations
 */
public class FAnimation {

    private Region element;
    private double rate;
    private List<StartAnimationListener> startListeners;
    private List<EndAnimationListener> endListeners;
    private Timeline timeline;
    private KeyFrameBuilder keyFrameBuilder;
    private Map<String, List<KeyFrame>> currentAnimations;
    private Map<String, DoubleProperty> doubleProperties;
    private Map<String, ObjectProperty<Color>> colorProperties;

    private DropShadow elementShadowEffect;
    /**
     * These properties are used to set the DropShadow radius value on starting
     * and on ending the animation because the shadow animation works by
     * incrementing and decrementing its background opacity instead of radius
     * because of performance concerns(animating the radius makes the animation laggy).
     */
    double lowerRadius = 0;
    double higherRadius = 0;
    double opacityRatio = 0;

    List<Double> shadowRadiusValues;

    public FAnimation (Region element) {
        this.element = element;
        this.rate = 1;
        this.startListeners = new ArrayList<>();
        this.endListeners = new ArrayList<>();
        this.timeline = new Timeline();
        this.keyFrameBuilder = new KeyFrameBuilder();
        this.currentAnimations = new HashMap<>();
        this.doubleProperties = new HashMap<>();
        this.colorProperties = new HashMap<>();
        keyFrameBuilder.setDoubleProperties(doubleProperties);
        keyFrameBuilder.setColorProperties(colorProperties);
    }

    /**
     * Used for temp animations to release the memory after completing its execution
     */
    public void destroyOnFinished () {
        timeline.setOnFinished((e) -> {
            startListeners = null;
            endListeners = null;
            keyFrameBuilder = null;
            currentAnimations.forEach((anim, frames) -> {
                frames.clear();
                frames = null;
            });
            currentAnimations.clear();
            currentAnimations = null;
            elementShadowEffect = null;
            timeline = null;
        });
    }

    /**
     * Set the element which the animation will be applied to.
     */
    public void setElement (Region element) {
        this.element = element;
    }

    /**
     * Set the rate of the animation. The rate represents the speed of animation playing.
     * For example if the rate is set to 2 the animation will run 2 times faster(1s -> 0.5s)
     */
    public void setRate (double rate) {
        this.rate = Math.abs(rate);
    }

    /**
     * Specifies if this animation will run infinitely
     */
    public void setInfinite (boolean infinite) {
        if (infinite) {
            timeline.setCycleCount(Animation.INDEFINITE);
        } else {
            timeline.setCycleCount(1);
        }
        setAutoReverse(infinite);
    }

    /**
     * Specifies if this animation will play back to starting point,
     * or it will jump straight to the starting point.
     */
    public void setAutoReverse (boolean autoReverse) {
        timeline.setAutoReverse(autoReverse);
    }

    /**
     * Add a new animation. See
     *
     * @param fAnimation represents the animation type.
     * @param value      represents the value for the background color(Color, hex or rgb/rgba).
     * @param duration   represents the duration in milliseconds.
     * @Class <strong>FAnimation</strong>
     * for all supported animations.
     */
    public void add (String fAnimation, String value, Duration duration) {
        add(fAnimation, value, duration, "linear");
    }

    /**
     * Add a new animation. See
     *
     * @param fAnimation    represents the animation type
     * @param value         represents the String value for the color animations.
     * @param duration      represents the duration of the animation
     * @param fInterpolator represents the interpolation type
     * @Class <strong>FAnimation</strong>
     * for all supported animations.
     */
    public void add (String fAnimation, String value, Duration duration, String fInterpolator) {
        String animation = FAnimations.get(fAnimation);
        Interpolator interpolator = FInterpolator.get(fInterpolator);
        if (animation != null) {
            KeyFrame keyFrame = keyFrameBuilder.setElement(element).setAnimation(animation).setStringValue(value).setDuration(duration).setInterpolator(interpolator).setOnFinishedListener((e) -> {
                if (rate > 0) {
                    KeyFrame sourceKeyFrame = (KeyFrame) e.getSource();
                    int currAnimationDuration = (int) sourceKeyFrame.getTime().toMillis();
                    notifyEndListeners(animation, currAnimationDuration);
                }
            }).buildFromStrVal();
            timeline.getKeyFrames().add(keyFrame);
            addToCurrentAnimation(animation, keyFrame);
        }
    }

    /**
     * Add a new animation. See
     *
     * @param fAnimation represents the animation type
     * @param value      represents the value for every animation except the background.
     * @param duration   represents the duration of the animation
     * @Class <strong>FAnimation</strong>
     * for all supported animations.
     */
    public void add (String fAnimation, double value, Duration duration) {
        add(fAnimation, value, duration, "linear");
    }

    /**
     * Add a new animation. See
     *
     * @param fAnimation    represents the animation type
     * @param value         represents the value for every animation except the background.
     * @param duration      represents the duration of the animation
     * @param fInterpolator represents the interpolation type
     * @Class <strong>FAnimation</strong>
     * for all supported animations.
     */
    public void add (String fAnimation, double value, Duration duration, String fInterpolator) {
        String animation = FAnimations.get(fAnimation);
        Interpolator interpolator = FInterpolator.get(fInterpolator);
        Effect effect = element.getEffect();
        if (animation != null && effect != null) {
            if (effect.getClass().getSimpleName().equals("DropShadow")) {
                this.elementShadowEffect = (DropShadow) effect;
                if (this.shadowRadiusValues == null) {
                    this.shadowRadiusValues = new ArrayList<>();
                }
                shadowRadiusValues.add(value);
            }
            KeyFrame keyFrame = keyFrameBuilder.setElement(element).setAnimation(animation).setDoubleValue(value).setDuration(duration).setEffect(element.getEffect()).setInterpolator(interpolator).setOnFinishedListener((e) -> {
                if (rate > 0) {
                    KeyFrame sourceKeyFrame = (KeyFrame) e.getSource();
                    int currAnimationDuration = (int) sourceKeyFrame.getTime().toMillis();
                    notifyEndListeners(animation, currAnimationDuration);
                }
            }).buildFromDoubleVal();
            timeline.getKeyFrames().add(keyFrame);
            addToCurrentAnimation(animation, keyFrame);

            timeline.setOnFinished((e) -> {
                if (this.elementShadowEffect != null) {
                    if (rate < 0) {
                        if (shadowRadiusValues.get(0) < shadowRadiusValues.get(1)) {
                            elementShadowEffect.setRadius(shadowRadiusValues.get(0));
                        }
                    }
                }
            });
        } else if (animation != null && !animation.equals(FAnimations.BG_COLOR) && !animation.equals(FAnimations.BR_COLOR)) {
            KeyFrame keyFrame = keyFrameBuilder.setElement(element).setAnimation(animation).setDoubleValue(value).setDuration(duration).setInterpolator(interpolator).setOnFinishedListener((e) -> {
                if (rate > 0) {
                    KeyFrame sourceKeyFrame = (KeyFrame) e.getSource();
                    int currAnimationDuration = (int) sourceKeyFrame.getTime().toMillis();
                    notifyEndListeners(animation, currAnimationDuration);
                }
            }).buildFromDoubleVal();
            timeline.getKeyFrames().add(keyFrame);
            addToCurrentAnimation(animation, keyFrame);
        }
    }

    /**
     * Adds a created keyframe in currentAnimations for the specific animation.
     */
    private void addToCurrentAnimation (String animation, KeyFrame keyFrame) {
        if (!currentAnimations.containsKey(animation)) {
            currentAnimations.put(animation, new ArrayList<>());
        }
        currentAnimations.get(animation).add(keyFrame);
    }

    /**
     * Updates the start value for an existing animation
     */
    public void updateStartValue(String fAnimation, double newValue){
        if (currentAnimations.containsKey(fAnimation)) {
            timeline.stop();
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            double startValue = 0;
            double endValue = 0;
            double duration = kf2.getTime().toMillis();
            String interpolator = FInterpolator.LINEAR;

            Set<KeyValue> keyValues1 = kf1.getValues();
            for(KeyValue kv : keyValues1){
                startValue = (double)kv.getEndValue();
                interpolator = kv.getInterpolator()
                                 .toString()
                                 .substring(13)
                                 .replace("_", "-")
                                 .toLowerCase();
            }
            Set<KeyValue> keyValues2 = kf2.getValues();
            for(KeyValue kv : keyValues2){
                endValue = (double)kv.getEndValue();
            }
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                endValue = startValue;
                duration = kf1.getTime().toMillis();
            }
            removeAnimation(fAnimation);
            add(fAnimation, newValue, Duration.ZERO, interpolator);
            add(fAnimation, endValue, Duration.millis(duration), interpolator);
        }
    }

    /**
     * Updates the end value for an existing animation
     */
    public void updateEndValue(String fAnimation, double newValue){
        if (currentAnimations.containsKey(fAnimation)) {
            timeline.stop();
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            double startValue = 0;
            double endValue = 0;
            double duration = kf2.getTime().toMillis();
            String interpolator = FInterpolator.LINEAR;

            Set<KeyValue> keyValues1 = kf1.getValues();
            for(KeyValue kv : keyValues1){
                startValue = (double)kv.getEndValue();
                interpolator = kv.getInterpolator()
                                 .toString()
                                 .substring(13)
                                 .replace("_", "-")
                                 .toLowerCase();
            }
            Set<KeyValue> keyValues2 = kf2.getValues();
            for(KeyValue kv : keyValues2){
                endValue = (double)kv.getEndValue();
            }
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                startValue = endValue;
                duration = kf1.getTime().toMillis();
            }
            removeAnimation(fAnimation);
            add(fAnimation, startValue, Duration.ZERO, interpolator);
            add(fAnimation, newValue, Duration.millis(duration), interpolator);
        }
    }

    /**
     * Updates the start value and the end value for an existing animation
     */
    public void updateValues(String fAnimation, double startValue, double endValue){
        if (currentAnimations.containsKey(fAnimation)) {
            timeline.stop();
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            double duration = kf2.getTime().toMillis();
            String interpolator = FInterpolator.LINEAR;

            Set<KeyValue> keyValues1 = kf1.getValues();
            for(KeyValue kv : keyValues1){
                interpolator = kv.getInterpolator()
                                 .toString()
                                 .substring(13)
                                 .replace("_", "-")
                                 .toLowerCase();
            }
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                duration = kf1.getTime().toMillis();
            }
            removeAnimation(fAnimation);
            add(fAnimation, startValue, Duration.ZERO, interpolator);
            add(fAnimation, endValue, Duration.millis(duration), interpolator);
        }
    }

    /**
     * Updates the start color for an existing animation
     */
    public void updateStartColor(String fAnimation, String newColor){
        if (currentAnimations.containsKey(fAnimation)) {
            timeline.stop();
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            Color startValue = null;
            Color endValue = null;
            double duration = kf2.getTime().toMillis();
            String interpolator = FInterpolator.LINEAR;

            Set<KeyValue> keyValues1 = kf1.getValues();
            for(KeyValue kv : keyValues1){
                startValue = (Color) kv.getEndValue();
                interpolator = kv.getInterpolator()
                                 .toString()
                                 .substring(13)
                                 .replace("_", "-")
                                 .toLowerCase();
            }
            Set<KeyValue> keyValues2 = kf2.getValues();
            for(KeyValue kv : keyValues2){
                endValue = (Color)kv.getEndValue();
            }
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                endValue = startValue;
                duration = kf1.getTime().toMillis();
            }
            removeAnimation(fAnimation);
            String redHex = Integer.toHexString((int)(endValue.getRed() * 255));
            String greenHex = Integer.toHexString((int)(endValue.getGreen() * 255));
            String blueHex = Integer.toHexString((int)(endValue.getBlue() * 255));
            String endColor = "#" + redHex + greenHex + blueHex;
            add(fAnimation, newColor, Duration.ZERO, interpolator);
            add(fAnimation, endColor, Duration.millis(duration), interpolator);
        }
    }

    /**
     * Updates the end color for an existing animation
     */
    public void updateEndColor(String fAnimation, String newColor){
        if (currentAnimations.containsKey(fAnimation)) {
            timeline.stop();
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            Color startValue = null;
            Color endValue = null;
            double duration = kf2.getTime().toMillis();
            String interpolator = FInterpolator.LINEAR;

            Set<KeyValue> keyValues1 = kf1.getValues();
            for(KeyValue kv : keyValues1){
                startValue = (Color)kv.getEndValue();
                interpolator = kv.getInterpolator()
                                 .toString()
                                 .substring(13)
                                 .replace("_", "-")
                                 .toLowerCase();
            }
            Set<KeyValue> keyValues2 = kf2.getValues();
            for(KeyValue kv : keyValues2){
                endValue = (Color)kv.getEndValue();
            }
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                startValue = endValue;
                duration = kf1.getTime().toMillis();
            }
            removeAnimation(fAnimation);
            String redHex = Integer.toHexString((int)(endValue.getRed() * 255));
            String greenHex = Integer.toHexString((int)(endValue.getGreen() * 255));
            String blueHex = Integer.toHexString((int)(endValue.getBlue() * 255));
            String startColor = "#" + redHex + greenHex + blueHex;
            add(fAnimation, startColor, Duration.ZERO, interpolator);
            add(fAnimation, newColor, Duration.millis(duration), interpolator);
        }
    }

    /**
     * Updates the start color and the end color for an existing animation
     */
    public void updateColors(String fAnimation, String startColor, String endColor){
        if (currentAnimations.containsKey(fAnimation)) {
            timeline.stop();
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            double duration = kf2.getTime().toMillis();
            String interpolator = FInterpolator.LINEAR;

            Set<KeyValue> keyValues1 = kf1.getValues();
            for(KeyValue kv : keyValues1){
                interpolator = kv.getInterpolator()
                                 .toString()
                                 .substring(13)
                                 .replace("_", "-")
                                 .toLowerCase();
            }
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                duration = kf1.getTime().toMillis();
            }
            removeAnimation(fAnimation);
            add(fAnimation, startColor, Duration.ZERO, interpolator);
            add(fAnimation, endColor, Duration.millis(duration), interpolator);
        }
    }

    /**
     * Updates the duration for an existing animation
     */
    public void updateDuration(String fAnimation, long newDuration){
        if (currentAnimations.containsKey(fAnimation)) {
            timeline.stop();
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            double startValue = 0;
            double endValue = 0;
            String interpolator = FInterpolator.LINEAR;

            Set<KeyValue> keyValues1 = kf1.getValues();
            for(KeyValue kv : keyValues1){
                startValue = (double)kv.getEndValue();
                interpolator = kv.getInterpolator()
                                 .toString()
                                 .substring(13)
                                 .replace("_", "-")
                                 .toLowerCase();
            }
            Set<KeyValue> keyValues2 = kf2.getValues();
            for(KeyValue kv : keyValues2){
                endValue = (double)kv.getEndValue();
            }
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                double tempValue = endValue;
                endValue = startValue;
                startValue = tempValue;
            }
            removeAnimation(fAnimation);
            add(fAnimation, startValue, Duration.ZERO, interpolator);
            add(fAnimation, endValue, Duration.millis(newDuration), interpolator);
        }
    }

    /**
     * Updates the interpolator of an existing animation
     */
    public void updateInterpolator(String fAnimation, String newInterpolator){
        if(currentAnimations.containsKey(fAnimation) && FInterpolator.contains(newInterpolator)){
            timeline.stop();
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            double startValue = 0;
            double endValue = 0;
            double duration = kf2.getTime().toMillis();

            Set<KeyValue> keyValues1 = kf1.getValues();
            for(KeyValue kv : keyValues1){
                startValue = (double)kv.getEndValue();
            }
            Set<KeyValue> keyValues2 = kf2.getValues();
            for(KeyValue kv : keyValues2){
                endValue = (double)kv.getEndValue();
            }
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                double tempValue = endValue;
                endValue = startValue;
                startValue = tempValue;
                duration = kf1.getTime().toMillis();
            }
            removeAnimation(fAnimation);
            add(fAnimation, startValue, Duration.ZERO, newInterpolator);
            add(fAnimation, endValue, Duration.millis(duration), newInterpolator);
        }
    }

    /**
     * Returns the start value as an Object for an existing animation
     */
    public Object getStartValue(String fAnimation){
        if(currentAnimations.containsKey(fAnimation)){
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            if(kf1.getTime().toMillis() < kf2.getTime().toMillis()){
                Set<KeyValue> keyValues = kf1.getValues();
                for(KeyValue kv : keyValues){
                    return kv.getEndValue();
                }
            }
            else{
                Set<KeyValue> keyValues = kf2.getValues();
                for(KeyValue kv : keyValues){
                    return kv.getEndValue();
                }
            }
        }
        return null;
    }

    /**
     * Returns the end value as an Object for an existing animation
     */
    public Object getEndValue(String fAnimation){
        if(currentAnimations.containsKey(fAnimation)){
            KeyFrame kf1 = currentAnimations.get(fAnimation).get(0);
            KeyFrame kf2 = currentAnimations.get(fAnimation).get(1);
            if(kf1.getTime().toMillis() > kf2.getTime().toMillis()){
                Set<KeyValue> keyValues = kf1.getValues();
                for(KeyValue kv : keyValues){
                    return kv.getEndValue();
                }
            }
            else{
                Set<KeyValue> keyValues = kf2.getValues();
                for(KeyValue kv : keyValues){
                    return kv.getEndValue();
                }
            }
        }
        return null;
    }

    /**
     * Returns the duration in milliseconds for an existing animation
     */
    public long getDuration(String fAnimation){
        if(currentAnimations.containsKey(fAnimation)){
            double duration1 = currentAnimations.get(fAnimation).get(0).getTime().toMillis();
            double duration2 = currentAnimations.get(fAnimation).get(1).getTime().toMillis();
            if(duration1 > 0){
                return (long) duration1;
            }
            return (long) duration2;
        }
        return -1;
    }

    /**
     * Returns the interpolator for an existing animation
     */
    public String getInterpolator(String fAnimation){
        if(currentAnimations.containsKey(fAnimation)) {
            Set<KeyValue> keyValues = currentAnimations.get(fAnimation).get(0).getValues();
            for(KeyValue kv : keyValues){
                return kv.getInterpolator()
                         .toString()
                         .substring(13)
                         .replace("_", "-")
                         .toLowerCase();
            }
        }
        return FInterpolator.LINEAR;
    }

    public DoubleProperty getDoubleProperty(String fAnimation){
        return doubleProperties.get(fAnimation);
    }

    public ObjectProperty<Color> getColorProperty(String fAnimation){
        return colorProperties.get(fAnimation);
    }

    /**
     * Removes the property from <strong>doubleProperties</strong> or <strong>colorProperties</strong>.<br>
     * The reason why <strong>doubleProperties</strong> and also <strong>colorProperties</strong><br>
     * are mentioned in the method is to prevent if checks to check if the animation type represents a DoubleProperty<br>
     * or and ObjectProperty<Color> and the existing property will be removed from its containing HashMap.
     */
    private void removeProperty(String animationType){
        doubleProperties.remove(animationType);
        colorProperties.remove(animationType);
    }

    private void removePropertyListener(String animationType){
        keyFrameBuilder.removePropertyListener(animationType);
    }

    /**
     * Removes the transition for the specified fAnimation.
     */
    public void removeAnimation (String animationType) {
        String animation = FAnimations.get(animationType);
        if (animation != null && currentAnimations.containsKey(animation)) {
            currentAnimations.get(animation).forEach(keyFrame -> {
                timeline.getKeyFrames().remove(keyFrame);
            });
            currentAnimations.remove(animation);
            removePropertyListener(animation);
            removeProperty(animation);
            if (animation.equals(FAnimations.SHADOW)) {
                lowerRadius = 0;
                higherRadius = 0;
                opacityRatio = 0;
                shadowRadiusValues = null;
                elementShadowEffect = null;
            }
        }
    }

    /**
     * Notifies the added listeners when the animation has been started.
     */
    private void notifyStartListeners () {
        for (int i = 0; i < startListeners.size(); i++) {
            startListeners.get(i).apply();
        }
    }

    /**
     * Notifies the added listeners when the animation ends.
     */
    private void notifyEndListeners (String currEndedAnimation, int currDuration) {
        for (int i = 0; i < endListeners.size(); i++) {
            endListeners.get(i).apply(currEndedAnimation, currDuration);
        }
    }

    /**
     * Add a listener which gets notified on animation start.
     */
    public void addStartListener (StartAnimationListener listener) {
        startListeners.add(listener);
    }

    /**
     * Add a listener which gets notified on animation end.
     */
    public void addEndListener (EndAnimationListener listener) {
        endListeners.add(listener);
    }

    /**
     * Removes an animation listener.
     */
    public void removeListener (StartAnimationListener listener) {
        startListeners.remove(listener);
    }

    /**
     * Removes an animation listener.
     */
    public void removeListener (EndAnimationListener listener) {
        endListeners.add(listener);
    }

    /**
     * Plays the animation.
     */
    public void play () {
        rate = Math.abs(rate);
        timeline.setRate(rate);
        notifyStartListeners();
        if (this.elementShadowEffect != null) {
            if (shadowRadiusValues.get(0) < shadowRadiusValues.get(1)) {
                elementShadowEffect.setRadius(shadowRadiusValues.get(1));
            }
        }
        timeline.play();
    }

    /**
     * Plays the animation backwards to reach the initial point.
     */
    public void reverse () {
        rate = -rate;
        timeline.setRate(rate);
        if (this.elementShadowEffect != null) {
            if (shadowRadiusValues.get(0) > shadowRadiusValues.get(1)) {
                elementShadowEffect.setRadius(shadowRadiusValues.get(0));
            }
        }
        timeline.play();
    }

    /**
     * Stops the animation
     */
    public void stop () {
        if(timeline != null){
            timeline.stop();
        }
    }
}
