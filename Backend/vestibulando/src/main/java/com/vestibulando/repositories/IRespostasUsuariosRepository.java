package com.vestibulando.repositories;

import com.vestibulando.dtos.RankingSimuladoDTO;
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

    @Query("select new com.vestibulando.dtos.RankingSimuladoDTO("+
            "u.id ,"+
            "u.email," +
            "count(*)"+
            ")"+
            " from RespostasUsuarios ru " +
            " join Usuario u on u.id = ru.usuario.id"+
            " join ru.respostas respsU "+
            " where ru.simulado.id = ?1 and respsU.correta = true "+
            " group by u.id "
    )
    List<RankingSimuladoDTO> getRankingSimulado(long idSimulado);

}
