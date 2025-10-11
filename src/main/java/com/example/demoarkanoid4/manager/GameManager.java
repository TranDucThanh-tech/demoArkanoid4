package com.example.demoarkanoid4.manager;

import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.states.MapData;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;

public class GameManager extends Pane {
    private AnimationTimer timer;

    private BrickManager brickManager;
    private CollisionManager collisionManager;
    private WallManager wallManager;
    private PaddleManager paddleManager;
    private PowerUpManager powerUpManager;
    private BallManager ballManager;
    private EffectManager effectManager;
    private MapManager mapManager;

    private int currentLevel = 1;
    private boolean inGame = true;
    private GraphicsContext gc;

    public GameManager() {
        setFocusTraversable(true);
        initBoard();
    }

    private void initBoard() {
        setPrefSize(VARIABLES.WIDTH, VARIABLES.HEIGHT);
        Canvas canvas = new Canvas(VARIABLES.WIDTH, VARIABLES.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);

        AssetManager.preloadAssets(); // ✅ load hình ảnh, âm thanh... trước khi game chạy
        gameInit();
    }

    

    private void gameInit() {
        Paddle paddle = new Paddle();

        // ✅ Object pooling: mỗi manager sẽ quản lý danh sách sẵn
        ballManager = new BallManager();
        paddleManager = new PaddleManager(paddle);
        brickManager = new BrickManager();
        wallManager = new WallManager();
        collisionManager = new CollisionManager();
        powerUpManager = new PowerUpManager();
        effectManager = new EffectManager();
        mapManager = new MapManager();

        loadLevel(currentLevel);
        loop();
    }

    private void loop() {
        final double FPS = 60.0;
        final double UPDATE_INTERVAL = 1e9 / FPS;
        final long[] lastUpdate = { System.nanoTime() };
        double[] accumulator = { 0 };

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                accumulator[0] += now - lastUpdate[0];
                lastUpdate[0] = now;

                // Giới hạn update theo tốc độ khung hình
                while (accumulator[0] >= UPDATE_INTERVAL) {
                    update(1.0 / FPS);
                    accumulator[0] -= UPDATE_INTERVAL;
                }

                render();
            }
        };
        timer.start();
    }

    private void gameFinished() {
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Verdana", 18));
        gc.fillText("Game Over",
                (VARIABLES.WIDTH - "Game Over".length() * 10) / 2.0,
                VARIABLES.HEIGHT / 2.0);
    }

    public void update(double deltaTime) {
        paddleManager.update(deltaTime);
        ballManager.update(deltaTime, paddleManager.getPaddle());
        collisionManager.handleBallWallCollision(ballManager, wallManager);
        collisionManager.handleBallPaddleCollision(ballManager, paddleManager);
        collisionManager.handleBallBrickCollision(ballManager, brickManager, powerUpManager);
        collisionManager.handlePaddlePowerUpCollision(powerUpManager, paddleManager, effectManager, ballManager);
        powerUpManager.update(deltaTime);
        effectManager.update(ballManager, paddleManager);
    }

    public void render() {
        // ✅ clearRect nhanh hơn fillRect vì nó bỏ qua màu nền
        gc.clearRect(0, 0, VARIABLES.WIDTH, VARIABLES.HEIGHT);

        if (!inGame) {
            gameFinished();
            return;
        }

        wallManager.render(gc);
        brickManager.render(gc);
        ballManager.render(gc);
        paddleManager.render(gc);
        powerUpManager.render(gc);
    }

    private void stopGame() {
        inGame = false;
        timer.stop();
    }

    public BallManager getBalls() {
        return ballManager;
    }

    public PaddleManager getPaddle() {
        return paddleManager;
    }

    public void loadLevel(int level) {
        MapData mapData;

        if (level <= 3) {
            mapData = mapManager.loadMap(level);
        } else {
            mapData = mapManager.loadMapFromMatrix(mapManager.createRandomMatrix());
        }

        wallManager.setWalls(mapData.walls());
        brickManager.setBricks(mapData.bricks());

        powerUpManager.clear();
        paddleManager.resetState();
        ballManager.resetState(paddleManager.getPaddle());
        effectManager.clear(); // ✅ xóa hiệu ứng cũ khỏi pool
    }
}
