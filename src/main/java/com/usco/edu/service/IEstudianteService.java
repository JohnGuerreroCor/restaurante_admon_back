package com.usco.edu.service;
import java.util.List;

import com.usco.edu.entities.Estudiante;

public interface IEstudianteService {
	
	public List<Estudiante> obtenerEstudiantes(String userdb);
	
	public List<Estudiante> findByCodigo(String codigo, String userdb);
	
	public List<Estudiante> buscarIdentificacion(String id);
	
}
