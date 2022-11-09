package com.vestibulando.repositories;

import com.vestibulando.entities.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerguntaRepository extends JpaRepository<Pergunta, Long> {
}
