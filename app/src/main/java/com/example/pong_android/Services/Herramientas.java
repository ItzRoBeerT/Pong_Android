package com.example.pong_android.Services;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class Herramientas {

    AppCompatActivity compAct;

    public   Herramientas(AppCompatActivity compAct){
        this.compAct= compAct;
    }
    public void quitarTitulo(){
        compAct.requestWindowFeature(Window.FEATURE_NO_TITLE);
        compAct.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        compAct.getSupportActionBar().hide();
    }
    public void pasarValor(Class c, int color){
        Intent i = new Intent(this.compAct,c);
        i.putExtra("key",color);
        this.compAct.startActivity(i);
    }
    public Integer recogerValor(){
        Bundle extras = this.compAct.getIntent().getExtras();
        int valor= Color.WHITE;
        if(extras !=null ){
            valor = extras.getInt("key");
        }
        if (valor == 0){
            valor= Color.WHITE;
        }
        return valor;
    }
    public void cambiarActividad(Class a){
        Intent cambiarActividad = new Intent(this.compAct,a);
        this.compAct.startActivity(cambiarActividad);
    }
}
