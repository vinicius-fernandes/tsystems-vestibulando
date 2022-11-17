package com.vestibulando.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.vestibulando.entities.Usuario;

import com.vestibulando.services.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
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
public class UsuarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;


    @Autowired
    ObjectMapper objectMapper;

    private String usuarioString;
    private String usuario2String;

    @BeforeEach
    public void beforeEach() throws JsonProcessingException {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("123456");
        usuario.setId(1l);
        usuarioString = objectMapper.writeValueAsString(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Joana");
        usuario2.setEmail("");
        usuario2.setSenha("");
        usuario2.setId(2l);
        usuario2String = objectMapper.writeValueAsString(usuario2);

    }

    @Test
    public void RetornaCreatedQuandoUsuarioCriado() throws Exception {
        ResultActions result = mockMvc.perform(post("/usuarios")
                .content(usuarioString)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());

    }

    @Test
    public void ListarUsuarioRetornaOk() throws Exception {
        mockMvc.perform(get("/usuarios")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void ObterUsuarioPorIdRetornaOk()throws Exception {
        mockMvc.perform(get("/usuarios/{idUsuario}", 1l)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void AlterarUsuarioRetornaOk()throws Exception {
        ResultActions result = mockMvc.perform(put("/usuarios/{idUsuario}", 1l)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(usuarioString)
                        .contentType(MediaType.APPLICATION_JSON));

                result.andExpect(status().isOk());
    }

    @Test
    public void DeletarUsuarioRetornaNada() throws Exception{
        mockMvc.perform(delete("/usuarios/{idUsuario}", 1l)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void Retorna400QuandoCamposObrigatoriosNaoAtendemParamentros() throws Exception {
        ResultActions result = mockMvc.perform(post("/usuarios")
                .content(usuario2String)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().is4xxClientError());
    }

}
