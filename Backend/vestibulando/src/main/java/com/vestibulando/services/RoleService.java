package com.vestibulando.services;


import com.vestibulando.entities.Role;
import com.vestibulando.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    IRoleRepository roleRepository;

    public List<Role> list(){
        return this.roleRepository.findAll();
    }
}
