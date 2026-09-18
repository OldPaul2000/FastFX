package com.example.fastfx.src.fastfxcomponents;

import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * Represents the components which contains text(Label, Button etc.) and
 * provides the methods for styling the text.
 */
public interface FLabeled extends FComponent {
    FLabeled font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize);

    FLabeled font (String fontFamily, FontPosture fontPosture, double fontSize);

    FLabeled font (String fontFamily, FontWeight fontWeight, double fontSize);

    FLabeled font (String fontFamily, double fontSize);

    FLabeled fontColor (Color color);

    FLabeled fontColor (String color);

    Color getFontColor ();

    String getFontColorHex ();

    FLabeled fontFamily (String fontFamily);

    FLabeled fontPosture (FontPosture fontPosture);

    FLabeled fontPosture (String fontPosture);

    String getFontPostureString ();

    FLabeled fontWeight (FontWeight fontWeight);

    FLabeled fontSize (double fontSize);

    double getFontSize ();

    FLabeled textAlignment(TextAlignment textAlignment);
}
