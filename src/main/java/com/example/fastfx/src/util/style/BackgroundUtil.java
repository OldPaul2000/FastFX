package com.example.fastfx.src.util.style;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Utility class whose purpose is to simplify setting background color,
 * background radius and insets
 */
public class BackgroundUtil {

    public static void setBackgroundColor (Region element, Color color) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if (background == null) {
                backgroundFill = new BackgroundFill(color, null, null);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                CornerRadii radii = backgroundFill.getRadii();
                Insets insets = backgroundFill.getInsets();
                element.setBackground(new Background(new BackgroundFill(color, radii, insets)));
            }
        }
    }

    public static void setBackgroundColor (Region element, String colorValue) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if (background == null) {
                backgroundFill = new BackgroundFill(Color.valueOf(colorValue), null, null);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                CornerRadii radii = backgroundFill.getRadii();
                Insets insets = backgroundFill.getInsets();
                element.setBackground(new Background(new BackgroundFill(Color.valueOf(colorValue), radii, insets)));
            }
        }
    }

    public static String getBackgroundHexColor (Region element) {
        Background background = element.getBackground();
        BackgroundFill backgroundFill;
        if (background != null) {
            backgroundFill = background.getFills().get(0);
            return backgroundFill.getFill().toString().substring(2);
        }
        return null;
    }

    public static Color getBackgroundColor (Region element) {
        Background background = element.getBackground();
        BackgroundFill backgroundFill;
        if (background != null) {
            backgroundFill = background.getFills().get(0);
            return (Color) backgroundFill.getFill();
        }
        return null;
    }

    public static void setBackgroundOpacity (Region element, double opacity) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if (background == null) {
                int intOpacity = (int)(opacity * 255);
                String hexOpacity = Integer.toHexString(intOpacity);
                if(intOpacity < 16){
                    hexOpacity = "0" + hexOpacity;
                }
                backgroundFill = new BackgroundFill(Color.valueOf("e2e2e2" + hexOpacity), null, null);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                CornerRadii radii = backgroundFill.getRadii();
                Insets insets = backgroundFill.getInsets();
                Color oldColor = (Color) backgroundFill.getFill();
                int red = (int) (oldColor.getRed() * 255);
                int green = (int) (oldColor.getGreen() * 255);
                int blue = (int) (oldColor.getBlue() * 255);
                element.setBackground(new Background(new BackgroundFill(Color.rgb(red, green, blue, opacity), radii, insets)));
            }
        }
    }

    public static double getBackgroundOpacity (Region element) {
        Background background = element.getBackground();
        BackgroundFill backgroundFill;
        if (background != null) {
            backgroundFill = background.getFills().get(0);
            return ((Color) backgroundFill.getFill()).getOpacity();
        }
        return 0;
    }

    /**
     * Set radius for every corner individually
     */
    public static void setBackgroundRadius (Region element, double topLeft, double topRight, double bottomRight, double bottomLeft) {
        if (element != null) {
            CornerRadii radii = new CornerRadii(topLeft, topRight, bottomRight, bottomLeft, false);
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if (background == null) {
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), radii, null);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("e2e2e2");
                }
                Insets insets = backgroundFill.getInsets();
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, insets)));
            }
        }
    }

    /**
     * Set the radius for top and bottom corners
     */
    public static void setBackgroundRadius (Region element, double topLeftBottomRight, double topRightBottomLeft) {
        setBackgroundRadius(element, topLeftBottomRight, topRightBottomLeft, topLeftBottomRight, topRightBottomLeft);
    }

    /**
     * Set the radius for every corner
     */
    public static void setBackgroundRadius (Region element, double radius) {
        setBackgroundRadius(element, radius, radius, radius, radius);
    }

    /**
     * Set the radius for the top left corner
     */
    public static void setTopLeftBackgroundRadius (Region element, double radius) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            CornerRadii radii = new CornerRadii(radius, 0, 0, 0, false);
            Insets insets;
            if (background == null) {
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), radii, null);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                CornerRadii newRadii = new CornerRadii(radius, radius, radii.getTopRightVerticalRadius(), radii.getTopRightHorizontalRadius(), radii.getBottomRightHorizontalRadius(), radii.getBottomRightVerticalRadius(), radii.getBottomLeftVerticalRadius(), radii.getBottomLeftHorizontalRadius(), false, false, false, false, false, false, false, false);
                element.setBackground(new Background(new BackgroundFill(backgroundColor, newRadii, insets)));
            }
        }
    }

    /**
     * Set the radius for the top right corner
     */
    public static void setTopRightBackgroundRadius (Region element, double radius) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            CornerRadii radii = new CornerRadii(0, radius, 0, 0, false);
            Insets insets;
            if (background == null) {
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), radii, null);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                CornerRadii newRadii = new CornerRadii(radii.getTopLeftHorizontalRadius(), radii.getTopLeftVerticalRadius(), radius, radius, radii.getBottomRightHorizontalRadius(), radii.getBottomRightVerticalRadius(), radii.getBottomLeftVerticalRadius(), radii.getBottomLeftHorizontalRadius(), false, false, false, false, false, false, false, false);
                element.setBackground(new Background(new BackgroundFill(backgroundColor, newRadii, insets)));
            }
        }
    }

    /**
     * Set the radius for the bottom right corner
     */
    public static void setBottomRightBackgroundRadius (Region element, double radius) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            CornerRadii radii = new CornerRadii(radius, 0, 0, 0, false);
            Insets insets;
            if (background == null) {
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), radii, null);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                CornerRadii newRadii = new CornerRadii(radii.getTopLeftHorizontalRadius(), radii.getTopLeftVerticalRadius(), radii.getTopRightVerticalRadius(), radii.getTopRightHorizontalRadius(), radius, radius, radii.getBottomLeftVerticalRadius(), radii.getBottomLeftHorizontalRadius(), false, false, false, false, false, false, false, false);
                element.setBackground(new Background(new BackgroundFill(backgroundColor, newRadii, insets)));
            }
        }
    }

    /**
     * Set the radius for the bottom left corner
     */
    public static void setBottomLeftBackgroundRadius (Region element, double radius) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            CornerRadii radii = new CornerRadii(radius, 0, 0, 0, false);
            Insets insets;
            if (background == null) {
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), radii, null);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                CornerRadii newRadii = new CornerRadii(radii.getTopLeftHorizontalRadius(), radii.getTopLeftVerticalRadius(), radii.getTopRightVerticalRadius(), radii.getTopRightHorizontalRadius(), radii.getBottomRightHorizontalRadius(), radii.getBottomRightVerticalRadius(), radius, radius, false, false, false, false, false, false, false, false);
                element.setBackground(new Background(new BackgroundFill(backgroundColor, newRadii, insets)));
            }
        }
    }

    public static CornerRadii getBackgroundRadius (Region element) {
        Background background = element.getBackground();
        BackgroundFill backgroundFill;
        if (background != null) {
            backgroundFill = background.getFills().get(0);
            return backgroundFill.getRadii();
        }
        return null;
    }

    public static double getTopLeftBackgroundRadius (Region element) {
        Background background = element.getBackground();
        BackgroundFill backgroundFill;
        if (background != null) {
            backgroundFill = background.getFills().get(0);
            return backgroundFill.getRadii().getTopLeftHorizontalRadius();
        }
        return 0;
    }

    public static double getTopRightBackgroundRadius (Region element) {
        Background background = element.getBackground();
        BackgroundFill backgroundFill;
        if (background != null) {
            backgroundFill = background.getFills().get(0);
            return backgroundFill.getRadii().getTopRightHorizontalRadius();
        }
        return 0;
    }

    public static double getBottomRightBackgroundRadius (Region element) {
        Background background = element.getBackground();
        BackgroundFill backgroundFill;
        if (background != null) {
            backgroundFill = background.getFills().get(0);
            return backgroundFill.getRadii().getBottomRightHorizontalRadius();
        }
        return 0;
    }

    public static double getBottomLeftBackgroundRadius (Region element) {
        Background background = element.getBackground();
        BackgroundFill backgroundFill;
        if (background != null) {
            backgroundFill = background.getFills().get(0);
            return backgroundFill.getRadii().getBottomLeftHorizontalRadius();
        }
        return 0;
    }

    /**
     * Set insets for every side individually
     */
    public static void setInsets (Region element, double top, double right, double bottom, double left) {
        if (element != null) {
            Insets insets = new Insets(top, right, bottom, left);
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if (background == null) {
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                ;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, insets)));
            }
        }
    }

    /**
     * Set insets for top and bottom, and left and right
     */
    public static void setInsets (Region element, double topAndBottom, double leftAndRight) {
        setInsets(element, topAndBottom, leftAndRight, topAndBottom, leftAndRight);
    }

    /**
     * Set insets for all sides
     */
    public static void setInsets (Region element, double insetValue) {
        setInsets(element, insetValue, insetValue, insetValue, insetValue);
    }

    public static void setTopInset (Region element, double insetValue) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if (background == null) {
                insets = new Insets(insetValue, 0, 0, 0);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insetValue, insets.getRight(), insets.getBottom(), insets.getLeft());
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static void setRightInset (Region element, double insetValue) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if (background == null) {
                insets = new Insets(0, insetValue, 0, 0);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insets.getTop(), insetValue, insets.getBottom(), insets.getLeft());
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static void setBottomInset (Region element, double insetValue) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if (background == null) {
                insets = new Insets(0, 0, insetValue, 0);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insets.getTop(), insets.getRight(), insetValue, insets.getLeft());
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static void setLeftInset (Region element, double insetValue) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if (background == null) {
                insets = new Insets(0, 0, 0, insetValue);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insets.getTop(), insets.getRight(), insets.getBottom(), insetValue);
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static void setTopBottomInsets (Region element, double value) {
        setTopBottomInsets(element, value, value);
    }

    public static void setTopBottomInsets (Region element, double top, double bottom) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if (background == null) {
                insets = new Insets(top, 0, bottom, 0);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(top, insets.getRight(), bottom, insets.getLeft());
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static void setRightLeftInsets (Region element, double value) {
        setRightLeftInsets(element, value, value);
    }

    public static void setRightLeftInsets (Region element, double right, double left) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if (background == null) {
                insets = new Insets(0, right, 0, left);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else {
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color) backgroundColor;
                if (color.toString().equals("0xffffffba")) {
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insets.getTop(), right, insets.getBottom(), left);
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static Insets getBackgroundInsets (Region element) {
        if (element != null) {
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if (background != null) {
                backgroundFill = background.getFills().get(0);
                return backgroundFill.getInsets();
            }
        }
        return null;
    }
}
