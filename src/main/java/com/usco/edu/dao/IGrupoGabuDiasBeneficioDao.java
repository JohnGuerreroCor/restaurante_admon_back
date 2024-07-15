package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.GrupoGabuDiasBeneficio;

public interface IGrupoGabuDiasBeneficioDao {

	public List<GrupoGabuDiasBeneficio> obtenerGabus(String userdb);
}
