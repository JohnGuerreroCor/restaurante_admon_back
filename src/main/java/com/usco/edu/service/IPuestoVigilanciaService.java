package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.PuestoVigilancia;
import com.usco.edu.entities.PuestoVigilanciaTipo;

public interface IPuestoVigilanciaService {
	
	public List<PuestoVigilanciaTipo> obtenerTipoPuesto(String userdb);
	
	public List<PuestoVigilancia> obtenerPuestoVigilancia(String userdb);
	
	public List<PuestoVigilancia> obtenerPuestoVigilanciaCodigo(int codigo, String userdb);
	
	public List<PuestoVigilancia> obtenerPuestoVigilanciaPorBloqueTipo(int subsede, int tipo, String userdb);
	
	public int registrar(String userdb, PuestoVigilancia puestoVigilancia);
	
	int actualizar(String userdb, PuestoVigilancia puestoVigilancia);

}
