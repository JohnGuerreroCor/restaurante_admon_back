package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IPeriodoDao;
import com.usco.edu.entities.Periodo;
import com.usco.edu.service.IPeriodoService;

@Service
public class PeriodoServiceImpl implements IPeriodoService {

	@Autowired
	private IPeriodoDao periodoDao;

	@Override
	public List<Periodo> obtenerPeriodos(String userdb) {
		System.out.println("entramos correctamente obtener-periodos " + userdb);
		return periodoDao.obtenerPeriodos(userdb);
	}

	@Override
	public List<Periodo> obtenerPeriodo(String userdb, int codigo) {
		System.out.println("entramos correctamente obtener-periodo " + userdb);
		return periodoDao.obtenerPeriodo(userdb, codigo);
	}

}
