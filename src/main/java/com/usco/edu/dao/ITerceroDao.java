package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Tercero;

public interface ITerceroDao {
	
	public List<Tercero> obtenerTerceroId( String id, String userdb);
	
	public int registrar(String userdb, Tercero tercero);
	
	int actualizar(String userdb, Tercero tercero);

}
