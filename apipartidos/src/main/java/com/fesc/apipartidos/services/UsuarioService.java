package com.fesc.apipartidos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fesc.apipartidos.entidades.PartidoEntity;
import com.fesc.apipartidos.entidades.UsuarioEntity;
import com.fesc.apipartidos.repositorios.IPartidoRepository;
import com.fesc.apipartidos.repositorios.IUsuarioRepository;
import com.fesc.apipartidos.shared.PartidoDto;
import com.fesc.apipartidos.shared.UsuarioDto;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    IPartidoRepository iPartidoRepository;

    @Override
    public UsuarioDto crearUsuario(UsuarioDto usuarioCrearDto) {

        if (iUsuarioRepository.findByEmail(usuarioCrearDto.getEmail()) != null) {
            throw new RuntimeException("Correo ya usado");
        }

        if (iUsuarioRepository.findByUsername(usuarioCrearDto.getUsername()) != null) {
            throw new RuntimeException("Username ya esta en uso");
        }

        UsuarioEntity usuarioEntityDto = modelMapper.map(usuarioCrearDto, UsuarioEntity.class);
        usuarioEntityDto.setIdUsuario(UUID.randomUUID().toString());
        usuarioEntityDto.setPasswordEncriptada(bCryptPasswordEncoder.encode(usuarioCrearDto.getPassword()));

        UsuarioEntity usuarioEntity = iUsuarioRepository.save(usuarioEntityDto);

        UsuarioDto usuarioDto = modelMapper.map(usuarioEntity, UsuarioDto.class);

        return usuarioDto;
    }

    @Override
    public UsuarioDto leerUsuario(String username) {

        UsuarioEntity usuarioEntity = iUsuarioRepository.findByUsername(username);

        if (usuarioEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        UsuarioDto usuarioDto = modelMapper.map(usuarioEntity, UsuarioDto.class);
        return usuarioDto;
    }

    @Override
    public List<PartidoDto> leerMispartidos(String userName) {

        UsuarioEntity usuarioEntity = iUsuarioRepository.findByUsername(userName);

        List<PartidoEntity> PartidoEntityList = iPartidoRepository
                .getByUsuarioEntityIdOrderByCreadoDesc(usuarioEntity.getId());

        List<PartidoDto> partidoDtoList = new ArrayList<PartidoDto>();

        for (PartidoEntity partidoEntity : PartidoEntityList) {
            PartidoDto partidoDto = modelMapper.map(partidoEntity, PartidoDto.class);
            partidoDtoList.add(partidoDto);
        }

        return partidoDtoList;

    }

}
