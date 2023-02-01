package com.example.pong_android.Figuras;

import android.graphics.Canvas;

import java.util.Date;
import java.util.Objects;

public abstract class Figure {

    private Long id;
    private Double posX, posY;

    public Figure(Double posX, Double posY) {
        this.id = new Date().getTime();
        this.posX = posX;
        this.posY = posY;
    }

    public Long getId() {
        return id;
    }

    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Objects.equals(id, figure.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public abstract  void draw(Canvas canvas);
    public abstract boolean isTouching(Double x, Double y);
    public abstract void updateCoord(Double desx, Double desy);
}
