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
        for (int i = 0; i < VARIABLES.N_OF_WALLS_LEFT_RIGHT; i++) {
            walls.add(new Wall(Wall.Side.LEFT, 0, i * VARIABLES.HEIGHT_OF_WALLS, VARIABLES.WIDTH_OF_WALLS, VARIABLES.HEIGHT_OF_WALLS));
            walls.add(new Wall(Wall.Side.RIGHT, VARIABLES.WIDTH - VARIABLES.WIDTH_OF_WALLS, i * VARIABLES.HEIGHT_OF_WALLS, VARIABLES.WIDTH_OF_WALLS, VARIABLES.HEIGHT_OF_WALLS));
        }
        for (int i = 0; i < VARIABLES.N_OF_WALLS_TOP; i++) {
            walls.add(new Wall(Wall.Side.TOP, i * VARIABLES.WIDTH_OF_WALLS, 0, VARIABLES.WIDTH_OF_WALLS, VARIABLES.HEIGHT_OF_WALLS));
        }
    }

    public void render(GraphicsContext gc) {
        for (Wall wall : walls){
            gc.drawImage(wall.getImage(), wall.getX(), wall.getY(),
                    wall.getWidth(), wall.getHeight());
        }
    }

    public List<Wall>  getWalls() {return walls;}
}
