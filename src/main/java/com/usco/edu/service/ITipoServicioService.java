package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.TipoServicio;

public interface ITipoServicioService {

	public List<TipoServicio> obtenerTiposServicio(String userdb);

	int actualizarTipoServicio(String userdb, TipoServicio tipoServicio);

	int crearTipoServicio(String userdb, TipoServicio tipoServicio);
}
