package com.example.demoarkanoid4.core.brick;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.GameObject;
import com.example.demoarkanoid4.core.ball.BallLike;
import com.example.demoarkanoid4.utils.GameRandom;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Brick extends GameObject implements BrickLike{

    private boolean destroyed;
    private int health;

    private static final int MAX_HEALTH = VARIABLES.MAXHEALTH_OF_BRICKS;
    private static final List<Image> BRICK_TEXTURES = new ArrayList<>(MAX_HEALTH);

    // Load images once
    static {
        for (int i = 1; i <= MAX_HEALTH; i++) {
            String path = (i == 1)
                    ? "/images/Bricks.png"
                    : "/images/Bricks" + i + ".png";
            BRICK_TEXTURES.add(new Image(
                    Objects.requireNonNull(Brick.class.getResourceAsStream(path),
                            "Missing image file: " + path)
            ));
        }
    }

    public Brick(int x, int y) {
        this(randomHealth(), x, y);
    }

    private Brick(int health, int x, int y) {
        super(getImageFromHealth(health), x, y);
        this.health = health;
        this.destroyed = false;
    }

    // Helper: use ThreadLocalRandom if multithreaded
    private static int randomHealth() {
        return GameRandom.nextInt(MAX_HEALTH) + 1;
    }
    private static Image getImageFromHealth(int health) {
        int index = Math.max(0, Math.min(health - 1, BRICK_TEXTURES.size() - 1));
        return BRICK_TEXTURES.get(index);
    }

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
    }

    public boolean isDestroyed() { return destroyed; }
    public int getHealth() { return health; }

    @Override
    public String toString() {
        return String.format("Brick[x=%d, y=%d, health=%d, destroyed=%s]",
                (int)getX(), (int)getY(), health, destroyed);
    }
}