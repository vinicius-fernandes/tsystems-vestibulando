package com.vestibulando.controllers;

import com.vestibulando.entities.Banca;
import com.vestibulando.services.BancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/banca")
@CrossOrigin(origins = "*")

public class BancaController {

    @Autowired
    BancaService bancaService;
    @GetMapping
    public ResponseEntity<List<Banca>> listar () {
        return ResponseEntity.status(HttpStatus.OK).body(bancaService.listar());
    }
    @GetMapping("{id}")
    public ResponseEntity<Banca> obter (@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bancaService.obter(id));
    }
    @PostMapping
    public ResponseEntity<Banca> criar(@RequestBody Banca banca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bancaService.salvar(banca));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Banca> editar(@PathVariable("id") long id, @RequestBody Banca banca) {
        return ResponseEntity.status(HttpStatus.OK).body(bancaService.atualizar(id, banca));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") long id) {
        bancaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Banca removida com sucesso!");
    }
}
