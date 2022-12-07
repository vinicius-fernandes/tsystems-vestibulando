package com.vestibulando.repositories;

import com.vestibulando.entities.Simulado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISimuladoRepository extends JpaRepository<Simulado, Long> {

    Page<Simulado> findAllByOrderByCreatedAtDesc(Pageable page);

    @Query("select distinct(s) from Simulado s join s.materias m  join s.bancas b where m.id IN ?1 and b.id IN ?2")
    Page<Simulado> findByBancaAndMateria(List<Long> idMaterias, List<Long> idBancas, Pageable pageable);
    @Query("select distinct(s) from Simulado s join s.bancas b where  b.id IN ?1")
    Page<Simulado> findByBancas(List<Long> idBancas, Pageable pageable);

    @Query("select distinct(s) from Simulado s  join s.materias m  where m.id  IN ?1")
    Page<Simulado> findByMaterias(List<Long> idMaterias, Pageable pageable);
}
