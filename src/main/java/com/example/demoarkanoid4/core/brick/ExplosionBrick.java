package com.example.demoarkanoid4.core.brick;

import com.example.demoarkanoid4.core.ball.BallLike;
import com.example.demoarkanoid4.manager.AssetManager;
import javafx.scene.image.Image;

import java.util.Objects;

public class ExplosionBrick extends Brick implements BrickLike{

    public ExplosionBrick(int x, int y) {
        super(AssetManager.EXPLOSION_TEXTURE, x, y);
        this.health = 1;
    }

    @Override
    public void takeDamage(BallLike ball) {
        if (isDestroyed()) return;
        destroyed = true;
    }
}
