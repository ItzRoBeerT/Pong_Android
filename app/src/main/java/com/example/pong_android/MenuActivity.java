package com.example.pong_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pong_android.Classes.MenuHolder;
import com.example.pong_android.Services.Herramientas;

public class MenuActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
    private  static int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        color= tools.recogerValor();
        MenuHolder menu = new MenuHolder(this,this,color);

        setContentView(menu);
    }


}