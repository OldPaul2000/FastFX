package com.example.fastfx;

import com.example.fastfx.fastfx.elements.cache.ElementsCache;
import com.example.fastfx.fastfx.style.FStyle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.util.Set;

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

    boolean clicked = false;
    public void initialize(){

        checkBox1.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

        FStyle fStyle = new FStyle(mainContainer);

        String style1 = "bc-c:red; h:100px; w:60px; br-r: 8%; " +
                       "Button{bc-c:green}; " +
                       ".child{bc-c:#214fea; w:200px; h:140px}; " +
                       ":pseudo-element{h:20px; br-r:5px; w:30px}; " +
                       ":pseudo-element2{br-r:7px; h:23.4px; w:45px}; " +
                       "::pseudo-class{w:100px; bc-c:yellow; pd:10%; h:200px}";

        String style2 = "bc-c:red;" +
                "Button{bc-c:green; tx:20%; ty:40rem; br-r: 0.8rem}; " +
                ".child{bc-c:#214fea; w:200px; h:140px}; " +
                ":pseudo-element{h:20px; br-r:5px; w:30px}; " +
                ":pseudo-element2{br-r:7px; h:23.4px; w:45px}; " +
                "::pseudo-class{w:100px; bc-c:yellow; pd:10%; h:200px}";

        String style3 = "bc-c:red; w:60px; bc-c:green; h:200px; " +
                "Button{bc-c:green}; " +
                ".child{bc-c:#214fea; w:200px; h:140px}; " +
                ":pseudo-element{h:20px; br-r:5px; w:30px}; " +
                ":pseudo-element2{br-r:7px; h:23.4px; w:45px}; " +
                "::pseudo-class{w:100px}";

        String style4 =
                "Button{bc-c:green}; " +
                ".child{bc-c:#214fea; w:200px; h:140px}; " +
                ":pseudo-element{h:20px; br-r:5px; w:30px}; " +
                ":pseudo-element2{br-r:7px; h:23.4px; w:45px}; " +
                "::pseudo-class{w:100px; bc-c:yellow; pd:10%; h:200px}";

        String style5 = "bc-c:red; h:100px; w:60px; " +
                "Button{bc-c:green}; " +
                ".child{bc-c:#214fea; w:200px; h:140px}; " +
                ":pseudo-element{h:20px; br-r:5px; w:30px}; " +
                ":pseudo-element2{br-r:7px; h:23.4px; w:45px}; " +
                "::pseudo-class{w:100px; bc-c:yellow; pd:10%; h:200px}";

        String style6 = "bc-c:red; h:100px; w:60px; " +
                ".child{bc-c:#214fea; w:200px; h:140px}; " +
                ":pseudo-element{h:20px; br-r:5px; w:30px}; " +
                ":pseudo-element2{br-r:7px; h:23.4px; w:45px}; " +
                "::pseudo-class{w:100px; bc-c:yellow; pd:10%; h:200px}";

        String style7 = "bc-c:red; h:100px; w:60px; " +
                "Button{bc-c:green}; " +
                ".child{bc-c:#214fea; w:200px; h:140px}; " +
                ":pseudo-element2{br-r:7px; h:23.4px; w:45px}; " +
                "::pseudo-class{w:100px; bc-c:yellow; pd:10%; h:200px}";

        String style8 = "bc-c:red; h:100px; w:60px; " +
                        "Button{bc-c:green}; " +
                        "::pseudo-class{w:100px; bc-c:yellow; pd:10%; h:200px}";

        String addStyle1 = "ty:30px; tx:10px";
        String addStyle2 = "Button{ty:-5.7px}";
        String addStyle3 = "::pseudo-class{sc-x:1.3}";
        String addStyle4 = ".child{op:0.7; bc-c:red}";

        fStyle.setStyle(button1, style8);
        fStyle.addStyle(button1, addStyle1);
        fStyle.addStyle(button1, addStyle2);
        fStyle.addStyle(button1, addStyle3);
        fStyle.addStyle(button1, addStyle4);
    }







    private void methodForHoldingCode(){
//        FastFXStyle style = new FastFXStyle(mainContainer);
        ElementsCache elementsCache = new ElementsCache(mainContainer);

        long start = System.currentTimeMillis();


        Set<Node> nodes = radioButtonsContainer.lookupAll("RadioButton");
        for(Node node :nodes){
            if(node.getId().equals("radio3")){
                System.out.println(node.getId());
            }
        }

//       elementsCache.searchAllElements();
//        System.out.println(elementsCache.getAllChildren());


//        System.out.println(Arrays.toString(FStyle.separateStyles("w:200 d:1s; h:100 d:2s; bc-c:#521def; br-r:0.8em; box:: w:1.2em; h:1.2em")));


        double execTime = ((double) System.currentTimeMillis() - start) / 1000;
        System.out.println("Execution time: " + execTime);
    }

}
















