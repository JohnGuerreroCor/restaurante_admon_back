package com.usco.edu.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ticket implements Serializable {
	
	private int codigo;
	private Tercero tercero;
	private PersonaCarnet persona;
	private SedeCarnet sede;
	private SubSede subSede;
	private Bloque bloque;
	private Oficina oficina;
	private int tipo;
	private int tipoLugar;
	private Date fechaCreacion;
	private Date fechaVigencia;
	private int emailGraduado;
	private String qr;
	
	private static final long serialVersionUID = 1L;
	
}
