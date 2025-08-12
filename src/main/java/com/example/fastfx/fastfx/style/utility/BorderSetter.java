package com.example.fastfx.fastfx.style.utility;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class whose purpose is to simplify setting border and all
 * of its properties individually
 */
public class BorderSetter {

    private static final Map<String, BorderStrokeStyle> BORDER_STYLES = new HashMap<>(
            Map.ofEntries(
                    Map.entry("solid", BorderStrokeStyle.SOLID),
                    Map.entry("dashed", BorderStrokeStyle.DASHED),
                    Map.entry("dotted", BorderStrokeStyle.DOTTED),
                    Map.entry("none", BorderStrokeStyle.NONE)
            )
    );

    /**
     * Set the border and all corners radius and all sides width individually
     */
    public static void setBorder(Region element, String colorValue, String borderStyle,
                                 double topLeftRadius, double topRightRadius, double bottomRightRadius,
                                 double bottomLeftRadius, double topWidth, double rightWidth,
                                 double bottomWidth, double leftWidth){

        BorderStrokeStyle borderStrokeStyle = BORDER_STYLES.get(borderStyle);
        CornerRadii radii = new CornerRadii(topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, false);
        BorderWidths borderWidths = new BorderWidths(topWidth, rightWidth, bottomWidth, leftWidth);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(colorValue), borderStrokeStyle, radii, borderWidths);
        Border border = new Border(borderStroke);
        element.setBorder(border);
    }

    /**
     * Set the border and the radius for all corners and the width for all sides
     */
    public static void setBorder(Region element, String colorValue, String borderStyle,
                                 double radius, double borderWidth){
        setBorder(element, colorValue, borderStyle, radius, radius, radius, radius,
                  borderWidth, borderWidth, borderWidth, borderWidth);
    }

    /**
     * Set the border style(Solid, Dotted, Dashed etc)
     */
    public static void setBorderStyle(Region element, String borderStyle){
        Border border = element.getBorder();
        BorderStrokeStyle borderStrokeStyle = BORDER_STYLES.get(borderStyle);
        if(border != null){
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            BorderWidths borderWidths = borderStroke.getWidths();

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(), borderStroke.getRightStroke(),
                    borderStroke.getBottomStroke(), borderStroke.getLeftStroke(), borderStrokeStyle, borderStrokeStyle,
                    borderStrokeStyle, borderStrokeStyle, radii, borderWidths, null);

            element.setBorder(new Border(newBorderStroke));
        }
        else{
            setBorder(element, "black", borderStyle, 0, 0);
        }
   }

    /**
     * Set the width for every side individually
     */
    public static void setBorderWidth(Region element, double topWidth, double rightWidth,
                                      double bottomWidth, double leftWidth){
        Border border = element.getBorder();
        if(border != null){
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            BorderWidths borderWidths = new BorderWidths(topWidth, rightWidth, bottomWidth, leftWidth);

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(), borderStroke.getRightStroke(),
                    borderStroke.getBottomStroke(), borderStroke.getLeftStroke(), borderStroke.getTopStyle(), borderStroke.getRightStyle(),
                    borderStroke.getBottomStyle(), borderStroke.getLeftStyle(), radii, borderWidths, null);

            element.setBorder(new Border(newBorderStroke));
        }
        else{
            setBorder(element, "black", "solid", 0, 0, 0, 0, topWidth, rightWidth,
                      bottomWidth, leftWidth);
        }
    }

    /**
     * Set the width for all sides
     */
   public static void setBorderWidth(Region element, double width){
       setBorderWidth(element, width, width, width, width);
   }

    /**
     * Set the border color
     */
   public static void setBorderColor(Region element, String colorValue){
       Border border = element.getBorder();
       if(border != null){
           BorderStroke borderStroke = border.getStrokes().get(0);
           CornerRadii radii = borderStroke.getRadii();
           BorderWidths borderWidths = borderStroke.getWidths();
           Paint color = Color.valueOf(colorValue);

           BorderStroke newBorderStroke = new BorderStroke(color,color,color,color,borderStroke.getTopStyle(),
                   borderStroke.getRightStyle(), borderStroke.getBottomStyle(), borderStroke.getLeftStyle(),
                   radii,borderWidths, null);

           element.setBorder(new Border(newBorderStroke));
       }
       else{
           setBorder(element, colorValue, "solid", 0, 0);
       }
   }

   /**
    * Set border insets for each side individually
    */
    public static void setBorderInsets(Region element, double top, double right, double bottom, double left){
        Border border = element.getBorder();
        if(border != null){
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = new Insets(top, right, bottom, left);

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(),borderStroke.getRightStroke(),
                    borderStroke.getBottomStroke(), borderStroke.getLeftStroke(),borderStroke.getTopStyle(),
                    borderStroke.getRightStyle(), borderStroke.getBottomStyle(), borderStroke.getLeftStyle(),
                    radii,borderWidths, insets);

            element.setBorder(new Border(newBorderStroke));
        }
    }

    /**
     * Set border insets for all sides
     */
    public static void setBorderInsets(Region element, double inset){
        setBorderInsets(element, inset, inset, inset, inset);
    }

    /**
     * Set border radius for each corner individually
     */
    public static void setBorderRadius(Region element, double topLeft, double topRight,
                                       double bottomRight, double bottomLeft){
        Border border = element.getBorder();
        if(border != null){
            BorderStroke borderStroke = border.getStrokes().get(0);
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            CornerRadii radii = new CornerRadii(topLeft, topRight, bottomRight, bottomLeft, false);

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(),borderStroke.getRightStroke(),
                    borderStroke.getBottomStroke(), borderStroke.getLeftStroke(),borderStroke.getTopStyle(),
                    borderStroke.getRightStyle(), borderStroke.getBottomStyle(), borderStroke.getLeftStyle(),
                    radii,borderWidths, insets);

            element.setBorder(new Border(newBorderStroke));
        }
        else{
            setBorder(element, "black", "solid", topLeft, topRight, bottomRight, bottomLeft,
                      0, 0, 0, 0);
        }
    }

    /**
     * Set border radius for all corners
     */
    public static void setBorderRadius(Region element, double radius){
        setBorderRadius(element, radius, radius, radius, radius);
    }

    public static void setTopBorder(Region element, String colorValue, String borderStyle, double borderWidth){
        Border border = element.getBorder();
        BorderStrokeStyle borderStrokeStyle = BORDER_STYLES.get(borderStyle);
        if(border != null) {
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            BorderWidths newWidths = new BorderWidths(borderWidth, borderWidths.getRight(), borderWidths.getBottom(), borderWidths.getLeft());

            BorderStroke newBorderStroke = new BorderStroke(Color.valueOf(colorValue), borderStroke.getRightStroke(),
                    borderStroke.getBottomStroke(), borderStroke.getLeftStroke(), borderStrokeStyle, borderStroke.getRightStyle(),
                    borderStroke.getBottomStyle(), borderStroke.getLeftStyle(), radii, newWidths, insets);

            element.setBorder(new Border(newBorderStroke));
        }
        else{
            CornerRadii radii = new CornerRadii(0, 0, 0, 0, false);
            BorderWidths borderWidths = new BorderWidths(borderWidth, 0 ,0 ,0);
            Insets insets = new Insets(0, 0, 0, 0);
            BorderStroke borderStroke = new BorderStroke(Color.valueOf(colorValue), Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT,
                    borderStrokeStyle, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, radii, borderWidths, insets);
            element.setBorder(new Border(borderStroke));
        }
    }

    public static void setRightBorder(Region element, String colorValue, String borderStyle, double borderWidth){
        Border border = element.getBorder();
        BorderStrokeStyle borderStrokeStyle = BORDER_STYLES.get(borderStyle);
        if(border != null) {
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            BorderWidths newWidths = new BorderWidths(borderWidths.getTop(), borderWidth, borderWidths.getBottom(), borderWidths.getLeft());

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(), Color.valueOf(colorValue),
                    borderStroke.getBottomStroke(), borderStroke.getLeftStroke(), borderStroke.getTopStyle(), borderStrokeStyle,
                    borderStroke.getBottomStyle(), borderStroke.getLeftStyle(), radii, newWidths, insets);

            element.setBorder(new Border(newBorderStroke));
        }
        else{
            CornerRadii radii = new CornerRadii(0, 0, 0, 0, false);
            BorderWidths borderWidths = new BorderWidths(0, borderWidth ,0 ,0);
            Insets insets = new Insets(0, 0, 0, 0);
            BorderStroke borderStroke = new BorderStroke(Color.TRANSPARENT, Color.valueOf(colorValue), Color.TRANSPARENT, Color.TRANSPARENT,
                    BorderStrokeStyle.NONE, borderStrokeStyle, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, radii, borderWidths, insets);
            element.setBorder(new Border(borderStroke));
        }
    }

    public static void setBottomBorder(Region element, String colorValue, String borderStyle, double borderWidth){
        Border border = element.getBorder();
        BorderStrokeStyle borderStrokeStyle = BORDER_STYLES.get(borderStyle);
        if(border != null) {
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            BorderWidths newWidths = new BorderWidths(borderWidths.getTop(), borderWidths.getRight(), borderWidth, borderWidths.getLeft());

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(), borderStroke.getRightStroke(),
                    Color.valueOf(colorValue), borderStroke.getLeftStroke(), borderStroke.getTopStyle(), borderStroke.getRightStyle(),
                    borderStrokeStyle, borderStroke.getLeftStyle(), radii, newWidths, insets);

            element.setBorder(new Border(newBorderStroke));
        }
        else{
            CornerRadii radii = new CornerRadii(0, 0, 0, 0, false);
            BorderWidths borderWidths = new BorderWidths(0, 0 ,borderWidth ,0);
            Insets insets = new Insets(0, 0, 0, 0);
            BorderStroke borderStroke = new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Color.valueOf(colorValue), Color.TRANSPARENT,
                    BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, borderStrokeStyle, BorderStrokeStyle.NONE, radii, borderWidths, insets);
            element.setBorder(new Border(borderStroke));
        }
    }

    public static void setLeftBorder(Region element, String colorValue, String borderStyle, double borderWidth){
        Border border = element.getBorder();
        BorderStrokeStyle borderStrokeStyle = BORDER_STYLES.get(borderStyle);
        if(border != null) {
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            BorderWidths newWidths = new BorderWidths(borderWidths.getTop(), borderWidths.getRight(), borderWidths.getBottom(), borderWidth);

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(), borderStroke.getRightStroke(),
                    borderStroke.getBottomStroke(), Color.valueOf(colorValue), borderStroke.getTopStyle(), borderStroke.getRightStyle(),
                    borderStroke.getBottomStyle(), borderStrokeStyle, radii, newWidths, insets);

            element.setBorder(new Border(newBorderStroke));
        }
        else{
            CornerRadii radii = new CornerRadii(0, 0, 0, 0, false);
            BorderWidths borderWidths = new BorderWidths(0, 0 ,0 ,borderWidth);
            Insets insets = new Insets(0, 0, 0, 0);
            BorderStroke borderStroke = new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.valueOf(colorValue),
                    BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, borderStrokeStyle, radii, borderWidths, insets);
            element.setBorder(new Border(borderStroke));
        }
    }

    public static void removeBorder(Region element){
        element.setBorder(null);
    }

    public static void removeTopBorder(Region element){
        Border border = element.getBorder();
        if(border != null){
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            CornerRadii newRadii = new CornerRadii(0,0,0,0, radii.getBottomRightHorizontalRadius(), radii.getBottomRightVerticalRadius(),
                    radii.getBottomLeftVerticalRadius(), radii.getBottomLeftHorizontalRadius(), false, false, false, false, false, false, false, false);
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            BorderWidths newWidths = new BorderWidths(0, borderWidths.getRight(), borderWidths.getBottom(), borderWidths.getLeft());

            BorderStroke newBorderStroke = new BorderStroke(Color.TRANSPARENT, borderStroke.getRightStroke(), borderStroke.getBottomStroke(),
                    borderStroke.getLeftStroke(), BorderStrokeStyle.NONE, borderStroke.getRightStyle(), borderStroke.getBottomStyle(),
                    borderStroke.getLeftStyle(), newRadii, newWidths, insets);
            element.setBorder(new Border(newBorderStroke));
        }
    }

    public static void removeRightBorder(Region element){
        Border border = element.getBorder();
        if(border != null){
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            CornerRadii newRadii = new CornerRadii(radii.getTopLeftHorizontalRadius(), radii.getTopLeftVerticalRadius(), 0, 0, 0, 0,
                    radii.getBottomLeftVerticalRadius(), radii.getBottomLeftHorizontalRadius(), false, false, false, false, false, false, false, false);
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            BorderWidths newWidths = new BorderWidths(borderWidths.getTop(), 0, borderWidths.getBottom(), borderWidths.getRight());

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(), Color.TRANSPARENT, borderStroke.getBottomStroke(),
                    borderStroke.getLeftStroke(), borderStroke.getTopStyle(), BorderStrokeStyle.NONE, borderStroke.getBottomStyle(),
                    borderStroke.getLeftStyle(), newRadii, newWidths, insets);
            element.setBorder(new Border(newBorderStroke));
        }
    }

    public static void removeBottomBorder(Region element){
        Border border = element.getBorder();
        if(border != null){
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            CornerRadii newRadii = new CornerRadii(radii.getTopLeftHorizontalRadius(), radii.getTopLeftVerticalRadius(), radii.getTopRightVerticalRadius(),
                    radii.getTopLeftHorizontalRadius(), 0, 0, 0, 0, false, false, false, false, false, false, false, false);
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            BorderWidths newWidths = new BorderWidths(borderWidths.getTop(), borderWidths.getRight(), 0, borderWidths.getRight());

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(), borderStroke.getRightStroke(), Color.TRANSPARENT,
                    borderStroke.getLeftStroke(), borderStroke.getTopStyle(), borderStroke.getRightStyle(), BorderStrokeStyle.NONE,
                    borderStroke.getLeftStyle(), newRadii, newWidths, insets);
            element.setBorder(new Border(newBorderStroke));
        }
    }

    public static void removeLeftBorder(Region element){
        Border border = element.getBorder();
        if(border != null){
            BorderStroke borderStroke = border.getStrokes().get(0);
            CornerRadii radii = borderStroke.getRadii();
            CornerRadii newRadii = new CornerRadii(0, 0, radii.getTopRightVerticalRadius(), radii.getTopLeftHorizontalRadius(),
                    radii.getBottomRightHorizontalRadius(), radii.getBottomRightVerticalRadius(), 0, 0, false, false, false, false, false, false, false, false);
            BorderWidths borderWidths = borderStroke.getWidths();
            Insets insets = borderStroke.getInsets();
            BorderWidths newWidths = new BorderWidths(borderWidths.getTop(), borderWidths.getRight(), borderWidths.getBottom(), 0);

            BorderStroke newBorderStroke = new BorderStroke(borderStroke.getTopStroke(), borderStroke.getRightStroke(), borderStroke.getBottomStroke(),
                    Color.TRANSPARENT, borderStroke.getTopStyle(), borderStroke.getRightStyle(), borderStroke.getBottomStyle(),
                    BorderStrokeStyle.NONE, newRadii, newWidths, insets);
            element.setBorder(new Border(newBorderStroke));
        }
    }

}
