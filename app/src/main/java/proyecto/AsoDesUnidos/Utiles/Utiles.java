package proyecto.AsoDesUnidos.Utiles;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioGroup;

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
}
