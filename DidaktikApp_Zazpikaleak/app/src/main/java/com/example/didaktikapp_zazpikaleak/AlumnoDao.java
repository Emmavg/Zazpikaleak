package com.example.didaktikapp_zazpikaleak;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AlumnoDao {

    public boolean insertarAlumno(ZazpiKaleakSQLiteHelper zazpidbh, String nombre) {

        SQLiteDatabase db = zazpidbh.getWritableDatabase();
        if (db != null){
            db.execSQL("INSERT INTO Alumno (nombre)" +
                    " VALUES ('"+nombre+"')");
        }
        db.close();
        return true;
    }

    // Guardar todos los nombres de los alumnos en un array
    public ArrayList<String> cogerAlumnos(ZazpiKaleakSQLiteHelper zazpidbh){
        SQLiteDatabase db = zazpidbh.getWritableDatabase();
        ArrayList<String> alumnos = new ArrayList<String>();
        if (db != null) {
            Cursor c =db.rawQuery("SELECT * FROM Alumno", null);
            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()){
                //Recorremos el cursor hasta que no haya más registros.
                do {
                    alumnos.add (c.getString(0));
                }while (c.moveToNext());
            }
        }
        db.close();
        return alumnos;
    }

    // En caso de resetear la partida
    public void juegoNuevo(ZazpiKaleakSQLiteHelper zazpidbh){
        SQLiteDatabase db = zazpidbh.getWritableDatabase();
        if(db != null) {
            db.execSQL("DELETE FROM Alumno");
        }
        db.close();
    }
}
