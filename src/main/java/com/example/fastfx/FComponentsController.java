package com.example.fastfx;

import com.example.fastfx.src.fastfxcomponents.FPane;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class FComponentsController {

    @FXML
    private FPane mainContainer;

    public FPane getMainContainer(){
        return mainContainer;
    }

    @FXML
    public void initialize() {
        FPane pane = new FPane();
        pane
                .width(100)
                .height(100)
                .x(200)
                .y(100)
                .bgColor(Color.RED);

        mainContainer.add(pane);
    }

    private FPane createPane(String color){
        return new FPane()
                .width(200)
                .height(180)
                .bgColor(color);
    }
}























