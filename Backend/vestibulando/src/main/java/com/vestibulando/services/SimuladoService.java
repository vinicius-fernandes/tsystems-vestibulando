package com.vestibulando.services;

import com.vestibulando.entities.Simulado;
import com.vestibulando.repositories.SimuladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SimuladoService {

    @Autowired
    SimuladoRepository simuladoRepository;

    public List<Simulado> consultar() {
        return simuladoRepository.findAll();
    }

    public Simulado consultar(Long id) {
        Optional<Simulado> s = simuladoRepository.findById(id);

        return s.orElseThrow(()-> new EntityNotFoundException("Simulado não encontrado."));
    }

    @Transactional
    public Simulado salvar(Simulado s) {

        return simuladoRepository.save(s);
    }
}
