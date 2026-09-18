package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.FontUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class FHyperlink extends Hyperlink implements FLabeled {
    private Margin margin;

    public FHyperlink () {
        this.margin = new Margin(0, 0, 0, 0);
    }

    public FHyperlink (String text) {
        setText(text);
        this.margin = new Margin(0, 0, 0, 0);
    }

    @Override
    public FHyperlink x (double value) {
        setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FHyperlink y (double value) {
        setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FHyperlink width (double value) {
        setPrefWidth(value);
        return this;
    }

    @Override
    public FHyperlink height (double value) {
        setPrefHeight(value);
        return this;
    }

    @Override
    public FHyperlink scaleX (double value) {
        setScaleX(value);
        return this;
    }

    @Override
    public FHyperlink scaleY (double value) {
        setScaleY(value);
        return this;
    }

    @Override
    public FHyperlink translateX (double value) {
        setTranslateX(value);
        return this;
    }

    @Override
    public FHyperlink translateY (double value) {
        setTranslateY(value);
        return this;
    }

    @Override
    public FHyperlink rotate (double value) {
        setRotate(value);
        return this;
    }

    @Override
    public FHyperlink padding (double value) {
        PaddingUtil.setPadding(this, value);
        return this;
    }

    @Override
    public FHyperlink padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FHyperlink topPadding (double value) {
        PaddingUtil.setTopPadding(this, value);
        return this;
    }

    @Override
    public FHyperlink rightPadding (double value) {
        PaddingUtil.setRightPadding(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomPadding (double value) {
        PaddingUtil.setBottomPadding(this, value);
        return this;
    }

    @Override
    public FHyperlink leftPadding (double value) {
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
    public FHyperlink margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FHyperlink margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FHyperlink topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FHyperlink rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FHyperlink bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FHyperlink leftMargin (double value) {
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
    public FHyperlink bgColor (String color) {
        BackgroundUtil.setBackgroundColor(this, color);
        return this;
    }

    @Override
    public FHyperlink bgColor (Color color) {
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
    public FHyperlink bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(this, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FHyperlink bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FHyperlink bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(this, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FHyperlink bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FHyperlink topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FHyperlink topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomLeftBgRadius (double value) {
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
    public FHyperlink bgInsets (double value) {
        BackgroundUtil.setInsets(this, value);
        return this;
    }

    @Override
    public FHyperlink bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FHyperlink topInset (double value) {
        BackgroundUtil.setTopInset(this, value);
        return this;
    }

    @Override
    public FHyperlink rightInset (double value) {
        BackgroundUtil.setRightInset(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomInset (double value) {
        BackgroundUtil.setBottomInset(this, value);
        return this;
    }

    @Override
    public FHyperlink leftInset (double value) {
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
    public FHyperlink alignment(Pos pos){
        setAlignment(pos);
        return this;
    }

    @Override
    public FHyperlink border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(this, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FHyperlink border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(this, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FHyperlink topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FHyperlink rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FHyperlink bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FHyperlink leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FHyperlink topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FHyperlink rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FHyperlink bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FHyperlink leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FHyperlink borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FHyperlink topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FHyperlink rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FHyperlink bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FHyperlink leftBorderStyle (String borderStyle) {
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
    public FHyperlink borderWidth (double value) {
        BorderUtil.setBorderWidth(this, value);
        return this;
    }

    @Override
    public FHyperlink borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FHyperlink topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(this, value);
        return this;
    }

    @Override
    public FHyperlink rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(this, value);
        return this;
    }

    @Override
    public FHyperlink leftBorderWidth (double value) {
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
    public FHyperlink borderColor (String colorValue) {
        BorderUtil.setBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FHyperlink borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FHyperlink topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FHyperlink rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FHyperlink bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FHyperlink leftBorderColor (String colorValue) {
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
    public FHyperlink borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FHyperlink borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(this, top, right, top, right);
        return this;
    }

    @Override
    public FHyperlink topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(this, color);
        return this;
    }

    @Override
    public FHyperlink rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(this, color);
        return this;
    }

    @Override
    public FHyperlink bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(this, color);
        return this;
    }

    @Override
    public FHyperlink leftBorderColor (Color color) {
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
    public FHyperlink borderOpacity (double value) {
        BorderUtil.setBorderOpacity(this, value);
        return this;
    }

    @Override
    public FHyperlink borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FHyperlink topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(this, value);
        return this;
    }

    @Override
    public FHyperlink rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(this, value);
        return this;
    }

    @Override
    public FHyperlink leftBorderOpacity (double value) {
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
    public FHyperlink borderRadius (double value) {
        BorderUtil.setBorderRadius(this, value);
        return this;
    }

    @Override
    public FHyperlink borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FHyperlink topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(this, value);
        return this;
    }

    @Override
    public FHyperlink topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomLeftBorderRadius (double value) {
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
    public FHyperlink borderInsets (double value) {
        BorderUtil.setBorderInsets(this, value);
        return this;
    }

    @Override
    public FHyperlink borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FHyperlink borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FHyperlink topBorderInset (double value) {
        BorderUtil.setTopBorderInset(this, value);
        return this;
    }

    @Override
    public FHyperlink rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(this, value);
        return this;
    }

    @Override
    public FHyperlink bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(this, value);
        return this;
    }

    @Override
    public FHyperlink leftBorderInset (double value) {
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
    public FHyperlink removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public FHyperlink removeTopBorder () {
        BorderUtil.setTopBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(this, 0);
        BorderUtil.setTopRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public FHyperlink removeRightBorder () {
        BorderUtil.setRightBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(this, 0);
        BorderUtil.setBottomRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public FHyperlink removeBottomBorder () {
        BorderUtil.setBottomBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(this, 0);
        BorderUtil.setBottomLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public FHyperlink removeLeftBorder () {
        BorderUtil.setLeftBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(this, 0);
        BorderUtil.setTopLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public FHyperlink shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        setShape(svgPath);
        return this;
    }

    @Override
    public FHyperlink shape (Shape shape) {
        setShape(shape);
        return this;
    }

    @Override
    public FHyperlink font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public FHyperlink font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public FHyperlink font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public FHyperlink font (String fontFamily, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public FHyperlink fontColor (Color color) {
        FontUtil.setFontColor(this, color);
        return this;
    }

    @Override
    public FHyperlink fontColor (String color) {
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
    public FHyperlink fontFamily (String fontFamily) {
        FontUtil.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public FHyperlink fontPosture (FontPosture fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public FHyperlink fontPosture (String fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public FHyperlink fontWeight (FontWeight fontWeight) {
        FontUtil.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public FHyperlink fontSize (double fontSize) {
        FontUtil.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }

    @Override
    public FHyperlink textAlignment(TextAlignment textAlignment){
        setTextAlignment(textAlignment);
        return this;
    }
}
