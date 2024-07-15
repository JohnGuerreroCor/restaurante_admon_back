package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bloque implements Serializable {
	
	private int codigo;
	private String bloqueNombre;
	private int subSedeCodigo;

	private static final long serialVersionUID = 1L;
	
}
