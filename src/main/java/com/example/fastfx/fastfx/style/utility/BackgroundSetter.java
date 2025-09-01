package com.example.fastfx.fastfx.style.utility;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Utility class whose purpose is to simplify setting background color,
 * background radius and insets
 */
public class BackgroundSetter {

    public static void setBackgroundColor(Region element, Color color){
        if(element != null){
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if(background == null){
                backgroundFill = new BackgroundFill(color, null, null);
                element.setBackground(new Background(backgroundFill));
            }
            else{
                backgroundFill = background.getFills().get(0);
                CornerRadii radii = backgroundFill.getRadii();
                Insets insets = backgroundFill.getInsets();
                element.setBackground(new Background(new BackgroundFill(color, radii, insets)));
            }
        }
    }

    public static void setBackgroundColor(Region element, String colorValue){
        if(element != null){
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if(background == null){
                backgroundFill = new BackgroundFill(Color.valueOf(colorValue), null, null);
                element.setBackground(new Background(backgroundFill));
            }
            else{
                backgroundFill = background.getFills().get(0);
                CornerRadii radii = backgroundFill.getRadii();
                Insets insets = backgroundFill.getInsets();
                element.setBackground(new Background(new BackgroundFill(Color.valueOf(colorValue), radii, insets)));
            }
        }
    }

    /**
     * Set radius for every corner individually
     */
    public static void setBackgroundRadius(Region element, double topLeft, double topRight,
                                           double bottomRight, double bottomLeft){
        if(element != null){
            CornerRadii radii = new CornerRadii(topLeft, topRight, bottomRight, bottomLeft, false);
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if(background == null){
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), radii, null);
                element.setBackground(new Background(backgroundFill));
            }
            else{
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color)backgroundColor;
                if(color.toString().equals("0xffffffba")){
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
    public static void setBackgroundRadius(Region element, double topLeftBottomRight, double topRightBottomLeft){
        setBackgroundRadius(element, topLeftBottomRight, topRightBottomLeft, topLeftBottomRight, topRightBottomLeft);
    }

    /**
     * Set the radius for every corner
     */
    public static void setBackgroundRadius(Region element, double radius){
        setBackgroundRadius(element, radius, radius, radius, radius);
    }

    /**
     * Set insets for every side individually
     */
    public static void setInsets(Region element, double top, double right, double bottom, double left){
        if(element != null){
            Insets insets = new Insets(top, right, bottom, left);
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            if(background == null){
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else{
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color)backgroundColor;;
                if(color.toString().equals("0xffffffba")){
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
    public static void setInsets(Region element, double topAndBottom, double leftAndRight){
        setInsets(element, topAndBottom, leftAndRight, topAndBottom, leftAndRight);
    }

    /**
     * Set insets for all sides
     */
    public static void setInsets(Region element, double insetValue){
        setInsets(element, insetValue, insetValue, insetValue, insetValue);
    }

    public static void setTopInset(Region element, double insetValue){
        if(element != null){
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if(background == null){
                insets = new Insets(insetValue, 0, 0, 0);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else{
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color)backgroundColor;
                if(color.toString().equals("0xffffffba")){
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insetValue, insets.getRight(), insets.getBottom(), insets.getLeft());
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static void setRightInset(Region element, double insetValue){
        if(element != null){
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if(background == null){
                insets = new Insets(0, insetValue, 0, 0);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else{
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color)backgroundColor;
                if(color.toString().equals("0xffffffba")){
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insets.getTop(), insetValue, insets.getBottom(), insets.getLeft());
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static void setBottomInset(Region element, double insetValue){
        if(element != null){
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if(background == null){
                insets = new Insets(0, 0, insetValue, 0);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else{
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color)backgroundColor;
                if(color.toString().equals("0xffffffba")){
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insets.getTop(), insets.getRight(), insetValue, insets.getLeft());
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

    public static void setLeftInset(Region element, double insetValue){
        if(element != null){
            Background background = element.getBackground();
            BackgroundFill backgroundFill;
            Insets insets;
            if(background == null){
                insets = new Insets(0, 0, 0, insetValue);
                backgroundFill = new BackgroundFill(Color.valueOf("#e2e2e2"), null, insets);
                element.setBackground(new Background(backgroundFill));
            }
            else{
                backgroundFill = background.getFills().get(0);
                Paint backgroundColor = backgroundFill.getFill();
                Color color = (Color)backgroundColor;
                if(color.toString().equals("0xffffffba")){
                    backgroundColor = Color.valueOf("#e2e2e2");
                }
                CornerRadii radii = backgroundFill.getRadii();
                insets = backgroundFill.getInsets();
                Insets newInsets = new Insets(insets.getTop(), insets.getRight(), insets.getBottom(), insetValue);
                element.setBackground(new Background(new BackgroundFill(backgroundColor, radii, newInsets)));
            }
        }
    }

}
