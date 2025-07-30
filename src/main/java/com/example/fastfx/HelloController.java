package com.example.fastfx;

import com.example.fastfx.fastfx.style.FStyle;
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

        fStyle.setStyle(button1, "sc-y:1.4;sc-x:1.3");
        System.out.println("------------------------------------------------------------------------");
        fStyle.addStyle(button1, "bc-c:pink; br-r:10px; ::click{bc-c:red;sc-x:2;rt:290};:label{bc-c:yellow; f-w:600; tx:10px; f-c:green; f-s:1.2rem}"); //Doesn't sort the properties
        System.out.println("------------------------------------------------------------------------");
        fStyle.removeProperty(button1, "bc-c");
        System.out.println("------------------------------------------------------------------------");
        fStyle.removeProperty(button1, "::click{sc-x}");
        System.out.println("------------------------------------------------------------------------");
        fStyle.removeProperty(button1, ":label{tx}");


        long end = System.currentTimeMillis() - start;
        System.out.println("Exec time: " + (double)end / 1000);

    }

}
















