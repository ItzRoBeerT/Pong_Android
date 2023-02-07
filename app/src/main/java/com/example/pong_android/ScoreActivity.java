package com.example.pong_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pong_android.Classes.AdaptadorDatos;
import com.example.pong_android.Services.Herramientas;

public class ScoreActivity extends AppCompatActivity {

    private Herramientas tools = new Herramientas(this);

    //recycler
    private RecyclerView recyclerView;
    AdaptadorDatos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_score);

        //recycler
        recyclerView = findViewById(R.id.miRecycler);
        setAdapter();


    }

    public void setAdapter(){
        adaptador = new AdaptadorDatos(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptador);

    }

    @Override
    public void onBackPressed() {
        finish();
        tools.cambiarActividad(MenuActivity.class);
    }
}