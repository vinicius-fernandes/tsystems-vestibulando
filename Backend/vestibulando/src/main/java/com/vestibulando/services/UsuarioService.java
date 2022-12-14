package com.vestibulando.services;

import com.vestibulando.dtos.UsuarioDTO;
import com.vestibulando.entities.Role;
import com.vestibulando.entities.Usuario;
import com.vestibulando.excepitions.ArgumentoDuplicado;
import com.vestibulando.excepitions.DeleteComAssociacoes;
import com.vestibulando.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;
    public List<UsuarioDTO> consultarUsuario(String email) {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        if (email == null) {
            List<Usuario> users = usuarioRepository.findAll();
            for (Usuario u : users) {
                usuarioDTOS.add(new UsuarioDTO(u));
            }
            return usuarioDTOS;
        }
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado"));

        usuarioDTOS.add(new UsuarioDTO(usuario));
        return usuarioDTOS;
    }

    public Page<UsuarioDTO> pageUsuarios(Pageable page){
        Page<Usuario> p = usuarioRepository.findAll(page);
        Page<UsuarioDTO> pDto = p.map(UsuarioDTO::new);
        return pDto;
    }

    public UsuarioDTO consultarById(long idUsuario) {
        Optional<Usuario> obj = usuarioRepository.findById(idUsuario);
        Usuario user = obj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontada"));
        UsuarioDTO userDTO = new UsuarioDTO(user);
        return userDTO;
    }

    public UsuarioDTO salvarUsuario(Usuario usuario)  {
       Optional<Usuario> user= usuarioRepository.findByEmail(usuario.getEmail());

       if(user.isPresent()  && !Objects.equals(usuario.getId(), user.get().getId())){
           throw new ArgumentoDuplicado("Email já está cadastrado");
       }
        if(usuario.getRoles().isEmpty()){
            Role rl = new Role();
            rl.setId(1L);
            Set<Role> rlList= new LinkedHashSet<>();
            rlList.add(rl);
            usuario.setRoles(rlList);
        }
//        if(usuario.getNome().indexOf(" ") == -1){
//            throw new ArgumentoDuplicado("Insira nome e sobrenome");
//        }
        if(!user.isPresent() || !user.get().getSenha().equals(usuario.getSenha())) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioRepository.save(usuario));
        return usuarioDTO;
    }

    public UsuarioDTO alterarUsuario(Long idUsuario, Usuario usuario) {
        Usuario user = this.consultar(idUsuario);
        user.setEmail(usuario.getEmail());
        user.setNome(usuario.getNome());
        user.setRoles(usuario.getRoles());
        if (usuario.getSenha() != null && !usuario.getSenha().isBlank() && !passwordEncoder.matches(usuario.getSenha(),user.getSenha())) {
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
            throw new DeleteComAssociacoes("Não é possível deletar o usuário pois há itens associados a ele");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Page<UsuarioDTO> pesquisar(Long idRole, String pesquisa,Pageable page) {
        if(idRole != 0) {
            Role role = roleService.obter(idRole);
            if(pesquisa != null) {
                if (!pesquisa.contains("@")) {
                    return usuarioRepository.findByRolesAndNomeContainsIgnoreCase(role, pesquisa,page);
                }
                return usuarioRepository.findByRolesAndEmailContainsIgnoreCase(role, pesquisa,page);

            }
            return usuarioRepository.findByRoles(role,page);
        }
        if (pesquisa != null) {
            if (!pesquisa.contains("@")) {
                return usuarioRepository.findByNomeContainsIgnoreCase(pesquisa, page);
            }
            return usuarioRepository.findByEmailContainsIgnoreCase(pesquisa,page);

        }

        return this.pageUsuarios(page);
    }
}
