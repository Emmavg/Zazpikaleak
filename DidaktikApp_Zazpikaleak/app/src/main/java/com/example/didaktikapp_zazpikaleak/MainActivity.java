package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MainActivity_Dialogo.OnDialogoConfirmacionListener{

    private TextView titulo, creditos, juegoNuevo, continuarJuego;
    private FloatingActionButton empezar, restart, add;
    private DialogoCreditos dialogo;
    private int cont = 0;
    private MainActivity_Dialogo dialogoConfir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Generamos la conexion de la BBDD

        //Abrimos la base de datos con nombre: ZazpikaleakDB en modo de escritura solo lo haces la primera vez
        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(this, "ZazpikaleakDB", null, 1);
        ProgresoDao pd = new ProgresoDao();
        //pd.crearBBDD(zazpidbh);

        titulo = findViewById(R.id.inicial_txt);
        empezar = findViewById(R.id.btnEmpezar);
        restart = findViewById(R.id.btnRestart);
        add = findViewById(R.id.btnAdd);
        creditos = findViewById(R.id.txtCreditos);
        juegoNuevo = findViewById(R.id.dinDonJuegoNuevo);
        continuarJuego = findViewById(R.id.dinDonContinuarJuego);

        juegoNuevo.setVisibility(View.VISIBLE);

        // Diálogo con los créditos
        creditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogo = new DialogoCreditos();
                dialogo.show(fragmentManager, "Créditos");
            }
        });

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (add.getVisibility() == View.VISIBLE || cont != 0){
                    Intent i = new Intent(MainActivity.this, MapaActivity.class);
                    startActivity(i);
                    juegoNuevo.setVisibility(View.INVISIBLE);
                    continuarJuego.setVisibility(View.VISIBLE);
                    cont = 0;
                } else {
                    add.setVisibility(View.VISIBLE);
                    restart.setVisibility(View.VISIBLE);
                    cont ++;
                    
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogoConfir = new MainActivity_Dialogo();
                dialogoConfir.show(fragmentManager, "Precaución!!");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainAlumnos.class);
                startActivity(i);
            }
        });

        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Desarrollador.class);
                startActivity(i);
            }
        });

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }


    @Override
    public void onPossitiveButtonClick() {
        // Marcamos la actividad como hecha en la base de datos pasandole el nombre de la base de datos
        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
        ProgresoDao pd = new ProgresoDao();
        AlumnoDao ad = new AlumnoDao();
        pd.juegoNuevo(zazpidbh);
        ad.juegoNuevo(zazpidbh);


        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

        restart.setVisibility(View.INVISIBLE);
        add.setVisibility(View.INVISIBLE);
        continuarJuego.setVisibility(View.INVISIBLE);
        juegoNuevo.setVisibility(View.VISIBLE);
    }
}
