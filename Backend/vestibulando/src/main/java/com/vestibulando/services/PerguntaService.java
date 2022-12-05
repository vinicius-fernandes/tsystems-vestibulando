package com.vestibulando.services;

import com.vestibulando.entities.*;
import com.vestibulando.repositories.IPerguntaRepository;
import com.vestibulando.repositories.IRespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PerguntaService {
    @Autowired
    IPerguntaRepository perguntaRepository;
    @Autowired
    IRespostaRepository respostaRepository;
    @Autowired
    BancaService bancaService;
    @Autowired
    MateriaService materiaService;

    public List<Pergunta> listarTodas(){
        return perguntaRepository.findAll();
    }

    public Page<Pergunta> listar(Pageable page){
        return perguntaRepository.findAll(page);
    }

    public Pergunta obter(long id){
        return perguntaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("A pergunta desejada não foi encontrada!"));
    }

    public Page<Pergunta> findByBanca(long id,Pageable page){
        Banca banca = new Banca();
        banca.setId(id);
        return perguntaRepository.findByBanca(banca,page);
    }

    public Page<Pergunta> findByMateria(long id,Pageable page){
        Materia materia = new Materia();
        materia.setId(id);
        return perguntaRepository.findByMateria(materia, page);
    }

    public Page<Pergunta> findBySimulado(long simuladoId, Pageable page ){
        Simulado simulado = new Simulado();
        simulado.setId(simuladoId);
        return perguntaRepository.findBySimulado(simulado, page);
    }

    public Page<Pergunta> findByCorpo(String corpo, Pageable page){
        return perguntaRepository.findByCorpoIgnoreCaseContaining(corpo, page);
    }

    public Page<Pergunta> findByBancaAndMateria(long idBanca, long idMateria, Pageable page){
        Banca banca = new Banca();
        banca.setId(idBanca);
        Materia materia = new Materia();
        materia.setId(idMateria);
        return perguntaRepository.findByBancaAndMateria(banca, materia, page);
    }

    public Page<Pergunta> findByCorpoAndMateria(String corpo, long idMateria, Pageable page){
        Materia materia = new Materia();
        materia.setId(idMateria);
        return perguntaRepository.findByCorpoIgnoreCaseContainingAndMateria(corpo, materia, page);
    }

    public Page<Pergunta> findByCorpoAndBanca(String corpo, long idBanca, Pageable page){
        Banca banca = new Banca();
        banca.setId(idBanca);
        return perguntaRepository.findByCorpoIgnoreCaseContainingAndBanca(corpo, banca, page);
    }

    public Page<Pergunta> consultarComFiltro(String corpo, long idBanca, long idMateria, Pageable page){
        Banca banca = new Banca();
        banca.setId(idBanca);
        Materia materia = new Materia();
        materia.setId(idMateria);
        return perguntaRepository.findByCorpoIgnoreCaseContainingAndBancaAndMateria(corpo, banca, materia, page);
    }

    @Transactional
    public void deletar(long id){
        Pergunta pergunta = this.obter(id);

        List<Resposta> resposta = this.respostaRepository.findByPergunta(pergunta);

        List<Long> idRespostas = resposta.stream().map(Resposta::getId).toList();
        try {
            respostaRepository.deleteFromRespostas(idRespostas);
            perguntaRepository.deleteRespostasPergunta(id);
            perguntaRepository.deletePerguntaCustomizado(id);
        }
        catch (Exception e){
           throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Pergunta salvar(Pergunta pergunta){
        materiaService.obter(pergunta.getMateria().getId());
        bancaService.obter(pergunta.getBanca().getId());
        return perguntaRepository.save(pergunta);
    }

    public Pergunta atualizar(long id,Pergunta pergunta){
        Pergunta p = obter(id);

        p.setCorpo(pergunta.getCorpo());
        p.setRespostas(pergunta.getRespostas());
        p.setBanca(pergunta.getBanca());
        p.setMateria(pergunta.getMateria());

        for(Resposta res : p.getRespostas()){
            res.setPergunta(new Pergunta());
            res.getPergunta().setId(p.getId());
        }
        return this.salvar(p);
    }
}
