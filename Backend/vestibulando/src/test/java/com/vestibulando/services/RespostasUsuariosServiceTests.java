package com.vestibulando.services;

import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.RespostasUsuarios;
import com.vestibulando.entities.Simulado;
import com.vestibulando.repositories.IRespostasUsuariosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class RespostasUsuariosServiceTests {

    @InjectMocks
    RespostasUsuariosService    respostasUsuariosService;

    @Mock
    IRespostasUsuariosRepository respostasUsuariosRepository;

    @Mock
    Page<RespostasUsuarios> pageRespUsuarios;

    private Pageable page = PageRequest.of(0,8);

    RespostasUsuarios respostasUsuarios;

    long idExistente = 1L;
    long idInexistente = 4L;

    @BeforeEach
    public void beforeEach(){
        respostasUsuarios = new RespostasUsuarios();
        respostasUsuarios.setId(idExistente);
        java.util.List<RespostasUsuarios> respostasUsuariosList = new ArrayList<>();

        Mockito.when(respostasUsuariosRepository.save(Mockito.any(RespostasUsuarios.class))).thenReturn(respostasUsuarios);

        Mockito.when(respostasUsuariosRepository.findAll(page)).thenReturn(pageRespUsuarios);

        Mockito.when(respostasUsuariosRepository.findBySimulado(Mockito.any(Simulado.class))).thenReturn(respostasUsuariosList);

        Mockito.when(respostasUsuariosRepository.findById(idExistente)).thenReturn(Optional.of(respostasUsuarios));

        Mockito.when(respostasUsuariosRepository.findById(idInexistente)).thenReturn(Optional.empty());
        Mockito.doNothing().when(respostasUsuariosRepository).delete(Mockito.any(RespostasUsuarios.class));

    }

    @Test
    public void ListarComPaginacaoRetornaPaginacao(){
        Page<RespostasUsuarios> pageRes = respostasUsuariosService.listar(page);
        Assertions.assertNotNull(pageRes);
        Assertions.assertInstanceOf(Page.class,pageRes);
    }
    @Test
    public void RetornaRespostaUsuarioAoSalvar(){
        RespostasUsuarios respCriada = respostasUsuariosService.salvar(respostasUsuarios);
        Assertions.assertNotNull(respCriada);
        Assertions.assertInstanceOf(RespostasUsuarios.class,respCriada);
    }

    @Test
    public void RetornaListaDeRespostaUsuarioAoConsultarPorSimulado(){
        List<RespostasUsuarios> resConsulta = respostasUsuariosService.listar(1L);
        Assertions.assertNotNull(resConsulta);
        Assertions.assertInstanceOf(ArrayList.class,resConsulta);
    }

    @Test
    public void ObterItemExistenteRetornaOItem(){
        RespostasUsuarios respObtida = respostasUsuariosService.obter(idExistente);
        Assertions.assertNotNull(respObtida);
        Assertions.assertInstanceOf(RespostasUsuarios.class,respObtida);
    }
    @Test
    public void ObterItemInexistenteLancaExcecao(){
        Assertions.assertThrows(EntityNotFoundException.class,()->respostasUsuariosService.obter(idInexistente));
    }

    @Test
    public void RetornaRespostaUsuarioAoAtualizar(){
        RespostasUsuarios respEditada = respostasUsuariosService.editar(idExistente,respostasUsuarios);
        Assertions.assertNotNull(respEditada);
        Assertions.assertInstanceOf(RespostasUsuarios.class,respEditada);
    }

    @Test
    public void NaoRetornaAoDeletar(){
        Assertions.assertDoesNotThrow(()->respostasUsuariosService.deletar(idExistente));
    }
}
