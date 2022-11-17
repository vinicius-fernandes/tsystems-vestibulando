package com.vestibulando.services;

import com.vestibulando.dtos.NotaSimuladoUsuarioDTO;
import com.vestibulando.dtos.RankingSimuladoDTO;
import com.vestibulando.entities.RespostasUsuarios;
import com.vestibulando.entities.Simulado;
import com.vestibulando.repositories.IRespostasUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RespostasUsuariosService {
    @Autowired
    IRespostasUsuariosRepository respostasUsuariosRepository;

    public Page<RespostasUsuarios> listar(Pageable page){
        Page<RespostasUsuarios> pagina = respostasUsuariosRepository.findAll(page);
        return pagina;
    }
    public List<RespostasUsuarios> listar(long idSimulado){

        Simulado simulado = new Simulado();
        simulado.setId(idSimulado);
       List<RespostasUsuarios> resps = respostasUsuariosRepository.findBySimulado(simulado);
        return resps;
    }

    public RespostasUsuarios salvar(RespostasUsuarios respostaUsuario){
        return respostasUsuariosRepository.save(respostaUsuario);
    }



    public RespostasUsuarios obter(long id){
        RespostasUsuarios resp = respostasUsuariosRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Não foi possível encontrar as respostas do usuários com esse id de respostas usuários"));
        return resp;
    }


    public RespostasUsuarios editar(long id,RespostasUsuarios respostasUsuarios){
        RespostasUsuarios respUsuario = this.obter(id);
        respUsuario.setRespostas(respostasUsuarios.getRespostas());
        return this.salvar(respUsuario);

    }

    public List<RankingSimuladoDTO> getRankingSimulado(Long idSimulado){
        return respostasUsuariosRepository.getRankingSimulado(idSimulado) ;
    }

    public NotaSimuladoUsuarioDTO getNotaSimuladoUsuario(long idUsuario,long idSimulado){
        return respostasUsuariosRepository.getNotaSimuladoUsuario(idSimulado,idUsuario).orElseThrow(()-> new EntityNotFoundException("Não foi possível encontrar respostas para o usuário e simulados especificados!"));
    }

    public List<NotaSimuladoUsuarioDTO> getNotasSimuladosUsuario(long idUsuario){
        return respostasUsuariosRepository.getNotasSimuladosUsuario(idUsuario);
    }


    public void deletar(long id){
        RespostasUsuarios respUsuarios = this.obter(id);
        respostasUsuariosRepository.delete(respUsuarios);
    }
}
