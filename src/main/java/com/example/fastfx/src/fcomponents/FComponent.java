package com.example.fastfx.src.fcomponents;

import com.example.fastfx.src.fcomponents.flistener.ResizeListener;
import com.example.fastfx.src.style.layouts.Margin;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.CornerRadii;

public interface FComponent {

    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    FComponent x(double x);
    FComponent x(double x, String unit);
    FComponent x(String rawValue);
    double getX();
    String getRawX();
    FComponent y(double y);
    FComponent y(double y, String unit);
    FComponent y(String rawValue);
    double getY();
    String getRawY();
    FComponent width(double value);
    FComponent width(String value);
    String getRawWidth();
    FComponent height(double value);
    FComponent height(String value);
    String getRawHeight();
    FComponent scaleX(double value);
    FComponent scaleX(String value);
    String getRawScaleX();
    FComponent scaleY(double value);
    FComponent scaleY(String value);
    String getRawScaleY();
    FComponent translateX(double translateX);
    FComponent translateX(double translateX, String unit);
    FComponent translateX(String rawValue);
    String getRawTranslateX();
    FComponent translateY(double translateY);
    FComponent translateY(double translateY, String unit);
    FComponent translateY(String rawValue);
    String getRawTranslateY();
    FComponent rotate(double value);
    FComponent rotate(String rawValue);
    String getRawRotate();
    FComponent padding(double padding);
    FComponent padding(double topBottom, double rightLeft);
    FComponent padding(double top, double right, double bottom, double left);
    FComponent padding(String rawValue);
    String getRawPadding();
    FComponent margin(double margin);
    FComponent margin(double topBottom, double rightLeft);
    FComponent margin(double top, double right, double bottom, double left);
    FComponent margin(String rawValue);
    Margin getMargin();
    String getRawMargin();
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
    FComponent bgColor(String color);
    String getBgColor();
    String getRawBgColor();
    FComponent bgOpacity(double opacity);
    FComponent bgOpacity(String value);
    double getBgOpacity();
    String getRawBgOpacity();
    FComponent bgRadius(double radius);
    FComponent bgRadius(double topLeftBottomRight, double topRightBottomLeft);
    FComponent bgRadius(double topLeft, double topRight, double bottomRight, double bottomLeft);
    FComponent bgRadius(String value);
    CornerRadii getBgRadius();
    String getRawBgRadius();
    FComponent topLeftBgRadius(double radius);
    FComponent topLeftBgRadius(String value);
    double getTopLeftBgRadius();
    String getRawTopLeftBgRadius();
    FComponent topRightBgRadius(double radius);
    FComponent topRightBgRadius(String value);
    double getTopRightBgRadius();
    String getRawTopRightBgRadius();
    FComponent bottomRightBgRadius(double radius);
    FComponent bottomRightBgRadius(String value);
    double getBottomRightBgRadius();
    String getRawBottomRightBgRadius();
    FComponent bottomLeftBgRadius(double radius);
    FComponent bottomLeftBgRadius(String value);
    double getBottomLeftBgRadius();
    String getRawBottomLeftBgRadius();
    FComponent bgInsets(double insets);
    FComponent bgInsets(double topBottom, double rightLeft);
    FComponent bgInsets(double top, double right, double bottom, double left);
    FComponent bgInsets(String value);
    Insets getBgInsets();
    String getRawBgInsets();
    FComponent topInset(double inset);
    double getTopInset();
    FComponent rightInset(double inset);
    double getRightInset();
    FComponent bottomInset(double inset);
    double getBottomInset();
    FComponent leftInset(double inset);
    double getLeftInset();
    // ******************************************
    //            Background properties         *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Border properties           *
    // ******************************************
    FComponent border(String colorValue, String borderStyle,
                   double topLeftRadius, double topRightRadius, double bottomRightRadius,
                   double bottomLeftRadius, double topWidth, double rightWidth,
                   double bottomWidth, double leftWidth);
    FComponent border(String colorValue, String borderStyle, double radius, double borderWidth);
    FComponent border(String border);
    String getRawBorder();
    FComponent topBorder(String colorValue, String borderStyle, double borderWidth);
    FComponent topBorder(String topBorder);
    String getRawTopBorder();
    FComponent rightBorder(String colorValue, String borderStyle, double borderWidth);
    FComponent rightBorder(String rightBorder);
    String getRawRightBorder();
    FComponent bottomBorder(String colorValue, String borderStyle, double borderWidth);
    FComponent bottomBorder(String bottomBorder);
    String getRawBottomBorder();
    FComponent leftBorder(String colorValue, String borderStyle, double borderWidth);
    FComponent leftBorder(String leftBorder);
    String getRawLeftBorder();
    FComponent borderStyle(String borderStyle);
    String getBorderStyle();
    FComponent borderWidth(double width);
    FComponent borderWidth(double topBottom, double rightLeft);
    FComponent borderWidth(double top, double right, double bottom, double left);
    FComponent borderWidth(String borderWidth);
    String getRawBorderWidth();
    FComponent borderColor(String colorValue);
    String getBorderColor();
    String getRawBorderColor();
    FComponent borderOpacity(double opacity);
    FComponent borderOpacity(double topBottom, double rightLeft);
    FComponent borderOpacity(double top, double right, double bottom, double left);
    FComponent borderOpacity(String borderOpacity);
    String getRawBorderOpacity();
    FComponent borderRadius(double radius);
    FComponent borderRadius(double topLeft, double topRight, double bottomRight, double bottomLeft);
    FComponent borderRadius(String borderRadius);
    String getRawBorderRadius();
    CornerRadii getBorderRadius();
    FComponent topLeftBorderRadius(double radius);
    FComponent topLeftBorderRadius(String radius);
    double getTopLeftBorderRadius();
    String getRawTopLeftBorderRadius();
    FComponent topRightBorderRadius(double radius);
    FComponent topRightBorderRadius(String radius);
    double getTopRightBorderRadius();
    String getRawTopRightBorderRadius();
    FComponent bottomRightBorderRadius(double radius);
    FComponent bottomRightBorderRadius(String radius);
    double getBottomRightBorderRadius();
    String getRawBottomRightBorderRadius();
    FComponent bottomLeftBorderRadius(double radius);
    FComponent bottomLeftBorderRadius(String radius);
    double getBottomLeftBorderRadius();
    String getRawBottomLeftBorderRadius();
    FComponent borderInsets(double inset);
    FComponent borderInsets(double topBottom, double rightLeft);
    FComponent borderInsets(double top, double right, double bottom, double left);
    FComponent borderInsets(String borderInsets);
    Insets getBorderInsets();
    String getRawBorderInsets();
    void removeBorder();
    void removeTopBorder();
    void removeRightBorder();
    void removeBottomBorder();
    void removeLeftBorder();
    // ******************************************
    //              Border properties           *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //                UI properties             *
    // ******************************************
    void add(Node node);
    // ******************************************
    //                UI properties             *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Custom listeners            *
    // ******************************************
    void resizeListener(ResizeListener resizeListener);
    void removeResizeListener(ResizeListener resizeListener);
    // ******************************************
    //              Custom listeners            *
    // ******************************************
    // **********************************************************
    //                                                          *
    //                      Custom properties                   *
    //                                                          *
    // **********************************************************
}
