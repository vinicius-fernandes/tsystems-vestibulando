package com.vestibulando.controllers;

import com.vestibulando.dtos.UsuarioDTO;
import com.vestibulando.entities.Usuario;
import com.vestibulando.services.UsuarioService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> consultarUsuario(@RequestParam(value= "email",required = false) String email) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.consultarUsuario(email));
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<UsuarioDTO>> consultarPaginado(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.pageUsuarios(page));
    }

    @GetMapping("/{idUsuário}")
    public ResponseEntity<UsuarioDTO> consultarById(@PathVariable("idUsuário") Long idusuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.consultarById(idusuario));
    }
    @GetMapping("pesquisar/{idRole}")
    public ResponseEntity<List<UsuarioDTO>> pesquisarUsuario(@PathVariable(value = "idRole") Long idRole,
                                                    @RequestParam(value = "pesquisa", required = false) String pesquisa) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.pesquisar(idRole, pesquisa));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> alterarUsuario(@PathVariable("idUsuario") Long idUsuario,
                                                  @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.alterarUsuario(idUsuario,usuario));
    }
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> apagarUsuario(@PathVariable("idUsuario") long idusuario){
        usuarioService.apagarUsuario(idusuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
