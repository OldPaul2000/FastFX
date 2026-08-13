package com.example.fastfx.src.util.color;

import javafx.scene.paint.Color;

/**
 * Utility class which contains methods for manipulating colors
 */
public class ColorUtil {
    /**
     * Returns the hexadecimal value as a String from the Color object
     *
     * @param color color object
     * @return the hexadecimal value from the Color object
     */
    public static String getHexFromColor (Color color) {
        long red = (long) (color.getRed() * 255);
        long green = (long) (color.getGreen() * 255);
        long blue = (long) (color.getBlue() * 255);
        long opacity = (long) (color.getOpacity() * 255);
        String redString = "";
        String greenString = "";
        String blueString = "";
        String opacityString = "";
        if (red < 17) {
            redString += 0 + Long.toHexString(red);
        }
        else {
            redString += Long.toHexString(red);
        }
        if (green < 17) {
            greenString += 0 + Long.toHexString(green);
        }
        else {
            greenString += Long.toHexString(green);
        }
        if (blue < 17) {
            blueString += 0 + Long.toHexString(blue);
        }
        else {
            blueString += Long.toHexString(blue);
        }
        if (opacity < 17) {
            opacityString += 0 + Long.toHexString(opacity);
        }
        else {
            opacityString += Long.toHexString(opacity);
        }
        return "#" + redString + greenString + blueString + opacityString;
    }

    /**
     * Changes the opacity for a Color object and returns a new Color<br>
     * with changed opacity
     *
     * @param color   the old color
     * @param opacity opacity value from 0.0 to 1.0
     * @return the new Color with changed opacity
     */
    public static Color setOpacity (Color color, double opacity) {
        if (opacity >= 0 && opacity <= 1) {
            int red = (int) (color.getRed() * 255);
            int green = (int) (color.getGreen() * 255);
            int blue = (int) (color.getBlue() * 255);
            return Color.rgb(red, green, blue, opacity);
        }
        return color;
    }

    /**
     * Changes the opacity for a color represented as hex<br>
     * and returns a new Color object with changed opacity
     *
     * @param colorHex the old hex color
     * @param opacity  opacity value from 0.0 to 1.0
     * @return the new Color with changed opacity
     */
    public static Color setOpacity (String colorHex, double opacity) {
        if (colorHex.matches("#[0-9a-fA-F]{6}|#[0-9a-fA-F]{8}") && opacity >= 0 && opacity <= 1) {
            int red = Integer.parseInt(colorHex.substring(1, 3), 16);
            int green = Integer.parseInt(colorHex.substring(3, 5), 16);
            int blue = Integer.parseInt(colorHex.substring(5, 7), 16);
            return Color.rgb(red, green, blue, opacity);
        }
        return Color.valueOf(colorHex);
    }

    /**
     * Changes the opacity for a color represented as hex value<br>
     * and returns a new hex value with changed opacity
     *
     * @param colorHex the old color
     * @param opacity  opacity value from 0.0 to 1.0
     * @return the new color hex value with changed opacity
     */
    public static String setHexOpacity (String colorHex, double opacity) {
        if (colorHex.matches("#[0-9a-fA-F]{6}|#[0-9a-fA-F]{8}") && opacity >= 0 && opacity <= 1) {
            String hexOpacity = Integer.toString((int) (opacity * 255), 16);
            if (hexOpacity.length() == 1) {
                hexOpacity = "0" + hexOpacity;
            }
            return "#" + colorHex.substring(1, 7) + hexOpacity;
        }
        return colorHex;
    }
}
