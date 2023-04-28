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

        //Se hace un administrador quemado la primera vez para poder acceder al módulo de Administrador
        //y poder crear/manipular clientes
        Usuario usuario1= new Usuario("admin", "clave","cliente");

        db.usuarioDAO().insertAll(usuario1);
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
