package com.vestibulando.repositories;

import com.vestibulando.dtos.SimuladoSimplificadoDTO;
import com.vestibulando.entities.Simulado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISimuladoRepository extends JpaRepository<Simulado, Long> {

    Page<Simulado> findAllByOrderByCreatedAtDesc(Pageable page);

    @Query("select  new com.vestibulando.dtos.SimuladoSimplificadoDTO("+
            " s.id ,"+
            " count(distinct(p)), "+
            " group_concat(m.nome, true, '|#|', m.nome, 'DESC'),"+
            " group_concat(b.nome, true, '|#|', b.nome, 'DESC'), "+
            " s.createdAt "+
          " ) "+
            " from Simulado s "+
            " join s.perguntas p "+
            " join s.materias m "+
            " join s.bancas b "+
            " group by s.id , s.createdAt" +
            " order by s.createdAt desc"
    )
    Page<SimuladoSimplificadoDTO> findAllSimplificado(Pageable page);

    @Query("select  new com.vestibulando.dtos.SimuladoSimplificadoDTO("+
            " s.id ,"+
            " count(distinct(p)), "+
            " group_concat(m.nome, true, '|#|', m.nome, 'DESC'),"+
            " group_concat(b.nome, true, '|#|', b.nome, 'DESC'), "+
            " s.createdAt "+
            " ) "+
            " from Simulado s "+
            " join s.perguntas p "+
            " join s.materias m "+
            " join s.bancas b "+
            " where m.id IN ?1 and b.id IN ?2 "+
            " group by s.id , s.createdAt" +
            " order by s.createdAt desc"
    )
    Page<SimuladoSimplificadoDTO> findByBancaAndMateria(List<Long> idMaterias, List<Long> idBancas, Pageable pageable);

    @Query("select  new com.vestibulando.dtos.SimuladoSimplificadoDTO("+
            " s.id ,"+
            " count(distinct(p)), "+
            " group_concat(m.nome, true, '|#|', m.nome, 'DESC'),"+
            " group_concat(b.nome, true, '|#|', b.nome, 'DESC'), "+
            " s.createdAt "+
            " ) "+
            " from Simulado s "+
            " join s.perguntas p "+
            " join s.materias m "+
            " join s.bancas b "+
            " where  b.id IN ?1 "+
            " group by s.id , s.createdAt" +
            " order by s.createdAt desc"
    )
    Page<SimuladoSimplificadoDTO> findByBancas(List<Long> idBancas, Pageable pageable);

    @Query("select  new com.vestibulando.dtos.SimuladoSimplificadoDTO("+
            " s.id ,"+
            " count(distinct(p)), "+
            " group_concat(m.nome, true, '|#|', m.nome, 'DESC'),"+
            " group_concat(b.nome, true, '|#|', b.nome, 'DESC'), "+
            " s.createdAt "+
            " ) "+
            " from Simulado s "+
            " join s.perguntas p "+
            " join s.materias m "+
            " join s.bancas b "+
            " where  m.id IN ?1 "+
            " group by s.id , s.createdAt" +
            " order by s.createdAt desc"
    )
    Page<SimuladoSimplificadoDTO> findByMaterias(List<Long> idMaterias, Pageable pageable);
}
