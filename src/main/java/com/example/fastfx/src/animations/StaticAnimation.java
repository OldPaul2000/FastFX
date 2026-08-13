package com.example.fastfx.src.animations;

import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;

/*
 * Used to change styles in simple methods(e.g. x(100)) when performing a pseudo-class action(e.g. border style).<br>
 * It's purpose is to make setting properties in the components in the same way as they are set with<br>
 * regular animations but when simple methods are used therefore no animation is set<br>
 * It's used to keep the same structure for setting the properties.
 */
public class StaticAnimation {

    private Region element;
    private Map<String, String[]> stringValues;
    private Map<String, double[]> doubleValues;

    public StaticAnimation (Region element) {
        this.element = element;
        stringValues = new HashMap<>();
        doubleValues = new HashMap<>();
    }

    public void updateStartDoubleValue (String animationType, double value) {
        double[] values = doubleValues.get(animationType);
        if (values != null && values.length == 2) {
            values[0] = value;
        }
    }

    public void updateEndDoubleValue (String animationType, double value) {
        double[] values = doubleValues.get(animationType);
        if (values != null && values.length == 2) {
            values[1] = value;
        }
    }

    public void updateStartStringValue (String animationType, String color) {
        String[] values = stringValues.get(animationType);
        if (values != null && values.length == 2) {
            values[0] = color;
        }
    }

    public void updateEndStringValue (String animationType, String color) {
        String[] values = stringValues.get(animationType);
        if (values != null && values.length == 2) {
            values[1] = color;
        }
    }

    public void addAnimation (String animation, String initialValue, String finalValue) {
        if (StaticAnimations.get(animation) != null) {
            stringValues.put(animation, new String[]{initialValue, finalValue});
        }
    }

    public void addAnimation (String animation, double initialValue, double finalValue) {
        if (StaticAnimations.get(animation) != null) {
            doubleValues.put(animation, new double[]{initialValue, finalValue});
        }
    }

    public void addAnimation (String animation, double initialFirstValue, double initialSecondValue, double finalFirstValue, double finalSecondValue) {
        if (StaticAnimations.get(animation) != null) {
            doubleValues.put(animation, new double[]{initialFirstValue, initialSecondValue, finalFirstValue, finalSecondValue});
        }
    }

    public void addAnimation (String animation, double initialFirstValue, double initialSecondValue, double initialThirdValue, double initialFourthValue, double finalFirstValue, double finalSecondValue, double finalThirdValue, double finalFourthValue) {
        if (StaticAnimations.get(animation) != null) {
            doubleValues.put(animation, new double[]{initialFirstValue, initialSecondValue, initialThirdValue, initialFourthValue, finalFirstValue, finalSecondValue, finalThirdValue, finalFourthValue});
        }
    }

    public void removeAnimation (String animation) {
        if (StaticAnimations.get(animation) != null) {
            stringValues.remove(animation);
            doubleValues.remove(animation);
        }
    }

    public void play () {
        //**********************
        //*  BASIC PROPERTIES  *
        //**********************
        if (doubleValues.containsKey(StaticAnimations.LAYOUT_X)) {
            element.setLayoutX(doubleValues.get(StaticAnimations.LAYOUT_X)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.LAYOUT_Y)) {
            element.setLayoutY(doubleValues.get(StaticAnimations.LAYOUT_Y)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.WIDTH)) {
            element.setPrefWidth(doubleValues.get(StaticAnimations.WIDTH)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.HEIGHT)) {
            element.setPrefHeight(doubleValues.get(StaticAnimations.HEIGHT)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.SCALE_X)) {
            element.setScaleX(doubleValues.get(StaticAnimations.SCALE_X)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.SCALE_Y)) {
            element.setScaleY(doubleValues.get(StaticAnimations.SCALE_Y)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.TRANSLATE_X)) {
            element.setTranslateX(doubleValues.get(StaticAnimations.TRANSLATE_X)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.TRANSLATE_Y)) {
            element.setTranslateY(doubleValues.get(StaticAnimations.TRANSLATE_Y)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.ROTATE)) {
            element.setRotate(doubleValues.get(StaticAnimations.ROTATE)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.PADDING);
            if (values.length == 2) {
                PaddingUtil.setPadding(element, values[1]);
            }
            if (values.length == 4) {
                PaddingUtil.setPadding(element, values[2], values[3], values[2], values[3]);
            }
            if (values.length == 8) {
                PaddingUtil.setPadding(element, values[0], values[1], values[2], values[3]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.TOP_PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.TOP_PADDING);
            PaddingUtil.setTopPadding(element, values[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.RIGHT_PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.RIGHT_PADDING);
            PaddingUtil.setRightPadding(element, values[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BOTTOM_PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.BOTTOM_PADDING);
            PaddingUtil.setBottomPadding(element, values[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.LEFT_PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.LEFT_PADDING);
            PaddingUtil.setLeftPadding(element, values[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.MARGIN)) {
            double[] values = doubleValues.get(StaticAnimations.MARGIN);
            // Need to implement in another way because Region doesn't have a Margin property
        }
        //**********************
        //*  BASIC PROPERTIES  *
        //**********************
        //***************************
        //*  BACKGROUND PROPERTIES  *
        //***************************
        if (stringValues.containsKey(StaticAnimations.BG_COLOR)) {
            BackgroundUtil.setBackgroundColor(element, stringValues.get(StaticAnimations.BG_COLOR)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_OPACITY)) {
            BackgroundUtil.setBackgroundOpacity(element, doubleValues.get(StaticAnimations.BG_OPACITY)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BG_RADIUS);
            if (values.length == 2) {
                BackgroundUtil.setBackgroundRadius(element, values[1]);
            }
            if (values.length == 4) {
                BackgroundUtil.setBackgroundRadius(element, values[2], values[3]);
            }
            if (values.length == 8) {
                BackgroundUtil.setBackgroundRadius(element, values[4], values[5], values[6], values[7]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BG_TL_RADIUS)) {
            BackgroundUtil.setTopLeftBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_TL_RADIUS)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_TR_RADIUS)) {
            BackgroundUtil.setTopRightBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_TR_RADIUS)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_BR_RADIUS)) {
            BackgroundUtil.setBottomRightBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_BR_RADIUS)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_BL_RADIUS)) {
            BackgroundUtil.setBottomLeftBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_BL_RADIUS)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_INSETS)) {
            double[] values = doubleValues.get(StaticAnimations.BG_INSETS);
            if (values.length == 2) {
                BackgroundUtil.setInsets(element, values[1]);
            }
            if (values.length == 4) {
                BackgroundUtil.setInsets(element, values[2], values[3]);
            }
            if (values.length == 8) {
                BackgroundUtil.setInsets(element, values[4], values[5], values[6], values[7]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BG_TOP_INSET)) {
            BackgroundUtil.setTopInset(element, doubleValues.get(StaticAnimations.BG_TOP_INSET)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_RIGHT_INSET)) {
            BackgroundUtil.setRightInset(element, doubleValues.get(StaticAnimations.BG_RIGHT_INSET)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_BOTTOM_INSET)) {
            BackgroundUtil.setBottomInset(element, doubleValues.get(StaticAnimations.BG_BOTTOM_INSET)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_LEFT_INSET)) {
            BackgroundUtil.setLeftInset(element, doubleValues.get(StaticAnimations.BG_LEFT_INSET)[1]);
        }
        //***************************
        //*  BACKGROUND PROPERTIES  *
        //***************************
        //***********************
        //*  BORDER PROPERTIES  *
        //***********************
        if (stringValues.containsKey(StaticAnimations.BORDER)) {
            String[] values = stringValues.get(StaticAnimations.BORDER);
            if (values.length == 8) {
                String color = values[4];
                String style = values[5];
                double radius = Double.parseDouble(values[6]);
                double width = Double.parseDouble(values[7]);
                BorderUtil.setBorder(element, color, style, radius, width);
            }
            if (values.length == 20) {
                String color = values[10];
                String style = values[11];
                double tlRadius = Double.parseDouble(values[12]);
                double trRadius = Double.parseDouble(values[13]);
                double brRadius = Double.parseDouble(values[14]);
                double blRadius = Double.parseDouble(values[15]);
                double topWidth = Double.parseDouble(values[16]);
                double rightWidth = Double.parseDouble(values[17]);
                double bottomWidth = Double.parseDouble(values[18]);
                double leftWidth = Double.parseDouble(values[19]);
                BorderUtil.setBorder(element, color, style, tlRadius, trRadius, brRadius, blRadius, topWidth, rightWidth, bottomWidth, leftWidth);
            }
        }
        if (stringValues.containsKey(StaticAnimations.TOP_BORDER)) {
            String[] values = stringValues.get(StaticAnimations.TOP_BORDER);
            BorderUtil.setTopBorder(element, values[3], values[4], Double.parseDouble(values[5]));
        }
        if (stringValues.containsKey(StaticAnimations.RIGHT_BORDER)) {
            String[] values = stringValues.get(StaticAnimations.RIGHT_BORDER);
            BorderUtil.setRightBorder(element, values[3], values[4], Double.parseDouble(values[5]));
        }
        if (stringValues.containsKey(StaticAnimations.BOTTOM_BORDER)) {
            String[] values = stringValues.get(StaticAnimations.BOTTOM_BORDER);
            BorderUtil.setBottomBorder(element, values[3], values[4], Double.parseDouble(values[5]));
        }
        if (stringValues.containsKey(StaticAnimations.LEFT_BORDER)) {
            String[] values = stringValues.get(StaticAnimations.LEFT_BORDER);
            BorderUtil.setLeftBorder(element, values[3], values[4], Double.parseDouble(values[5]));
        }
        if (stringValues.containsKey(StaticAnimations.BR_TOP_COLOR)) {
            BorderUtil.setTopBorderColor(element, stringValues.get(StaticAnimations.BR_TOP_COLOR)[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_RIGHT_COLOR)) {
            BorderUtil.setRightBorderColor(element, stringValues.get(StaticAnimations.BR_RIGHT_COLOR)[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_BOTTOM_COLOR)) {
            BorderUtil.setBottomBorderColor(element, stringValues.get(StaticAnimations.BR_BOTTOM_COLOR)[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_LEFT_COLOR)) {
            BorderUtil.setLeftBorderColor(element, stringValues.get(StaticAnimations.BR_LEFT_COLOR)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_WIDTH)) {
            double[] values = doubleValues.get(StaticAnimations.BR_WIDTH);
            if (values.length == 2) {
                BorderUtil.setBorderWidth(element, values[1]);
            }
            if (values.length == 4) {
                BorderUtil.setBorderWidth(element, values[2], values[3], values[2], values[3]);
            }
            if (values.length == 8) {
                BorderUtil.setBorderWidth(element, values[4], values[5], values[6], values[7]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BR_TOP_WIDTH)) {
            BorderUtil.setTopBorderWidth(element, doubleValues.get(StaticAnimations.BR_TOP_WIDTH)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_RIGHT_WIDTH)) {
            BorderUtil.setRightBorderWidth(element, doubleValues.get(StaticAnimations.BR_RIGHT_WIDTH)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_BOTTOM_WIDTH)) {
            BorderUtil.setBottomBorderWidth(element, doubleValues.get(StaticAnimations.BR_BOTTOM_WIDTH)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_LEFT_WIDTH)) {
            BorderUtil.setLeftBorderWidth(element, doubleValues.get(StaticAnimations.BR_LEFT_WIDTH)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_INSETS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_INSETS);
            if (values.length == 2) {
                BorderUtil.setBorderInsets(element, values[1]);
            }
            if (values.length == 4) {
                BorderUtil.setBorderInsets(element, values[2], values[3], values[2], values[3]);
            }
            if (values.length == 8) {
                BorderUtil.setBorderInsets(element, values[4], values[5], values[6], values[7]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BR_TOP_INSET)) {
            double[] values = doubleValues.get(StaticAnimations.BR_TOP_INSET);
            BorderUtil.setTopBorderInset(element, values[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_RIGHT_INSET)) {
            double[] values = doubleValues.get(StaticAnimations.BR_RIGHT_INSET);
            BorderUtil.setRightBorderInset(element, values[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_BOTTOM_INSET)) {
            double[] values = doubleValues.get(StaticAnimations.BR_BOTTOM_INSET);
            BorderUtil.setBottomBorderInset(element, values[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_LEFT_INSET)) {
            double[] values = doubleValues.get(StaticAnimations.BR_LEFT_INSET);
            BorderUtil.setLeftBorderInset(element, values[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_OPACITY)) {
            double[] values = doubleValues.get(StaticAnimations.BR_OPACITY);
            if (values.length == 2) {
                BorderUtil.setBorderOpacity(element, values[1]);
            }
            if (values.length == 4) {
                BorderUtil.setBorderOpacity(element, values[2], values[3]);
            }
            if (values.length == 8) {
                BorderUtil.setBorderOpacity(element, values[4], values[5], values[6], values[7]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BR_TOP_OPACITY)) {
            BorderUtil.setTopBorderOpacity(element, doubleValues.get(StaticAnimations.BR_TOP_OPACITY)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_RIGHT_OPACITY)) {
            BorderUtil.setRightBorderOpacity(element, doubleValues.get(StaticAnimations.BR_RIGHT_OPACITY)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_BOTTOM_OPACITY)) {
            BorderUtil.setBottomBorderOpacity(element, doubleValues.get(StaticAnimations.BR_BOTTOM_OPACITY)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_LEFT_OPACITY)) {
            BorderUtil.setLeftBorderOpacity(element, doubleValues.get(StaticAnimations.BR_LEFT_OPACITY)[1]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_RADIUS);
            if (values.length == 2) {
                BorderUtil.setBorderRadius(element, values[1]);
            }
            if (values.length == 4) {
                BorderUtil.setBorderRadius(element, values[2], values[3], values[2], values[3]);
            }
            if (values.length == 8) {
                BorderUtil.setBorderRadius(element, values[4], values[5], values[6], values[7]);
            }
        }
        if (stringValues.containsKey(StaticAnimations.BR_TL_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_TL_RADIUS);
            BorderUtil.setTopLeftBorderRadius(element, values[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_TR_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_TR_RADIUS);
            BorderUtil.setTopRightBorderRadius(element, values[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_BR_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_BR_RADIUS);
            BorderUtil.setBottomRightBorderRadius(element, values[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_BL_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_BL_RADIUS);
            BorderUtil.setBottomLeftBorderRadius(element, values[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_STYLE)) {
            BorderUtil.setBorderStyle(element, stringValues.get(StaticAnimations.BR_STYLE)[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_TOP_STYLE)) {
            BorderUtil.setTopBorderStyle(element, stringValues.get(StaticAnimations.BR_TOP_STYLE)[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_RIGHT_STYLE)) {
            BorderUtil.setRightBorderStyle(element, stringValues.get(StaticAnimations.BR_RIGHT_STYLE)[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_BOTTOM_STYLE)) {
            BorderUtil.setBottomBorderStyle(element, stringValues.get(StaticAnimations.BR_BOTTOM_STYLE)[1]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_LEFT_STYLE)) {
            BorderUtil.setLeftBorderStyle(element, stringValues.get(StaticAnimations.BR_LEFT_STYLE)[1]);
        }
        //***********************
        //*  BORDER PROPERTIES  *
        //***********************
    }

    public void reverse () {
        //**********************
        //*  BASIC PROPERTIES  *
        //**********************
        if (doubleValues.containsKey(StaticAnimations.LAYOUT_X)) {
            element.setLayoutX(doubleValues.get(StaticAnimations.LAYOUT_X)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.LAYOUT_Y)) {
            element.setLayoutY(doubleValues.get(StaticAnimations.LAYOUT_Y)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.WIDTH)) {
            element.setPrefWidth(doubleValues.get(StaticAnimations.WIDTH)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.HEIGHT)) {
            element.setPrefHeight(doubleValues.get(StaticAnimations.HEIGHT)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.SCALE_X)) {
            element.setScaleX(doubleValues.get(StaticAnimations.SCALE_X)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.SCALE_Y)) {
            element.setScaleY(doubleValues.get(StaticAnimations.SCALE_Y)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.TRANSLATE_X)) {
            element.setTranslateX(doubleValues.get(StaticAnimations.TRANSLATE_X)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.TRANSLATE_Y)) {
            element.setTranslateY(doubleValues.get(StaticAnimations.TRANSLATE_Y)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.ROTATE)) {
            element.setRotate(doubleValues.get(StaticAnimations.ROTATE)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.PADDING);
            if (values.length == 2) {
                element.setPadding(new Insets(values[0]));
            }
            if (values.length == 4) {
                element.setPadding(new Insets(values[0], values[1], values[0], values[1]));
            }
            if (values.length == 8) {
                element.setPadding(new Insets(values[0], values[1], values[2], values[3]));
            }
        }
        if (doubleValues.containsKey(StaticAnimations.TOP_PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.TOP_PADDING);
            PaddingUtil.setTopPadding(element, values[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.RIGHT_PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.RIGHT_PADDING);
            PaddingUtil.setRightPadding(element, values[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BOTTOM_PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.BOTTOM_PADDING);
            PaddingUtil.setBottomPadding(element, values[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.LEFT_PADDING)) {
            double[] values = doubleValues.get(StaticAnimations.LEFT_PADDING);
            PaddingUtil.setLeftPadding(element, values[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.MARGIN)) {
            double[] values = doubleValues.get(StaticAnimations.MARGIN);
            // Need to implement in another way because Region doesn't have a Margin property
        }
        //**********************
        //*  BASIC PROPERTIES  *
        //**********************
        //***************************
        //*  BACKGROUND PROPERTIES  *
        //***************************
        if (stringValues.containsKey(StaticAnimations.BG_COLOR)) {
            BackgroundUtil.setBackgroundColor(element, stringValues.get(StaticAnimations.BG_COLOR)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_OPACITY)) {
            BackgroundUtil.setBackgroundOpacity(element, doubleValues.get(StaticAnimations.BG_OPACITY)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BG_RADIUS);
            if (values.length == 2) {
                BackgroundUtil.setBackgroundRadius(element, values[0]);
            }
            if (values.length == 4) {
                BackgroundUtil.setBackgroundRadius(element, values[0], values[1]);
            }
            if (values.length == 8) {
                BackgroundUtil.setBackgroundRadius(element, values[0], values[1], values[2], values[3]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BG_TL_RADIUS)) {
            BackgroundUtil.setTopLeftBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_TL_RADIUS)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_TR_RADIUS)) {
            BackgroundUtil.setTopRightBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_TR_RADIUS)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_BR_RADIUS)) {
            BackgroundUtil.setBottomRightBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_BR_RADIUS)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_BL_RADIUS)) {
            BackgroundUtil.setBottomLeftBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_BL_RADIUS)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_INSETS)) {
            double[] values = doubleValues.get(StaticAnimations.BG_INSETS);
            if (values.length == 2) {
                BackgroundUtil.setInsets(element, values[0]);
            }
            if (values.length == 4) {
                BackgroundUtil.setInsets(element, values[0], values[1]);
            }
            if (values.length == 8) {
                BackgroundUtil.setInsets(element, values[0], values[1], values[2], values[3]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BG_TOP_INSET)) {
            BackgroundUtil.setTopInset(element, doubleValues.get(StaticAnimations.BG_TOP_INSET)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_RIGHT_INSET)) {
            BackgroundUtil.setRightInset(element, doubleValues.get(StaticAnimations.BG_RIGHT_INSET)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_BOTTOM_INSET)) {
            BackgroundUtil.setBottomInset(element, doubleValues.get(StaticAnimations.BG_BOTTOM_INSET)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BG_LEFT_INSET)) {
            BackgroundUtil.setLeftInset(element, doubleValues.get(StaticAnimations.BG_LEFT_INSET)[0]);
        }
        //***************************
        //*  BACKGROUND PROPERTIES  *
        //***************************
        //***********************
        //*  BORDER PROPERTIES  *
        //***********************
        if (stringValues.containsKey(StaticAnimations.BORDER)) {
            String[] values = stringValues.get(StaticAnimations.BORDER);
            if (values.length == 8) {
                String color = values[0];
                String style = values[1];
                double radius = Double.parseDouble(values[2]);
                double width = Double.parseDouble(values[3]);
                BorderUtil.setBorder(element, color, style, radius, width);
            }
            if (values.length == 20) {
                String color = values[0];
                String style = values[1];
                double tlRadius = Double.parseDouble(values[2]);
                double trRadius = Double.parseDouble(values[3]);
                double brRadius = Double.parseDouble(values[4]);
                double blRadius = Double.parseDouble(values[5]);
                double topWidth = Double.parseDouble(values[6]);
                double rightWidth = Double.parseDouble(values[7]);
                double bottomWidth = Double.parseDouble(values[8]);
                double leftWidth = Double.parseDouble(values[9]);
                BorderUtil.setBorder(element, color, style, tlRadius, trRadius, brRadius, blRadius, topWidth, rightWidth, bottomWidth, leftWidth);
            }
        }
        if (stringValues.containsKey(StaticAnimations.TOP_BORDER)) {
            String[] values = stringValues.get(StaticAnimations.TOP_BORDER);
            BorderUtil.setTopBorder(element, values[0], values[1], Double.parseDouble(values[2]));
        }
        if (stringValues.containsKey(StaticAnimations.RIGHT_BORDER)) {
            String[] values = stringValues.get(StaticAnimations.RIGHT_BORDER);
            BorderUtil.setRightBorder(element, values[0], values[1], Double.parseDouble(values[2]));
        }
        if (stringValues.containsKey(StaticAnimations.BOTTOM_BORDER)) {
            String[] values = stringValues.get(StaticAnimations.BOTTOM_BORDER);
            BorderUtil.setBottomBorder(element, values[0], values[1], Double.parseDouble(values[2]));
        }
        if (stringValues.containsKey(StaticAnimations.LEFT_BORDER)) {
            String[] values = stringValues.get(StaticAnimations.LEFT_BORDER);
            BorderUtil.setLeftBorder(element, values[0], values[1], Double.parseDouble(values[2]));
        }
        if (stringValues.containsKey(StaticAnimations.BR_TOP_COLOR)) {
            BorderUtil.setTopBorderColor(element, stringValues.get(StaticAnimations.BR_TOP_COLOR)[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_RIGHT_COLOR)) {
            BorderUtil.setRightBorderColor(element, stringValues.get(StaticAnimations.BR_RIGHT_COLOR)[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_BOTTOM_COLOR)) {
            BorderUtil.setBottomBorderColor(element, stringValues.get(StaticAnimations.BR_BOTTOM_COLOR)[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_LEFT_COLOR)) {
            BorderUtil.setLeftBorderColor(element, stringValues.get(StaticAnimations.BR_LEFT_COLOR)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_WIDTH)) {
            double[] values = doubleValues.get(StaticAnimations.BR_WIDTH);
            if (values.length == 2) {
                BorderUtil.setBorderWidth(element, values[0]);
            }
            if (values.length == 4) {
                BorderUtil.setBorderWidth(element, values[0], values[1], values[0], values[1]);
            }
            if (values.length == 8) {
                BorderUtil.setBorderWidth(element, values[0], values[1], values[2], values[3]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BR_TOP_WIDTH)) {
            BorderUtil.setTopBorderWidth(element, doubleValues.get(StaticAnimations.BR_TOP_WIDTH)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_RIGHT_WIDTH)) {
            BorderUtil.setRightBorderWidth(element, doubleValues.get(StaticAnimations.BR_RIGHT_WIDTH)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_BOTTOM_WIDTH)) {
            BorderUtil.setBottomBorderWidth(element, doubleValues.get(StaticAnimations.BR_BOTTOM_WIDTH)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_LEFT_WIDTH)) {
            BorderUtil.setLeftBorderWidth(element, doubleValues.get(StaticAnimations.BR_LEFT_WIDTH)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_INSETS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_INSETS);
            if (values.length == 2) {
                BorderUtil.setBorderInsets(element, values[0]);
            }
            if (values.length == 4) {
                BorderUtil.setBorderInsets(element, values[0], values[1], values[0], values[1]);
            }
            if (values.length == 8) {
                BorderUtil.setBorderInsets(element, values[0], values[1], values[2], values[3]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BR_TOP_INSET)) {
            double[] values = doubleValues.get(StaticAnimations.BR_TOP_INSET);
            BorderUtil.setTopBorderInset(element, values[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_RIGHT_INSET)) {
            double[] values = doubleValues.get(StaticAnimations.BR_RIGHT_INSET);
            BorderUtil.setRightBorderInset(element, values[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_BOTTOM_INSET)) {
            double[] values = doubleValues.get(StaticAnimations.BR_BOTTOM_INSET);
            BorderUtil.setBottomBorderInset(element, values[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_LEFT_INSET)) {
            double[] values = doubleValues.get(StaticAnimations.BR_LEFT_INSET);
            BorderUtil.setLeftBorderInset(element, values[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_OPACITY)) {
            double[] values = doubleValues.get(StaticAnimations.BR_OPACITY);
            if (values.length == 2) {
                BorderUtil.setBorderOpacity(element, values[0]);
            }
            if (values.length == 4) {
                BorderUtil.setBorderOpacity(element, values[0], values[1]);
            }
            if (values.length == 8) {
                BorderUtil.setBorderOpacity(element, values[0], values[1], values[2], values[3]);
            }
        }
        if (doubleValues.containsKey(StaticAnimations.BR_TOP_OPACITY)) {
            BorderUtil.setTopBorderOpacity(element, doubleValues.get(StaticAnimations.BR_TOP_OPACITY)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_RIGHT_OPACITY)) {
            BorderUtil.setRightBorderOpacity(element, doubleValues.get(StaticAnimations.BR_RIGHT_OPACITY)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_BOTTOM_OPACITY)) {
            BorderUtil.setBottomBorderOpacity(element, doubleValues.get(StaticAnimations.BR_BOTTOM_OPACITY)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_LEFT_OPACITY)) {
            BorderUtil.setLeftBorderOpacity(element, doubleValues.get(StaticAnimations.BR_LEFT_OPACITY)[0]);
        }
        if (doubleValues.containsKey(StaticAnimations.BR_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_RADIUS);
            if (values.length == 2) {
                BorderUtil.setBorderRadius(element, values[0]);
            }
            if (values.length == 4) {
                BorderUtil.setBorderRadius(element, values[0], values[1], values[0], values[1]);
            }
            if (values.length == 8) {
                BorderUtil.setBorderRadius(element, values[0], values[1], values[2], values[3]);
            }
        }
        if (stringValues.containsKey(StaticAnimations.BR_TL_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_TL_RADIUS);
            BorderUtil.setTopLeftBorderRadius(element, values[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_TR_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_TR_RADIUS);
            BorderUtil.setTopRightBorderRadius(element, values[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_BR_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_BR_RADIUS);
            BorderUtil.setBottomRightBorderRadius(element, values[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_BL_RADIUS)) {
            double[] values = doubleValues.get(StaticAnimations.BR_BL_RADIUS);
            BorderUtil.setBottomLeftBorderRadius(element, values[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_STYLE)) {
            BorderUtil.setBorderStyle(element, stringValues.get(StaticAnimations.BR_STYLE)[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_TOP_STYLE)) {
            BorderUtil.setTopBorderStyle(element, stringValues.get(StaticAnimations.BR_TOP_STYLE)[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_RIGHT_STYLE)) {
            BorderUtil.setRightBorderStyle(element, stringValues.get(StaticAnimations.BR_RIGHT_STYLE)[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_BOTTOM_STYLE)) {
            BorderUtil.setBottomBorderStyle(element, stringValues.get(StaticAnimations.BR_BOTTOM_STYLE)[0]);
        }
        if (stringValues.containsKey(StaticAnimations.BR_LEFT_STYLE)) {
            BorderUtil.setLeftBorderStyle(element, stringValues.get(StaticAnimations.BR_LEFT_STYLE)[0]);
        }
        //***********************
        //*  BORDER PROPERTIES  *
        //***********************
        //*************
        //*  EFFECTS  *
        //*************
        //*************
        //*  EFFECTS  *
        //*************
    }
}
