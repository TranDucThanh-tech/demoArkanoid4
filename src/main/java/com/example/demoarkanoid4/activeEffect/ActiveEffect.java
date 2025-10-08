package com.example.demoarkanoid4.activeEffect;

import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.manager.BallManager;
import com.example.demoarkanoid4.manager.PaddleManager;

public class ActiveEffect {
    private final Effect effect;
    private long expireAt; // mốc thời gian hết hạn (ms)

    public ActiveEffect(Effect effect, double durationSeconds) {
        this.effect = effect;
        this.expireAt = System.currentTimeMillis() + (long)(durationSeconds * 1000);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expireAt;
    }

    public void deactivate(BallManager balls, PaddleManager paddle) {
        effect.revert(balls, paddle);
    }

    public void activate(BallManager balls, PaddleManager paddle) {
        effect.apply(balls, paddle);
    }

    public char getType() {
        return effect.getType();
    }
    public Effect getEffect() {
        return effect;
    }

    public void refreshDuration(double durationSeconds) {
        // cập nhật lại expireAt từ thời điểm hiện tại
        long now = System.currentTimeMillis();
        this.expireAt = now + (long)(durationSeconds * 1000);
    }
}

