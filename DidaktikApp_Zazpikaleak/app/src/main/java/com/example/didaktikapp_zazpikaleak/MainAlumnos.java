package com.example.didaktikapp_zazpikaleak;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAlumnos extends AppCompatActivity {

    private ListView vista;
    private ArrayList<String> alum;
    private ArrayList<Alumno> arrLAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alumnos);

        vista = (ListView) findViewById(R.id.vista);

        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
        AlumnoDao ad = new AlumnoDao();
        //ad.insertarAlumno(zazpidbh, "Emma");

        alum = ad.cogerAlumnos(zazpidbh);

        for (String s : alum){
            System.out.println(s+"**********************************************");
            arrLAlumnos.add(new Alumno(s));
        }

        for (Alumno s : arrLAlumnos){
            System.out.println(s.getNombre()+"**********************************************");
        }


        //Adaptador
        AdaptadorAlumnos adapt =
                new AdaptadorAlumnos(this, arrLAlumnos);
        vista.setAdapter(adapt);

        //Eventos
        vista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Seleccion del alumno:
                String opcionSeleccionada =
                        ((Alumno)parent.getItemAtPosition(position)).getNombre();
                long opcion = parent.getItemIdAtPosition(position);
            }
        });
    }

    class AdaptadorAlumnos extends ArrayAdapter<Alumno> {
        public AdaptadorAlumnos(@NonNull Context context, ArrayList<Alumno> a) {
            super(context, R.layout.listitem_alumnos, a);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_alumnos, null);
            TextView nombre = (TextView)item.findViewById(R.id.nombre);
            nombre.setText(arrLAlumnos.get(position).getNombre());
            return (item);
        }
    }
}