package com.example.pong_android.Figuras;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TextTimer {

    private String txt;
    private float x,y;

    public  TextTimer(String txt, float x, float y){
        this.txt = txt;
        this.x = x;
        this.y = y;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);
        p.setTextSize(120);
        canvas.drawText(this.txt,this.x,this.y,p);
    }


}
