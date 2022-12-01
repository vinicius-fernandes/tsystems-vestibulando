package com.vestibulando.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestibulando.entities.Resposta;
import com.vestibulando.services.RespostaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = { "ADMIN"})

public class RespostaControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RespostaService respostaService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void PostarRespostaRetornaCreated() throws Exception {
        Resposta resposta = new Resposta();
        resposta.setDescricao("Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit");
        resposta.setCorreta(false);

        String respostaString = objectMapper.writeValueAsString(resposta);

        ResultActions result = mockMvc.perform(post("/respostas")
                .content(respostaString)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
    }

    @Test
    public void ConsultarRespostasAdminRetornaOK() throws Exception {
        ResultActions result = mockMvc.perform(get("/respostas/admin")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void ConsultarRespostasAdminPaginadoRetornaOK() throws Exception {
        ResultActions result = mockMvc.perform(get("/respostas/adminPaginado")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void ConsultarRespostasAdminByIdRetornaOK() throws Exception {
        ResultActions result = mockMvc.perform(get("/respostas/admin/{idresposta}", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void ConsultarRespostasRetornaOK() throws Exception {
        ResultActions result = mockMvc.perform(get("/respostas")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void ConsultarRespostasPaginadoRetornaOK() throws Exception {
        ResultActions result = mockMvc.perform(get("/respostas/paginado")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void ConsultarRespostasByIdRetornaOK() throws Exception {
        ResultActions result = mockMvc.perform(get("/respostas/{idresposta}", 1)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void AlterarRespostasRetornaOK() throws Exception {
        Resposta resposta = new Resposta();
        resposta.setDescricao("Sed commodo porttitor mi, auctor lacinia velit interdum hendrerit");
        resposta.setCorreta(false);

        String respostaString = objectMapper.writeValueAsString(resposta);

        ResultActions result = mockMvc.perform(put("/respostas/{idresposta}", 1)
                .content(respostaString)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void ExcluirRespostaRetornaOK() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/respostas/{idresposta}",1L)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }


}
