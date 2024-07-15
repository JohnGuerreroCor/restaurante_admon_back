package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.PersonaCarnet;

public interface IPersonaDao {
	public List<PersonaCarnet> buscarPorPerCodigo(int codigo, String userdb);
	public List<PersonaCarnet> buscarPorIdentificacion(String id, String userdb); 

}
