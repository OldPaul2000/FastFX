package com.example.fastfx;

import com.example.fastfx.fastfx.elements.utility.UI;
import com.example.fastfx.fastfx.style.utility.BackgroundSetter;
import com.example.fastfx.fastfx.userinteraction.ElementMover;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class AnimationsController {

    @FXML
    private Pane mainContainer;

    public void initialize() {
        BackgroundSetter.setBackgroundColor(mainContainer, "#2c4773");

        Pane pane = new Pane();
        pane.setPrefWidth(200);
        pane.setPrefHeight(200);
        pane.setLayoutX(50);
        pane.setLayoutY(50);
        BackgroundSetter.setBackgroundColor(pane, "#333333");
        UI.add(mainContainer, pane);

        Pane movableElement = new Pane();
        movableElement.setPrefWidth(50);
        movableElement.setPrefHeight(50);
        movableElement.setLayoutX(20);
        movableElement.setLayoutY(20);
        BackgroundSetter.setBackgroundColor(movableElement, "#bbbbbb");
//        BorderSetter.setBorderColor(movableElement, "transparent");
        UI.add(pane, movableElement);

        Pane windowBar = new Pane();
        windowBar.prefWidthProperty().bind(movableElement.prefWidthProperty());
        windowBar.setPrefHeight(20);
        BackgroundSetter.setBackgroundColor(windowBar, "#999999");
        UI.add(movableElement, windowBar);

        Pane movableElement2 = new Pane();
        movableElement2.setPrefWidth(50);
        movableElement2.setPrefHeight(50);
        movableElement2.setLayoutX(100);
        movableElement2.setLayoutY(20);
        BackgroundSetter.setBackgroundColor(movableElement2, "green");
//        UI.add(pane, movableElement2);

//        ElementResizer resizer = new ElementResizer(pane);
//        resizer.printStatus();
//        resizer.addElement(movableElement, ResizeMode.DRAG_EDGES);

        ElementMover mover = new ElementMover(pane, movableElement);
        mover.addMouseButton(MouseButton.PRIMARY);
        mover.addPermittedChild(windowBar);
        mover.initialize();

    }
}
