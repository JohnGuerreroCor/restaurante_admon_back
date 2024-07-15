package com.usco.edu.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IPoliticaDao;
import com.usco.edu.entities.PoliticaEstamento;
import com.usco.edu.service.IPoliticaService;

@Service
public class PoliticaServiceImpl implements IPoliticaService {
	
	@Autowired
	private IPoliticaDao politicaDao;

	@Override
	public List<PoliticaEstamento> obtenerPoliticaEstamento(String userdb) {
		
		return politicaDao.obtenerPoliticaEstamento(userdb);
		
	}
	
	@Override
	public List<PoliticaEstamento> obtenerPoliticaPorCodigoEstamento(int codigo, String userdb) {
		
		return politicaDao.obtenerPoliticaPorCodigoEstamento(codigo, userdb);
		
	}

	@Override
	public int actualizar(String userdb, PoliticaEstamento politica) {
		
		return politicaDao.actualizar(userdb, politica);
		
	}
	
}
