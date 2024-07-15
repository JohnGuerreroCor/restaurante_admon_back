package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SedeCarnet implements Serializable {
	
	private int codigo;
	private String sedeNombre;
	private int municipioCodigo;
	private String sedeNombreCorto;

	private static final long serialVersionUID = 1L;
	
}
