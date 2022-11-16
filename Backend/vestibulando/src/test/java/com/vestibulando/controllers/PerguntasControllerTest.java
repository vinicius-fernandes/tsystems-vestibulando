package com.vestibulando.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.services.PerguntaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
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

    @Test
    public void ListarPerguntasRetornaOk() throws Exception {
        mockMvc.perform(get("/perguntas")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

    @Test
    public void ObterPerguntaRetornaOk() throws Exception {
        mockMvc.perform(get("/perguntas/2")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }


}
