package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.manager.PaddleManager;

import java.util.ArrayList;
import java.util.List;

public class Stronger implements Effect{
    public char getType() {
        return VARIABLES.STRONGER;
    }
    public void apply(BallManager ballManager, PaddleManager paddle) {
        for (Ball ball : ballManager.getBalls()) {
            ball.setStrong(ball.getStrong() * VARIABLES.BUFF);
        }
    }
    public void revert(BallManager ballManager, PaddleManager paddle){
        for (Ball ball : ballManager.getBalls()) {
            ball.setStrong(ball.getStrong() / VARIABLES.BUFF);
        }
    }
}
