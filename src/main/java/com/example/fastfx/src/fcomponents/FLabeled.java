package com.example.fastfx.src.fcomponents;

import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public interface FLabeled extends FComponent {
    void setFont(String fontFamily, FontPosture fontPosture, FontWeight fontWeight, double fontSize);
    void setFont(String fontFamily, FontPosture fontPosture, double fontSize);
    void setFont(String fontFamily, FontWeight fontWeight, double fontSize);
    void setFont(String fontFamily, double fontSize);
    void setStrFont(String font);
    String getStrFont();
    void setFontColor(Color color);
    void setFontColor(String color);
    void setSFontColor(String color);
    String getSFontColor();
    void setFontFamily(String fontFamily);
    void setFontPosture(FontPosture fontPosture);
    void setSFontPosture(String fontPosture);
    String getSFontPosture();
    void setFontWeight(FontWeight fontWeight);
    void setSFontWeight(String fontWeight);
    String getSFontWeight();
    void setFontSize(double fontSize);
    void setSFontSize(String fontSize);
    String getSFontSize();
}
