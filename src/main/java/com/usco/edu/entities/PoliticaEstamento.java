package com.usco.edu.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PoliticaEstamento implements Serializable  {
	
	private int codigo;
	private Estamento estamento;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String descripcion;
	
	private static final long serialVersionUID = 1L;
}
