package com.asoDesUnidos.dataAccessObjects;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.asoDesUnidos.modelos.Prestamo;

@Dao
public interface PrestamoDAO {
    @Insert
    void insertAll(Prestamo... prestamos);

    @Query("SELECT * FROM prestamo")
    List<Prestamo> getAllPrestamos();

    @Update
    void updatePrestamos(Prestamo...prestamo);

    @Query("SELECT * FROM prestamo WHERE idCliente = :clienteId")
    List<Prestamo> findByClienteId(int clienteId);
}
