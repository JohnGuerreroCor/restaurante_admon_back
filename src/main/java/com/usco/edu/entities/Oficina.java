package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Oficina implements Serializable {
	
	private int codigo;
	private String uaaNombre;
	private int uaaTipo;
	private int dependencia;
	private int sedeCodigo;

	private static final long serialVersionUID = 1L;
	
}
