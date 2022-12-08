package com.vestibulando.repositories;

import com.vestibulando.entities.Materia;
import com.vestibulando.entities.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMateriaRepository extends JpaRepository<Materia, Long> {
    public List<Materia> findAllByOrderByIdAsc();

    Materia findByNomeIgnoreCase(String nome);
    @Query(value = "select m.id, m.nome from Materia m " +
            "right join Pergunta as p on m.id = p.materia_id " +
            "where p.banca_id in (?1) " +
            "group by m.id, m.nome " +
            "order by m.id", nativeQuery = true)
    List<Materia> getByBanca(List<Long> idBanca);
}
