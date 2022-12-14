package com.vestibulando.controllers;

import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import com.vestibulando.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/materia")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    MateriaService materiaService;
    @GetMapping
    public ResponseEntity<List<Materia>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.listar());
    }
    @GetMapping("/paginado")
    public ResponseEntity<Page<Materia>> consultarPaginado(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.pageBancas(page));
    }

    @GetMapping("/materiaporbanca")
    public ResponseEntity<List<Materia>> obterPorBanca(@RequestParam("idBancas") List<Long> idBancas){
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.obterPorBanca(idBancas));
    }

    @GetMapping("{id}")
    public ResponseEntity<Materia> obter(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.obter(id));
    }
    @PostMapping
    public ResponseEntity<Materia> criar(@RequestBody Materia materia){
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.salvar(materia));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Materia> editar(@PathVariable("id") long id, @RequestBody Materia materia){
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.atualizar(id, materia));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable ("id") long id){
        materiaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Materia removida com sucesso!");
    }
}
