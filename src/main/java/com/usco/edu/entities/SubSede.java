package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubSede implements Serializable {
	
	private int codigo;
	private String subSedeNombre;
	private int sedeCodigo;
	private String subSedeDireccion;

	private static final long serialVersionUID = 1L;
	
}
