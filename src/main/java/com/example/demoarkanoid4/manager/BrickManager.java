package com.example.demoarkanoid4.manager;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.brick.Brick;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrickManager {
    private final List<Brick> bricks = new ArrayList<>();

    public void generateLevel() {
        bricks.clear();
        for (int i = 0; i < VARIABLES.N_OF_BRICKS; i++){
            int row = i / VARIABLES.BRICKS_PER_ROW;
            int col = i % VARIABLES.BRICKS_PER_ROW;
            int x = VARIABLES.FIRST_X_OF_BRICKS + col * (VARIABLES.WIDTH_OF_BRICKS + VARIABLES.PADDING_X);
            int y = VARIABLES.FIRST_Y_OF_BRICKS + row * (VARIABLES.HEIGHT_OF_BRICKS + VARIABLES.PADDING_Y);
            bricks.add(new Brick(x, y));
        }
    }

    public void update() {
        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            if (brick.isDestroyed()) {
                iterator.remove();
                // TODO: báo cho PowerUpManager hoặc ScoreManager
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (Brick b : bricks) {
            if (!b.isDestroyed()) {
                gc.drawImage(b.getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        }
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public boolean isAllDestroyed() {
        return bricks.isEmpty();
    }
}
