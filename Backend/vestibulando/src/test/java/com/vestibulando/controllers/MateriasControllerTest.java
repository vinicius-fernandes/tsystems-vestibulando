package com.vestibulando.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.entities.Materia;
import com.vestibulando.services.MateriaService;
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
public class MateriasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void ListarMateriasRetornaOk() throws Exception{
        mockMvc.perform(get("/materia")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void ObterMateriaRetornaOk()throws Exception{
        mockMvc.perform(get("/materia/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void CriarMateriaRetornaCreated()throws Exception{
        Materia materia = new Materia();
        materia.setNome("Teste materia criada");

        ResultActions ra = mockMvc.perform(post("/materia")
                .content(objectMapper.writeValueAsString(materia))
                .contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(status().isCreated());
    }

    @Test
    public void EditarMateriaRetornaOk() throws Exception{
        Materia materia = new Materia();
        materia.setNome("Teste materia");

        ResultActions ra = mockMvc.perform(put("/materia/{id}", 1L)
                .content(objectMapper.writeValueAsString(materia))
                .contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(status().isOk());
    }

    @Test
    public void DeletarMateriaRetornaOk() throws Exception{
        mockMvc.perform(delete("/materia/{id}", 3L)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
