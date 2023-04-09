package proyecto.AsoDesUnidos.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import proyecto.AsoDesUnidos.Modelos.Usuario;

@Dao
public interface UsuarioDAO {
    @Insert
    void insertAll(Usuario... usuarios);

    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsuarios();

    @Insert
    void insertUser(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE nombre = :username AND clave= :password")
    Usuario findByUsernameAndPassword(String username, String password);

    @Delete
    void delete(Usuario... usuarios);

    @Update
    public void updateUsers(Usuario... usuarios);

    @Query("SELECT * FROM usuario")
    public Usuario[] loadAllUsuarios();


}