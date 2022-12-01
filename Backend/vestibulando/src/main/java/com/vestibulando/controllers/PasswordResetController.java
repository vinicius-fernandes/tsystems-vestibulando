package com.vestibulando.controllers;

import com.vestibulando.dtos.AlterarSenhaDTO;
import com.vestibulando.dtos.GerarTokenResetPasswordDTO;
import com.vestibulando.dtos.SimpleResponse;
import com.vestibulando.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passwordReset")
public class PasswordResetController {
    @Autowired
    PasswordResetService passwordResetService;

    @PostMapping("/alterarSenha")
    public ResponseEntity<SimpleResponse> alterarSenha(@RequestBody AlterarSenhaDTO alterarSenhaDTO){
        passwordResetService.alterarSenha(alterarSenhaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new SimpleResponse("Senha alterada com sucesso"));

    }

    @PostMapping("/gerarToken")
    public ResponseEntity<SimpleResponse> sendRestPass(@RequestBody GerarTokenResetPasswordDTO  gerarTokenResetPasswordDTO){
        passwordResetService.gerarToken(gerarTokenResetPasswordDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new SimpleResponse("Um e-mail será enviado caso o usuário exista"));
    }
}
