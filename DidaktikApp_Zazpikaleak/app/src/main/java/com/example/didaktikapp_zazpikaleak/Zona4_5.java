package com.example.didaktikapp_zazpikaleak;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Zona4_5 extends AppCompatActivity {

    private static Lienzo lienzo;

    private FloatingActionButton btnZona4_5_Siguiente;
    private Button borrar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona4_5);

        btnZona4_5_Siguiente = findViewById(R.id.btnZona4_5_Siguiente);
        borrar = findViewById(R.id.btnZona4_5_Borrar);

        lienzo = (Lienzo)findViewById(R.id.layZona4_5_lienzo);


        btnZona4_5_Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Zona4_5.this, Zona4_6.class);
                startActivity(intent);
                finish();
            }
        });


        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

    }
}

