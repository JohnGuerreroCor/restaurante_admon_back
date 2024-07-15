package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.TipoGabu;

public interface ITipoGabuDao {

	public List<TipoGabu> obtenerTiposGabu(String userdb);

	int actualizarTipoGabu(String userdb, TipoGabu tipoGabu);

	int crearTipoGabu(String userdb, TipoGabu tipoGabu);
}
