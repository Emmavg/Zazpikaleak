package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Zona7_3 extends AppCompatActivity {

    private final HashMap<TextView, Boolean> palabras = new HashMap<>();

    private final String[] letras = new String[] {
            "E","R","A","T","U","N","P","S","P","T","V","C","E","P",
            "G","U","K","M","L","E","I","O","E","B","E","B","T","C",
            "U","X","S","E","E","B","P","I","N","T","X","O","S","O",
            "L","S","U","K","K","R","O","Ñ","P","M","E","O","O","L",
            "Y","M","O","M","A","F","C","L","T","P","Y","N","B","U",
            "P","Y","P","L","B","L","U","A","J","H","I","L","L","M",
            "N","B","U","P","I","N","T","X","D","S","O","Z","M","N",
            "M","Ñ","Q","N","A","S","H","Z","I","I","T","X","H","A",
            "A","K","T","W","T","P","D","M","A","B","L","M","P","S",
            "R","A","L","V","O","S","L","J","M","I","W","L","E","B",
            "C","J","B","A","R","E","S","Y","P","O","N","L","O","W",
            "O","T","M","S","T","T","H","R","T","U","B","D","O","M",
            "S","E","T","N","A","R","U","A","T","S","E","R","I","Ñ",
            "U","J","B","T","S","B","L","V","T","B","M","U","Z","A"
    };
    private TextView sopatxt;
    private TextView resultado;
    private Button btnSiguiente;



    Rect rectant = new Rect();
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona7_3);
        cargarMapa();


        // Quitar la barra de notificaciones, bateria, hora, etc
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        TextView resultado = findViewById(R.id.resultado);

        Button btnSiguiente = findViewById(R.id.zona7_3btn_Siguiente);

        GridView sopa = findViewById(R.id.sopaLetras);
        sopa.setHorizontalSpacing(30);
        sopa.setVerticalSpacing(30);

        ArrayAdapter<String> listaAdapter = new ArrayAdapter<>(this, R.layout.activity_zona7_3sopaestr, letras);
        sopa.setAdapter(listaAdapter);

        final String[] anterior = {""};
        ArrayList<View> listaAcertada = new ArrayList<View>();
        ArrayList <View> listaPosible = new ArrayList<View>();
        sopa.setOnTouchListener((view, motionEvent) -> {

            for(int i = 0; i < sopa.getChildCount(); i++) {
                View v = sopa.getChildAt(i);

                Rect outRecto = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                if(outRecto.contains((int)motionEvent.getX(), (int)motionEvent.getY())) {
                    if(!outRecto.equals(rectant)) {
                        resultado.append(((TextView) v).getText());
                        rectant=outRecto;
                        v.setBackgroundColor(Color.RED);
                        listaPosible.add(v);
                        boolean posible = false;

                        for(TextView palabra : palabras.keySet()) {
                            if(!palabras.get(palabra)) {
                                if (palabra.getText().toString().toUpperCase().startsWith(resultado.getText().toString())) {
                                    posible = true;

                                }
                                if (palabra.getText().toString().toUpperCase().equals(resultado.getText().toString())) {
                                    palabra.setTextColor(Color.parseColor("#37962E"));
                                    palabras.put(palabra,true);

                                    if(comprobarAcertadas()){
                                        btnSiguiente.setEnabled(true);
                                    }

                                    for(View vposi:listaPosible){
                                        listaAcertada.add(vposi);
                                    }
                                    for(View vpal : listaAcertada){
                                        vpal.setBackgroundColor(Color.GREEN);
                                        //listaPosible.remove(vpal);
                                    }
                                    posible=true;

                                    //comprobarMapa();
                                    resultado.setText("");
                                }
                            }
                        }
                        if(!posible) {
                            resultado.setText("");

                            for(View vcam: listaPosible){
                                vcam.setBackgroundColor(getColor(R.color.fondo));

                            }
                            for(View vac: listaAcertada){
                                vac.setBackgroundColor(Color.GREEN);
                            }
                            listaPosible.clear();
                        }
                    }
                }
            }
            return false;
        });
    }
    private void cargarMapa() {
        palabras.put((TextView)findViewById(R.id.zona7_txtmercadillo), false);
        palabras.put((TextView)findViewById(R.id.zona7_txtcolumnas), false);
        palabras.put((TextView)findViewById(R.id.zona7_txteuskaltzaindia), false);
        palabras.put((TextView)findViewById(R.id.zona7_txtbares), false);
        palabras.put((TextView)findViewById(R.id.zona7_txtpintxos), false);
        palabras.put((TextView)findViewById(R.id.zona7_txtarcos), false);
        palabras.put((TextView)findViewById(R.id.zona7_txtrestaurantes), false);
    }

    private boolean comprobarAcertadas(){

        for(TextView palabra : palabras.keySet()){
            if(palabras.get(palabra)==false){
                return false;
            }
        }
        return true;
    }






}
