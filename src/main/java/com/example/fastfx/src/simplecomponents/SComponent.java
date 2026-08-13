package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.style.layouts.Margin;
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
public interface SComponent {
    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    SComponent x(double value);
    double getX();
    SComponent y(double value);
    double getY();
    SComponent width(double value);
    SComponent height(double value);
    SComponent scaleX(double value);
    SComponent scaleY(double value);
    SComponent translateX(double value);
    SComponent translateY(double value);
    SComponent rotate(double value);

    SComponent padding(double value);
    SComponent padding(double topBottom, double rightLeft);
    SComponent padding(double top, double right, double bottom, double left);
    SComponent topPadding(double value);
    SComponent rightPadding(double value);
    SComponent bottomPadding(double value);
    SComponent leftPadding(double value);
    double getTopPadding();
    double getRightPadding();
    double getBottomPadding();
    double getLeftPadding();

    SComponent margin(double value);
    SComponent margin(double topBottom, double rightLeft);
    SComponent margin(double top, double right, double bottom, double left);
    Margin getMargin();
    SComponent topMargin(double value);
    SComponent rightMargin(double value);
    SComponent bottomMargin(double value);
    SComponent leftMargin(double value);
    double getTopMargin();
    double getRightMargin();
    double getBottomMargin();
    double getLeftMargin();
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
    SComponent bgColor(String color);
    SComponent bgColor(Color color);
    String getBgColorHex();
    Color getBgColor();

    SComponent bgOpacity(double value);
    double getBgOpacity();

    SComponent bgRadius(double value);
    SComponent bgRadius(double topLeftBottomRight, double topRightBottomLeft);
    SComponent bgRadius(double topLeft, double topRight, double bottomRight, double bottomLeft);
    CornerRadii getBgRadius();
    SComponent topLeftBgRadius(double value);
    SComponent topRightBgRadius(double value);
    SComponent bottomRightBgRadius(double value);
    SComponent bottomLeftBgRadius(double value);
    double getTopLeftBgRadius();
    double getTopRightBgRadius();
    double getBottomRightBgRadius();
    double getBottomLeftBgRadius();

    SComponent bgInsets(double value);
    SComponent bgInsets(double topBottom, double rightLeft);
    SComponent bgInsets(double top, double right, double bottom, double left);
    Insets getBgInsets();
    SComponent topInset(double value);
    SComponent rightInset(double value);
    SComponent bottomInset(double value);
    SComponent leftInset(double value);
    double getTopInset();
    double getRightInset();
    double getBottomInset();
    double getLeftInset();
    // ******************************************
    //            Background properties         *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Border properties           *
    // ******************************************
    SComponent border(String colorValue, String borderStyle,
                      double topLeftRadius, double topRightRadius, double bottomRightRadius,
                      double bottomLeftRadius, double topWidth, double rightWidth,
                      double bottomWidth, double leftWidth);
    SComponent border(String colorValue, String borderStyle, double radius, double borderWidth);
    SComponent topBorder(String colorValue, String borderStyle, double borderWidth);
    SComponent rightBorder(String colorValue, String borderStyle, double borderWidth);
    SComponent bottomBorder(String colorValue, String borderStyle, double borderWidth);
    SComponent leftBorder(String colorValue, String borderStyle, double borderWidth);
    SComponent topBorder(Color color, String borderStyle, double borderWidth);
    SComponent rightBorder(Color color, String borderStyle, double borderWidth);
    SComponent bottomBorder(Color color, String borderStyle, double borderWidth);
    SComponent leftBorder(Color color, String borderStyle, double borderWidth);

    SComponent borderStyle(String borderStyle);
    SComponent topBorderStyle(String borderStyle);
    SComponent rightBorderStyle(String borderStyle);
    SComponent bottomBorderStyle(String borderStyle);
    SComponent leftBorderStyle(String borderStyle);
    String getTopBorderStyle();
    String getRightBorderStyle();
    String getBottomBorderStyle();
    String getLeftBorderStyle();

    SComponent borderWidth(double value);
    SComponent borderWidth(double topBottom, double rightLeft);
    SComponent borderWidth(double top, double right, double bottom, double left);
    SComponent topBorderWidth(double value);
    SComponent rightBorderWidth(double value);
    SComponent bottomBorderWidth(double value);
    SComponent leftBorderWidth(double value);
    double getTopBorderWidth();
    double getRightBorderWidth();
    double getBottomBorderWidth();
    double getLeftBorderWidth();

    SComponent borderColor(String colorValue);
    SComponent borderColor(String topBottom, String rightLeft);
    SComponent borderColor(String top, String right, String bottom, String left);
    SComponent topBorderColor(String colorValue);
    SComponent rightBorderColor(String colorValue);
    SComponent bottomBorderColor(String colorValue);
    SComponent leftBorderColor(String colorValue);
    String getTopBorderColorHex();
    String getRightBorderColorHex();
    String getBottomBorderColorHex();
    String getLeftBorderColorHex();

    SComponent borderColor(Color color);
    SComponent borderColor(Color topBottom, Color rightLeft);
    SComponent borderColor(Color top, Color right, Color bottom, Color left);
    SComponent topBorderColor(Color color);
    SComponent rightBorderColor(Color color);
    SComponent bottomBorderColor(Color color);
    SComponent leftBorderColor(Color color);
    Color getTopBorderColor();
    Color getRightBorderColor();
    Color getBottomBorderColor();
    Color getLeftBorderColor();

    SComponent borderOpacity(double value);
    SComponent borderOpacity(double topBottom, double rightLeft);
    SComponent borderOpacity(double top, double right, double bottom, double left);
    SComponent topBorderOpacity(double value);
    SComponent rightBorderOpacity(double value);
    SComponent bottomBorderOpacity(double value);
    SComponent leftBorderOpacity(double value);
    double getTopBorderOpacity();
    double getRightBorderOpacity();
    double getBottomBorderOpacity();
    double getLeftBorderOpacity();

    SComponent borderRadius(double value);
    SComponent borderRadius(double tobBottom, double rightLeft);
    SComponent borderRadius(double topLeft, double topRight, double bottomRight, double bottomLeft);
    CornerRadii getBorderRadius();
    SComponent topLeftBorderRadius(double value);
    SComponent topRightBorderRadius(double value);
    SComponent bottomRightBorderRadius(double value);
    SComponent bottomLeftBorderRadius(double value);
    double getTopLeftBorderRadius();
    double getTopRightBorderRadius();
    double getBottomRightBorderRadius();
    double getBottomLeftBorderRadius();

    SComponent borderInsets(double value);
    SComponent borderInsets(double topBottom, double rightLeft);
    SComponent borderInsets(double top, double right, double bottom, double left);
    Insets getBorderInsets();
    SComponent topBorderInset(double value);
    SComponent rightBorderInset(double value);
    SComponent bottomBorderInset(double value);
    SComponent leftBorderInset(double value);
    double getTopBorderInset();
    double getRightBorderInset();
    double getBottomBorderInset();
    double getLeftBorderInset();

    SComponent removeBorder();
    SComponent removeTopBorder();
    SComponent removeRightBorder();
    SComponent removeBottomBorder();
    SComponent removeLeftBorder();
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
    SComponent shape(String svg);
    SComponent shape(Shape shape);
    // ******************************************
    //              Shape properties           *
    // ******************************************
    // **********************************************************
    //                                                          *
    //                       Other properties                   *
    //                                                          *
    // **********************************************************
}
