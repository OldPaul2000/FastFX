package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.FontUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class FLabel extends Label implements FLabeled {
    private Margin margin;

    public FLabel () {
        this.margin = new Margin(0, 0, 0, 0);
    }

    public FLabel (String text) {
        setText(text);
        this.margin = new Margin(0, 0, 0, 0);
    }

    @Override
    public FLabel x (double value) {
        setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FLabel y (double value) {
        setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FLabel width (double value) {
        setPrefWidth(value);
        return this;
    }

    @Override
    public FLabel height (double value) {
        setPrefHeight(value);
        return this;
    }

    @Override
    public FLabel scaleX (double value) {
        setScaleX(value);
        return this;
    }

    @Override
    public FLabel scaleY (double value) {
        setScaleY(value);
        return this;
    }

    @Override
    public FLabel translateX (double value) {
        setTranslateX(value);
        return this;
    }

    @Override
    public FLabel translateY (double value) {
        setTranslateY(value);
        return this;
    }

    @Override
    public FLabel rotate (double value) {
        setRotate(value);
        return this;
    }

    @Override
    public FLabel padding (double value) {
        PaddingUtil.setPadding(this, value);
        return this;
    }

    @Override
    public FLabel padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel topPadding (double value) {
        PaddingUtil.setTopPadding(this, value);
        return this;
    }

    @Override
    public FLabel rightPadding (double value) {
        PaddingUtil.setRightPadding(this, value);
        return this;
    }

    @Override
    public FLabel bottomPadding (double value) {
        PaddingUtil.setBottomPadding(this, value);
        return this;
    }

    @Override
    public FLabel leftPadding (double value) {
        PaddingUtil.setLeftPadding(this, value);
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
    public FLabel margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FLabel margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FLabel topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FLabel rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FLabel bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FLabel leftMargin (double value) {
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
    public FLabel bgColor (String color) {
        BackgroundUtil.setBackgroundColor(this, color);
        return this;
    }

    @Override
    public FLabel bgColor (Color color) {
        BackgroundUtil.setBackgroundColor(this, color);
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
    public FLabel bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(this, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FLabel bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FLabel bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(this, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FLabel bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FLabel topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FLabel topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FLabel bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FLabel bottomLeftBgRadius (double value) {
        BackgroundUtil.setBottomLeftBackgroundRadius(this, value);
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
    public FLabel bgInsets (double value) {
        BackgroundUtil.setInsets(this, value);
        return this;
    }

    @Override
    public FLabel bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FLabel topInset (double value) {
        BackgroundUtil.setTopInset(this, value);
        return this;
    }

    @Override
    public FLabel rightInset (double value) {
        BackgroundUtil.setRightInset(this, value);
        return this;
    }

    @Override
    public FLabel bottomInset (double value) {
        BackgroundUtil.setBottomInset(this, value);
        return this;
    }

    @Override
    public FLabel leftInset (double value) {
        BackgroundUtil.setLeftInset(this, value);
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
    public FLabel alignment(Pos pos){
        setAlignment(pos);
        return this;
    }

    @Override
    public FLabel border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(this, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FLabel border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(this, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FLabel topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FLabel topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FLabel rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FLabel bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FLabel leftBorderStyle (String borderStyle) {
        BorderUtil.setLeftBorderStyle(this, borderStyle);
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
    public FLabel borderWidth (double value) {
        BorderUtil.setBorderWidth(this, value);
        return this;
    }

    @Override
    public FLabel borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(this, value);
        return this;
    }

    @Override
    public FLabel rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(this, value);
        return this;
    }

    @Override
    public FLabel bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(this, value);
        return this;
    }

    @Override
    public FLabel leftBorderWidth (double value) {
        BorderUtil.setLeftBorderWidth(this, value);
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
    public FLabel borderColor (String colorValue) {
        BorderUtil.setBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FLabel borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FLabel rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FLabel bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FLabel leftBorderColor (String colorValue) {
        BorderUtil.setLeftBorderColor(this, colorValue);
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
    public FLabel borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FLabel borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(this, top, right, top, right);
        return this;
    }

    @Override
    public FLabel topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(this, color);
        return this;
    }

    @Override
    public FLabel rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(this, color);
        return this;
    }

    @Override
    public FLabel bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(this, color);
        return this;
    }

    @Override
    public FLabel leftBorderColor (Color color) {
        BorderUtil.setLeftBorderColor(this, color);
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
    public FLabel borderOpacity (double value) {
        BorderUtil.setBorderOpacity(this, value);
        return this;
    }

    @Override
    public FLabel borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(this, value);
        return this;
    }

    @Override
    public FLabel rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(this, value);
        return this;
    }

    @Override
    public FLabel bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(this, value);
        return this;
    }

    @Override
    public FLabel leftBorderOpacity (double value) {
        BorderUtil.setLeftBorderOpacity(this, value);
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
    public FLabel borderRadius (double value) {
        BorderUtil.setBorderRadius(this, value);
        return this;
    }

    @Override
    public FLabel borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FLabel topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(this, value);
        return this;
    }

    @Override
    public FLabel topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(this, value);
        return this;
    }

    @Override
    public FLabel bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(this, value);
        return this;
    }

    @Override
    public FLabel bottomLeftBorderRadius (double value) {
        BorderUtil.setBottomLeftBorderRadius(this, value);
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
    public FLabel borderInsets (double value) {
        BorderUtil.setBorderInsets(this, value);
        return this;
    }

    @Override
    public FLabel borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FLabel topBorderInset (double value) {
        BorderUtil.setTopBorderInset(this, value);
        return this;
    }

    @Override
    public FLabel rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(this, value);
        return this;
    }

    @Override
    public FLabel bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(this, value);
        return this;
    }

    @Override
    public FLabel leftBorderInset (double value) {
        BorderUtil.setLeftBorderInset(this, value);
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
    public FLabel removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public FLabel removeTopBorder () {
        BorderUtil.setTopBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(this, 0);
        BorderUtil.setTopRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public FLabel removeRightBorder () {
        BorderUtil.setRightBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(this, 0);
        BorderUtil.setBottomRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public FLabel removeBottomBorder () {
        BorderUtil.setBottomBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(this, 0);
        BorderUtil.setBottomLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public FLabel removeLeftBorder () {
        BorderUtil.setLeftBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(this, 0);
        BorderUtil.setTopLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public FLabel shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        setShape(svgPath);
        return this;
    }

    @Override
    public FLabel shape (Shape shape) {
        setShape(shape);
        return this;
    }

    @Override
    public FLabel font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public FLabel font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public FLabel font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public FLabel font (String fontFamily, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public FLabel fontColor (Color color) {
        FontUtil.setFontColor(this, color);
        return this;
    }

    @Override
    public FLabel fontColor (String color) {
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
    public FLabel fontFamily (String fontFamily) {
        FontUtil.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public FLabel fontPosture (FontPosture fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public FLabel fontPosture (String fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public FLabel fontWeight (FontWeight fontWeight) {
        FontUtil.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public FLabel fontSize (double fontSize) {
        FontUtil.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }

    @Override
    public FLabel textAlignment(TextAlignment textAlignment){
        setTextAlignment(textAlignment);
        return this;
    }
}
