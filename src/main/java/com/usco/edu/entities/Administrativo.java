package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Administrativo implements Serializable {
	
	private int codigo;
	private String identificacion;
	private int codigoPersona;
	private int vinculacionCodigo;
	private String vinculacionNombre;
	private String cargoNombre;
	private Date vinculacionFechaInicio;
	private Date vinculacionFechaFin;
	private int uaaDependencia;
	private String nombrePrograma;
	
	private static final long serialVersionUID = 1L;

}
