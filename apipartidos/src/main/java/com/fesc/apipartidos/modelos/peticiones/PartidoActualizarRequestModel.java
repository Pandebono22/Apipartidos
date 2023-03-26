package com.fesc.apipartidos.modelos.peticiones;

public class PartidoActualizarRequestModel {

    private String golesLocal;
    private String golesVisitantes;

    public String getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(String golesLocal) {
        this.golesLocal = golesLocal;
    }

    public String getGolesVisitantes() {
        return golesVisitantes;
    }

    public void setGolesVisitantes(String golesVisitantes) {
        this.golesVisitantes = golesVisitantes;
    }

}
