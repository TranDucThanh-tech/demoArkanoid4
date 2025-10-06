package com.example.demoarkanoid4.core.ball;

import com.example.demoarkanoid4.core.GameObject;
import com.example.demoarkanoid4.vector2D.Vector2D;
import com.example.demoarkanoid4.core.paddle.PaddleLike;
import javafx.scene.canvas.GraphicsContext;

public class Ball extends GameObject implements BallLike {
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
        this.velocity = new Vector2D(0, -1); // Upwards
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
        if (isStuck()){
            setX(paddle.getX() + paddle.getWidth() / 2.0 - getWidth() / 2.0);
            setY(paddle.getY() - getHeight());
            setPosition();
        }
        else {
            Vector2D step = velocity.normalize().multiply(currentSpeed * deltaTime);
            setX(getX() + step.x);
            setY(getY() + step.y);
        }
    }
    public void release(PaddleLike paddle) {
        if (isStuck()) {
            launch();
            // Set velocity based on where the ball is on the paddle
            double angle = calculateAngle(paddle);
            velocity = new Vector2D(Math.cos(angle), -Math.sin(angle));
            // Ensure speed positive
            if (currentSpeed <= 0) currentSpeed = baseSpeed;
            System.out.printf("Ball.release(paddle): stuck=%b vel=(%.3f,%.3f) speed=%.3f%n",
                    stuck, velocity.x, velocity.y, currentSpeed);
        }
    }
    // Overload: for compatibility with old code, but should pass paddle!
    public void release() {
        stick();
        velocity = new Vector2D(0, -1);
        if (currentSpeed <= 0) currentSpeed = baseSpeed;
    }

    public double calculateHitRatio(PaddleLike paddle) {
        double paddleLeft = paddle.getX();
        double ballCenterX = getX() + getWidth() / 2.0;
        return (ballCenterX - paddleLeft) / paddle.getWidth();
    }

    public double calculateAngle(PaddleLike paddle){
        // Returns angle in radians between 30° (right edge) and 150° (left edge)
        // 0 = left, 1 = right
        double ratio = calculateHitRatio(paddle);
        return Math.toRadians(150 * (1 - ratio) + 30 * ratio);
    }

    public void adjustPositionAbovePaddle(PaddleLike paddle) {
        setY(paddle.getY() - getHeight());
        this.setPosition();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(),
                this.getWidth(), this.getHeight());
    }

    public void setVelocity(Vector2D v) {
        if (v == null || (v.x == 0 && v.y == 0)) {
            velocity = new Vector2D(0, -1);
        } else {
            velocity = v.normalize();
        }
    }

    public void stick() { stuck = true; }
    public void launch() { stuck = false;}
    public boolean isStuck() {return stuck; }
    public double getSpeed() { return currentSpeed; }
    public void setSpeed(double speed) { this.currentSpeed = speed; }
    public Vector2D getVelocity() { return velocity; }

}