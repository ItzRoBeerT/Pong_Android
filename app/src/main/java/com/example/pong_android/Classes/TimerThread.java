package com.example.pong_android.Classes;

import android.graphics.Color;
import android.view.SurfaceView;

import com.example.pong_android.Figuras.Ball;
import com.example.pong_android.Figuras.TextTimer;

public class TimerThread extends Thread {


    private Boolean stop =false;
    private Integer minutos,segundos,milesimas;
    private  TextTimer txt;
    private Ball ball;
    private  MovePlayer surface;

    public TimerThread(TextTimer txt, Ball ball,MovePlayer surface){
        this.minutos=0;
        this.segundos=0;
        this.milesimas=0;
        this.txt = txt;
        this.ball= ball;
        this.surface=surface;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        super.run();

        String min="", seg="", texto="";
        try{
            while (!stop){
                Thread.sleep(4);
                milesimas+=4;
                if(milesimas==1000){
                    milesimas=0;
                    segundos+=1;
                    if(segundos ==60){
                        segundos=0;
                        minutos++;
                    }
                }
                if(minutos <10) min= "0"+minutos;
                else min = minutos.toString();
                if( segundos < 10 ) seg = "0" + segundos;
                else seg = segundos.toString();
                texto= min+":"+seg;

                if(texto.equals("00:15")){
                    this.ball.speedUpdate(15);
                }
                if(texto.equals("00:30")){
                    this.ball.speedUpdate(20);
                }
                if(texto.equals("00:45")){
                    this.ball.speedUpdate(25);
                }
                if(texto.equals("01:00")){
                    txt.setColor(Color.YELLOW);
                    this.ball.speedUpdate(30);
                }
                if(texto.equals("01:15")){
                    this.ball.speedUpdate(32);
                }
                if(texto.equals("01:30")){
                    this.ball.speedUpdate(35);
                }
                if(texto.equals("01:45")){
                    this.ball.speedUpdate(36);
                }
                if(texto.equals("02:00")){
                    txt.setColor(Color.RED);
                    this.ball.speedUpdate(40);
                }
                txt.setTxt(texto);
            }
        }catch (Exception e){
            txt.setTxt("00:00");
        }
    }
}
