package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Desarrollador extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desarrollador);

        btn1 = findViewById(R.id.desarrollador_btn1);
        btn2 = findViewById(R.id.desarrollador_btn2);
        btn3 = findViewById(R.id.desarrollador_btn3);
        btn4 = findViewById(R.id.desarrollador_btn4);
        btn5 = findViewById(R.id.desarrollador_btn5);
        btn6 = findViewById(R.id.desarrollador_btn6);
        btn7 = findViewById(R.id.desarrollador_btn7);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Desarrollador.this, Zona1_3.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Desarrollador.this, Zona2_1.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Desarrollador.this, Zona3_2.class);
                startActivity(i);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Desarrollador.this, Zona4_1.class);
                startActivity(i);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Desarrollador.this, Zona4_5.class);
                startActivity(i);
         }
       });

//        btn6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Desarrollador.this, Zona1_3.class);
//                startActivity(i);
//            }
//        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Desarrollador.this, Zona7_3.class);
                startActivity(i);
            }
        });

    }
}