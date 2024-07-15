package com.usco.edu.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tercero implements Serializable {
	
	int codigo;
	int tipoDocumento;
	String identificacion;
	String nombreCompleto;
	String nombre1;
	String nombre2;
	String apellido1;
	String apellido2;
	String email;
	String estado;
	Date fechaRegistro;
	
	
	private static final long serialVersionUID = 1L;
	
}
