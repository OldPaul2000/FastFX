package com.example.fastfx;

import com.example.fastfx.src.utility.ui.UI;
import com.example.fastfx.src.style.layouts.flex.Flex;
import com.example.fastfx.src.style.layouts.Justify;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.MarginSetter;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class HelloController2 {

    @FXML
    public AnchorPane mainContainer;
    @FXML
    public AnchorPane squareContainer;

    AnchorPane square = null;
    public void initialize(){

        System.out.println(Math.pow(10,10));

        long start = System.currentTimeMillis();
        //=============== EXECUTION TIME ===============



        double layX = 1400;
        double layY = 50;

        Button button1 = new Button("Add element");
        button1.setPrefWidth(100);
        button1.setPrefHeight(30);
        button1.setLayoutY(layY);
        button1.setLayoutX(layX);
        UI.add(mainContainer, button1);


        Button button2 = new Button("Set gap");
        button2.setPrefWidth(100);
        button2.setPrefHeight(30);
        button2.setLayoutY(layY + 50);
        button2.setLayoutX(layX);
        UI.add(mainContainer, button2);

        Button button3 = new Button("Set padding");
        button3.setPrefWidth(100);
        button3.setPrefHeight(30);
        button3.setLayoutY(layY + 100);
        button3.setLayoutX(layX);
        UI.add(mainContainer, button3);

        Button button4 = new Button("Set margin");
        button4.setPrefWidth(100);
        button4.setPrefHeight(30);
        button4.setLayoutY(layY + 150);
        button4.setLayoutX(layX);
        UI.add(mainContainer, button4);
        button4.setDisable(true);

        Button button5 = new Button("Set direction");
        button5.setPrefWidth(100);
        button5.setPrefHeight(30);
        button5.setLayoutY(layY + 200);
        button5.setLayoutX(layX);
        UI.add(mainContainer, button5);

        Button button6 = new Button("Align");
        button6.setPrefWidth(100);
        button6.setPrefHeight(30);
        button6.setLayoutY(layY + 250);
        button6.setLayoutX(layX);
        UI.add(mainContainer, button6);

        Button button7 = new Button("Justify");
        button7.setPrefWidth(100);
        button7.setPrefHeight(30);
        button7.setLayoutY(layY + 300);
        button7.setLayoutX(layX);
        UI.add(mainContainer, button7);

        Button button8 = new Button("Remove element");
        button8.setPrefWidth(100);
        button8.setPrefHeight(30);
        button8.setLayoutY(layY + 350);
        button8.setLayoutX(layX);
        UI.add(mainContainer, button8);

        Button button9 = new Button("Set el height");
        button9.setPrefWidth(100);
        button9.setPrefHeight(30);
        button9.setLayoutY(layY + 400);
        button9.setLayoutX(layX);
        UI.add(mainContainer, button9);

        Spinner<Integer> spinner = new Spinner<>();
        spinner.setPrefWidth(70);
        spinner.setPrefHeight(15);
        spinner.setLayoutX(layX + 20);
        spinner.setLayoutY(layY + 450);
        SpinnerValueFactory<Integer> s = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
        spinner.setValueFactory(s);
        UI.add(mainContainer, spinner);

        Slider widthSlider = new Slider();
        widthSlider.setOrientation(Orientation.VERTICAL);
        widthSlider.setMinHeight(500);
        widthSlider.setLayoutX(layX - 50);
        widthSlider.setLayoutY(layY);
        widthSlider.setMin(300);
        widthSlider.setMax(1000);
        UI.add(mainContainer, widthSlider);

        Slider heightSlider = new Slider();
        heightSlider.setOrientation(Orientation.VERTICAL);
        heightSlider.setMinHeight(500);
        heightSlider.setLayoutX(layX - 100);
        heightSlider.setLayoutY(layY);
        heightSlider.setMin(100);
        heightSlider.setMax(1000);
        UI.add(mainContainer, heightSlider);

        squareContainer.setLayoutX(100);
        squareContainer.setLayoutY(70);
        BackgroundSetter.setBackgroundColor(squareContainer, "#ae5142");

        Flex flex = new Flex(squareContainer);
        flex.setWidth(500);
        flex.setHeight(120);
        flex.setFlexDirection("row");
        flex.alignContent("center");
        flex.justifyContent("space-around");
//        flex.setPadding(10);
//        flex.setGap(40);


        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            square = new AnchorPane();
            squareStyle(square);
//            MarginSetter.setMargin(square, 0, 10, 0, 10);
            flex.addElement(2,square);
        });

        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            flex.setGap(10);
        });

        button3.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            flex.setPadding(10);
        });

        button4.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

        });

        button5.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if(flex.getFlexDirection().equals("row")){
                flex.setFlexDirection("column");
            }
            else{
                flex.setFlexDirection("row");
            }
        });

        button6.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if(flex.getAlignment().equals("start")){
                flex.alignContent("center");
            }
            else if(flex.getAlignment().equals("center")){
                flex.alignContent("end");
            }
            else if(flex.getAlignment().equals("end")){
                flex.alignContent("start");
            }
        });

        button7.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if(flex.getJustify().equals(Justify.START.justify())){
                flex.justifyContent(Justify.CENTER);
            }
            else if(flex.getJustify().equals(Justify.CENTER.justify())){
                flex.justifyContent(Justify.END);
            }
            else if(flex.getJustify().equals(Justify.END.justify())){
                flex.justifyContent(Justify.START);
            }
//            if(flex.getJustify().equals(Justify.SPACE_BETWEEN.justify())){
//                flex.justifyContent(Justify.SPACE_AROUND);
//            }
//            else if(flex.getJustify().equals(Justify.SPACE_AROUND.justify())){
//                flex.justifyContent(Justify.SPACE_EVENLY);
//            }
//            else if(flex.getJustify().equals(Justify.SPACE_EVENLY.justify())){
//                flex.justifyContent(Justify.SPACE_BETWEEN);
//            }
            System.out.println("Justify: " + flex.getJustify());
        });

        button8.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            flex.removeElement("end");
            flex.removeElement(2);
        });

        button9.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            Region r = (Region) UI.getChildByIndex(squareContainer, spinner.getValue());
            if(r != null){
//                r.setPrefWidth(100);
                MarginSetter.setMargin(r, 0,10,0,0);
            }
        });

        widthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            flex.setWidth((Double) newVal);
        });

        heightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            flex.setHeight((Double) newVal);
        });

        //=============== EXECUTION TIME ===============
        long end = System.currentTimeMillis() - start;
        System.out.println("Exec time: " + (double)end);

    }


    private double randomWidth(){
        return Math.max(30, Math.random() * 100);
    }

    private double randomHeight(){
        return Math.max(30, Math.random() * 100);
    }

    private String randomBg(){
        int r = (int)Math.max(10, Math.random() * 100);
        int g = (int)Math.max(10, Math.random() * 100);
        int b = (int)Math.max(10, Math.random() * 100);

        return "#" + r + "" + g + "" + b;
    }

    private void squareStyle(AnchorPane square){
        System.out.println("rand width: " + randomWidth());
        System.out.println("rand height: " + randomHeight());
//        square.setPrefWidth(randomWidth());
//        square.setPrefHeight(randomHeight());
        square.setPrefWidth(100);
        square.setPrefHeight(100);
        System.out.println("rand color: " + randomBg());
        BackgroundSetter.setBackgroundColor(square, randomBg());
    }

}
















