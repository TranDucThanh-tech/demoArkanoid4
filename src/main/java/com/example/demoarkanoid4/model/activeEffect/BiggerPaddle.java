package com.example.demoarkanoid4.model.activeEffect;

import com.example.demoarkanoid4.model.utils.GameVar;
import com.example.demoarkanoid4.controller.core.BallManager;
import com.example.demoarkanoid4.controller.core.PaddleManager;

public class BiggerPaddle implements Effect {

    @Override
    public char getType() {
        return GameVar.BIGGERPADDLE;
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
