package com.fesc.apipartidos.services;

import java.util.List;

import com.fesc.apipartidos.shared.PartidoDto;

public interface IPartidoService {
    public PartidoDto crearPartido(PartidoDto partidoCrearDto);

    public List<PartidoDto> partidosCreados();

    public PartidoDto detallePartido(String id);

    public PartidoDto actualizarPartido(String idPartido, PartidoDto partidoActualizarDto);

    public void eliminarPartido(String idPartido, long IdUsuario);

}
