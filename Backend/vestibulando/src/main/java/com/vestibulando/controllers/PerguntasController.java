package com.vestibulando.controllers;

import com.vestibulando.entities.Pergunta;
import com.vestibulando.services.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perguntas")
public class PerguntasController {
    @Autowired
    PerguntaService perguntaService ;

    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> obter (@RequestParam("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.obter(id));
    }

    @PostMapping
    public ResponseEntity<Pergunta> criar(@RequestBody Pergunta pergunta){
        return null;
    }
}
