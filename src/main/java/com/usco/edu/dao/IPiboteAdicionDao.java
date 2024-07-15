package com.usco.edu.dao;

import com.usco.edu.entities.PiboteAdicion;

public interface IPiboteAdicionDao {

	public int crearPibote(String userdb, PiboteAdicion piboteAdicion);

	public int actualizarPibote(String userdb, PiboteAdicion piboteAdicion);
}
