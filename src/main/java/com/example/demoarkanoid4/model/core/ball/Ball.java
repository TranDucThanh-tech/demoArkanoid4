package com.example.demoarkanoid4.model.core.ball;

import com.example.demoarkanoid4.model.utils.GameVar;
import com.example.demoarkanoid4.model.core.GameObject;
import com.example.demoarkanoid4.model.core.paddle.PaddleLike;
import com.example.demoarkanoid4.controller.system.AssetManager;
import com.example.demoarkanoid4.model.utils.Vector2D;
import javafx.scene.canvas.GraphicsContext;

public class Ball extends GameObject implements BallLike {

    public enum CollisionMode {
        NORMAL,     // Bật lại khi chạm gạch
        PIERCING    // Xuyên gạch (hiệu ứng Stronger)
    }

    // ==================== FIELDS ==================== //
    private final double baseSpeed = GameVar.SPEED_OF_BALL;
    private double currentSpeed;
    private boolean stuck;
    private Vector2D velocity;
    private int strong;
    private CollisionMode collisionMode;

    // ==================== CONSTRUCTORS ==================== //
    public Ball() {
        super(AssetManager.IMAGE_OF_BALL, GameVar.INIT_BALL_X, GameVar.INIT_BALL_Y);
        initDefaults();
    }

    // ==================== INIT DEFAULTS ==================== //
    private void initDefaults() {
        this.currentSpeed = baseSpeed;
        this.stuck = true;
        this.velocity = new Vector2D(0, -1);
        this.strong = GameVar.STRONG_OF_BALL;
        this.collisionMode = CollisionMode.NORMAL;
    }

    // ==================== ALIGN WITH PADDLE ==================== //
    private void alignWithPaddle(PaddleLike paddle, double offsetY, double lerpFactor) {
        double targetX = paddle.getX() + paddle.getWidth() / 2.0 - getWidth() / 2.0;
        double targetY = paddle.getY() - getHeight() - offsetY;

        if (lerpFactor >= 1.0) {
            x = targetX;
            y = targetY;
        } else {
            x += (targetX - x) * lerpFactor;
            y += (targetY - y) * lerpFactor;
        }

        double minX = paddle.getX();
        double maxX = paddle.getX() + paddle.getWidth() - getWidth();
        if (x < minX) x = minX;
        if (x > maxX) x = maxX;

        setPosition();
    }

    // ==================== UPDATE ==================== //
    public void update(double deltaTime, PaddleLike paddle) {
        if (stuck) {
            alignWithPaddle(paddle, 0, 0.1);
        } else {
            Vector2D step = velocity.normalize().multiply(currentSpeed * deltaTime);
            x += step.x;
            y += step.y;
            setPosition();
        }
    }

    // ==================== STATE CONTROL ==================== //
    public void release() {
        if (stuck) {
            stuck = false;
            velocity = new Vector2D(0, -1);
            if (currentSpeed <= 0) currentSpeed = baseSpeed;
        }
    }

    public void resetState(PaddleLike paddle) {
        stuck = true;
        alignWithPaddle(paddle, 10, 1.0);
        velocity = new Vector2D(0, -1);
        collisionMode = CollisionMode.NORMAL;
        currentSpeed = baseSpeed;
    }

    // ==================== REFLECTION CALCULATIONS ==================== //
    public double calculateHitRatio(PaddleLike paddle) {
        double paddleLeft = paddle.getX();
        double ballCenterX = x + width / 2.0;
        return (ballCenterX - paddleLeft) / paddle.getWidth();
    }

    public double calculateAngle(PaddleLike paddle) {
        double ratio = calculateHitRatio(paddle);
        return Math.toRadians(150 * (1 - ratio) + 30 * ratio);
    }

    public void adjustPositionAbovePaddle(PaddleLike paddle) {
        y = paddle.getY() - height;
        setPosition();
    }

    // ==================== GETTERS / SETTERS ==================== //
    public Vector2D getVelocity() { return velocity; }

    public void setVelocity(double x, double y) {
        velocity = (x == 0 && y == 0)
                ? new Vector2D(0, 0)
                : new Vector2D(x, y).normalize();
    }

    public double getSpeed() { return currentSpeed; }
    public void setSpeed(double speed) { this.currentSpeed = speed; }

    public boolean isStuck() { return stuck; }
    public void stick() { stuck = true; }
    public void launch() { stuck = false; }

    public int getStrong() { return strong; }
    public void setStrong(int strong) { this.strong = strong; }

    public void setCollisionMode(CollisionMode mode) { this.collisionMode = mode; }
    public CollisionMode getCollisionMode() { return collisionMode; }
    public boolean isPiercing() { return collisionMode == CollisionMode.PIERCING; }

    // ==================== RENDER ==================== //
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(AssetManager.IMAGE_OF_BALL, x, y, width, height);
    }

    // ==================== COPY STATE ==================== //
    public void copyState(Ball original) {
        this.image = AssetManager.IMAGE_OF_BALL;

        this.x = original.x;
        this.y = original.y;
        this.width = original.width;
        this.height = original.height;

        Vector2D v = original.getVelocity();
        this.velocity = new Vector2D(v.x, v.y);

        this.currentSpeed = original.getSpeed();
        this.strong = original.getStrong();
        this.collisionMode = original.getCollisionMode();

        this.stuck = false;
        setPosition();
    }
}
