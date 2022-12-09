package com.vestibulando.services;

import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Resposta;
import com.vestibulando.repositories.IRespostaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class RespostaServiceTests {
    @InjectMocks
    private RespostaService respostaService;

    @Mock
    private IRespostaRepository respostaRepository;

    @Mock
    private PerguntaService perguntaService;

    Resposta resposta = new Resposta();
    Resposta respostaAlterada = new Resposta();
    Pergunta pergunta = new Pergunta();
    List<Resposta> lista = new ArrayList<>();

    @BeforeEach
    public void beforeEach(){
        resposta.setDescricao("Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit");
        resposta.setCorreta(false);
        pergunta.setId(1);
        resposta.setPergunta(pergunta);

        respostaAlterada.setDescricao("Resposta Alterada");
        respostaAlterada.setCorreta(true);
        respostaAlterada.setPergunta(pergunta);
    }

    @Test
    public void RetornaRespostaAposCria(){
        Mockito.when(perguntaService.obter(resposta.getPergunta().getId())).thenReturn(pergunta);
        Mockito.when(respostaRepository.save(resposta)).thenReturn(resposta);
        Assertions.assertNotNull(respostaService.salvar(resposta));
        Mockito.verify(respostaRepository,Mockito.times(1)).save(resposta);
    }

    @Test
    public void RetornaListaAposConsultar(){
        Mockito.when(respostaRepository.findAll()).thenReturn(lista);
        Assertions.assertNotNull(respostaService.consultarComoAdmin());
    }

    @Test
    public void RetornaRespostaAposConsultarById(){
        Assertions.assertNotNull(respostaService.consultarByIdComoAdmin(1L));
    }

    @Test
    public void RetornaOkAposExcluir(){
        Mockito.when(respostaRepository.findById(resposta.getId())).thenReturn(Optional.of(resposta));
        Mockito.doNothing().when(respostaRepository).delete(resposta);
        Assertions.assertDoesNotThrow( () -> respostaService.excluir(resposta.getId()));
        Mockito.verify(respostaRepository, Mockito.times(1)).delete(resposta);
    }

}
