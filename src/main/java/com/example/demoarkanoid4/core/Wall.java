package com.example.demoarkanoid4.core;

import javafx.scene.canvas.GraphicsContext;

public class Wall extends GameObject{
    public enum Side { LEFT, RIGHT, TOP }

    private final Side side;

    public Wall(Side side, double x, double y, double width, double height) {
        super("/images/Wall.png", x, y); // optional: could just use a colored rect
        this.width = width;
        this.height = height;
        this.side = side;
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }
    public void render(GraphicsContext gc){
        gc.drawImage(getImage(), getX(), getY(), getWidth(), getHeight());
    }
    public Side getSide() {
        return side;
    }
}
