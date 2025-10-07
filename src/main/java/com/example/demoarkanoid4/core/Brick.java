package com.example.demoarkanoid4.core;

import com.example.demoarkanoid4.VARIABLES;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Brick extends GameObject {

    private boolean destroyed;
    private int health;

    private static final int MAX_HEALTH = VARIABLES.MAXHEALTH_OF_BRICKS;
    private static final List<Image> BRICK_TEXTURES = new ArrayList<>(); // [1HP, 2HP, ..., MAX_HP]

    // --- Tải ảnh một lần duy nhất ---
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

    // --- Constructor ---
    public Brick(int x, int y) {
        this(randomHealth(), x, y);
    }

    private Brick(int health, int x, int y) {
        super(getImageFromHealth(health), x, y);
        this.health = health;
        this.destroyed = false;
    }

    // --- Static helpers ---
    private static final Random RANDOM = new Random();
    private static int randomHealth() {
        return RANDOM.nextInt(MAX_HEALTH) + 1;
    }
    private static Image getImageFromHealth(int health) {
        int index = Math.max(0, Math.min(health - 1, BRICK_TEXTURES.size() - 1));
        return BRICK_TEXTURES.get(index);
    }

    // --- Gameplay logic ---
    public void takeDamage() {
        if (destroyed) return;

        health--;
        if (health <= 0) {
            destroy();
        } else {
            updateImage();
        }
    }

    private void updateImage() {
        setImage(getImageFromHealth(health));
    }

    private void destroy() {
        destroyed = true;
    }

    // --- Getters ---
    public boolean isDestroyed() {
        return destroyed;
    }

    public int getHealth() {
        return health;
    }

    // --- Optional: reset or debug ---
    @Override
    public String toString() {
        return String.format("Brick[x=%d, y=%d, health=%d, destroyed=%s]",
                getX(), getY(), health, destroyed);
    }
}
