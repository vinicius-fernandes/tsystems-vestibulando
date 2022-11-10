package com.vestibulando.controllers;

import com.vestibulando.entities.Simulado;
import com.vestibulando.services.SimuladoService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulados")
public class SimuladoController {
    @Autowired
    SimuladoService simuladoService;

    @GetMapping
    public ResponseEntity<List<Simulado>> consultar() {
        return ResponseEntity.ok().body(simuladoService.consultar());
    }

    @GetMapping("/{idSimulado}")
    public ResponseEntity<Simulado> consultar(@PathVariable("idSimulado") Long id) {
        return ResponseEntity.ok().body(simuladoService.consultar(id));
    }

    @PostMapping
    public ResponseEntity<Simulado> salvar(@RequestBody Simulado s) {
        return ResponseEntity.ok().body(simuladoService.salvar(s));
    }
}
