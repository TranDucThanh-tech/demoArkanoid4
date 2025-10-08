package com.example.demoarkanoid4.manager;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.PowerUp;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.Collision;
import com.example.demoarkanoid4.core.GameObject;
import com.example.demoarkanoid4.utils.Vector2D;
import com.example.demoarkanoid4.core.Wall;
import com.example.demoarkanoid4.core.brick.Brick;
import kotlin.contracts.Effect;

import java.util.Iterator;
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

    public void handleBallPaddleCollision(BallManager balls, PaddleManager paddleManager) {
        for (Ball ball : balls.getBalls()) {  // lặp qua tất cả bóng
            Collision collision = buildCollision(ball, paddleManager.getPaddle());
            if (collision != null) {
                ball.adjustPositionAbovePaddle(paddleManager.getPaddle());

                // Reflection angle dựa vào vị trí chạm trên paddle
                double angle = ball.calculateAngle(paddleManager.getPaddle());
                ball.setVelocity(Math.cos(angle), -Math.sin(angle));

                //SoundManager.getInstance().playSound("paddle_hit");
            }
        }
    }


    public void handleBallWallCollision(BallManager balls, WallManager walls) {
        for (Ball ball : balls.getBalls()) {  // lặp qua tất cả bóng
            for (Wall wall : walls.getWalls()) {
                Collision c = buildCollision(ball, wall);
                if (c != null) {
                    //SoundManager.getInstance().playSound("wall_hit"); // nếu muốn
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
    }


    public void handleBallBrickCollision(BallManager balls, BrickManager bricks, PowerUpManager powerUpManager) {
        for (Ball ball : balls.getBalls()) {  // lặp qua tất cả bóng
            for (Brick brick : bricks.getBricks()) {
                if (brick.isDestroyed()) continue;

                Collision c = buildCollision(ball, brick);
                if (c != null) {
                    brick.takeDamage();
                    //SoundManager.getInstance().playSound("brick_hit");

                    boolean ballFromSide = c.getOverlapX() < c.getOverlapY();
                    Vector2D v = ball.getVelocity();

                    if (ballFromSide) {
                        ball.setVelocity(-v.x, v.y);
                    } else {
                        ball.setVelocity(v.x, -v.y);
                    }

                    if (brick.isDestroyed()) {
                        powerUpManager.trySpawnPowerUp(brick);
                    }

                    break; // mỗi bóng chỉ va chạm 1 brick mỗi frame
                }
            }
        }
    }


    public void handlePaddlePowerUpCollision(PowerUpManager powerUps, PaddleManager paddle, EffectManager activeEffect, BallManager balls) {
        Iterator<PowerUp> puIterator = powerUps.getPowerUps().iterator();
        while (puIterator.hasNext()) {
            PowerUp p = puIterator.next();
            Collision c = buildCollision(p, paddle.getPaddle());
            if (c != null && p.isVisible()) {
                //SoundManager.getInstance().playSound("power_up");
                //ActiveEffect here
                activeEffect.addEffect(p.getType(), balls, paddle);
                p.setVisible(false);
            }

            if (p.isVisible() && p.getBounds().getMaxY() > VARIABLES.BOTTOM_EDGE) {
                p.setVisible(false);
            }
        }
    }
}