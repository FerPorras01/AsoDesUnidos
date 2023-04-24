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
    @ColumnInfo(name = "idCliente", index = true)
    public int idCliente;
    @ColumnInfo(name = "cuota")
    public Double cuota;

    @ColumnInfo(name = "tipo")
    public String tipo;

    @ColumnInfo(name = "totalAhorrado")
    public Double totalAhorrado;

    @ColumnInfo(name = "estado")
    public Boolean estado;
    public Ahorro(int idCliente, Double cuota, String tipo, Double totalAhorrado, Boolean estado){
        this.idCliente=idCliente;
        this.cuota=cuota;
        this.tipo=tipo;
        this.totalAhorrado=totalAhorrado;
        this.estado=estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getTotalAhorrado() {
        return totalAhorrado;
    }

    public void setTotalAhorrado(Double totalAhorrado) {
        this.totalAhorrado = totalAhorrado;
    }

    public Boolean isActivo() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}