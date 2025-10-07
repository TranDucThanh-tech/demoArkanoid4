package com.example.demoarkanoid4.manager;
import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.PowerUp;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.brick.Brick;
import com.example.demoarkanoid4.core.brick.BrickLike;
import com.example.demoarkanoid4.utils.GameRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PowerUpManager {
    private final List<PowerUp> powerUps = new ArrayList<>();

    public void update(double deltaTime) {
        for (PowerUp powerUp : powerUps){
            powerUp.update(deltaTime);
        }
    }

    public void trySpawnPowerUp(BrickLike brick) {
        if (GameRandom.nextDouble() < VARIABLES.DROP_RATE) {
            char type = getRandomType();
            PowerUp powerUp = new PowerUp(type, VARIABLES.IMAGE_OF_POWERUP[(int)type]);
            powerUps.add(powerUp);
        }
        powerUps.removeIf(p -> !p.isVisible());
    }

    private char getRandomType() {
        int n = GameRandom.nextInt(4); // 0 -> 3
        return (char) ('0' + n);
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }
}
