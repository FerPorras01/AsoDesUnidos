package proyecto.AsoDesUnidos.Utiles;

import android.widget.EditText;

public class Utiles {
    public static boolean verificaCampo(EditText editText){
        return editText.toString().isBlank();
    }
}
