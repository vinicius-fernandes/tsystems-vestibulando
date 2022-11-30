package com.vestibulando.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Resposta;
import com.vestibulando.services.PerguntaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.LinkedHashSet;
import java.util.Set;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PerguntasControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerguntaService perguntaService;
    @Autowired
    ObjectMapper objectMapper;
    Pergunta pergunta;

    @BeforeEach
    public void beforeEach(){
        pergunta = new Pergunta();
        pergunta.setCorpo("Pergunta teste 12345436");
        Resposta resp1 = new Resposta();
        Resposta resp2 = new Resposta();
        Resposta resp3 = new Resposta();
        Resposta resp4 = new Resposta();
        resp1.setId(1L);
        resp2.setId(2L);
        resp3.setId(3L);
        resp4.setId(4L);
        resp1.setCorreta(true);
        resp2.setCorreta(false);
        resp3.setCorreta(false);
        resp4.setCorreta(false);
        Set<Resposta> respostasPergunta = new LinkedHashSet<>();
        respostasPergunta.add(resp1);
        respostasPergunta.add(resp2);
        respostasPergunta.add(resp3);
        respostasPergunta.add(resp4);
        pergunta.setRespostas(respostasPergunta);
    }

    @Test
    public void ListarPerguntasRetornaOk() throws Exception {
        mockMvc.perform(get("/perguntas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void ObterPerguntaRetornaOk() throws Exception {
        mockMvc.perform(get("/perguntas/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void ObterPerguntaPorBancaRetornaOk() throws Exception {
        mockMvc.perform(get("/perguntas/banca/{id}",2L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void ObterPerguntaPorMateriaRetornaOk() throws Exception {
        mockMvc.perform(get("/perguntas/materia/{id}",2L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void ObterPerguntaPorSimuladoRetornaOk() throws Exception {
        mockMvc.perform(get("/perguntas/simulado/{id}",2L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void ObterPerguntaPorCorpoRetornaOk() throws Exception {
        mockMvc.perform(get("/perguntas/pesquisar/corpo}","Pergunta1231 asda12")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void CriarPerguntaRetornaCreated() throws Exception {
        ResultActions ra = mockMvc.perform(post("/perguntas")
                .content(objectMapper.writeValueAsString(pergunta))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().isCreated());
    }

    @Test
    public void EditarPerguntaRetornaOk() throws Exception {
        ResultActions ra = mockMvc.perform(put("/perguntas/{id}",1L)
                .content(objectMapper.writeValueAsString(pergunta))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().isOk());
    }

    @Test
    public void CriarPerguntaRetorna400QuandoMaisDeUmaRespostaECorreta() throws Exception {

        Resposta resp1 = new Resposta();
        Resposta resp2 = new Resposta();
        Resposta resp3 = new Resposta();
        Resposta resp4 = new Resposta();
        resp1.setId(1L);
        resp2.setId(2L);
        resp3.setId(3L);
        resp4.setId(4L);
        resp1.setCorreta(true);
        resp2.setCorreta(true);
        resp3.setCorreta(false);
        resp4.setCorreta(false);
        Set<Resposta> respostasPergunta = new LinkedHashSet<>();
        respostasPergunta.add(resp1);
        respostasPergunta.add(resp2);
        respostasPergunta.add(resp3);
        respostasPergunta.add(resp4);
        pergunta.setRespostas(respostasPergunta);

        ResultActions ra = mockMvc.perform(post("/perguntas")
                .content(objectMapper.writeValueAsString(pergunta))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().is4xxClientError());
    }

    @Test
    public void DeletarPerguntaRetornaOk() throws Exception {
        mockMvc.perform(delete("/perguntas/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void CriarPerguntaRetorna4XXQuandoTemMenosDe4Respostas() throws Exception {

        Resposta resp1 = new Resposta();
        Resposta resp2 = new Resposta();
        Resposta resp3 = new Resposta();
        resp1.setCorreta(true);
        resp2.setCorreta(false);
        resp3.setCorreta(false);
        Set<Resposta> respostasPergunta = new LinkedHashSet<>();
        respostasPergunta.add(resp1);
        respostasPergunta.add(resp2);
        respostasPergunta.add(resp3);

        pergunta.setRespostas(respostasPergunta);
        ResultActions ra = mockMvc.perform(post("/perguntas")
                .content(objectMapper.writeValueAsString(pergunta))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().is4xxClientError());
    }


    @Test
    public void CriarPerguntaRetorna4XXQuandoTemMaisDe5Respostas() throws Exception {

        Resposta resp1 = new Resposta();
        Resposta resp2 = new Resposta();
        Resposta resp3 = new Resposta();
        Resposta resp4 = new Resposta();
        Resposta resp5 = new Resposta();
        Resposta resp6 = new Resposta();
        resp1.setCorreta(true);
        resp2.setCorreta(false);
        resp3.setCorreta(false);
        resp4.setCorreta(false);
        resp5.setCorreta(false);
        resp6.setCorreta(false);
        Set<Resposta> respostasPergunta = new LinkedHashSet<>();
        respostasPergunta.add(resp1);
        respostasPergunta.add(resp2);
        respostasPergunta.add(resp3);
        respostasPergunta.add(resp4);
        respostasPergunta.add(resp5);
        respostasPergunta.add(resp6);
        pergunta.setRespostas(respostasPergunta);

        ResultActions ra = mockMvc.perform(post("/perguntas")
                .content(objectMapper.writeValueAsString(pergunta))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().is4xxClientError());
    }
}
