package com.asoDesUnidos.dataAccessObjects;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.asoDesUnidos.modelos.Ahorro;

@Dao
public interface AhorroDAO {
    @Insert
    void insertAll(Ahorro... ahorros);

    @Query("SELECT * FROM ahorro")
    List<Ahorro> getAllAhorros();

    @Update
    void updateAhorros(Ahorro... ahorros);

    @Query("SELECT * FROM ahorro WHERE id = :id")
    Ahorro getAhorroById(int id);
    @Query("UPDATE ahorro SET totalAhorrado = totalAhorrado + :cuota WHERE id = :ahorroId")
    void updateAhorroSaldo(double cuota, int ahorroId);

    @Query("SELECT * FROM ahorro WHERE idCliente = :clienteId")
    List<Ahorro> findByClienteId(int clienteId);

}