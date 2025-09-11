package com.example.fastfx.fastfx.style.layouts.flex;

/**
 * Constant values for direction property
 */
public enum Direction {

    ROW("row"),
    COLUMN("column");

    private String direction;

    public String direction(){
        return direction;
    }

    Direction(String direction){
        this.direction = direction;
    }

}
