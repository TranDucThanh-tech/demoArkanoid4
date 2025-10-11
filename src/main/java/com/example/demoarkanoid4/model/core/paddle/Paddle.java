package com.example.demoarkanoid4.model.core.paddle;

import com.example.demoarkanoid4.model.utils.GameVar;
import com.example.demoarkanoid4.model.utils.GlobalVar;
import com.example.demoarkanoid4.model.core.GameObject;
import com.example.demoarkanoid4.controller.system.AssetManager;
import javafx.scene.canvas.GraphicsContext;

public class Paddle extends GameObject implements PaddleLike {

    // ==================== IMAGE (TỪ ASSET MANAGER) ==================== //
    private int direction;          // -1 = left, 0 = stop, 1 = right
    private double speed;
    private final double baseWidth = GameVar.WIDTH_OF_PADDLE;
    private final double baseHeight = GameVar.HEIGHT_OF_PADDLE;

    // ==================== CONSTRUCTOR ==================== //
    public Paddle() {
        super(AssetManager.IMAGE_OF_PADDLE, GameVar.INIT_PADDLE_X, GameVar.INIT_PADDLE_Y);
        this.speed = GameVar.SPEED_OF_PADDLE;
        resetState();
    }

    // ==================== UPDATE ==================== //
    public void update(double deltaTime) {
        x += direction * speed * deltaTime;

        // Giới hạn trong màn hình
        if (x < GameVar.HEIGHT_OF_WALLS) {
            x = GameVar.HEIGHT_OF_WALLS;
        }
        if (x > GlobalVar.WIDTH - getWidth() - GameVar.WIDTH_OF_WALLS) {
            x = GlobalVar.WIDTH - getWidth() - GameVar.WIDTH_OF_WALLS;
        }

        setPosition();
    }

    // ==================== STATE ==================== //
    public void resetState() {
        x = GameVar.INIT_PADDLE_X;
        y = GameVar.INIT_PADDLE_Y;
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
