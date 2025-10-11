package com.example.demoarkanoid4.core.brick;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.GameObject;
import com.example.demoarkanoid4.core.ball.BallLike;
import com.example.demoarkanoid4.manager.AssetManager;
import com.example.demoarkanoid4.utils.GameRandom;
import javafx.scene.image.Image;

import static com.example.demoarkanoid4.manager.AssetManager.BRICK_TEXTURES;

public class Brick extends GameObject implements BrickLike {

    protected boolean destroyed;
    protected int health;

    // ==================== CONSTRUCTORS ==================== //
    public Brick(int x, int y) {
        this(randomHealth(), x, y);
    }

    private Brick(int health, int x, int y) {
        super(getImageFromHealth(health), x, y);
        this.health = health;
        this.destroyed = false;
    }

    protected Brick(Image image, int x, int y) {
        super(image, x, y);
        this.destroyed = false;
    }

    // ==================== HELPERS ==================== //
    private static int randomHealth() {
        return GameRandom.nextInt(VARIABLES.MAXHEALTH_OF_BRICKS) + 1;
    }

    private static Image getImageFromHealth(int health) {
        int index = Math.max(0, Math.min(health - 1, BRICK_TEXTURES.length - 1));
        return BRICK_TEXTURES[index];
    }

    // ==================== LOGIC ==================== //
    public void takeDamage(BallLike ball) {
        if (destroyed) return;

        health -= ball.getStrong();
        if (health <= 0) {
            destroy();
        } else {
            setImage(getImageFromHealth(health));
        }
    }

    private void destroy() {
        destroyed = true;
        // có thể thêm hiệu ứng nổ hoặc spawn PowerUp tại đây
    }

    public boolean isDestroyed() { return destroyed; }
    public int getHealth() { return health; }

    @Override
    public String toString() {
        return String.format("Brick[x=%d, y=%d, health=%d, destroyed=%s]",
                (int) getX(), (int) getY(), health, destroyed);
    }
}
