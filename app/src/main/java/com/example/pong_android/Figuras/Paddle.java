package com.example.pong_android.Figuras;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle extends Figure{

    private  Double width, height;
    private Integer color;

    public Paddle(Double posX, Double posY, Double width, Double height, Integer color) {
        super(posX, posY);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(this.getColor());
        canvas.drawRect(getPosX().floatValue(),getPosY().floatValue(),(float)(getPosX()+getWidth()), (float)(getPosY()+getHeight()),p);
    }

    @Override
    public boolean isTouching(Double x, Double y) {
        if(x > getPosX() && x < (getPosX()+getWidth()) && y > getPosY() && y < (getPosY()+getHeight())){
            return true;
        }else
            return false;
    }

    @Override
    public void updateCoord(Double desx, Double desy) {
        setPosY(desy+getPosY());
    }
}
