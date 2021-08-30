package com.example.prcticasemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
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
private Button retryButton;
private TextView puntajeTxt;
private Pregunta preguntaActual;
private int puntaje;
private int tiempoRest;
private boolean isPressing;
private int tiempoPuls;
private boolean runTime;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias
        preguntaTxt = findViewById(R.id.preguntaTxt);
        tiempoTxt = findViewById(R.id.tiempoTxt);
        respuestaUser = findViewById(R.id.respuestaUser);
        respuestaUser.setInputType( InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        responderButton = findViewById(R.id.ResponderButton);
        retryButton = findViewById(R.id.retryButton);
        puntajeTxt = findViewById(R.id.puntajeTxt);
        puntaje=0;
        tiempoRest=30;
        isPressing=false;
        runTime=true;
        generarPregunta();
        tiempoTxt.setText(""+tiempoRest);
        puntajeTxt.setText("Puntaje: "+puntaje);
        reintentar();

//Intentar de nuevo
        retryButton.setOnClickListener(
                (v) -> {
                   reintentar();

                }
        );
        responderButton.setOnClickListener(
                (v) -> {
                    if(tiempoRest>0){
verificarResp();
        }else{
                        Toast.makeText(this,"Presiona Intentar de Nuevo",Toast.LENGTH_SHORT).show();
                    }}
        );
        //SKIP PREGUNTA
        preguntaTxt.setOnTouchListener(
               (view, motionEvent) -> {
                   switch (motionEvent.getAction()){
                       case MotionEvent.ACTION_DOWN:
                          isPressing=true;
                          new Thread(
                                  () ->{
                        tiempoPuls=0;
                        while (tiempoPuls<1500){
                            try {
                                Thread.sleep(1);
                                tiempoPuls++;
                                if(!isPressing){
                                    return;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        runOnUiThread(() ->{
                            Toast.makeText(this,"Pregunta omitida!",Toast.LENGTH_SHORT).show();
                            generarPregunta();
                        });
                                  }
                          ).start();
                           break;
                   }
                   return true;
               }
        );

    }

    private void reintentar() {
        generarPregunta();
        puntaje=0;
        puntajeTxt.setText("Puntaje:"+puntaje);
        tiempoRest=30;
        tiempoTxt.setText(""+tiempoRest);
        respuestaUser.setText("");
        retryButton.setVisibility(View.GONE);
        new Thread(
                () ->{
                    while(tiempoRest>0){
                        runOnUiThread(
                                ()->{
                                    retryButton.setVisibility(View.GONE);
                                }
                        );

                        tiempoRest--;
                        runOnUiThread(
                                () ->{
                                    tiempoTxt.setText(""+tiempoRest);
                                }
                        );
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    runOnUiThread(
                            ()->{
                                retryButton.setVisibility(View.VISIBLE);
                            }
                    );

                }
        ).start();

    }

    private void generarPregunta() {
preguntaActual = new Pregunta();
preguntaTxt.setText(preguntaActual.getPregunta());
    }

    private void verificarResp() {
        String respTxt = respuestaUser.getText().toString();
        respuestaUser.setText("");
        int respuestaInt = (int) Integer.parseInt(respTxt);
        if (respuestaInt==preguntaActual.responder()){
            Toast.makeText(this,"Correcto!",Toast.LENGTH_SHORT).show();
            puntaje+=5;
            puntajeTxt.setText("Puntaje:"+puntaje);
        }else{
            Toast.makeText(this,"Incorrecto!",Toast.LENGTH_SHORT).show();
            puntaje-=4;
            puntajeTxt.setText("Puntaje:"+puntaje);
        }
        generarPregunta();
    }
}