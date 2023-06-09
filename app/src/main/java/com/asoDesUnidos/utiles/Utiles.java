package com.asoDesUnidos.utiles;

import android.widget.EditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utiles {

    public static boolean verificarCampo(EditText campo){
        if(campo.getText().toString().isBlank()){
            campo.setError("El campo \"" + campo.getHint() + "\" no puede estar vacío.");
            return false;
        }
        campo.setError(null);
        return true;
    }

    public static boolean mayorA(EditText campo, int num) {
        if(verificarCampo(campo)) {
            if (Double.parseDouble(campo.getText().toString()) <= num) {
                campo.setError("El campo \"" + campo.getHint() + "\" debe ser mayor a " + num + '.');
                return false;
            }
            campo.setError(null);
            return true;
        }
        return false;
    }

    public static boolean mayorOIgualA(EditText campo, int num) {
        if(verificarCampo(campo)) {
            if (Double.parseDouble(campo.getText().toString()) < num) {
                campo.setError("El campo \"" + campo.getHint() + "\" debe ser mayor o igual a " + num + '.');
                return false;
            }
            campo.setError(null);
            return true;
        }
        return false;
    }

    public static boolean menorA(EditText campo, Double num) {
        if(verificarCampo(campo)) {
            if (Double.parseDouble(campo.getText().toString()) > num) {
                campo.setError("El campo \"" + campo.getHint() + "\" debe ser menor o igual a " + num + '.');
                return false;
            }
            campo.setError(null);
            return true;
        }
        return false;
    }

    public static boolean igualA(EditText campo, int num) {
        if(verificarCampo(campo)) {
            if (campo.getText().toString().length() != num) {
                campo.setError("El campo \"" + campo.getHint() + "\" debe ser tener " + num + " dígitos.");
                return false;
            }
            campo.setError(null);
            return true;
        }
        return false;
    }

    public static boolean diferenteA(EditText campo, double num) {
        if(verificarCampo(campo)) {
            if (campo.getText().toString().length() != num) {
                campo.setError("El campo \"" + campo.getHint() + "\" debe ser tener " + num + " dígitos.");
                return false;
            }
            campo.setError(null);
            return true;
        }
        return false;
    }

    public static LocalDate parsearFecha(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
