package com.vestibulando.services;


import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Resposta;
import com.vestibulando.repositories.IPerguntaRepository;
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


    public List<Pergunta> listar(){
        return perguntaRepository.findAll();
    }

    public Page<Pergunta> listar(Pageable page){
        return perguntaRepository.findAll(page);
    }

    public Pergunta obter(long id){
        return perguntaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("A pergunta desejada não foi encontrada!"));
    }

    public List<Pergunta> findByBancaId(long id){
        return perguntaRepository.findByBancaId(id);
    }
    public List<Pergunta> findByMateriaId(long id){
        return perguntaRepository.findByMateriaId(id);
    }


    public void deletar(long id){
        Pergunta pergunta = this.obter(id);
        perguntaRepository.delete(pergunta);

    }

    @Transactional
    public Pergunta salvar(Pergunta pergunta){
        return perguntaRepository.save(pergunta);
    }

    public Pergunta atualizar(long id,Pergunta pergunta){
        Pergunta p = obter(id);

        p.setCorpo(pergunta.getCorpo());
        p.setRespostas(pergunta.getRespostas());
        for(Resposta res : p.getRespostas()){
        }

        return this.salvar(p);
    }

}
