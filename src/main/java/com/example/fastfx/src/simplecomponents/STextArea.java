package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.simplecomponents.stylinghelpers.CSSStyleManager;
import com.example.fastfx.src.simplecomponents.stylinghelpers.ComponentStyleTracker;
import com.example.fastfx.src.simplecomponents.stylinghelpers.MainProps;
import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.color.ColorUtil;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.FontSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.skin.ScrollBarSkin;
import javafx.scene.control.skin.ScrollPaneSkin;
import javafx.scene.control.skin.TextAreaSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.Set;

public class STextArea extends TextArea implements SLabeled {
    private Margin margin;
    private Text text;
    private ScrollPane scrollPane;
    private ScrollBar verticalScrollBar;
    private ScrollBar horizontalScrollBar;
    private Region verticalThumb;
    private Region horizontalThumb;
    private Region incrementVButton;
    private Region decrementVButton;
    private Region incrementHButton;
    private Region decrementHButton;
    private Region incrementVArrow;
    private Region decrementVArrow;
    private Region incrementHArrow;
    private Region decrementHArrow;
    private StackPane corner;

    private ComponentStyleTracker styleTracker;
    private CSSStyleManager cssStyleManager;

    public STextArea(){
        setText("");
        this.margin = new Margin(0);
        this.styleTracker = new ComponentStyleTracker();
        this.cssStyleManager = new CSSStyleManager();
        initElements();
        initAllowedStyleProperties();
    }

    public STextArea(String textElement){
        setText(textElement);
        this.margin = new Margin(0);
        this.styleTracker = new ComponentStyleTracker();
        this.cssStyleManager = new CSSStyleManager();
        initElements();
        initAllowedStyleProperties();
    }

    public STextArea text(String text){
        setText(text);
        return this;
    }

    public STextArea promptText(String text){
        setPromptText(text);
        return this;
    }
    
    private void initElements() {
        setSkin(new TextAreaSkin(this));
        this.scrollPane = (ScrollPane) lookup(".scroll-pane");
        scrollPane.setSkin(new ScrollPaneSkin(scrollPane));
        Set<Node> scrollBars = scrollPane.lookupAll(".scroll-bar");
        scrollBars.forEach(el -> {
            ScrollBar currScrollBar = (ScrollBar) el;
            if (currScrollBar.getOrientation() == Orientation.VERTICAL) {
                this.verticalScrollBar = currScrollBar;
            } else if (currScrollBar.getOrientation() == Orientation.HORIZONTAL) {
                this.horizontalScrollBar = currScrollBar;
            }
        });
        verticalScrollBar.setSkin(new ScrollBarSkin(verticalScrollBar));
        horizontalScrollBar.setSkin(new ScrollBarSkin(horizontalScrollBar));
        this.text = (Text) lookup(".text");
        this.verticalThumb = (Region) verticalScrollBar.lookup(".thumb");
        this.horizontalThumb = (Region) horizontalScrollBar.lookup(".thumb");
        this.incrementVButton = (Region) verticalScrollBar.lookup(".increment-button");
        this.decrementVButton = (Region) verticalScrollBar.lookup(".decrement-button");
        this.incrementHButton = (Region) horizontalScrollBar.lookup(".increment-button");
        this.decrementHButton = (Region) horizontalScrollBar.lookup(".decrement-button");
        this.incrementVArrow = (Region) verticalScrollBar.lookup(".increment-arrow");
        this.decrementVArrow = (Region) verticalScrollBar.lookup(".decrement-arrow");
        this.incrementHArrow = (Region) horizontalScrollBar.lookup(".increment-arrow");
        this.decrementHArrow = (Region) horizontalScrollBar.lookup(".decrement-arrow");
        this.corner = (StackPane) lookup(".corner");
    }

    private void initAllowedStyleProperties () {
        Set<String> notAllowedProperties = new HashSet<>();
        styleTracker.addComponent(this, notAllowedProperties);
        styleTracker.setCurrentComponent(this);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(verticalScrollBar, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.WIDTH);
        styleTracker.addComponent(horizontalScrollBar, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.TRANSLATE_X);
        notAllowedProperties.add(MainProps.TRANSLATE_Y);
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(verticalThumb, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.TRANSLATE_X);
        notAllowedProperties.add(MainProps.TRANSLATE_Y);
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(horizontalThumb, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementVButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(decrementVButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementHButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(decrementHButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementVArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(decrementVArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementHArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(decrementHArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(corner, notAllowedProperties);
    }

    public Text getTextObject(){
        return text;
    }

    public ScrollPane getScrollPane () {
        return scrollPane;
    }

    public ScrollBar getVScrollBar () {
        return verticalScrollBar;
    }

    public ScrollBar getHScrollBar () {
        return horizontalScrollBar;
    }

    public Region getVThumb () {
        return verticalThumb;
    }

    public Region getHThumb () {
        return horizontalThumb;
    }

    public Region getIncrementVButton () {
        return incrementVButton;
    }

    public Region getDecrementVButton () {
        return decrementVButton;
    }

    public Region getIncrementHButton () {
        return incrementHButton;
    }

    public Region getDecrementHButton () {
        return decrementHButton;
    }

    public Region getIncrementVArrow () {
        return incrementVArrow;
    }

    public Region getDecrementVArrow () {
        return decrementVArrow;
    }

    public Region getIncrementHArrow () {
        return incrementHArrow;
    }

    public Region getDecrementHArrow () {
        return decrementHArrow;
    }

    public StackPane getCorner () {
        return corner;
    }

    public STextArea verticalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea horizontalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea verticalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea horizontalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea incrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea decrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea incrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea decrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea incrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea decrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea incrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea decrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public STextArea corner (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(corner);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    @Override
    public STextArea x (double value) {
        styleTracker.getCurrentComponent().setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public STextArea y (double value) {
        styleTracker.getCurrentComponent().setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public STextArea width (double value) {
        styleTracker.getCurrentComponent().setPrefWidth(value);
        return this;
    }

    @Override
    public STextArea height (double value) {
        styleTracker.getCurrentComponent().setPrefHeight(value);
        return this;
    }

    @Override
    public STextArea scaleX (double value) {
        styleTracker.getCurrentComponent().setScaleX(value);
        return this;
    }

    @Override
    public STextArea scaleY (double value) {
        styleTracker.getCurrentComponent().setScaleY(value);
        return this;
    }

    @Override
    public STextArea translateX (double value) {
        styleTracker.getCurrentComponent().setTranslateX(value);
        return this;
    }

    @Override
    public STextArea translateY (double value) {
        styleTracker.getCurrentComponent().setTranslateY(value);
        return this;
    }

    @Override
    public STextArea rotate (double value) {
        styleTracker.getCurrentComponent().setRotate(value);
        return this;
    }

    @Override
    public STextArea padding (double value) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public STextArea topPadding (double value) {
        PaddingUtil.setTopPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea rightPadding (double value) {
        PaddingUtil.setRightPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomPadding (double value) {
        PaddingUtil.setBottomPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea leftPadding (double value) {
        PaddingUtil.setLeftPadding(styleTracker.getCurrentComponent(), value);
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
    public STextArea margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public STextArea margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public STextArea topMargin(double value){
        margin.setTop(value);
        return this;
    }

    @Override
    public STextArea rightMargin(double value){
        margin.setRight(value);
        return this;
    }

    @Override
    public STextArea bottomMargin(double value){
        margin.setBottom(value);
        return this;
    }

    @Override
    public STextArea leftMargin(double value){
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
    public STextArea bgColor (String color) {
        BackgroundSetter.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public STextArea bgColor (Color color) {
        BackgroundSetter.setBackgroundColor(styleTracker.getCurrentComponent(), color);
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
    public STextArea bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public STextArea bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public STextArea bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public STextArea topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomLeftBgRadius (double value) {
        BackgroundSetter.setBottomLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
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
    public STextArea bgInsets (double value) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public STextArea topInset (double value) {
        BackgroundSetter.setTopInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea rightInset (double value) {
        BackgroundSetter.setRightInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomInset (double value) {
        BackgroundSetter.setBottomInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea leftInset (double value) {
        BackgroundSetter.setLeftInset(styleTracker.getCurrentComponent(), value);
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
    public STextArea border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public STextArea border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public STextArea topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public STextArea rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public STextArea bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public STextArea leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public STextArea topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public STextArea rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public STextArea bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public STextArea leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public STextArea borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public STextArea topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public STextArea rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public STextArea bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public STextArea leftBorderStyle (String borderStyle) {
        BorderSetter.setLeftBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
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
    public STextArea borderWidth (double value) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public STextArea topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea leftBorderWidth (double value) {
        BorderSetter.setLeftBorderWidth(styleTracker.getCurrentComponent(), value);
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
    public STextArea borderColor (String colorValue) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public STextArea borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public STextArea topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public STextArea rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public STextArea bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public STextArea leftBorderColor (String colorValue) {
        BorderSetter.setLeftBorderColor(styleTracker.getCurrentComponent(), colorValue);
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
    public STextArea borderColor (Color color) {
        BorderSetter.setBorderColor(this, color);
        return this;
    }

    @Override
    public STextArea borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), top, right, top, right);
        return this;
    }

    @Override
    public STextArea topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public STextArea rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public STextArea bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public STextArea leftBorderColor (Color color) {
        BorderSetter.setLeftBorderColor(styleTracker.getCurrentComponent(), color);
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
    public STextArea borderOpacity (double value) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public STextArea topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea leftBorderOpacity (double value) {
        BorderSetter.setLeftBorderOpacity(styleTracker.getCurrentComponent(), value);
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
    public STextArea borderRadius (double value) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public STextArea topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomLeftBorderRadius (double value) {
        BorderSetter.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), value);
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
    public STextArea borderInsets (double value) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public STextArea borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public STextArea topBorderInset (double value) {
        BorderSetter.setTopBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public STextArea leftBorderInset (double value) {
        BorderSetter.setLeftBorderInset(styleTracker.getCurrentComponent(), value);
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
    public STextArea removeBorder () {
        styleTracker.getCurrentComponent().setBorder(null);
        return this;
    }

    @Override
    public STextArea removeTopBorder () {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public STextArea removeRightBorder () {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public STextArea removeBottomBorder () {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public STextArea removeLeftBorder () {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public STextArea shape(String svg){
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        styleTracker.getCurrentComponent().setShape(svgPath);
        return this;
    }

    @Override
    public STextArea shape(Shape shape){
        styleTracker.getCurrentComponent().setShape(shape);
        return this;
    }

    @Override
    public STextArea font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public STextArea font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public STextArea font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public STextArea font (String fontFamily, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public STextArea fontColor (Color color) {
        if(color != null){
            cssStyleManager.setProperty("-fx-text-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    @Override
    public STextArea fontColor (String color) {
        if(color != null){
            cssStyleManager.setProperty("-fx-text-fill", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    @Override
    public Color getFontColor () {
        return (Color) text.getFill();
    }

    @Override
    public String getFontColorHex () {
        return ColorUtil.getHexFromColor((Color) text.getFill());
    }

    @Override
    public STextArea fontFamily (String fontFamily) {
        FontSetter.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public STextArea fontPosture (FontPosture fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public STextArea fontPosture (String fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public STextArea fontWeight (FontWeight fontWeight) {
        FontSetter.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public STextArea fontSize (double fontSize) {
        FontSetter.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }

    // TextArea-related methods
    public STextArea highlightFill(Color color){
        if(color != null){
            cssStyleManager.setProperty("-fx-highlight-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public STextArea highlightFill(String color){
        if(color != null){
            cssStyleManager.setProperty("-fx-highlight-fill", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public STextArea highlightTextFill(Color color){
        if(color != null){
            cssStyleManager.setProperty("-fx-highlight-text-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public STextArea highlightTextFill(String color){
        if(color != null){
            cssStyleManager.setProperty("-fx-highlight-text-fill", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public STextArea promptTextFill(Color color){
        if(color != null){
            cssStyleManager.setProperty("-fx-prompt-text-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public STextArea promptTextFill(String color){
        if(color != null){
            cssStyleManager.setProperty("-fx-prompt-text-fill", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    // ScrollPane-related methods
    public STextArea hBarPolicy(ScrollPane.ScrollBarPolicy scrollBarPolicy){
        scrollPane.setHbarPolicy(scrollBarPolicy);
        return this;
    }

    public STextArea vBarPolicy(ScrollPane.ScrollBarPolicy scrollBarPolicy){
        scrollPane.setVbarPolicy(scrollBarPolicy);
        return this;
    }

    public STextArea vMin(double value){
        scrollPane.setVmin(value);
        return this;
    }

    public STextArea vMax(double value){
        scrollPane.setVmax(value);
        return this;
    }

    public STextArea hMin(double value){
        scrollPane.setHmin(value);
        return this;
    }

    public STextArea hMax(double value){
        scrollPane.setHmax(value);
        return this;
    }

    public STextArea hValue(double value){
        scrollPane.setHvalue(value);
        return this;
    }

    public STextArea vValue(double value){
        scrollPane.setVvalue(value);
        return this;
    }

    public STextArea fitToWidth(boolean fit){
        scrollPane.setFitToWidth(fit);
        return this;
    }

    public STextArea fitToHeight(boolean fit){
        scrollPane.setFitToHeight(fit);
        return this;
    }

    public STextArea pannable(boolean pannable){
        scrollPane.setPannable(pannable);
        return this;
    }

    public STextArea minViewportWidth(double value){
        scrollPane.setMinViewportWidth(value);
        return this;
    }

    public STextArea minViewportHeight(double value){
        scrollPane.setMinViewportHeight(value);
        return this;
    }

    public STextArea prefViewportWidth(double value){
        scrollPane.setPrefViewportWidth(value);
        return this;
    }

    public STextArea prefViewportHeight(double value){
        scrollPane.setPrefViewportHeight(value);
        return this;
    }

    public STextArea viewportBounds(Bounds bounds){
        scrollPane.setViewportBounds(bounds);
        return this;
    }
}
