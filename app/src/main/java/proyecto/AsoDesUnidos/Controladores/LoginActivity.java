package proyecto.AsoDesUnidos.Controladores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.Controladores.Cliente.ClientActivity;
import proyecto.AsoDesUnidos.DataAccessObjects.UsuarioDAO;
import proyecto.AsoDesUnidos.Modelos.Usuario;
import proyecto.AsoDesUnidos.R;


public class LoginActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtClave;
    private Button btnIngresar;
    private Button btnSalir;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtNombre = findViewById(R.id.txtNombre);
        txtClave = findViewById(R.id.txtClave);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnSalir = findViewById(R.id.btnSalir);


        //inicializacion de BD.



        btnIngresar.setOnClickListener(view->Ingresar());
        btnSalir.setOnClickListener(view->Salir());


        //Configurar botón de Ingresar segun rol


        // Configurar botón de Salir

    }
    public void Ingresar() {
        ConexionBaseDatos db = Room.databaseBuilder(getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        String username;
        String password;
        usuarioDAO = db.usuarioDAO();
        Usuario user;
        Intent intent;
        if (verificarCampo(txtNombre, txtClave)) {
            username = txtNombre.getText().toString();
            password = txtClave.getText().toString();
            user = usuarioDAO.findByUsernameAndPassword(username, password);
            if (user != null) {
                if (user.getRol().equals("administrador")) {
                    intent = new Intent(this, AdminActivity.class);
                } else {
                    intent = new Intent(this, ClientActivity.class);
                }
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void Salir()
    {

        finish();
    }
    private boolean verificarCampo(EditText campo1, EditText campo2 ){
        boolean verificarCampo1=true, verificarCampo2=true;

        if(campo1.getText().toString().isBlank()){
            campo1.setError("El campo se encuentra vacío");
           verificarCampo1=false;
        }
        if(campo2.getText().toString().isBlank()){
            campo2.setError("El campo se encuentra vacío");
            verificarCampo2=false;
        }
        return verificarCampo1 && verificarCampo2;
    }

}