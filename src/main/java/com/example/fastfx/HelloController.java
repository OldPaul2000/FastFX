package com.example.fastfx;

import com.example.fastfx.fastfx.style.FStyle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

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


        String style = "bc-c:red; h:120px; w:60px; " +   // Index 0 -> 2
                "Button{bc-c:green}; " +                  // Index 3 -> 4
                ".child{bc-c:#214fea; w:200px; h:140px}; " + // Index 5 -> 8
                "::pseudo-class{w:130px; bc-c:yellow; pd:10%; h:200px};" + // Index 9 -> 13
                ":pseudo-element2{br-r:7px; h:23.4px; w:45px; pd:none};"; // Index 14 -> 18

        List<String> removeStyles = new ArrayList<>();

        String removeStyle1 = "h"; //Works
        String removeStyle2 = "h;bc-c"; //Works
        String removeStyle3 = "w;bc-c;h"; //Works
        String removeStyle4 = "Button{bc-c}"; //Works
        String removeStyle5 = ".child{bc-c;h}"; //Works
        String removeStyle6 = ".child{bc-c;h;w}"; //Works
        String removeStyle7 = ".child{br-r; bc-c; w; h}"; // Works
        String removeStyle8 = ":pseudo-element2{w}"; //Works
        String removeStyle9 = ":pseudo-element2{h;br-r}"; //Works
        String removeStyle10 = ":pseudo-element2{br-r}"; //Works
        String removeStyle11 = ":pseudo-element2{pd; br-r; h; w}"; //Works
        String removeStyle12 = "::pseudo-class{bc-c;pd;w;h}"; //Works
        String removeStyle13 = "Button"; //Works
        String removeStyle14 = ".child"; //Works
        String removeStyle15 = ":pseudo-element2"; //Works
        String removeStyle16 = "::pseudo-class{w;h;pd;bc-c}"; //Works
        String removeStyle17 = ":pseudo-element2;.child{h;bc-c;w};::pseudo-class{w;pd;h}"; //Works
        String removeStyle18 = ":pseudo-element2;.child{h};::pseudo-class{w;pd}"; //Works
        String removeStyle19 = ":pseudo-element2;.child{w};::pseudo-class{w;pd}"; //Works
        String removeStyle20 = ":pseudo-element2;.child{bc-c};::pseudo-class{w;pd}"; //Works
        String removeStyle21 = ":pseudo-element2;.child{h;w};::pseudo-class{w;pd}"; //Works
        String removeStyle22 = ":pseudo-element2;.child{h;w};::pseudo-class{w;pd;h;bc-c}"; //Works
        String removeStyle23 = ":pseudo-element2;.child{w;bc-c};::pseudo-class{w;pd}"; //Works
        String removeStyle24 = ":pseudo-element2;.child{w;bc-c};::pseudo-class{w;pd};Button"; //Works
        String removeStyle25 = ":pseudo-element2;.child{w;bc-c};::pseudo-class{w;pd};Button{bc-c}"; //Works
        String removeStyle26 = ":pseudo-element2;.child;::pseudo-class"; //Works.
        String removeStyle27 = "w;h;:pseudo-element2;.child;::pseudo-class"; //Works.
        String removeStyle28 = "w;h;:pseudo-element2;.child{h;w;bc-c};::pseudo-class{w;pd;h;bc-c}"; //Works
        String removeStyle29 = "w;h;:pseudo-element2{h; w; pd; br-r};Button{bc-c};.child{h;w;bc-c};::pseudo-class{w;pd;h;bc-c}"; //Works
        String removeStyle30 = "w;h;.child{h;w;bc-c};::pseudo-class{w;pd;h;bc-c};:pseudo-element2{h; w; br-r; pd}"; //Works
        String removeStyle31 = "w;h;bc-c;.child{h;w;bc-c};::pseudo-class{w;pd;h;bc-c};:pseudo-element2{h; w; br-r; pd}"; //Works
        String removeStyle32 = "w;h;bc-c;.child{h;w;bc-c};Button;::pseudo-class{w;pd;h;bc-c};:pseudo-element2{h; w; br-r; pd}"; //Works
        String removeStyle33 = "w;h;bc-c;.child{h;w;bc-c};Button{bc-c};::pseudo-class{w;pd;h;bc-c};:pseudo-element2{h; w; br-r; pd}"; //Works

        long start = System.currentTimeMillis();

//        fStyle.setStyle(button1, "bc-c:red");
//        fStyle.addStyle(button1, "bc-c:red");
        fStyle.setStyle(button1, "sc-y:1.4;sc-x:1.3;::click{bc-c:red}"); //Doesn't sort the properties
        System.out.println("------------------------------------------------------------------------");
//        fStyle.addStyle(button1, "sc-x:1.3");
//        System.out.println("------------------------------------------------------------------------");
//        fStyle.addStyle(button1, "br-w:0.2rem;rt:290");
        fStyle.addStyle(button1, "::click{rt:290;sc-x:2}");
        System.out.println("------------------------------------------------------------------------");
//        fStyle.addStyle(button1, ":label{f-s:10px; f-w:500}"); //Doesn't sort the properties

        //Add more test cases for the below code
//        System.out.println("Set style(1)");
//        fStyle.setStyle(button1, style);
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Remove property(2)");
//        fStyle.removeProperty(button1, "h"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Add style(3)");
//        fStyle.addStyle(button1, "h:100px"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Update style(4)");
//        fStyle.updateStyle(button1, "h:300.5px"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Update style(5)");
//        fStyle.updateStyle(button1, "Button{bc-c:#123456}"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Add style(6)");
//        fStyle.addStyle(button1, "Button{w:3rem; h:1.2rem}"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Update style(7)");
//        fStyle.updateStyle(button1, "Button{h: 1.4rem}"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Update style(8)");
//        fStyle.updateStyle(button1, "::pseudo-class{w:12rem; bc-c:#f23a22}"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Add style(9)");
//        fStyle.addStyle(button1, "Label{f-s:1.4rem; f-w:400}"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Update style(10)");
//        fStyle.updateStyle(button1, "Label{f-w:500}"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Update style(11)");
//        fStyle.updateStyle(button1, "w:9.4rem;.child{w:220px}"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Remove property(12)");
//        fStyle.removeProperty(button1, "Button;Label"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Remove property(13)");
//        fStyle.removeProperty(button1, "h;::pseudo-class{w;pd};.child{bc-c;w;h};:pseudo-element2{br-r;w}"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Remove property(14)");
//        fStyle.removeProperty(button1, ":pseudo-element2{pd;h};::pseudo-class"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Add style(15)");
//        fStyle.addStyle(button1, "br-r: 3px 3px 3px 3px; f-s: 0.8rem"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Remove property(16)");
//        fStyle.removeProperty(button1, "f-s; w; bc-c; br-r"); //Works
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Add style(17)");
//        fStyle.addStyle(button1, "::click{bc-c:green; br-r:0.8rem; f-w:700}");
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("Add style(18)");
//        fStyle.addStyle(button1, "bc-c:red");


        long end = System.currentTimeMillis() - start;
        System.out.println("Exec time: " + (double)end / 1000);


        removeStyles.add(removeStyle1);
        removeStyles.add(removeStyle2);
        removeStyles.add(removeStyle3);
        removeStyles.add(removeStyle4);
        removeStyles.add(removeStyle5);
        removeStyles.add(removeStyle6);
        removeStyles.add(removeStyle7);
        removeStyles.add(removeStyle8);
        removeStyles.add(removeStyle9);
        removeStyles.add(removeStyle10);
        removeStyles.add(removeStyle11);
        removeStyles.add(removeStyle12);
        removeStyles.add(removeStyle13);
        removeStyles.add(removeStyle14);
        removeStyles.add(removeStyle15);
        removeStyles.add(removeStyle16);
        removeStyles.add(removeStyle17);
        removeStyles.add(removeStyle18);
        removeStyles.add(removeStyle19);
        removeStyles.add(removeStyle20);
        removeStyles.add(removeStyle21);
        removeStyles.add(removeStyle22);
        removeStyles.add(removeStyle23);
        removeStyles.add(removeStyle24);
        removeStyles.add(removeStyle25);
        removeStyles.add(removeStyle26);
        removeStyles.add(removeStyle27);
        removeStyles.add(removeStyle28);
        removeStyles.add(removeStyle29);
        removeStyles.add(removeStyle30);
        removeStyles.add(removeStyle31);
        removeStyles.add(removeStyle32);
        removeStyles.add(removeStyle33);

//        for(int i = 0; i < removeStyles.size(); i++){
//            System.out.println("Style to remove index: " + i);
//            fStyle.setStyle(button1, style);
//            fStyle.removeProperty(button1, removeStyles.get(i));
//            System.out.println();
//        }

    }

}
















