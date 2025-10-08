package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.manager.PaddleManager;

public class Stronger implements Effect {

    @Override
    public char getType() {
        return VARIABLES.STRONGER;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            //  Tăng sức mạnh
            ball.setStrong(ball.getStrong() * VARIABLES.BUFF);

            //  Chuyển sang chế độ xuyên gạch
            ball.setCollisionMode(Ball.CollisionMode.PIERCING);
        }
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            //Trả lại giá trị gốc
            ball.setStrong(VARIABLES.STRONG_OF_BALL);
            // Trả lại chế độ bình thường
            ball.setCollisionMode(Ball.CollisionMode.NORMAL);
        }
    }
}
