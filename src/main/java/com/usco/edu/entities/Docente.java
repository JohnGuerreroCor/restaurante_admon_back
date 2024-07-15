package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Docente implements Serializable  {
	
	private int codigo;
	private int vinculacionCodigo;
	private String vinculacionNombre;
	private Date vinculacionFechaInicio;
	private Date vinculacionFechaFin;
	private String nombrePrograma;
	private String sede;
	private PersonaCarnet persona;
	
	private static final long serialVersionUID = 1L;

}
