package com.vestibulando.services;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.vestibulando.dtos.UsuarioDTO;
import com.vestibulando.entities.Role;
import com.vestibulando.entities.Usuario;
import com.vestibulando.enums.EnumsUsuario;
import com.vestibulando.excepitions.DeleteComAssociacoes;
import com.vestibulando.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.*;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    public List<UsuarioDTO> consultarUsuario(String email) {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        if (email == null) {
            List<Usuario> users = usuarioRepository.findAll();
            for (Usuario u : users) {
                usuarioDTOS.add(new UsuarioDTO(u));
            }
            return usuarioDTOS;
        }
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario == null){
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        usuarioDTOS.add(new UsuarioDTO(usuario));
        return usuarioDTOS;
    }

    public UsuarioDTO consultarById(long idUsuario) {
        Optional<Usuario> obj = usuarioRepository.findById(idUsuario);
        Usuario user = obj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontada"));
        UsuarioDTO userDTO = new UsuarioDTO(user);
        return userDTO;
    }

    public UsuarioDTO salvarUsuario(Usuario usuario) {

        if(usuario.getRoles().isEmpty()){
            Role rl = new Role();
            rl.setId(1L);
            Set<Role> rlList= new LinkedHashSet<>();
            rlList.add(rl);
            usuario.setRoles(rlList);
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioRepository.save(usuario));
        return usuarioDTO;
    }

    public UsuarioDTO alterarUsuario(Long idUsuario, Usuario usuario) {
        Usuario user = this.consultar(idUsuario);
        user.setEmail(usuario.getEmail());
        user.setNome(usuario.getNome());
        user.setRoles(usuario.getRoles());
        if (usuario.getSenha() != null && !usuario.getSenha().isBlank() && !usuario.getSenha().equals(user.getSenha())) {
            user.setSenha(usuario.getSenha());
        }

        return this.salvarUsuario(user);
    }

    private Usuario consultar(long idUsuario) {
        Optional<Usuario> obj = usuarioRepository.findById(idUsuario);
        Usuario user = obj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontada"));
        return user;
    }

    public void apagarUsuario(long idUsuario) {
        Usuario usuario = this.consultar(idUsuario);

        try {
            usuarioRepository.delete(usuario);
        }
        catch (Exception e){
            throw  new DeleteComAssociacoes("Não é possível deletar o usuário pois há itens associados a ele");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }
}
