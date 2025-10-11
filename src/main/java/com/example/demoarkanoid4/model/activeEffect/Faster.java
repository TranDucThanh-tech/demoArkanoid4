package com.example.demoarkanoid4.model.activeEffect;

import com.example.demoarkanoid4.model.utils.GameVar;
import com.example.demoarkanoid4.model.core.ball.Ball;
import com.example.demoarkanoid4.controller.core.BallManager;
import com.example.demoarkanoid4.controller.core.PaddleManager;

public class Faster implements Effect {

    @Override
    public char getType() {
        return GameVar.FASTER;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            // tăng tốc dựa trên vận tốc gốc
            ball.setSpeed(GameVar.SPEED_OF_BALL * GameVar.ACCELERATED_SPEED_MULTIPLIER);
        }
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            // khôi phục vận tốc gốc
            ball.setSpeed(GameVar.SPEED_OF_BALL);
        }
    }
}

