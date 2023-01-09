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

public class Zona1_5 extends AppCompatActivity {

    private ImageView fotoAntigua;
    private TextView pista;
    private MediaPlayer audio;
    private Button btnSiguiente;
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
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnSiguiente.setVisibility(View.VISIBLE);
            }
        },5000);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Zona1_5.this, Zona2_1.class);

                //Creas un objeto dao y marcas la actividad como hecha en la bbdd
                ProgresoDao pd = new ProgresoDao();
                pd.actHecha(zazpidbh, "Actividad 1");

                startActivity(intent);
            }
        });


    }
}