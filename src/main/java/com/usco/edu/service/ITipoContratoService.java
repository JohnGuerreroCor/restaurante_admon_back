package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.TipoContrato;

public interface ITipoContratoService {

	public List<TipoContrato> obtenerTiposContrato(String userdb);
	
	int actualizarTipoContrato(String userdb, TipoContrato tipoContrato);
	
	int crearTipoContrato(String userdb, TipoContrato tipoContrato);
	
}
