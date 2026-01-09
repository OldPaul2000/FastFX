package com.example.fastfx.src.style.animations;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will be used for adding and removing every type of animation.
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

    public FAnimation(Region element) {
        this.element = element;
        this.rate = 1;
        this.startListeners = new ArrayList<>();
        this.endListeners = new ArrayList<>();
        this.timeline = new Timeline();
        this.keyFrameBuilder = new KeyFrameBuilder();
        this.currentAnimations = new HashMap<>();
    }

    public void destroyOnFinished(){
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
    public void setElement(Region element) {
        this.element = element;
    }

    /**
     * Set the rate of the animation. The rate represents the speed of animation playing.
     * For example if the rate is set to 2 the animation will run 2 times faster(1s -> 0.5s)
     */
    public void setRate(double rate) {
        this.rate = Math.abs(rate);
    }

    /**
     * Specifies if this animation will run infinitely
     */
    public void setInfinite(boolean infinite) {
        if (infinite) {
            timeline.setCycleCount(Animation.INDEFINITE);
        }
        else {
            timeline.setCycleCount(1);
        }
        setAutoReverse(infinite);
    }

    /**
     * Specifies if this animation will play back to starting point,
     * or it will jump straight to the starting point.
     */
    public void setAutoReverse(boolean autoReverse) {
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
    public void add(String fAnimation, String value, Duration duration) {
        add(fAnimation, value, duration, "linear");
    }

    public void add(String fAnimation, String value, Duration duration, String fInterpolator) {
        String animation = FAnimations.get(fAnimation);
        Interpolator interpolator = FInterpolator.get(fInterpolator);
        if (animation != null) {
            KeyFrame keyFrame = keyFrameBuilder
                    .setElement(element)
                    .setAnimation(animation)
                    .setStringValue(value)
                    .setDuration(duration)
                    .setInterpolator(interpolator)
                    .setOnFinishedListener((e) -> {
                        if (rate > 0) {
                            KeyFrame sourceKeyFrame = (KeyFrame) e.getSource();
                            int currAnimationDuration = (int) sourceKeyFrame.getTime().toMillis();
                            notifyEndListeners(animation, currAnimationDuration);
                        }
                    })
                    .buildFromStrVal();
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
    public void add(String fAnimation, double value, Duration duration) {
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
    public void add(String fAnimation, double value, Duration duration, String fInterpolator) {
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
            KeyFrame keyFrame = keyFrameBuilder
                    .setElement(element)
                    .setAnimation(animation)
                    .setDoubleValue(value)
                    .setDuration(duration)
                    .setEffect(element.getEffect())
                    .setInterpolator(interpolator)
                    .setOnFinishedListener((e) -> {
                        if (rate > 0) {
                            KeyFrame sourceKeyFrame = (KeyFrame) e.getSource();
                            int currAnimationDuration = (int) sourceKeyFrame.getTime().toMillis();
                            notifyEndListeners(animation, currAnimationDuration);
                        }
                    })
                    .buildFromDoubleVal();
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
        }
        else if (animation != null && !animation.equals(FAnimations.BG_COLOR) && !animation.equals(FAnimations.BR_COLOR)) {
            KeyFrame keyFrame = keyFrameBuilder
                    .setElement(element)
                    .setAnimation(animation)
                    .setDoubleValue(value)
                    .setDuration(duration)
                    .setInterpolator(interpolator)
                    .setOnFinishedListener((e) -> {
                        if (rate > 0) {
                            KeyFrame sourceKeyFrame = (KeyFrame) e.getSource();
                            int currAnimationDuration = (int) sourceKeyFrame.getTime().toMillis();
                            notifyEndListeners(animation, currAnimationDuration);
                        }
                    })
                    .buildFromDoubleVal();
            timeline.getKeyFrames().add(keyFrame);
            addToCurrentAnimation(animation, keyFrame);
        }
    }

    /**
     * Adds a created keyframe in currentAnimations for the specific animation.
     */
    private void addToCurrentAnimation(String animation, KeyFrame keyFrame) {
        if (!currentAnimations.containsKey(animation)) {
            currentAnimations.put(animation, new ArrayList<>());
            currentAnimations.get(animation).add(keyFrame);
        }
        else {
            currentAnimations.get(animation).add(keyFrame);
        }
    }

    /**
     * Removes the transition for the specified fAnimation.
     */
    public void removeAnimation(String fAnimation) {
        String animation = FAnimations.get(fAnimation);
        if (animation != null && currentAnimations.containsKey(animation)) {
            currentAnimations.get(animation).forEach(keyFrame -> {
                timeline.getKeyFrames().remove(keyFrame);
            });
            currentAnimations.remove(animation);
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
    private void notifyStartListeners() {
        for (int i = 0; i < startListeners.size(); i++) {
            startListeners.get(i).apply();
        }
    }

    /**
     * Notifies the added listeners when the animation ends.
     */
    private void notifyEndListeners(String currEndedAnimation, int currDuration) {
        for (int i = 0; i < endListeners.size(); i++) {
            endListeners.get(i).apply(currEndedAnimation, currDuration);
        }
    }

    /**
     * Add a listener which gets notified on animation start.
     */
    public void addStartListener(StartAnimationListener listener) {
        startListeners.add(listener);
    }

    /**
     * Add a listener which gets notified on animation end.
     */
    public void addEndListener(EndAnimationListener listener) {
        endListeners.add(listener);
    }

    /**
     * Removes an animation listener.
     */
    public void removeListener(StartAnimationListener listener) {
        startListeners.remove(listener);
    }

    /**
     * Removes an animation listener.
     */
    public void removeListener(EndAnimationListener listener) {
        endListeners.add(listener);
    }

    /**
     * Plays the animation.
     */
    public void play() {
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
    public void reverse() {
        rate = -Math.abs(rate);
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
    public void stop() {
        timeline.stop();
    }
}
