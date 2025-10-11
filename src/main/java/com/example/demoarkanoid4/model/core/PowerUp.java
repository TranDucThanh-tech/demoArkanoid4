package com.example.demoarkanoid4.model.core;

import com.example.demoarkanoid4.model.utils.GameVar;
import com.example.demoarkanoid4.model.core.brick.BrickLike;
import com.example.demoarkanoid4.model.utils.GlobalVar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static com.example.demoarkanoid4.controller.system.AssetManager.POWERUP_IMAGES;

public class PowerUp extends GameObject {
    private final double speed = GameVar.SPEED_OF_POWERUP;
    private boolean visible;
    private char type;

    // ==================== STATIC IMAGES ==================== /

    // ==================== CONSTRUCTOR ==================== //
    public PowerUp(char type) {
        super(selectImageByType(type), 0, 0);
        this.type = GameVar.MULTIBALL;
        this.visible = false;
    }

    // ==================== STATIC HELPER ==================== //
    private static Image selectImageByType(char type) {
        switch (type) {
            case GameVar.FASTER: return POWERUP_IMAGES[0];
            case GameVar.BIGGERPADDLE: return POWERUP_IMAGES[1];
            case GameVar.MULTIBALL: return POWERUP_IMAGES[2];
            case GameVar.STRONGER: return POWERUP_IMAGES[3];
            default:  return POWERUP_IMAGES[0];
        }
    }

    // ==================== LOGIC ==================== //
    public void dropFrom(BrickLike brick) {
        setX(brick.getX());
        setY(brick.getY());
        setPosition();
        visible = true;
    }

    public void update(double deltaTime) {
        if (visible) {
            setY(y + speed * deltaTime);
            setPosition();

            if (y > GlobalVar.HEIGHT) {
                visible = false;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (visible)
            gc.drawImage(image, x, y, width, height);
    }

    // ==================== GETTERS / SETTERS ==================== //
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    public char getType() { return type; }
}
