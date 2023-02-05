package com.example.didaktikapp_zazpikaleak;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MainAlumnos_Dialogo extends DialogFragment {

    private OnDialogoConfirmacionListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        setCancelable(false);
        // Hay que inflarlo si o sí para que aparezcatodo el contenido de la ventana
        builder.setView(inflater.inflate(R.layout.activity_main_alumnos_dialogo, null))
                //Añadimos un botón de aceptar
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onPossitiveButtonClick();
                            }
                        })
                //Añadimos un botón de cancelar
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    //Creamos una interfaz con las funciones asociadas a los botones.
    //Luego en el main activity, implementaremos la interfaz y las funciones
    public interface OnDialogoConfirmacionListener {
        void onPossitiveButtonClick(); //Eventos Botón Positivos
    }

    //Hay que añadir esto para que no salga un error
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (OnDialogoConfirmacionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +" no Implemento OnDialogoConfirmacionListener");
        }
    }


}


