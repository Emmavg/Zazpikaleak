package com.example.didaktikapp_zazpikaleak;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Zona4_5 extends AppCompatActivity {

    private static Lienzo lienzo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona4_5);


        lienzo = (Lienzo)findViewById(R.id.layZona4_5_lienzo);

    }
}

