package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.FontUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.skin.RadioButtonSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class FRadioButton extends RadioButton implements FLabeled {
    private Margin margin;
    private Region currentComponentToStyle;
    private StackPane radio;
    private StackPane dot;

    public FRadioButton () {
        this.margin = new Margin(0);
        initElements();
    }

    public FRadioButton (String text) {
        setText(text);
        this.margin = new Margin(0);
        initElements();
    }

    private void initElements () {
        setSkin(new RadioButtonSkin(this));
        this.currentComponentToStyle = this;
        this.radio = (StackPane) lookup(".radio");
        this.dot = (StackPane) lookup(".dot");
    }

    public StackPane getRadio () {
        return radio;
    }

    public StackPane getDot () {
        return dot;
    }

    public FRadioButton radio (PseudoElement pseudoElement) {
        currentComponentToStyle = radio;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public FRadioButton dot (PseudoElement pseudoElement) {
        currentComponentToStyle = dot;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    @Override
    public FRadioButton x (double value) {
        currentComponentToStyle.setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FRadioButton y (double value) {
        currentComponentToStyle.setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FRadioButton width (double value) {
        currentComponentToStyle.setPrefWidth(value);
        return this;
    }

    @Override
    public FRadioButton height (double value) {
        currentComponentToStyle.setPrefHeight(value);
        return this;
    }

    @Override
    public FRadioButton scaleX (double value) {
        currentComponentToStyle.setScaleX(value);
        return this;
    }

    @Override
    public FRadioButton scaleY (double value) {
        currentComponentToStyle.setScaleY(value);
        return this;
    }

    @Override
    public FRadioButton translateX (double value) {
        currentComponentToStyle.setTranslateX(value);
        return this;
    }

    @Override
    public FRadioButton translateY (double value) {
        currentComponentToStyle.setTranslateY(value);
        return this;
    }

    @Override
    public FRadioButton rotate (double value) {
        currentComponentToStyle.setRotate(value);
        return this;
    }

    @Override
    public FRadioButton padding (double value) {
        PaddingUtil.setPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FRadioButton topPadding (double value) {
        PaddingUtil.setTopPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton rightPadding (double value) {
        PaddingUtil.setRightPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomPadding (double value) {
        PaddingUtil.setBottomPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton leftPadding (double value) {
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
    public FRadioButton margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FRadioButton margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FRadioButton topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FRadioButton rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FRadioButton bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FRadioButton leftMargin (double value) {
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
    public FRadioButton bgColor (String color) {
        BackgroundUtil.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FRadioButton bgColor (Color color) {
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
    public FRadioButton bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FRadioButton bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FRadioButton bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FRadioButton topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomLeftBgRadius (double value) {
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
    public FRadioButton bgInsets (double value) {
        BackgroundUtil.setInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FRadioButton topInset (double value) {
        BackgroundUtil.setTopInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton rightInset (double value) {
        BackgroundUtil.setRightInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomInset (double value) {
        BackgroundUtil.setBottomInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton leftInset (double value) {
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
    public FRadioButton alignment(Pos pos){
        setAlignment(pos);
        return this;
    }

    @Override
    public FRadioButton border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(currentComponentToStyle, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FRadioButton border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(currentComponentToStyle, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FRadioButton topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FRadioButton rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FRadioButton bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FRadioButton leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FRadioButton topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FRadioButton rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FRadioButton bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FRadioButton leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FRadioButton borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FRadioButton topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FRadioButton rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FRadioButton bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FRadioButton leftBorderStyle (String borderStyle) {
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
    public FRadioButton borderWidth (double value) {
        BorderUtil.setBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FRadioButton topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton leftBorderWidth (double value) {
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
    public FRadioButton borderColor (String colorValue) {
        BorderUtil.setBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FRadioButton borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FRadioButton topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FRadioButton rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FRadioButton bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FRadioButton leftBorderColor (String colorValue) {
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
    public FRadioButton borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FRadioButton borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(currentComponentToStyle, top, right, top, right);
        return this;
    }

    @Override
    public FRadioButton topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FRadioButton rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FRadioButton bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FRadioButton leftBorderColor (Color color) {
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
    public FRadioButton borderOpacity (double value) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FRadioButton topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton leftBorderOpacity (double value) {
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
    public FRadioButton borderRadius (double value) {
        BorderUtil.setBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FRadioButton topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomLeftBorderRadius (double value) {
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
    public FRadioButton borderInsets (double value) {
        BorderUtil.setBorderInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FRadioButton borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FRadioButton topBorderInset (double value) {
        BorderUtil.setTopBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FRadioButton leftBorderInset (double value) {
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
    public FRadioButton removeBorder () {
        currentComponentToStyle.setBorder(null);
        return this;
    }

    @Override
    public FRadioButton removeTopBorder () {
        BorderUtil.setTopBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FRadioButton removeRightBorder () {
        BorderUtil.setRightBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FRadioButton removeBottomBorder () {
        BorderUtil.setBottomBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FRadioButton removeLeftBorder () {
        BorderUtil.setLeftBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FRadioButton shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        currentComponentToStyle.setShape(svgPath);
        return this;
    }

    @Override
    public FRadioButton shape (Shape shape) {
        currentComponentToStyle.setShape(shape);
        return this;
    }

    @Override
    public FRadioButton font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public FRadioButton font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public FRadioButton font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public FRadioButton font (String fontFamily, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public FRadioButton fontColor (Color color) {
        FontUtil.setFontColor(this, color);
        return this;
    }

    @Override
    public FRadioButton fontColor (String color) {
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
    public FRadioButton fontFamily (String fontFamily) {
        FontUtil.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public FRadioButton fontPosture (FontPosture fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public FRadioButton fontPosture (String fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public FRadioButton fontWeight (FontWeight fontWeight) {
        FontUtil.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public FRadioButton fontSize (double fontSize) {
        FontUtil.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }

    @Override
    public FRadioButton textAlignment(TextAlignment textAlignment){
        setTextAlignment(textAlignment);
        return this;
    }
}
