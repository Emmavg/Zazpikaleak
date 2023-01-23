package com.example.didaktikapp_zazpikaleak;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Zona4_2 extends AppCompatActivity {

    TextView txtPregunta1;
    Button btnSiguente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona4_2);
        txtPregunta1 = findViewById(R.id.Zona4_2txtPregunta1);
        btnSiguente = findViewById(R.id.zona4_2btnSiguiente);


        //****************************** Comprobar la Pregunta **************************
        btnSiguente.setOnClickListener(new View.OnClickListener(){


            public void onClick(View view) {
                System.out.println("*********************************");
                System.out.println(txtPregunta1.getText().toString().toLowerCase(Locale.ROOT));
                if (txtPregunta1.getText().toString().toLowerCase(Locale.ROOT).contains("calle pelota")){
                    txtPregunta1.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_bien);
                }
                else{
                    txtPregunta1.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_mal);
                }
            }
        });


    }



}
