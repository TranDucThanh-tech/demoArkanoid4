package com.example.demoarkanoid4.model.core.brick;

import com.example.demoarkanoid4.model.core.ball.BallLike;
import com.example.demoarkanoid4.controller.system.AssetManager;

public class SteelBrick extends Brick implements BrickLike {

    public SteelBrick(int x, int y) {
        super(AssetManager.STEEL_TEXTURE, x, y);
    }

    @Override
    public void takeDamage(BallLike ball) {
        return;// Return the sound for a steel hit
    }
}