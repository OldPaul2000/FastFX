package com.example.fastfx.fastfx.style.utility;

import javafx.scene.control.Labeled;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Utility class whose purpose is to simplify setting font and
 * its properties separately from the others.
 * e.g.: setFontFamily which will change only the font family
 * but will keep the previous set properties the same
 */
public class FontSetter {

    public static void setFont(Labeled element, String fontFamily, FontWeight fontWeight,
                               FontPosture fontPosture, double fontSize){
        if(element != null){
            element.setFont(Font.font(fontFamily, fontWeight, fontPosture, fontSize));
        }
    }

    public static void setFont(Labeled element, String fontFamily, FontPosture fontPosture, double fontSize){
        setFont(element, fontFamily, FontWeight.NORMAL, fontPosture, fontSize);
    }

    public static void setFont(Labeled element, String fontFamily, FontWeight fontWeight, double fontSize){
        setFont(element, fontFamily, fontWeight, FontPosture.REGULAR, fontSize);
    }

    public static void setFont(Labeled element, String fontFamily, double fontSize){
        setFont(element, fontFamily, FontWeight.NORMAL, FontPosture.REGULAR, fontSize);
    }

    public static void setFontFamily(Labeled element, String fontFamily){
        if(element != null){
            Font font = element.getFont();
            String[] fontStyle = font.getStyle().split(" ");
            FontWeight fontWeight = FontWeight.NORMAL;
            FontPosture fontPosture = FontPosture.REGULAR;
            if(fontStyle.length == 2){
                fontWeight = FontWeight.findByName(fontStyle[0]);
                fontPosture = FontPosture.findByName(fontStyle[1]);
            }
            else {
                if (isFontWeight(fontStyle[0])) {
                    fontWeight = FontWeight.findByName(fontStyle[0]);
                } else {
                    fontPosture = FontPosture.findByName(fontStyle[0]);
                }
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontWeight(Labeled element, FontWeight fontWeight){
        if(element != null){
            Font font = element.getFont();
            String fontFamily = font.getFamily();
            String[] fontStyle = font.getStyle().split(" ");
            FontPosture fontPosture = FontPosture.REGULAR;
            if(fontStyle.length == 1){
                if (isFontPosture(fontStyle[0])) {
                    fontPosture = FontPosture.findByName(fontStyle[0]);
                }
            }
            else{
                fontPosture = FontPosture.findByName(fontStyle[1]);
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontPosture(Labeled element, FontPosture fontPosture){
        if(element != null){
            Font font = element.getFont();
            String fontFamily = font.getFamily();
            String[] fontStyle = font.getStyle().split(" ");
            FontWeight fontWeight = FontWeight.NORMAL;
            if(fontStyle.length == 1){
                if (isFontWeight(fontStyle[0])) {
                    fontWeight = FontWeight.findByName(fontStyle[0]);
                }
            }
            else{
                fontWeight = FontWeight.findByName(fontStyle[0]);
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    private static boolean isFontWeight(String fontWeight){
        if(fontWeight.equals("Regular")){
            return false;
        }
        return FontWeight.findByName(fontWeight) != null;
    }

    private static boolean isFontPosture(String fontPosture){
        if(fontPosture.equals("Normal")){
            return false;
        }
        return FontPosture.findByName(fontPosture) != null;
    }

}
