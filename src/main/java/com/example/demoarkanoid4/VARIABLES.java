package com.example.demoarkanoid4;

/**
 * Game constants for Arkanoid.
 */
public final class VARIABLES {
    private VARIABLES() {}

    // Window
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final int BOTTOM_EDGE = HEIGHT;

    // Paddle
    public static final int INIT_PADDLE_X = WIDTH / 2 - 50;
    public static final int INIT_PADDLE_Y = HEIGHT - 30;
    public static final float SPEED_OF_PADDLE = 500.0F;
    public static final double WIDTH_OF_PADDLE = 32;
    public static final double HEIGHT_OF_PADDLE = 9;
    // Walls
    public static final int WIDTH_OF_WALLS = 15;
    public static final int HEIGHT_OF_WALLS = 16;
    public static final int N_OF_WALLS_LEFT_RIGHT = HEIGHT / HEIGHT_OF_WALLS;
    public static final int N_OF_WALLS_TOP = WIDTH / WIDTH_OF_WALLS;


    // Ball
    public static final int INIT_BALL_X = WIDTH / 2 - 10;
    public static final int INIT_BALL_Y = HEIGHT - 60;
    public static final float SPEED_OF_BALL = 400.0F;
    public static final float ACCELERATED_SPEED_MULTIPLIER = 2.0F;
    public static final int MAX_BALL = 4;
    public static final int STRONG_OF_BALL = 1;
    // Bricks
    public static final int N_OF_BRICKS = 150;
    public static final int BRICKS_PER_ROW = 18;
    public static final int MAXHEALTH_OF_BRICKS = 2;
    public static final int WIDTH_OF_BRICKS = 29;
    public static final int HEIGHT_OF_BRICKS = 14;
    public static final int PADDING_X = 0;
    public static final int PADDING_Y = 0;



    //PowerUp
    public static final int N_OF_POWERUP = 4;
    public static final char FASTER = '0';
    public static final char BIGGERPADDLE = '1';
    public static final char STRONGER = '2';
    public static final char MULTIBALL = '3';
    public static final double DROP_RATE = 1; // 25%
    public static float SPEED_OF_POWERUP = 200F;
    public static int BUFF = 5;

}