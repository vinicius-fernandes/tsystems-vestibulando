package com.vestibulando.services;

import com.vestibulando.entities.Usuario;
import com.vestibulando.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    IUsuarioRepository usuarioRepository;

    public List<Usuario> consultarUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario consultarById(long idUsuario) {
        Optional<Usuario> obj = usuarioRepository.findById(idUsuario);
        Usuario user = obj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontada"));
        return user;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        Usuario user = usuarioRepository.findByEmail(usuario.getEmail());
        if (user != null && (usuario.getId() == null || usuario.getId() != user.getId())) {
            throw new EntityExistsException("Email já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario alterarUsuario(Long idUsuario, Usuario usuario) {
        Usuario user = this.consultarById(idUsuario);
        user.setEmail(usuario.getEmail());
        user.setNome(usuario.getNome());
        user.setTipo(usuario.getTipo());
        user.setSenha(usuario.getSenha());

        return this.salvarUsuario(user);
    }

    public void apagarUsuario(long idUsuario) {
        usuarioRepository.delete(this.consultarById(idUsuario));
    }
}
