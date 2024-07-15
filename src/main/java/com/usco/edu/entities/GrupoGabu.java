package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GrupoGabu implements Serializable {

	private int codigo;
	private TipoGabu tipoGabu;
	private Persona persona;
	private long codigoEstudiante;
	private long identificacion;
	private String programa;
	private Date vigencia;
	private DiaBeneficio diaBeneficio;
	private int estado;

	private static final long serialVersionUID = 1L;
}
