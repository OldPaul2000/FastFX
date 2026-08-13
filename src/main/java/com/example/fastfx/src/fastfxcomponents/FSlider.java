package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;
import javafx.scene.control.skin.SliderSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class FSlider extends Slider implements FComponent {
    private Margin margin;
    private Region currentComponentToStyle;
    private StackPane track;
    private StackPane thumb;
    private NumberAxis axis;
    private Path minorTicks;
    private Path majorTicks;

    public FSlider () {
        this.margin = new Margin(0);
        initElements();
        setOrientation(Orientation.VERTICAL);
    }

    private void initElements () {
        setSkin(new SliderSkin(this));
        this.currentComponentToStyle = this;
        this.track = (StackPane) lookup(".track");
        this.thumb = (StackPane) lookup(".thumb");
    }

    public StackPane getTrack () {
        return track;
    }

    public StackPane getThumb () {
        return thumb;
    }

    public NumberAxis getAxis () {
        return axis;
    }

    public Path getMinorTicks () {
        return minorTicks;
    }

    public Path getMajorTicks () {
        return majorTicks;
    }

    public FSlider track (PseudoElement pseudoElement) {
        currentComponentToStyle = track;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public FSlider thumb (PseudoElement pseudoElement) {
        currentComponentToStyle = thumb;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    @Override
    public FSlider x (double value) {
        currentComponentToStyle.setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FSlider y (double value) {
        currentComponentToStyle.setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FSlider width (double value) {
        currentComponentToStyle.setPrefWidth(value);
        return this;
    }

    @Override
    public FSlider height (double value) {
        currentComponentToStyle.setPrefHeight(value);
        return this;
    }

    @Override
    public FSlider scaleX (double value) {
        currentComponentToStyle.setScaleX(value);
        return this;
    }

    @Override
    public FSlider scaleY (double value) {
        currentComponentToStyle.setScaleY(value);
        return this;
    }

    @Override
    public FSlider translateX (double value) {
        currentComponentToStyle.setTranslateX(value);
        return this;
    }

    @Override
    public FSlider translateY (double value) {
        currentComponentToStyle.setTranslateY(value);
        return this;
    }

    @Override
    public FSlider rotate (double value) {
        currentComponentToStyle.setRotate(value);
        return this;
    }

    @Override
    public FSlider padding (double value) {
        PaddingUtil.setPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FSlider topPadding (double value) {
        PaddingUtil.setTopPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider rightPadding (double value) {
        PaddingUtil.setRightPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomPadding (double value) {
        PaddingUtil.setBottomPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider leftPadding (double value) {
        PaddingUtil.setLeftPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopPadding () {
        return PaddingUtil.getTopPadding(this);
    }

    @Override
    public double getRightPadding () {
        return PaddingUtil.getRightPadding(this);
    }

    @Override
    public double getBottomPadding () {
        return PaddingUtil.getBottomPadding(this);
    }

    @Override
    public double getLeftPadding () {
        return PaddingUtil.getLeftPadding(this);
    }

    @Override
    public FSlider margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FSlider margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FSlider topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FSlider rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FSlider bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FSlider leftMargin (double value) {
        margin.setLeft(value);
        return this;
    }

    @Override
    public double getTopMargin () {
        return margin.getTop();
    }

    @Override
    public double getRightMargin () {
        return margin.getRight();
    }

    @Override
    public double getBottomMargin () {
        return margin.getBottom();
    }

    @Override
    public double getLeftMargin () {
        return margin.getLeft();
    }

    @Override
    public FSlider bgColor (String color) {
        BackgroundUtil.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FSlider bgColor (Color color) {
        BackgroundUtil.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public String getBgColorHex () {
        return BackgroundUtil.getBackgroundHexColor(this);
    }

    @Override
    public Color getBgColor () {
        return BackgroundUtil.getBackgroundColor(this);
    }

    @Override
    public FSlider bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FSlider bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FSlider bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FSlider topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomLeftBgRadius (double value) {
        BackgroundUtil.setBottomLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopLeftBgRadius () {
        return BackgroundUtil.getTopLeftBackgroundRadius(this);
    }

    @Override
    public double getTopRightBgRadius () {
        return BackgroundUtil.getTopRightBackgroundRadius(this);
    }

    @Override
    public double getBottomRightBgRadius () {
        return BackgroundUtil.getBottomRightBackgroundRadius(this);
    }

    @Override
    public double getBottomLeftBgRadius () {
        return BackgroundUtil.getBottomLeftBackgroundRadius(this);
    }

    @Override
    public FSlider bgInsets (double value) {
        BackgroundUtil.setInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FSlider topInset (double value) {
        BackgroundUtil.setTopInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider rightInset (double value) {
        BackgroundUtil.setRightInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomInset (double value) {
        BackgroundUtil.setBottomInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider leftInset (double value) {
        BackgroundUtil.setLeftInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopInset () {
        return BackgroundUtil.getBackgroundInsets(this).getTop();
    }

    @Override
    public double getRightInset () {
        return BackgroundUtil.getBackgroundInsets(this).getRight();
    }

    @Override
    public double getBottomInset () {
        return BackgroundUtil.getBackgroundInsets(this).getBottom();
    }

    @Override
    public double getLeftInset () {
        return BackgroundUtil.getBackgroundInsets(this).getLeft();
    }

    @Override
    public FSlider border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(currentComponentToStyle, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FSlider border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(currentComponentToStyle, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FSlider topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSlider rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSlider bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSlider leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSlider topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSlider rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSlider bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSlider leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSlider borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FSlider topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FSlider rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FSlider bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FSlider leftBorderStyle (String borderStyle) {
        BorderUtil.setLeftBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public String getTopBorderStyle () {
        return BorderUtil.getTopBorderStyle(this);
    }

    @Override
    public String getRightBorderStyle () {
        return BorderUtil.getRightBorderStyle(this);
    }

    @Override
    public String getBottomBorderStyle () {
        return BorderUtil.getBottomBorderStyle(this);
    }

    @Override
    public String getLeftBorderStyle () {
        return BorderUtil.getLeftBorderStyle(this);
    }

    @Override
    public FSlider borderWidth (double value) {
        BorderUtil.setBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FSlider topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider leftBorderWidth (double value) {
        BorderUtil.setLeftBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopBorderWidth () {
        return BorderUtil.getTopBorderWidth(this);
    }

    @Override
    public double getRightBorderWidth () {
        return BorderUtil.getRightBorderWidth(this);
    }

    @Override
    public double getBottomBorderWidth () {
        return BorderUtil.getBottomBorderWidth(this);
    }

    @Override
    public double getLeftBorderWidth () {
        return BorderUtil.getLeftBorderWidth(this);
    }

    @Override
    public FSlider borderColor (String colorValue) {
        BorderUtil.setBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FSlider borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FSlider topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FSlider rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FSlider bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FSlider leftBorderColor (String colorValue) {
        BorderUtil.setLeftBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public String getTopBorderColorHex () {
        return BorderUtil.getTopBorderColor(this);
    }

    @Override
    public String getRightBorderColorHex () {
        return BorderUtil.getRightBorderColor(this);
    }

    @Override
    public String getBottomBorderColorHex () {
        return BorderUtil.getBottomBorderColor(this);
    }

    @Override
    public String getLeftBorderColorHex () {
        return BorderUtil.getLeftBorderColor(this);
    }

    @Override
    public FSlider borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FSlider borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(currentComponentToStyle, top, right, top, right);
        return this;
    }

    @Override
    public FSlider topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FSlider rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FSlider bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FSlider leftBorderColor (Color color) {
        BorderUtil.setLeftBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public Color getTopBorderColor () {
        return Color.valueOf(BorderUtil.getTopBorderColor(this));
    }

    @Override
    public Color getRightBorderColor () {
        return Color.valueOf(BorderUtil.getRightBorderColor(this));
    }

    @Override
    public Color getBottomBorderColor () {
        return Color.valueOf(BorderUtil.getBottomBorderColor(this));
    }

    @Override
    public Color getLeftBorderColor () {
        return Color.valueOf(BorderUtil.getLeftBorderColor(this));
    }

    @Override
    public FSlider borderOpacity (double value) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FSlider topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider leftBorderOpacity (double value) {
        BorderUtil.setLeftBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopBorderOpacity () {
        return BorderUtil.getTopBorderOpacity(this);
    }

    @Override
    public double getRightBorderOpacity () {
        return BorderUtil.getRightBorderOpacity(this);
    }

    @Override
    public double getBottomBorderOpacity () {
        return BorderUtil.getBottomBorderOpacity(this);
    }

    @Override
    public double getLeftBorderOpacity () {
        return BorderUtil.getLeftBorderOpacity(this);
    }

    @Override
    public FSlider borderRadius (double value) {
        BorderUtil.setBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FSlider topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomLeftBorderRadius (double value) {
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopLeftBorderRadius () {
        return BorderUtil.getBorderRadius(this).getTopLeftHorizontalRadius();
    }

    @Override
    public double getTopRightBorderRadius () {
        return BorderUtil.getBorderRadius(this).getTopRightHorizontalRadius();
    }

    @Override
    public double getBottomRightBorderRadius () {
        return BorderUtil.getBorderRadius(this).getBottomRightHorizontalRadius();
    }

    @Override
    public double getBottomLeftBorderRadius () {
        return BorderUtil.getBorderRadius(this).getBottomLeftHorizontalRadius();
    }

    @Override
    public FSlider borderInsets (double value) {
        BorderUtil.setBorderInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSlider borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FSlider topBorderInset (double value) {
        BorderUtil.setTopBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSlider leftBorderInset (double value) {
        BorderUtil.setLeftBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopBorderInset () {
        return BorderUtil.getBorderInsets(this).getTop();
    }

    @Override
    public double getRightBorderInset () {
        return BorderUtil.getBorderInsets(this).getRight();
    }

    @Override
    public double getBottomBorderInset () {
        return BorderUtil.getBorderInsets(this).getBottom();
    }

    @Override
    public double getLeftBorderInset () {
        return BorderUtil.getBorderInsets(this).getLeft();
    }

    @Override
    public FSlider removeBorder () {
        currentComponentToStyle.setBorder(null);
        return this;
    }

    @Override
    public FSlider removeTopBorder () {
        BorderUtil.setTopBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FSlider removeRightBorder () {
        BorderUtil.setRightBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FSlider removeBottomBorder () {
        BorderUtil.setBottomBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FSlider removeLeftBorder () {
        BorderUtil.setLeftBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FSlider shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        currentComponentToStyle.setShape(svgPath);
        return this;
    }

    @Override
    public FSlider shape (Shape shape) {
        currentComponentToStyle.setShape(shape);
        return this;
    }

    // Slider-related methods
    public FSlider orientation (Orientation orientation) {
        setOrientation(orientation);
        return this;
    }

    public FSlider orientation (String orientation) {
        orientation = orientation.toLowerCase();
        if (orientation.equals("horizontal")) {
            setOrientation(Orientation.HORIZONTAL);
        }
        else if (orientation.equals("vertical")) {
            setOrientation(Orientation.VERTICAL);
        }
        return this;
    }

    public FSlider min (double min) {
        setMin(min);
        return this;
    }

    public FSlider max (double max) {
        setMax(max);
        return this;
    }

    public FSlider incrementStep (double step) {
        setBlockIncrement(step);
        return this;
    }

    public FSlider majorTickUnit (double value) {
        setMajorTickUnit(value);
        return this;
    }

    public FSlider minorTickCount (int value) {
        setMinorTickCount(value);
        return this;
    }

    public FSlider showTickLabels (boolean show) {
        setShowTickLabels(show);
        if (show && axis == null) {
            axis = (NumberAxis) lookup(".axis");
            minorTicks = (Path) lookup(".axis-minor-tick-mark");
            majorTicks = (Path) lookup(".axis-tick-mark");
        }
        return this;
    }

    public FSlider showTickMarks (boolean show) {
        setShowTickMarks(show);
        if (show && axis == null) {
            axis = (NumberAxis) lookup(".axis");
            minorTicks = (Path) lookup(".axis-minor-tick-mark");
            majorTicks = (Path) lookup(".axis-tick-mark");
        }
        return this;
    }

    public FSlider snapToTicks (boolean snap) {
        setSnapToTicks(snap);
        return this;
    }
}
