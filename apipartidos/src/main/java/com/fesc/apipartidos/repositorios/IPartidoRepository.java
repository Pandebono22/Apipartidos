package com.fesc.apipartidos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fesc.apipartidos.entidades.PartidoEntity;

@Repository
public interface IPartidoRepository extends PagingAndSortingRepository<PartidoEntity, Long> {

    List<PartidoEntity> getByUsuarioEntityIdOrderByCreadoDesc(long usuarioEntityId);

    @Query(nativeQuery = true, value = "SELECT * FROM partido ORDER BY creado DESC LIMIT 10")
    List<PartidoEntity> partidosCreados();

    PartidoEntity save(PartidoEntity partidoEntity);

    PartidoEntity delete(PartidoEntity partidoEntity);

    PartidoEntity findByIdPartido(String idPartido);

}
