package com.example.demoarkanoid4.core.ball;

import com.example.demoarkanoid4.VARIABLES;
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

    public Ball() {
        super(VARIABLES.IMAGE_OF_BALL, VARIABLES.INIT_BALL_X, VARIABLES.INIT_BALL_Y);
        this.baseSpeed = VARIABLES.SPEED_OF_BALL;
        this.currentSpeed = VARIABLES.SPEED_OF_BALL;
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
        if (y >= VARIABLES.HEIGHT) {
            resetState(paddle);
        }
        if (isStuck()){
            x = paddle.getX() + paddle.getWidth() / 2.0 - width / 2.0;
            y = paddle.getY() - height;
            setPosition();
        }
        else {
            Vector2D step = velocity.normalize().multiply(currentSpeed * deltaTime);
            setX(getX() + step.x);
            setY(getY() + step.y);
        }
    }
    // Overload: for compatibility with old code, but should pass paddle!
    public void release() {
        if(isStuck()) {
            launch();
            velocity = new Vector2D(0, -1);
            if (currentSpeed <= 0) currentSpeed = baseSpeed;
        }
    }

    public void resetState(PaddleLike paddle) {
        stick();
        x = paddle.getX() + paddle.getWidth() / 2.0 - width / 2.0;
        y = paddle.getY() - height;
        setPosition();
        velocity = new Vector2D(0, -1);
        //activeEffectList.clear();
    }

    public double calculateHitRatio(PaddleLike paddle) {
        double paddleLeft = paddle.getX();
        double ballCenterX = x + width / 2.0;
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

    public void setVelocity(double x, double y) {
        if (x == 0 && y == 0) {
            velocity.x = 0;
            velocity.y = 0;
        } else {
            velocity.x = x;
            velocity.y = y;
            velocity = velocity.normalize();
        }
    }

    public double getRadius(){
        return getHeight()/2;
    }
    public void stick() { stuck = true; }
    public void launch() { stuck = false;}
    public boolean isStuck() {return stuck; }
    public double getSpeed() { return currentSpeed; }
    public void setSpeed(double speed) { this.currentSpeed = speed; }
    public Vector2D getVelocity() { return velocity; }

}