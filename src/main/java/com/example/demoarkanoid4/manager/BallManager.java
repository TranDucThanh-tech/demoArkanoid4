package com.example.demoarkanoid4.manager;
import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.paddle.PaddleLike;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BallManager {
    private final List<Ball> balls = new ArrayList<>();

    public BallManager() {
        // Khởi tạo 10 quả bóng mẫu ở vị trí cố định, có thể điều chỉnh vị trí nếu muốn
        for (int i = 0; i < 1; i++) {
            Ball ball = new Ball();
            balls.add(ball);
        }
    }

    public void update(double deltaTime, PaddleLike paddle) {
        Iterator<Ball> iterator = balls.iterator();
        while (iterator.hasNext()) {
            Ball ball = iterator.next();
            ball.update(deltaTime, paddle);

            if (ball.getY() > VARIABLES.HEIGHT) {
                iterator.remove(); // xóa an toàn
            }
        }

        if (balls.isEmpty()) {
            Ball newBall = new Ball();
            balls.add(newBall);
        }
    }


    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void launchAll() {
        for (Ball ball : balls) ball.launch();
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void render(GraphicsContext gc){
        for(Ball ball : balls){
            ball.render(gc);
        }
    }
}
