package com.example.didaktikapp_zazpikaleak;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;



public class Lienzo extends View{
    private  int anchoTotal;
    float x = 100;
    float y = 100;
    String accion = "accion";
    Path path = new Path();

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void onDraw(Canvas canvas){
        Paint paint = new Paint();

        //Seleccionamos el tipo de linea para dibujar
        paint.setStyle(Paint.Style.STROKE);

        //Seleccionamos el grosor de las lineas
        paint.setStrokeWidth(25);

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
        canvas.drawRect(100, 900, ancho-100, 100, paint);


        canvas.drawPath(path, paint);

    }

    public boolean onTouchEvent(MotionEvent e){

        //Comprobamos que donde toca de la pantalla esta dentro del cuadrado marcado
        if(e.getY()<=900 && e.getY()>=100 && e.getX()>=100 && e.getX()<=anchoTotal-100){
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

