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
    private BrickManager brickManager;
    private CollisionManager collisionManager;
    private WallManager wallManager;
    private Ball ball;
    private PaddleManager paddleManager;
    private List<Wall> walls;
    private boolean inGame;
    private GraphicsContext gc;


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
        ball = new Ball();
        brickManager = new BrickManager();
        collisionManager = new CollisionManager();
        paddleManager = new PaddleManager();
        brickManager.generateLevel();
        wallManager.generateLevel();
        loop();
    }

    public void update(double deltaTime) {
        // 1) cập nhật paddle trước để ball (nếu ở trạng thái stuck) đặt đúng vị trí dựa vào paddle
        paddleManager.update(deltaTime);

        // 2) cập nhật bóng (nếu stuck thì nó sẽ follow paddle)
        ball.update(deltaTime, paddleManager.getPaddle());

        // 3) kiểm tra va chạm (dựa trên vị trí mới của bóng)
        collisionManager.handleBallPaddleCollision(ball, paddleManager.getPaddle());
        collisionManager.handleBallWallCollision(ball, walls);
        collisionManager.handleBallBrickCollision(ball, brickManager.getBricks());

        // 4) cập nhật bricks (hoặc logic powerups)
        brickManager.update();
    }


    public void render() {
        gc.clearRect(0,0, VARIABLES.WIDTH, VARIABLES.HEIGHT);
        if(!inGame){
            gameFinished();
            return;
        }
        brickManager.render(gc);
        ball.render(gc);
        paddleManager.render(gc);
    }

    private void loop() {
        timer = new AnimationTimer() {
            private long lastUpdate = System.nanoTime();

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastUpdate) / 1_000_000_000.0; // seconds
                if (deltaTime > 0.05) deltaTime = 0.05; // clamp to avoid jumps
                update(deltaTime);
                render();
                lastUpdate = now;
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

    private void stopGame() {
        inGame = false;
        timer.stop();
        // stop background music
        //SoundManager.getInstance().stopMusic();
    }

    // --- Add these getter methods for Main and InputHandler ---
    public Ball getBall() {
        return ball;
    }
    public Paddle getPaddle() {
        return paddleManager.getPaddle();
    }
}