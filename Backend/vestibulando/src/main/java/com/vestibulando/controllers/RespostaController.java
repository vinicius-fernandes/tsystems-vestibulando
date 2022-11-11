package com.vestibulando.controllers;

import com.vestibulando.entities.Resposta;
import com.vestibulando.services.RespostaService;
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
    RespostaService respostaService;

    @PostMapping("{idpergunta}")
    public ResponseEntity<Resposta> salvar(@Valid @RequestBody Resposta resposta){
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaService.salvar(resposta));
    }

    @GetMapping
    public ResponseEntity<List<Resposta>> consultar(){
        return ResponseEntity.status(HttpStatus.OK).body(respostaService.consultar());
    }

    @GetMapping("/{idresposta}")
    public ResponseEntity<Optional<Resposta>> consultarById(@PathVariable("idresposta") Long idresposta){
        return ResponseEntity.ok().body(respostaService.consultarById(idresposta));
    }

    @PutMapping("/{idresposta}")
    public ResponseEntity<Object> alterar(@PathVariable("idresposta") Long idresposta, @Valid @RequestBody Resposta resposta) {
        return ResponseEntity.status(HttpStatus.OK).body(respostaService.alterar(idresposta, resposta));
    }

    @DeleteMapping("/{idresposta}")
    public ResponseEntity<String> excluir(@PathVariable("idresposta") Long idresposta){
        return respostaService.excluir(idresposta);
    }

}
