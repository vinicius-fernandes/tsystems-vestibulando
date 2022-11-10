package com.vestibulando.repositories;

import com.vestibulando.entities.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPerguntaRepository extends JpaRepository<Pergunta, Long> {

    @Query("select p from Pergunta p where p.banca.id = ?1")
    List<Pergunta> findByBancaId (long id);

    @Query("select p from Pergunta p where p.materia.id = ?1")
    List<Pergunta> findByMateriaId(long id);
}
