package com.example.fastfx;

import com.example.fastfx.fastfx.elements.utility.UI;
import com.example.fastfx.fastfx.style.utility.BackgroundSetter;
import com.example.fastfx.fastfx.style.utility.FontSetter;
import com.example.fastfx.fastfx.userinteraction.ElementMover3;
import com.example.fastfx.fastfx.userinteraction.ElementResizer;
import com.example.fastfx.fastfx.userinteraction.ResizeMode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class AnimationsController {

    @FXML
    private Pane mainContainer;

    public void initialize() {
        BackgroundSetter.setBackgroundColor(mainContainer, "#2c4773");

        Pane pane = new Pane();
        pane.setPrefWidth(400);
        pane.setPrefHeight(300);
        pane.setLayoutX(50);
        pane.setLayoutY(50);
        BackgroundSetter.setBackgroundColor(pane, "#333333");
        UI.add(mainContainer, pane);

        Pane movableElement = new Pane();
        movableElement.setId("Movable el 1");
        movableElement.setPrefWidth(50);
        movableElement.setPrefHeight(50);
        movableElement.setLayoutX(20);
        movableElement.setLayoutY(20);
        BackgroundSetter.setBackgroundColor(movableElement, "#bbbbbb");
//        BorderSetter.setBorderColor(movableElement, "transparent");
        UI.add(pane, movableElement);

        Pane windowBar = new Pane();
        windowBar.setId("Window bar");
        windowBar.prefWidthProperty().bind(movableElement.prefWidthProperty());
        windowBar.setPrefHeight(20);
        BackgroundSetter.setBackgroundColor(windowBar, "#999999");
        UI.add(movableElement, windowBar);

        Label barTitle = new Label("Window");
        FontSetter.setFont(barTitle, "black", 10);
        barTitle.setLayoutY(3);
        barTitle.setLayoutX(5);
        UI.add(windowBar, barTitle);

        Pane movableElement2 = new Pane();
        movableElement2.setPrefWidth(50);
        movableElement2.setPrefHeight(50);
        movableElement2.setLayoutX(80);
        movableElement2.setLayoutY(20);
        BackgroundSetter.setBackgroundColor(movableElement2, "green");
        UI.add(pane, movableElement2);

        Pane windowBar2 = new Pane();
        windowBar2.setId("Window bar2");
        windowBar2.prefWidthProperty().bind(movableElement2.prefWidthProperty());
        windowBar2.setPrefHeight(20);
        BackgroundSetter.setBackgroundColor(windowBar2, "#999999");
        UI.add(movableElement2, windowBar2);

        ElementMover3 mover = new ElementMover3(pane);
        mover.addElement(movableElement);
        mover.permitAllChildren(movableElement);
        mover.setEdgeLimits(movableElement, 7);
        mover.addElement(movableElement2);
        mover.permitAllChildren(movableElement2);
        mover.setEdgeLimits(movableElement2, 7);


        // Fix LabeledText cast error for ElementResizer on line 320
        ElementResizer resizer = new ElementResizer(pane);
        resizer.addElement(movableElement, ResizeMode.DRAG_EDGES);
        resizer.addElement(movableElement2, ResizeMode.DRAG_EDGES);



    }
}
