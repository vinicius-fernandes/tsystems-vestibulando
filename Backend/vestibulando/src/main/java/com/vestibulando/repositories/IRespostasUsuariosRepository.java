package com.vestibulando.repositories;

import com.vestibulando.entities.RespostasUsuarios;
import com.vestibulando.entities.Simulado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRespostasUsuariosRepository extends JpaRepository<RespostasUsuarios,Long> {

    @Query("select r from RespostasUsuarios r where r.id = ?1")
    Optional<RespostasUsuarios> findById(long id);
    List<RespostasUsuarios> findBySimulado(Simulado simulado);
}
