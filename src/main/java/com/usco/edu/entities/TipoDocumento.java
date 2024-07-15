package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoDocumento implements Serializable {
	
	private int codigo;
	private String tipoNombre;
	private String tipoNombreCorto;
	private String snies;
	
	private static final long serialVersionUID = 1L;

}
