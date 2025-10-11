package com.example.demoarkanoid4.core.brick;

import com.example.demoarkanoid4.core.ball.BallLike;
import com.example.demoarkanoid4.manager.AssetManager;
import javafx.scene.image.Image;

import java.util.Objects;

public class SteelBrick extends Brick implements BrickLike {

    public SteelBrick(int x, int y) {
        super(AssetManager.STEEL_TEXTURE, x, y);
    }

    @Override
    public void takeDamage(BallLike ball) {
        return;// Return the sound for a steel hit
    }
}