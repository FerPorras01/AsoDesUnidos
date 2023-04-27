package com.asoDesUnidos.dataAccessObjects;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.asoDesUnidos.modelos.Cliente;

@Dao
public interface ClienteDAO {
    @Insert
    void insertAll(Cliente... clientes);

    @Query("SELECT * FROM cliente")
    List<Cliente> getAllClientes();

    @Insert
    long insertarCliente(Cliente cliente);

    @Update
    void updateClients(Cliente... clientes);

    @Query("SELECT * FROM cliente where idUsuario = :idUsuario;")
    Cliente getClienteByIdUsuario(int idUsuario);

    @Query("SELECT * FROM cliente where id = :idCliente;")
    Cliente getClienteById(int idCliente);

    @Query("SELECT * FROM cliente where cedula = :cedula;")
    Cliente getClienteByCedula(String cedula);
}


