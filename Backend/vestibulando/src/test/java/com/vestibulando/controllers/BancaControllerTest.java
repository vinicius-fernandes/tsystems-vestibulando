package com.vestibulando.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.entities.Banca;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BancaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void ListarBancasRetornaOk() throws Exception {
        mockMvc.perform(get("/banca")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

    @Test
    public void ObterBancaRetornarOK() throws Exception {
        mockMvc.perform(get("/banca/1")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

    @Test
    public void criarBancaRetornaCreated() throws Exception {
        Banca banca = new Banca();

        banca.setId(5L);
        banca.setNome("Universidade Federal de Santa Catarina");
        banca.setSigla("UFSC");

        ResultActions ra = mockMvc.perform(post("/banca")
                .content(objectMapper.writeValueAsString(banca))
                .contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(status().isCreated());
    }

    @Test
    public void editarBancaRetornaOk() throws Exception {
        Banca banca = new Banca();

        banca.setId(1L);
        banca.setNome("Fundação Universidade Regional de Blumenau");
        banca.setSigla("FURB");

        ResultActions ra = mockMvc.perform(put("/banca/1")
                .content(objectMapper.writeValueAsString(banca))
                .contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(status().isOk());
    }

    @Test
    public void deletarBancaRetornaOk() throws Exception {
        mockMvc.perform(delete("/banca/{id}", 4L)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }
    
}
