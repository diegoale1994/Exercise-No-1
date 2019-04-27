package com.rexsoft.ejercicioNo1.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rexsoft.ejercicioNo1.app.models.Producto;
import com.rexsoft.ejercicioNo1.app.repos.IProductoRepo;
import com.rexsoft.ejercicioNo1.services.IProductoService;

@Service
public class IProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoRepo productoRepo;
	
	@Override
	public Producto registrar(Producto t) {
		return productoRepo.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		return productoRepo.save(t);
	}

	@Override
	public Producto leer(Integer id) {
		return productoRepo.findById(id).orElse(null);
	}

	@Override
	public List<Producto> listartodos() {
		return productoRepo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		productoRepo.deleteById(id);

	}

}
