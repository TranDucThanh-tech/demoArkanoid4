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
    private final List<Ball> inactiveBalls = new ArrayList<>();

    public BallManager() {
        // Chuẩn bị sẵn pool bóng
        for (int i = 0; i < VARIABLES.MAX_BALL; i++) {
            inactiveBalls.add(new Ball());
        }
    }

    public void update(double deltaTime, PaddleLike paddle) {
        Iterator<Ball> iterator = balls.iterator();
        while (iterator.hasNext()) {
            Ball ball = iterator.next();
            ball.update(deltaTime, paddle);

            // Nếu bóng rơi khỏi màn hình
            if (ball.getY() > VARIABLES.HEIGHT) {
                iterator.remove();
                inactiveBalls.add(ball);
            }
        }

        // Nếu không còn bóng nào hoạt động → tái sử dụng một bóng
        if (balls.isEmpty() && !inactiveBalls.isEmpty()) {
            Ball newBall = getLastInactiveBall();
            newBall.resetState(paddle); // Đưa về trạng thái chờ
            balls.add(newBall);
        }
    }

    private Ball getLastInactiveBall() {
        int lastIndex = inactiveBalls.size() - 1;
        Ball ball = inactiveBalls.get(lastIndex);
        inactiveBalls.remove(lastIndex);
        return ball;
    }

    public void render(GraphicsContext gc) {
        for (Ball ball : balls) ball.render(gc);
    }

    public List<Ball> getBalls() { return balls; }

    public List<Ball> getInactiveBalls() {
        return inactiveBalls;
    }

}
