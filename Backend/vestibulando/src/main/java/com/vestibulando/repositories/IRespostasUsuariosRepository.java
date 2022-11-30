package com.vestibulando.repositories;

import com.vestibulando.dtos.NotaSimuladoUsuarioDTO;
import com.vestibulando.dtos.RankingSimuladoDTO;
import com.vestibulando.entities.RespostasUsuarios;
import com.vestibulando.entities.Simulado;
import com.vestibulando.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "sum(CASE  WHEN (respsU.correta = true) then 1 ELSE 0 END) as nota"+
            ")"+
            " from RespostasUsuarios ru " +
            " join Usuario u on u.id = ru.usuario.id"+
            " join ru.respostas respsU "+
            " where ru.simulado.id = ?1  "+
            " group by u.id order by nota desc"
    )
    List<RankingSimuladoDTO> getRankingSimulado(long idSimulado);

    @Query("select new com.vestibulando.dtos.RankingSimuladoDTO("+
            "u.id ,"+
            "u.email," +
            "sum(CASE  WHEN (respsU.correta = true) then 1 ELSE 0 END) as nota" +
            ")"+
            " from RespostasUsuarios ru " +
            " join Usuario u on u.id = ru.usuario.id"+
            " join ru.respostas respsU  "+
            " group by u.id order by nota desc "
    )
    Page<RankingSimuladoDTO> getRankingGlobal(Pageable pageable);

    @Query("select new com.vestibulando.dtos.NotaSimuladoUsuarioDTO("+
            "ru.simulado.id ,"+
            "sum(CASE  WHEN (respsU.correta = true) then 1 ELSE 0 END) as nota"+
            ")"+
            " from RespostasUsuarios ru " +
            " join ru.respostas respsU "+
            " where ru.simulado.id = ?1 and ru.usuario.id= ?2 "+
            " group by ru.simulado.id "
    )
    Optional<NotaSimuladoUsuarioDTO> getNotaSimuladoUsuario( long idSimulado,long idUsuario);


    @Query("select u from RespostasUsuarios ru join ru.usuario u where ru.simulado.id=?1 ")
    Set<Usuario> getUsuariosSimulado(long idSimulado);

    @Query("select ru from RespostasUsuarios ru where ru.simulado.id=?1 and ru.usuario.id= ?2 ")
    Optional<RespostasUsuarios> getBySimuladoAndUsuario(long idSimulado,long idUsuario);

    @Query("select new com.vestibulando.dtos.NotaSimuladoUsuarioDTO("+
            "ru.simulado.id ,"+
            "sum(CASE  WHEN (respsU.correta = true) then 1 ELSE 0 END) as nota"+
            ")"+
            " from RespostasUsuarios ru " +
            " join ru.respostas respsU "+
            " where ru.usuario.id= ?1  "+
            " group by ru.simulado.id "
    )
    List<NotaSimuladoUsuarioDTO> getNotasSimuladosUsuario( long idUsuario);

}
