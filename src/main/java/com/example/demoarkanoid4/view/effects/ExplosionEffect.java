package com.example.demoarkanoid4.view.effects;

import com.example.demoarkanoid4.view.animation.Animation;

/**
 * ExplosionEffect
 * Hiệu ứng nổ (Explosion), sử dụng animation truyền vào từ EffectLoader.
 * Quản lý vòng đời hiệu ứng và (tùy chọn) phát âm thanh khi kích hoạt.
 */
public class ExplosionEffect extends AnimatedEffect {
    // Constructor
    public ExplosionEffect(double x, double y, double durationSeconds, Animation animation) {
        super(x, y, durationSeconds, animation);
    }

    // Override activate and deactivate methods for update sounds
    @Override
    public void activate() {
        super.activate();
        // Play explosion sound
    }

    // (Optional) If you want explosion to play a sound when reset:
    @Override
    public void reset(double x, double y, double durationSeconds) {
        super.reset(x, y, durationSeconds);

        // Play explosion sound effect (optional)
        // SoundManager.getInstance().playSound("explosion_hit");
    }

    // (Optional) reset with a new animation
    // public void reset(double x, double y, double durationSeconds, Animation animation) {
    //     super.reset(x, y, durationSeconds, animation);

    //     // Play sound again if needed
    //     // SoundManager.getInstance().playSound("explosion_hit");
    // }
}
