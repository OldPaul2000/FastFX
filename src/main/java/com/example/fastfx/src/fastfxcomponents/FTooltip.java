package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.stylehelpers.CSSStyleManager;
import com.example.fastfx.src.util.color.ColorUtil;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class FTooltip extends Tooltip {
    private CSSStyleManager cssStyleManager;

    public FTooltip () {
        cssStyleManager = new CSSStyleManager();
    }

    public FTooltip (String text) {
        setText(text);
        cssStyleManager = new CSSStyleManager();
    }

    public FTooltip width (double value) {
        setPrefWidth(value);
        return this;
    }

    public FTooltip height (double value) {
        setPrefHeight(value);
        return this;
    }

    public FTooltip translateX (double value) {
        cssStyleManager.setProperty("-fx-translate-x", value, "px");
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip translateY (double value) {
        cssStyleManager.setProperty("-fx-translate-y", value, "px");
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip padding (double value) {
        cssStyleManager.setProperty("-fx-padding", value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip padding (double topBottom, double rightLeft) {
        cssStyleManager.setProperty("-fx-padding", topBottom, rightLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip padding (double top, double right, double bottom, double left) {
        cssStyleManager.setProperty("-fx-padding", top, right, bottom, left);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bgColor (String color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-background-color", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTooltip bgColor (Color color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-background-color", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTooltip bgOpacity (double value) {
        cssStyleManager.setProperty("-fx-background-color", ColorUtil.setHexOpacity(cssStyleManager.getProperty("-fx-background-color"), value));
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bgRadius (double value) {
        cssStyleManager.setProperty("-fx-background-radius", value + "px");
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        cssStyleManager.setProperty("-fx-background-radius", topLeftBottomRight, topRightBottomLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        cssStyleManager.setProperty("-fx-background-radius", topLeft, topRight, bottomRight, bottomLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        ;
        return this;
    }

    public FTooltip topLeftRadius (double value) {
        cssStyleManager.setNthParameter("-fx-background-radius", "number", 0, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }


    public FTooltip topRightRadius (double value) {
        cssStyleManager.setNthParameter("-fx-background-radius", "number", 1, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }


    public FTooltip bottomRightRadius (double value) {
        cssStyleManager.setNthParameter("-fx-background-radius", "number", 2, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }


    public FTooltip bottomLeftRadius (double value) {
        cssStyleManager.setNthParameter("-fx-background-radius", "number", 3, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bgInsets (double value) {
        cssStyleManager.setProperty("-fx-background-insets", value + "px");
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bgInsets (double topLeftBottomRight, double topRightBottomLeft) {
        cssStyleManager.setProperty("-fx-background-insets", topLeftBottomRight, topRightBottomLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bgInsets (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        cssStyleManager.setProperty("-fx-background-insets", topLeft, topRight, bottomRight, bottomLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip topInset (double value) {
        cssStyleManager.setNthParameter("-fx-background-insets", "number", 0, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip rightInset (double value) {
        cssStyleManager.setNthParameter("-fx-background-insets", "number", 1, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bottomInset (double value) {
        cssStyleManager.setNthParameter("-fx-background-insets", "number", 2, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip leftInset (double value) {
        cssStyleManager.setNthParameter("-fx-background-insets", "number", 3, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        cssStyleManager.setProperty("-fx-border-color", colorValue);
        cssStyleManager.setProperty("-fx-border-style", borderStyle);
        cssStyleManager.setProperty("-fx-border-radius", topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
        cssStyleManager.setProperty("-fx-border-width", topWidth, rightWidth, bottomWidth, leftWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip border (String colorValue, String borderStyle, double radius, double width) {
        cssStyleManager.setProperty("-fx-border-color", colorValue);
        cssStyleManager.setProperty("-fx-border-style", borderStyle);
        cssStyleManager.setProperty("-fx-border-radius", radius);
        cssStyleManager.setProperty("-fx-border-width", width);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip topBorder (String colorValue, String borderStyle, double borderWidth) {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 0, colorValue);
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 0, borderStyle);
        cssStyleManager.setNthParameter("-fx-border-width", "number", 0, borderWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip rightBorder (String colorValue, String borderStyle, double borderWidth) {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 1, colorValue);
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 1, borderStyle);
        cssStyleManager.setNthParameter("-fx-border-width", "number", 1, borderWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 2, colorValue);
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 2, borderStyle);
        cssStyleManager.setNthParameter("-fx-border-width", "number", 2, borderWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip leftBorder (String colorValue, String borderStyle, double borderWidth) {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 3, colorValue);
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 3, borderStyle);
        cssStyleManager.setNthParameter("-fx-border-width", "number", 3, borderWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip topBorder (Color color, String borderStyle, double borderWidth) {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 0, ColorUtil.getHexFromColor(color));
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 0, borderStyle);
        cssStyleManager.setNthParameter("-fx-border-width", "number", 0, borderWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip rightBorder (Color color, String borderStyle, double borderWidth) {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 1, ColorUtil.getHexFromColor(color));
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 1, borderStyle);
        cssStyleManager.setNthParameter("-fx-border-width", "number", 1, borderWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bottomBorder (Color color, String borderStyle, double borderWidth) {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 2, ColorUtil.getHexFromColor(color));
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 2, borderStyle);
        cssStyleManager.setNthParameter("-fx-border-width", "number", 2, borderWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip leftBorder (Color color, String borderStyle, double borderWidth) {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 3, ColorUtil.getHexFromColor(color));
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 3, borderStyle);
        cssStyleManager.setNthParameter("-fx-border-width", "number", 3, borderWidth);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip borderStyle (String value) {
        cssStyleManager.setProperty("-fx-border-style", value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip borderRadius (double value) {
        cssStyleManager.setProperty("-fx-border-radius", value + "px");
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip borderRadius (double topLeftBottomRight, double topRightBottomLeft) {
        cssStyleManager.setProperty("-fx-border-radius", topLeftBottomRight, topRightBottomLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }


    public FTooltip borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        cssStyleManager.setProperty("-fx-background-radius", topLeft, topRight, bottomRight, bottomLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip topLeftBorderRadius (double value) {
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 0, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }


    public FTooltip topRightBorderRadius (double value) {
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 1, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }


    public FTooltip bottomRightBorderRadius (double value) {
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 2, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }


    public FTooltip bottomLeftBorderRadius (double value) {
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 3, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip fontColor (Color color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-text-fill", ColorUtil.getHexFromColor(color));
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTooltip borderInsets (double value) {
        cssStyleManager.setProperty("-fx-border-insets", value + "px");
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip borderInsets (double topLeftBottomRight, double topRightBottomLeft) {
        cssStyleManager.setProperty("-fx-border-insets", topLeftBottomRight, topRightBottomLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip borderInsets (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        cssStyleManager.setProperty("-fx-border-insets", topLeft, topRight, bottomRight, bottomLeft);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip topBorderInset (double value) {
        cssStyleManager.setNthParameter("-fx-border-insets", "number", 0, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip rightBorderInset (double value) {
        cssStyleManager.setNthParameter("-fx-border-insets", "number", 1, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip bottomBorderInset (double value) {
        cssStyleManager.setNthParameter("-fx-border-insets", "number", 2, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip leftBorderInset (double value) {
        cssStyleManager.setNthParameter("-fx-border-insets", "number", 3, value);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip removeBorder () {
        cssStyleManager.setProperty("-fx-border-color", "transparent");
        cssStyleManager.setProperty("-fx-border-style", "solid");
        cssStyleManager.setProperty("-fx-border-width", 0);
        cssStyleManager.setProperty("-fx-border-radius", 0);
        cssStyleManager.setProperty("-fx-border-insets", 0);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip removeTopBorder () {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 0, "transparent");
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 0, "solid");
        cssStyleManager.setNthParameter("-fx-border-width", "number", 0, 0);
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 0, 0);
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 1, 0);
        cssStyleManager.setNthParameter("-fx-border-insets", "number", 0, 0);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip removeRightBorder () {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 1, "transparent");
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 1, "solid");
        cssStyleManager.setNthParameter("-fx-border-width", "number", 1, 0);
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 1, 0);
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 2, 0);
        cssStyleManager.setNthParameter("-fx-border-insets", "number", 1, 0);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip removeBottomBorder () {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 2, "transparent");
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 2, "solid");
        cssStyleManager.setNthParameter("-fx-border-width", "number", 2, 0);
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 2, 0);
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 3, 0);
        cssStyleManager.setNthParameter("-fx-border-insets", "number", 2, 0);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip removeLeftBorder () {
        cssStyleManager.setNthParameter("-fx-border-color", "color", 3, "transparent");
        cssStyleManager.setNthParameter("-fx-border-style", "borderStyle", 3, "solid");
        cssStyleManager.setNthParameter("-fx-border-width", "number", 3, 0);
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 3, 0);
        cssStyleManager.setNthParameter("-fx-border-radius", "number", 1, 0);
        cssStyleManager.setNthParameter("-fx-border-insets", "number", 3, 0);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    public FTooltip fontColor (String color) {
        if (color != null) {
            cssStyleManager.setProperty("-fx-text-fill", color);
            cssStyleManager.buildStyle();
            setStyle(cssStyleManager.getStyle());
        }
        return this;
    }

    public FTooltip effect (String effect) {
        if (effect == null) {
            effect = "null";
        }
        cssStyleManager.setProperty("-fx-effect", effect);
        cssStyleManager.buildStyle();
        setStyle(cssStyleManager.getStyle());
        return this;
    }

    // Tooltip-related methods
    public FTooltip showDelay (long millis) {
        setShowDelay(Duration.millis(millis));
        return this;
    }

    public FTooltip hideDelay (long millis) {
        setHideDelay(Duration.millis(millis));
        return this;
    }

    public FTooltip showDuration (long millis) {
        setShowDuration(Duration.millis(millis));
        return this;
    }
}
