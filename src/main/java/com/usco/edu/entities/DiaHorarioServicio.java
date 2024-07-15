package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DiaHorarioServicio implements Serializable {

	private int codigo;
	private HorarioServicio horarioServicio;
	private Dia dia;
	private int estado;

	private static final long serialVersionUID = 1L;
}
