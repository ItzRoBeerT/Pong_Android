package com.example.pong_android.Figuras;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TextTimer {

    private String txt;
    private int color;
    private float x,y;

    public  TextTimer(String txt, float x, float y){
        this.txt = txt;
        this.x = x;
        this.y = y;
        color = Color.WHITE;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void draw(Canvas canvas){
        //Borde del cronometro
        Paint stkPaint = new Paint();
        stkPaint.setStyle(Paint.Style.STROKE);
        stkPaint.setStrokeWidth(8);
        stkPaint.setTextSize(120);
        stkPaint.setColor(Color.BLACK);
        canvas.drawText(this.txt,this.x,this.y,stkPaint);

        //el cronometro
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(color);
        p.setTextSize(120);

        canvas.drawText(this.txt,this.x,this.y,p);
    }

    public  boolean isTouching(float x,float y){
        if(x > this.x  && y > this.y){
            return true;
        }else
            return false;
    }


}
