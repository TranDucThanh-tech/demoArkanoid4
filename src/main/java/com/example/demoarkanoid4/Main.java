package com.example.demoarkanoid4;

import com.example.demoarkanoid4.manager.GameManager;
import com.example.demoarkanoid4.utils.InputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameManager gameManager = new GameManager();
        Scene scene = new Scene(gameManager, VARIABLES.WIDTH, VARIABLES.HEIGHT);

        // Input handler setup (assuming you have getPaddle() and getBall() in GameManager)
        InputHandler inputHandler = new InputHandler(gameManager.getPaddle(), gameManager.getBall());
        inputHandler.input(scene);

        primaryStage.setTitle("Arkanoid Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Make sure GameManager receives keyboard focus
        gameManager.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}