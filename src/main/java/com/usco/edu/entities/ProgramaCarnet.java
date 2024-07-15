package com.usco.edu.entities;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProgramaCarnet implements Serializable {
	
	private int codigo;
	private int codigoUaa;
	private String nombrePrograma;
	private String nombreCorto;
	private String sede;
	
	private static final long serialVersionUID = 1L;

}
