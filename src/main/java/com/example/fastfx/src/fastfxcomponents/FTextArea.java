package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.cssstylehelpers.CSSStyleManager;
import com.example.fastfx.src.cssstylehelpers.ComponentStyleTracker;
import com.example.fastfx.src.cssstylehelpers.MainProps;
import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.color.ColorUtil;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.FontUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
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

public class FTextArea extends TextArea implements FLabeled {
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

    public FTextArea () {
        setText("");
        this.margin = new Margin(0);
        this.styleTracker = new ComponentStyleTracker();
        this.cssStyleManager = new CSSStyleManager();
        initElements();
        initAllowedStyleProperties();
    }

    public FTextArea (String textElement) {
        setText(textElement);
        this.margin = new Margin(0);
        this.styleTracker = new ComponentStyleTracker();
        this.cssStyleManager = new CSSStyleManager();
        initElements();
        initAllowedStyleProperties();
    }

    public FTextArea text (String text) {
        setText(text);
        return this;
    }

    public FTextArea promptText (String text) {
        setPromptText(text);
        return this;
    }

    private void initElements () {
        setSkin(new TextAreaSkin(this));
        this.scrollPane = (ScrollPane) lookup(".scroll-pane");
        scrollPane.setSkin(new ScrollPaneSkin(scrollPane));
        Set<Node> scrollBars = scrollPane.lookupAll(".scroll-bar");
        scrollBars.forEach(el -> {
            ScrollBar currScrollBar = (ScrollBar) el;
            if (currScrollBar.getOrientation() == Orientation.VERTICAL) {
                this.verticalScrollBar = currScrollBar;
            }
            else if (currScrollBar.getOrientation() == Orientation.HORIZONTAL) {
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

    public Text getTextObject () {
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

    public FTextArea verticalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea horizontalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea verticalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea horizontalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea incrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea decrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea incrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea decrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea incrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea decrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea incrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea decrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTextArea corner (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(corner);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    @Override
    public FTextArea x (double value) {
        styleTracker.getCurrentComponent().setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FTextArea y (double value) {
        styleTracker.getCurrentComponent().setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FTextArea width (double value) {
        styleTracker.getCurrentComponent().setPrefWidth(value);
        return this;
    }

    @Override
    public FTextArea height (double value) {
        styleTracker.getCurrentComponent().setPrefHeight(value);
        return this;
    }

    @Override
    public FTextArea scaleX (double value) {
        styleTracker.getCurrentComponent().setScaleX(value);
        return this;
    }

    @Override
    public FTextArea scaleY (double value) {
        styleTracker.getCurrentComponent().setScaleY(value);
        return this;
    }

    @Override
    public FTextArea translateX (double value) {
        styleTracker.getCurrentComponent().setTranslateX(value);
        return this;
    }

    @Override
    public FTextArea translateY (double value) {
        styleTracker.getCurrentComponent().setTranslateY(value);
        return this;
    }

    @Override
    public FTextArea rotate (double value) {
        styleTracker.getCurrentComponent().setRotate(value);
        return this;
    }

    @Override
    public FTextArea padding (double value) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FTextArea topPadding (double value) {
        PaddingUtil.setTopPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea rightPadding (double value) {
        PaddingUtil.setRightPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomPadding (double value) {
        PaddingUtil.setBottomPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea leftPadding (double value) {
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
    public FTextArea margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FTextArea margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FTextArea topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FTextArea rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FTextArea bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FTextArea leftMargin (double value) {
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
    public FTextArea bgColor (String color) {
        BackgroundUtil.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FTextArea bgColor (Color color) {
        BackgroundUtil.setBackgroundColor(styleTracker.getCurrentComponent(), color);
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
    public FTextArea bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FTextArea bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FTextArea bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FTextArea topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomLeftBgRadius (double value) {
        BackgroundUtil.setBottomLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
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
    public FTextArea bgInsets (double value) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FTextArea topInset (double value) {
        BackgroundUtil.setTopInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea rightInset (double value) {
        BackgroundUtil.setRightInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomInset (double value) {
        BackgroundUtil.setBottomInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea leftInset (double value) {
        BackgroundUtil.setLeftInset(styleTracker.getCurrentComponent(), value);
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
    public FTextArea border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FTextArea border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FTextArea topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTextArea rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTextArea bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTextArea leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTextArea topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTextArea rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTextArea bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTextArea leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTextArea borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FTextArea topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FTextArea rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FTextArea bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FTextArea leftBorderStyle (String borderStyle) {
        BorderUtil.setLeftBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
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
    public FTextArea borderWidth (double value) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FTextArea topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea leftBorderWidth (double value) {
        BorderUtil.setLeftBorderWidth(styleTracker.getCurrentComponent(), value);
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
    public FTextArea borderColor (String colorValue) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FTextArea borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FTextArea topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FTextArea rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FTextArea bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FTextArea leftBorderColor (String colorValue) {
        BorderUtil.setLeftBorderColor(styleTracker.getCurrentComponent(), colorValue);
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
    public FTextArea borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FTextArea borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, top, right);
        return this;
    }

    @Override
    public FTextArea topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FTextArea rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FTextArea bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FTextArea leftBorderColor (Color color) {
        BorderUtil.setLeftBorderColor(styleTracker.getCurrentComponent(), color);
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
    public FTextArea borderOpacity (double value) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FTextArea topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea leftBorderOpacity (double value) {
        BorderUtil.setLeftBorderOpacity(styleTracker.getCurrentComponent(), value);
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
    public FTextArea borderRadius (double value) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FTextArea topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomLeftBorderRadius (double value) {
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), value);
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
    public FTextArea borderInsets (double value) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTextArea borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FTextArea topBorderInset (double value) {
        BorderUtil.setTopBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTextArea leftBorderInset (double value) {
        BorderUtil.setLeftBorderInset(styleTracker.getCurrentComponent(), value);
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
    public FTextArea removeBorder () {
        styleTracker.getCurrentComponent().setBorder(null);
        return this;
    }

    @Override
    public FTextArea removeTopBorder () {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FTextArea removeRightBorder () {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FTextArea removeBottomBorder () {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FTextArea removeLeftBorder () {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FTextArea shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        styleTracker.getCurrentComponent().setShape(svgPath);
        return this;
    }

    @Override
    public FTextArea shape (Shape shape) {
        styleTracker.getCurrentComponent().setShape(shape);
        return this;
    }

    @Override
    public FTextArea font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public FTextArea font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public FTextArea font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public FTextArea font (String fontFamily, double fontSize) {
        FontUtil.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public FTextArea fontColor (Color color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-text-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    @Override
    public FTextArea fontColor (String color) {
        if (color != null) {
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
    public FTextArea fontFamily (String fontFamily) {
        FontUtil.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public FTextArea fontPosture (FontPosture fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public FTextArea fontPosture (String fontPosture) {
        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public FTextArea fontWeight (FontWeight fontWeight) {
        FontUtil.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public FTextArea fontSize (double fontSize) {
        FontUtil.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }

    // TextArea-related methods
    public FTextArea highlightFill (Color color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-highlight-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTextArea highlightFill (String color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-highlight-fill", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTextArea highlightTextFill (Color color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-highlight-text-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTextArea highlightTextFill (String color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-highlight-text-fill", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTextArea promptTextFill (Color color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-prompt-text-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTextArea promptTextFill (String color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-prompt-text-fill", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    // ScrollPane-related methods
    public FTextArea hBarPolicy (ScrollPane.ScrollBarPolicy scrollBarPolicy) {
        scrollPane.setHbarPolicy(scrollBarPolicy);
        return this;
    }

    public FTextArea vBarPolicy (ScrollPane.ScrollBarPolicy scrollBarPolicy) {
        scrollPane.setVbarPolicy(scrollBarPolicy);
        return this;
    }

    public FTextArea vMin (double value) {
        scrollPane.setVmin(value);
        return this;
    }

    public FTextArea vMax (double value) {
        scrollPane.setVmax(value);
        return this;
    }

    public FTextArea hMin (double value) {
        scrollPane.setHmin(value);
        return this;
    }

    public FTextArea hMax (double value) {
        scrollPane.setHmax(value);
        return this;
    }

    public FTextArea hValue (double value) {
        scrollPane.setHvalue(value);
        return this;
    }

    public FTextArea vValue (double value) {
        scrollPane.setVvalue(value);
        return this;
    }

    public FTextArea fitToWidth (boolean fit) {
        scrollPane.setFitToWidth(fit);
        return this;
    }

    public FTextArea fitToHeight (boolean fit) {
        scrollPane.setFitToHeight(fit);
        return this;
    }

    public FTextArea pannable (boolean pannable) {
        scrollPane.setPannable(pannable);
        return this;
    }

    public FTextArea minViewportWidth (double value) {
        scrollPane.setMinViewportWidth(value);
        return this;
    }

    public FTextArea minViewportHeight (double value) {
        scrollPane.setMinViewportHeight(value);
        return this;
    }

    public FTextArea prefViewportWidth (double value) {
        scrollPane.setPrefViewportWidth(value);
        return this;
    }

    public FTextArea prefViewportHeight (double value) {
        scrollPane.setPrefViewportHeight(value);
        return this;
    }

    public FTextArea viewportBounds (Bounds bounds) {
        scrollPane.setViewportBounds(bounds);
        return this;
    }
}
