package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Estamento;

public interface IEstamentoDao {
	
	public List<Estamento> estamentos(String userdb);

}
