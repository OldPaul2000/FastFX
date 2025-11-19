package com.example.fastfx;

import com.example.fastfx.src.style.FStyle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class HelloController {

    @FXML
    public AnchorPane mainContainer;
    @FXML
    public Button buttonInMain1;

    @FXML
    public AnchorPane buttonsContainer;
    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;
    @FXML
    public Button button5;
    @FXML
    public Button button6;
    @FXML
    public Button button7;
    @FXML
    public Button button8;
    @FXML
    public TextField textField1;

    @FXML
    public AnchorPane checkBoxesContainer;
    @FXML
    public CheckBox checkBox1;
    @FXML
    public CheckBox checkBox2;
    @FXML
    public CheckBox checkBox3;
    @FXML
    public CheckBox checkBox4;

    @FXML
    public AnchorPane comboBoxesContainer;
    @FXML
    public ComboBox<String> comboBox1;
    @FXML
    public ComboBox<String> comboBox2;
    @FXML
    public ComboBox<String> comboBox3;
    @FXML
    public ComboBox<String> comboBox4;

    @FXML
    public AnchorPane slidersContainer;
    @FXML
    public Slider slider1;
    @FXML
    public Slider slider2;
    @FXML
    public Slider slider3;
    @FXML
    public Slider slider4;

    @FXML
    public AnchorPane radioButtonsContainer;
    @FXML
    public RadioButton radio1;
    @FXML
    public RadioButton radio2;
    @FXML
    public RadioButton radio3;
    @FXML
    public RadioButton radio4;

    public void initialize(){

        FStyle fStyle = new FStyle(mainContainer);

        long start = System.currentTimeMillis();





        long end = System.currentTimeMillis() - start;
        System.out.println("Exec time: " + (double)end);

    }

    private static void testCase1(FStyle fStyle, Button button1){
        String style = "bc-c:red; h:120px; w:60px; " +   // Index 0 -> 2
                "Button{bc-c:green}; " +                  // Index 3 -> 4
                ".child{bc-c:#214fea; w:200px; h:140px}; " + // Index 5 -> 8
                "::pseudo-class{w:130px; bc-c:yellow; pd:10%; h:200px};" + // Index 9 -> 13
                ":pseudo-element2{br-r:7px; h:23.4px; w:45px; pd:none};"; // Index 14 -> 18


        System.out.println("Set style(1)");
        fStyle.setStyle(button1, style); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Remove property(2)");
        fStyle.removeProperty(button1, "h"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(3)");
        fStyle.addStyle(button1, "h:100px"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Update style(4)");
        fStyle.updateStyle(button1, "h:300.5px"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Update style(5)");
        fStyle.updateStyle(button1, "Button{bc-c:#123456}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(6)");
        fStyle.addStyle(button1, "Button{h:1.2rem; w:3rem}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Update style(7)");
        fStyle.updateStyle(button1, "Button{h: 1.4rem}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Update style(8)");
        fStyle.updateStyle(button1, "::pseudo-class{w:12rem; bc-c:#f23a22}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(9)");
        fStyle.addStyle(button1, "Label{f-w:400; f-s:1.4rem}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Update style(10)");
        fStyle.updateStyle(button1, "Label{f-w:500}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Update style(11)");
        fStyle.updateStyle(button1, "w:9.4rem;.child{w:220px}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Remove property(12)");
        fStyle.removeProperty(button1, "Button; Label"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Remove property(13)");
        fStyle.removeProperty(button1, "h;::pseudo-class{w;pd};.child{bc-c;w;h};:pseudo-element2{br-r;w}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Remove property(14)");
        fStyle.removeProperty(button1, ":pseudo-element2{pd;h};::pseudo-class"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(15)");
        fStyle.addStyle(button1, "br-r: 3px 3px 3px 3px; f-s: 0.8rem"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Remove property(16)");
        fStyle.removeProperty(button1, "f-s; w; bc-c; br-r"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(17)");
        fStyle.addStyle(button1, "::click{bc-c:green; br-r:0.8rem; f-w:700}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Remove style(18)");
        fStyle.removeStyle(button1); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Set style(19)");
        fStyle.setStyle(button1, "bc-c:red; ty:10px; tx:10px"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(20)");
        fStyle.addStyle(button1, "w:7rem; h:3rem; f-s:1.2rem; f-w:500"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Remove style(21)");
        fStyle.removeProperty(button1, "tx;ty;w; h; f-s; f-w; bc-c"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(22)");
        fStyle.addStyle(button1, "bc-c:green; ty:22%; tx:10px; h:25%; f-s:2.2%; f-w:600; w:50%;"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(23)");
        fStyle.addStyle(button1, "::hover{ty:2rem; w:60%; h:20%; bc-c: red}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(24)");
        fStyle.addStyle(button1, ":label{f-c:black}"); //Works
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Add style(25)");
        fStyle.addStyle(button1, ":label{vo:1; f-s:1.2rem; f-w:550}"); //Works

    }
}
















