package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Horario implements Serializable {
	
	private int codigo;
	private PuestoVigilancia puestoVigilancia;
	private String horaApertura;
	private String horaCierre;
	private Date fechaCreacion;
	private Date fechaCierre;
	private Dia dia;
	private int estado;

	private static final long serialVersionUID = 1L;
}
