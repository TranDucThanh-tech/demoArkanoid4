package com.example.demoarkanoid4.controller.system;

import com.example.demoarkanoid4.model.utils.GlobalVar;
import com.example.demoarkanoid4.controller.core.*;
import com.example.demoarkanoid4.controller.effect.EffectManager;
import com.example.demoarkanoid4.model.core.PowerUp;
import com.example.demoarkanoid4.model.core.ball.Ball;
import com.example.demoarkanoid4.model.core.Collision;
import com.example.demoarkanoid4.model.core.GameObject;
import com.example.demoarkanoid4.model.core.brick.SteelBrick;
import com.example.demoarkanoid4.model.utils.Vector2D;
import com.example.demoarkanoid4.model.core.Wall;
import com.example.demoarkanoid4.model.core.brick.Brick;

import java.util.Iterator;

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
        for (Ball ball : balls.getBalls()) {
            for (Brick brick : bricks.getBricks()) {
                if (brick.isDestroyed()) continue;

                Collision c = buildCollision(ball, brick);
                if (c != null) {
                    brick.takeDamage(ball);

                    // Nếu là SteelBrick => luôn bật lại (kể cả bóng xuyên)
                    boolean isSteel = brick instanceof SteelBrick;
                    boolean ballFromSide = c.getOverlapX() < c.getOverlapY();
                    Vector2D v = ball.getVelocity();

                    if (isSteel || !ball.isPiercing()) {
                        if (ballFromSide) {
                            // Đẩy bóng ra ngoài theo trục X
                            if (v.x > 0)
                                ball.setX(brick.getX() - ball.getWidth()); // đi từ trái sang phải
                            else
                                ball.setX(brick.getX() + brick.getWidth()); // đi từ phải sang trái

                            ball.setVelocity(-v.x, v.y);
                        } else {
                            // Đẩy bóng ra ngoài theo trục Y
                            if (v.y > 0)
                                ball.setY(brick.getY() - ball.getHeight()); // từ trên xuống
                            else
                                ball.setY(brick.getY() + brick.getHeight()); // từ dưới lên

                            ball.setVelocity(v.x, -v.y);
                        }
                    }


                    // Gạch bị phá thì thử spawn PowerUp
                    if (brick.isDestroyed()) {
                        powerUpManager.trySpawnPowerUp(brick);
                    }

                    // Nếu bóng không xuyên, thì dừng lại ở viên đầu tiên va chạm
                    break;
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
                activeEffect.addEffect(p.getType(), balls, paddle);
                p.setVisible(false);
            }

            if (p.isVisible() && p.getBounds().getMaxY() > GlobalVar.BOTTOM_EDGE) {
                p.setVisible(false);
            }
        }
    }
}