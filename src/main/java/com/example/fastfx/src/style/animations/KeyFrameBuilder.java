package com.example.fastfx.src.style.animations;

import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Builder class which helps to create a new KeyFrame
 * based on specific input(type of animation, duration, interpolator).
 */
public class KeyFrameBuilder {

    private Region element;
    private String animation;
    private Duration duration;
    private String stringValue;
    private double doubleValue;
    private Interpolator interpolator;
    private EventHandler<ActionEvent> onFinishedHandler;

    private Effect effect;

    // Background properties
    private ObjectProperty<Color> bgColorProperty;
    private DoubleProperty bgOpacityProperty;
    private DoubleProperty bgRadiusProperty;
    private DoubleProperty bgTopLeftRadiusProperty;
    private DoubleProperty bgTopRightRadiusProperty;
    private DoubleProperty bgBottomRightRadiusProperty;
    private DoubleProperty bgBottomLeftRadiusProperty;

    // Border properties
    private ObjectProperty<Color> brColorProperty;
    private DoubleProperty brOpacityProperty;
    private DoubleProperty brWidthProperty;
    private DoubleProperty brRadiusProperty;
    private DoubleProperty brTopLeftRadiusProperty;
    private DoubleProperty brTopRightRadiusProperty;
    private DoubleProperty brBottomRightRadiusProperty;
    private DoubleProperty brBottomLeftRadiusProperty;

    /**
     * Set the element from which this class can access the properties to animate.
     */
    public KeyFrameBuilder setElement(Region element) {
        this.element = element;
        return this;
    }

    /**
     * Set the animation type.
     *
     * @see FAnimations
     */
    public KeyFrameBuilder setAnimation(String animation) {
        this.animation = animation;
        return this;
    }

    /**
     * Set the animation duration in milliseconds.
     */
    public KeyFrameBuilder setDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Set the animation value using a String.
     * Is used for more complex properties(e.g. color using rgb or hex).
     */
    public KeyFrameBuilder setStringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    /**
     * Set the animation value using a double value.
     * Is used for simple properties(e.g. layoutX).
     */
    public KeyFrameBuilder setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
        return this;
    }

    /**
     * Set the animation Interpolator.
     */
    public KeyFrameBuilder setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    /**
     * Set the effect to animate.
     */
    public KeyFrameBuilder setEffect(Effect effect) {
        this.effect = effect;
        return this;
    }

    /**
     * Set the actions to be performed after this KeyFrame animation finished.
     */
    public KeyFrameBuilder setOnFinishedListener(EventHandler<ActionEvent> e) {
        this.onFinishedHandler = e;
        return this;
    }

    /**
     * Build the KeyFrame taking into account the configurations made for this object.
     * This method will create and animation which value is not a number(e.g. background color).
     */
    public KeyFrame buildFromStrVal() {
        return buildKeyFrame(animation, stringValue, duration);
    }

    /**
     * Build the KeyFrame taking into account the configurations made for this object.
     * This method will create and animation which value is a number(e.g. layoutX).
     */
    public KeyFrame buildFromDoubleVal() {
        return buildKeyFrame(animation, doubleValue, duration);
    }

    /**
     * Builds the KeyFrame using a String value for the property value.
     */
    private KeyFrame buildKeyFrame(String animation, String value, Duration duration) {
        KeyValue keyValue = createKeyValue(animation, value);
        if (onFinishedHandler == null) {
            return new KeyFrame(duration, keyValue);
        }
        return new KeyFrame(duration, onFinishedHandler, keyValue);
    }

    /**
     * Builds the KeyFrame using a double value for the property value.
     */
    private KeyFrame buildKeyFrame(String animation, double value, Duration duration) {
        KeyValue keyValue = createKeyValue(animation, value);
        if (onFinishedHandler == null) {
            return new KeyFrame(duration, keyValue);
        }
        return new KeyFrame(duration, onFinishedHandler, keyValue);
    }

    private KeyValue createKeyValue(String animation, double value) {
        // Regular animations
        if (animation.equals(FAnimations.WIDTH)) {
            return new KeyValue(element.prefWidthProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.HEIGHT)) {
            return new KeyValue(element.prefHeightProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.LAYOUT_X)) {
            return new KeyValue(element.layoutXProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.LAYOUT_Y)) {
            return new KeyValue(element.layoutYProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.ROTATE)) {
            return new KeyValue(element.rotateProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.SCALE_X)) {
            return new KeyValue(element.scaleXProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.SCALE_Y)) {
            return new KeyValue(element.scaleYProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.TRANSLATE_X)) {
            return new KeyValue(element.translateXProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.TRANSLATE_Y)) {
            return new KeyValue(element.translateYProperty(), value, interpolator);
        }
        if (animation.equals(FAnimations.OPACITY)) {
            return new KeyValue(element.opacityProperty(), value, interpolator);
        }

        // Background animations
        if (animation.equals(FAnimations.BG_OPACITY)) {
            value = Math.max(0, Math.min(1, value));
            if (bgOpacityProperty == null) {
                bgOpacityProperty = new SimpleDoubleProperty();
            }
            bgOpacityProperty.addListener((prop, oldVal, newVal) -> {
                BackgroundSetter.setBackgroundOpacity(element, bgOpacityProperty.get());
            });
            return new KeyValue(bgOpacityProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BG_RADIUS)) {
            if (bgRadiusProperty == null) {
                bgRadiusProperty = new SimpleDoubleProperty();
            }
            bgRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BackgroundSetter.setBackgroundRadius(element, bgRadiusProperty.get());
            });
            return new KeyValue(bgRadiusProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BG_TL_RADIUS)) {
            if (bgTopLeftRadiusProperty == null) {
                bgTopLeftRadiusProperty = new SimpleDoubleProperty();
            }
            bgTopLeftRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BackgroundSetter.setTopLeftBackgroundRadius(element, bgTopLeftRadiusProperty.get());
            });
            return new KeyValue(bgTopLeftRadiusProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BG_TR_RADIUS)) {
            if (bgTopRightRadiusProperty == null) {
                bgTopRightRadiusProperty = new SimpleDoubleProperty();
            }
            bgTopRightRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BackgroundSetter.setTopRightBackgroundRadius(element, bgTopRightRadiusProperty.get());
            });
            return new KeyValue(bgTopRightRadiusProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BG_BR_RADIUS)) {
            if (bgBottomRightRadiusProperty == null) {
                bgBottomRightRadiusProperty = new SimpleDoubleProperty();
            }
            bgBottomRightRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BackgroundSetter.setBottomRightBackgroundRadius(element, bgBottomRightRadiusProperty.get());
            });
            return new KeyValue(bgBottomRightRadiusProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BG_BL_RADIUS)) {
            if (bgBottomLeftRadiusProperty == null) {
                bgBottomLeftRadiusProperty = new SimpleDoubleProperty();
            }
            bgBottomLeftRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BackgroundSetter.setBottomLeftBackgroundRadius(element, bgBottomLeftRadiusProperty.get());
            });
            return new KeyValue(bgBottomLeftRadiusProperty, value, interpolator);
        }

        // Border animations
        if (animation.equals(FAnimations.BR_OPACITY)) {
            value = Math.max(0, Math.min(1, value));
            if (brOpacityProperty == null) {
                brOpacityProperty = new SimpleDoubleProperty();
            }
            brOpacityProperty.addListener((prop, oldVal, newVal) -> {
                BorderSetter.setBorderOpacity(element, brOpacityProperty.get());
            });
            return new KeyValue(brOpacityProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BR_WIDTH)) {
            if (brWidthProperty == null) {
                brWidthProperty = new SimpleDoubleProperty();
            }
            brWidthProperty.addListener((prop, oldVal, newVal) -> {
                BorderSetter.setBorderWidth(element, brWidthProperty.get());
            });
            return new KeyValue(brWidthProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BR_RADIUS)) {
            if (brRadiusProperty == null) {
                brRadiusProperty = new SimpleDoubleProperty();
            }
            brRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BorderSetter.setBorderRadius(element, brRadiusProperty.get());
            });
            return new KeyValue(brRadiusProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BR_TL_RADIUS)) {
            if (brTopLeftRadiusProperty == null) {
                brTopLeftRadiusProperty = new SimpleDoubleProperty();
            }
            brTopLeftRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BorderSetter.setTopLeftBorderRadius(element, brTopLeftRadiusProperty.get());
            });
            return new KeyValue(brTopLeftRadiusProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BR_TR_RADIUS)) {
            if (brTopRightRadiusProperty == null) {
                brTopRightRadiusProperty = new SimpleDoubleProperty();
            }
            brTopRightRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BorderSetter.setTopRightBorderRadius(element, brTopRightRadiusProperty.get());
            });
            return new KeyValue(brTopRightRadiusProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BR_BR_RADIUS)) {
            if (brBottomRightRadiusProperty == null) {
                brBottomRightRadiusProperty = new SimpleDoubleProperty();
            }
            brBottomRightRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BorderSetter.setBottomRightBorderRadius(element, brBottomRightRadiusProperty.get());
            });
            return new KeyValue(brBottomRightRadiusProperty, value, interpolator);
        }
        if (animation.equals(FAnimations.BR_BL_RADIUS)) {
            if (brBottomLeftRadiusProperty == null) {
                brBottomLeftRadiusProperty = new SimpleDoubleProperty();
            }
            brBottomLeftRadiusProperty.addListener((prop, oldVal, newVal) -> {
                BorderSetter.setBottomLeftBorderRadius(element, brBottomLeftRadiusProperty.get());
            });
            return new KeyValue(brBottomLeftRadiusProperty, value, interpolator);
        }

        // Shadow animation
        if (animation.equals(FAnimations.SHADOW) && effect != null) {
            if (effect.getClass().getSimpleName().equals("DropShadow")) {
                value = Math.max(0, Math.min(1, value));
                DropShadow shadow = (DropShadow) effect;
                Color oldColor = shadow.getColor();
                Color newColor = Color.rgb(
                        (int) (oldColor.getRed() * 255),
                        (int) (oldColor.getGreen() * 255),
                        (int) (oldColor.getBlue() * 255),
                        value);
                ObjectProperty<Color> colorProperty = shadow.colorProperty();
                return new KeyValue(colorProperty, newColor, interpolator);
            }
        }

        // Blur animation
        if (animation.equals(FAnimations.BLUR) && effect != null) {
            if (effect.getClass().getSimpleName().equals("GaussianBlur")) {
                GaussianBlur blur = (GaussianBlur) effect;
                return new KeyValue(blur.radiusProperty(), value, interpolator);
            }
        }
        return null;
    }

    /**
     * Color related animations.
     */
    private KeyValue createKeyValue(String animation, String value) {
        if (animation.equals(FAnimations.BG_COLOR)) {
            if (value.matches("#([a-zA-Z0-9]{6}|[a-zA-Z0-9]{8})")) {
                if (bgColorProperty == null) {
                    bgColorProperty = new SimpleObjectProperty<>();
                }
                Color color = Color.valueOf(value);
                bgColorProperty.addListener((prop, oldVal, newVal) -> {
                    BackgroundSetter.setBackgroundColor(element, newVal);
                });
                return new KeyValue(bgColorProperty, color, interpolator);
            }
        }
        if (animation.equals(FAnimations.BR_COLOR)) {
            if (value.matches("#([a-zA-Z0-9]{6}|[a-zA-Z0-9]{8})")) {
                if (brColorProperty == null) {
                    brColorProperty = new SimpleObjectProperty<>();
                }
                Color color = Color.valueOf(value);
                brColorProperty.addListener((prop, oldVal, newVal) -> {
                    BorderSetter.setBorderColor(element, newVal);
                });
                return new KeyValue(brColorProperty, color, interpolator);
            }
        }
        return null;
    }
}
