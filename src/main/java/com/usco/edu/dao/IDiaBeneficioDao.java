package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.DiaBeneficio;

public interface IDiaBeneficioDao {

	public List<DiaBeneficio> obtenerDiaBeneficio(String userdb, int idGrupoGabu);
	
	public List<DiaBeneficio> obtenerDiasBeneficio(String userdb);

	int actualizarDiaBeneficio(String userdb, DiaBeneficio diasBeneficio);

	int crearDiaBeneficio(String username, DiaBeneficio diaBeneficio);
	
	int crearDiasBeneficio(String username, List<DiaBeneficio> diasBeneficio);
	
}
