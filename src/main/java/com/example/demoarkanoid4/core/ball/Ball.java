package com.example.demoarkanoid4.core.ball;

import com.example.demoarkanoid4.core.GameObject;
import com.example.demoarkanoid4.vector2D.Vector2D;
import com.example.demoarkanoid4.core.paddle.PaddleLike;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ball extends GameObject implements BallLike, PaddleLike{
    private final double baseSpeed;
    private double currentSpeed;
    private boolean stuck;
    private Vector2D velocity;
    private boolean accelerated;

    public Ball(String image, double x, double y, double speed) {
        super(image, x, y);
        this.baseSpeed = speed;
        this.currentSpeed = speed;
        this.stuck = true;
        this.velocity = new Vector2D(0, -1);
    }
    public void applyAcceleration(double multiplier) {
        accelerated = true;
        currentSpeed = baseSpeed * multiplier;
    }
    public void resetSpeed() {
        accelerated = false;
        currentSpeed = baseSpeed;
    }
    public void update(double deltaTime, PaddleLike paddle) {
        if (stuck){
            setX(paddle.getX() + paddle.getWidth() / 2.0 - getWidth() / 2.0);
            setY(paddle.getY() - getHeight());
        }
        else {
            Vector2D step = velocity.normalize().multiply(currentSpeed * deltaTime);
            setX(getX() + step.x);
            setY(getY() + step.y);
        }
        setPosition();
    }
    public void release() {
        if (stuck) {
            stuck = false;
            velocity = new Vector2D(0, -1);
        }
    }

    public double calculateHitRatio(PaddleLike paddle) {
        double paddleLeft = paddle.getBounds().getMinX();
        double ballCenterX = getBounds().getMinX() + getWidth() / 2.0;
        return (ballCenterX - paddleLeft) / paddle.getWidth();
    }

    public double calculateAngle(PaddleLike paddle){
        return Math.toRadians(150 * (1 - calculateHitRatio(paddle)) + 30 * calculateHitRatio(paddle));
    }

    public void adjustPositionAbovePaddle(PaddleLike paddle) {
        setY(paddle.getBounds().getMinY() - getHeight());
        this.setPosition();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(),
                this.getWidth(), this.getHeight());
    }

    public void stick() { stuck = true; }
    public void launch() { stuck = false;}
    public boolean isStuck() {return stuck; }
    public double getSpeed() { return currentSpeed; }
    public void setSpeed(double speed) { this.currentSpeed = speed; }
    public Vector2D getVelocity() { return velocity; }
    public void setVelocity(Vector2D v) { velocity = v.normalize(); }
}

