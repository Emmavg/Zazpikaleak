package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

public class Zona2_4 extends AppCompatActivity implements Zona2_5_Dialogo.OnDialogoConfirmacionListener{

    private MediaPlayer audio, audioDindong;
    private ImageView imgZona2_4_Foto1,imgZona2_4_Foto2;
    private FloatingActionButton btnZona2_4_Siguiente;
    private TextView txtZona2_4_Narrador_1, txtZona2_4_Narrador_2;
    private Zona2_5_Dialogo dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona2_4);
        imgZona2_4_Foto1 = findViewById(R.id.imgZona2_4_Foto1);
        imgZona2_4_Foto2 = findViewById(R.id.imgZona2_4_Foto2);
        btnZona2_4_Siguiente = findViewById(R.id.zona2_4_btnSiguiente);
        txtZona2_4_Narrador_1 = findViewById(R.id.txtZona2_4_Narrador_1);
        txtZona2_4_Narrador_2 = findViewById(R.id.txtZona2_4_Narrador_2);

        //Texto 1 Narrador
        txtZona2_4_Narrador_1.setMovementMethod(new ScrollingMovementMethod());
        setText(getString(R.string.txtZona2_4_Narrador_1),txtZona2_4_Narrador_1, 60);

        //Audio Narrador
        audio = MediaPlayer.create(Zona2_4.this, R.raw.audio_zona2_4_parte1);
        audio.start();

        //Cuando termina aparece la foto
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){


            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgZona2_4_Foto2.setVisibility(View.VISIBLE);

                //Texto 1 Narrador
                txtZona2_4_Narrador_2.setMovementMethod(new ScrollingMovementMethod());
                setText(getString(R.string.txtZona2_4_Narrador_2),txtZona2_4_Narrador_2, 60);

                //Audio Narrador
                audio = MediaPlayer.create(Zona2_4.this, R.raw.audio_zona2_4_parte2);
                audio.start();



            }
        });


        btnZona2_4_Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogo = new Zona2_5_Dialogo();
                dialogo.show(fragmentManager, "Informacion Parada");
                audio.stop();

                //Audio Dindong
                audioDindong = MediaPlayer.create(Zona2_4.this, R.raw.audio_zona2_5_dindong);
                audioDindong.start();

            }
        });



    }

    @Override
    public void onPossitiveButtonClick() {
        Intent intent = new Intent(Zona2_4.this, MapaActivity.class);

        // Marcamos la actividad como hecha en la base de datos pasandole el nombre de la base de datos
        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
        ProgresoDao pd = new ProgresoDao();
        pd.actHecha(zazpidbh,"Actividad 2");

        startActivity(intent);
        finish();

    }




    //Parar el audio cuando se pulsa el boton back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Zona2_4.this, Zona2_1.class);
        startActivity(intent);
        finish();
        audio.stop();
    }


    //Se visualiza el texto palabra por palabra
    public void setText(final String s, TextView txt, int velocidad)
    {
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                //Log.d("Strange",""+c);
                txt.append(String.valueOf(c));
                i[0]++;
            }
        };

        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, velocidad);
    }
}