package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.usco.edu.dao.IPuestoVigilanciaDao;
import com.usco.edu.entities.PuestoVigilancia;
import com.usco.edu.entities.PuestoVigilanciaTipo;
import com.usco.edu.service.IPuestoVigilanciaService;

@Service
public class PuestoVigilanciaServiceImpl implements IPuestoVigilanciaService {
	
	@Autowired
	private IPuestoVigilanciaDao puestoDao;

	@Override
	public List<PuestoVigilanciaTipo> obtenerTipoPuesto(String userdb) {
		
		return puestoDao.obtenerTipoPuesto(userdb);
		
	}

	@Override
	public List<PuestoVigilancia> obtenerPuestoVigilancia(String userdb) {
		
		return puestoDao.obtenerPuestoVigilancia(userdb);
		
	}
	
	@Override
	public List<PuestoVigilancia> obtenerPuestoVigilanciaCodigo(int codigo, String userdb) {
		
		return puestoDao.obtenerPuestoVigilanciaCodigo(codigo, userdb);
		
	}
	
	@Override
	public List<PuestoVigilancia> obtenerPuestoVigilanciaPorBloqueTipo(int subsede, int tipo, String userdb) {
		
		return puestoDao.obtenerPuestoVigilanciaPorBloqueTipo(subsede, tipo, userdb);
		
	}

	@Override
	public int registrar(String userdb, PuestoVigilancia puestoVigilancia) {
		
		return puestoDao.registrar(userdb, puestoVigilancia);
		
	}

	@Override
	public int actualizar(String userdb, PuestoVigilancia puestoVigilancia) {
		
		return puestoDao.actualizar(userdb, puestoVigilancia);
		
	}

}
