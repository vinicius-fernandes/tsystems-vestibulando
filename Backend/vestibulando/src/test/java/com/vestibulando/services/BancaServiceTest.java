package com.vestibulando.services;

import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import com.vestibulando.repositories.IBancaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class BancaServiceTest {

    @InjectMocks
    BancaService bancaService;

    @Mock
    IBancaRepository bancaRepository;

    Banca banca;

    long idExistente = 1L;

    long idInexistente = 4L;

    @BeforeEach
    public void beforeEach(){
        banca = new Banca();
        Mockito.when(bancaRepository.save(Mockito.any(Banca.class))).thenReturn(banca);

        Mockito.when(bancaRepository.findAll()).thenReturn(new ArrayList<Banca>());

        Mockito.doNothing().when(bancaRepository).delete(Mockito.any(Banca.class));

        Mockito.when(bancaRepository.findById(idExistente)).thenReturn(Optional.of(banca));

        Mockito.when(bancaRepository.findById(idInexistente)).thenReturn(Optional.empty());
    }

    @Test
    public void RetornarMateriaAposCriar(){

        Assertions.assertNotNull(bancaService.salvar(banca));
    }

    @Test
    public void RetornarMateriaAposAtualizar(){

        Assertions.assertNotNull(bancaService.atualizar(1L, banca));
    }

    @Test
    public void ObterItemExistenteRetornaOItem(){

        Assertions.assertNotNull(bancaService.obter(idExistente));
    }

    @Test
    public void ObterItemNaoExistenteLancaExecao(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> bancaService.obter(idInexistente));
    }
}
