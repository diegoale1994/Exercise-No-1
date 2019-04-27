package com.rexsoft.ejercicioNo1.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Informacion del producto")
@Entity
@Table(name = "productos")
public class Producto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	@ApiModelProperty(notes="Nombre debe tener minimo 3 caracteres")
	@Size(min = 3, message="nombre deben tener minimo 3 caracteres")
	@NotEmpty(message = "No puede estar vacio !")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;
	@ApiModelProperty(notes="Marca debe tener minimo 3 caracteres")
	@Size(min = 3, message="marca deben tener minimo 3 caracteres")
	@NotEmpty(message = "No puede estar vacio !")
	@Column(name = "marca", nullable = false, length = 70)
	private String marca;
	@Column(name = "precio", nullable = false)
	private Double precio;
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
}
