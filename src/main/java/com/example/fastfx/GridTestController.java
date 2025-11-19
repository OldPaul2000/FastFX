package com.example.fastfx;

import com.example.fastfx.src.utility.ui.UI;
import com.example.fastfx.src.style.layouts.Alignment;
import com.example.fastfx.src.style.layouts.Justify;
import com.example.fastfx.src.style.layouts.grid.Grid;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.MarginSetter;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class GridTestController {

    @FXML
    public AnchorPane mainContainer;
    @FXML
    public Pane gridContainer;

    AnchorPane square = null;
    public void initialize(){

        long start = System.currentTimeMillis();
        //=============== EXECUTION TIME ===============

        Grid grid = new Grid(gridContainer);
        grid.width(500);
        grid.height(500);
        grid.templateRows("1fr 1fr 1fr");
        grid.templateColumns("1fr 1fr 1fr");
//        grid.gap(25);
//        grid.padding(25);
        grid.alignItems("center");
        grid.justifyItems("center");
//        System.out.println("Allocated heights: " + grid.getAllocatedHeights());
//        System.out.println("Allocated widths: " + grid.getAllocatedWidths());
        System.out.println("=================================");


        Button gridButton1 = new Button("Button 1");
        gridButton1.setPrefWidth(100);
        gridButton1.setPrefHeight(100);
        grid.add(gridButton1);
//        grid.setElWidthPercent(gridButton1, 50);
//        grid.setElHeightPercent(gridButton1, 50);

        Button gridButton2 = new Button("Button 2");
        gridButton2.setPrefWidth(100);
        gridButton2.setPrefHeight(100);
        grid.add(gridButton2);
//        grid.setElWidthPercent(gridButton2, 50);
//        grid.setElHeightPercent(gridButton2, 50);

        Button gridButton3 = new Button("Button 3");
        gridButton3.setPrefWidth(100);
        gridButton3.setPrefHeight(100);
        grid.add(gridButton3);
//        grid.setElWidthPercent(gridButton3, 90);
//        grid.setElHeightPercent(gridButton3, 90);

        Button gridButton4 = new Button("Button 4");
        gridButton4.setPrefWidth(100);
        gridButton4.setPrefHeight(100);
        grid.add(gridButton4);

        Button gridButton5 = new Button("Button 5");
        gridButton5.setPrefWidth(100);
        gridButton5.setPrefHeight(100);
        grid.add(gridButton5);

        Button gridButton6 = new Button("Button 6");
        gridButton6.setPrefWidth(100);
        gridButton6.setPrefHeight(100);
        grid.add(gridButton6);

        Button gridButton7 = new Button("Button 7");
        gridButton7.setPrefWidth(100);
        gridButton7.setPrefHeight(100);
        grid.add(gridButton7);

        Button gridButton8 = new Button("Button 8");
        gridButton8.setPrefWidth(100);
        gridButton8.setPrefHeight(100);
//        grid.add(gridButton8);

        Button gridButton9 = new Button("Button 9");
        gridButton9.setPrefWidth(100);
        gridButton9.setPrefHeight(100);
//        grid.add(gridButton9);

//        gridButton5.setPrefWidth(200);
//        grid.absY(gridButton9, 10);
//        grid.absX(gridButton9, 90);
//        grid.removeAbsPositioning(gridButton9);

//        grid.switchElements(gridButton5, gridButton7);


//        UI.switchElementsIndex(gridContainer, 1, 3);

//        grid.setElWidthPercent(gridButton1, 50);
//        grid.setElHeightPercent(gridButton1, 50);
//        grid.setElWidthPercent(gridButton2, 50);
//        grid.setElHeightPercent(gridButton2, 50);
//        grid.setElWidthPercent(gridButton3, 50);
//        grid.setElHeightPercent(gridButton3, 50);
//        grid.setElWidthPercent(gridButton4, 50);
//        grid.setElHeightPercent(gridButton4, 50);
//        grid.setElWidthPercent(gridButton5, 50);
//        grid.setElHeightPercent(gridButton5, 50);
//        grid.setElWidthPercent(gridButton6, 50);
//        grid.setElHeightPercent(gridButton6, 50);
//        grid.setElWidthPercent(gridButton7, 50);
//        grid.setElHeightPercent(gridButton7, 50);
//        grid.setElWidthPercent(gridButton8, 50);
//        grid.setElHeightPercent(gridButton8, 50);
//        grid.setElWidthPercent(gridButton9, 50);
//        grid.setElHeightPercent(gridButton9, 50);

//        grid.setElWidthPercent(gridButton1, 100);
//        grid.setElHeightPercent(gridButton1, 100);
//        grid.setElWidthPercent(gridButton2, 100);
//        grid.setElHeightPercent(gridButton2, 100);
//        grid.setElWidthPercent(gridButton3, 100);
//        grid.setElHeightPercent(gridButton3, 100);
//        grid.setElWidthPercent(gridButton4, 100);
//        grid.setElHeightPercent(gridButton4, 100);
//        grid.setElWidthPercent(gridButton5, 100);
//        grid.setElHeightPercent(gridButton5, 100);
//        grid.setElWidthPercent(gridButton6, 100);
//        grid.setElHeightPercent(gridButton6, 100);
//        grid.setElWidthPercent(gridButton7, 100);
//        grid.setElHeightPercent(gridButton7, 100);
//        grid.setElWidthPercent(gridButton8, 100);
//        grid.setElHeightPercent(gridButton8, 100);
//        grid.setElWidthPercent(gridButton9, 100);
//        grid.setElHeightPercent(gridButton9, 100);



//        Button gridButton10 = new Button("Button 10");
//        gridButton10.setPrefWidth(70);
//        gridButton10.setPrefHeight(30);
//        grid.add(gridButton10);
//
//        Button gridButton11 = new Button("Button 11");
//        gridButton11.setPrefWidth(70);
//        gridButton11.setPrefHeight(30);
//        grid.add(gridButton11);
//
//        Button gridButton12 = new Button("Button 12");
//        gridButton12.setPrefWidth(70);
//        gridButton12.setPrefHeight(30);
//        grid.add(gridButton12);
//
//        Button gridButton13 = new Button("Button 13");
//        gridButton13.setPrefWidth(70);
//        gridButton13.setPrefHeight(30);
//        grid.add(gridButton13);
//
//        Button gridButton14 = new Button("Button 14");
//        gridButton14.setPrefWidth(70);
//        gridButton14.setPrefHeight(30);
//        grid.add(gridButton14);
//
//        Button gridButton15 = new Button("Button 15");
//        gridButton15.setPrefWidth(70);
//        gridButton15.setPrefHeight(30);
//        grid.add(gridButton15);
//
//        Button gridButton16 = new Button("Button 16");
//        gridButton16.setPrefWidth(70);
//        gridButton16.setPrefHeight(30);
//        grid.add(gridButton16);

// ======================== CONTROL TESTING PANEL ================================================
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
        widthSlider.setMin(0);
        widthSlider.setMax(gridContainer.getPrefWidth() * 2);
        widthSlider.setValue(gridContainer.getPrefWidth());
        UI.add(mainContainer, widthSlider);

        Slider heightSlider = new Slider();
        heightSlider.setOrientation(Orientation.VERTICAL);
        heightSlider.setMinHeight(500);
        heightSlider.setLayoutX(layX - 100);
        heightSlider.setLayoutY(layY);
        heightSlider.setMin(0);
        heightSlider.setMax(gridContainer.getPrefHeight() * 2);
        heightSlider.setValue(gridContainer.getPrefHeight());
        UI.add(mainContainer, heightSlider);

        gridContainer.setLayoutX(100);
        gridContainer.setLayoutY(70);
        BackgroundSetter.setBackgroundColor(gridContainer, "#ae5142");


        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            square = new AnchorPane();
            squareStyle(square);
//            MarginSetter.setMargin(square, 0, 10, 0, 10);
            grid.add(square);
        });

        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            grid.gap(10);
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
            if(grid.getItemsAlignment().equals(Alignment.START.alignment())){
                grid.alignItems(Alignment.CENTER.alignment());
            }
            else if(grid.getItemsAlignment().equals(Alignment.CENTER.alignment())){
                grid.alignItems(Alignment.END.alignment());
            }
            else if(grid.getItemsAlignment().equals(Alignment.END.alignment())){
                grid.alignItems(Alignment.START.alignment());
            }
        });

        button8.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if(grid.getItemsJustify().equals(Justify.START.justify())){
                grid.justifyItems(Justify.CENTER.justify());
            }
            else if(grid.getItemsJustify().equals(Justify.CENTER.justify())){
                grid.justifyItems(Justify.END.justify());
            }
            else if(grid.getItemsJustify().equals(Justify.END.justify())){
                grid.justifyItems(Justify.START.justify());
            }
//            System.out.println("Justify: " + grid.getJustify());
        });

        button9.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//           grid.removeElement();
        });

        button10.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            // Set grid element height
        });

        widthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            grid.width((Double) newVal);
        });

        heightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            grid.height((Double) newVal);
        });

        //=============== EXECUTION TIME ===============
        long end = System.currentTimeMillis() - start;
        System.out.println("Exec time: " + (double)end);


        // ======================== CONTROL TESTING PANEL ================================================

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
