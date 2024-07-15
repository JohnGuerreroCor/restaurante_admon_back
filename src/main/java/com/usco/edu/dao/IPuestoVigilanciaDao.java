package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.PuestoVigilanciaTipo;
import com.usco.edu.entities.PuestoVigilancia;

public interface IPuestoVigilanciaDao {
	
	public List<PuestoVigilanciaTipo> obtenerTipoPuesto(String userdb);
	
	public List<PuestoVigilancia> obtenerPuestoVigilancia(String userdb);
	
	public List<PuestoVigilancia> obtenerPuestoVigilanciaCodigo(int codigo, String userdb);
	
	public List<PuestoVigilancia> obtenerPuestoVigilanciaPorBloqueTipo(int subsede, int tipo, String userdb);
	
	public int registrar(String userdb, PuestoVigilancia puestoVigilancia);
	
	int actualizar(String userdb, PuestoVigilancia puestoVigilancia);

}
