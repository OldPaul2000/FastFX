package com.example.fastfx.src.style.animations;

import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
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

    public StaticAnimation(Region element){
        this.element = element;
        stringValues = new HashMap<>();
        doubleValues = new HashMap<>();
    }

    public void addAnimation(String animation, String initialValue, String finalValue){
        if(StaticAnimations.get(animation) != null){
            stringValues.put(animation, new String[]{initialValue, finalValue});
        }
    }

    public void addAnimation(String animation, double initialValue, double finalValue){
        if(StaticAnimations.get(animation) != null){
            doubleValues.put(animation, new double[]{initialValue, finalValue});
        }
    }

    public void addAnimation(String animation, double initialFirstValue, double initialSecondValue,
                             double finalFirstValue, double finalSecondValue){
        if(StaticAnimations.get(animation) != null){
            doubleValues.put(animation, new double[]{
                    initialFirstValue, initialSecondValue,
                    finalFirstValue, finalSecondValue
            });
        }
    }

    public void addAnimation(String animation, 
                             double initialFirstValue, double initialSecondValue,
                             double initialThirdValue, double initialFourthValue,
                             double finalFirstValue, double finalSecondValue,
                             double finalThirdValue, double finalFourthValue){
        if(StaticAnimations.get(animation) != null){
            doubleValues.put(animation, new double[]{
                    initialFirstValue, initialSecondValue,
                    initialThirdValue, initialFourthValue,
                    finalFirstValue, finalSecondValue,
                    finalThirdValue, finalFourthValue
            });
        }
    }

    public void removeAnimation(String animation){
        if(StaticAnimations.get(animation) != null){
            stringValues.remove(animation);
        }
    }

    public void play(){
        //**********************
        //*  BASIC PROPERTIES  *
        //**********************
        if(doubleValues.containsKey(StaticAnimations.LAYOUT_X)){
            element.setLayoutX(doubleValues.get(StaticAnimations.LAYOUT_X)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.LAYOUT_Y)){
            element.setLayoutY(doubleValues.get(StaticAnimations.LAYOUT_Y)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.WIDTH)){
            element.setPrefWidth(doubleValues.get(StaticAnimations.WIDTH)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.HEIGHT)){
            element.setPrefHeight(doubleValues.get(StaticAnimations.HEIGHT)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.SCALE_X)){
            element.setScaleX(doubleValues.get(StaticAnimations.SCALE_X)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.SCALE_Y)){
            element.setScaleY(doubleValues.get(StaticAnimations.SCALE_Y)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.TRANSLATE_X)){
            element.setTranslateX(doubleValues.get(StaticAnimations.TRANSLATE_X)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.TRANSLATE_Y)){
            element.setTranslateY(doubleValues.get(StaticAnimations.TRANSLATE_Y)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.ROTATE)){
            element.setRotate(doubleValues.get(StaticAnimations.ROTATE)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.PADDING)){
            double[] values = doubleValues.get(StaticAnimations.PADDING);
            if(values.length == 2){
                element.setPadding(new Insets(values[1]));
            }
            if(values.length == 4){
                element.setPadding(new Insets(values[2], values[3], values[2], values[3]));
            }
            if(values.length == 8){
                element.setPadding(new Insets(values[4], values[5], values[6], values[7]));
            }
        }
        if(doubleValues.containsKey(StaticAnimations.MARGIN)){
            double[] values = doubleValues.get(StaticAnimations.MARGIN);
            // Need to implement in another way because Region doesn't have a Margin property
        }
        //**********************
        //*  BASIC PROPERTIES  *
        //**********************
        //***************************
        //*  BACKGROUND PROPERTIES  *
        //***************************
        if(doubleValues.containsKey(StaticAnimations.BG_OPACITY)){
            BackgroundSetter.setBackgroundOpacity(element, doubleValues.get(StaticAnimations.BG_OPACITY)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BG_RADIUS);
            if(values.length == 2){
                BackgroundSetter.setBackgroundRadius(element, values[1]);
            }
            if(values.length == 4){
                BackgroundSetter.setBackgroundRadius(element, values[2], values[3]);
            }
            if(values.length == 8){
                BackgroundSetter.setBackgroundRadius(element, values[4], values[5], values[6], values[7]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BG_TL_RADIUS)){
            BackgroundSetter.setTopLeftBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_TL_RADIUS)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_TR_RADIUS)){
            BackgroundSetter.setTopRightBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_TR_RADIUS)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_BR_RADIUS)){
            BackgroundSetter.setBottomRightBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_BR_RADIUS)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_BL_RADIUS)){
            BackgroundSetter.setBottomLeftBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_BL_RADIUS)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_INSETS)){
            double[] values = doubleValues.get(StaticAnimations.BG_INSETS);
            if(values.length == 2){
                BackgroundSetter.setInsets(element, values[1]);
            }
            if(values.length == 4){
                BackgroundSetter.setInsets(element, values[2], values[3]);
            }
            if(values.length == 8){
                BackgroundSetter.setInsets(element, values[4], values[5], values[6], values[7]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BG_TOP_INSET)){
            BackgroundSetter.setTopInset(element, doubleValues.get(StaticAnimations.BG_TOP_INSET)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_RIGHT_INSET)){
            BackgroundSetter.setRightInset(element, doubleValues.get(StaticAnimations.BG_RIGHT_INSET)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_BOTTOM_INSET)){
            BackgroundSetter.setBottomInset(element, doubleValues.get(StaticAnimations.BG_BOTTOM_INSET)[1]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_LEFT_INSET)){
            BackgroundSetter.setLeftInset(element, doubleValues.get(StaticAnimations.BG_LEFT_INSET)[1]);
        }
        //***************************
        //*  BACKGROUND PROPERTIES  *
        //***************************
        //***********************
        //*  BORDER PROPERTIES  *
        //***********************
        if(stringValues.containsKey(StaticAnimations.BORDER)){
            String[] values = stringValues.get(StaticAnimations.BORDER);
            if(values.length == 8){
                String color = values[4];
                String style = values[5];
                double radius = Double.parseDouble(values[6]);
                double width = Double.parseDouble(values[7]);
                BorderSetter.setBorder(element, color, style, radius, width);
            }
            if(values.length == 20){
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
                BorderSetter.setBorder(element, color, style,
                        tlRadius, trRadius, brRadius, blRadius,
                        topWidth, rightWidth, bottomWidth, leftWidth);
            }
        }
        if(stringValues.containsKey(StaticAnimations.TOP_BORDER)){
            String[] values = stringValues.get(StaticAnimations.TOP_BORDER);
            BorderSetter.setTopBorder(element, values[3], values[4], Double.parseDouble(values[5]));
        }
        if(stringValues.containsKey(StaticAnimations.RIGHT_BORDER)){
            String[] values = stringValues.get(StaticAnimations.RIGHT_BORDER);
            BorderSetter.setRightBorder(element, values[3], values[4], Double.parseDouble(values[5]));
        }
        if(stringValues.containsKey(StaticAnimations.BOTTOM_BORDER)){
            String[] values = stringValues.get(StaticAnimations.BOTTOM_BORDER);
            BorderSetter.setBottomBorder(element, values[3], values[4], Double.parseDouble(values[5]));
        }
        if(stringValues.containsKey(StaticAnimations.LEFT_BORDER)){
            String[] values = stringValues.get(StaticAnimations.LEFT_BORDER);
            BorderSetter.setLeftBorder(element, values[3], values[4], Double.parseDouble(values[5]));
        }
        if(doubleValues.containsKey(StaticAnimations.BR_WIDTH)){
            double[] values = doubleValues.get(StaticAnimations.BR_WIDTH);
            if(values.length == 2){
                BorderSetter.setBorderWidth(element, values[1]);
            }
            if(values.length == 4){
                BorderSetter.setBorderWidth(element, values[2], values[3], values[2], values[3]);
            }
            if(values.length == 8){
                BorderSetter.setBorderWidth(element, values[4], values[5], values[6], values[7]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BR_INSETS)){
            double[] values = doubleValues.get(StaticAnimations.BR_INSETS);
            if(values.length == 2){
                BorderSetter.setBorderInsets(element, values[1]);
            }
            if(values.length == 4){
                BorderSetter.setBorderInsets(element, values[2], values[3], values[2], values[3]);
            }
            if(values.length == 8){
                BorderSetter.setBorderInsets(element, values[4], values[5], values[6], values[7]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BR_OPACITY)){
            double[] values = doubleValues.get(StaticAnimations.BR_OPACITY);
            if(values.length == 2){
                BorderSetter.setBorderOpacity(element, values[1]);
            }
            if(values.length == 4){
                BorderSetter.setBorderOpacity(element, values[2], values[3]);
            }
            if(values.length == 8){
                BorderSetter.setBorderOpacity(element, values[4], values[5], values[6], values[7]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BR_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_RADIUS);
            if(values.length == 2){
                BorderSetter.setBorderRadius(element, values[1]);
            }
            if(values.length == 4){
                BorderSetter.setBorderRadius(element, values[2], values[3], values[2], values[3]);
            }
            if(values.length == 8){
                BorderSetter.setBorderRadius(element, values[4], values[5], values[6], values[7]);
            }
        }
        if(stringValues.containsKey(StaticAnimations.BR_TL_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_TL_RADIUS);
            BorderSetter.setTopLeftBorderRadius(element, values[1]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_TR_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_TR_RADIUS);
            BorderSetter.setTopRightBorderRadius(element, values[1]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_BR_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_BR_RADIUS);
            BorderSetter.setBottomRightBorderRadius(element, values[1]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_BL_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_BL_RADIUS);
            BorderSetter.setBottomLeftBorderRadius(element, values[1]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_STYLE)){
            BorderSetter.setBorderStyle(element, stringValues.get(StaticAnimations.BR_STYLE)[1]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_TOP_STYLE)){
            BorderSetter.setTopBorderStyle(element, stringValues.get(StaticAnimations.BR_TOP_STYLE)[1]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_RIGHT_STYLE)){
            BorderSetter.setRightBorderStyle(element, stringValues.get(StaticAnimations.BR_RIGHT_STYLE)[1]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_BOTTOM_STYLE)){
            BorderSetter.setBottomBorderStyle(element, stringValues.get(StaticAnimations.BR_BOTTOM_STYLE)[1]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_LEFT_STYLE)){
            BorderSetter.setLeftBorderStyle(element, stringValues.get(StaticAnimations.BR_LEFT_STYLE)[1]);
        }
        //***********************
        //*  BORDER PROPERTIES  *
        //***********************
    }

    public void reverse(){
        //**********************
        //*  BASIC PROPERTIES  *
        //**********************
        if(doubleValues.containsKey(StaticAnimations.LAYOUT_X)){
            element.setLayoutX(doubleValues.get(StaticAnimations.LAYOUT_X)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.LAYOUT_Y)){
            element.setLayoutY(doubleValues.get(StaticAnimations.LAYOUT_Y)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.WIDTH)){
            element.setPrefWidth(doubleValues.get(StaticAnimations.WIDTH)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.HEIGHT)){
            element.setPrefHeight(doubleValues.get(StaticAnimations.HEIGHT)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.SCALE_X)){
            element.setScaleX(doubleValues.get(StaticAnimations.SCALE_X)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.SCALE_Y)){
            element.setScaleY(doubleValues.get(StaticAnimations.SCALE_Y)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.TRANSLATE_X)){
            element.setTranslateX(doubleValues.get(StaticAnimations.TRANSLATE_X)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.TRANSLATE_Y)){
            element.setTranslateY(doubleValues.get(StaticAnimations.TRANSLATE_Y)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.ROTATE)){
            element.setRotate(doubleValues.get(StaticAnimations.ROTATE)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.PADDING)){
            double[] values = doubleValues.get(StaticAnimations.PADDING);
            if(values.length == 2){
                element.setPadding(new Insets(values[0]));
            }
            if(values.length == 4){
                element.setPadding(new Insets(values[0], values[1], values[0], values[1]));
            }
            if(values.length == 8){
                element.setPadding(new Insets(values[0], values[1], values[2], values[3]));
            }
        }
        if(doubleValues.containsKey(StaticAnimations.MARGIN)){
            double[] values = doubleValues.get(StaticAnimations.MARGIN);
            // Need to implement in another way because Region doesn't have a Margin property
        }
        //**********************
        //*  BASIC PROPERTIES  *
        //**********************
        //***************************
        //*  BACKGROUND PROPERTIES  *
        //***************************
        if(doubleValues.containsKey(StaticAnimations.BG_OPACITY)){
            BackgroundSetter.setBackgroundOpacity(element, doubleValues.get(StaticAnimations.BG_OPACITY)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BG_RADIUS);
            if(values.length == 2){
                BackgroundSetter.setBackgroundRadius(element, values[0]);
            }
            if(values.length == 4){
                BackgroundSetter.setBackgroundRadius(element, values[0], values[1]);
            }
            if(values.length == 8){
                BackgroundSetter.setBackgroundRadius(element, values[0], values[1], values[2], values[3]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BG_TL_RADIUS)){
            BackgroundSetter.setTopLeftBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_TL_RADIUS)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_TR_RADIUS)){
            BackgroundSetter.setTopRightBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_TR_RADIUS)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_BR_RADIUS)){
            BackgroundSetter.setBottomRightBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_BR_RADIUS)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_BL_RADIUS)){
            BackgroundSetter.setBottomLeftBackgroundRadius(element, doubleValues.get(StaticAnimations.BG_BL_RADIUS)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_INSETS)){
            double[] values = doubleValues.get(StaticAnimations.BG_INSETS);
            if(values.length == 2){
                BackgroundSetter.setInsets(element, values[0]);
            }
            if(values.length == 4){
                BackgroundSetter.setInsets(element, values[0], values[1]);
            }
            if(values.length == 8){
                BackgroundSetter.setInsets(element, values[0], values[1], values[2], values[3]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BG_TOP_INSET)){
            BackgroundSetter.setTopInset(element, doubleValues.get(StaticAnimations.BG_TOP_INSET)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_RIGHT_INSET)){
            BackgroundSetter.setRightInset(element, doubleValues.get(StaticAnimations.BG_RIGHT_INSET)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_BOTTOM_INSET)){
            BackgroundSetter.setBottomInset(element, doubleValues.get(StaticAnimations.BG_BOTTOM_INSET)[0]);
        }
        if(doubleValues.containsKey(StaticAnimations.BG_LEFT_INSET)){
            BackgroundSetter.setLeftInset(element, doubleValues.get(StaticAnimations.BG_LEFT_INSET)[0]);
        }
        //***************************
        //*  BACKGROUND PROPERTIES  *
        //***************************
        //***********************
        //*  BORDER PROPERTIES  *
        //***********************
        if(stringValues.containsKey(StaticAnimations.BORDER)){
            String[] values = stringValues.get(StaticAnimations.BORDER);
            if(values.length == 8){
                String color = values[0];
                String style = values[1];
                double radius = Double.parseDouble(values[2]);
                double width = Double.parseDouble(values[3]);
                BorderSetter.setBorder(element, color, style, radius, width);
            }
            if(values.length == 20){
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
                BorderSetter.setBorder(element, color, style,
                        tlRadius, trRadius, brRadius, blRadius,
                        topWidth, rightWidth, bottomWidth, leftWidth);
            }
        }
        if(stringValues.containsKey(StaticAnimations.TOP_BORDER)){
            String[] values = stringValues.get(StaticAnimations.TOP_BORDER);
            BorderSetter.setTopBorder(element, values[0], values[1], Double.parseDouble(values[2]));
        }
        if(stringValues.containsKey(StaticAnimations.RIGHT_BORDER)){
            String[] values = stringValues.get(StaticAnimations.RIGHT_BORDER);
            BorderSetter.setRightBorder(element, values[0], values[1], Double.parseDouble(values[2]));
        }
        if(stringValues.containsKey(StaticAnimations.BOTTOM_BORDER)){
            String[] values = stringValues.get(StaticAnimations.BOTTOM_BORDER);
            BorderSetter.setBottomBorder(element, values[0], values[1], Double.parseDouble(values[2]));
        }
        if(stringValues.containsKey(StaticAnimations.LEFT_BORDER)){
            String[] values = stringValues.get(StaticAnimations.LEFT_BORDER);
            BorderSetter.setLeftBorder(element, values[0], values[1], Double.parseDouble(values[2]));
        }
        if(doubleValues.containsKey(StaticAnimations.BR_WIDTH)){
            double[] values = doubleValues.get(StaticAnimations.BR_WIDTH);
            if(values.length == 2){
                BorderSetter.setBorderWidth(element, values[0]);
            }
            if(values.length == 4){
                BorderSetter.setBorderWidth(element, values[0], values[1], values[0], values[1]);
            }
            if(values.length == 8){
                BorderSetter.setBorderWidth(element, values[0], values[1], values[2], values[3]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BR_INSETS)){
            double[] values = doubleValues.get(StaticAnimations.BR_INSETS);
            if(values.length == 2){
                BorderSetter.setBorderInsets(element, values[0]);
            }
            if(values.length == 4){
                BorderSetter.setBorderInsets(element, values[0], values[1], values[0], values[1]);
            }
            if(values.length == 8){
                BorderSetter.setBorderInsets(element, values[0], values[1], values[2], values[3]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BR_OPACITY)){
            double[] values = doubleValues.get(StaticAnimations.BR_OPACITY);
            if(values.length == 2){
                BorderSetter.setBorderOpacity(element, values[0]);
            }
            if(values.length == 4){
                BorderSetter.setBorderOpacity(element, values[0], values[1]);
            }
            if(values.length == 8){
                BorderSetter.setBorderOpacity(element, values[0], values[1], values[2], values[3]);
            }
        }
        if(doubleValues.containsKey(StaticAnimations.BR_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_RADIUS);
            if(values.length == 2){
                BorderSetter.setBorderRadius(element, values[0]);
            }
            if(values.length == 4){
                BorderSetter.setBorderRadius(element, values[0], values[1], values[0], values[1]);
            }
            if(values.length == 8){
                BorderSetter.setBorderRadius(element, values[0], values[1], values[2], values[3]);
            }
        }
        if(stringValues.containsKey(StaticAnimations.BR_TL_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_TL_RADIUS);
            BorderSetter.setTopLeftBorderRadius(element, values[0]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_TR_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_TR_RADIUS);
            BorderSetter.setTopRightBorderRadius(element, values[0]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_BR_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_BR_RADIUS);
            BorderSetter.setBottomRightBorderRadius(element, values[0]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_BL_RADIUS)){
            double[] values = doubleValues.get(StaticAnimations.BR_BL_RADIUS);
            BorderSetter.setBottomLeftBorderRadius(element, values[0]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_STYLE)){
            BorderSetter.setBorderStyle(element, stringValues.get(StaticAnimations.BR_STYLE)[0]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_TOP_STYLE)){
            BorderSetter.setTopBorderStyle(element, stringValues.get(StaticAnimations.BR_TOP_STYLE)[0]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_RIGHT_STYLE)){
            BorderSetter.setRightBorderStyle(element, stringValues.get(StaticAnimations.BR_RIGHT_STYLE)[0]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_BOTTOM_STYLE)){
            BorderSetter.setBottomBorderStyle(element, stringValues.get(StaticAnimations.BR_BOTTOM_STYLE)[0]);
        }
        if(stringValues.containsKey(StaticAnimations.BR_LEFT_STYLE)){
            BorderSetter.setLeftBorderStyle(element, stringValues.get(StaticAnimations.BR_LEFT_STYLE)[0]);
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
