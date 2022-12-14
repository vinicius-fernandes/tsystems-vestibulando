package com.vestibulando.repositories;

import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Simulado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IPerguntaRepository extends JpaRepository<Pergunta, Long> {

    @Query("select p from Pergunta p where p.banca.id = ?1")
    List<Pergunta> findByBancaId (long id);
    @Query("select p from Pergunta p where p.materia.id = ?1")
    List<Pergunta> findByMateriaId(long id);
    @Modifying
    @Query("delete from Resposta r where r.pergunta.id = ?1")
    void deleteRespostasPergunta(long id);
    @Modifying
    @Query("delete from Pergunta p where p.id = ?1")
    void deletePerguntaCustomizado(long id);


    @Query(
            "select p from Pergunta p where p.materia.id IN ?1 and p.banca.id IN ?2 order by rand() "
    )
    List<Pergunta> getPerguntasParaGerarSimulado(List<Long> idMaterias, List<Long> idBancas,Pageable pageable);

    Page<Pergunta> findBySimulado(Simulado simulado, Pageable page);
    Page<Pergunta> findByBanca(Banca banca, Pageable page);
    Page<Pergunta> findByMateria(Materia materia, Pageable page);
    Page<Pergunta> findByCorpoContaining(String corpo,Pageable page);
    Page<Pergunta> findByCorpoIgnoreCaseContaining(String corpo,Pageable page);
    Page<Pergunta> findByCorpoIgnoreCaseContainingAndBancaAndMateria(String corpo, Banca banca, Materia materia, Pageable page);
    Page<Pergunta> findByBancaAndMateria(Banca banca, Materia materia, Pageable page);
    Page<Pergunta> findByCorpoIgnoreCaseContainingAndMateria(String corpo, Materia materia, Pageable page);
    Page<Pergunta> findByCorpoIgnoreCaseContainingAndBanca(String corpo, Banca banca, Pageable page);

}
