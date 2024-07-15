package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Docente;

public interface IDocenteDao {
	public List<Docente> findByIdentificacion( String id, String userdb);

}
