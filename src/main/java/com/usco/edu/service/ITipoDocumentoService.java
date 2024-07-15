package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.TipoDocumento;

public interface ITipoDocumentoService {
	
	public List<TipoDocumento> obtenerTiposDocumentos(String user);

}
