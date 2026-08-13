package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Interface used for simple components. The simple components will only
 * set the properties in a basic way(directly) without parsing any style
 * or implementing some complex logic like in the FComponents.
 * The animations and other logic will be added separately.
 */
public interface FComponent {
    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    FComponent x (double value);

    double getX ();

    FComponent y (double value);

    double getY ();

    FComponent width (double value);

    FComponent height (double value);

    FComponent scaleX (double value);

    FComponent scaleY (double value);

    FComponent translateX (double value);

    FComponent translateY (double value);

    FComponent rotate (double value);

    FComponent padding (double value);

    FComponent padding (double topBottom, double rightLeft);

    FComponent padding (double top, double right, double bottom, double left);

    FComponent topPadding (double value);

    FComponent rightPadding (double value);

    FComponent bottomPadding (double value);

    FComponent leftPadding (double value);

    double getTopPadding ();

    double getRightPadding ();

    double getBottomPadding ();

    double getLeftPadding ();

    FComponent margin (double value);

    FComponent margin (double topBottom, double rightLeft);

    FComponent margin (double top, double right, double bottom, double left);

    Margin getMargin ();

    FComponent topMargin (double value);

    FComponent rightMargin (double value);

    FComponent bottomMargin (double value);

    FComponent leftMargin (double value);

    double getTopMargin ();

    double getRightMargin ();

    double getBottomMargin ();

    double getLeftMargin ();

    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    // *********************************************************************************************
    // **********************************************************
    //                                                          *
    //                      Custom properties                   *
    //                                                          *
    // **********************************************************
    // ******************************************
    //            Background properties         *
    // ******************************************
    FComponent bgColor (String color);

    FComponent bgColor (Color color);

    String getBgColorHex ();

    Color getBgColor ();

    FComponent bgOpacity (double value);

    double getBgOpacity ();

    FComponent bgRadius (double value);

    FComponent bgRadius (double topLeftBottomRight, double topRightBottomLeft);

    FComponent bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft);

    CornerRadii getBgRadius ();

    FComponent topLeftBgRadius (double value);

    FComponent topRightBgRadius (double value);

    FComponent bottomRightBgRadius (double value);

    FComponent bottomLeftBgRadius (double value);

    double getTopLeftBgRadius ();

    double getTopRightBgRadius ();

    double getBottomRightBgRadius ();

    double getBottomLeftBgRadius ();

    FComponent bgInsets (double value);

    FComponent bgInsets (double topBottom, double rightLeft);

    FComponent bgInsets (double top, double right, double bottom, double left);

    Insets getBgInsets ();

    FComponent topInset (double value);

    FComponent rightInset (double value);

    FComponent bottomInset (double value);

    FComponent leftInset (double value);

    double getTopInset ();

    double getRightInset ();

    double getBottomInset ();

    double getLeftInset ();

    // ******************************************
    //            Background properties         *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Border properties           *
    // ******************************************
    FComponent border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth);

    FComponent border (String colorValue, String borderStyle, double radius, double borderWidth);

    FComponent topBorder (String colorValue, String borderStyle, double borderWidth);

    FComponent rightBorder (String colorValue, String borderStyle, double borderWidth);

    FComponent bottomBorder (String colorValue, String borderStyle, double borderWidth);

    FComponent leftBorder (String colorValue, String borderStyle, double borderWidth);

    FComponent topBorder (Color color, String borderStyle, double borderWidth);

    FComponent rightBorder (Color color, String borderStyle, double borderWidth);

    FComponent bottomBorder (Color color, String borderStyle, double borderWidth);

    FComponent leftBorder (Color color, String borderStyle, double borderWidth);

    FComponent borderStyle (String borderStyle);

    FComponent topBorderStyle (String borderStyle);

    FComponent rightBorderStyle (String borderStyle);

    FComponent bottomBorderStyle (String borderStyle);

    FComponent leftBorderStyle (String borderStyle);

    String getTopBorderStyle ();

    String getRightBorderStyle ();

    String getBottomBorderStyle ();

    String getLeftBorderStyle ();

    FComponent borderWidth (double value);

    FComponent borderWidth (double topBottom, double rightLeft);

    FComponent borderWidth (double top, double right, double bottom, double left);

    FComponent topBorderWidth (double value);

    FComponent rightBorderWidth (double value);

    FComponent bottomBorderWidth (double value);

    FComponent leftBorderWidth (double value);

    double getTopBorderWidth ();

    double getRightBorderWidth ();

    double getBottomBorderWidth ();

    double getLeftBorderWidth ();

    FComponent borderColor (String colorValue);

    FComponent borderColor (String topBottom, String rightLeft);

    FComponent borderColor (String top, String right, String bottom, String left);

    FComponent topBorderColor (String colorValue);

    FComponent rightBorderColor (String colorValue);

    FComponent bottomBorderColor (String colorValue);

    FComponent leftBorderColor (String colorValue);

    String getTopBorderColorHex ();

    String getRightBorderColorHex ();

    String getBottomBorderColorHex ();

    String getLeftBorderColorHex ();

    FComponent borderColor (Color color);

    FComponent borderColor (Color topBottom, Color rightLeft);

    FComponent borderColor (Color top, Color right, Color bottom, Color left);

    FComponent topBorderColor (Color color);

    FComponent rightBorderColor (Color color);

    FComponent bottomBorderColor (Color color);

    FComponent leftBorderColor (Color color);

    Color getTopBorderColor ();

    Color getRightBorderColor ();

    Color getBottomBorderColor ();

    Color getLeftBorderColor ();

    FComponent borderOpacity (double value);

    FComponent borderOpacity (double topBottom, double rightLeft);

    FComponent borderOpacity (double top, double right, double bottom, double left);

    FComponent topBorderOpacity (double value);

    FComponent rightBorderOpacity (double value);

    FComponent bottomBorderOpacity (double value);

    FComponent leftBorderOpacity (double value);

    double getTopBorderOpacity ();

    double getRightBorderOpacity ();

    double getBottomBorderOpacity ();

    double getLeftBorderOpacity ();

    FComponent borderRadius (double value);

    FComponent borderRadius (double tobBottom, double rightLeft);

    FComponent borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft);

    CornerRadii getBorderRadius ();

    FComponent topLeftBorderRadius (double value);

    FComponent topRightBorderRadius (double value);

    FComponent bottomRightBorderRadius (double value);

    FComponent bottomLeftBorderRadius (double value);

    double getTopLeftBorderRadius ();

    double getTopRightBorderRadius ();

    double getBottomRightBorderRadius ();

    double getBottomLeftBorderRadius ();

    FComponent borderInsets (double value);

    FComponent borderInsets (double topBottom, double rightLeft);

    FComponent borderInsets (double top, double right, double bottom, double left);

    Insets getBorderInsets ();

    FComponent topBorderInset (double value);

    FComponent rightBorderInset (double value);

    FComponent bottomBorderInset (double value);

    FComponent leftBorderInset (double value);

    double getTopBorderInset ();

    double getRightBorderInset ();

    double getBottomBorderInset ();

    double getLeftBorderInset ();

    FComponent removeBorder ();

    FComponent removeTopBorder ();

    FComponent removeRightBorder ();

    FComponent removeBottomBorder ();

    FComponent removeLeftBorder ();

    // ******************************************
    //              Border properties           *
    // ******************************************
    // *********************************************************************************************
    // **********************************************************
    //                                                          *
    //                      Custom properties                   *
    //                                                          *
    // **********************************************************
    // *********************************************************************************************
    // **********************************************************
    //                                                          *
    //                       Other properties                   *
    //                                                          *
    // **********************************************************
    // ******************************************
    //              Shape properties           *
    // ******************************************
    FComponent shape (String svg);

    FComponent shape (Shape shape);
    // ******************************************
    //              Shape properties           *
    // ******************************************
    // **********************************************************
    //                                                          *
    //                       Other properties                   *
    //                                                          *
    // **********************************************************
}
