package com.vestibulando.repositories;

import com.vestibulando.entities.Banca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBancaRepository extends JpaRepository<Banca, Long> {
}
