package com.fesc.apipartidos.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fesc.apipartidos.entidades.PartidoEntity;

@Repository
public interface IPartidoRepository extends PagingAndSortingRepository<PartidoEntity, Long> {

    PartidoEntity save(PartidoEntity partidoEntity);

}
