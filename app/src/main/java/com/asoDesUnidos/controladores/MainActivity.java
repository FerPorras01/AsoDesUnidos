package com.asoDesUnidos.controladores;

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
import com.asoDesUnidos.bd.ConexionBaseDatos;
import com.asoDesUnidos.controladores.admin.AdminActivity;
import com.asoDesUnidos.controladores.cliente.ClientActivity;
import com.asoDesUnidos.modelos.Ahorro;
import com.asoDesUnidos.modelos.Cliente;
import com.asoDesUnidos.modelos.Prestamo;
import com.asoDesUnidos.modelos.Usuario;


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
            switch(rol){
                case "administrador":
                    intent = new Intent(this, AdminActivity.class);
                    break;
                case "cliente":
                    intent = new Intent(this, ClientActivity.class);
                    break;
            }
            assert intent != null;
            usuario = new Usuario(nombreUsuario, clave, rol);
            usuario.setId(idUsuario);
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
        Usuario usuario4= new Usuario("sebasAd", "sebas1205", "administrador");

        Cliente mario= new Cliente(1,"402450378", "Mario", 255.000, "7667-5665", LocalDate.of(2010,5,1),"soltero", "400 metros oeste del HSVP");
        Cliente maria= new Cliente(2, "402450320", "Maria", 355.000, "7688-5665", LocalDate.of(2000,2,10),"Soltero", "300 metros oeste del HSVP");
        Cliente fer= new Cliente(3, "402450321", "Fernanda", 555.000, "8546-5164", LocalDate.of(2001,2,10),"Soltero", "400 metros oeste del parque de San Pedro");


        Prestamo pre1=new Prestamo(1,20000.0, (20000.0*0.1)+20000,"Personal", (float) 0.10, (5));
        Prestamo pre2=new Prestamo(1,100000.0,(100000.0*0.08)+100000,"Educacion", (float) 0.08, 10);

        Ahorro aho1 = new Ahorro(1,0.0,"Navideño",0.0, false);
        Ahorro aho2 = new Ahorro(1,0.0,"Escolar",0.0, false);
        Ahorro aho3 = new Ahorro(1,0.0,"Marchamo",0.0, false);
        Ahorro aho4 = new Ahorro(1,0.0,"Extraordinario",0.0, false);

        /*db.usuarioDAO().insertAll(usuario1, usuario2, usuario3, usuario4);
        db.clienteDAO().insertAll(mario, maria, fer);
        db.prestamoDAO().insertAll(pre1, pre2);
        db.ahorroDAO().insertAll(aho1, aho2, aho3, aho4);*/

        List<Cliente> clienteList=db.clienteDAO().getAllClientes();
        List<Usuario> usuarioList=db.usuarioDAO().getAllUsuarios();
        List<Prestamo> prestamoList=db.prestamoDAO().getAllPrestamos();
        List<Ahorro> ahorroList=db.ahorroDAO().getAllAhorros();

        for(Usuario list: usuarioList){
            Log.d("Usuario", list.getNombre() + " " + list.getClave() + " " + list.getRol());
        }

        for(Cliente list: clienteList){
            Log.d("Cliente", list.getCedula() + " " + list.getNombre() + " " + list.getSalario() + " " + list.getTelefono() + " " + list.getFecNac() + " " + list.getEstCivil() + " " + list.getDireccion());
        }
        for(Prestamo list: prestamoList){
            Log.d("Prestamo", list.getMonto() + " " + list.getMontoTotal() + " " + list.getTipo() + " " + list.getInteres() + " " + list.getPeriodo());
        }
        for(Ahorro list: ahorroList){
            Log.d("Ahorro", list.getTipo() + " " + list.getEstado() + " " + list.getCuota() + " " + list.getTotalAhorrado());
        }
    }
    public static void cerrarSesion(Activity activity){
        activity.finish();
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
