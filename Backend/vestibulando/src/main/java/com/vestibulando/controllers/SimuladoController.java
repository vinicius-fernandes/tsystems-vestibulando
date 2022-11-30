package com.vestibulando.controllers;

import com.vestibulando.dtos.GerarSimuladoDTO;
import com.vestibulando.dtos.SimuladoDTO;
import com.vestibulando.entities.*;
import com.vestibulando.repositories.ISimuladoRepository;
import com.vestibulando.services.SimuladoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/simulados")
@CrossOrigin(origins = "*")
public class SimuladoController {
    @Autowired
    SimuladoService simuladoService;
    @Autowired
    ISimuladoRepository simuladoRepository;

    @GetMapping
    public ResponseEntity<List<Simulado>> consultar() {
        return ResponseEntity.ok().body(simuladoService.consultar());
    }
    @GetMapping("/{idSimulado}")
    public ResponseEntity<Simulado> consultar(@PathVariable("idSimulado") Long id) {
        return ResponseEntity.ok().body(simuladoService.consultar(id));
    }
    @GetMapping("realizar/{idSimulado}")
    public ResponseEntity<SimuladoDTO> realizar(@PathVariable("idSimulado") Long id) {
        return ResponseEntity.ok().body(simuladoService.realizar(id));
    }
    @GetMapping("/{idSimulado}/perguntas")
    public ResponseEntity<Set<Pergunta>> consultarPerguntas(@PathVariable("idSimulado") Long idSimulado) {
        return ResponseEntity.ok().body(simuladoService.consultarPerguntas(idSimulado));
    }
    @GetMapping("/{idSimulado}/bancas")
    public ResponseEntity<Set<Banca>> consultarBancas(@PathVariable("idSimulado") Long idSimulado) {
        return ResponseEntity.ok().body(simuladoService.consultarBancas(idSimulado));
    }
    @GetMapping("/{idSimulado}/materias")
    public ResponseEntity<Set<Materia>> consultarMaterias(@PathVariable("idSimulado") Long idSimulado) {
        return ResponseEntity.ok().body(simuladoService.consultarMaterias(idSimulado));
    }
    @PostMapping
    public ResponseEntity<Simulado> salvar(@RequestBody Simulado s) {
        return ResponseEntity.ok().body(simuladoService.salvar(s));
    }
    @PutMapping("/{idSimulado}/bancas")
    public ResponseEntity<Simulado> adicionarBanca(@PathVariable("idSimulado") Long idSimulado, @RequestBody Banca b) {
        return ResponseEntity.ok().body(simuladoService.adicionarBanca(idSimulado, b));
    }
    @PutMapping("/{idSimulado}/materias")
    public ResponseEntity<Simulado> adicionarMateria(@PathVariable("idSimulado") Long idSimulado, @RequestBody Materia m) {
        return ResponseEntity.ok().body(simuladoService.adicionarMateria(idSimulado, m));
    }
    @PutMapping("/{idSimulado}/perguntas")
    public ResponseEntity<Simulado> adicionarPergunta(@PathVariable("idSimulado") Long idSimulado, @RequestBody Pergunta p) {
        return ResponseEntity.ok().body(simuladoService.adicionarPergunta(idSimulado, p));
    }
    @DeleteMapping("/{idSimulado}/perguntas/{idPergunta}")
    public ResponseEntity<Simulado> deletarPergunta(@PathVariable("idSimulado") Long idSimulado, @PathVariable("idPergunta") Long idPergunta) {
        return ResponseEntity.ok().body(simuladoService.deletarPergunta(idSimulado, idPergunta));
    }
    @DeleteMapping("/{idSimulado}/bancas/{idBanca}")
    public ResponseEntity<Simulado> deletarBanca(@PathVariable("idSimulado") Long idSimulado, @PathVariable("idBanca") Long idBanca) {
        return ResponseEntity.ok().body(simuladoService.deletarBanca(idSimulado, idBanca));
    }
    @DeleteMapping("/{idSimulado}/materias/{idMateria}")
    public ResponseEntity<Simulado> deletarMateria(@PathVariable("idSimulado") Long idSimulado, @PathVariable("idMateria") Long idMateria) {
        return ResponseEntity.ok().body(simuladoService.deletarMateria(idSimulado, idMateria));
    }
    @DeleteMapping("/{idSimulado}")
    public ResponseEntity<String> deletarSimulado(@PathVariable("idSimulado") Long id) {
        return ResponseEntity.ok().body(simuladoService.deletarSimulado(id));
    }
    @PutMapping("/{idSimulado}")
    public ResponseEntity<Simulado> alterarSimulado(@PathVariable("idSimulado") Long id, @RequestBody Simulado s) {
        return ResponseEntity.ok().body(simuladoService.alterar(id, s));
    }
    @PostMapping("/gerar")
    public ResponseEntity<SimuladoDTO> gerarSimulado(@Valid @RequestBody GerarSimuladoDTO gerarSimuladoDTO){
        return ResponseEntity.ok().body(simuladoService.gerarSimulado(gerarSimuladoDTO));
    }
    @PutMapping("/{idSimulado}/perguntas/{idPergunta}")
    public ResponseEntity<Simulado> alterarPergunta(@PathVariable("idSimulado") Long idSimulado, @PathVariable("idPergunta") Long idPergunta, @RequestBody Pergunta p) {
        return ResponseEntity.ok().body(simuladoService.alterarPergunta(idSimulado, idPergunta, p));
    }
    @PutMapping("/{idSimulado}/materias/{idMateria}")
    public ResponseEntity<Simulado> alterarMateria(@PathVariable("idSimulado") Long idSimulado, @PathVariable("idMateria") Long idMateria, @RequestBody Materia m) {
        return ResponseEntity.ok().body(simuladoService.alterarMateria(idSimulado, idMateria, m));
    }
    @PutMapping("/{idSimulado}/bancas/{idBanca}")
    public ResponseEntity<Simulado> alterarBanca(@PathVariable("idSimulado") Long idSimulado, @PathVariable("idBanca") Long idBanca, @RequestBody Banca b) {
        return ResponseEntity.ok().body(simuladoService.alterarBanca(idSimulado, idBanca, b));
    }
}
