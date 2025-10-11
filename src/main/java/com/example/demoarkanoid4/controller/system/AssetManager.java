package com.example.demoarkanoid4.controller.system;

import com.example.demoarkanoid4.model.core.brick.SteelBrick;
import com.example.demoarkanoid4.model.utils.GameVar;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * AssetManager
 * Quản lý toàn bộ tài nguyên của game Arkanoid.
 * Chỉ preload() một lần khi khởi động.
 */
public class AssetManager {

    public static Image IMAGE_OF_BALL;
    public static Image IMAGE_OF_PADDLE;
    public static Image IMAGE_OF_WALL;
    public static Image EXPLOSION_TEXTURE;
    public static Image STEEL_TEXTURE;

    // --------- Mảng texture bricks và power-ups ---------
    public static final Image[] BRICK_TEXTURES = new Image[GameVar.MAXHEALTH_OF_BRICKS];
    public static final Image[] POWERUP_IMAGES = new Image[GameVar.N_OF_POWERUP];

    /**
     * Tải sẵn tất cả hình ảnh.
     */
    public static void preloadImages() {
        IMAGE_OF_BALL = loadImage("/images/Ball.png");
        IMAGE_OF_PADDLE = loadImage("/images/Paddle.png");
        EXPLOSION_TEXTURE = loadImage("/images/ExplosionBricks.png");
        STEEL_TEXTURE = loadImage("/images/SteelBricks.png");
        IMAGE_OF_WALL = loadImage("/images/Wall.png");

        // Brick textures theo độ bền
        for (int i = 0; i < GameVar.MAXHEALTH_OF_BRICKS; i++) {
            String path = (i == 0)
                    ? "/images/Bricks.png"
                    : "/images/Bricks" + (i + 1) + ".png";
            BRICK_TEXTURES[i] = loadImage(path);
        }

        // Power-up textures
        POWERUP_IMAGES[0] = loadImage("/images/Faster.png");
        POWERUP_IMAGES[1] = loadImage("/images/BiggerPaddle.png");
        POWERUP_IMAGES[2] = loadImage("/images/MultiBall.png");
        POWERUP_IMAGES[3] = loadImage("/images/Stronger.png");
    }

    private static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(
                SteelBrick.class.getResourceAsStream(path),
                "Missing image file: " + path
        ));
    }

    public static void preloadAssets(){
        preloadImages();
    }
}
