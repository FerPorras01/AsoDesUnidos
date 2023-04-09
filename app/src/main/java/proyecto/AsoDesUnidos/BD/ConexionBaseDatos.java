package proyecto.AsoDesUnidos.BD;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import proyecto.AsoDesUnidos.DataAccessObjects.AhorroDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.ClienteDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.PrestamoDAO;
import proyecto.AsoDesUnidos.DataAccessObjects.UsuarioDAO;
import proyecto.AsoDesUnidos.Modelos.Ahorro;
import proyecto.AsoDesUnidos.Modelos.Cliente;
import proyecto.AsoDesUnidos.Modelos.Prestamo;
import proyecto.AsoDesUnidos.Modelos.Usuario;


@Database(entities = {Cliente.class, Usuario.class, Ahorro.class, Prestamo.class}, version = 1, exportSchema = false)
    public abstract class ConexionBaseDatos extends RoomDatabase {

        public abstract ClienteDAO clienteDAO();

        public abstract UsuarioDAO usuarioDAO();

        public abstract AhorroDAO ahorroDAO();

        public abstract PrestamoDAO prestamoDAO();
    }


