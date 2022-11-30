package com.vestibulando.repositories;

import com.vestibulando.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("Select u from Usuario u where u.email = ?1")
    Usuario findByEmail(String email);
}
