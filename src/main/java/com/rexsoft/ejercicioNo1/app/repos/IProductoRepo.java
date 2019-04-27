package com.rexsoft.ejercicioNo1.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rexsoft.ejercicioNo1.app.models.Producto;

public interface IProductoRepo extends JpaRepository<Producto,Integer> {

}
