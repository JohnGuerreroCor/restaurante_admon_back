package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Tercero;


public interface ITerceroService {
	
	public List<Tercero> obtenerTerceroId(String id, String userdb);
	
	int registrar(String userdb, Tercero tercero);
	
	int actualizar(String userdb, Tercero tercero);

}
