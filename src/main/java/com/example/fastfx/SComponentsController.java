package com.example.fastfx;

import com.example.fastfx.src.simplecomponents.SPane;
import javafx.fxml.FXML;

public class SComponentsController {

    @FXML
    private SPane mainContainer;

    public SPane getMainContainer(){
        return mainContainer;
    }

    @FXML
    public void initialize() {
        
    }

    private SPane createPane(String color){
        return new SPane()
                .width(200)
                .height(180)
                .bgColor(color);
    }
}























