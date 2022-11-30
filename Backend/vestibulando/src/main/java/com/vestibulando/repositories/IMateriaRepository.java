package com.vestibulando.repositories;

import com.vestibulando.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMateriaRepository extends JpaRepository<Materia, Long> {
    public List<Materia> findAllByOrderByIdAsc();

    @Query("Select m from Materia m where m.nome = ?1")
    Materia findByNome(String nome);
}
