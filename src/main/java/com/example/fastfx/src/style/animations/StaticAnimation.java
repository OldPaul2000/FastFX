package com.example.fastfx.src.style.animations;

import com.example.fastfx.src.utility.style.BorderSetter;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;

/*
 * Used to change styles which can't be animated when performing a pseudo-class action(e.g. border style).<br>
 * It's purpose is to make setting properties in the components in the same way as they are set with<br>
 * regular animations in order to keep the same structure.
 */
public class StaticAnimation {

    private Region element;
    private Map<String, String[]> animations;

    public StaticAnimation(Region element){
        this.element = element;
        animations = new HashMap<>();
    }

    public void addAnimation(String animation, String initialValue, String finalValue){
        if(StaticAnimations.get(animation) != null){
            animations.put(animation, new String[]{initialValue, finalValue});
        }
    }

    public void removeAnimation(String animation){
        if(StaticAnimations.get(animation) != null){
            animations.remove(animation);
        }
    }

    public void play(){
        if(animations.containsKey(StaticAnimations.BR_STYLE)){
            BorderSetter.setBorderStyle(element, animations.get(StaticAnimations.BR_STYLE)[1]);
        }
        if(animations.containsKey(StaticAnimations.BR_TOP_STYLE)){
            BorderSetter.setTopBorderStyle(element, animations.get(StaticAnimations.BR_TOP_STYLE)[1]);
        }
        if(animations.containsKey(StaticAnimations.BR_RIGHT_STYLE)){
            BorderSetter.setRightBorderStyle(element, animations.get(StaticAnimations.BR_RIGHT_STYLE)[1]);
        }
        if(animations.containsKey(StaticAnimations.BR_BOTTOM_STYLE)){
            BorderSetter.setBottomBorderStyle(element, animations.get(StaticAnimations.BR_BOTTOM_STYLE)[1]);
        }
        if(animations.containsKey(StaticAnimations.BR_LEFT_STYLE)){
            BorderSetter.setLeftBorderStyle(element, animations.get(StaticAnimations.BR_LEFT_STYLE)[1]);
        }
    }

    public void reverse(){
        if(animations.containsKey(StaticAnimations.BR_STYLE)){
            BorderSetter.setBorderStyle(element, animations.get(StaticAnimations.BR_STYLE)[0]);
        }
        if(animations.containsKey(StaticAnimations.BR_TOP_STYLE)){
            BorderSetter.setTopBorderStyle(element, animations.get(StaticAnimations.BR_TOP_STYLE)[0]);
        }
        if(animations.containsKey(StaticAnimations.BR_RIGHT_STYLE)){
            BorderSetter.setRightBorderStyle(element, animations.get(StaticAnimations.BR_RIGHT_STYLE)[0]);
        }
        if(animations.containsKey(StaticAnimations.BR_BOTTOM_STYLE)){
            BorderSetter.setBottomBorderStyle(element, animations.get(StaticAnimations.BR_BOTTOM_STYLE)[0]);
        }
        if(animations.containsKey(StaticAnimations.BR_LEFT_STYLE)){
            BorderSetter.setLeftBorderStyle(element, animations.get(StaticAnimations.BR_LEFT_STYLE)[0]);
        }
    }
}
