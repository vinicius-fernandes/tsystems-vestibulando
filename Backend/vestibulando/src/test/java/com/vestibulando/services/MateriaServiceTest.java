package com.vestibulando.services;

import com.vestibulando.entities.Materia;
import com.vestibulando.repositories.IMateriaRepository;
import com.vestibulando.services.MateriaService;
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
public class MateriaServiceTest {

    @InjectMocks
    MateriaService materiaService;

    @Mock
    IMateriaRepository materiaRepository;

    Materia materia;

    long idExistente = 1L;

    long idInexistente = 6L;

    @BeforeEach
    public void beforeEach(){
        materia = new Materia();
        Mockito.when(materiaRepository.save(Mockito.any(Materia.class))).thenReturn(materia);

        Mockito.when(materiaRepository.findAll()).thenReturn(new ArrayList<Materia>());

        Mockito.doNothing().when(materiaRepository).delete(Mockito.any(Materia.class));

        Mockito.when(materiaRepository.findById(idExistente)).thenReturn(Optional.of(materia));

        Mockito.when(materiaRepository.findById(idInexistente)).thenReturn(Optional.empty());
    }

    @Test
    public void RetornarMateriaAposCriar(){
        Assertions.assertNotNull(materiaService.salvar(materia));
    }

    @Test
    public void RetornarMateriaAposAtualizar(){
        Assertions.assertNotNull(materiaService.atualizar(1L, materia));
    }

    @Test
    public void ObterItemExistenteRetornaOItem(){
        Assertions.assertNotNull(materiaService.obter(idExistente));
    }

    @Test
    public void ObterItemNaoExistenteLancaExecao(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> materiaService.obter(idInexistente));
    }
}
