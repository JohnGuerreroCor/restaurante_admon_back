package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.TipoDocumento;

public interface ITipoDocumentoDao {
	
	public List<TipoDocumento> obtenerTiposDocumentos(String userdb);

}
