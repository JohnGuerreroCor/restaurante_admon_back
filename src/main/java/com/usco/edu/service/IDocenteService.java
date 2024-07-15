package com.usco.edu.service;
import java.util.List;

import com.usco.edu.entities.Docente;

public interface IDocenteService {
	
	public List<Docente> findByIdentificacion(String id, String userdb);

}
