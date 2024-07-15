package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GrupoGabuDiasBeneficio implements Serializable {

	private int codigo;
	private TipoGabu tipoGabu;
	private Persona persona;
	private Persona usuario;
	private Dependencia dependecia;
	private Date vigencia;
	private int diasBeneficioCodigo;
	private Dia diaCodigo;
	private int estado;

	private static final long serialVersionUID = 1L;
}
