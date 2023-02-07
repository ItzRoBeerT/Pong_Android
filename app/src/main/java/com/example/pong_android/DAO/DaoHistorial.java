package com.example.pong_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pong_android.Classes.Partida;

import java.util.ArrayList;

public class DaoHistorial extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String NOMBREBBDD = "pong.db";

    public DaoHistorial(Context context) {
        super(context, NOMBREBBDD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table partida" + "(" +
                "id_partida INTEGER Primary Key AUTOINCREMENT," +
                "jugador1 TEXT NOT NULL," +
                "jugador2 TEXT NOT NULL," +
                "ganador TEXT NOT NULL," +
                "tiempo TEXT NOT NULL" + ")");

        sqLiteDatabase.execSQL("INSERT INTO partida(jugador1, jugador2,ganador,tiempo) VALUES('Jugador 1','Jugador 2','Ganador','Tiempo')");
        sqLiteDatabase.execSQL("INSERT INTO partida(jugador1, jugador2,ganador,tiempo) VALUES('P1','P2','P1','01:00')");

        System.out.println("Se creó la BBDD");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //insertar datos
    public  void insertarPartida(String p1, String p2, String ganador, String tiempo){
        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("jugador1",p1);
            values.put("jugador2",p2);
            values.put("ganador",ganador);
            values.put("tiempo",tiempo);
            bbdd.insert("partida",null,values);

        } catch (Exception ex){
            System.err.println("Algo falló");
        }
    }

    //obtener Partidas
    public ArrayList<Partida> obtenerPartidas() {
        SQLiteDatabase bbdd = this.getWritableDatabase();
        ArrayList<Partida> partidas = new ArrayList<Partida>();
        Partida p;
        Cursor cursor = bbdd.rawQuery("Select * from partida", null);

        if (cursor.moveToFirst()) {
            do {

                p = new Partida(cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));
                partidas.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return partidas;
    }

}
