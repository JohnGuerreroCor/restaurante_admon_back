package com.usco.edu.entities;
import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Estudiante implements Serializable {
	
	private String codigo;
	private int codigoPrograma;
	private Date fechaIngreso;
	private int codigoInscripcion;
	private int estadoEgresado;
	private PersonaCarnet persona;
	private ProgramaCarnet programa;
	
	private static final long serialVersionUID = 1L;

}
