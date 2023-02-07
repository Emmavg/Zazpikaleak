package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Zona1_5 extends AppCompatActivity {

    private ImageView fotoAntigua;
    private TextView pista;
    private MediaPlayer audio;
    private FloatingActionButton btnSiguiente;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona1_5);

        fotoAntigua = findViewById(R.id.imgZona1_5_Foto);
        pista = findViewById(R.id.txtZona1_5_Hint);
        btnSiguiente = findViewById(R.id.zona1_5_btnSiguiente);

        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(this, "ZazpikaleakDB", null, 1);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Audio Narrador
                audio = MediaPlayer.create(Zona1_5.this, R.raw.audio_zona1_5);
                audio.start();
            }
        },1000);


        fotoAntigua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pista.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnSiguiente.setVisibility(View.VISIBLE);
                    }
                },1000);
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Zona1_5.this, MapaActivity.class);

                // Marcamos la actividad como hecha en la base de datos pasandole el nombre de la base de datos
                ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
                ProgresoDao pd = new ProgresoDao();
                pd.actHecha(zazpidbh,"Actividad 11");
                startActivity(intent);
                finish();
            }
        });

    }

    //Parar el audio cuando se pulsa el boton back
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Zona1_5.this, Zona1_3.class);
        startActivity(intent);
        finish();
        audio.stop();
    }
}