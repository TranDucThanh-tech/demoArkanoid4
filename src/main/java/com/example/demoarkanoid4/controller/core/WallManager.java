package com.example.demoarkanoid4.controller.core;
import com.example.demoarkanoid4.model.core.Wall;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class WallManager {
    //private final List<Wall> wallsFromMap = new ArrayList<>();
    private Wall[] walls;

    public void generateLevel() {
        
    }

    public void render(GraphicsContext gc) {
        for (Wall wall : walls){
            gc.drawImage(wall.getImage(), wall.getX(), wall.getY(),
                    wall.getWidth(), wall.getHeight());
        }
    }

    //public List<Wall>  getWalls() {return wallsFromMap;}

    public Wall[] getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> newWalls) {
        walls = newWalls.toArray(new Wall[0]);
    }
}
