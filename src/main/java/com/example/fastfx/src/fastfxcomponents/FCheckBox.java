package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.FontUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.skin.CheckBoxSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class FCheckBox extends CheckBox implements FLabeled {
    private Margin margin;
    private Region currentComponentToStyle;
    private StackPane box;
    private StackPane mark;

    public FCheckBox () {
        this.margin = new Margin(0);
        initElements();
    }

    public FCheckBox (String text) {
        setText(text);
        this.margin = new Margin(0);
        initElements();
    }

    private void initElements () {
        setSkin(new CheckBoxSkin(this));
        this.currentComponentToStyle = this;
        this.box = (StackPane) lookup(".box");
        this.mark = (StackPane) lookup(".mark");
    }

    public StackPane getBox () {
        return box;
    }

    public StackPane getMark () {
        return mark;
    }

    public FCheckBox mark (PseudoElement pseudoElement) {
        currentComponentToStyle = mark;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public FCheckBox box (PseudoElement pseudoElement) {
        currentComponentToStyle = box;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    @Override
    public FCheckBox x (double value) {
        currentComponentToStyle.setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FCheckBox y (double value) {
        currentComponentToStyle.setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FCheckBox width (double value) {
        currentComponentToStyle.setPrefWidth(value);
        return this;
    }

    @Override
    public FCheckBox height (double value) {
        currentComponentToStyle.setPrefHeight(value);
        return this;
    }

    @Override
    public FCheckBox scaleX (double value) {
        currentComponentToStyle.setScaleX(value);
        return this;
    }

    @Override
    public FCheckBox scaleY (double value) {
        currentComponentToStyle.setScaleY(value);
        return this;
    }

    @Override
    public FCheckBox translateX (double value) {
        currentComponentToStyle.setTranslateX(value);
        return this;
    }

    @Override
    public FCheckBox translateY (double value) {
        currentComponentToStyle.setTranslateY(value);
        return this;
    }

    @Override
    public FCheckBox rotate (double value) {
        currentComponentToStyle.setRotate(value);
        return this;
    }

    @Override
    public FCheckBox padding (double value) {
        PaddingUtil.setPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FCheckBox topPadding (double value) {
        PaddingUtil.setTopPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox rightPadding (double value) {
        PaddingUtil.setRightPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomPadding (double value) {
        PaddingUtil.setBottomPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox leftPadding (double value) {
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
    public FCheckBox margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FCheckBox margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FCheckBox topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FCheckBox rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FCheckBox bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FCheckBox leftMargin (double value) {
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
    public FCheckBox bgColor (String color) {
        BackgroundUtil.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FCheckBox bgColor (Color color) {
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
    public FCheckBox bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FCheckBox bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FCheckBox bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FCheckBox topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomLeftBgRadius (double value) {
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
    public FCheckBox bgInsets (double value) {
        BackgroundUtil.setInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FCheckBox topInset (double value) {
        BackgroundUtil.setTopInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox rightInset (double value) {
        BackgroundUtil.setRightInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomInset (double value) {
        BackgroundUtil.setBottomInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox leftInset (double value) {
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
    public FCheckBox alignment(Pos pos){
        setAlignment(pos);
        return this;
    }

    @Override
    public FCheckBox border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(currentComponentToStyle, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FCheckBox border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(currentComponentToStyle, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FCheckBox topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FCheckBox rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FCheckBox bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FCheckBox leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FCheckBox topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FCheckBox rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FCheckBox bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FCheckBox leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FCheckBox borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FCheckBox topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FCheckBox rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FCheckBox bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FCheckBox leftBorderStyle (String borderStyle) {
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
    public FCheckBox borderWidth (double value) {
        BorderUtil.setBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FCheckBox topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox leftBorderWidth (double value) {
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
    public FCheckBox borderColor (String colorValue) {
        BorderUtil.setBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FCheckBox borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FCheckBox topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FCheckBox rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FCheckBox bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FCheckBox leftBorderColor (String colorValue) {
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
    public FCheckBox borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FCheckBox borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(currentComponentToStyle, top, right, top, right);
        return this;
    }

    @Override
    public FCheckBox topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FCheckBox rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FCheckBox bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FCheckBox leftBorderColor (Color color) {
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
    public FCheckBox borderOpacity (double value) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FCheckBox topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox leftBorderOpacity (double value) {
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
    public FCheckBox borderRadius (double value) {
        BorderUtil.setBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FCheckBox topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomLeftBorderRadius (double value) {
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
    public FCheckBox borderInsets (double value) {
        BorderUtil.setBorderInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FCheckBox borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FCheckBox topBorderInset (double value) {
        BorderUtil.setTopBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FCheckBox leftBorderInset (double value) {
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
    public FCheckBox removeBorder () {
        currentComponentToStyle.setBorder(null);
        return this;
    }

    @Override
    public FCheckBox removeTopBorder () {
        BorderUtil.setTopBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FCheckBox removeRightBorder () {
        BorderUtil.setRightBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FCheckBox removeBottomBorder () {
        BorderUtil.setBottomBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FCheckBox removeLeftBorder () {
        BorderUtil.setLeftBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FCheckBox shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        currentComponentToStyle.setShape(svgPath);
        return this;
    }

    @Override
    public FCheckBox shape (Shape shape) {
        currentComponentToStyle.setShape(shape);
        return this;
    }

    @Override
    public FCheckBox font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public FCheckBox font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public FCheckBox font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public FCheckBox font (String fontFamily, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public FCheckBox fontColor (Color color) {
        FontUtil.setFontColor(this, color);
        return this;
    }

    @Override
    public FCheckBox fontColor (String color) {
        FontUtil.setFontColor(this, color);
        return this;
    }

    @Override
    public Color getFontColor () {
        return FontUtil.getFontColor(this);
    }

    @Override
    public String getFontColorHex () {
        return FontUtil.getFontColorHex(this);
    }

    @Override
    public FCheckBox fontFamily (String fontFamily) {
        FontUtil.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public FCheckBox fontPosture (FontPosture fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public FCheckBox fontPosture (String fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public FCheckBox fontWeight (FontWeight fontWeight) {
        FontUtil.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public FCheckBox fontSize (double fontSize) {
        FontUtil.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }

    @Override
    public FCheckBox textAlignment(TextAlignment textAlignment){
        setTextAlignment(textAlignment);
        return this;
    }
}
