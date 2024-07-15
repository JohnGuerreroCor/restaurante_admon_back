package com.usco.edu.service;

import java.util.List;
import com.usco.edu.entities.Rol;

public interface IRolService {
	
	public List<Rol> rol(String userdb);
	public List<Rol> rolByEstamento(String userdb, String estamento);
	int createRol(String userdb, Rol r);
	int updateRol(String userdb, Rol r);

}
