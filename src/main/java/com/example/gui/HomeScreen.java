package com.example.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreen extends Application {
    private Stage currentStage;


    @FXML
    Button play,levels,options,exit;

    @FXML
    protected void onPlayButton() throws Exception {
        new Snakegame().start(new Stage());
    }

    @FXML
    protected void onExitButton() throws Exception {
        System.exit(0);

    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreen.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Anaconda");
        stage.setScene(scene);
        stage.show();




    }
    public static void main(String[] args) {
        launch(args);
    }
}
