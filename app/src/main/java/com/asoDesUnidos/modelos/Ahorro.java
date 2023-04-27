package com.asoDesUnidos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Cliente.class, parentColumns = "id", childColumns = "idCliente")})
public class Ahorro {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "idCliente", index = true)
    private final int idCliente;

    @ColumnInfo(name = "cuota")
    private Double cuota;

    @ColumnInfo(name = "tipo")
    private final String tipo;
    @ColumnInfo(name = "totalAhorrado")
    private Double totalAhorrado;
    @ColumnInfo(name = "estado")
    private Boolean estado;
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

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getTotalAhorrado() {
        return totalAhorrado;
    }

    public void setTotalAhorrado(Double totalAhorrado) {
        this.totalAhorrado = totalAhorrado;
    }

    public Boolean getEstado() {return estado;}
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}