package com.example.demoarkanoid4.model.utils;

import com.example.demoarkanoid4.model.core.ball.Ball;
import com.example.demoarkanoid4.controller.core.BallManager;
import com.example.demoarkanoid4.controller.core.PaddleManager;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class InputHandler {
    private final PaddleManager paddle;
    private final BallManager balls;
    public InputHandler(PaddleManager paddle, BallManager balls) {
        this.paddle = paddle;
        this.balls = balls;
    }

    public void input(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT) {
                    paddle.getPaddle().setDirection(-1);
                } else if (e.getCode() == KeyCode.RIGHT) {
                    paddle.getPaddle().setDirection(1);
                } else if (e.getCode() == KeyCode.SPACE) {
                    for (Ball ball : balls.getBalls())
                        ball.release();
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                    paddle.getPaddle().setDirection(0);
                }
            }
        });
    }
}