package com.example.fastfx.src.util.style;

import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Utility class whose purpose is to simplify setting font and
 * its properties separately from the others.
 * e.g.: setFontFamily which will change only the font family
 * but will keep the previous set properties the same
 */
public class FontUtil {

    // For Labeled elements
    public static void setFont (Labeled element, String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        if (element != null) {
            element.setFont(Font.font(fontFamily, fontWeight, fontPosture, fontSize));
        }
    }

    public static void setFont (Labeled element, String fontFamily, FontPosture fontPosture, double fontSize) {
        setFont(element, fontFamily, FontWeight.NORMAL, fontPosture, fontSize);
    }

    public static void setFont (Labeled element, String fontFamily, FontWeight fontWeight, double fontSize) {
        setFont(element, fontFamily, fontWeight, FontPosture.REGULAR, fontSize);
    }

    public static void setFont (Labeled element, String fontFamily, double fontSize) {
        setFont(element, fontFamily, FontWeight.NORMAL, FontPosture.REGULAR, fontSize);
    }

    public static void setFontSize (Labeled element, double fontSize) {
        if (element != null) {
            Font font = element.getFont();
            String fontFamily = font.getFamily();
            String[] fontStyle = font.getStyle().split(" ");
            FontWeight fontWeight = FontWeight.NORMAL;
            FontPosture fontPosture = FontPosture.REGULAR;
            if (fontStyle.length == 2) {
                fontWeight = FontWeight.findByName(fontStyle[0]);
                fontPosture = FontPosture.findByName(fontStyle[1]);
            }
            else {
                if (isFontWeight(fontStyle[0])) {
                    fontWeight = FontWeight.findByName(fontStyle[0]);
                }
                else {
                    fontPosture = FontPosture.findByName(fontStyle[0]);
                }
            }
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontColor (Labeled element, Color color) {
        if (element != null) {
            element.setTextFill(color);
        }
    }

    public static void setFontColor (Labeled element, String color) {
        if (element != null) {
            element.setTextFill(Color.valueOf(color));
        }
    }

    public static Color getFontColor (Labeled element) {
        if (element != null) {
            element.getTextFill();
        }
        return null;
    }

    public static String getFontColorHex (Labeled element) {
        if (element != null) {
            return element.getTextFill().toString();
        }
        return null;
    }

    public static void setFontFamily (Labeled element, String fontFamily) {
        if (element != null) {
            Font font = element.getFont();
            String[] fontStyle = font.getStyle().split(" ");
            FontWeight fontWeight = FontWeight.NORMAL;
            FontPosture fontPosture = FontPosture.REGULAR;
            if (fontStyle.length == 2) {
                fontWeight = FontWeight.findByName(fontStyle[0]);
                fontPosture = FontPosture.findByName(fontStyle[1]);
            }
            else {
                if (isFontWeight(fontStyle[0])) {
                    fontWeight = FontWeight.findByName(fontStyle[0]);
                }
                else {
                    fontPosture = FontPosture.findByName(fontStyle[0]);
                }
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontWeight (Labeled element, FontWeight fontWeight) {
        if (element != null) {
            Font font = element.getFont();
            String fontFamily = font.getFamily();
            String[] fontStyle = font.getStyle().split(" ");
            FontPosture fontPosture = FontPosture.REGULAR;
            if (fontStyle.length == 1) {
                if (isFontPosture(fontStyle[0])) {
                    fontPosture = FontPosture.findByName(fontStyle[0]);
                }
            }
            else {
                fontPosture = FontPosture.findByName(fontStyle[1]);
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontPosture (Labeled element, FontPosture fontPosture) {
        if (element != null) {
            Font font = element.getFont();
            String fontFamily = font.getFamily();
            String[] fontStyle = font.getStyle().split(" ");
            FontWeight fontWeight = FontWeight.NORMAL;
            if (fontStyle.length == 1) {
                if (isFontWeight(fontStyle[0])) {
                    fontWeight = FontWeight.findByName(fontStyle[0]);
                }
            }
            else {
                fontWeight = FontWeight.findByName(fontStyle[0]);
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontPosture (Labeled element, String fontPosture) {
        setFontPosture(element, FontPosture.findByName(fontPosture));
    }

    private static boolean isFontWeight (String fontWeight) {
        if (fontWeight.equals("Regular")) {
            return false;
        }
        return FontWeight.findByName(fontWeight) != null;
    }

    private static boolean isFontPosture (String fontPosture) {
        if (fontPosture.equals("Normal")) {
            return false;
        }
        return FontPosture.findByName(fontPosture) != null;
    }

    // For TextInputControl elements
    public static void setFont (TextInputControl element, String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        if (element != null) {
            element.setFont(Font.font(fontFamily, fontWeight, fontPosture, fontSize));
        }
    }

    public static void setFont (TextInputControl element, String fontFamily, FontPosture fontPosture, double fontSize) {
        setFont(element, fontFamily, FontWeight.NORMAL, fontPosture, fontSize);
    }

    public static void setFont (TextInputControl element, String fontFamily, FontWeight fontWeight, double fontSize) {
        setFont(element, fontFamily, fontWeight, FontPosture.REGULAR, fontSize);
    }

    public static void setFont (TextInputControl element, String fontFamily, double fontSize) {
        setFont(element, fontFamily, FontWeight.NORMAL, FontPosture.REGULAR, fontSize);
    }

    public static void setFontSize (TextInputControl element, double fontSize) {
        if (element != null) {
            Font font = element.getFont();
            String fontFamily = font.getFamily();
            String[] fontStyle = font.getStyle().split(" ");
            FontWeight fontWeight = FontWeight.NORMAL;
            FontPosture fontPosture = FontPosture.REGULAR;
            if (fontStyle.length == 2) {
                fontWeight = FontWeight.findByName(fontStyle[0]);
                fontPosture = FontPosture.findByName(fontStyle[1]);
            }
            else {
                if (isFontWeight(fontStyle[0])) {
                    fontWeight = FontWeight.findByName(fontStyle[0]);
                }
                else {
                    fontPosture = FontPosture.findByName(fontStyle[0]);
                }
            }
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }
    //
    //    public static Color getFontColor(TextInputControl element){
    //        if(element != null){
    //            element;
    //        }
    //        return null;
    //    }
    //
    //    public static String getFontColorHex(TextInputControl element){
    //        if(element != null){
    //            return element.getTextFill().toString();
    //        }
    //        return null;
    //    }

    public static void setFontFamily (TextInputControl element, String fontFamily) {
        if (element != null) {
            Font font = element.getFont();
            String[] fontStyle = font.getStyle().split(" ");
            FontWeight fontWeight = FontWeight.NORMAL;
            FontPosture fontPosture = FontPosture.REGULAR;
            if (fontStyle.length == 2) {
                fontWeight = FontWeight.findByName(fontStyle[0]);
                fontPosture = FontPosture.findByName(fontStyle[1]);
            }
            else {
                if (isFontWeight(fontStyle[0])) {
                    fontWeight = FontWeight.findByName(fontStyle[0]);
                }
                else {
                    fontPosture = FontPosture.findByName(fontStyle[0]);
                }
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontWeight (TextInputControl element, FontWeight fontWeight) {
        if (element != null) {
            Font font = element.getFont();
            String fontFamily = font.getFamily();
            String[] fontStyle = font.getStyle().split(" ");
            FontPosture fontPosture = FontPosture.REGULAR;
            if (fontStyle.length == 1) {
                if (isFontPosture(fontStyle[0])) {
                    fontPosture = FontPosture.findByName(fontStyle[0]);
                }
            }
            else {
                fontPosture = FontPosture.findByName(fontStyle[1]);
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontPosture (TextInputControl element, FontPosture fontPosture) {
        if (element != null) {
            Font font = element.getFont();
            String fontFamily = font.getFamily();
            String[] fontStyle = font.getStyle().split(" ");
            FontWeight fontWeight = FontWeight.NORMAL;
            if (fontStyle.length == 1) {
                if (isFontWeight(fontStyle[0])) {
                    fontWeight = FontWeight.findByName(fontStyle[0]);
                }
            }
            else {
                fontWeight = FontWeight.findByName(fontStyle[0]);
            }
            double fontSize = font.getSize();
            setFont(element, fontFamily, fontWeight, fontPosture, fontSize);
        }
    }

    public static void setFontPosture (TextInputControl element, String fontPosture) {
        setFontPosture(element, FontPosture.findByName(fontPosture));
    }
}
