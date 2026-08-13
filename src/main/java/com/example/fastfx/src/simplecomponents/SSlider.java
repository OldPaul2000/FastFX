package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
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

public class SSlider extends Slider implements SComponent{
    private Margin margin;
    private Region currentComponentToStyle;
    private StackPane track;
    private StackPane thumb;
    private NumberAxis axis;
    private Path minorTicks;
    private Path majorTicks;

    public SSlider (){
        this.margin = new Margin(0);
        initElements();
        setOrientation(Orientation.VERTICAL);
    }

    private void initElements(){
        setSkin(new SliderSkin(this));
        this.currentComponentToStyle = this;
        this.track = (StackPane) lookup(".track");
        this.thumb = (StackPane) lookup(".thumb");
    }

    public StackPane getTrack (){
        return track;
    }

    public StackPane getThumb (){
        return thumb;
    }

    public NumberAxis getAxis (){
        return axis;
    }

    public Path getMinorTicks (){
        return minorTicks;
    }

    public Path getMajorTicks (){
        return majorTicks;
    }

    public SSlider track(PseudoElement pseudoElement){
        currentComponentToStyle = track;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public SSlider thumb(PseudoElement pseudoElement){
        currentComponentToStyle = thumb;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    @Override
    public SSlider x (double value) {
        currentComponentToStyle.setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public SSlider y (double value) {
        currentComponentToStyle.setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public SSlider width (double value) {
        currentComponentToStyle.setPrefWidth(value);
        return this;
    }

    @Override
    public SSlider height (double value) {
        currentComponentToStyle.setPrefHeight(value);
        return this;
    }

    @Override
    public SSlider scaleX (double value) {
        currentComponentToStyle.setScaleX(value);
        return this;
    }

    @Override
    public SSlider scaleY (double value) {
        currentComponentToStyle.setScaleY(value);
        return this;
    }

    @Override
    public SSlider translateX (double value) {
        currentComponentToStyle.setTranslateX(value);
        return this;
    }

    @Override
    public SSlider translateY (double value) {
        currentComponentToStyle.setTranslateY(value);
        return this;
    }

    @Override
    public SSlider rotate (double value) {
        currentComponentToStyle.setRotate(value);
        return this;
    }

    @Override
    public SSlider padding (double value) {
        PaddingUtil.setPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SSlider topPadding (double value) {
        PaddingUtil.setTopPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider rightPadding (double value) {
        PaddingUtil.setRightPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomPadding (double value) {
        PaddingUtil.setBottomPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider leftPadding (double value) {
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
    public SSlider margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public SSlider margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public SSlider topMargin(double value){
        margin.setTop(value);
        return this;
    }

    @Override
    public SSlider rightMargin(double value){
        margin.setRight(value);
        return this;
    }

    @Override
    public SSlider bottomMargin(double value){
        margin.setBottom(value);
        return this;
    }

    @Override
    public SSlider leftMargin(double value){
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
    public SSlider bgColor (String color) {
        BackgroundSetter.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SSlider bgColor (Color color) {
        BackgroundSetter.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public String getBgColorHex () {
        return BackgroundSetter.getBackgroundHexColor(this);
    }

    @Override
    public Color getBgColor () {
        return BackgroundSetter.getBackgroundColor(this);
    }

    @Override
    public SSlider bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public SSlider bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public SSlider bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public SSlider topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomLeftBgRadius (double value) {
        BackgroundSetter.setBottomLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopLeftBgRadius () {
        return BackgroundSetter.getTopLeftBackgroundRadius(this);
    }

    @Override
    public double getTopRightBgRadius () {
        return BackgroundSetter.getTopRightBackgroundRadius(this);
    }

    @Override
    public double getBottomRightBgRadius () {
        return BackgroundSetter.getBottomRightBackgroundRadius(this);
    }

    @Override
    public double getBottomLeftBgRadius () {
        return BackgroundSetter.getBottomLeftBackgroundRadius(this);
    }

    @Override
    public SSlider bgInsets (double value) {
        BackgroundSetter.setInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public SSlider topInset (double value) {
        BackgroundSetter.setTopInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider rightInset (double value) {
        BackgroundSetter.setRightInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomInset (double value) {
        BackgroundSetter.setBottomInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider leftInset (double value) {
        BackgroundSetter.setLeftInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopInset () {
        return BackgroundSetter.getBackgroundInsets(this).getTop();
    }

    @Override
    public double getRightInset () {
        return BackgroundSetter.getBackgroundInsets(this).getRight();
    }

    @Override
    public double getBottomInset () {
        return BackgroundSetter.getBackgroundInsets(this).getBottom();
    }

    @Override
    public double getLeftInset () {
        return BackgroundSetter.getBackgroundInsets(this).getLeft();
    }

    @Override
    public SSlider border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(currentComponentToStyle, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public SSlider border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(currentComponentToStyle, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public SSlider topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSlider rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSlider bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSlider leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSlider topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSlider rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSlider bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSlider leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSlider borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SSlider topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SSlider rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SSlider bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SSlider leftBorderStyle (String borderStyle) {
        BorderSetter.setLeftBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public String getTopBorderStyle () {
        return BorderSetter.getTopBorderStyle(this);
    }

    @Override
    public String getRightBorderStyle () {
        return BorderSetter.getRightBorderStyle(this);
    }

    @Override
    public String getBottomBorderStyle () {
        return BorderSetter.getBottomBorderStyle(this);
    }

    @Override
    public String getLeftBorderStyle () {
        return BorderSetter.getLeftBorderStyle(this);
    }

    @Override
    public SSlider borderWidth (double value) {
        BorderSetter.setBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SSlider topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider leftBorderWidth (double value) {
        BorderSetter.setLeftBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopBorderWidth () {
        return BorderSetter.getTopBorderWidth(this);
    }

    @Override
    public double getRightBorderWidth () {
        return BorderSetter.getRightBorderWidth(this);
    }

    @Override
    public double getBottomBorderWidth () {
        return BorderSetter.getBottomBorderWidth(this);
    }

    @Override
    public double getLeftBorderWidth () {
        return BorderSetter.getLeftBorderWidth(this);
    }

    @Override
    public SSlider borderColor (String colorValue) {
        BorderSetter.setBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SSlider borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SSlider topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SSlider rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SSlider bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SSlider leftBorderColor (String colorValue) {
        BorderSetter.setLeftBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public String getTopBorderColorHex () {
        return BorderSetter.getTopBorderColor(this);
    }

    @Override
    public String getRightBorderColorHex () {
        return BorderSetter.getRightBorderColor(this);
    }

    @Override
    public String getBottomBorderColorHex () {
        return BorderSetter.getBottomBorderColor(this);
    }

    @Override
    public String getLeftBorderColorHex () {
        return BorderSetter.getLeftBorderColor(this);
    }

    @Override
    public SSlider borderColor (Color color) {
        BorderSetter.setBorderColor(this, color);
        return this;
    }

    @Override
    public SSlider borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(currentComponentToStyle, top, right, top, right);
        return this;
    }

    @Override
    public SSlider topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SSlider rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SSlider bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SSlider leftBorderColor (Color color) {
        BorderSetter.setLeftBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public Color getTopBorderColor () {
        return Color.valueOf(BorderSetter.getTopBorderColor(this));
    }

    @Override
    public Color getRightBorderColor () {
        return Color.valueOf(BorderSetter.getRightBorderColor(this));
    }

    @Override
    public Color getBottomBorderColor () {
        return Color.valueOf(BorderSetter.getBottomBorderColor(this));
    }

    @Override
    public Color getLeftBorderColor () {
        return Color.valueOf(BorderSetter.getLeftBorderColor(this));
    }

    @Override
    public SSlider borderOpacity (double value) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SSlider topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider leftBorderOpacity (double value) {
        BorderSetter.setLeftBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopBorderOpacity () {
        return BorderSetter.getTopBorderOpacity(this);
    }

    @Override
    public double getRightBorderOpacity () {
        return BorderSetter.getRightBorderOpacity(this);
    }

    @Override
    public double getBottomBorderOpacity () {
        return BorderSetter.getBottomBorderOpacity(this);
    }

    @Override
    public double getLeftBorderOpacity () {
        return BorderSetter.getLeftBorderOpacity(this);
    }

    @Override
    public SSlider borderRadius (double value) {
        BorderSetter.setBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public SSlider topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomLeftBorderRadius (double value) {
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopLeftBorderRadius () {
        return BorderSetter.getBorderRadius(this).getTopLeftHorizontalRadius();
    }

    @Override
    public double getTopRightBorderRadius () {
        return BorderSetter.getBorderRadius(this).getTopRightHorizontalRadius();
    }

    @Override
    public double getBottomRightBorderRadius () {
        return BorderSetter.getBorderRadius(this).getBottomRightHorizontalRadius();
    }

    @Override
    public double getBottomLeftBorderRadius () {
        return BorderSetter.getBorderRadius(this).getBottomLeftHorizontalRadius();
    }

    @Override
    public SSlider borderInsets (double value) {
        BorderSetter.setBorderInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSlider borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public SSlider topBorderInset (double value) {
        BorderSetter.setTopBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSlider leftBorderInset (double value) {
        BorderSetter.setLeftBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getTopBorderInset () {
        return BorderSetter.getBorderInsets(this).getTop();
    }

    @Override
    public double getRightBorderInset () {
        return BorderSetter.getBorderInsets(this).getRight();
    }

    @Override
    public double getBottomBorderInset () {
        return BorderSetter.getBorderInsets(this).getBottom();
    }

    @Override
    public double getLeftBorderInset () {
        return BorderSetter.getBorderInsets(this).getLeft();
    }

    @Override
    public SSlider removeBorder () {
        currentComponentToStyle.setBorder(null);
        return this;
    }

    @Override
    public SSlider removeTopBorder () {
        BorderSetter.setTopBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SSlider removeRightBorder () {
        BorderSetter.setRightBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SSlider removeBottomBorder () {
        BorderSetter.setBottomBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SSlider removeLeftBorder () {
        BorderSetter.setLeftBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SSlider shape(String svg){
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        currentComponentToStyle.setShape(svgPath);
        return this;
    }

    @Override
    public SSlider shape(Shape shape){
        currentComponentToStyle.setShape(shape);
        return this;
    }

    // Slider-related methods
    public SSlider orientation(Orientation orientation){
        setOrientation(orientation);
        return this;
    }

    public SSlider orientation(String orientation){
        orientation = orientation.toLowerCase();
        if(orientation.equals("horizontal")) {
            setOrientation(Orientation.HORIZONTAL);
        }
        else if(orientation.equals("vertical")) {
            setOrientation(Orientation.VERTICAL);
        }
        return this;
    }

    public SSlider min(double min){
        setMin(min);
        return this;
    }

    public SSlider max(double max){
        setMax(max);
        return this;
    }

    public SSlider incrementStep(double step){
        setBlockIncrement(step);
        return this;
    }

    public SSlider majorTickUnit(double value){
        setMajorTickUnit(value);
        return this;
    }

    public SSlider minorTickCount(int value){
        setMinorTickCount(value);
        return this;
    }

    public SSlider showTickLabels(boolean show){
        setShowTickLabels(show);
        if(show && axis == null){
            axis = (NumberAxis) lookup(".axis");
            minorTicks = (Path) lookup(".axis-minor-tick-mark");
            majorTicks = (Path) lookup(".axis-tick-mark");
        }
        return this;
    }

    public SSlider showTickMarks(boolean show){
        setShowTickMarks(show);
        if(show && axis == null){
            axis = (NumberAxis) lookup(".axis");
            minorTicks = (Path) lookup(".axis-minor-tick-mark");
            majorTicks = (Path) lookup(".axis-tick-mark");
        }
        return this;
    }

    public SSlider snapToTicks(boolean snap){
        setSnapToTicks(snap);
        return this;
    }
}
