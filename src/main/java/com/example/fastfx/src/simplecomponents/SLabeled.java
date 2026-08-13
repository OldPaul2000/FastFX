package com.example.fastfx.src.simplecomponents;

import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Represents the components which contains text(Label, Button etc.) and
 * provides the methods for styling the text.
 */
public interface SLabeled extends SComponent {
    SLabeled font(String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize);
    SLabeled font(String fontFamily, FontPosture fontPosture, double fontSize);
    SLabeled font(String fontFamily, FontWeight fontWeight, double fontSize);
    SLabeled font(String fontFamily, double fontSize);
    SLabeled fontColor(Color color);
    SLabeled fontColor(String color);
    Color getFontColor();
    String getFontColorHex();
    SLabeled fontFamily(String fontFamily);
    SLabeled fontPosture(FontPosture fontPosture);
    SLabeled fontPosture(String fontPosture);
    String getFontPostureString();
    SLabeled fontWeight(FontWeight fontWeight);
    SLabeled fontSize(double fontSize);
    double getFontSize();
}
