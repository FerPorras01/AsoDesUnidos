package proyecto.AsoDesUnidos.Controladores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import java.time.LocalDate;
import java.util.List;
import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.Controladores.Admin.AdminActivity;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.Modelos.Prestamo;
import proyecto.AsoDesUnidos.Modelos.Usuario;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ConexionBaseDatos db = Room.databaseBuilder(getApplicationContext(),
                ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
        Intent intent = null;
        SharedPreferences sharedPreferences= getSharedPreferences("inicio_sesion", Context.MODE_PRIVATE);
        int idUsuario = sharedPreferences.getInt("id", -1);
        String nombreUsuario = sharedPreferences.getString("nombre_usuario", null), clave = sharedPreferences.getString("clave", null),
        rol = sharedPreferences.getString("rol", null);
        Usuario usuario;
        if(idUsuario != -1){
            switch(sharedPreferences.getString("rol", null)){
                case "administrador":
                    intent = new Intent(this, AdminActivity.class);
                    break;
                case "Cliente":
                    //intent = new Intent(this, ClientActivity.class);
                    break;
            }
            assert intent != null;
            usuario = new Usuario(nombreUsuario, clave, rol);
            usuario.setID(idUsuario);
            intent.putExtra(LoginActivity.IDUSUARIO, usuario);
        }
        else {
            intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }

        this.startActivity(intent);
        finish();


        Usuario usuario1= new Usuario("mario12", "jsjsjdjsha22*","cliente");
        Usuario usuario2= new Usuario("maria89", "jsjsjdooha28*","cliente");
        Usuario usuario3= new Usuario("fernanda18", "jueyeubcdf18+","administrador");

        Prestamo pre1=new Prestamo(1,0.0,"Personal", 10, LocalDate.of(2010,05,01));
        Prestamo pre2=new Prestamo(1,0.0,"Educacion", 8, LocalDate.of(2010,05,01));

         //db.usuarioDAO().insertAll(usuario1, usuario2, usuario3);
        // db.clienteDAO().insertAll(mario, maria, fer);
         //db.prestamoDAO().insertAll(pre1, pre2);
    List<Cliente> clienteList=db.clienteDAO().getAllClientes();
    List<Usuario> usuarioList=db.usuarioDAO().getAllUsuarios();
    List<Prestamo> prestamoList=db.prestamoDAO().getAllPrestamos();

        //db.usuarioDAO().insertAll(usuario1, usuario2, usuario3);
        //db.clienteDAO().insertAll(mario, maria, fer);

        for(Cliente list: clienteList){
            Log.d("Cliente", list.cedula + " " + list.nombre + " " + list.salario + " " + list.telefono + " " + list.fecNac + " " + list.estCivil + " " + list.direccion);
        }
        for(Prestamo list: prestamoList){
            Log.d("Prestamo", list.monto + " " + list.tipo + " " + list.interes + " " + list.periodo);
        }
    }
    public static void cerrarSesion(Activity activity){
        activity.getSharedPreferences("inicio_sesion", Context.MODE_PRIVATE).edit().clear().apply();
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    public static void deseleccionarMenu(Menu menu){
        menu.setGroupCheckable(0, true, false);
        for(int i=0; i< menu.size(); i++){
            menu.getItem(i).setChecked(false);
        }
        menu.setGroupCheckable(0, true, true);
    }
}
