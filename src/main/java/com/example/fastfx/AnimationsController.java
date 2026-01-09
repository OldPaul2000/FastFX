package com.example.fastfx;

import com.example.fastfx.src.fcomponents.FPane;
import com.example.fastfx.src.fcomponents.FPane2;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnimationsController {

    @FXML
    private FPane mainContainer;

    public FPane getMainContainer(){
        return mainContainer;
    }

    public void initialize() {
        BackgroundSetter.setBackgroundColor(mainContainer, "#2c4773");


        FPane2 fPane = new FPane2();
        fPane.bgColor("#999999")
                .width("200")
                .height("100")
                .x("200")
                .y(100)
                .click(() -> {
                    fPane.x(320);
                })
                .click(() -> {
                    fPane.x("320 1s");
                });


        Button button = new Button("Play");
        button.setPrefWidth(80);
        button.setPrefHeight(30);
        button.setLayoutX(100);
        button.setLayoutY(40);
        mainContainer.add(button);

        Button button2 = new Button("Reverse");
        button2.setPrefWidth(80);
        button2.setPrefHeight(30);
        button2.setLayoutX(100);
        button2.setLayoutY(80);
        mainContainer.add(button2);

        Button button3 = new Button("Play fast");
        button3.setPrefWidth(80);
        button3.setPrefHeight(30);
        button3.setLayoutX(100);
        button3.setLayoutY(120);
        mainContainer.add(button3);

        Button button4 = new Button("Reverse fast");
        button4.setPrefWidth(80);
        button4.setPrefHeight(30);
        button4.setLayoutX(100);
        button4.setLayoutY(160);
        mainContainer.add(button4);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(fPane.prefWidthProperty(), 200);
        KeyFrame kf = new KeyFrame(Duration.ZERO, kv);
        KeyValue kv2 = new KeyValue(fPane.prefWidthProperty(), 220);
        KeyFrame kf2 = new KeyFrame(Duration.millis(1000), kv2);
        KeyFrame kf3 = new KeyFrame(Duration.millis(300), kv2);
        timeline.getKeyFrames().addAll(kf, kf2);


        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            timeline.setRate(1);
            timeline.getKeyFrames().add(kf3);
            timeline.getKeyFrames().remove(kf2);
            timeline.play();
        });

        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            timeline.setRate(-1);
            timeline.getKeyFrames().add(kf2);
            timeline.getKeyFrames().remove(kf3);
            timeline.play();
        });

        button3.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            timeline.stop();
            fPane.width("220");
        });

        button4.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            timeline.stop();
            fPane.width("200");
        });



        /* Properties remained to implement:
            top
            raw string top
            right
            raw string right
            bottom
            raw string bottom
            left
            raw string left
         */



        mainContainer.add(fPane);


//        String[] p1 = new String[]{"20px", "1s", "2s", "40px", "100px", "2s"};
//        String[] props1 = PropertyValidatorFilter.filterMultipleDimensionValues(p1);
//        System.out.println(Arrays.toString(props1));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p1));
//        System.out.println();
//
//        String[] p2 = new String[]{"20px", "1s", "30px", "0.5s", "40px", "100px", "2s"};
//        String[] props2 = PropertyValidatorFilter.filterMultipleDimensionValues(p2);
//        System.out.println(Arrays.toString(props2));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p2));
//        System.out.println();
//
//        String[] p3 = new String[]{"20px", "1s", "30px", "40px", "0.4s", "100px", "2s"};
//        String[] props3 = PropertyValidatorFilter.filterMultipleDimensionValues(p3);
//        System.out.println(Arrays.toString(props3));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p3));
//        System.out.println();
//
//        String[] p4 = new String[]{"20px", "1s", "30px", "2s","40px", "0.15s", "100px", "2s"};
//        String[] props4 = PropertyValidatorFilter.filterMultipleDimensionValues(p4);
//        System.out.println(Arrays.toString(props4));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p4));
//        System.out.println();
//
//        String[] p5 = new String[]{"-20px", "30px", "1s", "40px", "100px", "2s"};
//        String[] props5 = PropertyValidatorFilter.filterMultipleDimensionValues(p5);
//        System.out.println(Arrays.toString(props5));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p5));
//        System.out.println();
//
//        String[] p6  = new String[]{"20px", "1s", "30px", "1s", "-40px", "100px", "-2s"};
//        String[] props6 = PropertyValidatorFilter.filterMultipleDimensionValues(p6);
//        System.out.println(Arrays.toString(props6));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p6));
//        System.out.println();
//
//        String[] p7 = new String[]{"1s", "30px", "1s", "40px", "-100px", "2s"};
//        String[] props7 = PropertyValidatorFilter.filterMultipleDimensionValues(p7);
//        System.out.println(Arrays.toString(props7));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p7));
//        System.out.println();
//
//        String[] p8 = new String[]{"40px", "1s" , "-100px", "0.5s"};
//        String[] props8 = PropertyValidatorFilter.filterMultipleDimensionValues(p8);
//        System.out.println(Arrays.toString(props8));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p8));
//        System.out.println();
//
//        String[] p9 = new String[]{"40px", "1s" , "200px", "1s" , "-100px", "0.5s"};
//        String[] props9 = PropertyValidatorFilter.filterMultipleDimensionValues(p9);
//        System.out.println(Arrays.toString(props9));
//        System.out.println("validated: " + PropertyValidatorFilter.validateMultipleDimensionValue(p9));
//        System.out.println();


//        FPane childPane = new FPane();
//        childPane.bgColor("green")
//                .x(0)
//                .y(0)
//                .height(20);
//        fPane.add(childPane);
//         childPane.width("100%");

//        ElementResizer resizer = new ElementResizer(mainContainer);
//        resizer.addElement(fPane, ResizeMode.DRAG_EDGES);
//
//        ElementMover mover = new ElementMover(mainContainer);
//        mover.addElement(fPane, childPane);
//        mover.setEdgeLimits(fPane, 7);

        //======================================================================================================
//        Pane pane = new Pane();
//        pane.setPrefWidth(400);
//        pane.setPrefHeight(400);
//        pane.setLayoutY(50);
//        pane.setLayoutX(150);
//        BackgroundSetter.setBackgroundColor(pane, "#333333");
//        UI.add(mainContainer, pane);
//
//        Pane element1 = new Pane();
//        element1.setPrefWidth(150);
//        element1.setPrefHeight(100);
//        element1.setLayoutY(50);
//        element1.setLayoutX(50);
//        BackgroundSetter.setBackgroundColor(element1, "#555555");
//        UI.add(pane, element1);
//
//        Pane titleBar = new Pane();
//        titleBar.prefWidthProperty().bind(element1.prefWidthProperty());
//        titleBar.setPrefHeight(30);
//        BackgroundSetter.setBackgroundColor(titleBar, "#666666");
//        UI.add(element1, titleBar);
//
//        Label title = new Label("Window (1)");
//        title.setLayoutX(5);
//        title.setPrefHeight(15);
//        double titleY = titleBar.getPrefHeight() / 2 - title.getPrefHeight() / 2;
//        title.setLayoutY(titleY);
//        FontSetter.setFontSize(title, 12);
//        UI.add(titleBar, title);
//
//        ElementMover mover = new ElementMover(pane);
//        mover.addElement(element1);
//        mover.addPermittedChild(element1, titleBar);
//        mover.setEdgeLimits(element1, 7);
//
//        ElementResizer resizer = new ElementResizer(pane);
//        resizer.addElement(element1, ResizeMode.DRAG_EDGES);
//
//        FAnimation animation = new FAnimation(element1);
//        animation.add(FAnimations.BG_RADIUS, 0, Duration.ZERO);
//        animation.add(FAnimations.BG_RADIUS, 10, Duration.millis(500));
//
//        FAnimation animation2 = new FAnimation(titleBar);
//        animation2.add(FAnimations.BG_TL_RADIUS, 0, Duration.ZERO);
//        animation2.add(FAnimations.BG_TL_RADIUS, 10, Duration.millis(500));
//        animation2.add(FAnimations.BG_TR_RADIUS, 0, Duration.ZERO);
//        animation2.add(FAnimations.BG_TR_RADIUS, 10, Duration.millis(500));
//
//        animation2.addStartListener(() -> {
//            System.out.println();
//            System.out.println("Animation2 started");
//        });
//
//        animation2.addEndListener((anim, time) -> {
//            System.out.println("Animation " + anim + " has ended");
//            System.out.println("Time: " + time);
//        });
//
//        element1.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) ->{
//            animation.play();
//            animation2.play();
//        });
//
//        element1.addEventHandler(MouseEvent.MOUSE_EXITED, (e) ->{
//            animation.reverse();
//            animation2.reverse();
//        });

//        ScrollBar scrollBar = new ScrollBar();
//        ScrollPane scrollPane = new ScrollPane();
//
//        Button button = new Button();
//        RadioButton radioButton = new RadioButton();
//
//        CheckBox checkBox = new CheckBox();
//        ChoiceBox<String> choiceBox = new ChoiceBox<>();
//        ComboBox<String> comboBox = new ComboBox<>();
//
//        Label label = new Label();
//        Hyperlink hyperlink = new Hyperlink();
//        TextArea textArea = new TextArea();
//        TextField textField = new TextField();
//        PasswordField passwordField = new PasswordField();
//
//        Slider slider = new Slider();
//        Spinner<Double> spinner = new Spinner<>();
//        ColorPicker colorPicker = new ColorPicker();
    }

    public static Border setBorder(String colorValue, String borderStyle, double radius, double width){
        BorderStrokeStyle bs = BorderStrokeStyle.NONE;
        if(borderStyle.equals("dashed")){
            bs = BorderStrokeStyle.DASHED;
        }
        if(borderStyle.equals("solid")){
            bs = BorderStrokeStyle.SOLID;
        }
        CornerRadii radii = new CornerRadii(radius, radius, radius, radius, false);
        BorderWidths borderWidths = new BorderWidths(width, width, width, width);
        BorderStroke borderStroke = new BorderStroke(Color.valueOf(colorValue), bs, radii, borderWidths);
        return new Border(borderStroke);
    }
}
