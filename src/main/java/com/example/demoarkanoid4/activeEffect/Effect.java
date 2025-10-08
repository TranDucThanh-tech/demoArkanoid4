package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.manager.PaddleManager;

public interface Effect {
    char getType();
    void apply(BallManager ballManager, PaddleManager paddle);
    void revert(BallManager ballManager, PaddleManager paddle);
}

