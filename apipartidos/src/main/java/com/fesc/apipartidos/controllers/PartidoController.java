package com.fesc.apipartidos.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.apipartidos.modelos.peticiones.PartidoCrearRequestModel;
import com.fesc.apipartidos.modelos.respuestas.PartidoDataRestModel;
import com.fesc.apipartidos.services.IPartidoService;
import com.fesc.apipartidos.shared.PartidoDto;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IPartidoService iPartidoService;

    @PostMapping
    public PartidoDataRestModel crearPartido(@RequestBody PartidoCrearRequestModel partidoCrearRequestModel) {
        String username = "rortegani";
        PartidoDto partidoCrearDto = modelMapper.map(partidoCrearRequestModel, PartidoDto.class);
        partidoCrearDto.setUsername(username);
        PartidoDto partidoDto = iPartidoService.crearPartido(partidoCrearDto);
        PartidoDataRestModel partidoDataRestModel = modelMapper.map(partidoDto, PartidoDataRestModel.class);
        return partidoDataRestModel;
    }
}
