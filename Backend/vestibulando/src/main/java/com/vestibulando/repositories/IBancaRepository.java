package com.vestibulando.repositories;

import com.vestibulando.entities.Banca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBancaRepository extends JpaRepository<Banca, Long> {

    @Query("Select b from Banca b where b.sigla = ?1")
    Banca findBySigla(String sigla);

    @Query("Select b from Banca b where b.nome = ?1")
    Banca findByNome(String nome);
}
