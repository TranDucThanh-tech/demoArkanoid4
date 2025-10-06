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

        // Initialize paddle and ball
        Paddle paddle = new Paddle();
        paddleManager = new PaddleManager(paddle);
        ball = new Ball("/images/Ball.png", VARIABLES.INIT_BALL_X, VARIABLES.INIT_BALL_Y, VARIABLES.SPEED);

        // Initialize walls
        walls = new ArrayList<>();
        // left wall
        walls.add(new Wall(Wall.Side.LEFT, 0, 0, VARIABLES.WIDTH_OF_WALLS, VARIABLES.HEIGHT));
        // right wall
        walls.add(new Wall(Wall.Side.RIGHT, VARIABLES.WIDTH - VARIABLES.WIDTH_OF_WALLS, 0, VARIABLES.WIDTH_OF_WALLS, VARIABLES.HEIGHT));
        // top wall
        walls.add(new Wall(Wall.Side.TOP, 0, 0, VARIABLES.WIDTH, VARIABLES.HEIGHT_OF_WALLS));

        inGame = true;

        gameInit();
    }

    private void gameInit() {
        brickManager = new BrickManager();
        collisionManager = new CollisionManager();
        brickManager.generateLevel();
        loop();
    }

    public void update(double deltaTime) {
        // Check collisions
        collisionManager.handleBallPaddleCollision(ball, paddleManager.getPaddle());
        collisionManager.handleBallWallCollision(ball, walls);
        collisionManager.handleBallBrickCollision(ball, brickManager.getBricks());

        // Update bricks (remove destroyed ones + trigger powerups)
        brickManager.update();
        ball.update(deltaTime, paddleManager.getPaddle());
        paddleManager.update(deltaTime);
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