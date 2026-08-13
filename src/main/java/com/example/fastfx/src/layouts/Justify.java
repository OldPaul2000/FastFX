package com.example.fastfx.src.layouts;

/**
 * Constant values for justify property
 */
public enum Justify {

    START("start"), CENTER("center"), END("end"), SPACE_BETWEEN("space-between"), SPACE_AROUND("space-around"), SPACE_EVENLY("space-evenly");

    private String justify;

    public String justify () {
        return justify;
    }

    Justify (String justify) {
        this.justify = justify;
    }
}
