package com.example.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Myapp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("get out");
        Pane pane = new Pane();
        pane.getChildren().add(button);
        Scene scene = new Scene(pane, 400 ,400);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("My App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
