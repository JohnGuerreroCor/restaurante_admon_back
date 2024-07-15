package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Rector;
import com.usco.edu.entities.Firma;

public interface IFirmaDigitalDao {
	
	public List<Rector> buscarRectorActual(String userdb);
	
	public List<Firma> buscarFirmaActiva(String userdb);
	
	public List<Firma> buscarFirma(String userdb);
	
	void registrarFirma(Firma firma, String userdb);
	
	void actualizarFirmaEstado(Firma firma, String userdb);

}
