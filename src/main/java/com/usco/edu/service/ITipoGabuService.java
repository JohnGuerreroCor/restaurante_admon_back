package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.TipoGabu;

public interface ITipoGabuService {
	
	public List<TipoGabu> obtenerTiposGabu(String userdb);

	int actualizarTipoGabu(String userdb, TipoGabu tipoGabu);

	int crearTipoGabu(String userdb, TipoGabu tipoGabu);
}
