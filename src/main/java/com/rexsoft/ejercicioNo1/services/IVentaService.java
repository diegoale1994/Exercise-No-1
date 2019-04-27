package com.rexsoft.ejercicioNo1.services;

import java.util.List;

import com.rexsoft.ejercicioNo1.app.models.Venta;

public interface IVentaService {
	public Venta Crear(Venta venta);
	public List<Venta> buscarTodos();
	
}
