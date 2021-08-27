package com.example.prcticasemana2;

public class Pregunta {
    //Atributos
    private int op1;
    private int op2;
    private int whichSign;
    private String sign;
    private String[] signs = {"+","-","x","/"};
    //Constructor
    public Pregunta(){
        this.op1 = (int)((Math.random()*10)+1);
        this.op2 = (int)((Math.random()*10)+1);
        this.whichSign = (int)(Math.random()*4);
        this.sign = signs[whichSign];
    }
    //Metodos
    public String getPregunta(){
        return op1+" "+sign+" "+op2;
    }
    public int responder(){
      int respuesta=0;
        switch (sign){
            case "+":
                respuesta= op1+op2;
                break;
            case "-":
                respuesta= op1-op2;
            break;
            case "x":
                respuesta= op1*op2;
            break;
            case "/":
                respuesta= op1/op2;
            break;
        }
        return respuesta;
    }
}
