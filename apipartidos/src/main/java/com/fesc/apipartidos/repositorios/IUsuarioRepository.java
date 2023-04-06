package com.fesc.apipartidos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fesc.apipartidos.entidades.UsuarioEntity;

@Repository
public interface IUsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
    public UsuarioEntity findByEmail(String email);

    public UsuarioEntity findByUsername(String username);

    public UsuarioEntity findOneByUsername(String username);

}
