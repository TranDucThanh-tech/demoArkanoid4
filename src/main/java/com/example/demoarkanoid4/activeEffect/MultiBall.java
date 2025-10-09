package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.manager.PaddleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MultiBall implements Effect {

    @Override
    public char getType() {
        return VARIABLES.MULTIBALL;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddleManager) {
        List<Ball> activeBalls = ballManager.getBalls();
        Queue<Ball> inactiveBalls = ballManager.getInactiveBalls();

        if (activeBalls.isEmpty()) return;

        // Sao chép danh sách hiện tại để tránh clone nhân tiếp trong cùng lượt
        List<Ball> originalBalls = new ArrayList<>(activeBalls);

        // Góc lệch ±20 độ
        double[] offsets = { Math.toRadians(-20), Math.toRadians(20) };

        for (Ball mainBall : originalBalls) {
            double baseSpeed = mainBall.getVelocity().length();
            double baseAngle = mainBall.getVelocity().getAngle();

            for (double offset : offsets) {
                Ball clone = inactiveBalls.poll();
                if (clone == null) return; // hết bóng trong pool thì dừng luôn

                clone.copyState(mainBall);
                clone.getVelocity().setAngleAndLength(baseAngle + offset, baseSpeed);
                clone.launch();

                activeBalls.add(clone);
            }
        }
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddleManager) {
        // MultiBall không revert
    }
}
