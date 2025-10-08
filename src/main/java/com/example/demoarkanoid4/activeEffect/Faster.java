package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.manager.PaddleManager;

public class Faster implements Effect {

    @Override
    public char getType() {
        return VARIABLES.FASTER;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            ball.setSpeed(ball.getSpeed() * VARIABLES.ACCELERATED_SPEED_MULTIPLIER);
        }
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            ball.setSpeed(ball.getSpeed() / VARIABLES.ACCELERATED_SPEED_MULTIPLIER);
        }
    }
}

