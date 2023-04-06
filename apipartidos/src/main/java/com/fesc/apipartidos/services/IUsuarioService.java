package com.fesc.apipartidos.services;

import java.util.List;

import com.fesc.apipartidos.shared.PartidoDto;
import com.fesc.apipartidos.shared.UsuarioDto;

public interface IUsuarioService{

    public UsuarioDto crearUsuario(UsuarioDto usuarioCrearDto);

    public UsuarioDto leerUsuario(String username);

    public List<PartidoDto> leerMispartidos(String userName);

}
