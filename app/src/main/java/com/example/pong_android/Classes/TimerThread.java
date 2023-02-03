package com.example.pong_android.Classes;

import android.graphics.Color;

import com.example.pong_android.Figuras.Ball;
import com.example.pong_android.Figuras.TextTimer;

public class TimerThread extends Thread {


    private Boolean stop =false;
    private Integer minutos,segundos,milesimas;
    private  TextTimer txt;
    private Ball ball;

    public TimerThread(TextTimer txt, Ball ball){
        this.minutos=0;
        this.segundos=0;
        this.milesimas=0;
        this.txt = txt;
        this.ball= ball;
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

                if(texto.equals("00:30")){
                    this.ball.speedUpdate(15);
                }
                if(texto.equals("01:00")){
                    txt.setColor(Color.YELLOW);
                    this.ball.speedUpdate(25);
                }
                txt.setTxt(texto);
            }
        }catch (Exception e){
            txt.setTxt("00:00");
        }
    }
}
