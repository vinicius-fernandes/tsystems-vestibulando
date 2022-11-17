package com.vestibulando.services;

import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Resposta;
import com.vestibulando.repositories.IRespostaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class RespostaServiceTests {
    @InjectMocks
    private RespostaService respostaService;

    @Mock
    private IRespostaRepository respostaRepository;

    @Mock
    private PerguntaService perguntaService;

    @Test
    public void RetornaRespostaAposCria(){
        Resposta resposta = new Resposta();
        resposta.setDescricao("Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit");
        resposta.setCorreta(false);
        Pergunta pergunta = new Pergunta();

        Mockito.when(perguntaService.obter(resposta.getPergunta().getId())).thenReturn(pergunta);

        Mockito.when(respostaRepository.save(resposta)).thenReturn(resposta);
        Assertions.assertNotNull(respostaService.salvar(resposta));

        Mockito.verify(respostaRepository,Mockito.times(1)).save(resposta);
    }

    @Test
    public void RetornaOkAposExcluir(){
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setDescricao("Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit");
        resposta.setCorreta(false);

        Mockito.when(respostaRepository.findById(resposta.getId())).thenReturn(Optional.of(resposta));
        Mockito.doNothing().when(respostaRepository).delete(resposta);

        Assertions.assertDoesNotThrow( () -> respostaService.excluir(resposta.getId()));
        Mockito.verify(respostaRepository, Mockito.times(1)).delete(resposta);
    }


}
