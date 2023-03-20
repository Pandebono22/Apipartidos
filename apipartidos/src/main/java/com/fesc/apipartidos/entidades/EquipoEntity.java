package com.fesc.apipartidos.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "equipo")
public class EquipoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoEntityLocal")
    private List<PartidoEntity> partidoEntityLocalList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoEntityVisitante")
    private List<PartidoEntity> equipoEntityVisitanteList = new ArrayList<>();

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PartidoEntity> getPartidoEntityLocalList() {
        return partidoEntityLocalList;
    }

    public void setPartidoEntityLocalList(List<PartidoEntity> partidoEntityLocalList) {
        this.partidoEntityLocalList = partidoEntityLocalList;
    }

    public List<PartidoEntity> getEquipoEntityVisitanteList() {
        return equipoEntityVisitanteList;
    }

    public void setEquipoEntityVisitanteList(List<PartidoEntity> equipoEntityVisitanteList) {
        this.equipoEntityVisitanteList = equipoEntityVisitanteList;
    }

}
