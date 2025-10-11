package com.example.demoarkanoid4.controller.effect;

import com.example.demoarkanoid4.controller.core.BallManager;
import com.example.demoarkanoid4.controller.core.PaddleManager;
import com.example.demoarkanoid4.model.activeEffect.*;
import com.example.demoarkanoid4.model.utils.GameVar;

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
            case GameVar.FASTER: return new Faster();
            case GameVar.BIGGERPADDLE: return new BiggerPaddle();
            case GameVar.MULTIBALL: return new MultiBall();
            case GameVar.STRONGER: return new Stronger();
            default: return null;
        }
    }

    public void clear(){
        activeEffects.clear();
    }
}

