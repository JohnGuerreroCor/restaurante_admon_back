package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Graduado;

public interface IGraduadoDao {
	
	public List<Graduado> buscarPorCodigo( String codigo, String userdb);
	
}
