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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vista vista = new Vista(this);
        setContentView(R.layout.activity_zona4_5);
        vista.setBackgroundColor(Color.parseColor("#ffc771"));
        setContentView(vista);
    }

    class Vista extends View {
        float x = 50;
        float y = 50;
        String accion = "accion";
        Path path = new Path();
        int anchoPantalla;
        public Vista(Context context) {
            super(context);
        }


        public void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(40);
            paint.setColor(Color.BLACK);

            int ancho = canvas.getWidth();
            anchoPantalla = canvas.getWidth();
            if(accion=="down")
                path.moveTo(x,y);
            if(accion=="move")
                path.lineTo(x,y);


            canvas.drawRect(100, 1500, ancho-100, 700, paint);
            canvas.drawPath(path, paint);

        }


        public boolean onTouchEvent(MotionEvent e) {
            x = e.getX();
            y = e.getY();

            if(x>=100 && x<=anchoPantalla-100  && y>=700 && y<=1500){
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    accion = "down";
                }
                if (e.getAction() == MotionEvent.ACTION_MOVE) {
                    accion = "move";
                }
                invalidate();
            }


            return true;
        }

    }
}
