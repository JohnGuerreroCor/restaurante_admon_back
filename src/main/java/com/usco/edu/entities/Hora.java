package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Hora implements Serializable {
	
	private int codigo;
	private String nombre;
	private String horaInicioNacional;
	private String horaFinNacional;
	private String horaInicioInternacionaol;
	private String horaFinInternacional;

	private static final long serialVersionUID = 1L;
}
