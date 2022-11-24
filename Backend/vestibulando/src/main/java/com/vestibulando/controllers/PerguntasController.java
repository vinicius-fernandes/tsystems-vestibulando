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
@CrossOrigin(origins = "*")

public class PerguntasController {
    @Autowired
    PerguntaService perguntaService ;

    @GetMapping("/todas")
    public ResponseEntity<List<Pergunta>> listarTodas(){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.listarTodas());
    }

    @GetMapping
    public ResponseEntity<Page<Pergunta>> listar(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> obter (@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.obter(id));
    }

    @GetMapping("/banca/{id}")
    public ResponseEntity<Page<Pergunta>> obterPorBanca (@PathVariable("id") long id, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.findByBanca(id,page));
    }

    @GetMapping("/materia/{id}")
    public ResponseEntity<Page<Pergunta>> obterPorMateria (@PathVariable("id") long id, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.findByMateria(id,page));
    }

    @GetMapping("/simulado/{id}")
    public ResponseEntity<Page<Pergunta>> obterPorSimulado(@PathVariable("id") long id, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.findBySimulado(id,page));
    }

    @GetMapping("/pesquisar/{corpo}")
    public ResponseEntity<Page<Pergunta>> obterPorCorpo(@PathVariable("corpo") String corpo, Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.findByCorpo(corpo,page));
    }


    @PostMapping
    public ResponseEntity<Pergunta> criar(@Valid @RequestBody Pergunta pergunta){
        return ResponseEntity.status(HttpStatus.CREATED).body(perguntaService.salvar(pergunta));
    }

    @PutMapping("{id}")
    public ResponseEntity<Pergunta> editar(@PathVariable("id") long id,@Valid @RequestBody Pergunta pergunta){
        return ResponseEntity.status(HttpStatus.OK).body(perguntaService.atualizar(id,pergunta));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") long id){
        perguntaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pergunta removida com sucesso!");
    }

}
