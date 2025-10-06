package com.example.demoarkanoid4.core;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;

import java.util.Objects;

public class GameObject {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Image image;
    protected ImageView imageView;
    private double scaleX;
    private double scaleY;

    public GameObject(String imagePath, double startX, double startY) {
        setImagePath(imagePath);
        x = startX;
        y = startY;
        setPosition();
    }

    public GameObject(Image image, double startX, double startY) {
        setImage(image);
        x = startX;
        y = startY;
        setPosition();
    }

    public void setImagePath(String imagePath) {
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        this.imageView = new ImageView(image);
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void setImage(Image image) {
        this.image = image;
        if (this.imageView == null) {
            this.imageView = new ImageView(image);
        } else {
            this.imageView.setImage(image);
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
        if (this.scaleX != 0 && this.scaleY != 0) {
            setScale(this.scaleX, this.scaleY);
        }
    }

    public void setPosition() {
        imageView.setX(getX());
        imageView.setY(getY());
    }

    public void setScale(double sx, double sy) {
        this.scaleX = sx;
        this.scaleY = sy;
        imageView.setFitWidth(width * sx);
        imageView.setFitHeight(height * sy);
    }


    public void drawObject(GraphicsContext gc) {
        if (gc == null) return;
        gc.drawImage(image, x, y, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    // True bounding box based on x/y/width/height for reliable collision
    public Bounds getBounds() {
        return new javafx.geometry.BoundingBox(x, y, width, height);
    }

    public void setY(double v) {
        y = v;
    }

    public void setX(double v) { x = v; }
}