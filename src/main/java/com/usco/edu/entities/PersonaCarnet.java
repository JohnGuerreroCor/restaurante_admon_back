package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonaCarnet implements Serializable {
	
	private int codigo;
	
	private int tipoDocumento;
	
	private String identificacion;
	
	private String grupoSanguineo;
	
	private String genero;
	
	private Date fechaExpedicionDocumento;
	
	private Date fechaNacimiento;
	
	private int edad;

	private String nombre;

	private String apellido;
	
	private String emailPersonal;
	
	private String emailInterno;
	
	private static final long serialVersionUID = 1L;

}
