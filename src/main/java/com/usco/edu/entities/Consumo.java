package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Consumo implements Serializable {

	private int codigo;
	private Persona persona;
	private Venta venta;
	private TipoServicio tipoServicio;
	private Contrato contrato;
	private Timestamp fechaHora;
	private Uaa uaa;
	private Persona usuario;
	private int estado;

	private static final long serialVersionUID = 1L;
}
