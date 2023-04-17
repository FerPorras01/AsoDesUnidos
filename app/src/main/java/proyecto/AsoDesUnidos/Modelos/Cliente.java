package proyecto.AsoDesUnidos.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;

import proyecto.AsoDesUnidos.BD.DateConverter;

@TypeConverters(DateConverter.class)
@Entity(foreignKeys = {@ForeignKey(entity = Usuario.class, parentColumns = "id", childColumns = "idUsuario")}, indices = {@Index(value = {"idUsuario"}, unique = true)})
public class Cliente {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="idUsuario")
    public int idUsuario;
    @ColumnInfo(name = "cedula")
    public String cedula;
    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "salario")
    public Double salario;
    @ColumnInfo(name = "telefono")
    public String telefono;

    @ColumnInfo(name = "fecNac")
    public LocalDate fecNac;
    @ColumnInfo(name = "estCivil")
    public String estCivil;
    @ColumnInfo(name = "direccion")
    public String direccion;

    public Cliente(int idUsuario, String cedula, String nombre, Double salario, String telefono, LocalDate fecNac, String estCivil, String direccion){
        this.idUsuario=idUsuario;
        this.cedula=cedula;
        this.nombre=nombre;
        this.salario=salario;
        this.telefono=telefono;
        this.fecNac=fecNac;
        this.estCivil=estCivil;
        this.direccion=direccion;
    }
}
