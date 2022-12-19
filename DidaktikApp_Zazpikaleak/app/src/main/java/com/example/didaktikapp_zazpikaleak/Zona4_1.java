package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.didaktikapp_zazpikaleak.Zona4_1DialogoGrupos;

import java.util.ArrayList;
import java.util.HashMap;

public class Zona4_1 extends AppCompatActivity {
    private Button btnGrupos,btnComprobar, btnSiguiente;
    private EditText txtRima;
    private Zona4_1DialogoGrupos dialogoGrupos;
    private RelativeLayout relativeDidon1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona4_1);

        txtRima=findViewById(R.id.Zona4_1txtRima);
        btnGrupos=findViewById(R.id.Zona4_1btnGrupos);
        btnComprobar=findViewById(R.id.Zona4_1btnComprobar);
        btnSiguiente=findViewById(R.id.Zona4_1btnSiguiente);
        relativeDidon1= findViewById(R.id.Zona4_1relativeDidon);


//*************************** Cogemos el array de la base de datos se lo pasamos a un arrayList ************************************

        String[] arrAlumnos = {"Pepe", "Juan", "Iker", "Emma", "Miguel", "Airam", "Alayn", "Xavi", "Xabi", "Javi", "Pablo"};
        ArrayList<String> listaAlumnos = new ArrayList<>();
        for (String s : arrAlumnos) {
            listaAlumnos.add(s);
        }

//**************** Creamos una variable para saber cuántos grupos tenemos que hacer y hacemos un random de dichos alumnos ************************************

        ArrayList<String> alumnos = new ArrayList<>();
        int cantGrupos = listaAlumnos.size() / 3;
        if (listaAlumnos.size() % 3 != 0) {
            cantGrupos++;
        }

        while (listaAlumnos.size() > 0) {
            int a = (int) (Math.random() * listaAlumnos.size());
            alumnos.add(listaAlumnos.get(a));
            listaAlumnos.remove(a);
        }


//************************** Asignar Arraylist a Array para pasarselo al Dialogo a la vez que creamos un mapara para manteber dichos grupos de 3 ************************************

        String[] total = new String[alumnos.size() + cantGrupos];
        int cont = 1;
        int pos = 0;
        HashMap<Integer, ArrayList<String>> mapaGrupos = new HashMap<>();
        for (int i = 0; i < total.length; i++) {
            if (i % 3 == 0) {
                total[i] = "Grupo " + cont + ":";

                //**************** Parte Mapa ******************
                mapaGrupos.put(cont, new ArrayList<String>());

                cont++;
            } else {
                total[i] = "\t\t\t" + alumnos.get(pos);

                //**************** Parte Mapa ******************
                mapaGrupos.get(cont - 1).add(alumnos.get(pos));

                pos += 1;
            }
        }

//********************************* Mostrar Grupos ***************************

        btnGrupos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirDialogo(total);
                txtRima.setEnabled(true);
            }

        });
//********************************* Comprobar que la rima contenga la palabra baldosa ***************************
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rima= String.valueOf(txtRima.getText());
                if(txtRima.length()>1 && rima.toLowerCase().contains("baldosa")){
                    txtRima.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_bien);
                    relativeDidon1.setVisibility(View.VISIBLE);
                    btnSiguiente.setVisibility(view.VISIBLE);

                }else{
                    txtRima.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_mal);
                    relativeDidon1.setVisibility(View.INVISIBLE);
                    btnSiguiente.setVisibility(view.INVISIBLE);
                }
            }
        });
//********************************* Accion del boton siguiente ***************************
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Zona4_1.this, Zona4_5.class);
                startActivity(i);
            }
        });
    }
    private void abrirDialogo(String[] total) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogoGrupos = new Zona4_1DialogoGrupos(total);
        dialogoGrupos.show(fragmentManager, "Hacer Grupos");
    };


}