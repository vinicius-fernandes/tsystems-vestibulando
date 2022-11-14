package com.vestibulando.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.services.RespostaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RespostaControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RespostaService respostaService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void retornaOKAoConsultarCategorias() throws Exception {
        ResultActions result = mockMvc.perform(get("/respostas")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }


}
