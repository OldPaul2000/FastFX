package com.example.fastfx.fastfx.methodscssproperties;

// Used for temporary storing javafx style functions
// in order to find them easier later

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

// setInput() method from effects lets you add another effect to the node(to chain the effects).
// Not all available effects are mentioned in the below list
public class StylePropertiesMethods {

    public static void BACKGROUND_COLOR(Node node){

    }


    public static void BACKGROUND_COLOR(Region region){
        CornerRadii radius = new CornerRadii(10, false);
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf("#555555"), radius, null);
        Background background = new Background(backgroundFill);

        region.setBackground(background);
    }

    // Need to test if it doesn't throw error when trying to set padding on elements which
    // don't have padding
    public static void PADDING(Region region){
        Insets insets = new Insets(10,10,10,10);
        region.setPadding(insets);
    }

    // =========== EFFECTS ===========

    public static void MOTION_BLUR(Node node){
        MotionBlur motionBlur = new MotionBlur();
        motionBlur.setAngle(60);
        motionBlur.setRadius(10);


        node.setEffect(motionBlur);
    }

    public static void GAUSSIAN_BLUR(Node node){
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(3);

        node.setEffect(blur);
    }

    public static void BOX_BLUR(Node node){
        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setWidth(1);
        boxBlur.setHeight(10);
        boxBlur.setIterations(1);

        node.setEffect(boxBlur);
    }

    public static void REFLECTION(Node node){
        Reflection reflection = new Reflection();
        reflection.setTopOffset(10);
        reflection.setBottomOpacity(0.3);
        reflection.setTopOpacity(0.8);
        reflection.setFraction(0.2);

        node.setEffect(reflection);
    }

    public static void DROP_SHADOW(Node node){
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.valueOf("#000000"));
        shadow.setHeight(10);
        shadow.setRadius(2);
        shadow.setSpread(3);
        shadow.setOffsetX(10);
        shadow.setOffsetY(10);

        node.setEffect(shadow);
    }

    public static void INNER_SHADOW(Node node){
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.valueOf("#000000"));
        innerShadow.setRadius(10);
        innerShadow.setChoke(10);
        innerShadow.setHeight(10);
        innerShadow.setWidth(10);
        innerShadow.setOffsetX(10);
        innerShadow.setOffsetY(10);

        node.setEffect(innerShadow);
    }
    // =========== EFFECTS ===========
}
