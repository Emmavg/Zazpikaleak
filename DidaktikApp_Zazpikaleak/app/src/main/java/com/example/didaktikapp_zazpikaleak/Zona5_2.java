package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Timer;
import java.util.TimerTask;

public class Zona5_2 extends AppCompatActivity {

    private MediaPlayer audio;
    private ImageView imgZona5_2_Foto1,imgZona5_2_Foto2,imgZona5_2_Foto3;
    private Button btnZona5_2_Siguiente;
    private TextView txtZona5_2_Narrador_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona5_2);

        imgZona5_2_Foto1 = findViewById(R.id.imgZona5_2_Foto1);
        imgZona5_2_Foto2 = findViewById(R.id.imgZona5_2_Foto2);
        imgZona5_2_Foto3 = findViewById(R.id.imgZona5_2_Foto3);
        btnZona5_2_Siguiente = findViewById(R.id.btnZona5_2_Siguiente);
        txtZona5_2_Narrador_1 = findViewById(R.id.txtZona5_2_Narrador_1);

        final ScrollView scroller1 = findViewById(R.id.scrollerZona5_2_Narrador_1);

        //Texto 1 Narrador
        txtZona5_2_Narrador_1.setMovementMethod(new ScrollingMovementMethod());
        setText(getString(R.string.txtZona5_2_Narrador_1),txtZona5_2_Narrador_1, 60);
        scroller1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    scroller1.fullScroll(View.FOCUS_DOWN);
                }
            }
        });
        //Audio Narrador
        audio = MediaPlayer.create(Zona5_2.this, R.raw.audio_zona5_2);
        audio.start();

        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
              @Override
              public void onCompletion(MediaPlayer mediaPlayer) {
                  imgZona5_2_Foto3.setVisibility(View.VISIBLE);
              }
          }
        );
        btnZona5_2_Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Zona5_2.this, MapaActivity.class);

                // Marcamos la actividad como hecha en la base de datos pasandole el nombre de la base de datos
                ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
                ProgresoDao pd = new ProgresoDao();
                pd.actHecha(zazpidbh,"Actividad 5");

                startActivity(intent);
                finish();
            }
        });
    }

    //Parar el audio cuando se pulsa el boton back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        audio.stop();
        Intent intent = new Intent(Zona5_2.this, Zona5_1.class);
        startActivity(intent);
        finish();
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