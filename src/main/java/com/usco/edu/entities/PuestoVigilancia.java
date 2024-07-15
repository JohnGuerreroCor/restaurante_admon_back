package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PuestoVigilancia implements Serializable {
	
	private int codigo;
	private Sede sede;
	private SubSede subsede;
	private Bloque bloque;
	private String nombre;
	private Date fechaCreacion;
	private Date fechaCierre;
	private int cupoCarro;
	private int cupoMoto;
	private int cupoBicicleta;
	private int estado;
	private PuestoVigilanciaTipo tipoPuesto;

	private static final long serialVersionUID = 1L;
}
