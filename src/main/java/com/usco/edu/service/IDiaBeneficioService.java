package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.DiaBeneficio;

public interface IDiaBeneficioService {

	public List<DiaBeneficio> obtenerDiaBeneficio(String userdb, int idGrupoGabu);
	
	public List<DiaBeneficio> obtenerDiasBeneficio(String userdb);

	int actualizarDiaBeneficio(String userdb, DiaBeneficio diasBeneficio);

	int crearDiaBeneficio(String userdb, DiaBeneficio diasBeneficio);
	
	int crearDiasBeneficio(String username, List<DiaBeneficio> diasBeneficio);

}
