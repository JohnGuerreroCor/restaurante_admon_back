package com.usco.edu.service;

import com.usco.edu.entities.PiboteAdicion;

public interface IPiboteAdicionService {

	public int crearPibote(String userdb, PiboteAdicion piboteAdicion);

	public int actualizarPibote(String userdb, PiboteAdicion piboteAdicion);
	
}
