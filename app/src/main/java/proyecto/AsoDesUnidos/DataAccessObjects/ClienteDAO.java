package proyecto.AsoDesUnidos.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import proyecto.AsoDesUnidos.Modelos.Ahorro;
import proyecto.AsoDesUnidos.Modelos.Cliente;

@Dao
public interface ClienteDAO {
    @Insert
    void insertAll(Cliente... clientes);

    @Query("SELECT * FROM cliente")
    List<Cliente> getAllClientes();

    @Insert
    long insertarCliente(Cliente cliente);
    @Delete
    void delete(Cliente... clientes);

    @Update
    public void updateClients(Cliente... clientes);

    @Query("SELECT * FROM cliente")
    public Cliente[] loadAllClients();

    @Query("SELECT * FROM cliente where idUsuario = :idUsuario;")
    Cliente getClienteByIdUsuario(int idUsuario);

    @Query("SELECT * FROM cliente where cedula = :cedula;")
    Cliente getClienteByCedula(String cedula);
}


