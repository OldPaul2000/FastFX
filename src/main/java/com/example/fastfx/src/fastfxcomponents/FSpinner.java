package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
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

public class FSpinner<T> extends Spinner<T> implements FComponent {
    private Margin margin;
    private Region currentComponentToStyle;
    private TextField textField;
    private StackPane incrementButton;
    private StackPane decrementButton;
    private Region incrementArrow;
    private Region decrementArrow;

    private int intMinValue;
    private int intMaxValue;
    private int intStep;
    private double doubleMinValue;
    private double doubleMaxValue;
    private double doubleStep;

    public FSpinner (int minValue, int maxValue) {
        this(minValue, maxValue, 1, 1);
    }

    public FSpinner (int minValue, int maxValue, int initialValue) {
        this(minValue, maxValue, initialValue, 1);
    }

    public FSpinner (int minValue, int maxValue, int initialValue, int step) {
        this.margin = new Margin(0);
        this.intMinValue = minValue;
        this.intMaxValue = maxValue;
        this.intStep = step;
        this.currentComponentToStyle = this;
        initElements();
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, maxValue, initialValue, step);
        setValueFactory((SpinnerValueFactory<T>) valueFactory);
    }

    public FSpinner (double minValue, double maxValue) {
        this(minValue, maxValue, 1, 1);
    }

    public FSpinner (double minValue, double maxValue, double initialValue) {
        this(minValue, maxValue, initialValue, 1);
    }

    public FSpinner (double minValue, double maxValue, double initialValue, double step) {
        this.margin = new Margin(0);
        this.doubleMinValue = minValue;
        this.doubleMaxValue = maxValue;
        this.doubleStep = step;
        this.currentComponentToStyle = this;
        initElements();
        SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(minValue, maxValue, initialValue, step);
        setValueFactory((SpinnerValueFactory<T>) valueFactory);
    }

    public void setValue (T value) {
        getValueFactory().setValue(value);
    }

    public void setMinValue (T minValue) {
        if (minValue instanceof Integer) {
            this.intMinValue = (int) minValue;
            int value = (int) getValue();
            if (value > (int) minValue) {
                value = (int) minValue;
            }
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory((int) minValue, intMaxValue, value, intStep);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
        else if (minValue instanceof Double) {
            this.doubleMinValue = (double) minValue;
            double value = (double) getValue();
            if (value > (double) minValue) {
                value = (double) minValue;
            }
            SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory((double) minValue, doubleMaxValue, value, doubleStep);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
    }

    public void setMaxValue (T maxValue) {
        if (maxValue instanceof Integer) {
            this.intMaxValue = (int) maxValue;
            int value = (int) getValue();
            if (value > (int) maxValue) {
                value = (int) maxValue;
            }
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(intMinValue, (int) maxValue, value, intStep);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
        else if (maxValue instanceof Double) {
            this.doubleMaxValue = (double) maxValue;
            double value = (double) getValue();
            if (value > (double) maxValue) {
                value = (double) maxValue;
            }
            SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(doubleMinValue, (double) maxValue, value, doubleStep);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
    }

    public void setStep (T step) {
        if (step instanceof Integer) {
            this.intStep = (int) step;
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(intMinValue, intMaxValue, (int) getValue(), (int) step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
        else if (step instanceof Double) {
            this.doubleStep = (double) step;
            SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(doubleMinValue, doubleMaxValue, (double) getValue(), (double) step);
            setValueFactory((SpinnerValueFactory<T>) valueFactory);
        }
    }

    private void initElements () {
        setSkin(new SpinnerSkin<>(this));
        textField = (TextField) lookup(".text-field");
        incrementButton = (StackPane) lookup(".increment-arrow-button");
        decrementButton = (StackPane) lookup(".decrement-arrow-button");
        incrementArrow = (Region) lookup(".increment-arrow");
        decrementArrow = (Region) lookup(".decrement-arrow");
    }

    public TextField getTextField () {
        return textField;
    }

    public StackPane getIncrementButton () {
        return incrementButton;
    }

    public StackPane getDecrementButton () {
        return decrementButton;
    }

    public Region getIncrementArrow () {
        return incrementArrow;
    }

    public Region getDecrementArrow () {
        return decrementArrow;
    }

    public Spinner<T> textField (PseudoElement pseudoElement) {
        currentComponentToStyle = textField;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public Spinner<T> incrementButton (PseudoElement pseudoElement) {
        currentComponentToStyle = incrementButton;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public Spinner<T> decrementButton (PseudoElement pseudoElement) {
        currentComponentToStyle = decrementButton;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public Spinner<T> incrementArrow (PseudoElement pseudoElement) {
        currentComponentToStyle = incrementArrow;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public Spinner<T> decrementArrow (PseudoElement pseudoElement) {
        currentComponentToStyle = decrementArrow;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    @Override
    public FSpinner<T> x (double value) {
        currentComponentToStyle.setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FSpinner<T> y (double value) {
        currentComponentToStyle.setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FSpinner<T> width (double value) {
        currentComponentToStyle.setPrefWidth(value);
        return this;
    }

    @Override
    public FSpinner<T> height (double value) {
        currentComponentToStyle.setPrefHeight(value);
        return this;
    }

    @Override
    public FSpinner<T> scaleX (double value) {
        currentComponentToStyle.setScaleX(value);
        return this;
    }

    @Override
    public FSpinner<T> scaleY (double value) {
        currentComponentToStyle.setScaleY(value);
        return this;
    }

    @Override
    public FSpinner<T> translateX (double value) {
        currentComponentToStyle.setTranslateX(value);
        return this;
    }

    @Override
    public FSpinner<T> translateY (double value) {
        currentComponentToStyle.setTranslateY(value);
        return this;
    }

    @Override
    public FSpinner<T> rotate (double value) {
        currentComponentToStyle.setRotate(value);
        return this;
    }

    @Override
    public FSpinner<T> padding (double value) {
        PaddingUtil.setPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FSpinner<T> topPadding (double value) {
        PaddingUtil.setTopPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> rightPadding (double value) {
        PaddingUtil.setRightPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomPadding (double value) {
        PaddingUtil.setBottomPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> leftPadding (double value) {
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
    public FSpinner<T> margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FSpinner<T> margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FSpinner<T> topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FSpinner<T> rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FSpinner<T> bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FSpinner<T> leftMargin (double value) {
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
    public FSpinner<T> bgColor (String color) {
        BackgroundUtil.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FSpinner<T> bgColor (Color color) {
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
    public FSpinner<T> bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FSpinner<T> bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FSpinner<T> bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FSpinner<T> topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomLeftBgRadius (double value) {
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
    public FSpinner<T> bgInsets (double value) {
        BackgroundUtil.setInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FSpinner<T> topInset (double value) {
        BackgroundUtil.setTopInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> rightInset (double value) {
        BackgroundUtil.setRightInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomInset (double value) {
        BackgroundUtil.setBottomInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> leftInset (double value) {
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
    public FSpinner<T> border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(currentComponentToStyle, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FSpinner<T> border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(currentComponentToStyle, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FSpinner<T> borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FSpinner<T> topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FSpinner<T> rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FSpinner<T> bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public FSpinner<T> leftBorderStyle (String borderStyle) {
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
    public FSpinner<T> borderWidth (double value) {
        BorderUtil.setBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FSpinner<T> topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> leftBorderWidth (double value) {
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
    public FSpinner<T> borderColor (String colorValue) {
        BorderUtil.setBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FSpinner<T> borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FSpinner<T> topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FSpinner<T> rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FSpinner<T> bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public FSpinner<T> leftBorderColor (String colorValue) {
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
    public FSpinner<T> borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FSpinner<T> borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(currentComponentToStyle, top, right, top, right);
        return this;
    }

    @Override
    public FSpinner<T> topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FSpinner<T> rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FSpinner<T> bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public FSpinner<T> leftBorderColor (Color color) {
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
    public FSpinner<T> borderOpacity (double value) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public FSpinner<T> topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> leftBorderOpacity (double value) {
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
    public FSpinner<T> borderRadius (double value) {
        BorderUtil.setBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FSpinner<T> topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomLeftBorderRadius (double value) {
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
    public FSpinner<T> borderInsets (double value) {
        BorderUtil.setBorderInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FSpinner<T> borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FSpinner<T> topBorderInset (double value) {
        BorderUtil.setTopBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public FSpinner<T> leftBorderInset (double value) {
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
    public FSpinner<T> removeBorder () {
        currentComponentToStyle.setBorder(null);
        return this;
    }

    @Override
    public FSpinner<T> removeTopBorder () {
        BorderUtil.setTopBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FSpinner<T> removeRightBorder () {
        BorderUtil.setRightBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FSpinner<T> removeBottomBorder () {
        BorderUtil.setBottomBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FSpinner<T> removeLeftBorder () {
        BorderUtil.setLeftBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        BorderUtil.setTopLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public FSpinner<T> shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        currentComponentToStyle.setShape(svgPath);
        return this;
    }

    @Override
    public FSpinner<T> shape (Shape shape) {
        currentComponentToStyle.setShape(shape);
        return this;
    }

    //    @Override
    //    public FSpinner<T> font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
    //        FontUtil.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
    //        return this;
    //    }
    //
    //    @Override
    //    public FSpinner<T> font (String fontFamily, FontPosture fontPosture, double fontSize) {
    //        FontUtil.setFont(this, fontFamily, fontPosture, fontSize);
    //        return this;
    //    }
    //
    //    @Override
    //    public FSpinner<T> font (String fontFamily, FontWeight fontWeight, double fontSize) {
    //        FontUtil.setFont(this, fontFamily, fontWeight, fontSize);
    //        return this;
    //    }
    //
    //    @Override
    //    public FSpinner<T> font (String fontFamily, double fontSize) {
    //        FontUtil.setFont(this, fontFamily, fontSize);
    //        return this;
    //    }
    //
    //    @Override
    //    public FSpinner<T> fontColor (Color color) {
    //        FontUtil.setFontColor(this, color);
    //        return this;
    //    }
    //
    //    @Override
    //    public FSpinner<T> fontColor (String color) {
    //        FontUtil.setFontColor(this, color);
    //        return this;
    //    }
    //
    //    @Override
    //    public Color getFontColor () {
    //        return FontUtil.getFontColor(this);
    //    }
    //
    //    @Override
    //    public String getFontColorHex () {
    //        return FontUtil.getFontColorHex(this);
    //    }
    //
    //    @Override
    //    public FSpinner<T> fontFamily (String fontFamily) {
    //        FontUtil.setFontFamily(this, fontFamily);
    //        return this;
    //    }
    //
    //    @Override
    //    public FSpinner<T> fontPosture (FontPosture fontPosture) {
    //        FontUtil.setFontPosture(this, fontPosture);
    //        return this;
    //    }
    //
    //    @Override
    //    public FSpinner<T> fontPosture (String fontPosture) {
    //        FontUtil.setFontPosture(this, fontPosture);
    //        return this;
    //    }
    //
    //    @Override
    //    public String getFontPostureString () {
    //        return getFont().getStyle();
    //    }
    //
    //    @Override
    //    public FSpinner<T> fontWeight (FontWeight fontWeight) {
    //        FontUtil.setFontWeight(this, fontWeight);
    //        return this;
    //    }
    //
    //    @Override
    //    public FSpinner<T> fontSize (double fontSize) {
    //        FontUtil.setFontSize(this, fontSize);
    //        return this;
    //    }
    //
    //    @Override
    //    public double getFontSize () {
    //        return getFont().getSize();
    //    }
}
