package com.example.demoarkanoid4.core.paddle;

import javafx.geometry.Bounds;

public interface PaddleLike {
    double getX();
    double getY();
    double getWidth();
    double getHeight();
    Bounds getBounds();
}
