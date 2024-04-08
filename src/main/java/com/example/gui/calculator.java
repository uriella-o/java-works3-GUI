package com.example.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class calculator extends Application {

    Button btn0,btn1, btn2, btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    TextField screen;
    @Override
    public void start(Stage stage) throws Exception {
        screen = new TextField();
        screen.setPrefWidth(320);
        screen.setPrefHeight(100);

        btn0=new Button("0");
        btn0.setPrefWidth(320);
        btn0.setPrefHeight(100);

        btn1=new Button("1");
        btn1.setPrefWidth(100);
        btn1.setPrefHeight(100);

        btn2=new Button("2");
        btn2.setPrefWidth(100);
        btn2.setPrefHeight(100);

        btn3=new Button("3");
        btn3.setPrefWidth(100);
        btn3.setPrefHeight(100);

        btn4=new Button("4");
        btn4.setPrefWidth(100);
        btn4.setPrefHeight(100);

        btn5=new Button("5");
        btn5.setPrefWidth(100);
        btn5.setPrefHeight(100);

        btn6=new Button("6");
        btn6.setPrefWidth(100);
        btn6.setPrefHeight(100);

        btn7=new Button("7");
        btn7.setPrefWidth(100);
        btn7.setPrefHeight(100);

        btn8=new Button("8");
        btn8.setPrefWidth(100);
        btn8.setPrefHeight(100);

        btn9=new Button("9");
        btn9.setPrefWidth(100);
        btn9.setPrefHeight(100);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(screen,0,1,3,1);
        grid.addRow(3,btn1,btn2,btn3);
        grid.addRow(4,btn4,btn5,btn6);
        grid.addRow(5,btn7,btn8,btn9);
        grid.add(btn0,0,6,3,1);
        Scene calc = new Scene(grid, 350,500);
        stage.setScene(calc);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
