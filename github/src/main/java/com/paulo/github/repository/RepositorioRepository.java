package com.paulo.github.repository;

import com.paulo.github.Model.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RepositorioRepository extends JpaRepository<Repositorio, Long> {

/*
    @Query("select a from AnuncioModel a where a.cliente = ?1")
    AnuncioModel findByCliente(String nomeCliente);
*/



}
