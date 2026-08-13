package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.SpinnerSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class SSpinner2<T> extends Spinner<T> implements SComponent {
    private Margin margin;
    private Region currentComponentToStyle;
    private TextField textField;
    private StackPane incrementButton;
    private StackPane decrementButton;
    private Region incrementArrow;
    private Region decrementArrow;

    private T minValue;
    private T maxValue;
    private T step;

    public SSpinner2 (T minValue, T maxValue, T initialValue, T step) {
        this.margin = new Margin(0);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.step = step;
        initElements();
        if(minValue instanceof Double
                || maxValue instanceof Double
                || initialValue instanceof Double
                || step instanceof Double){
            SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory((double)minValue, (double)maxValue, (double)initialValue, (double)step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
        else{
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory((int)minValue, (int)maxValue, (int)initialValue, (int)step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
    }

    public void setValue(T value){
        getValueFactory().setValue(value);
    }

    public void setMinValue(T minValue){
        this.minValue = minValue;
        if(minValue instanceof Integer){
            int value = (int)getValue();
            if(value < (int) minValue){
                value = (int) minValue;
            }
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory((int)minValue, (int)maxValue, value, (int)step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
        else if(minValue instanceof Double){
            double value = (double)getValue();
            if(value < (double)minValue){
                value = (double)minValue;
            }
            SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory((double)minValue, (double)maxValue, value, (double)step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
    }

    public void setMaxValue(T maxValue){
        this.maxValue = maxValue;
        if(maxValue instanceof Integer){
            int value = (int)getValue();
            if(value > (int) maxValue){
                value = (int) maxValue;
            }
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory((int)minValue, (int)maxValue, value, (int)step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
        else if(maxValue instanceof Double){
            double value = (double)getValue();
            if(value > (double)maxValue){
                value = (double)maxValue;
            }
            SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory((double)minValue, (double)maxValue, value, (double)step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
    }

    public void setStep(T step){
        this.step = step;
        if(step instanceof Integer){
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory((int)minValue, (int)maxValue, (int)getValue(), (int)step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
        else if(step instanceof Double){
            SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory((double)minValue, (double)maxValue, (double)getValue(), (double)step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
    }

    private void initElements(){
        setSkin(new SpinnerSkin<>(this));
        this.currentComponentToStyle = this;
        textField = (TextField) lookup(".text-field");
        incrementButton = (StackPane) lookup(".increment-arrow-button");
        decrementButton = (StackPane) lookup(".decrement-arrow-button");
        incrementArrow = (Region) lookup(".increment-arrow");
        decrementArrow = (Region) lookup(".decrement-arrow");
    }

    public TextField getTextField(){
        return textField;
    }

    public StackPane getIncrementButton (){
        return incrementButton;
    }

    public StackPane getDecrementButton (){
        return decrementButton;
    }

    public Region getIncrementArrow(){
        return incrementArrow;
    }

    public Region getDecrementArrow(){
        return decrementArrow;
    }

    public Spinner<T> textField(PseudoElement pseudoElement){
        currentComponentToStyle = textField;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public Spinner<T> incrementButton(PseudoElement pseudoElement){
        currentComponentToStyle = incrementButton;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public Spinner<T> decrementButton(PseudoElement pseudoElement){
        currentComponentToStyle = decrementButton;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public Spinner<T> incrementArrow(PseudoElement pseudoElement){
        currentComponentToStyle = incrementArrow;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public Spinner<T> decrementArrow(PseudoElement pseudoElement){
        currentComponentToStyle = decrementArrow;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    @Override
    public SSpinner2<T> x (double value) {
        currentComponentToStyle.setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public SSpinner2<T> y (double value) {
        currentComponentToStyle.setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public SSpinner2<T> width (double value) {
        currentComponentToStyle.setPrefWidth(value);
        return this;
    }

    @Override
    public SSpinner2<T> height (double value) {
        currentComponentToStyle.setPrefHeight(value);
        return this;
    }

    @Override
    public SSpinner2<T> scaleX (double value) {
        currentComponentToStyle.setScaleX(value);
        return this;
    }

    @Override
    public SSpinner2<T> scaleY (double value) {
        currentComponentToStyle.setScaleY(value);
        return this;
    }

    @Override
    public SSpinner2<T> translateX (double value) {
        currentComponentToStyle.setTranslateX(value);
        return this;
    }

    @Override
    public SSpinner2<T> translateY (double value) {
        currentComponentToStyle.setTranslateY(value);
        return this;
    }

    @Override
    public SSpinner2<T> rotate (double value) {
        currentComponentToStyle.setRotate(value);
        return this;
    }

    @Override
    public SSpinner2<T> padding (double value) {
        PaddingUtil.setPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SSpinner2<T> topPadding (double value) {
        PaddingUtil.setTopPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> rightPadding (double value) {
        PaddingUtil.setRightPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomPadding (double value) {
        PaddingUtil.setBottomPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> leftPadding (double value) {
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
    public SSpinner2<T> margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public SSpinner2<T> margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public SSpinner2<T> topMargin(double value){
        margin.setTop(value);
        return this;
    }

    @Override
    public SSpinner2<T> rightMargin(double value){
        margin.setRight(value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomMargin(double value){
        margin.setBottom(value);
        return this;
    }

    @Override
    public SSpinner2<T> leftMargin(double value){
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
    public SSpinner2<T> bgColor (String color) {
        BackgroundSetter.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SSpinner2<T> bgColor (Color color) {
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
    public SSpinner2<T> bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public SSpinner2<T> bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public SSpinner2<T> bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public SSpinner2<T> topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomLeftBgRadius (double value) {
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
    public SSpinner2<T> bgInsets (double value) {
        BackgroundSetter.setInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public SSpinner2<T> topInset (double value) {
        BackgroundSetter.setTopInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> rightInset (double value) {
        BackgroundSetter.setRightInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomInset (double value) {
        BackgroundSetter.setBottomInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> leftInset (double value) {
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
    public SSpinner2<T> border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(currentComponentToStyle, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public SSpinner2<T> border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(currentComponentToStyle, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SSpinner2<T> borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SSpinner2<T> topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SSpinner2<T> rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SSpinner2<T> bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SSpinner2<T> leftBorderStyle (String borderStyle) {
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
    public SSpinner2<T> borderWidth (double value) {
        BorderSetter.setBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SSpinner2<T> topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> leftBorderWidth (double value) {
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
    public SSpinner2<T> borderColor (String colorValue) {
        BorderSetter.setBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SSpinner2<T> borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SSpinner2<T> topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SSpinner2<T> rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SSpinner2<T> bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SSpinner2<T> leftBorderColor (String colorValue) {
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
    public SSpinner2<T> borderColor (Color color) {
        BorderSetter.setBorderColor(this, color);
        return this;
    }

    @Override
    public SSpinner2<T> borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(currentComponentToStyle, top, right, top, right);
        return this;
    }

    @Override
    public SSpinner2<T> topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SSpinner2<T> rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SSpinner2<T> bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SSpinner2<T> leftBorderColor (Color color) {
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
    public SSpinner2<T> borderOpacity (double value) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SSpinner2<T> topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> leftBorderOpacity (double value) {
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
    public SSpinner2<T> borderRadius (double value) {
        BorderSetter.setBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public SSpinner2<T> topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomLeftBorderRadius (double value) {
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
    public SSpinner2<T> borderInsets (double value) {
        BorderSetter.setBorderInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SSpinner2<T> borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public SSpinner2<T> topBorderInset (double value) {
        BorderSetter.setTopBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SSpinner2<T> leftBorderInset (double value) {
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
    public SSpinner2<T> removeBorder () {
        currentComponentToStyle.setBorder(null);
        return this;
    }

    @Override
    public SSpinner2<T> removeTopBorder () {
        BorderSetter.setTopBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SSpinner2<T> removeRightBorder () {
        BorderSetter.setRightBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SSpinner2<T> removeBottomBorder () {
        BorderSetter.setBottomBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SSpinner2<T> removeLeftBorder () {
        BorderSetter.setLeftBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SSpinner2<T> shape(String svg){
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        currentComponentToStyle.setShape(svgPath);
        return this;
    }

    @Override
    public SSpinner2<T> shape(Shape shape){
        currentComponentToStyle.setShape(shape);
        return this;
    }

//    @Override
//    public SSpinner2<T> font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
//        FontSetter.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
//        return this;
//    }
//
//    @Override
//    public SSpinner2<T> font (String fontFamily, FontPosture fontPosture, double fontSize) {
//        FontSetter.setFont(this, fontFamily, fontPosture, fontSize);
//        return this;
//    }
//
//    @Override
//    public SSpinner2<T> font (String fontFamily, FontWeight fontWeight, double fontSize) {
//        FontSetter.setFont(this, fontFamily, fontWeight, fontSize);
//        return this;
//    }
//
//    @Override
//    public SSpinner2<T> font (String fontFamily, double fontSize) {
//        FontSetter.setFont(this, fontFamily, fontSize);
//        return this;
//    }
//
//    @Override
//    public SSpinner2<T> fontColor (Color color) {
//        FontSetter.setFontColor(this, color);
//        return this;
//    }
//
//    @Override
//    public SSpinner2<T> fontColor (String color) {
//        FontSetter.setFontColor(this, color);
//        return this;
//    }
//
//    @Override
//    public Color getFontColor () {
//        return FontSetter.getFontColor(this);
//    }
//
//    @Override
//    public String getFontColorHex () {
//        return FontSetter.getFontColorHex(this);
//    }
//
//    @Override
//    public SSpinner2<T> fontFamily (String fontFamily) {
//        FontSetter.setFontFamily(this, fontFamily);
//        return this;
//    }
//
//    @Override
//    public SSpinner2<T> fontPosture (FontPosture fontPosture) {
//        FontSetter.setFontPosture(this, fontPosture);
//        return this;
//    }
//
//    @Override
//    public SSpinner2<T> fontPosture (String fontPosture) {
//        FontSetter.setFontPosture(this, fontPosture);
//        return this;
//    }
//
//    @Override
//    public String getFontPostureString () {
//        return getFont().getStyle();
//    }
//
//    @Override
//    public SSpinner2<T> fontWeight (FontWeight fontWeight) {
//        FontSetter.setFontWeight(this, fontWeight);
//        return this;
//    }
//
//    @Override
//    public SSpinner2<T> fontSize (double fontSize) {
//        FontSetter.setFontSize(this, fontSize);
//        return this;
//    }
//
//    @Override
//    public double getFontSize () {
//        return getFont().getSize();
//    }
}
