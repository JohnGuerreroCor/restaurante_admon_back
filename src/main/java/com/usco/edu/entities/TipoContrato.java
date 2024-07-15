package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoContrato implements Serializable {
	
	private int codigo;
	private String nombre;
	private String descripcion;
	private int estado;
	
	private static final long serialVersionUID = 1L;
}