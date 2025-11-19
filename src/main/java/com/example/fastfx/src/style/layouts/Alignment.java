package com.example.fastfx.src.style.layouts;

/**
 * Constant values for alignment property
 */
public enum Alignment {

    START("start"),
    CENTER("center"),
    END("end");

    private String alignment;

    public String alignment(){
        return alignment;
    }

    Alignment(String alignment){
        this.alignment = alignment;
    }

}
