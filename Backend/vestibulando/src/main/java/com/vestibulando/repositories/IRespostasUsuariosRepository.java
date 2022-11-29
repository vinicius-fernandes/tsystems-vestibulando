package com.vestibulando.repositories;

import com.vestibulando.dtos.NotaSimuladoUsuarioDTO;
import com.vestibulando.dtos.RankingSimuladoDTO;
import com.vestibulando.entities.RespostasUsuarios;
import com.vestibulando.entities.Simulado;
import com.vestibulando.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Query("select new com.vestibulando.dtos.NotaSimuladoUsuarioDTO("+
            "ru.simulado.id ,"+
            "count(*)"+
            ")"+
            " from RespostasUsuarios ru " +
            " join ru.respostas respsU "+
            " where ru.simulado.id = ?1 and ru.usuario.id= ?2 and respsU.correta = true "+
            " group by ru.simulado.id "
    )
    Optional<NotaSimuladoUsuarioDTO> getNotaSimuladoUsuario( long idSimulado,long idUsuario);


    @Query("select u from RespostasUsuarios ru join ru.usuario u where ru.simulado.id=?1 ")
    Set<Usuario> getUsuariosSimulado(long idSimulado);

    @Query("select ru from RespostasUsuarios ru where ru.simulado.id=?1 and ru.usuario.id= ?2 ")
    Optional<RespostasUsuarios> getBySimuladoAndUsuario(long idSimulado,long idUsuario);

    @Query("select new com.vestibulando.dtos.NotaSimuladoUsuarioDTO("+
            "ru.simulado.id ,"+
            "count(*)"+
            ")"+
            " from RespostasUsuarios ru " +
            " join ru.respostas respsU "+
            " where ru.usuario.id= ?1 and respsU.correta = true "+
            " group by ru.simulado.id "
    )
    List<NotaSimuladoUsuarioDTO> getNotasSimuladosUsuario( long idUsuario);

}
