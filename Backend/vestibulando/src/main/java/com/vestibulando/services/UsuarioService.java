package com.vestibulando.services;

import com.vestibulando.entities.Usuario;
import com.vestibulando.repositories.UsuarioRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> consultarUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario consultarById(long idUsuario) {
        //Após mplementar excessão
        /*Optional<Usuario> obj = usuarioRepository.findById(idUsuario);
        Usuario user = obj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontada"));
        return user;*/

        Optional<Usuario> user = usuarioRepository.findById(idUsuario);
        return user.get();
    }
    public Usuario salvarUsuario(Usuario usuario) {
        Usuario user = usuarioRepository.findByEmail(usuario.getEmail());
        if (user != null) {
            throw new EntityExistsException("Email já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }


}
