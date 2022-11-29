package com.vestibulando.repositories;

import com.vestibulando.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,IRoleRepository> {
}
