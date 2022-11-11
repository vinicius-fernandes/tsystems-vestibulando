package com.vestibulando.repositories;

import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Simulado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Pergunta> findBySimulado(Simulado simulado, Pageable page);
    Page<Pergunta> findByBanca(Banca banca, Pageable page);
    Page<Pergunta> findByMateria(Materia materia, Pageable page);

    Page<Pergunta> findByCorpoContaining(String corpo,Pageable page);


}
