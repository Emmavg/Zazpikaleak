package com.example.didaktikapp_zazpikaleak;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Zona4_5 extends AppCompatActivity {

    private  int anchoTotal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona4_5);
        Vista vista = new Vista(this);
        setContentView(vista);
    }




    class Vista extends View {
        float x = 100;
        float y = 100;
        String accion = "accion";
        Path path = new Path();

        public Vista (Context context){
            super(context);
        }


        public void onDraw(Canvas canvas){
            Paint paint = new Paint();

            //Seleccionamos el tipo de linea para dibujar
            paint.setStyle(Paint.Style.STROKE);

            //Seleccionamos el grosor de las lineas
            paint.setStrokeWidth(15);

            //Seleccionamos el color de las lineas
            paint.setColor(Color.BLACK);

            int ancho = canvas.getWidth();
            anchoTotal=canvas.getWidth();

            //Dependiendo del movimiento detectado dibuja de una manera u otra
            if(accion=="down"){
                path.moveTo(x, y);
            }
            if(accion=="move"){
                path.lineTo(x, y);
            }
            //Dibujamos el cuadrado para poder dibujar
            canvas.drawRect(100, 1500, ancho-100, 700, paint);


            canvas.drawPath(path, paint);

        }

        public boolean onTouchEvent(MotionEvent e){

            //Comprobamos que donde toca de la pantalla esta dentro del cuadrado marcado
            if(e.getY()<=1500 && e.getY()>=700 && e.getX()>=100 && e.getX()<=anchoTotal-100){
                x = e.getX();
                y = e.getY();
                //Comprobamos el movimiento del dedo
                if(e.getAction()== MotionEvent.ACTION_DOWN){
                    accion="down";
                }
                if(e.getAction()== MotionEvent.ACTION_MOVE){
                    accion="move";
                }
                invalidate();
            }

            return true;
        }
    }
}
