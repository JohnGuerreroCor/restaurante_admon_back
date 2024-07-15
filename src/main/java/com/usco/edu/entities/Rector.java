package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rector implements Serializable {
	
	private PersonaCarnet persona;
	private int uaaPersonalCodigo;
	private int uaaCodigo;
	private String uaaNombre;
	private Date fechaInicio;
	private Date fechaFin;
	private int uaaCargoCodigo;
	private String uaaCargoNombre;
	private String uaaEmail;
	
	private static final long serialVersionUID = 1L;

}
