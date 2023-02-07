package com.example.pong_android.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pong_android.DAO.DaoHistorial;
import com.example.pong_android.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolder> {
    private List<ItemLista> mDataSet;
    private Context context;
    private ArrayList<Partida> partidas;

    public AdaptadorDatos(Context context) {
        DaoHistorial dao = new DaoHistorial(context);

        this.context = context;
        mDataSet = new ArrayList<ItemLista>();

        partidas = dao.obtenerPartidas();

        for (int i = 0; i < partidas.size(); i++) {
            add(new ItemLista(partidas.get(i).getJugador1(),partidas.get(i).getJugador2(),partidas.get(i).getGanador(),partidas.get(i).getTiempo()));
        }
    }

    public void add(ItemLista i) {
        mDataSet.add(i);
        notifyItemInserted(mDataSet.indexOf(i));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemLista item = (ItemLista) mDataSet.get(position);
        holder.jugador1.setText(item.getJugador1());
        holder.jugador2.setText(item.getJugador2());
        holder.ganador.setText(item.getGanador());
        holder.tiempo.setText(item.getTiempo());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView jugador1;
        protected TextView jugador2;
        protected TextView ganador;
        protected TextView tiempo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.jugador1 = (TextView) itemView.findViewById(R.id.txtP1);
            this.jugador2 = (TextView) itemView.findViewById(R.id.txtP2);
            this.ganador = (TextView) itemView.findViewById(R.id.txtGanador);
            this.tiempo = (TextView) itemView.findViewById(R.id.txtTime);
        }
    }
}
