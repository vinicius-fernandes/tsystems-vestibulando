package com.vestibulando.services;

import com.vestibulando.entities.Resposta;
import com.vestibulando.repositories.IPerguntaRepository;
import com.vestibulando.repositories.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespostaService {
    @Autowired
    RespostaRepository respostaRepository;

    @Autowired
    IPerguntaRepository perguntaRepository;

    public Resposta salvar(Resposta resposta, Long idpergunta){
        return respostaRepository.save(resposta);
    }

    public List<Resposta> consultar(){
        return respostaRepository.findAll();
    }

    public Optional<Resposta> consultarById(Long idresposta){
        return respostaRepository.findById(idresposta);
    }

    public Resposta alterar(Long idresposta, Resposta resposta) {
        Resposta resp = respostaRepository.findById(idresposta).get();
        resp.setDescricao(resposta.getDescricao());
        resp.setCorreta(resposta.getCorreta());
        return respostaRepository.save(resp);
    }

    public <String> ResponseEntity<java.lang.String> excluir(Long idresposta){
        try{
            Resposta resp = respostaRepository.findById(idresposta).get();
            if(resp != null){
                respostaRepository.delete(resp);
            }
            return ResponseEntity.ok().body("Resposta excluída com sucesso.");
        }
        catch (Exception e) {
            return ResponseEntity.ok().body("Resposta não existe.");
        }
    }

}
