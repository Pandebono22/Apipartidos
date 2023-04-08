package com.fesc.apipartidos.controllers;

// importancion del proyecto
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fesc.apipartidos.modelos.peticiones.UsuarioCrearDataRequestModel;
import com.fesc.apipartidos.modelos.respuestas.PartidoDataRestModel;
import com.fesc.apipartidos.modelos.respuestas.UsuarioDataRestModel;
import com.fesc.apipartidos.services.IUsuarioService;
import com.fesc.apipartidos.shared.PartidoDto;
import com.fesc.apipartidos.shared.UsuarioDto;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUsuarioService iUsuarioService;

    @GetMapping
    public UsuarioDataRestModel leerUsuario() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();

        System.out.println(username);
        UsuarioDto usuarioDto = iUsuarioService.leerUsuario(username);
        UsuarioDataRestModel usuarioDataRestModel = modelMapper.map(usuarioDto, UsuarioDataRestModel.class);
        return usuarioDataRestModel;
    }

    @PostMapping
    public UsuarioDataRestModel crearUsuario(@RequestBody UsuarioCrearDataRequestModel usuarioCrearRequestModel) {
        UsuarioDto usuarioCrearDto = modelMapper.map(usuarioCrearRequestModel, UsuarioDto.class);
        UsuarioDto usuarioDto = iUsuarioService.crearUsuario(usuarioCrearDto);
        UsuarioDataRestModel usuarioDataRestModel = modelMapper.map(usuarioDto, UsuarioDataRestModel.class);
        return usuarioDataRestModel;
    }

    @GetMapping(path = "/mispartidos")
    public List<PartidoDataRestModel> leerMispartidos() {

        // String username = "rortegani";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();

        List<PartidoDto> partidoDtoList = iUsuarioService.leerMispartidos(username);
        List<PartidoDataRestModel> partidoRestModelList = new ArrayList<>();
        for (PartidoDto partidoDto : partidoDtoList) {

            PartidoDataRestModel partidoRestModel = modelMapper.map(partidoDto, PartidoDataRestModel.class);
            if (partidoRestModel.getFecha().compareTo(new Date(System.currentTimeMillis())) < 0) {
                partidoRestModel.setJugado(true);
            }
            partidoRestModelList.add(partidoRestModel);
        }
        return partidoRestModelList;
    }

}
