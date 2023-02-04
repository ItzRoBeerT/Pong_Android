package com.example.pong_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pong_android.Services.Herramientas;

public class CreditsActivity extends AppCompatActivity {

    private Herramientas tools = new Herramientas(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_credits);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tools.cambiarActividad(MenuActivity.class);
        this.finish();
    }
}