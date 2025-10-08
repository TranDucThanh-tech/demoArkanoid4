package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.manager.PaddleManager;
import com.example.demoarkanoid4.core.paddle.PaddleLike;

import java.util.List;

public class MultiBall implements Effect {

    @Override
    public char getType() {
        return VARIABLES.MULTIBALL;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddleManager) {
        List<Ball> activeBalls = ballManager.getBalls();
        List<Ball> inactiveBalls = ballManager.getInactiveBalls(); // cần thêm getter
        PaddleLike paddle = paddleManager.getPaddle();

        int currentSize = activeBalls.size();
        int available = inactiveBalls.size();
        int maxAdd = Math.min(currentSize, Math.min(available, VARIABLES.MAX_BALL - currentSize));

        for (int i = 0; i < maxAdd; i++) {
            Ball source = activeBalls.get(i);
            Ball clone = inactiveBalls.remove(inactiveBalls.size() - 1); // lấy bóng cuối
            clone.copyState(source);
            activeBalls.add(clone);
        }
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddleManager) {
        // MultiBall không revert (không thể “xóa” bóng dư)
    }
}
