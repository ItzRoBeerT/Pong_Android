package com.example.pong_android.Classes;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.SurfaceHolder;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pong_android.Figuras.Ball;
import com.example.pong_android.Figuras.Paddle;
import com.example.pong_android.Figuras.TextTimer;
import com.example.pong_android.MainActivity;
import com.example.pong_android.MenuActivity;
import com.example.pong_android.R;
import com.example.pong_android.Services.Herramientas;

public class BallThread extends Thread{

    private Boolean stop=true;
    private Ball ball;
    private  MovePlayer move;
    private  Paddle p1, p2;
    private TextTimer puntuacionp1, puntuacionp2;
    private Integer cont1,cont2;
    private AppCompatActivity activity;
    private Herramientas tools;

    public BallThread(Ball ball, MovePlayer move, Paddle p1, Paddle p2, TextTimer puntuacionp1, TextTimer puntuacionp2){
        this.ball= ball;
        this.move = move;
        this.p1 = p1;
        this.p2 = p2;
        this.cont1=0;
        this.cont2=0;
        this.puntuacionp1= puntuacionp1;
        this.puntuacionp2 = puntuacionp2;
        this.activity=activity;
        tools = new Herramientas(activity);
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public int getPuntos1(){
        return  cont1;
    }

    public int getPuntos2(){
        return  cont2;
    }
    public void stopBall(){
        this.stop=false;
    }

    @Override
    public void run() {
        super.run();
        double width =  move.getWidth();
        double height = move.getHeight();
        try{
            while (stop){
                ball.slide();
                //MOVIMIENTO DE LA PELOTA CUANDO TOCA LOS BORDES
                if( ball.getPosX() > width-100){
                    ball.touchBorder();
                    cont1++;
                    puntuacionp1.setTxt(cont1.toString());
                    ball.setPosX((double) (move.getWidth()/2));
                    ball.setPosY((double) (move.getHeight()/2));
                    Thread.sleep(1000);
                }
                if(ball.getPosX() < 100){
                    ball.touchBorder();
                    cont2++;
                    puntuacionp2.setTxt(cont2.toString());
                    ball.setPosX((double) (move.getWidth()/2));
                    ball.setPosY((double) (move.getHeight()/2));
                    Thread.sleep(1000);
                }
                if (ball.getPosY() > height){
                    ball.touchBorderY();
                }
                if(ball.getPosY() < 0){
                    ball.touchBorderY();
                }
                //COLISION CON JUGADOR 1
                if(ball.getPosX() < p1.getPosX()+50 && ball.getPosY() > p1.getPosY() && ball.getPosY() < (float)(p1.getPosY()+p1.getHeight())){
                    ball.touchBorder();
                }
                //COLISION CON JUGADOR 2
                if(ball.getPosX() > p2.getPosX()-50 && ball.getPosY() > p2.getPosY() && ball.getPosY() < (float)(p2.getPosY()+p2.getHeight())){
                    ball.touchBorder();
                }

                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){

        }

    }
}
