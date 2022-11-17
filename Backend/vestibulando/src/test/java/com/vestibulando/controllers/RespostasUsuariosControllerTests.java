package com.vestibulando.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.entities.Resposta;
import com.vestibulando.entities.RespostasUsuarios;
import com.vestibulando.entities.Simulado;
import com.vestibulando.entities.Usuario;
import com.vestibulando.services.RespostasUsuariosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class RespostasUsuariosControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RespostasUsuariosService respostasUsuariosService;

    RespostasUsuarios respUsuario;

    @BeforeEach
    public void beforeEach(){
        respUsuario = new RespostasUsuarios();

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
        List<Resposta> respostasMarcadas = new ArrayList<>();
        respUsuario.setRespostas(respostasMarcadas);

        Simulado simulado = new Simulado();
        Usuario usuario = new Usuario();
        respUsuario.setUsuario(usuario);
        respUsuario.setSimulado(simulado);
    }

    @Test
    public void ListarPaginadoRetornaOk() throws Exception {
        mockMvc.perform(get("/respostasUsuarios")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    public void ListarPorSimuladoRetornaOk() throws Exception {
        mockMvc.perform(get("/respostasUsuarios/simulados/{idsimulado}",1L)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    public void ObterPorIdRetornaOk() throws Exception {
        mockMvc.perform(get("/respostasUsuarios/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    public void DeletarRetornaOk() throws Exception {
        mockMvc.perform(delete("/respostasUsuarios/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    public void CriarRetornaCreated() throws Exception  {



        ResultActions ra = mockMvc.perform(post("/respostasUsuarios")
                .content(objectMapper.writeValueAsString(respUsuario))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().isCreated());
    }


    @Test
    public void AtualizarRetornaOk() throws Exception {


        ResultActions ra = mockMvc.perform(put("/respostasUsuarios/{id}",1L)
                .content(objectMapper.writeValueAsString(respUsuario))
                .contentType(MediaType.APPLICATION_JSON));

        ra.andExpect(status().isOk());
    }


}
