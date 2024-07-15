package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Rol;

public interface IRolDao {
	
	public List<Rol> rol(String userdb);
	public List<Rol> rolByEstamento(String userdb, String estamento);
	public int createRol(String userdb, Rol r);
	int updateRol(String userdb, Rol r);

}
