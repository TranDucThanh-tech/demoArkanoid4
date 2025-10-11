package com.example.demoarkanoid4.controller.core;

import com.example.demoarkanoid4.model.utils.GameVar;
import com.example.demoarkanoid4.model.core.ball.Ball;
import com.example.demoarkanoid4.model.core.paddle.PaddleLike;
import com.example.demoarkanoid4.model.utils.GlobalVar;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BallManager {

    private final List<Ball> balls = new ArrayList<>(); // Danh sách bóng đang hoạt động

    public BallManager() {
        // Khởi tạo một quả bóng đầu tiên
        balls.add(new Ball());
    }

    public void update(double deltaTime, PaddleLike paddle) {
        Iterator<Ball> iterator = balls.iterator();
        while (iterator.hasNext()) {
            Ball ball = iterator.next();
            ball.update(deltaTime, paddle);

            // Nếu bóng rơi khỏi màn hình thì loại bỏ
            if (ball.getY() > GlobalVar.HEIGHT) {
                iterator.remove();
            }
        }

        // Nếu không còn bóng nào → tạo lại 1 bóng mới
        if (balls.isEmpty()) {
            Ball newBall = new Ball();
            newBall.resetState(paddle);
            balls.add(newBall);
        }
    }

    public void render(GraphicsContext gc) {
        for (Ball ball : balls) {
            ball.render(gc);
        }
    }

    public void resetState(PaddleLike paddle) {
        // Giữ lại 1 bóng duy nhất
        while (balls.size() > 1) {
            balls.remove(balls.size() - 1);
        }

        // Nếu không còn bóng thì tạo mới
        if (balls.isEmpty()) {
            Ball newBall = new Ball();
            newBall.resetState(paddle);
            balls.add(newBall);
        } else {
            balls.get(0).resetState(paddle);
        }
    }

    public List<Ball> getBalls() {
        return balls;
    }
}
