package com.rexsoft.ejercicioNo1.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rexsoft.ejercicioNo1.app.models.DetalleVenta;
import com.rexsoft.ejercicioNo1.app.models.Producto;
import com.rexsoft.ejercicioNo1.app.models.Venta;
import com.rexsoft.ejercicioNo1.app.repos.IProductoRepo;
import com.rexsoft.ejercicioNo1.app.repos.IVentaRepo;
import com.rexsoft.ejercicioNo1.services.IVentaService;

@Service
public class IVentaServiceImpl implements IVentaService {

	@Autowired 
	private IVentaRepo ventaRepo;
	@Autowired 
	private IProductoRepo productoRepo;
	@Override
	public Venta Crear(Venta venta) {
		venta.getDetalleVenta().forEach(det -> det.setVenta(venta));
		Double total =  0.00;
		for (DetalleVenta dv : venta.getDetalleVenta()) {
			Producto prod = productoRepo.findById(dv.getProducto().getIdProducto()).orElse(null);
		    total += dv.getCantidad()*prod.getPrecio();
		}
		venta.setImporte(total);
		return ventaRepo.save(venta);
	}

	@Override
	public List<Venta> buscarTodos() {
		return ventaRepo.findAll();
	}

}
