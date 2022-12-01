package com.vestibulando.repositories;

import com.vestibulando.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,IRoleRepository> {
    Optional<Role> findById(Long id);
}
