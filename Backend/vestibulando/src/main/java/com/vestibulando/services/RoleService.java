package com.vestibulando.services;


import com.vestibulando.entities.Role;
import com.vestibulando.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    IRoleRepository roleRepository;

    public List<Role> list(){
        return this.roleRepository.findAll();
    }

    public Role obter(Long id){
        return roleRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado"));
    }
}
