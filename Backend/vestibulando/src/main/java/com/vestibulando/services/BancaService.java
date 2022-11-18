package com.vestibulando.services;

import com.vestibulando.entities.Banca;
import com.vestibulando.excepitions.DeleteComAssociacoes;
import com.vestibulando.repositories.IBancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BancaService {

    @Autowired
    IBancaRepository bancaRepository;

    public List<Banca> listar() {
        return bancaRepository.findAll();
    }

    public Banca obter(long id) {
        if (bancaRepository.findById(id).isPresent()) {
            return bancaRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("Banca não encontrada");
        }
    }

    public void deletar(long id) {
        Banca banca = this.obter(id);
        try {
            bancaRepository.delete(banca);
        }
        catch (Exception e){
            throw new DeleteComAssociacoes("Não é possível deletar a banca pois há itens associados com ela");
        }
    }

    @Transactional
    public Banca salvar(Banca banca) {
        return bancaRepository.save(banca);
    }

    public Banca atualizar(Long id, Banca newBanca) {
        Banca oldBanca = this.obter(id);

        oldBanca.setNome(newBanca.getNome());
        oldBanca.setSigla(newBanca.getSigla());
        oldBanca.setSimulado(newBanca.getSimulado());

        return this.salvar(oldBanca);
    }

}
