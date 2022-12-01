package com.vestibulando.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.entities.*;
import com.vestibulando.repositories.IRespostasUsuariosRepository;
import com.vestibulando.repositories.ISimuladoRepository;
import com.vestibulando.services.SimuladoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = { "ADMIN"})

public class SimuladoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SimuladoService service;

    @MockBean
    private IRespostasUsuariosRepository  respUserRepo;
    Simulado simulado;
    Simulado simuladoAdicionaPergunta;
    Simulado simuladoAdicionaBanca;
    Simulado simuladoAdicionaMateria;

    Materia materia;
    Materia materia2;

    Pergunta pergunta;
    Pergunta pergunta2;

    Banca banca;
    Banca banca2;

    long idExistente = 1L;

    long idExistente2 = 2L;

    long idExistente3 = 3L;

    long idExistente4 = 4L;

    long idInexistente = -1L;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        simulado = new Simulado();
        simulado.setId(idExistente);
        simulado.setPerguntas(new HashSet<>());
        simulado.setMaterias(new HashSet<>());
        simulado.setBancas(new HashSet<>());

        simuladoAdicionaBanca = new Simulado();
        simuladoAdicionaBanca.setId(idExistente2);
        simuladoAdicionaBanca.setPerguntas(new HashSet<>());
        simuladoAdicionaBanca.setMaterias(new HashSet<>());
        simuladoAdicionaBanca.setBancas(new HashSet<>());

        simuladoAdicionaMateria = new Simulado();
        simuladoAdicionaMateria.setId(idExistente3);
        simuladoAdicionaMateria.setPerguntas(new HashSet<>());
        simuladoAdicionaMateria.setMaterias(new HashSet<>());
        simuladoAdicionaMateria.setBancas(new HashSet<>());

        simuladoAdicionaPergunta = new Simulado();
        simuladoAdicionaPergunta.setId(idExistente4);
        simuladoAdicionaPergunta.setPerguntas(new HashSet<>());
        simuladoAdicionaPergunta.setMaterias(new HashSet<>());
        simuladoAdicionaPergunta.setBancas(new HashSet<>());

        pergunta = new Pergunta();
        pergunta.setId(idExistente);
        simulado.getPerguntas().add(pergunta);
        simuladoAdicionaBanca.getPerguntas().add(pergunta);
        simuladoAdicionaMateria.getPerguntas().add(pergunta);
        simuladoAdicionaPergunta.getPerguntas().add(pergunta);

        materia = new Materia();
        materia.setId(idExistente);
        simulado.getMaterias().add(materia);
        simuladoAdicionaBanca.getMaterias().add(materia);
        simuladoAdicionaMateria.getMaterias().add(materia);
        simuladoAdicionaPergunta.getMaterias().add(materia);

        banca = new Banca();
        banca.setId(idExistente);
        simulado.getBancas().add(banca);
        simuladoAdicionaBanca.getBancas().add(banca);
        simuladoAdicionaMateria.getBancas().add(banca);
        simuladoAdicionaPergunta.getBancas().add(banca);

        banca2 = new Banca();
        banca2.setId(idExistente2);
        simuladoAdicionaBanca.getBancas().add(banca2);

        materia2 = new Materia();
        materia2.setId(idExistente2);
        simuladoAdicionaMateria.getMaterias().add(materia2);

        pergunta2 = new Pergunta();
        pergunta2.setId(idExistente2);
        simuladoAdicionaPergunta.getPerguntas().add(pergunta2);

        Mockito.when(service.consultar(idExistente)).thenReturn(simulado);

        Mockito.when(service.consultar(idInexistente)).thenThrow(new EntityNotFoundException("Simulado não encontrado."));

        Mockito.when(service.consultarPerguntas(idExistente)).thenReturn(simulado.getPerguntas());

        Mockito.when(service.consultarBancas(idExistente)).thenReturn(simulado.getBancas());

        Mockito.when(service.consultarMaterias(idExistente)).thenReturn(simulado.getMaterias());

        Mockito.when(service.salvar(Mockito.any())).thenReturn(simulado);

        Mockito.when(service.adicionarBanca(Mockito.eq(idExistente), Mockito.any())).thenReturn(simuladoAdicionaBanca);

        Mockito.when(service.adicionarPergunta(Mockito.eq(idExistente), Mockito.any())).thenReturn(simuladoAdicionaPergunta);

        Mockito.when(service.adicionarMateria(Mockito.eq(idExistente), Mockito.any())).thenReturn(simuladoAdicionaMateria);

        Mockito.when(service.deletarSimulado(idExistente)).thenReturn("Simulado deletado com sucesso.");

        Mockito.when(service.deletarBanca(idExistente2, idExistente)).thenReturn(simulado);

        Mockito.when(service.deletarMateria(idExistente3, idExistente)).thenReturn(simulado);

        Mockito.when(service.deletarPergunta(idExistente4, idExistente)).thenReturn(simulado);

        Mockito.when(service.alterar(Mockito.eq(idExistente), Mockito.any())).thenReturn(simulado);

        Mockito.when(service.alterarPergunta(Mockito.eq(idExistente), Mockito.eq(pergunta.getId()), Mockito.any())).thenReturn(simulado);

        Mockito.when(service.alterarBanca(Mockito.eq(idExistente), Mockito.eq(banca.getId()), Mockito.any())).thenReturn(simulado);

        Mockito.when(service.alterarMateria(Mockito.eq(idExistente), Mockito.eq(materia.getId()), Mockito.any())).thenReturn(simulado);

        Mockito.when(respUserRepo.findBySimulado(Mockito.any(Simulado.class))).thenReturn(new ArrayList<RespostasUsuarios>());
    }

    @Test
    public void retornaOkAoConsultarSimulados() throws Exception {
        mockMvc.perform(get("/simulados")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void retornaOkAoConsultarSimuladoExistente() throws Exception {
        mockMvc.perform(get("/simulados/{idSimulado}", idExistente)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaOkAoConsultarPerguntasDoSimulado() throws Exception {
        mockMvc.perform(get("/simulados/{idSimulado}/perguntas", idExistente)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(pergunta.getId()));
    }

    @Test
    public void retornaOkAoConsultarBancasDoSimulado() throws Exception {
        mockMvc.perform(get("/simulados/{idSimulado}/bancas", idExistente)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(banca.getId()));
    }

    @Test
    public void retornaOkAoConsultarMateriasDoSimulado() throws Exception {
        mockMvc.perform(get("/simulados/{idSimulado}/materias", idExistente)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(materia.getId()));
    }

    @Test
    public void retornaOkAoInserirSimulado() throws Exception {
        mockMvc.perform(post("/simulados")
                .content(objectMapper.writeValueAsString(simulado))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaOkAoInserirBancaNoSimulado() throws Exception {
        mockMvc.perform(put("/simulados/{idSimulado}/bancas", idExistente)
                .content(objectMapper.writeValueAsString(banca2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(2)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaOkAoInserirPerguntaNoSimulado() throws Exception {
        mockMvc.perform(put("/simulados/{idSimulado}/perguntas", idExistente)
                .content(objectMapper.writeValueAsString(pergunta2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(2)));
    }

    @Test
    public void retornaOkAoInserirMateriaNoSimulado() throws Exception {
        mockMvc.perform(put("/simulados/{idSimulado}/materias", idExistente)
                .content(objectMapper.writeValueAsString(materia2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(2)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaStringAoDeletarSimulado() throws Exception {
        mockMvc.perform(delete("/simulados/{idSimulado}", idExistente)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void retornaSimuladoAoDeletarPerguntaDoSimulado() throws Exception {
        mockMvc.perform(delete("/simulados/{idSimulado}/perguntas/{idPergunta}", idExistente4, idExistente)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaSimuladoAoDeletarBancaDoSimulado() throws Exception {
        mockMvc.perform(delete("/simulados/{idSimulado}/bancas/{idBanca}", idExistente2, idExistente)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaSimuladoAoDeletarMateriaDoSimulado() throws Exception {
        mockMvc.perform(delete("/simulados/{idSimulado}/materias/{idMateria}", idExistente3, idExistente)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaSimuladoAoAlterarSimulado() throws Exception {
        mockMvc.perform(put("/simulados/{idSimulado}", idExistente)
                .content(objectMapper.writeValueAsString(simulado))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaSimuladoAoAlterarPerguntaDoSimulado() throws Exception {
        mockMvc.perform(put("/simulados/{idSimulado}/perguntas/{idPerguntas}", idExistente, pergunta.getId())
                .content(objectMapper.writeValueAsString(pergunta))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.perguntas[0].id").value(pergunta.getId()))
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaSimuladoAoAlterarBancaDoSimulado() throws Exception {
        mockMvc.perform(put("/simulados/{idSimulado}/bancas/{idBanca}", idExistente, banca.getId())
                .content(objectMapper.writeValueAsString(banca))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bancas[0].id").value(banca.getId()))
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }

    @Test
    public void retornaSimuladoAoAlterarMateriaDoSimulado() throws Exception {
        mockMvc.perform(put("/simulados/{idSimulado}/materias/{idMateria}", idExistente, materia.getId())
                .content(objectMapper.writeValueAsString(materia))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.materias[0].id").value(materia.getId()))
                .andExpect(jsonPath("$.bancas", hasSize(1)))
                .andExpect(jsonPath("$.materias", hasSize(1)))
                .andExpect(jsonPath("$.perguntas", hasSize(1)));
    }
}
