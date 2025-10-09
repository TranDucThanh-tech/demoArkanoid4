package com.example.demoarkanoid4.core.brick;

import com.example.demoarkanoid4.core.ball.BallLike;
import javafx.scene.image.Image;

import java.util.Objects;

public class ExplosionBrick extends Brick implements BrickLike{
    private static final Image EXPLOSION_TEXTURE;

    static {
        EXPLOSION_TEXTURE = new Image(Objects.requireNonNull(
                SteelBrick.class.getResourceAsStream("/images/ExplosionBricks.png"),
                "Missing image file: /images/ExplosionBricks.png"));
    }

    public ExplosionBrick(int x, int y) {
        super(EXPLOSION_TEXTURE, x, y);
        this.health = 1;
    }

    @Override
    public void takeDamage(BallLike ball) {
        if (isDestroyed()) return;
        destroyed = true;
    }
}
