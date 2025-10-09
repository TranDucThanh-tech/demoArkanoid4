package com.example.demoarkanoid4.manager;

import com.example.demoarkanoid4.activeEffect.*;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.VARIABLES;

import java.util.*;

public class EffectManager {
    private final List<ActiveEffect> activeEffects = new ArrayList<>();

    // Khi ăn power-up:
    public void addEffect(char type, BallManager ballManager, PaddleManager paddle) {
        // Nếu đã có hiệu ứng cùng loại thì chỉ gia hạn thời gian
        for (ActiveEffect e : activeEffects) {
            if (e.getType() == type) {
                e.refreshDuration(6.0);
                return;
            }
        }

        // Tạo và kích hoạt hiệu ứng mới
        Effect newEffect = createEffect(type);
        if (newEffect != null) {
            ActiveEffect active = new ActiveEffect(newEffect, 6.0);
            active.activate(ballManager, paddle);//  kích hoạt
            activeEffects.add(active);
        }
    }

    public void update(BallManager balls, PaddleManager paddle) {
        Iterator<ActiveEffect> it = activeEffects.iterator();
        while (it.hasNext()) {
            ActiveEffect e = it.next();
            if (e.isExpired()) {
                e.deactivate(balls, paddle); //  hủy tác dụng
                it.remove();
            }
        }
    }

    private Effect createEffect(char type) {
        switch (type) {
            case VARIABLES.FASTER: return new Faster();
            case VARIABLES.BIGGERPADDLE: return new BiggerPaddle();
            case VARIABLES.MULTIBALL: return new MultiBall();
            case VARIABLES.STRONGER: return new Stronger();
            default: return null;
        }
    }
}

