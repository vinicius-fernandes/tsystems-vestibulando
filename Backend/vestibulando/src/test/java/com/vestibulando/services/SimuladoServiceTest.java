package com.vestibulando.services;

import com.vestibulando.entities.Simulado;
import com.vestibulando.repositories.ISimuladoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class SimuladoServiceTest {

    @InjectMocks
    private SimuladoService service;

    @Mock
    private ISimuladoRepository repository;

    @Test
    public void retornaSimuladoAoSalvarSimulado() {
        Simulado simulado = new Simulado();

        Mockito.when(repository.save(simulado)).thenReturn(simulado);

        Assertions.assertNotNull(service.salvar(simulado));
        Mockito.verify(repository, Mockito.times(1)).save(simulado);
    }

    @Test
    public void retornaStringAoDeletarSimulado() {
        long idSimulado = 1L;

        Mockito.doNothing().when(repository).deleteById(idSimulado);
        Mockito.when(repository.findById(idSimulado)).thenReturn(Optional.of(new Simulado()));

        Assertions.assertDoesNotThrow(() -> service.deletarSimulado(idSimulado));
        Assertions.assertEquals("Simulado deletado com sucesso.", service.deletarSimulado(idSimulado));
        Mockito.verify(repository, Mockito.times(2)).deleteById(idSimulado);
    }
}
