package com.example.demoarkanoid4.core.ball;

import javafx.geometry.Bounds;

public interface BallLike {
    double getX();
    double getY();
    double getWidth();
    double getHeight();
    Bounds getBounds();
    int getStrong();
}
