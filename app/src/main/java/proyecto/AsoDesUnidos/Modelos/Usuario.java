package proyecto.AsoDesUnidos.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(indices = {@Index(value = {"nombre"}, unique = true)})
public class Usuario implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "clave")
    public String clave;

    @ColumnInfo(name = "rol")
    public String rol;


    public Usuario(String nombre, String clave, String rol){
        this.nombre=nombre;
        this.clave=clave;
        this.rol=rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getRol() {
        return rol;
    }

    public void setID(int id) {this.id = id;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
