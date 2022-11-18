package com.vestibulando.repositories;

import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRespostaRepository extends JpaRepository<Resposta, Long> {
    List<Resposta> findByPergunta(Pergunta perg);
}
