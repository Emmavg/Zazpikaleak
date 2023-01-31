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


public class Zona1_1 extends AppCompatActivity implements Zona1_2_Dialogo.OnDialogoConfirmacionListener{

    private MediaPlayer audio;
    private ImageView imgZona1_1_Foto1,imgZona1_1_Foto2,imgZona1_1_Foto3,imgZona1_1_Foto4, imgZona1_1_Foto5;
    private FloatingActionButton btnZona1_1_Siguiente;
    private TextView txtZona1_1_Narrador_1, txtZona1_1_Narrador_2, explicacionDialogo;
    private Zona1_2_Dialogo dialogo;

    //Las acciónes que queremos que se ejecuten cuando se inicia la actividad
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona1_1);

        imgZona1_1_Foto1 = findViewById(R.id.imgZona1_1_Foto1);
        imgZona1_1_Foto2 = findViewById(R.id.imgZona1_1_Foto2);
        imgZona1_1_Foto3 = findViewById(R.id.imgZona1_1_Foto3);
        imgZona1_1_Foto4 = findViewById(R.id.imgZona1_1_Foto4);
        imgZona1_1_Foto5 = findViewById(R.id.imgZona1_1_Foto5);
        btnZona1_1_Siguiente = findViewById(R.id.btnZona1_1_Siguiente);
        txtZona1_1_Narrador_1 = findViewById(R.id.txtZona1_1_Narrador_1);
        txtZona1_1_Narrador_2 = findViewById(R.id.txtZona1_1_Narrador_2);
        explicacionDialogo = findViewById(R.id.txtDialogo);

        final ScrollView scroller1 = findViewById(R.id.scrollerZona1_1_Narrador_1);
        final ScrollView scroller2 = findViewById(R.id.scrollerZona1_1_Narrador_2);

        //Texto 1 Narrador
        txtZona1_1_Narrador_1.setMovementMethod(new ScrollingMovementMethod());
        setText(getString(R.string.txtZona1_Narrador_1),txtZona1_1_Narrador_1, 60);
        scroller1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    scroller1.fullScroll(View.FOCUS_DOWN);
                }
            }
        });
        //Audio Narrador
        audio = MediaPlayer.create(Zona1_1.this, R.raw.audio_zona1_parte1);
        audio.start();

        //Cuando termina aparece la foto
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

            //Texto 2 Narrador
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgZona1_1_Foto3.setVisibility(View.VISIBLE);
                imgZona1_1_Foto4.setVisibility(View.VISIBLE);

                txtZona1_1_Narrador_2.setMovementMethod(new ScrollingMovementMethod());
//                txtZona1_1_Narrador_2.setText("");
                setText(getString(R.string.txtZona1_Narrador_2),txtZona1_1_Narrador_2, 60);
                scroller2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                        if (hasFocus) {
                            scroller2.fullScroll(View.FOCUS_DOWN);
                        }
                    }
                });

                //Audio Narrador 2
                audio = MediaPlayer.create(Zona1_1.this, R.raw.audio_zona1_parte2);
                audio.start();

                audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        imgZona1_1_Foto5.setVisibility(View.VISIBLE);

                        //Audio Narrador 3
                        audio = MediaPlayer.create(Zona1_1.this, R.raw.audio_zona1_parte3);
                        audio.start();

                        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
//                                imgZona1_1_Foto5.setVisibility(View.VISIBLE);

                            }
                        });

                    }
                });

            }
        });

        btnZona1_1_Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogo = new Zona1_2_Dialogo();
                dialogo.show(fragmentManager, "Informacion Parada");
                audio.stop();
            }
        });

    }

    @Override
    public void onPossitiveButtonClick() {
        // Marcamos la actividad como hecha en la base de datos pasandole el nombre de la base de datos
        ZazpiKaleakSQLiteHelper zazpidbh = new ZazpiKaleakSQLiteHelper(getBaseContext(), "ZazpikaleakDB", null, 1);
        ProgresoDao pd = new ProgresoDao();
        pd.actHecha(zazpidbh,"Actividad 1");

        Intent intent = new Intent(Zona1_1.this, MapaActivity.class);
        startActivity(intent);
        finish();
    }

    //Parar el audio cuando se pulsa el boton back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Zona1_1.this, MapaActivity.class);
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