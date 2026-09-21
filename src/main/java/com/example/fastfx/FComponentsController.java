package com.example.fastfx;

import com.example.fastfx.src.fastfxcomponents.FLabel;
import com.example.fastfx.src.fastfxcomponents.FPane;
import com.example.fastfx.src.fastfxcomponents.fdropdownlist.FDropdownList;
import com.example.fastfx.src.util.ui.UIUtil;
import com.example.fastfx.tests.UITestingPanel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class FComponentsController {

    @FXML
    private FPane mainContainer;

    private int itemIndex = 1;
    private boolean displayOpenButton = false;

    public FPane getMainContainer(){
        return mainContainer;
    }

    @FXML
    public void initialize() {
        UITestingPanel testingPanel = new UITestingPanel();
        testingPanel.x(25).y(25);
        mainContainer.add(testingPanel);

        FDropdownList<String> list = new FDropdownList<>();

        Image headerIconImage = new Image(getClass().getResourceAsStream("contacts-alt-svgrepo-com.png"));
        Image openListImage = new Image(getClass().getResourceAsStream("triangle-filled-svgrepo-com.png"));
        Image selectedItemImage = new Image(getClass().getResourceAsStream("checkmark-svgrepo-com.png"));
        list.displayHeaderIcon(true);
        list.displaySelectedItemIcon(true);
        list.setHeaderIcon(headerIconImage);
        list.setOpenButtonIcon(openListImage);
        list.setSelectedItemIcon(selectedItemImage);



        for(int i = 1; i <= 10; i++){
            list.addItem("Item " + itemIndex);
            itemIndex++;
        }


        list.x(300).y(150).height(120).width(100);

        list.itemsSpacing(3);
        list.listHeader(() -> {
            list.height(20);
           list.bgColor(Color.BLACK);
           list.bgOpacity(0.5);
           list.bgRadius(6, 6, 0, 0);
        });
        list.listItem(() -> {
            list.bgColor("#a4acb3");
            list.bgRadius(5);
        });
        list.listContent(() -> {
            list.bgColor("#4a657d");
            list.bgRadius(0, 0, 6, 6);
        });
        list.listContentPane(() -> {
            list.bgRadius(0, 0, 6, 6);
        });
        list.verticalScrollBar(() -> {
            list.width(8);
        });
        list.openButtonPane(() -> {
            list.rotate(180);
        });


        mainContainer.add(list);











        testingPanel.setButtonText(1, "Display open button");
        testingPanel.setButtonText(2, "");
        testingPanel.setButtonText(3, "");
        testingPanel.setButtonText(4, "");
        testingPanel.setButtonText(5, "");
        testingPanel.setButtonEvent(1, e -> {
            list.displayOpenButton(displayOpenButton);
            displayOpenButton = !displayOpenButton;
        });
        testingPanel.setButtonEvent(2, e -> {

        });
        testingPanel.setButtonEvent(3, e -> {

        });
        testingPanel.setButtonEvent(4, e -> {

        });
        testingPanel.setButtonEvent(5, e -> {

        });
    }

    private FPane createListItem(Image image, String name){
        FPane mainPane = new FPane();
        mainPane.bgColor(Color.AZURE);
        ImageView picture = new ImageView();
        picture.setImage(image);
        FLabel nameLabel = new FLabel(name);
        nameLabel.fontSize(12);
        UIUtil.add(mainPane, picture);
        UIUtil.add(mainPane, nameLabel);

        mainPane.prefWidthProperty().addListener((prop, oldVal, newVal) -> {
           nameLabel.setPrefWidth((double) newVal - picture.getLayoutX() - picture.getFitWidth() - 5);
        });
        mainPane.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            picture.setFitWidth((double) newVal * 0.8);
            picture.setFitHeight((double) newVal * 0.8);
            picture.setLayoutY((double) newVal / 2 - picture.getFitHeight() / 2);
            nameLabel.setPrefWidth(picture.getLayoutX() + picture.getFitWidth() + 5);
            nameLabel.setPrefHeight((double) newVal);
            nameLabel.setLayoutX(picture.getLayoutX() + picture.getFitWidth() + 5);
            nameLabel.setLayoutY((double) newVal / 2 - nameLabel.getPrefHeight() / 2);
        });

        picture.setFitWidth(mainPane.getPrefHeight() * 0.8);
        picture.setFitHeight(mainPane.getPrefHeight() * 0.8);
        picture.setLayoutX(5);
        picture.setLayoutY(mainPane.getPrefHeight() / 2 - picture.getFitHeight() / 2);
        nameLabel.setPrefWidth(mainPane.getPrefWidth() - picture.getLayoutX() - picture.getFitWidth() - 5);
        nameLabel.setPrefHeight(mainPane.getPrefHeight());
        nameLabel.setLayoutX(picture.getLayoutX() + picture.getFitWidth() + 10);
        nameLabel.setLayoutY(mainPane.getPrefHeight() / 2 - nameLabel.getPrefHeight() / 2);

        return mainPane;
    }
}























