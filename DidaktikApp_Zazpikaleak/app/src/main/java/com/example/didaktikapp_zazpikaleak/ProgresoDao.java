package com.example.didaktikapp_zazpikaleak;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProgresoDao {

    public boolean crearBBDD(ZazpiKaleakSQLiteHelper zazpidbh) {

        // Se crea la base de datos con todos los valores en falso
        SQLiteDatabase db = zazpidbh.getWritableDatabase();
        if (db != null){
            for (int i =1; i<=7; i++){
                String nombre ="Actividad "+i;
                db.execSQL("INSERT INTO Progreso (actividad, hecho)" +
                        " VALUES ('"+nombre+"',False) ");
            }
        }
        db.close();
        return true;
    }

    // Marcar como hecha una actividad
    public void actHecha(ZazpiKaleakSQLiteHelper zazpidbh, String nombre){
        SQLiteDatabase db = zazpidbh.getWritableDatabase();
        if (db != null) {
            db.execSQL("UPDATE Progreso SET hecho=True WHERE actividad='" + nombre+"'");
        }
        db.close();
    }

    // En caso de resetear la partida
    public void juegoNuevo(ZazpiKaleakSQLiteHelper zazpidbh){
        SQLiteDatabase db = zazpidbh.getWritableDatabase();
        if(db != null) {
            db.execSQL("UPDATE Progreso SET hecho=False");
        }
        db.close();
    }

    // En caso de continuar la partida
    public int actividadesHechas(ZazpiKaleakSQLiteHelper zazpidbh){
        int cuantos = 0;
        SQLiteDatabase db = zazpidbh.getReadableDatabase();
        if(db != null) {
            Cursor c = db.rawQuery("SELECT actividad FROM Progreso " +
                    "WHERE hecho = True", null);
            cuantos = c.getCount();
            c.close();
        }
        db.close();
        System.out.println(cuantos+"");
        return cuantos;
    }
}
