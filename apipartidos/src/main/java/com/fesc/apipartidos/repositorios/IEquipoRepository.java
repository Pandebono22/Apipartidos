package com.fesc.apipartidos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fesc.apipartidos.entidades.EquipoEntity;

@Repository
public interface IEquipoRepository extends CrudRepository<EquipoEntity, Long> {
    EquipoEntity findById(long id);
}
