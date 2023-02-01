package com.example.pong_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pong_android.Classes.MovePlayer;
import com.example.pong_android.Services.Herramientas;

public class MainActivity extends AppCompatActivity {

    private Herramientas tools = new Herramientas(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        MovePlayer move = new MovePlayer(this);
        setContentView(move);
    }
}