package com.example.didaktikapp_zazpikaleak;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class Zona1_3 extends AppCompatActivity implements Zona1_3_Dialogo.OnDialogoConfirmacionListener{

    // variables para los componentes de la vista
    private ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09;
    private ImageButton[] tablero = new ImageButton[10];
    private RelativeLayout rlayout;
    private FloatingActionButton botonReiniciar, botonSiguiente;
    private MediaPlayer audio;
    private int aciertos;
    private Zona1_3_Dialogo dialogo;

    //imagenes
    int[] imagenes;
    int fondo;

    //variables del juego
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    int numeroPrimero, numeroSegundo;
    boolean bloqueo = false;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona1_3);
        rlayout = findViewById(R.id.zona1_3_dindon);

        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogo = new Zona1_3_Dialogo();
        dialogo.show(fragmentManager, "Información Juego");

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }


    private void cargarTablero(){
        imb00 = findViewById(R.id.zona1_3_btn0);
        imb01 = findViewById(R.id.zona1_3_btn1);
        imb02 = findViewById(R.id.zona1_3_btn2);
        imb03 = findViewById(R.id.zona1_3_btn3);
        imb04 = findViewById(R.id.zona1_3_btn4);
        imb05 = findViewById(R.id.zona1_3_btn5);
        imb06 = findViewById(R.id.zona1_3_btn6);
        imb07 = findViewById(R.id.zona1_3_btn7);
        imb08 = findViewById(R.id.zona1_3_btn8);
        imb09 = findViewById(R.id.zona1_3_btn9);

        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
    }

    private void cargarBotones(){
        botonReiniciar = findViewById(R.id.zona1_3_botonJuegoReiniciar);
        botonSiguiente = findViewById(R.id.zona1_3_btnSiguiente);

        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogo = new Zona1_3_Dialogo();
                dialogo.show(fragmentManager, "Informacion Juego");


                rlayout.setVisibility(View.INVISIBLE);
                botonSiguiente.setVisibility(View.INVISIBLE);
                puntuacion();
            }
        });

        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zona1_3.this, Zona1_5.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void puntuacion(){
        aciertos = 0;
    }

    private void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.memory1,
                R.drawable.memory2,
                R.drawable.memory3,
                R.drawable.memory4,
                R.drawable.memory5
        };
        fondo = R.drawable.interrogacion;
    }

    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0; i<longitud*2; i++){
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        return result;
    }

    private void comprobar(int i, final ImageButton imgb){
        if(primero == null){
            primero = imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arrayDesordenado.get(i)]);
            primero.setEnabled(false);
            numeroPrimero = arrayDesordenado.get(i);
        } else {
            bloqueo = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arrayDesordenado.get(i)]);
            imgb.setEnabled(false);
            numeroSegundo = arrayDesordenado.get(i);
            if(numeroPrimero == numeroSegundo){
                primero = null;
                bloqueo = false;
                aciertos++;
                if(aciertos == imagenes.length){
                    //Boton Siguiente Visible
                    botonSiguiente.setVisibility(View.VISIBLE);
                    rlayout.setVisibility(View.VISIBLE);
                    //Audio Din Dong Feliz
                    audio = MediaPlayer.create(Zona1_3.this, R.raw.audio_zona1_dindongfeliz);
                    audio.start();
                }
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(fondo);
                        imgb.setEnabled(true);
                        bloqueo = false;
                        primero = null;
                    }
                }, 1000);
            }
        }
    }

    private void init(){
        cargarTablero();
        cargarBotones();
        puntuacion();
        cargarImagenes();

        arrayDesordenado = barajar(imagenes.length);
        for(int i=0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<tablero.length; i++){
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 2000);
        for(int i=0; i<tablero.length; i++) {
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo)
                        comprobar(j, tablero[j]);
                }
            });
        }

    }

    @Override
    public void onPossitiveButtonClick() {
        init();
    }

    //Cuando se pulsa el boton back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Zona1_3.this, MapaActivity.class);
        startActivity(intent);
    }
}
