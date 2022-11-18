package com.vestibulando.services;

import com.vestibulando.dtos.GerarSimuladoDTO;
import com.vestibulando.dtos.RankingSimuladoDTO;
import com.vestibulando.dtos.SimuladoDTO;
import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Simulado;
import com.vestibulando.repositories.IPerguntaRepository;
import com.vestibulando.repositories.ISimuladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class SimuladoService {

    @Autowired
    ISimuladoRepository simuladoRepository;

    @Autowired
    IPerguntaRepository perguntaRepository;

    public List<Simulado> consultar() {
        return simuladoRepository.findAll();
    }

    public Page<Simulado> consultarPaginada(Pageable pageable) {
        return simuladoRepository.findAll(pageable);
    }

    public Simulado consultar(Long id) {
        Optional<Simulado> s = simuladoRepository.findById(id);

        return s.orElseThrow(()-> new EntityNotFoundException("Simulado não encontrado."));
    }

    public SimuladoDTO realizar(Long id){
        Simulado simulado = this.consultar(id);
        return new SimuladoDTO(simulado);
    }

    public SimuladoDTO gerarSimulado(GerarSimuladoDTO gerarSimuladoDTO){
        List<Long> idMaterias = gerarSimuladoDTO.getMaterias().stream().map(Materia::getId).toList();
        List<Long> idBancas = gerarSimuladoDTO.getBancas().stream().map(Banca::getId).toList();

        List<Pergunta> perguntasParaOSimulado = perguntaRepository.getPerguntasParaGerarSimulado(idMaterias,idBancas, PageRequest.of(0,gerarSimuladoDTO.getNumeroPerguntas()));

        if(perguntasParaOSimulado.size()<gerarSimuladoDTO.getNumeroPerguntas()){
            throw new RuntimeException("Não foi possível obter o número de perguntas desejados para o simulado com base nos filtros inseridos");
        }

        Simulado simulado = new Simulado();

        simulado.setMaterias(gerarSimuladoDTO.getMaterias());
        simulado.setBancas(gerarSimuladoDTO.getBancas());
        simulado.setPerguntas(new HashSet<>(perguntasParaOSimulado));
        Simulado simuladoGerado = this.salvar(simulado);
        return new SimuladoDTO(simuladoGerado);
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
        Simulado s_antigo = consultar(id);
        s_antigo.setBancas(s.getBancas());
        s_antigo.setMaterias(s.getMaterias());
        s_antigo.setPerguntas(s.getPerguntas());

        return salvar(s_antigo);
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

    public Simulado alterarPergunta(Long idSimulado, Long idPergunta, Pergunta p) {
        Simulado s = consultar(idSimulado);
        deletarPergunta(idSimulado, idPergunta);
        return adicionarPergunta(idSimulado, p);
    }

    public Simulado alterarBanca(Long idSimulado, Long idBanca, Banca b) {
        Simulado s = consultar(idSimulado);
        deletarBanca(idSimulado, idBanca);
        return adicionarBanca(idSimulado, b);
    }

    public Simulado alterarMateria(Long idSimulado, Long idMateria, Materia m) {
        deletarMateria(idSimulado, idMateria);
        return adicionarMateria(idSimulado, m);
    }
}
