package com.rexsoft.ejercicioNo1.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rexsoft.ejercicioNo1.app.models.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {

}
