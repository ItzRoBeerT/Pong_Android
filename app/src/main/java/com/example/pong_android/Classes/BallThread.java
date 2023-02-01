package com.example.pong_android.Classes;

import android.view.SurfaceHolder;

import com.example.pong_android.Figuras.Ball;
import com.example.pong_android.Figuras.Paddle;

public class BallThread extends Thread{

    private Boolean stop=true;
    private Ball ball;
    private  MovePlayer move;
    private  Paddle p1;
    public BallThread(Ball ball, MovePlayer move, Paddle p1){
        this.ball= ball;
        this.move = move;
        this.p1 = p1;
    }

    public void stopBall(){
        this.stop=false;
    }

    @Override
    public void run() {
        super.run();
        double width =  move.getWidth();
        double height = move.getHeight();
        while (stop){
          ball.slide();
          //MOVIMIENTO DE LA PELOTA CUANDO TOCA LOS BORDES
          if( ball.getPosX() > width){
              ball.touchBorder();
          }
          if(ball.getPosX() < 0){
              ball.touchBorder();
          }
          if (ball.getPosY() > height){
              ball.touchBorderY();
          }
          if(ball.getPosY() < 0){
              ball.touchBorderY();
          }
          //COLISION CON JUGADOR 1
            if(ball.getPosX() < p1.getPosX()+p1.getWidth()){
                ball.touchBorder();
            }
            //COLISION CON JUGADOR 2

            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
