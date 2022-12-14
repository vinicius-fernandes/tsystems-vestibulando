package com.vestibulando.controllers;

import com.vestibulando.dtos.NotaSimuladoUsuarioDTO;
import com.vestibulando.dtos.RankingSimuladoDTO;
import com.vestibulando.entities.RespostasUsuarios;
import com.vestibulando.services.RespostasUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/respostasUsuarios")
@CrossOrigin(origins = "*")
public class RespostasUsuariosController {

    @Autowired
    RespostasUsuariosService respostasUsuariosService;
    @GetMapping
    public ResponseEntity<Page<RespostasUsuarios>> listar(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.listar(page));
    }
    @GetMapping("/simulados/{idsimulado}")
    public ResponseEntity<List<RespostasUsuarios>> listarSimulado(@PathVariable("idsimulado") long idSimulado){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.listar(idSimulado));
    }
    @GetMapping("/rankingGlobal")
    public ResponseEntity<Page<RankingSimuladoDTO>> rankingGlobal(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.getRankingGlobal(page));
    }
    @GetMapping("/rankingSimulado/{idsimulado}")
    public ResponseEntity<List<RankingSimuladoDTO>> listarRankingSimulado(@PathVariable("idsimulado") long idSimulado){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.getRankingSimulado(idSimulado));
    }
    @GetMapping("/notaSimuladoUsuario/{idusuario}/{idsimulado}")
    public ResponseEntity<NotaSimuladoUsuarioDTO> notaSimuladoUsuario(@PathVariable("idsimulado") long idSimulado, @PathVariable("idusuario") long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.getNotaSimuladoUsuario(idUsuario,idSimulado));
    }
    @GetMapping("/notasSimuladosUsuario/{idusuario}")
    public ResponseEntity<List<NotaSimuladoUsuarioDTO>> notasSimuladosUsuario(@PathVariable("idusuario") long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.getNotasSimuladosUsuario(idUsuario));
    }
    @GetMapping("/perguntasCorretas/{idusuario}/{idsimulado}")
    public ResponseEntity<List<Long>> perguntasCorretas(@PathVariable("idusuario") Long idUsuario,@PathVariable("idsimulado") Long idSimulado){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.perguntasCorretasSimuladoUsuario(idUsuario,idSimulado));
    }
    @GetMapping("/{id}")
    public ResponseEntity<RespostasUsuarios> listar(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.obter(id));
    }
    @PostMapping
    public ResponseEntity<RespostasUsuarios> criar(@RequestBody RespostasUsuarios respostasUsuarios){
        return ResponseEntity.status(HttpStatus.CREATED).body(respostasUsuariosService.salvar(respostasUsuarios));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RespostasUsuarios> editar(@PathVariable("id") long id,@RequestBody RespostasUsuarios respostasUsuarios){
        return ResponseEntity.status(HttpStatus.OK).body(respostasUsuariosService.editar(id,respostasUsuarios));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") long id){
        respostasUsuariosService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
    }
}
