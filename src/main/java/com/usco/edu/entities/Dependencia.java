package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Dependencia implements Serializable {

	private int codigo;
	private String nombre;
	private String correo;
	private String pagina;
	private String direccion;
	private String telefono;
	private String jefe;

	private static final long serialVersionUID = 1L;

}
