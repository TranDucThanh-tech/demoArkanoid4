package com.example.demoarkanoid4.core.paddle;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.GameObject;
import com.example.demoarkanoid4.manager.AssetManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Paddle extends GameObject implements PaddleLike {

    // ==================== IMAGE (TỪ ASSET MANAGER) ==================== //
    private int direction;          // -1 = left, 0 = stop, 1 = right
    private double speed;
    private final double baseWidth = VARIABLES.WIDTH_OF_PADDLE;
    private final double baseHeight = VARIABLES.HEIGHT_OF_PADDLE;

    // ==================== CONSTRUCTOR ==================== //
    public Paddle() {
        super(AssetManager.IMAGE_OF_PADDLE, VARIABLES.INIT_PADDLE_X, VARIABLES.INIT_PADDLE_Y);
        this.speed = VARIABLES.SPEED_OF_PADDLE;
        resetState();
    }

    // ==================== UPDATE ==================== //
    public void update(double deltaTime) {
        x += direction * speed * deltaTime;

        // Giới hạn trong màn hình
        if (x < VARIABLES.HEIGHT_OF_WALLS) {
            x = VARIABLES.HEIGHT_OF_WALLS;
        }
        if (x > VARIABLES.WIDTH - getWidth() - VARIABLES.WIDTH_OF_WALLS) {
            x = VARIABLES.WIDTH - getWidth() - VARIABLES.WIDTH_OF_WALLS;
        }

        setPosition();
    }

    // ==================== STATE ==================== //
    public void resetState() {
        x = VARIABLES.INIT_PADDLE_X;
        y = VARIABLES.INIT_PADDLE_Y;
        width = baseWidth;
        height = baseHeight;
        direction = 0;
        setPosition();
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public void setScale(double sx, double sy) {
        this.width = baseWidth * sx;
        this.height = baseHeight * sy;
    }

    // ==================== RENDER ==================== //
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(AssetManager.IMAGE_OF_PADDLE, x, y, width, height);
    }
}
