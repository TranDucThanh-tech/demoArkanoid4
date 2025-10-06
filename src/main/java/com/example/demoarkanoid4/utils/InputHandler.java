package com.example.demoarkanoid4.utils;

import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.core.ball.Ball;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class InputHandler {
    private final Paddle paddle;
    private final Ball ball;

    public InputHandler(Paddle paddle, Ball ball) {
        this.paddle = paddle;
        this.ball = ball;
    }

    public void input(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT) {
                    paddle.setDirection(-1);
                } else if (e.getCode() == KeyCode.RIGHT) {
                    paddle.setDirection(1);
                } else if (e.getCode() == KeyCode.SPACE) {
                    ball.release();
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                    paddle.setDirection(0);
                }
            }
        });
    }
}