package com.example.didaktikapp_zazpikaleak;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainAlumnos extends AppCompatActivity implements MainActivity_Dialogo.OnDialogoConfirmacionListener {

    private ListView vista;
    private ArrayList<String> alum;
    private FloatingActionButton addAlum;
    private MainAlumnosDialogo dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alumnos);

        vista = (ListView) findViewById(R.id.vista);
        addAlum = findViewById(R.id.btnAddAlumnos);

        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
        AlumnoDao ad = new AlumnoDao();


        addAlum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogo = new MainAlumnosDialogo();
                dialogo.show(fragmentManager, "Añadir Alumno");
            }
        });

        alum = ad.cogerAlumnos(zazpidbh);

        //Adaptador
        AdaptadorAlumnos adapt =
                new AdaptadorAlumnos(this, alum);
        vista.setAdapter(adapt);

        //Eventos
        vista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Seleccion del alumno:
                String opcionSeleccionada =
                        (String) parent.getItemAtPosition(position);
                //long opcion = parent.getItemIdAtPosition(position);
            }
        });
    }

    @Override
    public void onPossitiveButtonClick() {
        String nombre = ((EditText) dialogo.getDialog().findViewById(R.id.editTextJugador)).getText().toString();
        if (!nombre.isEmpty()){
            ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
            AlumnoDao ad = new AlumnoDao();
            ad.insertarAlumno(zazpidbh, nombre);
        }
    }

    class AdaptadorAlumnos extends ArrayAdapter<String> {
        public AdaptadorAlumnos(@NonNull Context context, ArrayList<String> a) {
            super(context, R.layout.listitem_alumnos, a);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_alumnos, null);
            TextView nombre = (TextView)item.findViewById(R.id.nombre);
            nombre.setText(alum.get(position));
            return (item);
        }
    }
}