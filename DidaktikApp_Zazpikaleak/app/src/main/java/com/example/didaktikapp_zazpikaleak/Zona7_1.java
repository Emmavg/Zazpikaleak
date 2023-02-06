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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

public class Zona7_1 extends AppCompatActivity {

    private MediaPlayer audio;
    private ImageView imgZona7_1_Foto1,imgZona7_1_Foto2,imgZona7_1_Foto3,imgZona7_1_Foto4;
    private FloatingActionButton btnZona7_1_Siguiente;
    private TextView txtZona7_1_Narrador_1, txtZona7_1_Narrador_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona7_1);

        imgZona7_1_Foto1 = findViewById(R.id.imgZona7_1_Foto1);
        imgZona7_1_Foto2 = findViewById(R.id.imgZona7_1_Foto2);
        imgZona7_1_Foto3 = findViewById(R.id.imgZona7_1_Foto3);
        imgZona7_1_Foto4 = findViewById(R.id.imgZona7_1_Foto4);
        btnZona7_1_Siguiente = findViewById(R.id.btnZona7_1_Siguiente);
        txtZona7_1_Narrador_1 = findViewById(R.id.txtZona7_1_Narrador_1);
        txtZona7_1_Narrador_2 = findViewById(R.id.txtZona7_1_Narrador_2);


        final ScrollView scroller1 = findViewById(R.id.scrollerZona7_1_Narrador_1);
        final ScrollView scroller2 = findViewById(R.id.scrollerZona7_1_Narrador_2);

        //Texto 1 Narrador
        txtZona7_1_Narrador_1.setMovementMethod(new ScrollingMovementMethod());
        setText(getString(R.string.txtZona7_1_Narrador_1),txtZona7_1_Narrador_1, 60);
        scroller1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    scroller1.fullScroll(View.FOCUS_DOWN);
                }
            }
        });
        btnZona7_1_Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Zona7_1.this, Zona7_3.class);
                startActivity(i);
                finish();
            }
        });
        //Audio Narrador
        audio = MediaPlayer.create(Zona7_1.this, R.raw.audio_zona7_1_parte1);
        audio.start();

        //Cuando termina aparece la foto
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            //Texto 2 Narrador
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgZona7_1_Foto1.setVisibility(View.VISIBLE);
                imgZona7_1_Foto2.setVisibility(View.VISIBLE);


                txtZona7_1_Narrador_2.setMovementMethod(new ScrollingMovementMethod());
                setText(getString(R.string.txtZona7_1_Narrador_2), txtZona7_1_Narrador_2, 60);
                scroller2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                        if (hasFocus) {
                            scroller2.fullScroll(View.FOCUS_DOWN);
                        }
                    }
                });

                //Audio Narrador 2
                audio = MediaPlayer.create(Zona7_1.this, R.raw.audio_zona7_1_parte2);
                audio.start();

                //Cuando termina aparece la foto
                audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    //Texto 2 Narrador
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        imgZona7_1_Foto3.setVisibility(View.VISIBLE);
                        imgZona7_1_Foto4.setVisibility(View.VISIBLE);

                    }
                });
            }
        });
    }

    //Parar el audio cuando se pulsa el boton back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        audio.stop();
        Intent intent = new Intent(Zona7_1.this, MapaActivity.class);
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