package com.example.didaktikapp_zazpikaleak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Zona2_1 extends AppCompatActivity {

    private FloatingActionButton zona2_1_btnCamara, zona2_1_btnSiguiente;
    private ImageView zona2_1_imgView;
    private RelativeLayout dindon;
    private MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona2_1);
        zona2_1_btnCamara = findViewById(R.id.zona2_1_btnCamara);
        zona2_1_imgView = findViewById(R.id.zona2_1_imageView);
        zona2_1_btnSiguiente = findViewById(R.id.zona2_1_btnSiguiente);

        dindon = findViewById(R.id.zona2_1_dindon);

        zona2_1_btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Zona2_1.this, Zona2_4.class);
                startActivity(intent);
                finish();
            }
        });

        zona2_1_btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();

            }
        });
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            zona2_1_imgView.setImageBitmap(imgBitmap);
            dindon.setVisibility(View.VISIBLE);

            //Audio Dindong
            audio = MediaPlayer.create(Zona2_1.this, R.raw.audio_zona2_2_dindong);
            audio.start();
        }
    }

    //Cuando se pulsa el boton back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Zona2_1.this, MapaActivity.class);
        startActivity(intent);
    }


}