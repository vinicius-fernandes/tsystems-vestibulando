package com.vestibulando.services;

import com.vestibulando.entities.Resposta;
import com.vestibulando.repositories.IPerguntaRepository;
import com.vestibulando.repositories.IRespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespostaService {
    @Autowired
    IRespostaRepository IRespostaRepository;

    @Autowired
    IPerguntaRepository perguntaRepository;

    public Resposta salvar(Resposta resposta){
        return IRespostaRepository.save(resposta);
    }

    public List<Resposta> consultar(){
        return IRespostaRepository.findAll();
    }

    public Optional<Resposta> consultarById(Long idresposta){
        return IRespostaRepository.findById(idresposta);
    }

    public Resposta alterar(Long idresposta, Resposta resposta) {
        Resposta resp = IRespostaRepository.findById(idresposta).get();
        resp.setDescricao(resposta.getDescricao());
        resp.setCorreta(resposta.getCorreta());
        resp.setPergunta(resposta.getPergunta());
        return IRespostaRepository.save(resp);
    }

    public <String> ResponseEntity<java.lang.String> excluir(Long idresposta){
        try{
            Resposta resp = IRespostaRepository.findById(idresposta).get();
            if(resp != null){
                IRespostaRepository.delete(resp);
            }
            return ResponseEntity.ok().body("Resposta excluída com sucesso.");
        }
        catch (Exception e) {
            return ResponseEntity.ok().body("Resposta não existe.");
        }
    }

}
