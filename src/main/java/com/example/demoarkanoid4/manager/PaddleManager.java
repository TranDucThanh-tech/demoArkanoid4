package com.example.demoarkanoid4.manager;

import com.example.demoarkanoid4.core.paddle.Paddle;
import javafx.scene.canvas.GraphicsContext;

public class PaddleManager {
    private Paddle paddle;

    public PaddleManager() {
         this.paddle = new Paddle();
    }

    public void update(double deltaTime) {
        paddle.update(deltaTime);
    }

    public void render(GraphicsContext gc) {
        paddle.drawObject(gc);
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void reset() { paddle.resetState();}
}
