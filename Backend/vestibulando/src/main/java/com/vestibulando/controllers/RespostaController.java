package com.vestibulando.controllers;

import com.vestibulando.entities.Resposta;
import com.vestibulando.repositories.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respostas") //http://localhost:8080/respostas
public class RespostaController {

    @Autowired
    RespostaRepository respostaRepository;

    @PostMapping
    public ResponseEntity<Resposta> salvar(@Valid @RequestBody Resposta resposta){
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaRepository.save(resposta));
    }

    @GetMapping
    public ResponseEntity<List<Resposta>> consultarRespostas(){
        return ResponseEntity.status(HttpStatus.OK).body(respostaRepository.findAll());
    }

    @GetMapping("/{idresposta}")
    public ResponseEntity<Optional<Resposta>> consultarById(@PathVariable("idresposta") Long idresposta){
        return ResponseEntity.ok().body(respostaRepository.findById(idresposta));
    }

    @PutMapping("/{idresposta}")
    public ResponseEntity<Object> alterar(
            @PathVariable("idresposta") Long idresposta,
            @Valid @RequestBody Resposta resposta) {
        Resposta resp = respostaRepository.findById(idresposta).get();
        resp.setDescricao(resposta.getDescricao());
        resp.setCorreta(resposta.getCorreta());
        resp.setPergunta(resposta.getPergunta());

        return ResponseEntity.status(HttpStatus.OK).body(respostaRepository.save(resp));
    }

    @DeleteMapping("/{idresposta}")
    public ResponseEntity<String> excluir(@PathVariable("idresposta") Long idresposta){
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
