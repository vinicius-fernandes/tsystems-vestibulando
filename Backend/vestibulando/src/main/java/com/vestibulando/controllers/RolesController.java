package com.vestibulando.controllers;

import com.vestibulando.entities.Role;
import com.vestibulando.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    RoleService roleService;
    @GetMapping
    public ResponseEntity<List<Role>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(this.roleService.list());
    }
}
