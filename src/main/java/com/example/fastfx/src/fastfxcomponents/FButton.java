package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.FontUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class FButton extends Button implements FLabeled {
    private Margin margin;

    public FButton () {
        this.margin = new Margin(0);
    }

    public FButton (String text) {
        setText(text);
        this.margin = new Margin(0);
    }

    @Override
    public FButton x (double value) {
        setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FButton y (double value) {
        setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FButton width (double value) {
        setPrefWidth(value);
        return this;
    }

    @Override
    public FButton height (double value) {
        setPrefHeight(value);
        return this;
    }

    @Override
    public FButton scaleX (double value) {
        setScaleX(value);
        return this;
    }

    @Override
    public FButton scaleY (double value) {
        setScaleY(value);
        return this;
    }

    @Override
    public FButton translateX (double value) {
        setTranslateX(value);
        return this;
    }

    @Override
    public FButton translateY (double value) {
        setTranslateY(value);
        return this;
    }

    @Override
    public FButton rotate (double value) {
        setRotate(value);
        return this;
    }

    @Override
    public FButton padding (double value) {
        PaddingUtil.setPadding(this, value);
        return this;
    }

    @Override
    public FButton padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FButton topPadding (double value) {
        PaddingUtil.setTopPadding(this, value);
        return this;
    }

    @Override
    public FButton rightPadding (double value) {
        PaddingUtil.setRightPadding(this, value);
        return this;
    }

    @Override
    public FButton bottomPadding (double value) {
        PaddingUtil.setBottomPadding(this, value);
        return this;
    }

    @Override
    public FButton leftPadding (double value) {
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
    public FButton margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FButton margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FButton topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FButton rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FButton bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FButton leftMargin (double value) {
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
    public FButton bgColor (String color) {
        BackgroundUtil.setBackgroundColor(this, color);
        return this;
    }

    @Override
    public FButton bgColor (Color color) {
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
    public FButton bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(this, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FButton bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FButton bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(this, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FButton bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FButton topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FButton topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FButton bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FButton bottomLeftBgRadius (double value) {
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
    public FButton bgInsets (double value) {
        BackgroundUtil.setInsets(this, value);
        return this;
    }

    @Override
    public FButton bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FButton topInset (double value) {
        BackgroundUtil.setTopInset(this, value);
        return this;
    }

    @Override
    public FButton rightInset (double value) {
        BackgroundUtil.setRightInset(this, value);
        return this;
    }

    @Override
    public FButton bottomInset (double value) {
        BackgroundUtil.setBottomInset(this, value);
        return this;
    }

    @Override
    public FButton leftInset (double value) {
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
    public FButton alignment(Pos pos){
        setAlignment(pos);
        return this;
    }

    @Override
    public FButton border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(this, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FButton border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(this, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FButton topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FButton rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FButton bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FButton leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FButton topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FButton rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FButton bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FButton leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FButton borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FButton topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FButton rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FButton bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FButton leftBorderStyle (String borderStyle) {
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
    public FButton borderWidth (double value) {
        BorderUtil.setBorderWidth(this, value);
        return this;
    }

    @Override
    public FButton borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FButton topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(this, value);
        return this;
    }

    @Override
    public FButton rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(this, value);
        return this;
    }

    @Override
    public FButton bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(this, value);
        return this;
    }

    @Override
    public FButton leftBorderWidth (double value) {
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
    public FButton borderColor (String colorValue) {
        BorderUtil.setBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FButton borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FButton topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FButton rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FButton bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FButton leftBorderColor (String colorValue) {
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
    public FButton borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FButton borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(this, top, right, top, right);
        return this;
    }

    @Override
    public FButton topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(this, color);
        return this;
    }

    @Override
    public FButton rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(this, color);
        return this;
    }

    @Override
    public FButton bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(this, color);
        return this;
    }

    @Override
    public FButton leftBorderColor (Color color) {
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
    public FButton borderOpacity (double value) {
        BorderUtil.setBorderOpacity(this, value);
        return this;
    }

    @Override
    public FButton borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FButton topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(this, value);
        return this;
    }

    @Override
    public FButton rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(this, value);
        return this;
    }

    @Override
    public FButton bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(this, value);
        return this;
    }

    @Override
    public FButton leftBorderOpacity (double value) {
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
    public FButton borderRadius (double value) {
        BorderUtil.setBorderRadius(this, value);
        return this;
    }

    @Override
    public FButton borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FButton topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(this, value);
        return this;
    }

    @Override
    public FButton topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(this, value);
        return this;
    }

    @Override
    public FButton bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(this, value);
        return this;
    }

    @Override
    public FButton bottomLeftBorderRadius (double value) {
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
    public FButton borderInsets (double value) {
        BorderUtil.setBorderInsets(this, value);
        return this;
    }

    @Override
    public FButton borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FButton borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FButton topBorderInset (double value) {
        BorderUtil.setTopBorderInset(this, value);
        return this;
    }

    @Override
    public FButton rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(this, value);
        return this;
    }

    @Override
    public FButton bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(this, value);
        return this;
    }

    @Override
    public FButton leftBorderInset (double value) {
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
    public FButton removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public FButton removeTopBorder () {
        BorderUtil.setTopBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(this, 0);
        BorderUtil.setTopRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public FButton removeRightBorder () {
        BorderUtil.setRightBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(this, 0);
        BorderUtil.setBottomRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public FButton removeBottomBorder () {
        BorderUtil.setBottomBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(this, 0);
        BorderUtil.setBottomLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public FButton removeLeftBorder () {
        BorderUtil.setLeftBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(this, 0);
        BorderUtil.setTopLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public FButton shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        setShape(svgPath);
        return this;
    }

    @Override
    public FButton shape (Shape shape) {
        setShape(shape);
        return this;
    }

    @Override
    public FButton font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public FButton font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public FButton font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public FButton font (String fontFamily, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public FButton fontColor (Color color) {
        FontUtil.setFontColor(this, color);
        return this;
    }

    @Override
    public FButton fontColor (String color) {
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
    public FButton fontFamily (String fontFamily) {
        FontUtil.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public FButton fontPosture (FontPosture fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public FButton fontPosture (String fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public FButton fontWeight (FontWeight fontWeight) {
        FontUtil.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public FButton fontSize (double fontSize) {
        FontUtil.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }

    @Override
    public FButton textAlignment(TextAlignment textAlignment){
        setTextAlignment(textAlignment);
        return this;
    }
}
