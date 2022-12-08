package com.vestibulando.repositories;

import com.vestibulando.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMateriaRepository extends JpaRepository<Materia, Long> {
    public List<Materia> findAllByOrderByIdAsc();

    Materia findByNomeIgnoreCase(String nome);
    @Query(value = "select m from Materia m " +
            "join Pergunta p on p.materia.id = m.id " +
            "where p.banca.id in (?1) " +
            "group by m.id, m.nome " +
            "order by m.id")
    List<Materia> getByBanca(List<Long> idBanca);
}
