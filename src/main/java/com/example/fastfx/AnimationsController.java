package com.example.fastfx;

import com.example.fastfx.fastfx.elements.utility.UI;
import com.example.fastfx.fastfx.style.animations.FAnimation;
import com.example.fastfx.fastfx.style.animations.FAnimations;
import com.example.fastfx.fastfx.style.utility.BackgroundSetter;
import com.example.fastfx.fastfx.style.utility.BorderSetter;
import com.example.fastfx.fastfx.style.utility.FontSetter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnimationsController {

    @FXML
    private Pane mainContainer;

    public void initialize() {
        BackgroundSetter.setBackgroundColor(mainContainer, "#2c4773");

        Button button = new Button("Button1");
        button.setPrefWidth(100);
        button.setPrefHeight(30);
        button.setLayoutX(300);
        button.setLayoutY(50);
        UI.add(mainContainer, button);

        Button button2 = new Button("Button2");
        button2.setPrefWidth(100);
        button2.setPrefHeight(30);
        button2.setLayoutX(300);
        button2.setLayoutY(100);
        UI.add(mainContainer, button2);

        Button animate1 = new Button("Animate 1");
        animate1.setPrefWidth(100);
        animate1.setPrefHeight(30);
        animate1.setLayoutX(450);
        animate1.setLayoutY(100);
        UI.add(mainContainer, animate1);
        
        Button animate2 = new Button("Animate 2");
        animate2.setPrefWidth(100);
        animate2.setPrefHeight(30);
        animate2.setLayoutX(450);
        animate2.setLayoutY(200);
        UI.add(mainContainer, animate2);
        
        Button animate3 = new Button("Animate 3");
        animate3.setPrefWidth(100);
        animate3.setPrefHeight(30);
        animate3.setLayoutX(450);
        animate3.setLayoutY(250);
        UI.add(mainContainer, animate3);
        
        Button animate4 = new Button("Animate 4");
        animate4.setPrefWidth(100);
        animate4.setPrefHeight(30);
        animate4.setLayoutX(450);
        animate4.setLayoutY(300);
        UI.add(mainContainer, animate4);
        
        Button animate5 = new Button("Animate 5");
        animate5.setPrefWidth(100);
        animate5.setPrefHeight(30);
        animate5.setLayoutX(450);
        animate5.setLayoutY(350);
        UI.add(mainContainer, animate5);
        
        Button animate6 = new Button("Animate 6");
        animate6.setPrefWidth(100);
        animate6.setPrefHeight(30);
        animate6.setLayoutX(450);
        animate6.setLayoutY(400);
        UI.add(mainContainer, animate6);

        Button animate7 = new Button("Animate 7");
        animate7.setPrefWidth(100);
        animate7.setPrefHeight(30);
        animate7.setLayoutX(450);
        animate7.setLayoutY(450);
        UI.add(mainContainer, animate7);

        Label label = new Label("Label");
        FontSetter.setFontSize(label, 30);
        FontSetter.setFontColor(label, Color.BISQUE);
        label.setPrefWidth(100);
        label.setPrefHeight(30);
        label.setLayoutX(300);
        label.setLayoutY(250);
        UI.add(mainContainer, label);

        BorderSetter.setBorder(animate1, "#787878", "solid", 0, 2);
        BackgroundSetter.setBackgroundColor(animate1, "#4f4f4f");
        BackgroundSetter.setInsets(animate1, 1.5);
        FontSetter.setFontColor(animate1, "#adadad");

        FAnimation a1 = new FAnimation(animate1);
        a1.add(FAnimations.BR_COLOR, "#787878", Duration.ZERO);
        a1.add(FAnimations.BR_COLOR, "#999999", Duration.millis(500));


        animate1.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
            a1.play();
        });

        animate1.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
            a1.reverse();
        });

    }
}
