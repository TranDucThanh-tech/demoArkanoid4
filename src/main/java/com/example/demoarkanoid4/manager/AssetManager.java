package com.example.demoarkanoid4.manager;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.brick.Brick;
import com.example.demoarkanoid4.core.brick.SteelBrick;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * AssetManager
 * Quản lý và preload toàn bộ tài nguyên hình ảnh cho game Arkanoid.
 *
 * Gọi AssetManager.preloadImage() một lần duy nhất khi khởi động game.
 */
public class AssetManager {

    // Ảnh cơ bản
    public static Image IMAGE_OF_BALL;
    public static Image IMAGE_OF_PADDLE;
    public static Image IMAGE_OF_WALL;

    public static Image EXPLOSION_TEXTURE;
    public static Image STEEL_TEXTURE;

    public static Image[] BRICK_TEXTURES = new Image[VARIABLES.MAXHEALTH_OF_BRICKS];
    public static final Image[] POWERUP_IMAGES = new Image[VARIABLES.N_OF_POWERUP];

    /**
     * Tải sẵn các texture, powerup, effect vào bộ nhớ (preload)
     * để tránh giật lag khi load giữa game
     */
    public static void preloadAssets() {
        preloadImage();
        //VARIABLES.preLoadSounds();
        //VARIABLES.preLoadPowerUps();
    }

    /**
     * Nạp tất cả hình ảnh vào bộ nhớ (preload).
     * Gọi một lần duy nhất ở GameManager hoặc Main.
     */
    private static void preloadImage() {
        IMAGE_OF_BALL = loadImage("/images/Ball.png");
        IMAGE_OF_PADDLE = loadImage("/images/Paddle.png");
        EXPLOSION_TEXTURE = loadImage("/images/ExplosionBricks.png");
        STEEL_TEXTURE = loadImage("/images/SteelBricks.png");
        IMAGE_OF_WALL = loadImage("/images/Wall.png");

        // Tải texture brick theo độ bền
        for (int i = 0; i < VARIABLES.MAXHEALTH_OF_BRICKS; i++) {
            String path = (i == 0)
                    ? "/images/Bricks.png"
                    : "/images/Bricks" + (i + 1) + ".png";
            BRICK_TEXTURES[i] = loadImage(path);
        }

        // Power-ups
        POWERUP_IMAGES[0] = loadImage("/images/Faster.png");
        POWERUP_IMAGES[1] = loadImage("/images/BiggerPaddle.png");
        POWERUP_IMAGES[2] = loadImage("/images/MultiBall.png");
        POWERUP_IMAGES[3] = loadImage("/images/Stronger.png");
    }

    /**
     * Hàm tiện ích để load ảnh an toàn, có kiểm tra null.
     */
    private static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(
                SteelBrick.class.getResourceAsStream(path),
                "Missing image file: " + path));
    }
}
