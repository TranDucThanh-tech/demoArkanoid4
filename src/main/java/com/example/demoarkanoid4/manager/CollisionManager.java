package com.example.demoarkanoid4.manager;

import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.core.Collision;
import com.example.demoarkanoid4.core.GameObject;
import com.example.demoarkanoid4.vector2D.Vector2D;
import com.example.demoarkanoid4.core.Wall;
import com.example.demoarkanoid4.core.Brick;

import java.util.List;

public class CollisionManager {

    private Collision buildCollision(GameObject a, GameObject b) {
        if (a == b) return null;
        if (!a.getBounds().intersects(b.getBounds())) return null;

        double overlapX = Math.min(a.getBounds().getMaxX(), b.getBounds().getMaxX())
                - Math.max(a.getBounds().getMinX(), b.getBounds().getMinX());
        double overlapY = Math.min(a.getBounds().getMaxY(), b.getBounds().getMaxY())
                - Math.max(a.getBounds().getMinY(), b.getBounds().getMinY());

        return new Collision(a, b, System.nanoTime(), overlapX, overlapY);
    }

    public void handleBallPaddleCollision(Ball ball, Paddle paddle) {
        Collision collision = buildCollision(ball, paddle);
        if (collision == null) return;
        ball.adjustPositionAbovePaddle(paddle);
        // Reflection angle based on where the ball hits the paddle
        double angle = ball.calculateAngle(paddle);
        ball.setVelocity(Math.cos(angle), -Math.sin(angle));
        //SoundManager.getInstance().playSound("paddle_hit");
    }

    public void handleBallWallCollision(Ball ball, List<Wall> walls) {
        for (Wall wall : walls) {
            Collision c = buildCollision(ball, wall);
            if (c != null) {
                //SoundManager.getInstance().playSound("wall_hit");
                Vector2D v = ball.getVelocity();

                switch (wall.getSide()) {
                    case LEFT:
                        ball.setVelocity(Math.abs(v.x), v.y);
                        break;
                    case RIGHT:
                        ball.setVelocity(-Math.abs(v.x), v.y);
                        break;
                    case TOP:
                        ball.setVelocity(v.x, Math.abs(v.y));
                        break;
                }
            }
        }
    }

    public void handleBallBrickCollision(Ball ball, List<Brick> bricks) {
        for (Brick brick : bricks) {
            Collision c = buildCollision(ball, brick);
            if (c != null && !brick.isDestroyed()) {
                brick.takeDamage();
                //SoundManager.getInstance().playSound("brick_hit");

                boolean ballFromSide = c.getOverlapX() < c.getOverlapY();
                Vector2D v = ball.getVelocity();

                if (ballFromSide) {
                    ball.setVelocity(-v.x, v.y);
                } else {
                    ball.setVelocity(v.x, -v.y);
                }
                break; // bóng chỉ va chạm 1 brick mỗi frame
            }
        }
    }
}