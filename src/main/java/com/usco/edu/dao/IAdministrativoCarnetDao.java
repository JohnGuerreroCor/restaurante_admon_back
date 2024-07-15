package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Administrativo;

public interface IAdministrativoCarnetDao {
	
	public List<Administrativo> findByIdentificacion(String id, String userdb);

}
