package com.example.fastfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("animations-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        AnimationsController controller = fxmlLoader.getController();
        controller.getMainContainer().prefWidthProperty().bind(stage.widthProperty());
        controller.getMainContainer().prefHeightProperty().bind(stage.heightProperty());
    }

    public static void main(String[] args) {
        launch();
    }
}