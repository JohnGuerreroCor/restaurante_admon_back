package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IUbicacionDao;
import com.usco.edu.entities.Bloque;
import com.usco.edu.entities.Oficina;
import com.usco.edu.entities.SedeCarnet;
import com.usco.edu.entities.SubSede;
import com.usco.edu.service.IUbicacionService;

@Service
public class UbicacionServiceImpl implements IUbicacionService {
	
	@Autowired
	private IUbicacionDao ubicacionDao;

	@Override
	public List<SedeCarnet> obtenerSedes(String userdb) {
		
		return ubicacionDao.obtenerSedes(userdb);
		
	}

	@Override
	public List<SubSede> obtenerSubSedes(String userdb) {

		return ubicacionDao.obtenerSubSedes(userdb);

	}

	@Override
	public List<Bloque> obtenerBloques(String userdb) {

		return ubicacionDao.obtenerBloques(userdb);

	}
	
	@Override
	public List<Oficina> obtenerOficinas(String userdb) {
		
		return ubicacionDao.obtenerOficinas(userdb);
		
	}

	@Override
	public List<SubSede> buscarSubSedePorSede(int codigo, String userdb) {

		return ubicacionDao.buscarSubSedePorSede(codigo, userdb);

	}

	@Override
	public List<Bloque> buscarBloquePorSubSede(int codigo, String userdb) {

		return ubicacionDao.buscarBloquePorSubSede(codigo, userdb);

	}

	@Override
	public List<Oficina> buscarOficinaPorSede(int codigo, String userdb) {
		
		return ubicacionDao.buscarOficinaPorSede(codigo, userdb);
		
	}

}
