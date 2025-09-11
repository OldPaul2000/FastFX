package com.example.fastfx;

import com.example.fastfx.fastfx.elements.utility.UI;
import com.example.fastfx.fastfx.style.layouts.flex.Flex;
import com.example.fastfx.fastfx.style.layouts.grid.Grid;
import com.example.fastfx.fastfx.style.utility.BackgroundSetter;
import com.example.fastfx.fastfx.style.utility.MarginSetter;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class GridTestController {

    @FXML
    public AnchorPane mainContainer;
    @FXML
    public AnchorPane gridContainer;

    AnchorPane square = null;
    public void initialize(){

//        System.out.println(5 / 4);
//        System.out.println(7 / 4);
//        System.out.println(9 / 4);

        long start = System.currentTimeMillis();
        //=============== EXECUTION TIME ===============

//        System.out.println(((double) 1 / 2));
//        System.out.println(((double) 2 / 2));
//        System.out.println(((double) 3 / 2));
//        System.out.println(((double) 4 / 2));
//        System.out.println(((double) 5 / 2));
//        System.out.println(((double) 6 / 2));


        Grid grid = new Grid(gridContainer);
//        columns = 4;
//        rows = 3;
        Button gridButton1 = new Button("Button 1");
        grid.addElement(gridButton1);
        Button gridButton2 = new Button("Button 2");
        grid.addElement(gridButton2);
        Button gridButton3 = new Button("Button 3");
        grid.addElement(gridButton3);
        Button gridButton4 = new Button("Button 4");
        grid.addElement(gridButton4);
        Button gridButton5 = new Button("Button 5");
        grid.addElement(gridButton5);
        Button gridButton6 = new Button("Button 6");
        grid.addElement(gridButton6);
        Button gridButton7 = new Button("Button 7");
        grid.addElement(gridButton7);
        Button gridButton8 = new Button("Button 8");
        grid.addElement(gridButton8);
        Button gridButton9 = new Button("Button 9");
        grid.addElement(gridButton9);
        Button gridButton10 = new Button("Button 10");
        grid.addElement(gridButton10);
        Button gridButton11 = new Button("Button 11");
        grid.addElement(gridButton11);
        Button gridButton12 = new Button("Button 12");
        grid.addElement(gridButton12);

        System.out.println("=================================");

        System.out.println(grid.getElementRow(gridButton1));
        System.out.println(grid.getElementColumn(gridButton1));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton2));
        System.out.println(grid.getElementColumn(gridButton2));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton3));
        System.out.println(grid.getElementColumn(gridButton3));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton4));
        System.out.println(grid.getElementColumn(gridButton4));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton5));
        System.out.println(grid.getElementColumn(gridButton5));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton6));
        System.out.println(grid.getElementColumn(gridButton6));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton7));
        System.out.println(grid.getElementColumn(gridButton7));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton8));
        System.out.println(grid.getElementColumn(gridButton8));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton9));
        System.out.println(grid.getElementColumn(gridButton9));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton10));
        System.out.println(grid.getElementColumn(gridButton10));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton11));
        System.out.println(grid.getElementColumn(gridButton11));
        System.out.println();
        System.out.println(grid.getElementRow(gridButton12));
        System.out.println(grid.getElementColumn(gridButton12));
        System.out.println();

        UI.switchElementsIndex(gridContainer, 0, 1);

        gridContainer.getChildren().forEach((el) -> {
            System.out.println(el);
        });

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

        Button button3 = new Button("Set row gap");
        button3.setPrefWidth(100);
        button3.setPrefHeight(30);
        button3.setLayoutY(layY + 100);
        button3.setLayoutX(layX);
        UI.add(mainContainer, button3);

        Button button4 = new Button("Set column gap");
        button4.setPrefWidth(100);
        button4.setPrefHeight(30);
        button4.setLayoutY(layY + 150);
        button4.setLayoutX(layX);
        UI.add(mainContainer, button4);

        Button button5 = new Button("Set padding");
        button5.setPrefWidth(100);
        button5.setPrefHeight(30);
        button5.setLayoutY(layY + 200);
        button5.setLayoutX(layX);
        UI.add(mainContainer, button5);

        Button button6 = new Button("Set margin");
        button6.setPrefWidth(100);
        button6.setPrefHeight(30);
        button6.setLayoutY(layY + 250);
        button6.setLayoutX(layX);
        UI.add(mainContainer, button6);
        button6.setDisable(true);

        Button button7 = new Button("Align");
        button7.setPrefWidth(100);
        button7.setPrefHeight(30);
        button7.setLayoutY(layY + 300);
        button7.setLayoutX(layX);
        UI.add(mainContainer, button7);

        Button button8 = new Button("Justify");
        button8.setPrefWidth(100);
        button8.setPrefHeight(30);
        button8.setLayoutY(layY + 350);
        button8.setLayoutX(layX);
        UI.add(mainContainer, button8);

        Button button9 = new Button("Remove element");
        button9.setPrefWidth(100);
        button9.setPrefHeight(30);
        button9.setLayoutY(layY + 400);
        button9.setLayoutX(layX);
        UI.add(mainContainer, button9);

        Button button10 = new Button("Set el height");
        button10.setPrefWidth(100);
        button10.setPrefHeight(30);
        button10.setLayoutY(layY + 450);
        button10.setLayoutX(layX);
        UI.add(mainContainer, button10);

        Spinner<Integer> spinner = new Spinner<>();
        spinner.setPrefWidth(70);
        spinner.setPrefHeight(15);
        spinner.setLayoutX(layX + 20);
        spinner.setLayoutY(layY + 500);
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

        gridContainer.setLayoutX(100);
        gridContainer.setLayoutY(70);
        BackgroundSetter.setBackgroundColor(gridContainer, "#ae5142");

        Flex flex = new Flex(gridContainer);
        flex.setWidth(500);
        flex.setHeight(500);
        flex.setFlexDirection("row");
        flex.alignContent("start");
        flex.justifyContent("start");
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
//            grid.setRowGap(10);
        });

        button4.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            grid.setColumnGap(10);
        });

        button5.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            grid.setPadding(10);
        });

        button6.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            Region r = (Region) UI.getChildByIndex(gridContainer, spinner.getValue());
            if(r != null){
//                r.setPrefWidth(100);
                MarginSetter.setMargin(r, 0,10,0,0);
            }
        });

        button7.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            if(grid.getAlignment().equals(Alignment.START.alignment())){
//                grid.alignItems(Alignment.CENTER);
//            }
//            else if(grid.getAlignment().equals(Alignment.CENTER.alignment())){
//                grid.alignItems(Alignment.END);
//            }
//            else if(grid.getAlignment().equals(Alignment.END.alignment())){
//                grid.alignItems(Alignment.START);
//            }
        });

        button8.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            if(grid.getJustify().equals(Justify.START.justify())){
//                grid.justifyContent(Justify.CENTER);
//            }
//            else if(grid.getJustify().equals(Justify.CENTER.justify())){
//                grid.justifyContent(Justify.END);
//            }
//            else if(grid.getJustify().equals(Justify.END.justify())){
//                grid.justifyContent(Justify.START);
//            }
//            System.out.println("Justify: " + grid.getJustify());
        });

        button9.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//           grid.removeElement();
        });

        button10.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            // Set grid element height
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
