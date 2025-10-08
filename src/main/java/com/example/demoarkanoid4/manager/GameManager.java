package com.example.demoarkanoid4.manager;
import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.core.ball.Ball;
import com.example.demoarkanoid4.core.paddle.Paddle;
import com.example.demoarkanoid4.core.Wall;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class GameManager extends Pane {
    private AnimationTimer timer;

    private BrickManager            brickManager;
    private CollisionManager        collisionManager;
    private WallManager             wallManager;
    private PaddleManager           paddleManager;
    private PowerUpManager          powerUpManager;
    private BallManager             ballManager;
    private EffectManager           effectManager;

    private boolean                 inGame;
    private GraphicsContext         gc;


    public GameManager() {
        setFocusTraversable(true); // To allow requestFocus()
        initBoard();
    }

    private void initBoard() {
        setPrefSize(VARIABLES.WIDTH, VARIABLES.HEIGHT);
        Canvas canvas = new Canvas(VARIABLES.WIDTH, VARIABLES.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
        inGame = true;
        gameInit();
    }

    private void gameInit() {
        Paddle paddle = new Paddle();
        ballManager = new BallManager();
        paddleManager = new PaddleManager(paddle);
        brickManager = new BrickManager();
        wallManager = new WallManager();
        collisionManager = new CollisionManager();
        powerUpManager = new PowerUpManager();
        brickManager.generateLevel();
        wallManager.generateLevel();
        loop();
    }



    private void loop() {
        final double FPS = 60.0;
        final double UPDATE_INTERVAL = 1e9 / FPS;
        final long[] lastUpate = {System.nanoTime()};

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                while (now - lastUpate[0] >= UPDATE_INTERVAL) {
                    update(1.0 / FPS);
                    lastUpate[0] += UPDATE_INTERVAL;
                }
                render();
            }
        };

        timer.start();
    }

    private void gameFinished() {
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Verdana", 18));
        gc.fillText("GameOver",
                (VARIABLES.WIDTH - "GameOver".length() * 10) / 2.0,
                VARIABLES.HEIGHT / 2.0);
    }

    public void update(double deltaTime) {
        // 1) cập nhật paddle trước để ball (nếu ở trạng thái stuck) đặt đúng vị trí dựa vào paddle
        paddleManager.update(deltaTime);
        ballManager.update(deltaTime, paddleManager.getPaddle());
        collisionManager.handleBallPaddleCollision(ballManager, paddleManager);
        collisionManager.handleBallWallCollision(ballManager, wallManager);
        collisionManager.handleBallBrickCollision(ballManager, brickManager, powerUpManager);
        collisionManager.handlePaddlePowerUpCollision(powerUpManager, paddleManager, effectManager, ballManager);
        brickManager.update();
        powerUpManager.update(deltaTime);
        effectManager.update(ballManager, paddleManager);

    }


    public void render() {
        gc.clearRect(0,0, VARIABLES.WIDTH, VARIABLES.HEIGHT);
        if(!inGame){
            gameFinished();
            return;
        }
        ballManager.render(gc);
        brickManager.render(gc);
        paddleManager.render(gc);
        wallManager.render(gc);
        powerUpManager.render(gc);
    }

    private void stopGame() {
        inGame = false;
        timer.stop();
        // stop background music
        //SoundManager.getInstance().stopMusic();
    }

    // --- Add these getter methods for Main and InputHandler ---
    public BallManager getBalls() {
        return ballManager;
    }

    public PaddleManager getPaddle() {
        return paddleManager;
    }
}