package com.vestibulando.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.entities.Materia;
import com.vestibulando.repositories.IMateriaRepository;
import com.vestibulando.services.MateriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN"})

public class MateriasControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MateriaService materiaService;

    @MockBean
    IMateriaRepository materiaRepository;
    @Autowired
    ObjectMapper objectMapper;
    Materia materia = new Materia();

    @BeforeEach
    public void beforeEach(){
        materia.setNome("Teste materia criada");
        materia.setId(1L);
        Mockito.doNothing().when(materiaRepository).delete(Mockito.any(Materia.class));

        Mockito.when(materiaRepository.findAllByOrderByIdAsc()).thenReturn(new ArrayList<>());

        Mockito.when(materiaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(materia));
    }

    @Test
    public void ListarMateriasRetornaOk() throws Exception{
        mockMvc.perform(get("/materia")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void ObterMateriaRetornaOk()throws Exception{
        mockMvc.perform(get("/materia/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void CriarMateriaRetornaCreated()throws Exception{

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
        mockMvc.perform(delete("/materia/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
