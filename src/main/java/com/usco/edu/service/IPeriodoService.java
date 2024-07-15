package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Periodo;

public interface IPeriodoService {

	public List<Periodo> obtenerPeriodos(String userdb);

	public List<Periodo> obtenerPeriodo(String userdb, int codigo);
}
