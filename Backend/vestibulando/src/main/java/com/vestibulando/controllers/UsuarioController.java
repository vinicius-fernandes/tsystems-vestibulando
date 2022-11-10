package com.vestibulando.controllers;

import com.vestibulando.entities.Usuario;
import com.vestibulando.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> consultarUsuario() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.consultarUsuario());
    }

    @GetMapping("/{idUsuário}")
    public ResponseEntity<Usuario> consultarById(@PathVariable("idUsuário") Long idusuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.consultarById(idusuario));
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> alterarUsuario(@PathVariable("idUsuario") Long idUsuario,
                                                  @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.alterarUsuario(idUsuario,usuario));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> apagarUsuario(@PathVariable("idUsuario") long idusuario){
        usuarioService.apagarUsuario(idusuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
