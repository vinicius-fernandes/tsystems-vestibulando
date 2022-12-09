package com.vestibulando.repositories;

import com.vestibulando.entities.Banca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBancaRepository extends JpaRepository<Banca, Long> {


    Banca findBySiglaIgnoreCase(String sigla);


    Banca findByNomeIgnoreCase(String nome);
}
