package com.example.pong_android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pong_android.Services.Herramientas;

public class OptionsActivity extends AppCompatActivity {

    private Herramientas tools = new Herramientas(this);
    private static int colorBate= Color.WHITE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_options);


        Button btnDefault = findViewById(R.id.btnDefault);
        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorBate = Color.WHITE;
                tools.pasarValor(MenuActivity.class,colorBate);
                finish();
            }
        });

        Button btnRojo = findViewById(R.id.btnRojo);
        btnRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorBate = Color.RED;
                tools.pasarValor(MenuActivity.class,colorBate);
                finish();
            }
        });

        Button btnAma = findViewById(R.id.btnAmarillo);
        btnAma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorBate = Color.YELLOW;
                tools.pasarValor(MenuActivity.class,colorBate);
                finish();
            }
        });
        Button btnAzul = findViewById(R.id.btnAzul);
        btnAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorBate = Color.BLUE;
                tools.pasarValor(MenuActivity.class,colorBate);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        tools.cambiarActividad(MenuActivity.class);
    }
}