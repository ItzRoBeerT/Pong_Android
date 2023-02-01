package com.example.pong_android.Classes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawThread extends Thread{

    private MovePlayer move;
    private SurfaceHolder holder;
    private boolean running;

    public  DrawThread(SurfaceHolder holder, MovePlayer move){
        this.move = move;
        this.holder = holder;
        this.running=false;
    }

    public void setRunning(boolean run){
        this.running = run;
    }

    @Override
    public void run() {
        super.run();
        Canvas canvas;
        while (running){
            canvas = null;
            try{
                canvas = holder.lockCanvas();
                if(canvas != null){
                    synchronized (holder){
                        Paint p = new Paint();
                        p.setColor(Color.RED);
                        p.setAntiAlias(true);
                    }
                }
            }finally {
                if(canvas != null)
                    holder.unlockCanvasAndPost(canvas);
            }
            canvas = holder.lockCanvas();
            move.postInvalidate();
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
