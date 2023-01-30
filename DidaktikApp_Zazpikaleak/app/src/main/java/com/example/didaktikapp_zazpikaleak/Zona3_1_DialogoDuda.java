package com.example.didaktikapp_zazpikaleak;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Zona3_1_DialogoDuda extends DialogFragment {

    private Zona3_1_DialogoDuda.OnDialogoConfirmacionListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_zona3_1_dialogoduda, null))
                .setTitle("Pasos Del Juego")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        return builder.create();
    }

    public interface OnDialogoConfirmacionListener{
        void onPossitiveButtonClick(); //Eventos Botón Positivos
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try{
//            listener = (Zona3_1_DialogoDuda.OnDialogoConfirmacionListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() +
//                    " no Implemento OnDialogoConfirmacionListener");
//        }
//    }
}