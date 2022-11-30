package com.vestibulando.services;

import com.vestibulando.dtos.NotaSimuladoUsuarioDTO;
import com.vestibulando.dtos.RankingSimuladoDTO;
import com.vestibulando.entities.RespostasUsuarios;
import com.vestibulando.entities.Simulado;
import com.vestibulando.entities.Usuario;
import com.vestibulando.excepitions.DeleteComAssociacoes;
import com.vestibulando.repositories.IRespostasUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        Optional<RespostasUsuarios> respUser = respostasUsuariosRepository.getBySimuladoAndUsuario(respostaUsuario.getSimulado().getId(),respostaUsuario.getUsuario().getId());
        respUser.ifPresent(respostasUsuarios -> this.deletar(respostasUsuarios.getId()));


        return respostasUsuariosRepository.save(respostaUsuario);
    }


    public Page<RankingSimuladoDTO> getRankingGlobal(Pageable page){
        return respostasUsuariosRepository.getRankingGlobal(page);
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

        List<RankingSimuladoDTO> ranking = respostasUsuariosRepository.getRankingSimulado(idSimulado);

        return  ranking;
    }

    public NotaSimuladoUsuarioDTO getNotaSimuladoUsuario(long idUsuario,long idSimulado){

        Optional<NotaSimuladoUsuarioDTO> notaSimulado = respostasUsuariosRepository.getNotaSimuladoUsuario(idSimulado,idUsuario);
        if(notaSimulado.isEmpty()){
            Optional<RespostasUsuarios> respUser = respostasUsuariosRepository.getBySimuladoAndUsuario(idSimulado,idUsuario);
            if(respUser.isEmpty()){
                throw new EntityNotFoundException("Não foi possível encontrar respostas para o usuário e simulados especificados!");
            }
            return new NotaSimuladoUsuarioDTO(idSimulado,0);
        }
        return notaSimulado.get();
    }

    public List<NotaSimuladoUsuarioDTO> getNotasSimuladosUsuario(long idUsuario){
        return respostasUsuariosRepository.getNotasSimuladosUsuario(idUsuario);
    }

    public List<Long> perguntasCorretasSimuladoUsuario(long idUsuario,long idSimulado){
        return respostasUsuariosRepository.getPerguntasCorretasSimuladoUsuario(idUsuario,idSimulado);
    }


    public void deletar(long id){
        RespostasUsuarios respUsuarios = this.obter(id);
        try {
            respostasUsuariosRepository.delete(respUsuarios);
        }
        catch (Exception e){
            throw new DeleteComAssociacoes("Não é possível deletar a resposta usuário pois há itens associados a ela");
        }
    }
}
