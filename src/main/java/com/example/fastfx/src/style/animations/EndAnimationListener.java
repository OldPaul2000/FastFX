package com.example.fastfx.src.style.animations;

/**
 * Listener which is called when an animation finished its execution.
 */
public interface EndAnimationListener {

    /**
     * @param fAnimation represents the animation type which ended.
     * @param duration   represents the duration in milliseconds of the finished KeyFrame.
     */
    void apply(String fAnimation, int duration);

}
