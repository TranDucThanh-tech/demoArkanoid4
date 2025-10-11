package com.example.demoarkanoid4.model.core.brick;

import com.example.demoarkanoid4.model.core.ball.BallLike;
import com.example.demoarkanoid4.controller.system.AssetManager;

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
