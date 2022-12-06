package com.vestibulando.services;

import com.vestibulando.dtos.RespostaDTO;
import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Resposta;
import com.vestibulando.repositories.IRespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RespostaService {
    @Autowired
    IRespostaRepository IRespostaRepository;
    @Autowired
    PerguntaService perguntaService;

    public Resposta salvar(Resposta resposta){
        Pergunta perg = perguntaService.obter(resposta.getPergunta().getId());
        Set<Resposta> listaRespostas = perg.getRespostas();
        Boolean novaResposta = (resposta.getId() == null);
        if(resposta.getCorreta()) {
            for (Resposta resp : listaRespostas) {
                if (resp.getCorreta()) {
                    throw new IllegalStateException("Pergunta já possui uma alternativa correta.");
                }
            }
        }
        if((listaRespostas.size()) == 5 && novaResposta) {
            throw new IllegalStateException("Pergunta já possui 5 alternativas.");
        }
        return IRespostaRepository.save(resposta);
    }

    public List<Resposta> consultarComoAdmin(){
        return IRespostaRepository.findAll();
    }

    public Page<Resposta> consultarComoAdminPaginado(Pageable page){
        return IRespostaRepository.findAll(page);
    }

    public Optional<Resposta> consultarByIdComoAdmin(Long idresposta){
        return IRespostaRepository.findById(idresposta);
    }

    public List<RespostaDTO> consultarComoUser(){
        List<Resposta> lista = IRespostaRepository.findAll();
        List<RespostaDTO> listaDTO = new ArrayList<>();
        for(Resposta resp : lista){
            listaDTO.add(new RespostaDTO(resp));
        }
        return listaDTO;
    }

    public Page<RespostaDTO> consultarComoUserPaginado(Pageable page){
        Page<Resposta> lista = IRespostaRepository.findAll(page);
        Page<RespostaDTO> listaDTO = lista.map(resp -> new RespostaDTO(resp));
        return listaDTO;
    }

    public Optional<RespostaDTO> consultarByIdComoUser(Long idrespostaDTO){
        Optional<Resposta> resposta = IRespostaRepository.findById(idrespostaDTO);
        RespostaDTO respostaDTO = new RespostaDTO(resposta.get());
        return Optional.of(respostaDTO);
    }

    public Resposta alterar(Long idresposta, Resposta resposta) {
        Resposta resp = IRespostaRepository.findById(idresposta).get();
        resp.setDescricao(resposta.getDescricao());
        resp.setCorreta(resposta.getCorreta());
        resp.setPergunta(resposta.getPergunta());
        return salvar(resp);
    }

    public <String> ResponseEntity<java.lang.String> excluir(Long idresposta){
        try{
            Resposta resp = IRespostaRepository.findById(idresposta).get();
            if(resp != null){
                resp.setPergunta(null);
                IRespostaRepository.save(resp);
                IRespostaRepository.delete(resp);
            }
            return ResponseEntity.ok().body("Resposta excluída com sucesso.");
        }
        catch (Exception e) {
            throw new RuntimeException("Não foi possível identificar a resposta a ser deletada");
        }
    }

}
