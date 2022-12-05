package com.vestibulando.services;

import com.vestibulando.dtos.AlterarSenhaDTO;
import com.vestibulando.dtos.GerarTokenResetPasswordDTO;
import com.vestibulando.entities.PasswordResetToken;
import com.vestibulando.entities.Usuario;
import com.vestibulando.mail.EmailServiceImpl;
import com.vestibulando.repositories.IPasswordResetTokenRepository;
import com.vestibulando.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    IUsuarioRepository userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    IPasswordResetTokenRepository passwordResetTokenRepo;

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
            msg+="<p>O link é válido por "+ PasswordResetToken.EXPIRATION +" minutos<p>";


            emailService.sendMimeMessage(usuario.get().getEmail(),"[Vestibulando]Resetar a senha",msg);

        }
    }

    public void alterarSenha(AlterarSenhaDTO alterarSenhaDTO){
        PasswordResetToken passResToken = passwordResetTokenRepo.findByToken(alterarSenhaDTO.getToken()).orElseThrow(()->new EntityNotFoundException("Não foi possível indentificar o token"));

        if(!passResToken.isTokenValid())
            throw  new IllegalStateException("O token para alterar a senha expirou, requisite a troca de senha novamente!");

        if(alterarSenhaDTO.getNovaSenha().isBlank()){
            throw new IllegalArgumentException("A senha deve ser preenchida");
        }

        if(!alterarSenhaDTO.senhasCoincidem()){
            throw new IllegalArgumentException("As senhas devem coincidir !");
        }

        Usuario user = passResToken.getUser();

        user.setSenha(passwordEncoder.encode(alterarSenhaDTO.getNovaSenha()));

        userRepo.save(user);
    }

}
