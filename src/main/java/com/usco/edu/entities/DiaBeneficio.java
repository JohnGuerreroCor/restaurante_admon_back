package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiaBeneficio implements Serializable {

	private int codigo;
	private int codigoGrupoGabu;
	private Dia dia;// 
	private int estado;
	private TipoServicio tipoServicio;
	
	private static final long serialVersionUID = 1L;
}
