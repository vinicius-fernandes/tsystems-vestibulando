package com.vestibulando.controllers;

import com.vestibulando.dtos.RespostaDTO;
import com.vestibulando.entities.Resposta;
import com.vestibulando.services.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respostas")
@CrossOrigin(origins = "*")
public class RespostaController {
    
    @Autowired
    RespostaService respostaService;

    @PostMapping
    public ResponseEntity<Resposta> salvar(@Valid @RequestBody Resposta resposta){
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaService.salvar(resposta));
    }
    @GetMapping("/admin")
    public ResponseEntity<List<Resposta>> consultarComoAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body(respostaService.consultarComoAdmin());
    }
    @GetMapping("/adminPaginado")
    public ResponseEntity<Page<Resposta>> consultarComoAdmin(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(respostaService.consultarComoAdminPaginado(page));
    }
    @GetMapping("/admin/{idresposta}")
    public ResponseEntity<Optional<Resposta>> consultarByIdComoAdmin(@PathVariable("idresposta") Long idresposta){
        return ResponseEntity.ok().body(respostaService.consultarByIdComoAdmin(idresposta));
    }
    @GetMapping
    public ResponseEntity<List<RespostaDTO>> consultarComoUser(){
        return ResponseEntity.status(HttpStatus.OK).body(respostaService.consultarComoUser());
    }
    @GetMapping("/paginado")
    public ResponseEntity<Page<RespostaDTO>> consultarComoUser(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(respostaService.consultarComoUserPaginado(page));
    }
    @GetMapping("/{idresposta}")
    public ResponseEntity<Optional<RespostaDTO>> consultarByIdComoUser(@PathVariable("idresposta") Long idrespostaDTO){
        return ResponseEntity.ok().body(respostaService.consultarByIdComoUser(idrespostaDTO));
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
