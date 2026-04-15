package com.example.fastfx.src.style.animations;

import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, DoubleProperty> doubleProperties;
    private Map<String, ObjectProperty<Color>> colorProperties;
    private Map<String, ChangeListener<Number>> numberPropertyListeners;
    private Map<String, ChangeListener<Color>> colorPropertyListeners;

    private Effect effect;

    // Background properties
    private ObjectProperty<Color> bgColorProperty;
    private DoubleProperty bgOpacityProperty;
    private DoubleProperty bgRadiusProperty;
    private DoubleProperty bgTopLeftRadiusProperty;
    private DoubleProperty bgTopRightRadiusProperty;
    private DoubleProperty bgBottomRightRadiusProperty;
    private DoubleProperty bgBottomLeftRadiusProperty;
    private DoubleProperty paddingProperty;

    // Padding properties
    private DoubleProperty topPaddingProperty;
    private DoubleProperty rightPaddingProperty;
    private DoubleProperty bottomPaddingProperty;
    private DoubleProperty leftPaddingProperty;

    // Border properties
    private ObjectProperty<Color> brColorProperty;
    private ObjectProperty<Color> brTopColorProperty;
    private ObjectProperty<Color> brRightColorProperty;
    private ObjectProperty<Color> brBottomColorProperty;
    private ObjectProperty<Color> brLeftColorProperty;
    private DoubleProperty brOpacityProperty;
    private DoubleProperty brWidthProperty;
    private DoubleProperty brTopWidthProperty;
    private DoubleProperty brRightWidthProperty;
    private DoubleProperty brBottomWidthProperty;
    private DoubleProperty brLeftWidthProperty;
    private DoubleProperty brRadiusProperty;
    private DoubleProperty brTopLeftRadiusProperty;
    private DoubleProperty brTopRightRadiusProperty;
    private DoubleProperty brBottomRightRadiusProperty;
    private DoubleProperty brBottomLeftRadiusProperty;
    private DoubleProperty brInsetsProperty;
    private DoubleProperty topBrInsetProperty;
    private DoubleProperty rightBrInsetProperty;
    private DoubleProperty bottomBrInsetProperty;
    private DoubleProperty leftBrInsetProperty;

    public KeyFrameBuilder(){
        this.numberPropertyListeners = new HashMap<>();
        this.colorPropertyListeners = new HashMap<>();
    }

    public void removePropertyListener(String animationType){
        if(numberPropertyListeners.containsKey(animationType)){
            ChangeListener<Number> listener = numberPropertyListeners.get(animationType);
            if(doubleProperties.containsKey(animationType)){
                doubleProperties.get(animationType).removeListener(listener);
            }
            numberPropertyListeners.remove(animationType);
        }
        else if(colorPropertyListeners.containsKey(animationType)){
            ChangeListener<Color> listener = colorPropertyListeners.get(animationType);
            if(colorProperties.containsKey(animationType)){
                colorProperties.get(animationType).removeListener(listener);
            }
            colorPropertyListeners.remove(animationType);
        }
    }

    private void addNumberPropertyListener(String animationType, ChangeListener<Number> listener){
        if(!numberPropertyListeners.containsKey(animationType)){
            numberPropertyListeners.put(animationType, listener);
        }
    }

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
     * Sets the double properties HashMap
     */
    public KeyFrameBuilder setDoubleProperties(Map<String, DoubleProperty> doubleProperties){
        this.doubleProperties = doubleProperties;
        return this;
    }

    /**
     * Adds a double property(layoutXProperty, prefWidthProperty etc.)
     */
    private void addDoubleProperty(String animationType, DoubleProperty doubleProperty){
        if(doubleProperties != null){
            doubleProperties.put(animationType, doubleProperty);
        }
    }

    /**
     * Sets the color properties HashMap
     */
    public KeyFrameBuilder setColorProperties(Map<String, ObjectProperty<Color>> colorProperties){
        this.colorProperties = colorProperties;
        return this;
    }

    /**
     * Adds a color property(bgColorProperty, brColorProperty etc.)
     */
    private void addColorProperty(String animationType, ObjectProperty<Color> colorProperty){
        if(colorProperties != null){
            colorProperties.put(animationType, colorProperty);
        }
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
            DoubleProperty prefWidthProperty = element.prefWidthProperty();
            addDoubleProperty(animation, prefWidthProperty);
            return new KeyValue(prefWidthProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.HEIGHT)) {
            DoubleProperty prefHeightProperty = element.prefHeightProperty();
            addDoubleProperty(animation, prefHeightProperty);
            return new KeyValue(prefHeightProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.LAYOUT_X)) {
            DoubleProperty layoutXProperty = element.layoutXProperty();
            addDoubleProperty(animation, layoutXProperty);
            return new KeyValue(layoutXProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.LAYOUT_Y)) {
            DoubleProperty layoutYProperty = element.layoutYProperty();
            addDoubleProperty(animation, layoutYProperty);
            return new KeyValue(layoutYProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.ROTATE)) {
            DoubleProperty rotateProperty = element.rotateProperty();
            addDoubleProperty(animation, rotateProperty);
            return new KeyValue(element.rotateProperty(), value, interpolator);
        }
        else if (animation.equals(FAnimations.SCALE_X)) {
            DoubleProperty scaleXProperty = element.scaleXProperty();
            addDoubleProperty(animation, scaleXProperty);
            return new KeyValue(scaleXProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.SCALE_Y)) {
            DoubleProperty scaleYProperty = element.scaleYProperty();
            addDoubleProperty(animation, scaleYProperty);
            return new KeyValue(scaleYProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.TRANSLATE_X)) {
            DoubleProperty translateXProperty = element.translateXProperty();
            addDoubleProperty(animation, translateXProperty);
            return new KeyValue(translateXProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.TRANSLATE_Y)) {
            DoubleProperty translateYProperty = element.translateYProperty();
            addDoubleProperty(animation, translateYProperty);
            return new KeyValue(translateYProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.OPACITY)) {
            DoubleProperty opacityProperty = element.opacityProperty();
            addDoubleProperty(animation, opacityProperty);
            return new KeyValue(opacityProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.PADDING)) {
            if (paddingProperty == null) {
                paddingProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    PaddingUtil.setPadding(element, paddingProperty.get());
                };
                paddingProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, paddingProperty);
            return new KeyValue(paddingProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.TOP_PADDING)) {
            if (topPaddingProperty == null) {
                topPaddingProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    PaddingUtil.setTopPadding(element, topPaddingProperty.get());
                };
                topPaddingProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, topPaddingProperty);
            return new KeyValue(topPaddingProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.RIGHT_PADDING)) {
            if (rightPaddingProperty == null) {
                rightPaddingProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    PaddingUtil.setTopPadding(element, topPaddingProperty.get());
                };
                rightPaddingProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, rightPaddingProperty);
            return new KeyValue(rightPaddingProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BOTTOM_PADDING)) {
            if (bottomPaddingProperty == null) {
                bottomPaddingProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    PaddingUtil.setBottomPadding(element, bottomPaddingProperty.get());
                };
                bottomPaddingProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, bottomPaddingProperty);
            return new KeyValue(bottomPaddingProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.LEFT_PADDING)) {
            if (leftPaddingProperty == null) {
                leftPaddingProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    PaddingUtil.setLeftPadding(element, leftPaddingProperty.get());
                };
                leftPaddingProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, leftPaddingProperty);
            return new KeyValue(leftPaddingProperty, value, interpolator);
        }

        // Background animations
        else if (animation.equals(FAnimations.BG_OPACITY)) {
            value = Math.max(0, Math.min(1, value));
            if (bgOpacityProperty == null) {
                bgOpacityProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BackgroundSetter.setBackgroundOpacity(element, bgOpacityProperty.get());
                };
                bgOpacityProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, bgOpacityProperty);
            return new KeyValue(bgOpacityProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BG_RADIUS)) {
            if (bgRadiusProperty == null) {
                bgRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BackgroundSetter.setBackgroundRadius(element, bgRadiusProperty.get());
                };
                bgRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, bgRadiusProperty);
            return new KeyValue(bgRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BG_TL_RADIUS)) {
            if (bgTopLeftRadiusProperty == null) {
                bgTopLeftRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BackgroundSetter.setTopLeftBackgroundRadius(element, bgTopLeftRadiusProperty.get());
                };
                bgTopLeftRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, bgTopLeftRadiusProperty);
            return new KeyValue(bgTopLeftRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BG_TR_RADIUS)) {
            if (bgTopRightRadiusProperty == null) {
                bgTopRightRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BackgroundSetter.setTopRightBackgroundRadius(element, bgTopRightRadiusProperty.get());
                };
                bgTopRightRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, bgTopRightRadiusProperty);
            return new KeyValue(bgTopRightRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BG_BR_RADIUS)) {
            if (bgBottomRightRadiusProperty == null) {
                bgBottomRightRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BackgroundSetter.setBottomRightBackgroundRadius(element, bgBottomRightRadiusProperty.get());
                };
                bgBottomRightRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, bgBottomRightRadiusProperty);
            return new KeyValue(bgBottomRightRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BG_BL_RADIUS)) {
            if (bgBottomLeftRadiusProperty == null) {
                bgBottomLeftRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BackgroundSetter.setBottomLeftBackgroundRadius(element, bgBottomLeftRadiusProperty.get());
                };
                bgBottomLeftRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, bgBottomLeftRadiusProperty);
            return new KeyValue(bgBottomLeftRadiusProperty, value, interpolator);
        }

        // Border animations
        else if (animation.equals(FAnimations.BR_OPACITY)) {
            value = Math.max(0, Math.min(1, value));
            if (brOpacityProperty == null) {
                brOpacityProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setBorderOpacity(element, brOpacityProperty.get());
                };
                brOpacityProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brOpacityProperty);
            return new KeyValue(brOpacityProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_WIDTH)) {
            if (brWidthProperty == null) {
                brWidthProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setBorderWidth(element, brWidthProperty.get());
                };
                brWidthProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brWidthProperty);
            return new KeyValue(brWidthProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_TOP_WIDTH)) {
            if (brTopWidthProperty == null) {
                brTopWidthProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setTopBorderWidth(element, brTopWidthProperty.get());
                };
                brTopWidthProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brTopWidthProperty);
            return new KeyValue(brTopWidthProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_RIGHT_WIDTH)) {
            if (brRightWidthProperty == null) {
                brRightWidthProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setRightBorderWidth(element, brRightWidthProperty.get());
                };
                brRightWidthProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brRightWidthProperty);
            return new KeyValue(brRightWidthProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_BOTTOM_WIDTH)) {
            if (brBottomWidthProperty == null) {
                brBottomWidthProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setBottomBorderWidth(element, brBottomWidthProperty.get());
                };
                brBottomWidthProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brBottomWidthProperty);
            return new KeyValue(brBottomWidthProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_LEFT_WIDTH)) {
            if (brLeftWidthProperty == null) {
                brLeftWidthProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setLeftBorderWidth(element, brLeftWidthProperty.get());
                };
                brLeftWidthProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brLeftWidthProperty);
            return new KeyValue(brLeftWidthProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_RADIUS)) {
            if (brRadiusProperty == null) {
                brRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setBorderRadius(element, brRadiusProperty.get());
                };
                brRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brRadiusProperty);
            return new KeyValue(brRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_TL_RADIUS)) {
            if (brTopLeftRadiusProperty == null) {
                brTopLeftRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setTopLeftBorderRadius(element, brTopLeftRadiusProperty.get());
                };
                brTopLeftRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brTopLeftRadiusProperty);
            return new KeyValue(brTopLeftRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_TR_RADIUS)) {
            if (brTopRightRadiusProperty == null) {
                brTopRightRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setTopRightBorderRadius(element, brTopRightRadiusProperty.get());
                };
                brTopRightRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brTopRightRadiusProperty);
            return new KeyValue(brTopRightRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_BR_RADIUS)) {
            if (brBottomRightRadiusProperty == null) {
                brBottomRightRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setBottomRightBorderRadius(element, brBottomRightRadiusProperty.get());
                };
                brBottomRightRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brBottomRightRadiusProperty);
            return new KeyValue(brBottomRightRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_BL_RADIUS)) {
            if (brBottomLeftRadiusProperty == null) {
                brBottomLeftRadiusProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setBottomLeftBorderRadius(element, brBottomLeftRadiusProperty.get());
                };
                brBottomLeftRadiusProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brBottomLeftRadiusProperty);
            return new KeyValue(brBottomLeftRadiusProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_INSETS)) {
            if (brInsetsProperty == null) {
                brInsetsProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setBorderInsets(element, brInsetsProperty.get());
                };
                brInsetsProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, brInsetsProperty);
            return new KeyValue(brInsetsProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_TOP_INSET)) {
            if (topBrInsetProperty == null) {
                topBrInsetProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setTopBorderInset(element, topBrInsetProperty.get());
                };
                topBrInsetProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, topBrInsetProperty);
            return new KeyValue(topBrInsetProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_RIGHT_INSET)) {
            if (rightBrInsetProperty == null) {
                rightBrInsetProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setRightBorderInset(element, rightBrInsetProperty.get());
                };
                rightBrInsetProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, rightBrInsetProperty);
            return new KeyValue(rightBrInsetProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_BOTTOM_INSET)) {
            if (bottomBrInsetProperty == null) {
                bottomBrInsetProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setBottomBorderInset(element, bottomBrInsetProperty.get());
                };
                bottomBrInsetProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, bottomBrInsetProperty);
            return new KeyValue(bottomBrInsetProperty, value, interpolator);
        }
        else if (animation.equals(FAnimations.BR_LEFT_INSET)) {
            if (leftBrInsetProperty == null) {
                leftBrInsetProperty = new SimpleDoubleProperty();
            }
            if(!numberPropertyListeners.containsKey(animation)){
                ChangeListener<Number> listener = (prop, oldVal, newVal) -> {
                    BorderSetter.setLeftBorderInset(element, leftBrInsetProperty.get());
                };
                leftBrInsetProperty.addListener(listener);
                numberPropertyListeners.put(animation, listener);
            }
            addDoubleProperty(animation, leftBrInsetProperty);
            return new KeyValue(leftBrInsetProperty, value, interpolator);
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
                if(!colorPropertyListeners.containsKey(animation)){
                    ChangeListener<Color> listener = (prop, oldVal, newVal) -> {
                        BackgroundSetter.setBackgroundColor(element, newVal);
                    };
                    bgColorProperty.addListener(listener);
                    colorPropertyListeners.put(animation, listener);
                }
                addColorProperty(animation, bgColorProperty);
                return new KeyValue(bgColorProperty, color, interpolator);
            }
        }
        else if (animation.equals(FAnimations.BR_COLOR)) {
            if (value.matches("#([a-zA-Z0-9]{6}|[a-zA-Z0-9]{8})")) {
                if (brColorProperty == null) {
                    brColorProperty = new SimpleObjectProperty<>();
                }
                Color color = Color.valueOf(value);
                if(!colorPropertyListeners.containsKey(animation)){
                    ChangeListener<Color> listener = (prop, oldVal, newVal) -> {
                        BorderSetter.setBorderColor(element, newVal);
                    };
                    brColorProperty.addListener(listener);
                    colorPropertyListeners.put(animation, listener);
                }
                addColorProperty(animation, brColorProperty);
                return new KeyValue(brColorProperty, color, interpolator);
            }
        }
        else if (animation.equals(FAnimations.BR_TOP_COLOR)) {
            if (value.matches("#([a-zA-Z0-9]{6}|[a-zA-Z0-9]{8})")) {
                if (brTopColorProperty == null) {
                    brTopColorProperty = new SimpleObjectProperty<>();
                }
                Color color = Color.valueOf(value);
                if(!colorPropertyListeners.containsKey(animation)){
                    ChangeListener<Color> listener = (prop, oldVal, newVal) -> {
                        BorderSetter.setTopBorderColor(element, newVal);
                    };
                    brTopColorProperty.addListener(listener);
                    colorPropertyListeners.put(animation, listener);
                }
                addColorProperty(animation, brTopColorProperty);
                return new KeyValue(brTopColorProperty, color, interpolator);
            }
        }
        else if (animation.equals(FAnimations.BR_RIGHT_COLOR)) {
            if (value.matches("#([a-zA-Z0-9]{6}|[a-zA-Z0-9]{8})")) {
                if (brRightColorProperty == null) {
                    brRightColorProperty = new SimpleObjectProperty<>();
                }
                Color color = Color.valueOf(value);
                if(!colorPropertyListeners.containsKey(animation)){
                    ChangeListener<Color> listener = (prop, oldVal, newVal) -> {
                        BorderSetter.setRightBorderColor(element, newVal);
                    };
                    brRightColorProperty.addListener(listener);
                    colorPropertyListeners.put(animation, listener);
                }
                addColorProperty(animation, brRightColorProperty);
                return new KeyValue(brRightColorProperty, color, interpolator);
            }
        }
        else if (animation.equals(FAnimations.BR_BOTTOM_COLOR)) {
            if (value.matches("#([a-zA-Z0-9]{6}|[a-zA-Z0-9]{8})")) {
                if (brBottomColorProperty == null) {
                    brBottomColorProperty = new SimpleObjectProperty<>();
                }
                Color color = Color.valueOf(value);
                if(!colorPropertyListeners.containsKey(animation)){
                    ChangeListener<Color> listener = (prop, oldVal, newVal) -> {
                        BorderSetter.setBottomBorderColor(element, newVal);
                    };
                    brBottomColorProperty.addListener(listener);
                    colorPropertyListeners.put(animation, listener);
                }
                addColorProperty(animation, brBottomColorProperty);
                return new KeyValue(brBottomColorProperty, color, interpolator);
            }
        }
        else if (animation.equals(FAnimations.BR_LEFT_COLOR)) {
            if (value.matches("#([a-zA-Z0-9]{6}|[a-zA-Z0-9]{8})")) {
                if (brLeftColorProperty == null) {
                    brLeftColorProperty = new SimpleObjectProperty<>();
                }
                Color color = Color.valueOf(value);
                if(!colorPropertyListeners.containsKey(animation)){
                    ChangeListener<Color> listener = (prop, oldVal, newVal) -> {
                        BorderSetter.setLeftBorderColor(element, newVal);
                    };
                    brLeftColorProperty.addListener(listener);
                    colorPropertyListeners.put(animation, listener);
                }
                addColorProperty(animation, brLeftColorProperty);
                return new KeyValue(brLeftColorProperty, color, interpolator);
            }
        }
        return null;
    }
}
