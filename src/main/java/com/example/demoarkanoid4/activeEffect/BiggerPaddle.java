package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.manager.PaddleManager;

public class BiggerPaddle implements Effect {

    @Override
    public char getType() {
        return VARIABLES.BIGGERPADDLE;
    }

    @Override
    public void apply(BallManager ballManager, PaddleManager paddle) {
        if (paddle == null) return;
        paddle.getPaddle().setScale(2, 2);
    }

    @Override
    public void revert(BallManager ballManager, PaddleManager paddle) {
        if (paddle == null) return;
        paddle.getPaddle().setScale(1, 1);
    }
}
