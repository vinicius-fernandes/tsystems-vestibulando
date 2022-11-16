package com.vestibulando.services;

import com.vestibulando.entities.Pergunta;
import com.vestibulando.repositories.IPerguntaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PerguntaServiceTests {
    @InjectMocks
    PerguntaService perguntaService;

    @Mock
    IPerguntaRepository perguntaRepository;

    @Mock
    Page<Pergunta> pagePergunta;

    private Pageable page = PageRequest.of(0,8);

    Pergunta pergunta;

    long idExistente = 1L;
    long idInexistente = 2L;

    @BeforeEach
    public void beforeEach(){
        pergunta= new Pergunta();
        Mockito.when(perguntaRepository.save(Mockito.any(Pergunta.class))).thenReturn(pergunta);
        Mockito.when(perguntaRepository.findAll()).thenReturn(new ArrayList<Pergunta>());
        Mockito.when(perguntaRepository.findAll(page)).thenReturn(pagePergunta);
        Mockito.doNothing().when(perguntaRepository).delete(Mockito.any(Pergunta.class));
        Mockito.when(perguntaRepository.findById(idExistente)).thenReturn(Optional.of(pergunta));
        Mockito.when(perguntaRepository.findById(idInexistente)).thenReturn(Optional.empty());
    }

    @Test
    public void RetornaPerguntaAposCriar(){
        Assertions.assertNotNull(perguntaService.salvar(pergunta));
    }

    @Test
    public void RetornaPerguntaAposAtualizar(){
        Assertions.assertNotNull(perguntaService.atualizar(1L,pergunta));
    }


    @Test
    public void ObterItemExistenteRetornaOItem(){
        Assertions.assertNotNull(perguntaService.obter(idExistente));
    }
    @Test
    public void ObterItemNaoExistenteLancaExcecao(){
        Assertions.assertThrows(EntityNotFoundException.class,()->perguntaService.obter(idInexistente));
    }
    @Test
    public void ListarSemPaginacaoRetornaLista(){
        Assertions.assertNotNull(perguntaService.listar());
    }

    @Test
    public void ListarComPaginacaoRetornaPaginacao(){
        Assertions.assertNotNull(perguntaService.listar(page));
    }



}
