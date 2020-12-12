package com.cenfotec.examen3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.examen3.entities.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

}
