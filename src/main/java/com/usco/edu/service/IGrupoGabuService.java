package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.GrupoGabu;
import com.usco.edu.entities.GrupoGabuDiasBeneficio;

public interface IGrupoGabuService {

	public List<GrupoGabu> obtenerGrupoGabus(String userdb);

	public List<GrupoGabu> obtenerGrupoGabu(String userdb, int codigo);

	public int crearGrupoGabu(String userdb, GrupoGabu grupoGabu);

	public int actualizarGrupoGabu(String userdb, GrupoGabu grupoGabu);

}
