package com.asoDesUnidos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.asoDesUnidos.bd.DateConverter;

@TypeConverters(DateConverter.class)
@Entity(foreignKeys = {@ForeignKey(entity = Cliente.class, parentColumns = "id", childColumns = "idCliente")})
public class Prestamo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "idCliente", index = true)
    private final int idCliente;

    @ColumnInfo(name = "monto")
    private Double monto;

    @ColumnInfo(name = "montoTotal")
    private Double montoTotal;

    @ColumnInfo(name = "tipo")
    private final String tipo;

    @ColumnInfo(name = "interes")
    private final float interes;

    @ColumnInfo(name = "periodo")
    private final int periodo;

    public Prestamo(int idCliente, Double monto, Double montoTotal, String tipo, float interes, int periodo){
        this.idCliente=idCliente;
        this.monto=monto;
        this.montoTotal=montoTotal;
        this.tipo=tipo;
        this.interes=interes;
        this.periodo=periodo;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {this.monto = monto;}

    public String getTipo() {
        return tipo;
    }

    public float getInteres() {
        return interes;
    }

    public int getPeriodo() {
        return periodo;
    }
}

