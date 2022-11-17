package com.vestibulando.services;

import com.vestibulando.entities.Materia;
import com.vestibulando.repositories.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MateriaService {

    @Autowired
    IMateriaRepository materiaRepository;

    public List<Materia> listar() {
        return materiaRepository.findAll();
    }

    public Materia obter(long id) {
        if (materiaRepository.findById(id).isPresent()) {
            return materiaRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("A matéria não foi encontrada");
        }
    }

    public void deletar(long id) {
        Materia materia = this.obter(id);
        materiaRepository.delete(materia);
    }

    public Materia salvar(Materia materia){
        return materiaRepository.save(materia);
    }

    public Materia atualizar(Long id, Materia newMateria ){
        Materia oldMateria = this.obter(id);

        oldMateria.setNome(newMateria.getNome());

        return this.salvar(oldMateria);
    }
}
