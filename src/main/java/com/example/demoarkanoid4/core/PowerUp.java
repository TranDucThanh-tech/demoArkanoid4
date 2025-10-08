package com.example.demoarkanoid4.core;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.brick.BrickLike;
import javafx.scene.canvas.GraphicsContext;

public class PowerUp extends GameObject{
    private final double speed = VARIABLES.SPEED_OF_POWERUP;
    private boolean visible;
    private char type;

    public PowerUp(char type, String image){
        super(image, 0, 0);
        this.type = type;
        this.visible = false;
    }

    public void dropFrom(BrickLike brick) {
        setX(brick.getX());
        setY(brick.getY());
        this.setPosition();
        this.visible = true;
    }
    public void update(double deltaTime) {
        if (visible) {
            setY(y + speed * deltaTime);
            setPosition();

            if (y > VARIABLES.HEIGHT) {
                visible = false;
            }
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public char getType() {
        return type;
    }
}
