package com.vestibulando.repositories;

import com.vestibulando.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMateriaRepository extends JpaRepository<Materia, Long> {
    public List<Materia> findAllByOrderByIdAsc();
}
