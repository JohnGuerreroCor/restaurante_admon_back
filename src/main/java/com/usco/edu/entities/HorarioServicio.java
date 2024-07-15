package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HorarioServicio implements Serializable {

	private int codigo;
	private Time horaIncioVenta;
	private Time horaFinVenta;
	private Time horaInicioAtencion;
	private Time horaFinAtencion;
	private TipoServicio tipoServicio;
	private Dependencia uaa;
	private int estado;
	private String observacionEstado;
	private Date fechaEstado;
	private int cantidadComidas;
	private int cantidadVentas;
	private int cantidadTiquetes;

	private static final long serialVersionUID = 1L;
}
