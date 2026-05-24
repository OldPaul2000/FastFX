package com.example.fastfx;

import com.example.fastfx.src.fcomponents.FLabel;
import com.example.fastfx.src.fcomponents.FPane2;
import com.example.fastfx.src.fcomponents.propertymanager.PropertyParser;
import com.example.fastfx.src.userinteraction.ElementResizer;
import com.example.fastfx.src.userinteraction.ResizeMode;
import javafx.fxml.FXML;

public class AnimationsController {

    @FXML
    private FPane2 mainContainer;

    public FPane2 getMainContainer(){
        return mainContainer;
    }

    @FXML
    public void initialize() {

        ElementResizer resizer = new ElementResizer(mainContainer);

        PropertyParser parser = new PropertyParser();
        parser.parse(PropertyParser.NUMBER_PROPERTY, "0.5");
        System.out.println("valid: " + parser.isValid());


        mainContainer.bgColor("#2c4773");

        FPane2 fPane = new FPane2();
        fPane.bgColor("#999999");
        fPane.width("400");
        fPane.height("200");
        fPane.x(70);
        fPane.y(50);
        mainContainer.add(fPane);

        FPane2 fPane2 = new FPane2();
        fPane2.bgColor("#ff0000");
//        fPane2.click(() -> {
//            fPane2.bgRadius("5px 0.3s");
//        });
        fPane.add(fPane2);
//        fPane2.bgRadius("10px 1s 20px 2s 5px 15px 1s");
        fPane2.bgOpacity(0.5);
        fPane2.width(70);
        fPane2.height(70);
        fPane2.right(15);
        fPane2.bottom("15");
//        fPane2.y("25");


        FLabel fLabel = new FLabel();
        fLabel.setText("Za label");
        fLabel.bgColor("#777777").x(40).y(50).width(200).height(80);
        fLabel.border("#f08b84 solid 3px 10px");
//        fLabel.borderColor("#f08b84 1s #e1f08d 2s");
//        fLabel.borderColor("#f08b84");
//        fLabel.borderOpacity("0.2 1s");
        fLabel.bgOpacity("0.3");
        fLabel.hover(() -> {
//            fLabel.borderWidth("3px 0.5s ease-both");
//            fLabel.borderColor("#ff0000 0.5s ease-both");
//            fLabel.borderOpacity("0.5 0.5s");
            fLabel.bgColor("#214122 1s");
        });
//        fLabel.borderColor("#ff0000");

        fPane.add(fLabel);

        resizer.addElement(fPane, ResizeMode.DRAG_EDGES);

        ElementResizer resizer2 = new ElementResizer(fPane);
        resizer2.addElement(fPane2, ResizeMode.DRAG_EDGES);
        resizer2.addElement(fLabel, ResizeMode.DRAG_EDGES);
    }
}
