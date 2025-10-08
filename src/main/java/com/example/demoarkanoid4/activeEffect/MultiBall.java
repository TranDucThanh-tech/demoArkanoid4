package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.manager.PaddleManager;

import java.util.ArrayList;
import java.util.List;

public class MultiBall implements Effect {

    @Override
    public char getType() {
        return VARIABLES.MULTIBALL;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddle) {
        List<Ball> balls = ballManager.getBalls();
        int currentSize = balls.size();

        // Nhân đôi nhưng không vượt quá MAX_BALL
        int limit = Math.min(currentSize, VARIABLES.MAX_BALL - currentSize);

        for (int i = 0; i < limit; i++) {
            Ball clone = new Ball(balls.get(i));
            balls.add(clone);
        }
    }


    @Override
    public void revert(BallManager ballManager, PaddleManager paddle) {
        // MultiBall không revert (không thể “xóa” bóng dư)
    }
}

