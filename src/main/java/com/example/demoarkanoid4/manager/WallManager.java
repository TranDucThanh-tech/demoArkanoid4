package com.example.demoarkanoid4.manager;
import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.Brick;
import com.example.demoarkanoid4.core.Wall;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WallManager {
    private final List<Wall> walls = new ArrayList<>();
    public void generateLevel() {
        walls.add(new Wall(Wall.Side.LEFT, 0, 0, VARIABLES.WIDTH_OF_WALLS, VARIABLES.HEIGHT));
        walls.add(new Wall(Wall.Side.RIGHT, VARIABLES.WIDTH - VARIABLES.WIDTH_OF_WALLS, 0, VARIABLES.WIDTH_OF_WALLS, VARIABLES.HEIGHT));
        walls.add(new Wall(Wall.Side.TOP, 0, 0, VARIABLES.WIDTH, VARIABLES.HEIGHT_OF_WALLS));

    }
    public void render(GraphicsContext gc) {
        for (Wall b : walls) {
            gc.drawImage(b.getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }
}
