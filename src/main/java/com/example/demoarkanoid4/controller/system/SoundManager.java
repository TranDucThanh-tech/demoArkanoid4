package com.example.demoarkanoid4.controller.system;

public class SoundManager {

    private static SoundManager instance;

    private long nextPaddleSoundTime = 0;         // thời điểm có thể phát tiếp
    private final long paddleSoundCooldown = 200; // ms, thời gian chờ giữa 2 lần phát

    private SoundManager() {
        // constructor riêng tư để đảm bảo singleton
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Phát âm thanh khi bóng chạm paddle.
     * Có cooldown để tránh spam âm thanh.
     */
    public void playPaddleHit() {
        long now = System.currentTimeMillis();
        if (now > nextPaddleSoundTime) {
            playSound("paddle_hit");           // hàm chung phát âm thanh
            nextPaddleSoundTime = now + paddleSoundCooldown;
        }
    }

    /**
     * Hàm chung phát âm thanh theo tên file/ID.
     */
    public void playSound(String soundName) {
        // TODO: triển khai logic phát âm thanh
        // Ví dụ: load file âm thanh, trigger AudioClip, MediaPlayer, v.v.
        System.out.println("Playing sound: " + soundName); // placeholder
    }
}
