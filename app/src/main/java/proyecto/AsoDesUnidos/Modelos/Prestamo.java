package proyecto.AsoDesUnidos.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;

import proyecto.AsoDesUnidos.BD.DateConverter;

@TypeConverters(DateConverter.class)
@Entity(foreignKeys = {@ForeignKey(entity = Cliente.class, parentColumns = "id", childColumns = "idCliente")})
public class Prestamo {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "idCliente")
    public int idCliente;
    @ColumnInfo(name = "monto")
    public Double monto;

    @ColumnInfo(name = "tipo")
    public String tipo;

    @ColumnInfo(name = "interes")
    public float interes;

    @ColumnInfo(name = "periodo")
    public LocalDate periodo;

    public Prestamo(int idCliente, Double monto, String tipo, float interes, LocalDate periodo){
        this.idCliente=idCliente;
        this.monto=monto;
        this.tipo=tipo;
        this.interes=interes;
        this.periodo=periodo;
    }
}

