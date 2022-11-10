package com.vestibulando.services;

import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Simulado;
import com.vestibulando.repositories.SimuladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class SimuladoService {

    @Autowired
    SimuladoRepository simuladoRepository;

    public List<Simulado> consultar() {
        return simuladoRepository.findAll();
    }

    public Simulado consultar(Long id) {
        Optional<Simulado> s = simuladoRepository.findById(id);

        return s.orElseThrow(()-> new EntityNotFoundException("Simulado não encontrado."));
    }

    public Set<Pergunta> consultarPerguntas(Long idSimulado) {
        return consultar(idSimulado).getPerguntas();
    }

    public Set<Banca> consultarBancas(Long idSimulado) {
        return consultar(idSimulado).getBancas();
    }

    public Set<Materia> consultarMaterias(Long idSimulado) {
        return consultar(idSimulado).getMaterias();
    }

    @Transactional
    public Simulado salvar(Simulado s) {
        return simuladoRepository.save(s);
    }

    @Transactional
    public String deletarSimulado(Long id) {
        consultar(id);
        simuladoRepository.deleteById(id);
        return "Simulado deletado com sucesso.";
    }

    @Transactional
    public Simulado alterar(Long id, Simulado s) {
        consultar(id);
        s.setId(id);

        return simuladoRepository.save(s);
    }

    public Simulado adicionarBanca(Long idSimulado, Banca b) {
        Simulado s = consultar(idSimulado);
        s.getBancas().add(b);

        return simuladoRepository.save(s);
    }

    public Simulado adicionarMateria(Long idSimulado, Materia m) {
        Simulado s = consultar(idSimulado);
        s.getMaterias().add(m);

        return simuladoRepository.save(s);
    }

    public Simulado adicionarPergunta(Long idSimulado, Pergunta p) {
        Simulado s = consultar(idSimulado);
        s.getPerguntas().add(p);

        return simuladoRepository.save(s);
    }

    public Simulado deletarPergunta(Long idSimulado, Long idPergunta) {
        Simulado s = consultar(idSimulado);

        for (Iterator<Pergunta> it = s.getPerguntas().iterator(); it.hasNext(); ) {
            if ( it.next().getId() == idPergunta ) {
                it.remove();
                return simuladoRepository.save(s);
            }
        }

        throw new EntityNotFoundException("Pergunta não encontrada ou não cadastrada no simulado.");
    }

    public Simulado deletarBanca(Long idSimulado, Long idBanca) {
        Simulado s = consultar(idSimulado);

        for (Iterator<Banca> it = s.getBancas().iterator(); it.hasNext(); ) {
            if ( it.next().getId() == idBanca ) {
                it.remove();
                return simuladoRepository.save(s);
            }
        }

        throw new EntityNotFoundException("Banca não encontrada ou não cadastrada no simulado.");
    }

    public Simulado deletarMateria(Long idSimulado, Long idMateria) {
        Simulado s = consultar(idSimulado);

        for (Iterator<Materia> it = s.getMaterias().iterator(); it.hasNext(); ) {
            if ( it.next().getId() == idMateria ) {
                it.remove();
                return simuladoRepository.save(s);
            }
        }

        throw new EntityNotFoundException("Matéria não encontrada ou não cadastrada no simulado.");
    }
}
