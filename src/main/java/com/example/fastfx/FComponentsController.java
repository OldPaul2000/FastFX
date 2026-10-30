package com.example.fastfx;

import com.example.fastfx.src.fastfxcomponents.FPane;
import com.example.fastfx.src.fastfxcomponents.FTabPane;
import com.example.fastfx.tests.UITestingPanel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class FComponentsController {
    @FXML
    private FPane mainContainer;

    public FPane getMainContainer () {
        return mainContainer;
    }

    @FXML
    public void initialize () {
        UITestingPanel testingPanel = new UITestingPanel();
        testingPanel.x(25).y(25);
        mainContainer.add(testingPanel);

        ComboBox<String> c = new ComboBox<>();
        c.getItems().add("Item 1");
        c.getItems().add("Item 2");
        c.getItems().add("Item 3");
        c.setLayoutX(300);
        c.setLayoutY(500);
        mainContainer.add(c);


        FTabPane tabPane = new FTabPane();
        tabPane.width(700).height(200).x(300).y(100);
        tabPane.tabsSpacing(4);

        FPane tab1 = new FPane();
        tab1.bgColor(Color.RED).width(200).height(200);
        FPane tab2 = new FPane();
        tab2.bgColor(Color.GREEN).width(200).height(200);
        FPane tab3 = new FPane();
        tab3.bgColor(Color.BLUE).width(200).height(200);
        FPane tab4 = new FPane();
        tab4.bgColor(Color.YELLOW).width(200).height(200);

        Image contactsIcon = new Image(getClass().getResourceAsStream("contacts-alt-svgrepo-com.png"));

        tabPane.addTab("Tab", tab1);
        tabPane.addTab("Tab", tab2);
        tabPane.addTab("Tab", tab3);
        tabPane.addTab("Tab", tab4);

        tabPane.setTabIcon(0, contactsIcon);
        tabPane.setTabIcon(1, contactsIcon);
        tabPane.setTabIcon(2, contactsIcon);
        tabPane.setTabIcon(3, contactsIcon);

        tabPane.tabsBar(() -> {
            tabPane.height(20);
        });


        tabPane.padding(2).bgRadius(5).bgColor("#183666");
        tabPane.tabs((index) -> {
            tabPane.width(100);
            tabPane.bgColor("#6495e3");
            tabPane.bgRadius(5, 5, 0, 0);
        });
        tabPane.tabsBar(() -> {
            tabPane.bgOpacity(0);
        });
        tabPane.tabsIconPanes((index) -> {
            tabPane.bgColor(Color.RED);
        });
        tabPane.tabsBarContent(() -> {
            tabPane.bgColor(Color.RED);
            tabPane.padding(2, 2, 0, 2);
        });
        tabPane.tabsCloseIconPanes((index) -> {
            //            tabPane.bgOpacity(0);
            //            tabPane.bgRadius(5, 0, 0, 5);
        });
        tabPane.viewScrollPanes((index) -> {
            tabPane.bgOpacity(0);
        });
        mainContainer.add(tabPane);


        testingPanel.setButtonText(1, "");
        testingPanel.setButtonText(2, "");
        testingPanel.setButtonText(3, "");
        testingPanel.setButtonText(4, "");
        testingPanel.setButtonText(5, "");
        testingPanel.setButtonEvent(1, e -> {
            tabPane.addTab("Tab", tab1);
            tabPane.tabs((index) -> {
                tabPane.width(100);
            });
        });
        testingPanel.setButtonEvent(2, e -> {
            tabPane.addTab("Tabb", tab2);
            tabPane.tabs((index) -> {
                tabPane.width(100);
            });
        });
        testingPanel.setButtonEvent(3, e -> {
            tabPane.addTab("Tabbb", tab3);
            tabPane.tabs((index) -> {
                tabPane.width(100);
            });
        });
        testingPanel.setButtonEvent(4, e -> {
            tabPane.addTab("Tabbbb", tab4);
            tabPane.tabs((index) -> {
                tabPane.width(100);
            });
        });
        testingPanel.setButtonEvent(5, e -> {

        });
    }
}























