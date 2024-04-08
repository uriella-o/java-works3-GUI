package com.example.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class tic extends Application {
        private char currentPlayer = 'X';
        private Button[][] buttons = new Button[3][3];

        @Override
        public void start(Stage primaryStage) throws Exception {


            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            // Create buttons and add them to the grid
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    Button button = new Button();
                    button.setPrefSize(100, 100);
                    button.setOnAction(event -> {
                        if (((Button) event.getSource()).getText().isEmpty()) {
                            button.setText(String.valueOf(currentPlayer));
                            if (checkWin()) {
                                System.out.println("Player " + currentPlayer + " wins!");
                                primaryStage.close();
                            } else if (isBoardFull()) {
                                System.out.println("It's a draw!");
                                primaryStage.close();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            }
                        }
                    });
                    buttons[row][col] = button;
                    gridPane.add(button, col, row);
                }
            }

            Scene scene = new Scene(gridPane, 320, 320);
            primaryStage.setTitle("Tic Tac Toe");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        // Check if the current player has won
        private boolean checkWin() {
            // Check rows and columns
            for (int i = 0; i < 3; i++) {
                if (buttons[i][0].getText().equals(String.valueOf(currentPlayer))
                        && buttons[i][1].getText().equals(String.valueOf(currentPlayer))
                        && buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                    return true; // Row win
                }
                if (buttons[0][i].getText().equals(String.valueOf(currentPlayer))
                        && buttons[1][i].getText().equals(String.valueOf(currentPlayer))
                        && buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                    return true; // Column win
                }
            }
            // Check diagonals
            if (buttons[0][0].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Diagonal win (top-left to bottom-right)
            }
            if (buttons[0][2].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Diagonal win (top-right to bottom-left)
            }
            return false;
        }

        // Check if the board is full
        private boolean isBoardFull() {
            for (Button[] buttonRow : buttons) {
                for (Button button : buttonRow) {
                    if (button.getText().isEmpty()) {
                        return false;
                    }
                }
            }
            return true;
        }

        public static void main(String[] args) {
            launch(args);
        }



}


