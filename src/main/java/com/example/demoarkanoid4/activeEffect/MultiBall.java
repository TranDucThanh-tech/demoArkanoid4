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
        List<Ball> newBalls = new ArrayList<>();

        // Nhân đôi tất cả bóng hiện tại
        for (Ball original : ballManager.getBalls()) {
            if (newBalls.size() > VARIABLES.MAX_BALL) return;
            Ball clone = new Ball(original); // constructor sao chép (bạn cần có sẵn trong Ball)

            // chỉnh hướng ngẫu nhiên nhẹ để các bóng không bay trùng
            clone.setVelocity(
                    original.getVelocity().x * (Math.random() < 0.5 ? 1 : -1),
                    original.getVelocity().y
            );

            newBalls.add(clone);
        }

        ballManager.getBalls().addAll(newBalls);
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddle) {
        // MultiBall không revert (không thể “xóa” bóng dư)
    }
}

