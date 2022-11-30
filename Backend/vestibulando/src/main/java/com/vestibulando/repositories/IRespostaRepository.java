package com.vestibulando.repositories;

import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IRespostaRepository extends JpaRepository<Resposta, Long> {
    List<Resposta> findByPergunta(Pergunta perg);
    @Modifying
    @Query("delete from RespostasUsuarios r where r in ( select ru from RespostasUsuarios ru join ru.respostas rr where rr.id in ?1)")
    void deleteFromRespostas(List<Long> resps);

}
