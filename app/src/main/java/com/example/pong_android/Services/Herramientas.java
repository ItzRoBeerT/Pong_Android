package com.example.pong_android.Services;

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
}
