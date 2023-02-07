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

public class Zona4_6 extends AppCompatActivity {


    private MediaPlayer audio;
    private ImageView imgZona4_6_Foto1,imgZona4_6_Foto2,imgZona4_6_Foto3,imgZona4_6_Foto4,imgZona4_6_Foto5;
    private FloatingActionButton btnZona4_6_Siguiente;
    private TextView txtZona4_6_Narrador_1, txtZona4_6_Narrador_2, txtZona4_6_Narrador_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona4_6);

        imgZona4_6_Foto1 = findViewById(R.id.imgZona4_6_Foto1);
        imgZona4_6_Foto2 = findViewById(R.id.imgZona4_6_Foto2);
        imgZona4_6_Foto3 = findViewById(R.id.imgZona4_6_Foto3);
        imgZona4_6_Foto4 = findViewById(R.id.imgZona4_6_Foto4);
        imgZona4_6_Foto5 = findViewById(R.id.imgZona4_6_Foto5);
        btnZona4_6_Siguiente = findViewById(R.id.btnZona4_6_Siguiente);
        txtZona4_6_Narrador_1 = findViewById(R.id.txtZona4_6_Narrador_1);
        txtZona4_6_Narrador_2 = findViewById(R.id.txtZona4_6_Narrador_2);
        txtZona4_6_Narrador_3 = findViewById(R.id.txtZona4_6_Narrador_3);

        final ScrollView scroller1 = findViewById(R.id.scrollerZona4_6_Narrador_1);
        final ScrollView scroller2 = findViewById(R.id.scrollerZona4_6_Narrador_2);
        final ScrollView scroller3 = findViewById(R.id.scrollerZona4_6_Narrador_3);

        //Texto 1 Narrador
        txtZona4_6_Narrador_1.setMovementMethod(new ScrollingMovementMethod());
        setText(getString(R.string.txtZona4_6_Narrador_1),txtZona4_6_Narrador_1, 60);
        scroller1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    scroller1.fullScroll(View.FOCUS_DOWN);
                }
            }
        });
        //Audio Narrador
        audio = MediaPlayer.create(Zona4_6.this, R.raw.audio_zona4_6_parte1);
        audio.start();

        //Cuando termina aparece la foto
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

            //Texto 2 Narrador
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgZona4_6_Foto1.setVisibility(View.VISIBLE);
                imgZona4_6_Foto2.setVisibility(View.VISIBLE);
                imgZona4_6_Foto3.setVisibility(View.VISIBLE);
                imgZona4_6_Foto4.setVisibility(View.VISIBLE);


                txtZona4_6_Narrador_2.setMovementMethod(new ScrollingMovementMethod());
                setText(getString(R.string.txtZona4_6_Narrador_2),txtZona4_6_Narrador_2, 60);
                scroller2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                        if (hasFocus) {
                            scroller2.fullScroll(View.FOCUS_DOWN);
                        }
                    }
                });

                //Audio Narrador 2
                audio = MediaPlayer.create(Zona4_6.this, R.raw.audio_zona4_6_parte2);
                audio.start();

                audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        imgZona4_6_Foto5.setVisibility(View.VISIBLE);

                        txtZona4_6_Narrador_3.setMovementMethod(new ScrollingMovementMethod());
                        setText(getString(R.string.txtZona4_6_Narrador_3),txtZona4_6_Narrador_3, 60);
                        scroller3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View view, boolean hasFocus) {
                                if (hasFocus) {
                                    scroller3.fullScroll(View.FOCUS_DOWN);
                                }
                            }
                        });

                        //Audio Narrador 3
                        audio = MediaPlayer.create(Zona4_6.this, R.raw.audio_zona4_6_parte3);
                        audio.start();

                    }
                });

            }
        });
        btnZona4_6_Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Zona4_6.this, MapaActivity.class);

                // Marcamos la actividad como hecha en la base de datos pasandole el nombre de la base de datos
                ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
                ProgresoDao pd = new ProgresoDao();
                pd.actHecha(zazpidbh,"Actividad 4");

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
        Intent intent = new Intent(Zona4_6.this, Zona4_5.class);
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