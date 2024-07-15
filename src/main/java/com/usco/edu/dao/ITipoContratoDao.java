package com.usco.edu.dao;

import com.usco.edu.entities.TipoContrato;
import java.util.List;

public interface ITipoContratoDao {

	public List<TipoContrato> obtenerTiposContrato(String userdb);
	
	int actualizarTipoContrato(String userdb, TipoContrato tipoContrato);
	
	int crearTipoContrato(String userdb, TipoContrato tipoContrato); 
	
}
