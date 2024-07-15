package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ITipoGabuDao;
import com.usco.edu.entities.TipoGabu;
import com.usco.edu.service.ITipoGabuService;

@Service
public class TipoGabuServiceImpl implements ITipoGabuService {

	@Autowired
	private ITipoGabuDao tipoGabuDao;

	@Override
	public List<TipoGabu> obtenerTiposGabu(String userdb) {
		System.out.println("entramos correctamente obtener-tiposGabu " + userdb);
		return tipoGabuDao.obtenerTiposGabu(userdb);
	}

	@Override
	public int actualizarTipoGabu(String userdb, TipoGabu tipoGabu) {
		System.out.println("entramos correctamente actualizar-tipoGabu " + userdb);
		return tipoGabuDao.actualizarTipoGabu(userdb, tipoGabu);
	}

	@Override
	public int crearTipoGabu(String userdb, TipoGabu tipoGabu) {
		System.out.println("entramos correctamente crear-tipoGabu " + userdb);
		return tipoGabuDao.crearTipoGabu(userdb, tipoGabu);
	}

}
