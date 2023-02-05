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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainAlumnos extends AppCompatActivity implements MainAlumnos_Dialogo.OnDialogoConfirmacionListener, MainAlumnos_Borrar_Dialogo.OnDialogoConfirmacionListener {

    private ListView vista;
    private ArrayList<String> alum;
    private FloatingActionButton addAlum;
    private MainAlumnos_Dialogo dialogo;
    private MainAlumnos_Borrar_Dialogo dialogoBorrar;
    private String opcionSeleccionada;

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
                dialogo = new MainAlumnos_Dialogo();
                dialogo.show(fragmentManager, "Añadir Jugador");
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
                opcionSeleccionada =
                        (String) parent.getItemAtPosition(position);
                //long opcion = parent.getItemIdAtPosition(position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogoBorrar = new MainAlumnos_Borrar_Dialogo();
                dialogoBorrar.show(fragmentManager, "Eliminar JugadorS");
            }
        });
    }

    @Override
    public void onPossitiveButtonClick() {
        String nombre = ((EditText) dialogo.getDialog().findViewById(R.id.editTextJugador)).getText().toString();
        if (!nombre.isEmpty()){
            ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
            AlumnoDao ad = new AlumnoDao();
            if (ad.insertarAlumno(zazpidbh, nombre)){
                Toast.makeText(this, "Jugador añadido correctamente" , Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(MainAlumnos.this, MainAlumnos.class);
            startActivity(i);
            finish();
        }

    }

    @Override
    public void onPossitiveBorrarButtonClick() {
        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
        AlumnoDao ad = new AlumnoDao();
        if(ad.borrarAlumno(zazpidbh, opcionSeleccionada)){
            Toast.makeText(this, "Jugador eliminado correctamente" , Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainAlumnos.this, MainAlumnos.class);
            startActivity(i);
            finish();
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