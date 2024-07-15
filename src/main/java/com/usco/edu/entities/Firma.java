package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Firma implements Serializable {
	
	private int codigo;
	private PersonaCarnet persona;
	private int uaaPersonalCodigo;
	private String nombreFirma;
	private Date fechaInicio;
	private Date fechaFin;
	private int estado;
	private String ruta;
	
	private static final long serialVersionUID = 1L;

}
