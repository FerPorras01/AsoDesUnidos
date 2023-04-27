package com.asoDesUnidos.dataAccessObjects;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.asoDesUnidos.modelos.Usuario;

@Dao
public interface UsuarioDAO {
    @Insert
    void insertAll(Usuario... usuarios);

    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsuarios();

    @Query("SELECT * FROM usuario WHERE nombre = :username")
    Usuario findByUsername(String username);

    @Insert
    long insertUser(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE nombre = :username AND clave= :password")
    Usuario findByUsernameAndPassword(String username, String password);
    }