package com.example.demoarkanoid4.model.activeEffect;

import com.example.demoarkanoid4.model.utils.GameVar;
import com.example.demoarkanoid4.model.core.ball.Ball;
import com.example.demoarkanoid4.controller.core.BallManager;
import com.example.demoarkanoid4.controller.core.PaddleManager;

import java.util.ArrayList;
import java.util.List;

public class MultiBall implements Effect {

    @Override
    public char getType() {
        return GameVar.MULTIBALL;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddleManager) {
        List<Ball> activeBalls = ballManager.getBalls();

        if (activeBalls.isEmpty()) return;

        // Sao chép danh sách hiện tại để tránh clone nhân tiếp trong cùng lượt
        List<Ball> originalBalls = new ArrayList<>(activeBalls);

        // Góc lệch ±20 độ
        double[] offsets = { Math.toRadians(-20), Math.toRadians(20) };

        for (Ball mainBall : originalBalls) {
            double baseSpeed = mainBall.getVelocity().length();
            double baseAngle = mainBall.getVelocity().getAngle();

            for (double offset : offsets) {
                //  Tạo mới bóng thay vì lấy từ pool
                Ball clone = new Ball();

                // Sao chép trạng thái (vị trí, kích thước, v.v.)
                clone.copyState(mainBall);

                // Điều chỉnh hướng bay
                clone.getVelocity().setAngleAndLength(baseAngle + offset, baseSpeed);
                clone.launch();

                // Thêm vào danh sách bóng hoạt động
                activeBalls.add(clone);
            }
        }
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddleManager) {
        // MultiBall không revert
    }
}
