package com.example.demoarkanoid4.model.core.brick;

import javafx.geometry.Bounds;

public interface BrickLike {
        double getX();
        double getY();
        double getWidth();
        double getHeight();
        Bounds getBounds();
}
