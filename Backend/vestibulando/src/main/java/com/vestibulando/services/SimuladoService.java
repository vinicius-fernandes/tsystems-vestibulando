package com.vestibulando.services;

import com.vestibulando.entities.Simulado;
import com.vestibulando.repositories.ISimuladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SimuladoService {

    @Autowired
    ISimuladoRepository ISimuladoRepository;

    public List<Simulado> consultar() {
        return ISimuladoRepository.findAll();
    }

    public Simulado consultar(Long id) {
        Optional<Simulado> s = ISimuladoRepository.findById(id);

        return s.orElseThrow(()-> new EntityNotFoundException("Simulado não encontrado."));
    }

    @Transactional
    public Simulado salvar(Simulado s) {

        return ISimuladoRepository.save(s);
    }
}
