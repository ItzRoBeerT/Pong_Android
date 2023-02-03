package com.example.pong_android.Figuras;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball extends Figure {

    private Double radio;
    private Integer color;
    private Integer speedX=10, speedY=10;

    public Ball(Double posX, Double posY, Double radio, Integer color) {
        super(posX, posY);
        this.radio = radio;
        this.color = color;
    }

    public Double getRadio() {
        return radio;
    }

    public void setRadio(Double radio) {
        this.radio = radio;
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
        p.setColor(getColor());
        canvas.drawCircle(getPosX().floatValue(),getPosY().floatValue(),getRadio().floatValue(),p);
    }



    @Override
    public boolean isTouching(Double x, Double y) {
        Integer disX = (int) (x-getPosX());
        Integer disY = (int) (y-getPosY());

        if(Math.pow(getRadio(),2) > (Math.pow(disX,2) + Math.pow(disY,2))){
            return true;
        }
        return false;
    }

    @Override
    public void updateCoord(Double desx, Double desy) {
        setPosX(desx + getPosX());
        setPosY(desy + getPosY());
    }

    public  void speedUpdate(int speed){
        if(this.speedX<0){
            this.speedX = -speed;
        }else
        this.speedX = speed;
    }

    public void slide(){
        setPosX(getPosX()+speedX);
        setPosY(getPosY()+speedY);
    }
    public void touchBorder(){
        speedX = -speedX;
    }
    public void touchBorderY(){
        speedY = -speedY;

    }
}
