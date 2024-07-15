package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PiboteAdicion implements Serializable{

	private int codigo;
	private int codigoGeneral;
	private int codigoAdicion;
	private int estado;
	
	private static final long serialVersionUID = 1L;
}
