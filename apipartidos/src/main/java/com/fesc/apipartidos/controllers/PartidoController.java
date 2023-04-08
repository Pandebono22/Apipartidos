package com.fesc.apipartidos.controllers;

import java.util.List;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.apipartidos.modelos.peticiones.PartidoActualizarRequestModel;
import com.fesc.apipartidos.modelos.peticiones.PartidoCrearRequestModel;
import com.fesc.apipartidos.modelos.respuestas.MensajeRestModel;
import com.fesc.apipartidos.modelos.respuestas.PartidoDataRestModel;
import com.fesc.apipartidos.services.IPartidoService;
import com.fesc.apipartidos.services.IUsuarioService;
import com.fesc.apipartidos.shared.PartidoDto;
import com.fesc.apipartidos.shared.UsuarioDto;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IPartidoService iPartidoService;

    @Autowired
    IUsuarioService iUsuarioService;

    @PostMapping
    public PartidoDataRestModel crearPartido(@RequestBody PartidoCrearRequestModel partidoCrearRequestModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        PartidoDto partidoCrearDto = modelMapper.map(partidoCrearRequestModel, PartidoDto.class);
        partidoCrearDto.setUsername(username);
        PartidoDto partidoDto = iPartidoService.crearPartido(partidoCrearDto);
        PartidoDataRestModel partidoDataRestModel = modelMapper.map(partidoDto, PartidoDataRestModel.class);
        return partidoDataRestModel;
    }

    @GetMapping(path = "/mispartidos")
    public List<PartidoDataRestModel> leerMisPartidos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();

        List<PartidoDto> partidoDtoList = iUsuarioService.leerMispartidos(username);
        List<PartidoDataRestModel> partidoDataRestModelList = new ArrayList<>();

        for (PartidoDto partidoDto : partidoDtoList) {
            PartidoDataRestModel partidoDataRestModel = modelMapper.map(partidoDto, PartidoDataRestModel.class);
            if (partidoDataRestModel.getFecha().compareTo(new Date(System.currentTimeMillis())) < 0) {
                partidoDataRestModel.setJugado(true);
            }

            partidoDataRestModelList.add(partidoDataRestModel);

        }

        return partidoDataRestModelList;

    }

    @GetMapping
    public List<PartidoDataRestModel> leerPartidos() {

        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String username = authentication.getPrincipal().toString();
        List<PartidoDto> partidoDtoList = iPartidoService.partidosCreados();
        List<PartidoDataRestModel> partidoDataRestList = new ArrayList<>();

        for (PartidoDto partidoDto : partidoDtoList) {
            PartidoDataRestModel partidoDataRestModel = modelMapper.map(partidoDto, PartidoDataRestModel.class);
            partidoDataRestList.add(partidoDataRestModel);
        }

        return partidoDataRestList;
    }

    @GetMapping(path = "/{id}")
    public PartidoDataRestModel detallePartido(@PathVariable String id) {
        PartidoDto partidoDto = iPartidoService.detallePartido(id);

        PartidoDataRestModel partidoDataRestModel = modelMapper.map(partidoDto, PartidoDataRestModel.class);

        return partidoDataRestModel;
    }

    @PutMapping(path = "/{id}")
    public PartidoDataRestModel actualizarPartido(@PathVariable String id,
            @RequestBody PartidoActualizarRequestModel partidoActualizarRequestModel) {

        // String username = "rortegani";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();

        PartidoDto partidoActualizarDto = modelMapper.map(partidoActualizarRequestModel, PartidoDto.class);

        partidoActualizarDto.setUsername(username);

        PartidoDto partidoDto = iPartidoService.actualizarPartido(id, partidoActualizarDto);

        PartidoDataRestModel partidoDataRestModel = modelMapper.map(partidoDto, PartidoDataRestModel.class);

        return partidoDataRestModel;
    }

    @DeleteMapping(path = "/{id}")
    public MensajeRestModel eliminarPartido(@PathVariable String id) {

        // String username = "rortegani";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();

        UsuarioDto usuarioDto = iUsuarioService.leerUsuario(username);

        MensajeRestModel mensajeRestModel = new MensajeRestModel();
        mensajeRestModel.setNombre("eliminar");

        iPartidoService.eliminarPartido(id, usuarioDto.getId());

        mensajeRestModel.setResultado("partido eliminado con exito");

        return mensajeRestModel;

    }

}
