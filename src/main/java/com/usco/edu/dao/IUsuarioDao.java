package com.usco.edu.dao;

import com.usco.edu.entities.Usuario;

public interface IUsuarioDao { 
	
	public Usuario findByUsername(String username);
	public boolean validarUser(String username);

}
