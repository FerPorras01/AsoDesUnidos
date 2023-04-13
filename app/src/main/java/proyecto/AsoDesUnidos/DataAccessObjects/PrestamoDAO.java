package proyecto.AsoDesUnidos.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import proyecto.AsoDesUnidos.Modelos.Prestamo;

@Dao
public interface PrestamoDAO {
    @Insert
    void insertAll(Prestamo... prestamos);

    @Query("SELECT * FROM prestamo")
    List<Prestamo> getAllPrestamos();

    @Delete
    void delete(Prestamo... prestamos);

    @Update
    void updatePrestamos(Prestamo...prestamos);

    @Query("SELECT * FROM prestamo")
    Prestamo[] loadAllPrestamo();

    @Query("SELECT * FROM prestamo WHERE idCliente = :clienteId")
    List<Prestamo> findByClienteId(int clienteId);
}
