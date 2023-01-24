package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView titulo, creditos;
    private Button empezar;
    private DialogoCreditos dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Generamos la conexion de la BBDD

        //Abrimos la base de datos con nombre: ZazpikaleakDB en modo de escritura solo lo haces la primera vez
//        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(this, "ZazpikaleakDB", null, 1);
//        ProgresoDao pd = new ProgresoDao();
//        pd.crearBBDD(zazpidbh);

        titulo = findViewById(R.id.inicial_txt);
        empezar = findViewById(R.id.btnEmpezar);
        creditos = findViewById(R.id.txtCreditos);

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
                Intent i = new Intent(MainActivity.this, MapaActivity.class);
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
    }
}
