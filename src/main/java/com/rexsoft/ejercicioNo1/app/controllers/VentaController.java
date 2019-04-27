package com.rexsoft.ejercicioNo1.app.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rexsoft.ejercicioNo1.app.models.Venta;
import com.rexsoft.ejercicioNo1.services.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService ventaService;
	
	@GetMapping
	public ResponseEntity<List<Venta>> listar(){
		List<Venta> ventas = ventaService.buscarTodos();
		return new ResponseEntity<List<Venta>>(ventas,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Venta venta) {
		Venta vent = ventaService.Crear(venta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vent.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}
}
