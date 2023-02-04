package com.example.pong_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pong_android.Classes.MenuHolder;
import com.example.pong_android.Classes.MovePlayer;
import com.example.pong_android.Services.Herramientas;

public class MainActivity extends AppCompatActivity {

    private Herramientas tools = new Herramientas(this);

    @Override
    public void onBackPressed() {
        Toast t = Toast.makeText(this, "No se puede volver", Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        MovePlayer move = new MovePlayer(this,this);
        setContentView(move);
    }


}