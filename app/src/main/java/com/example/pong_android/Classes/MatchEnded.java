package com.example.pong_android.Classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class MatchEnded extends Thread{

    Bitmap img;
    Canvas canvas;

    public MatchEnded(Canvas canvas, Bitmap img){
        this.img= img;
        this.canvas = canvas;
        this.img = makeTransparentBitmap(img,0);
        canvas.drawBitmap(this.img, 0, 0,null);

    }
    private static Bitmap makeTransparentBitmap(Bitmap bmp, int alpha) {
        Bitmap transBmp = Bitmap.createBitmap(bmp.getWidth(),
                bmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(transBmp);
        final Paint paint = new Paint();
        paint.setAlpha(alpha);
        canvas.drawBitmap(bmp, 0, 0, paint);
        return transBmp;
    }
    @Override
    public void run() {
       this.img = makeTransparentBitmap(img,200);
    }
}
