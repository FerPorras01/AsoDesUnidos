package proyecto.AsoDesUnidos.Controladores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.time.LocalDate;
import java.util.List;
import proyecto.AsoDesUnidos.BD.ConexionBaseDatos;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.Modelos.Prestamo;
import proyecto.AsoDesUnidos.Modelos.Usuario;
import proyecto.AsoDesUnidos.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

    ConexionBaseDatos db = Room.databaseBuilder(getApplicationContext(),
            ConexionBaseDatos.class, "database-name").allowMainThreadQueries().build();
    Intent login = new Intent(this, LoginActivity.class);
    login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP) ;
    this.startActivity(login);
    finish();


    Usuario usuario1= new Usuario("mario12", "jsjsjdjsha22*","cliente");
    Usuario usuario2= new Usuario("maria89", "jsjsjdooha28*","cliente");
    Usuario usuario3= new Usuario("fernanda18", "jueyeubcdf18+","administrador");

    Cliente mario= new Cliente(1,"402450378", "Mario", 255.000, "7667-5665", LocalDate.of(2010,05,01),"soltero", "400 metros oeste del HSVP");
    Cliente maria= new Cliente(2,"402450320", "Maria", 355.000, "7688-5665", LocalDate.of(2000,02,10),"Soltero", "300 metros oeste del HSVP");
    Cliente fer= new Cliente(3, "402450321", "Fernanda", 555.000, "8546-5164", LocalDate.of(2001,02,10),"Soltero", "400 metros oeste del parque de San Pedro");


    Prestamo pre1=new Prestamo(1,0.0,"Personal", 10, LocalDate.of(2010,05,01));
    Prestamo pre2=new Prestamo(1,0.0,"Educacion", 8, LocalDate.of(2010,05,01));

         //db.usuarioDAO().insertAll(usuario1, usuario2, usuario3);
        // db.clienteDAO().insertAll(mario, maria, fer);
         //db.prestamoDAO().insertAll(pre1, pre2);
    List<Cliente> clienteList=db.clienteDAO().getAllClientes();
    List<Usuario> usuarioList=db.usuarioDAO().getAllUsuarios();
    List<Prestamo> prestamoList=db.prestamoDAO().getAllPrestamos();

        for(Usuario list: usuarioList){
            Log.d("Usuario", list.nombre + " " + list.clave + " " + list.rol);
        }
        for(Cliente list: clienteList){
            Log.d("Cliente", list.cedula + " " + list.nombre + " " + list.salario + " " + list.telefono + " " + list.fecNac + " " + list.estCivil + " " + list.direccion);
        }
        for(Prestamo list: prestamoList){
            Log.d("Prestamo", list.monto + " " + list.tipo + " " + list.interes + " " + list.periodo);
        }
    }
    }
