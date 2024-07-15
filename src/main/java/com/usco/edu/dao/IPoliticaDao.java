package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.PoliticaEstamento;

public interface IPoliticaDao {
	
	public List<PoliticaEstamento> obtenerPoliticaEstamento(String userdb);
	
	public List<PoliticaEstamento> obtenerPoliticaPorCodigoEstamento(int codigo,String userdb);
	
	int actualizar(String userdb, PoliticaEstamento politica);

}
