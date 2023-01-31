package com.example.didaktikapp_zazpikaleak;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Zona6_2 extends AppCompatActivity {

    TextView txtPregunta1, txtPregunta2, txtPregunta3, txtPregunta4;
    Button btnSiguente,  btnfallo1, btnfallo2, btnfallo3, btnfallo4;
    ImageButton btnacierto1, btnacierto2, btnacierto3, btnacierto4;
    private Zona6_2_Dialogo dialogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_zona6_2);

        btnSiguente = findViewById(R.id.zona4_2btnSiguiente);

        txtPregunta1 = findViewById(R.id.Zona4_2txtPregunta1);
        txtPregunta2 = findViewById(R.id.Zona4_2txtPregunta2);
        txtPregunta3 = findViewById(R.id.Zona4_2txtPregunta3);
        txtPregunta4 = findViewById(R.id.Zona4_2txtPregunta4);


        btnacierto1 = findViewById(R.id.zona4_2_btnacierto1);
        btnacierto2 = findViewById(R.id.zona4_2_btnacierto2);
        btnacierto3 = findViewById(R.id.zona4_2_btnacierto3);
        btnacierto4 = findViewById(R.id.zona4_2_btnacierto4);

        btnfallo1 = findViewById(R.id.zona4_2_btnfallo1);
        btnfallo2 = findViewById(R.id.zona4_2_btnfallo2);
        btnfallo3 = findViewById(R.id.zona4_2_btnfallo3);
        btnfallo4 = findViewById(R.id.zona4_2_btnfallo4);

        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogo = new Zona6_2_Dialogo();
        dialogo.show(fragmentManager, "Información Juego");

    }
    //Accion al pulsar un boton verde
    public void pulsarAcierto(View view){
        //Buscamos cual de los cuatro botones se ha pulsado
        switch (view.getId()){
            case R.id.zona4_2_btnacierto1:
                //Hacemos que la pregunta acertado no se pueda editar y ponemos visbles los botones de la siguiente
                txtPregunta1.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_bien);
                btnacierto1.setEnabled(false);
                btnfallo1.setEnabled(false);
                btnacierto2.setVisibility(View.VISIBLE);
                btnfallo2.setVisibility(View.VISIBLE);;

                break;
            case R.id.zona4_2_btnacierto2:
                txtPregunta2.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_bien);
                btnacierto2.setEnabled(false);
                btnfallo2.setEnabled(false);
                btnacierto3.setVisibility(View.VISIBLE);
                btnfallo3.setVisibility(View.VISIBLE);
                break;
            case R.id.zona4_2_btnacierto3:
                txtPregunta3.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_bien);
                btnacierto3.setEnabled(false);
                btnfallo3.setEnabled(false);
                btnacierto4.setVisibility(View.VISIBLE);
                btnfallo4.setVisibility(View.VISIBLE);
                break;
            case R.id.zona4_2_btnacierto4:
                txtPregunta4.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_bien);
                btnacierto4.setEnabled(false);
                btnfallo4.setEnabled(false);
                break;
        }
        if(!btnacierto1.isEnabled() && !btnacierto2.isEnabled() && !btnacierto3.isEnabled() && !btnacierto4.isEnabled()){
            btnSiguente.setEnabled(true);
        }
    }

    //Accion al pulsar un boton rojo
    public void pulsarFallo(View view){
        switch (view.getId()){
            case R.id.zona4_2_btnfallo1:
                //Comprobamos si
                if(btnfallo1.getText().equals("x")){
                    btnfallo1.setText("1");
                }
                else{
                    int num = Integer.parseInt((String) btnfallo1.getText());
                    btnfallo1.setText(String.valueOf(num+1));
                }
                txtPregunta1.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_mal);
                break;
            case R.id.zona4_2_btnfallo2:
                if(btnfallo2.getText().equals("x")){
                    btnfallo2.setText("1");
                }
                else{
                    int num = Integer.parseInt((String) btnfallo2.getText());
                    btnfallo2.setText(String.valueOf(num+1));
                }
                txtPregunta2.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_mal);
                break;

            case R.id.zona4_2_btnfallo3:
                if(btnfallo3.getText().equals("x")){
                    btnfallo3.setText("1");
                }
                else{
                    int num = Integer.parseInt((String) btnfallo3.getText());
                    btnfallo3.setText(String.valueOf(num+1));
                }
                txtPregunta3.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_mal);
                break;

            case R.id.zona4_2_btnfallo4:
                if(btnfallo4.getText().equals("x")){
                    btnfallo4.setText("1");
                }
                else{
                    int num = Integer.parseInt((String) btnfallo4.getText());
                    btnfallo4.setText(String.valueOf(num+1));
                }
                txtPregunta4.setBackgroundResource(R.drawable.zona3_2_bordes_edittext_mal);
                break;
        }
    }
    public void ayuda(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogo = new Zona6_2_Dialogo();
        dialogo.show(fragmentManager, "Información Juego");
    }

}
