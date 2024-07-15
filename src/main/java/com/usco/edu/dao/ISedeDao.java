package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.SedeCarnet;

public interface ISedeDao {//extends CrudRepository<Sede, Long>{
	
	//@Query(value = "SELECT * FROM sede s WHERE s.sed_estado = 1", 
	//		  nativeQuery = true)
	public List<SedeCarnet> sedesAll();
}
