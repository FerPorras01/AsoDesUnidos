package proyecto.AsoDesUnidos.Controladores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.Controladores.Admin.AdminActivity;
import proyecto.AsoDesUnidos.DataAccessObjects.ClienteDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.UsuarioDAO;
import proyecto.AsoDesUnidos.Modelos.Usuario;
import proyecto.AsoDesUnidos.R;


public class LoginActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtClave;
    public static final String IDUSUARIO = "Login.idUsuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtNombre = findViewById(R.id.txtNombre);
        txtClave = findViewById(R.id.txtClave);
        Button btnIngresar = findViewById(R.id.btnIngresar);
        Button btnSalir = findViewById(R.id.btnSalir);


        //inicializacion de BD.



        btnIngresar.setOnClickListener(view->Ingresar());
        btnSalir.setOnClickListener(view->salir());


        //Configurar botón de Ingresar segun rol


        // Configurar botón de Salir

    }
    public void Ingresar() {
        ConexionBaseDatos db = Room.databaseBuilder(getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        String nombreUsuario;
        String clave;
        Usuario usuario;
        UsuarioDAO usuarioDAO = db.usuarioDAO();
        SharedPreferences sharedPreferences = getSharedPreferences("inicio_sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent intent = null;
        if (verificarCampo(txtNombre, txtClave)) {
            nombreUsuario = txtNombre.getText().toString();
            clave = txtClave.getText().toString();
            usuario = usuarioDAO.findByUsernameAndPassword(nombreUsuario, clave);
            if (usuario != null) {
                editor.putInt("id", usuario.id);
                editor.putString("nombre_usuario", nombreUsuario);
                editor.putString("clave", clave);
                editor.putString("rol", usuario.getRol());
                editor.apply();
                if (usuario.getRol().equals("administrador")) {
                    intent = new Intent(this, AdminActivity.class);
                } else {
                    ClienteDAO clienteDAO = db.clienteDAO();

                    //intent = new Intent(this, ClientActivity.class);
                }
                intent.putExtra(IDUSUARIO, usuario);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void salir(){
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