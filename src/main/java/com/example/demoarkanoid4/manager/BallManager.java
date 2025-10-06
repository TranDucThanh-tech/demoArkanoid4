package com.example.demoarkanoid4.manager;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.paddle.PaddleLike;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class BallManager {
    private final List<Ball> balls = new ArrayList<>();

    public void update(double deltaTime, PaddleLike paddle) {
        for (Ball ball : balls) {
            ball.update(deltaTime, paddle);
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
