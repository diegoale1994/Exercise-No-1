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
import com.rexsoft.ejercicioNo1.app.models.Producto;
import com.rexsoft.ejercicioNo1.services.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	
	@Autowired
	private IProductoService productoSerice;
	

	@GetMapping
	public ResponseEntity<List<Producto>> listar(){
		List<Producto> productos = productoSerice.listartodos();
		return new ResponseEntity<List<Producto>>(productos,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Resource<Producto> listarPorId(@PathVariable("id") Integer id) {
		Producto producto = productoSerice.leer(id);
		if(producto == null) {
			throw new ModeloNotFoundException(id + " no encontrado !");
		}
		//Level 3 
		Resource <Producto> resource = new Resource<Producto>(producto);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("persona-resource"));
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto producto) {
		Producto pro = productoSerice.registrar(producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pro.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Producto producto) {
		productoSerice.modificar(producto);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		
		Producto producto = productoSerice.leer(id);
		if(producto == null) {
			throw new ModeloNotFoundException(id + " no encontrado !");
		}else {
			productoSerice.eliminar(id);	
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
