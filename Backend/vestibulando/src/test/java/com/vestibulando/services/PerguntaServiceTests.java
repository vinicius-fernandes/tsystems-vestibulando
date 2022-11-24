package com.vestibulando.services;

import com.vestibulando.entities.*;
import com.vestibulando.repositories.IPerguntaRepository;
import com.vestibulando.services.PerguntaService;
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
import java.util.*;

import static org.mockito.ArgumentMatchers.eq;

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
        pergunta.setId(idExistente);
        Mockito.when(perguntaRepository.save(Mockito.any(Pergunta.class))).thenReturn(pergunta);

        Mockito.when(perguntaRepository.findAll()).thenReturn(new ArrayList<Pergunta>());

        Mockito.when(perguntaRepository.findAll(page)).thenReturn(pagePergunta);

        Mockito.when(perguntaRepository.findBySimulado(Mockito.any(Simulado.class),eq(page))).thenReturn(pagePergunta);
        Mockito.when(perguntaRepository.findByMateria(Mockito.any(Materia.class),eq(page))).thenReturn(pagePergunta);
        Mockito.when(perguntaRepository.findByBanca(Mockito.any(Banca.class),eq(page))).thenReturn(pagePergunta);
        Mockito.when(perguntaRepository.findByCorpoContaining(Mockito.any(String.class),eq(page))).thenReturn(pagePergunta);

        Mockito.doNothing().when(perguntaRepository).delete(Mockito.any(Pergunta.class));

        Mockito.when(perguntaRepository.findById(idExistente)).thenReturn(Optional.of(pergunta));

        Mockito.when(perguntaRepository.findById(idInexistente)).thenReturn(Optional.empty());
        Mockito.doNothing().when(perguntaRepository).delete(Mockito.any(Pergunta.class));
    }

    @Test
    public void RetornaPerguntaAposCriar(){

        Assertions.assertNotNull(perguntaService.salvar(pergunta));

    }

    @Test
    public void RetornaPerguntaAposAtualizar(){
        Resposta resp1 = new Resposta();
        Resposta resp2 = new Resposta();
        Resposta resp3 = new Resposta();
        Set<Resposta> respostasPergunta = new LinkedHashSet<>();
        respostasPergunta.add(resp1);
        respostasPergunta.add(resp2);
        respostasPergunta.add(resp3);
        pergunta.setRespostas(respostasPergunta);
        Pergunta perguntaAtualizada = perguntaService.atualizar(1L,pergunta);
        boolean todasAsRespostasPossuemOIdDaPergunta = perguntaAtualizada.getRespostas().stream().allMatch(resp->resp.getPergunta().getId()==1L);
        Assertions.assertNotNull(pergunta);
        Assertions.assertTrue(todasAsRespostasPossuemOIdDaPergunta);
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

    @Test
    public void EncontrarPorSimuladoRetornaPagina(){
        Page<Pergunta> pagePerguntaSimulado = perguntaService.findBySimulado(1L,page);
        Assertions.assertNotNull( pagePerguntaSimulado);
    }

    @Test
    public void EncontrarPorMateriaRetornaPagina(){
        Page<Pergunta> pagePerguntaMateria = perguntaService.findByMateria(1L,page);
        Assertions.assertNotNull(pagePerguntaMateria);
    }
    @Test
    public void EncontrarPorBancaRetornaPagina(){
        Page<Pergunta> pagePerguntaBanca= perguntaService.findByBanca(1L,page);
        Assertions.assertNotNull(pagePerguntaBanca);
    }


    @Test
    public void EncontrarPorCorpoRetornaPagina(){
        Page<Pergunta> pagePerguntaCorpo= perguntaService.findByCorpo("Pergunta x",page);
        Assertions.assertNotNull(pagePerguntaCorpo);
    }

    @Test
    public void ExcluirNaoRetornaNada(){
        Assertions.assertDoesNotThrow(()->perguntaService.deletar(idExistente));
    }
}
