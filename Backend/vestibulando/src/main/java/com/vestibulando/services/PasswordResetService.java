package com.vestibulando.services;

import com.vestibulando.dtos.GerarTokenResetPasswordDTO;
import com.vestibulando.entities.PasswordResetToken;
import com.vestibulando.entities.Usuario;
import com.vestibulando.mail.EmailServiceImpl;
import com.vestibulando.repositories.IPasswordResetToken;
import com.vestibulando.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    IUsuarioRepository userRepo;
    @Autowired
    IPasswordResetToken passwordResetTokenRepo;

    @Autowired
    EmailServiceImpl emailService;

    public void gerarToken(GerarTokenResetPasswordDTO gerarTokenResetPasswordDTO){
        Optional< Usuario > usuario = userRepo.findByEmail(gerarTokenResetPasswordDTO.getEmail());

        if(usuario.isPresent()){
            String token = UUID.randomUUID().toString();

            PasswordResetToken passwordResetToken = new PasswordResetToken(token, usuario.get());

            passwordResetTokenRepo.save(passwordResetToken);

            String link = gerarTokenResetPasswordDTO.getUrlFrom() + "/alterarSenha/" +token;

            String msg = "<h1>Requisição de mudança de senha</h1>";
            msg+="<p>Para alterar sua senha clique no link abaixo:<p>";
            msg+= "<a href='" + link + "'> " + link + "</a>";


            emailService.sendMimeMessage(usuario.get().getEmail(),"[Vestibulando]Resetar a senha",msg);

        }
    }

}
