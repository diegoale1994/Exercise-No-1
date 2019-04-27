package com.rexsoft.ejercicioNo1.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rexsoft.ejercicioNo1.app.models.Persona;
import com.rexsoft.ejercicioNo1.app.repos.IPersonaRepo;
import com.rexsoft.ejercicioNo1.services.IPersonaService;

@Service
public class IPersonaServiceImpl implements IPersonaService {

	@Autowired 
	private IPersonaRepo personaRepo;
	
	@Override
	public Persona registrar(Persona t) {
		return this.personaRepo.save(t);
	}

	@Override
	public Persona modificar(Persona t) {
		return this.personaRepo.save(t);
	}

	@Override
	public Persona leer(Integer id) {
		return this.personaRepo.findById(id).orElse(null);
	}

	@Override
	public List<Persona> listartodos() {
		return this.personaRepo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		this.personaRepo.deleteById(id);
	}

}
