package com.fesc.apipartidos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fesc.apipartidos.entidades.UsuarioEntity;
import com.fesc.apipartidos.repositorios.IUsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = iUsuarioRepository
                .findOneByUsername(username);

        if (usuarioEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(usuarioEntity);
    }

}
