package com.asoDesUnidos.bd;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.asoDesUnidos.dataAccessObjects.AhorroDAO;
import com.asoDesUnidos.dataAccessObjects.ClienteDAO;
import com.asoDesUnidos.dataAccessObjects.PrestamoDAO;
import com.asoDesUnidos.dataAccessObjects.UsuarioDAO;
import com.asoDesUnidos.modelos.Ahorro;
import com.asoDesUnidos.modelos.Cliente;
import com.asoDesUnidos.modelos.Prestamo;
import com.asoDesUnidos.modelos.Usuario;


@Database(entities = {Cliente.class, Usuario.class, Ahorro.class, Prestamo.class}, version = 1, exportSchema = false)
    public abstract class ConexionBaseDatos extends RoomDatabase {

        public abstract ClienteDAO clienteDAO();

        public abstract UsuarioDAO usuarioDAO();

        public abstract AhorroDAO ahorroDAO();

        public abstract PrestamoDAO prestamoDAO();

    }



