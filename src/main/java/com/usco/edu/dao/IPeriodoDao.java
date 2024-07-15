package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Periodo;

public interface IPeriodoDao {

	public List<Periodo> obtenerPeriodos(String userdb);

	public List<Periodo> obtenerPeriodo(String userdb, int codigo);

}
