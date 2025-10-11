package com.example.demoarkanoid4.model.activeEffect;

import com.example.demoarkanoid4.model.utils.GameVar;
import com.example.demoarkanoid4.model.core.ball.Ball;
import com.example.demoarkanoid4.controller.core.BallManager;
import com.example.demoarkanoid4.controller.core.PaddleManager;

public class Stronger implements Effect {

    @Override
    public char getType() {
        return GameVar.STRONGER;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            //  Tăng sức mạnh
            ball.setStrong(ball.getStrong() * GameVar.BUFF);

            //  Chuyển sang chế độ xuyên gạch
            ball.setCollisionMode(Ball.CollisionMode.PIERCING);
        }
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            //Trả lại giá trị gốc
            ball.setStrong(GameVar.STRONG_OF_BALL);
            // Trả lại chế độ bình thường
            ball.setCollisionMode(Ball.CollisionMode.NORMAL);
        }
    }
}
