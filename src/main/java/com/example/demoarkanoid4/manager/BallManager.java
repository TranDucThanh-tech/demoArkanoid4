package com.example.demoarkanoid4.manager;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.paddle.PaddleLike;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class BallManager {

    private final List<Ball> balls = new ArrayList<>();          // Bóng đang hoạt động
    private final Queue<Ball> inactiveBalls = new ArrayDeque<>(); // Pool bóng chưa dùng (FIFO)

    public BallManager() {
        // Chuẩn bị sẵn pool bóng
        for (int i = 0; i < VARIABLES.MAX_BALL; i++) {
            inactiveBalls.offer(new Ball()); // thêm vào cuối queue
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
                inactiveBalls.offer(ball); // trả lại cuối hàng chờ
                System.out.println("Inactive size: " + inactiveBalls.size());
            }
        }

        // Nếu không còn bóng nào hoạt động → tái sử dụng một bóng
        if (balls.isEmpty() && !inactiveBalls.isEmpty()) {
            Ball newBall = inactiveBalls.poll();
            if (newBall != null) {
                newBall.resetState(paddle); // Đưa về trạng thái chờ
                balls.add(newBall);
            }
        }
    }


    public void render(GraphicsContext gc) {
        for (Ball ball : balls) {
            ball.render(gc);
        }
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public Queue<Ball> getInactiveBalls() {
        return inactiveBalls;
    }
}
