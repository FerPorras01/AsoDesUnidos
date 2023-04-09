package proyecto.AsoDesUnidos.DataAccessObjects;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import proyecto.AsoDesUnidos.Modelos.Ahorro;

@Dao
public interface AhorroDAO {
    @Insert
    void insertAll(Ahorro... ahorros);

    @Query("SELECT * FROM ahorro")
    List<Ahorro> getAllAhorros();

    @Delete
    void delete(Ahorro... ahorro);

    @Update
    public void updateAhorros(Ahorro... ahorros);

    @Query("SELECT * FROM ahorro")
    public Ahorro[] loadAllAhorros();
}