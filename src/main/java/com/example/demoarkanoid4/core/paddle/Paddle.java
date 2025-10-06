package com.example.demoarkanoid4.core.paddle;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class Paddle extends GameObject implements PaddleLike{
    private int direction;          // -1 = left, 0 = stop, 1 = right
    private double speed;

    public Paddle() {
        super(VARIABLES.IMAGE_OF_PADDLE, VARIABLES.INIT_PADDLE_X, VARIABLES.INIT_PADDLE_Y);
        this.speed = VARIABLES.SPEED_OF_PADDLE;
        resetState();
    }

    public void update(double deltaTime) {
        setX(getX() + direction * speed * deltaTime);

        // Clamp inside screen
        if (getX() < VARIABLES.HEIGHT_OF_WALLS){
            setX(VARIABLES.HEIGHT_OF_WALLS);
        }
        if (getX() > VARIABLES.WIDTH - getWidth() - VARIABLES.WIDTH_OF_WALLS) {
            setX(VARIABLES.WIDTH - getWidth() - VARIABLES.WIDTH_OF_WALLS);
        }
        setPosition();
    }

    public void resetState() {
        setX(VARIABLES.INIT_PADDLE_X);
        setY(VARIABLES.INIT_PADDLE_Y);
        setPosition();
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }
    public void render(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(),
                this.getWidth(), this.getHeight());
    }
    // Optionally override getBounds() if you want rounded paddle, else use parent's
}