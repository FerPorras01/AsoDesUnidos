package com.asoDesUnidos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(indices = {@Index(value = {"nombre"}, unique = true)})
public class Usuario implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nombre")
    private final String nombre;

    @ColumnInfo(name = "clave")
    private final String clave;

    @ColumnInfo(name = "rol")
    private final String rol;


    public Usuario(String nombre, String clave, String rol){
        this.nombre=nombre;
        this.clave=clave;
        this.rol=rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
