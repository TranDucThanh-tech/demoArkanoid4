package com.example.demoarkanoid4;

import com.example.demoarkanoid4.manager.GameManager;
import com.example.demoarkanoid4.utils.InputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            GameManager gameManager = new GameManager();
            Scene scene = new Scene(gameManager, VARIABLES.WIDTH, VARIABLES.HEIGHT);

            InputHandler inputHandler = new InputHandler(gameManager.getPaddle(), gameManager.getBall());
            inputHandler.input(scene);

            primaryStage.setTitle("Arkanoid Game");
            primaryStage.setScene(scene);
            primaryStage.show();

            gameManager.requestFocus(); // focus AFTER scene is shown
        } catch (Exception e) {
            System.err.println("Failed to launch Arkanoid Game: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}