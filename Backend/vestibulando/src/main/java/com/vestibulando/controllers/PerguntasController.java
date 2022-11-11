package com.vestibulando.controllers;

import com.vestibulando.entities.Pergunta;
import com.vestibulando.services.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/perguntas")
public class PerguntasController {
    @Autowired
    PerguntaService perguntaService ;

    @GetMapping
    public ResponseEntity<Page<Pergunta>> listar(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> obter (@RequestParam("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.obter(id));
    }

    @GetMapping("/banca/{id}")
    public ResponseEntity<Page<Pergunta>> obterPorBanca (@RequestParam("id") long id, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.findByBanca(id,page));
    }

    @GetMapping("/materia/{id}")
    public ResponseEntity<Page<Pergunta>> obterPorMateria (@RequestParam("id") long id, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.findByMateria(id,page));
    }

    @GetMapping("/simulado/{id}")
    public ResponseEntity<Page<Pergunta>> obterPorSimulado(@RequestParam("id") long id, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.findBySimulado(id,page));
    }

    @GetMapping("/pesquisar/{corpo}")
    public ResponseEntity<Page<Pergunta>> obterPorCorpo(@RequestParam("corpo") String corpo, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.findByCorpo(corpo,page));
    }


    @PostMapping
    public ResponseEntity<Pergunta> criar(@Valid @RequestBody Pergunta pergunta){
        return ResponseEntity.status(HttpStatus.CREATED).body(perguntaService.salvar(pergunta));
    }

    @PutMapping("{id}")
    public ResponseEntity<Pergunta> editar(@RequestParam("id") long id,@Valid @RequestBody Pergunta pergunta){
        return ResponseEntity.status(HttpStatus.CREATED).body(perguntaService.atualizar(id,pergunta));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@RequestParam("id") long id){
        perguntaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pergunta removida com sucesso!");
    }

}
