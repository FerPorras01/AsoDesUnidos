package proyecto.AsoDesUnidos.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;

import proyecto.AsoDesUnidos.BD.DateConverter;

@Entity(foreignKeys = {@ForeignKey(entity = Cliente.class, parentColumns = "id", childColumns = "idCliente")})
public class Ahorro {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "idCliente")
    public int idCliente;
    @ColumnInfo(name = "monto")
    public Double monto;

    @ColumnInfo(name = "tipo")
    public String tipo;

    @ColumnInfo(name = "totalAhorrado")
    public Double totalAhorrado;

    public Ahorro(Double monto, String tipo, Double totalAhorrado){
        this.monto=monto;
        this.tipo=tipo;
        this.totalAhorrado=totalAhorrado;
    }

}