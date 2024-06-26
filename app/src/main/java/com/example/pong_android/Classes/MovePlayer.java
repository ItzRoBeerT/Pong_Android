package com.example.pong_android.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.text.Editable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pong_android.DAO.DaoHistorial;
import com.example.pong_android.Figuras.Ball;
import com.example.pong_android.Figuras.Paddle;
import com.example.pong_android.Figuras.TextTimer;
import com.example.pong_android.MainActivity;
import com.example.pong_android.MenuActivity;
import com.example.pong_android.R;
import com.example.pong_android.Services.Herramientas;

import java.util.ArrayList;

public class MovePlayer extends SurfaceView implements SurfaceHolder.Callback {

    private  final  Integer MAX_GOL=5;
    private Herramientas tools;
    private  Integer colorBate;
    private DrawThread drawThread;
    private BallThread ballThread;
    private Paddle p1,p2;
    private Float initYP1,initYP2;
    private Boolean p1Active=false, p2Active=false;
    private Ball ball;
    private TextTimer contador, puntuacionP1,puntuacionP2;
    private Integer puntos1=0,puntos2=0;
    private AppCompatActivity activity;
    private TimerThread timerThread;
    private MediaPlayer jump, knockaut;
    //foto bacGround
    private  Bitmap scaled, scaled2;
    private Canvas miCanvas;
    private int contadorActividad = 0;
    private Bitmap bmpKnock;
    private DaoHistorial dao;

    public MovePlayer(Context context, AppCompatActivity activity) {
        super(context);
        getHolder().addCallback(this);
        setBackgroundColor(Color.BLACK);

        //Musiquita to wapa
        jump = MediaPlayer.create(context,R.raw.floral_fury);
        jump.start();
        knockaut = MediaPlayer.create(context,R.raw.knockout);
        this.activity= activity;
        tools = new Herramientas(activity);

        dao = new DaoHistorial(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Float x = event.getX();
        Float y = event.getY();
        int pointerCount = event.getPointerCount();

       switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i< pointerCount; i++){
                    float xB = event.getX(i);
                    float yb = event.getY(i);
                    if(p1.isTouching((double)xB, (double)yb)){
                        p1Active = true;
                        initYP1 = event.getY();
                        System.out.println("dentro j1");
                    }
                    if(p2.isTouching((double)xB, (double)yb)){
                        p2Active = true;
                        initYP2 = event.getY();
                        System.out.println("dentro j2");
                    }
                }
                return  true;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Pinter count: "+pointerCount);
                if (p1Active || p2Active){
                    for (int i = 0; i< pointerCount; i++){
                        float xB = event.getX(i);
                        float yb = event.getY(i);

                        if(xB< 300){
                            Double des2 = (double) (yb-initYP1);
                            p1.updateCoord(0.0,des2);
                            initYP1 = yb;
                        }else if(xB > 300){
                            Double des2 = (double) (yb-initYP2);
                            p2.updateCoord(0.0,(double)des2);
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
        miCanvas= canvas;
        //dibujar fondo
        canvas.drawBitmap(scaled,0,0,null);

        p1.draw(canvas);
        p2.draw(canvas);

        ball.draw(canvas);

        contador.draw(canvas);
        puntuacionP1.draw(canvas);
        puntuacionP2.draw(canvas);


        canvas.drawBitmap(scaled2, 1000, 1000,null);
        terminar();

    }

    public void dibujar(Paddle p){
        p.draw(this.miCanvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        colorBate = tools.recogerValor();
        Integer midX = this.getWidth()/2;
        Integer midY = this.getHeight()/2;
        //Inicializar posicion inicial de los bates
        initYP1=(float)(this.getHeight()/2);
        initYP2=(float)(this.getHeight()/2);
        //Jugador 1
        p1 = new Paddle(150.0,midY.doubleValue(),50.0,200.0,colorBate);
        //Jugador 2
        p2 = new Paddle((double)(this.getWidth()-200),midY.doubleValue(),50.0,200.0,colorBate);
        //Pelota
        ball = new Ball((double)midX,(double)midY,50.0,Color.WHITE);
        //contador
        contador = new TextTimer("00:00",midX-150,this.getHeight());

        //hilo principal
        drawThread = new DrawThread(surfaceHolder, this);
        drawThread.setRunning(true);
        drawThread.start();

        //hilo cronometro
        timerThread = new TimerThread(contador,ball,this);
        timerThread.start();
        //pintar backGround
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        float scale = (float) background.getHeight() / (float) getHeight();
        int newWidth = getWidth();
        int newHeight = Math.round(background.getHeight() / scale);
        scaled = Bitmap.createScaledBitmap(background,newWidth,newHeight,true);

        bmpKnock = BitmapFactory.decodeResource(getResources(), R.drawable.foto_ganar);
        scaled2 = Bitmap.createScaledBitmap(bmpKnock,newWidth,newHeight,true);

        //pintar puntuacion p1
        puntuacionP1 = new TextTimer("0",getWidth()/3,100);
        //pintar puntuacion p2
        puntuacionP2 = new TextTimer("0",(getWidth()-850),100);

        //hilo de la pelota
        ballThread = new BallThread(ball,this, p1,p2,puntuacionP1,puntuacionP2);
        ballThread.start();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    public void terminar(){


        puntos1 = ballThread.getPuntos1();
        puntos2 = ballThread.getPuntos2();

        if(puntos1 == MAX_GOL || puntos2 == MAX_GOL ){
            miCanvas.drawBitmap(scaled2, 0, 0,null);
            jump.stop();
            knockaut.start();
            if(this.puntos1==MAX_GOL){
                drawThread.setRunning(false);
                ballThread.setStop(false);
                timerThread.setStop(true);
                if (contadorActividad==0) {
                    dao.insertarPartida("P1","P2","P1",contador.getTxt());
                }

            }else if(this.puntos2==MAX_GOL){
                drawThread.setRunning(false);
                ballThread.setStop(false);
                timerThread.setStop(true);
                if (contadorActividad==0) {
                    dao.insertarPartida("P1","P2","P2",contador.getTxt());
                }
            }
            if (contadorActividad==0) {
                tools.cambiarActividad(MenuActivity.class);
            }
            contadorActividad++;
            this.activity.finish();
        }
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
