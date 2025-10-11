package com.example.demoarkanoid4.model.activeEffect;

import com.example.demoarkanoid4.controller.core.BallManager;
import com.example.demoarkanoid4.controller.core.PaddleManager;

public interface Effect {
    char getType();
    void apply(BallManager ballManager, PaddleManager paddle);
    void revert(BallManager ballManager, PaddleManager paddle);
}

