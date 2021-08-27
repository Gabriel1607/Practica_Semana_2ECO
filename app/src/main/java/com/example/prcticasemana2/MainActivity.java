package com.example.prcticasemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private TextView preguntaTxt;
private TextView tiempoTxt;
private EditText respuestaUser;
private Button responderButton;
private TextView puntajeTxt;
private Pregunta preguntaActual;
private int puntaje;
private int tiempoRest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias
        preguntaTxt = findViewById(R.id.preguntaTxt);
        tiempoTxt = findViewById(R.id.tiempoTxt);
        respuestaUser = findViewById(R.id.respuestaUser);
        responderButton = findViewById(R.id.ResponderButton);
        puntajeTxt = findViewById(R.id.puntajeTxt);
        puntaje=0;
        tiempoRest=30;
       // preguntaActual= generarPregunta();
        preguntaTxt.setText(preguntaActual.getPregunta());
        tiempoTxt.setText(""+tiempoRest);
        puntajeTxt.setText("Puntaje: "+puntaje);

        responderButton.setOnClickListener(
                (v) -> {
verificarResp();
        }
        );
    }

   // private Pregunta generarPregunta() {

   // }

    private void verificarResp() {
        String respTxt = respuestaUser.getText().toString();
        int respuestaInt = (int) Integer.parseInt(respTxt);
        if (respuestaInt==preguntaActual.responder()){
            Toast.makeText(this,"Correcto!",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Incorrecto!",Toast.LENGTH_SHORT).show();
        }
    }
}