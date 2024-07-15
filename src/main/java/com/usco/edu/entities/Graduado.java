package com.usco.edu.entities;
import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Graduado implements Serializable {
	
	private String codigo;
	private Date fechaGrado;
	private int codigoPlan;
	private PersonaCarnet persona;
	private ProgramaCarnet programa;
	
	private static final long serialVersionUID = 1L;

}
