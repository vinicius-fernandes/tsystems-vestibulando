package com.vestibulando.services;

import com.vestibulando.dtos.UsuarioDTO;
import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Usuario;
import com.vestibulando.repositories.IPerguntaRepository;
import com.vestibulando.repositories.IUsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTests {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    IUsuarioRepository usuarioRepository;

    @Test
    public void SalvarUsuarioRetornaUsuarioDTO(){
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("123456");
        usuario.setId(1l);

        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Assertions.assertSame(UsuarioDTO.class, usuarioService.salvarUsuario(usuario).getClass());
    }

    @Test
    public void ListarUsuarios(){
        Mockito.when(usuarioRepository.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertNotNull(usuarioService.consultarUsuario(null));

    }

    @Test
    public void ApagarUsuarioRetornaNada(){
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("123456");
        usuario.setId(1l);

        Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        Mockito.doNothing().when(usuarioRepository).delete(usuario);

        Assertions.assertDoesNotThrow(()-> usuarioService.apagarUsuario(usuario.getId()));
    }
    @Test
    public void LancaExcecaoQuandoSenhaEmBranco(){
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("");
        usuario.setId(1l);

        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Assertions.assertThrows(MethodArgumentNotValidException.class,()-> usuarioService.salvarUsuario(usuario));
        //Assertions.assertDoesNotThrow(()-> usuarioService.salvarUsuario(usuario));
    }

}
