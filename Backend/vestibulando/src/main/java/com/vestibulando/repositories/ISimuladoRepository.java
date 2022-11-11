package com.vestibulando.repositories;

import com.vestibulando.entities.Simulado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISimuladoRepository extends JpaRepository<Simulado, Long> {

}
