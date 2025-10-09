package com.example.demoarkanoid4.core.brick;

import com.example.demoarkanoid4.core.ball.BallLike;
import javafx.scene.image.Image;

import java.util.Objects;

public class SteelBrick extends Brick implements BrickLike {
    private static final Image STEEL_TEXTURE;

    static {
        STEEL_TEXTURE = new Image(Objects.requireNonNull(
                SteelBrick.class.getResourceAsStream("/images/SteelBricks.png"),
                "Missing image file: /images/SteelBricks.png"));
    }

    public SteelBrick(int x, int y) {
        super(STEEL_TEXTURE, x, y);
    }

    @Override
    public void takeDamage(BallLike ball) {
        return;// Return the sound for a steel hit
    }
}