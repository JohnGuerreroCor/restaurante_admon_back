package com.usco.edu.service;

import com.usco.edu.entities.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
}
