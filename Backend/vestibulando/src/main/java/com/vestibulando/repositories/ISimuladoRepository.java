package com.vestibulando.repositories;

import com.vestibulando.entities.Simulado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISimuladoRepository extends JpaRepository<Simulado, Long> {



}
