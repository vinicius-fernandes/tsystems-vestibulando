package com.vestibulando.repositories;

import com.vestibulando.dtos.UsuarioDTO;
import com.vestibulando.entities.Role;
import com.vestibulando.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("Select u from Usuario u where u.email = ?1")
    Optional<Usuario> findByEmail(String email);
    List<UsuarioDTO> findByRolesAndNomeContainsIgnoreCase(Role role, String texto);
    List<UsuarioDTO> findByRoles(Role role);

}
