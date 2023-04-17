package proyecto.AsoDesUnidos.Utiles;

import android.content.Context;
import android.widget.EditText;

public class Utiles {

    public static boolean verificarCampo(EditText campo, Context context){
        if(campo.getText().toString().isBlank()){
            campo.setError("El campo \"" + campo.getHint() + "\" no puede estar vacío.");
            return false;
        }
        campo.setError(null);
        return true;
    }
}
