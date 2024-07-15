package com.usco.edu.dao;

public interface IFotoCarnetDao {
	
	String obtenerTokenFoto(String atributos, String user);

	String obtenerTokenFotoVisualizar(String atributos, String user);

}
