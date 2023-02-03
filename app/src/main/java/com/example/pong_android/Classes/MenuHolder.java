package com.example.pong_android.Classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pong_android.Figuras.Paddle;
import com.example.pong_android.Figuras.TextTimer;
import com.example.pong_android.MainActivity;
import com.example.pong_android.R;
import com.example.pong_android.Services.Herramientas;

public class MenuHolder extends SurfaceView implements SurfaceHolder.Callback {
    private  Bitmap scaled;
    private Paddle txtNewGame;
    private Herramientas tools;
    private MediaPlayer jump;


    public MenuHolder(Context context, AppCompatActivity compAct) {
        super(context);
        getHolder().addCallback(this);
        setBackgroundColor(Color.BLACK);

        jump = MediaPlayer.create(context,R.raw.menu_theme);
        jump.start();
        jump.setLooping(true);
        tools = new Herramientas(compAct);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(scaled,0,0,null);
        txtNewGame.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (txtNewGame.isTouching((double) x, (double) y)){
                    jump.stop();
                   tools.cambiarActividad(MainActivity.class);
                }
                break;
        }
        return true;
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        //pintar backGround
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.menubg1);
        int newWidth = getWidth();
        int newHeight =getHeight();
        scaled = Bitmap.createScaledBitmap(background,newWidth,newHeight,true);

        //new game
        txtNewGame = new Paddle((double) getWidth()*0.35, (double) (getHeight()*0.36),700.0,100.0,Color.TRANSPARENT);

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
