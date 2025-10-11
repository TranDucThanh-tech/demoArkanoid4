package com.example.demoarkanoid4.view.effects;

public interface UIEffect {
    void draw(javafx.scene.canvas.GraphicsContext gc);
    void update(double deltaTime);
    double duration();

    boolean isActive();
    void activate();
    void deactivate();
}
