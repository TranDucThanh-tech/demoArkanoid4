package com.example.demoarkanoid4.core;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.brick.SteelBrick;
import com.example.demoarkanoid4.manager.AssetManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Wall extends GameObject{
    public enum Side { LEFT, RIGHT, TOP }

    private final Side side;

    public Wall(Side side, double x, double y, double width, double height) {
        super(AssetManager.IMAGE_OF_WALL, x, y); // optional: could just use a colored rect
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
