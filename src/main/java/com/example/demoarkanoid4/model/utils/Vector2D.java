package com.example.demoarkanoid4.model.utils;

public class Vector2D {
    public double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // ==================== TOÁN HỌC CƠ BẢN ==================== //
    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D multiply(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    public double dot(Vector2D other) {
        return x * other.x + y * other.y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        double len = length();
        return len == 0 ? new Vector2D(0, 0) : new Vector2D(x / len, y / len);
    }

    // ==================== PHẢN XẠ ==================== //
    /**
     * Phản xạ vector với vector pháp tuyến.
     * r = v - 2*(v·n)*n
     */
    public Vector2D reflect(Vector2D normal) {
        double dot = this.dot(normal);
        return new Vector2D(
                x - 2 * dot * normal.x,
                y - 2 * dot * normal.y
        );
    }

    // ==================== GÓC & CHIỀU ==================== //
    /** Lấy góc (radian) của vector so với trục X, tính từ trục X dương ngược chiều kim đồng hồ */
    public double getAngle() {
        return Math.atan2(y, x);
    }

    /** Đặt lại vector theo góc và độ dài (speed) */
    public void setAngleAndLength(double angle, double length) {
        this.x = Math.cos(angle) * length;
        this.y = Math.sin(angle) * length;
    }

    /** Tạo bản sao */
    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}
