package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class Rol implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int codigo;

	private String nombre;

	private String estamento;

	private String descripcion;
	
	private int estado;

	@Override
	public String toString() {
		return "Rol [codigo=" + codigo + ", nombre=" + nombre + ", estamento=" + estamento + ", descripcion=" + descripcion +  "]";
	}

}
