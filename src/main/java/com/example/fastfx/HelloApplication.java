package com.example.fastfx;

import com.example.fastfx.src.fastfxcomponents.FPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fcomponents-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        FComponentsController controller = fxmlLoader.getController();
        FPane mainContainer = controller.getMainContainer();
        mainContainer.width(stage.getWidth());
        mainContainer.height(stage.getHeight());

        stage.widthProperty().addListener((val, oldVal, newVal) -> {
            mainContainer.width((double) newVal);
        });
        stage.heightProperty().addListener((val, oldVal, newVal) -> {
            mainContainer.height((double) newVal);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}