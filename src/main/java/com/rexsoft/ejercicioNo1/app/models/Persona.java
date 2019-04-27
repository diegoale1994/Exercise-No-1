package com.rexsoft.ejercicioNo1.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description="Informacion de personas")
@Entity
@Table(name = "personas")
public class Persona  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersona;
	@ApiModelProperty(notes="Nombres debe tener minimo 10 caracteres")
	@Size(min = 3, message="Los nombres deben tener minimo 10 caracteres")
	@Column(name = "nombres", nullable = false, length = 70)
	private String nombres;
	@ApiModelProperty(notes="Apellidos debe tener minimo 10 caracteres")
	@Size(min = 3, message="Los apellidos deben tener minimo 10 caracteres")
	@Column(name = "apellidos", nullable = false, length = 70)
	private String apellidos;
	
	
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
}
