package com.asoDesUnidos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;

import com.asoDesUnidos.bd.DateConverter;

@TypeConverters(DateConverter.class)
@Entity(foreignKeys = {@ForeignKey(entity = Usuario.class, parentColumns = "id", childColumns = "idUsuario")}, indices = {@Index(value = {"idUsuario"}, unique = true),
        @Index(value = {"cedula"}, unique = true)})
public class Cliente {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="idUsuario")
    private final int idUsuario;
    @ColumnInfo(name = "cedula")
    private final String cedula;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "salario")
    private Double salario;
    @ColumnInfo(name = "telefono")
    private String telefono;

    @ColumnInfo(name = "fecNac")
    private LocalDate fecNac;
    @ColumnInfo(name = "estCivil")
    private String estCivil;
    @ColumnInfo(name = "direccion")
    private String direccion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecNac() {
        return fecNac;
    }

    public void setFecNac(LocalDate fecNac) {
        this.fecNac = fecNac;
    }

    public String getEstCivil() {
        return estCivil;
    }

    public void setEstCivil(String estCivil) {
        this.estCivil = estCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

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
