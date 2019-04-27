package com.rexsoft.ejercicioNo1.app.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rexsoft.ejercicioNo1.app.exception.ModeloNotFoundException;
import com.rexsoft.ejercicioNo1.app.models.Persona;
import com.rexsoft.ejercicioNo1.services.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaSerice;
	
	@GetMapping
	public ResponseEntity<List<Persona>> listar(){
		List<Persona> personas = personaSerice.listartodos();
		return new ResponseEntity<List<Persona>>(personas,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Resource<Persona> listarPorId(@PathVariable("id") Integer id) {
		Persona persona = personaSerice.leer(id);
		if(persona == null) {
			throw new ModeloNotFoundException(id + " no encontrado !");
		}
		//Level 3 
		Resource <Persona> resource = new Resource<Persona>(persona);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("persona-resource"));
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Persona persona) {
		Persona per = personaSerice.registrar(persona);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(per.getIdPersona()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Persona persona) {
		personaSerice.modificar(persona);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		
		Persona persona = personaSerice.leer(id);
		if(persona == null) {
			throw new ModeloNotFoundException(id + " no encontrado !");
		}else {
			personaSerice.eliminar(id);	
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
