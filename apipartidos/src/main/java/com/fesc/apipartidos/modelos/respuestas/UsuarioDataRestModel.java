package com.fesc.apipartidos.modelos.respuestas;

import java.util.List;

public class UsuarioDataRestModel {

    private String idUsuario;
    private String nombre;
    private String email;
    private String username;

    private List<PartidoDataRestModel> partidoDataRestModelList;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PartidoDataRestModel> getPartidoDataRestModelList() {
        return partidoDataRestModelList;
    }

    public void setPartidoDataRestModelList(List<PartidoDataRestModel> partidoDataRestModelList) {
        this.partidoDataRestModelList = partidoDataRestModelList;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

}
