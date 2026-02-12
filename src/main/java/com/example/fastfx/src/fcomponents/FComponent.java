package com.example.fastfx.src.fcomponents;

import com.example.fastfx.src.fcomponents.flistener.ResizeListener;
import com.example.fastfx.src.style.layouts.Margin;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;

public interface FComponent {

    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    FComponent x(double value);
    FComponent x(double value, String unit);
    FComponent x(String rawValue);
    double getX();
    String getRawX();
    FComponent y(double value);
    FComponent y(double value, String unit);
    FComponent y(String rawValue);
    double getY();
    String getRawY();
    FComponent width(double value);
    FComponent width(String rawValue);
    String getRawWidth();
    FComponent height(double value);
    FComponent height(String rawValue);
    String getRawHeight();
    FComponent scaleX(double value);
    FComponent scaleX(String rawValue);
    String getRawScaleX();
    FComponent scaleY(double value);
    FComponent scaleY(String rawValue);
    String getRawScaleY();
    FComponent translateX(double value);
    FComponent translateX(double value, String unit);
    FComponent translateX(String rawValue);
    String getRawTranslateX();
    FComponent translateY(double value);
    FComponent translateY(double value, String unit);
    FComponent translateY(String rawValue);
    String getRawTranslateY();
    FComponent rotate(double value);
    FComponent rotate(String rawValue);
    String getRawRotate();
    FComponent padding(double value);
    FComponent padding(double topBottom, double rightLeft);
    FComponent padding(double top, double right, double bottom, double left);
    FComponent padding(String rawValue);
    String getRawPadding();
    FComponent margin(double value);
    FComponent margin(double topBottom, double rightLeft);
    FComponent margin(double top, double right, double bottom, double left);
    FComponent margin(String rawValue);
    Margin getMargin();
    String getRawMargin();
    FComponent top(double value);
    FComponent top(String rawValue);
    double getTop();
    FComponent right(double value);
    FComponent right(String rawValue);
    double getRight();
    FComponent bottom(double value);
    FComponent bottom(String rawValue);
    double getBottom();
    FComponent left(double value);
    FComponent left(String rawValue);
    double getLeft();
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
    FComponent bgOpacity(double value);
    FComponent bgOpacity(String rawValue);
    double getBgOpacity();
    String getRawBgOpacity();
    FComponent bgRadius(double value);
    FComponent bgRadius(double topLeftBottomRight, double topRightBottomLeft);
    FComponent bgRadius(double topLeft, double topRight, double bottomRight, double bottomLeft);
    FComponent bgRadius(String rawValue);
    CornerRadii getBgRadius();
    String getRawBgRadius();
    FComponent topLeftBgRadius(double value);
    FComponent topLeftBgRadius(String rawValue);
    double getTopLeftBgRadius();
    String getRawTopLeftBgRadius();
    FComponent topRightBgRadius(double value);
    FComponent topRightBgRadius(String rawValue);
    double getTopRightBgRadius();
    String getRawTopRightBgRadius();
    FComponent bottomRightBgRadius(double value);
    FComponent bottomRightBgRadius(String rawValue);
    double getBottomRightBgRadius();
    String getRawBottomRightBgRadius();
    FComponent bottomLeftBgRadius(double value);
    FComponent bottomLeftBgRadius(String rawValue);
    double getBottomLeftBgRadius();
    String getRawBottomLeftBgRadius();
    FComponent bgInsets(double value);
    FComponent bgInsets(double topBottom, double rightLeft);
    FComponent bgInsets(double top, double right, double bottom, double left);
    FComponent bgInsets(String rawValue);
    Insets getBgInsets();
    String getRawBgInsets();
    FComponent topInset(double value);
    double getTopInset();
    FComponent rightInset(double value);
    double getRightInset();
    FComponent bottomInset(double value);
    double getBottomInset();
    FComponent leftInset(double value);
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
    FComponent borderWidth(double value);
    FComponent borderWidth(double topBottom, double rightLeft);
    FComponent borderWidth(double top, double right, double bottom, double left);
    FComponent borderWidth(String rawValue);
    String getRawBorderWidth();
    FComponent borderColor(String colorValue);
    String getBorderColor();
    String getRawBorderColor();
    FComponent borderOpacity(double value);
    FComponent borderOpacity(double topBottom, double rightLeft);
    FComponent borderOpacity(double top, double right, double bottom, double left);
    FComponent borderOpacity(String rawValue);
    String getRawBorderOpacity();
    FComponent borderRadius(double value);
    FComponent borderRadius(double topLeft, double topRight, double bottomRight, double bottomLeft);
    FComponent borderRadius(String rawValue);
    String getRawBorderRadius();
    CornerRadii getBorderRadius();
    FComponent topLeftBorderRadius(double value);
    FComponent topLeftBorderRadius(String rawValue);
    double getTopLeftBorderRadius();
    String getRawTopLeftBorderRadius();
    FComponent topRightBorderRadius(double value);
    FComponent topRightBorderRadius(String rawValue);
    double getTopRightBorderRadius();
    String getRawTopRightBorderRadius();
    FComponent bottomRightBorderRadius(double value);
    FComponent bottomRightBorderRadius(String rawValue);
    double getBottomRightBorderRadius();
    String getRawBottomRightBorderRadius();
    FComponent bottomLeftBorderRadius(double value);
    FComponent bottomLeftBorderRadius(String rawValue);
    double getBottomLeftBorderRadius();
    String getRawBottomLeftBorderRadius();
    FComponent borderInsets(double value);
    FComponent borderInsets(double topBottom, double rightLeft);
    FComponent borderInsets(double top, double right, double bottom, double left);
    FComponent borderInsets(String rawValue);
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
    //    void add(Node node);
    void add(FComponent component);
    void notifyAdded(Region region);
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
