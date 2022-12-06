package com.vestibulando.services;

import com.vestibulando.entities.*;
import com.vestibulando.repositories.IRespostasUsuariosRepository;
import com.vestibulando.repositories.ISimuladoRepository;
import com.vestibulando.services.SimuladoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class SimuladoServiceTest {

    @InjectMocks
    private SimuladoService service;
    @Mock
    private ISimuladoRepository repository;
    Simulado simulado;
    Banca banca;
    Materia materia;
    Pergunta pergunta;

    long idExistente = 1L;
    long idInexistente = 2L;

    @Mock
    Page<Simulado> simuladoPage;

    @Mock
    private IRespostasUsuariosRepository respUserRepo;

    Pageable pageRequest = PageRequest.of(0, 1);

    @BeforeEach
    public void beforeEach() {
        simulado = new Simulado();
        simulado.setBancas(new HashSet<>());
        simulado.setMaterias(new HashSet<>());
        simulado.setPerguntas(new HashSet<>());

        banca = new Banca();
        banca.setId(idExistente);

        pergunta = new Pergunta();
        pergunta.setId(idExistente);

        materia = new Materia();
        materia.setId(idExistente);

        Mockito.when(repository.save(Mockito.any(Simulado.class))).thenReturn(simulado);

        Mockito.doNothing().when(repository).deleteById(idExistente);

        Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(simulado));

        Mockito.when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        Mockito.when(repository.findAll(pageRequest)).thenReturn(simuladoPage);
        Mockito.when(respUserRepo.findBySimulado(Mockito.any(Simulado.class))).thenReturn(new ArrayList<RespostasUsuarios>());

    }

    @Test
    public void retornaSimuladoAoSalvarSimulado() {
        Assertions.assertNotNull(service.salvar(simulado));
        Mockito.verify(repository, Mockito.times(1)).save(simulado);
    }

    @Test
    public void retornaSimuladoAoAlterarSimuladoExistente() {
        Assertions.assertNotNull(service.alterar(idExistente, simulado));
    }

    @Test
    public void lancaExcecaoAoAlterarSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.alterar(idInexistente, simulado));
    }

    @Test
    public void retornaSimuladoAoConsultarSimuladoExistente() {
        Assertions.assertNotNull(service.consultar(idExistente));
    }

    @Test
    public void lancaExcecaoAoConsultarSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.consultar(idInexistente));
    }

    @Test
    public void retornaListaAoConsultarSimulados() {
        Assertions.assertNotNull(service.consultar());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void retornaStringAoDeletarSimuladoExistente() {
        Assertions.assertDoesNotThrow(() -> service.deletarSimulado(idExistente));
        Assertions.assertEquals("Simulado excluido com sucesso!", service.deletarSimulado(idExistente));
        Mockito.verify(repository, Mockito.times(2)).deleteById(idExistente);
    }

    @Test
    public void lancaExcecaoAoDeletarSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.deletarSimulado(idInexistente));
    }

    @Test
    public void retornaSetAoConsultarPerguntasDoSimuladoExistente() {
        Assertions.assertNotNull(service.consultarPerguntas(idExistente));
    }

    @Test
    public void lancaExcecaoAoConsultarPerguntasDoSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.consultarPerguntas(idInexistente));
    }

    @Test
    public void retornaSetAoConsultarBancasDoSimuladoExistente() {
        Assertions.assertNotNull(service.consultarBancas(idExistente));
    }

    @Test
    public void lancaExcecaoAoConsultarBancasDoSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.consultarBancas(idInexistente));
    }

    @Test
    public void retornaSetAoConsultarMateriasDoSimuladoExistente() {
        Assertions.assertNotNull(service.consultarMaterias(idExistente));
    }

    @Test
    public void lancaExcecaoAoConsultarMateriasDoSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.consultarMaterias(idInexistente));
    }

    @Test
    public void retornaSimuladoAoAdicionarBancaAoSimuladoExistente() {
        Assertions.assertNotNull(service.adicionarBanca(idExistente, banca));
    }

    @Test
    public void lancaExcecaoAoAdicionarBancaAoSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.adicionarBanca(idInexistente, banca));
    }

    @Test
    public void retornaSimuladoAoAdicionarMateriaAoSimuladoExistente() {
        Assertions.assertNotNull(service.adicionarMateria(idExistente, materia));
    }

    @Test
    public void lancaExcecaoAoAdicionarMateriaAoSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.adicionarMateria(idInexistente, materia));
    }

    @Test
    public void retornaSimuladoAoAdicionarPerguntaAoSimuladoExistente() {
        Assertions.assertNotNull(service.adicionarPergunta(idExistente, pergunta));
    }

    @Test
    public void lancaExcecaoAoAdicionarPerguntaAoSimuladoInexistente() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.adicionarPergunta(idInexistente, pergunta));
    }

    @Test
    public void retornaSimuladoAoDeletarPerguntaExistenteDoSimuladoExistente() {
        simulado.getPerguntas().add(pergunta);
        Assertions.assertNotNull(service.deletarPergunta(idExistente, idExistente));
    }

    @Test
    public void lancaExcecaoAoDeletarPerguntaInexistenteDoSimuladoExistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.deletarPergunta(idExistente, idInexistente));
        Assertions.assertEquals("Pergunta não encontrada ou não cadastrada no simulado.", expectedException.getMessage());
    }

    @Test
    public void lancaExcecaoAoDeletarPerguntaDoSimuladoInexistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.deletarPergunta(idInexistente, idExistente));
        Assertions.assertEquals("Simulado não encontrado.", expectedException.getMessage());
    }

    @Test
    public void retornaSimuladoAoDeletarBancaExistenteDoSimuladoExistente() {
        simulado.getBancas().add(banca);
        Assertions.assertNotNull(service.deletarBanca(idExistente, idExistente));
    }

    @Test
    public void lancaExcecaoAoDeletarBancaInexistenteDoSimuladoExistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.deletarBanca(idExistente, idInexistente));
        Assertions.assertEquals("Banca não encontrada ou não cadastrada no simulado.", expectedException.getMessage());
    }

    @Test
    public void lancaExcecaoAoDeletarBancaDoSimuladoInexistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.deletarBanca(idInexistente, idExistente));
        Assertions.assertEquals("Simulado não encontrado.", expectedException.getMessage());
    }

    @Test
    public void retornaSimuladoAoDeletarMateriaExistenteDoSimuladoExistente() {
        simulado.getMaterias().add(materia);
        Assertions.assertNotNull(service.deletarMateria(idExistente, idExistente));
    }

    @Test
    public void lancaExcecaoAoDeletarMateriaInexistenteDoSimuladoExistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.deletarMateria(idExistente, idInexistente));
        Assertions.assertEquals("Matéria não encontrada ou não cadastrada no simulado.", expectedException.getMessage());
    }

    @Test
    public void lancaExcecaoAoDeletarMateriaDoSimuladoInexistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.deletarMateria(idInexistente, idExistente));
        Assertions.assertEquals("Simulado não encontrado.", expectedException.getMessage());
    }

    @Test
    public void retornaSimuladoAoAlterarPerguntaExistenteDoSimuladoExistente() {
        simulado.getPerguntas().add(pergunta);
        Assertions.assertNotNull(service.alterarPergunta(idExistente, idExistente, pergunta));
    }

    @Test
    public void lancaExcecaoAoAlterarPerguntaInexistenteDoSimuladoExistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.alterarPergunta(idExistente, idInexistente, pergunta));
        Assertions.assertEquals("Pergunta não encontrada ou não cadastrada no simulado.", expectedException.getMessage());
    }

    @Test
    public void lancaExcecaoAoAlterarPerguntaDoSimuladoInexistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.alterarPergunta(idInexistente, idExistente, pergunta));
        Assertions.assertEquals("Simulado não encontrado.", expectedException.getMessage());
    }

    @Test
    public void retornaSimuladoAoAlterarBancaExistenteDoSimuladoExistente() {
        simulado.getBancas().add(banca);
        Assertions.assertNotNull(service.alterarBanca(idExistente, idExistente, banca));
    }

    @Test
    public void lancaExcecaoAoAlterarBancaInexistenteDoSimuladoExistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.alterarBanca(idExistente, idInexistente, banca));
        Assertions.assertEquals("Banca não encontrada ou não cadastrada no simulado.", expectedException.getMessage());
    }

    @Test
    public void lancaExcecaoAoAlterarBancaDoSimuladoInexistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.alterarBanca(idInexistente, idExistente, banca));
        Assertions.assertEquals("Simulado não encontrado.", expectedException.getMessage());
    }

    @Test
    public void retornaSimuladoAoAlterarMateriaExistenteDoSimuladoExistente() {
        simulado.getMaterias().add(materia);
        Assertions.assertNotNull(service.alterarMateria(idExistente, idExistente, materia));
    }

    @Test
    public void lancaExcecaoAoAlterarMateriaInexistenteDoSimuladoExistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.alterarMateria(idExistente, idInexistente, materia));
        Assertions.assertEquals("Matéria não encontrada ou não cadastrada no simulado.", expectedException.getMessage());
    }

    @Test
    public void lancaExcecaoAoAlterarMateriaDoSimuladoInexistente() {
        EntityNotFoundException expectedException = Assertions.assertThrows(EntityNotFoundException.class, () -> service.alterarMateria(idInexistente, idExistente, materia));
        Assertions.assertEquals("Simulado não encontrado.", expectedException.getMessage());
    }

    @Test
    public void retornaPageableAoConsultarSimuladosPaginado() {
        Assertions.assertNotNull(service.consultarPaginada(pageRequest));
    }
}
