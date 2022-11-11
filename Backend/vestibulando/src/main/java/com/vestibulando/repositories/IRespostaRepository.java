package com.vestibulando.repositories;

import com.vestibulando.entities.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRespostaRepository extends JpaRepository<Resposta, Long> {
}
