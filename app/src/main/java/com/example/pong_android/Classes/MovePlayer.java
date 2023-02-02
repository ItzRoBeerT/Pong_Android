package com.example.pong_android.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.pong_android.Figuras.Ball;
import com.example.pong_android.Figuras.Paddle;

import java.util.ArrayList;

public class MovePlayer extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    private BallThread ballThread;
    private Paddle p1,p2;
    private Boolean p1Active=false, p2Active=false;
    private Ball ball;
    private Float initXP1, initYP1,initXP2,initYP2 ;

    private float mPosX;
    private float mPosY;

    private float mLastTouchX;
    private float mLastTouchY;

    public MovePlayer(Context context) {
        super(context);
        getHolder().addCallback(this);
        setBackgroundColor(Color.BLACK);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Float x = event.getX();
        Float y = event.getY();
        int pointerCount = event.getPointerCount();

       switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(p1.isTouching(x.doubleValue(), y.doubleValue())){
                    p1Active = true;
                    initXP1 = event.getX();
                    initYP1 = event.getY();
                    System.out.println("dentro j1");
                }
                if(p2.isTouching(x.doubleValue(), y.doubleValue())){
                    p2Active = true;
                    initXP2 = event.getX();
                    initYP2 = event.getY();
                    System.out.println("dentro j2");
                }
                return  true;
            case MotionEvent.ACTION_MOVE:
                if(p1Active || p2Active){
                    for (int i = 0; i< pointerCount; i++){
                        float xB = event.getX(i);
                        float yb = event.getY(i);

                        if(xB< 300){
                            Double des1 = (double)(xB-initXP1);
                            Double des2 = (double) (yb-initYP1);
                            p1.updateCoord(des1,des2);
                            initYP1 = yb;
                        }else if(xB > 300){
                            Double des1 = (double)(xB-initXP2);
                            Double des2 = (double) (yb-initYP2);
                            p2.updateCoord(des1,des2);
                            initYP2 = yb;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                p1Active= false;
                p2Active=false;
                break;
       }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p1.draw(canvas);
        p2.draw(canvas);
        ball.draw(canvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        Integer midX = this.getWidth()/2;
        Integer midY = this.getHeight()/2;
        //Jugador 1
        p1 = new Paddle(200.0,midY.doubleValue(),50.0,200.0,Color.WHITE);
        //Jugador 2
        p2 = new Paddle((double)(this.getWidth()-200),midY.doubleValue(),50.0,200.0,Color.WHITE);
        //Pelota
        ball = new Ball((double)midX,(double)midY,50.0,Color.WHITE);

        //hilo principal
        drawThread = new DrawThread(surfaceHolder, this);
        drawThread.setRunning(true);
        drawThread.start();

        //hilo de la pelota
        ballThread = new BallThread(ball,this, p1);
        ballThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry= true;
        drawThread.setRunning(false);
        while (retry){
            try {
                drawThread.join();
                retry=false;
            }catch (InterruptedException e ){
            }
        }

    }
}
