package com.example.demoarkanoid4.core.paddle;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class Paddle extends GameObject implements PaddleLike{
    private int direction;          // -1 = left, 0 = stop, 1 = right
    private double speed;
    private final double baseWidth = VARIABLES.WIDTH_OF_PADDLE;
    private final double baseHeight = VARIABLES.HEIGHT_OF_PADDLE;

    public Paddle() {
        super(VARIABLES.IMAGE_OF_PADDLE, VARIABLES.INIT_PADDLE_X, VARIABLES.INIT_PADDLE_Y);
        this.speed = VARIABLES.SPEED_OF_PADDLE;
        resetState();
    }

    public void update(double deltaTime) {
        x += direction * speed * deltaTime;

        // Clamp inside screen
        if (getX() < VARIABLES.HEIGHT_OF_WALLS){
            x = VARIABLES.HEIGHT_OF_WALLS;
        }
        if (getX() > VARIABLES.WIDTH - getWidth() - VARIABLES.WIDTH_OF_WALLS) {
            x = VARIABLES.WIDTH - width- VARIABLES.WIDTH_OF_WALLS;
        }
        setPosition();
    }

    public void resetState() {
        x = VARIABLES.INIT_PADDLE_X;
        y = VARIABLES.INIT_PADDLE_Y;
        setPosition();
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }


    public void setScale(double sx, double sy) {
        double newWidth = baseWidth * sx;
        double newHeight = baseHeight * sy;
        this.width = newWidth;
        this.height = newHeight;
    }
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y, width, height);
    }

}