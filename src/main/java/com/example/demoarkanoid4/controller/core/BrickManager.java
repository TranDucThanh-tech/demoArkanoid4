package com.example.demoarkanoid4.controller.core;

import com.example.demoarkanoid4.model.core.brick.Brick;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class BrickManager {
    private Brick[] bricks;
    //private final List<Brick> bricksFromMap = new ArrayList<>();
    public void generateLevel() {

    }
    /*
    public void update() {
        Iterator<Brick> iterator = bricksFromMap.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            if (brick.isDestroyed()) {
                iterator.remove();
            }
        }
    } */

    public void render(GraphicsContext gc) {
        for (Brick b : bricks) {
            if (b.isDestroyed()) continue;
            b.render(gc);
        }
    }

    public Brick[] getBricks() {
        return bricks;
    }
    /*
    public List<Brick> getBricksFromMap(){
        return bricksFromMap;
    }

    public boolean isAllDestroyed() {
        return bricksFromMap.isEmpty();
    }
*/
    public void setBricks(List<Brick> newBricks) {
        bricks = newBricks.toArray(new Brick[0]);
    }

}
