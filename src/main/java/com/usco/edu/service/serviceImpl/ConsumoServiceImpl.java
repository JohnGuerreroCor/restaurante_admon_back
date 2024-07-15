package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IConsumoDao;
import com.usco.edu.entities.Consumo;
import com.usco.edu.service.IConsumoService;

@Service
public class ConsumoServiceImpl implements IConsumoService {

	@Autowired
	private IConsumoDao consumoDao;

	@Override
	public List<Consumo> obtenerConsumos(String userdb) {
		consumoDao.obtenerConsumos(userdb);
		return null;
	}

	@Override
	public List<Consumo> obtenerConsumo(String userdb, int codigo) {
		consumoDao.obtenerConsumo(userdb, codigo);
		return null;
	}

	@Override
	public int crearConsumo(String userdb, Consumo consumo) {
		consumoDao.crearConsumo(userdb, consumo);
		return 0;
	}

	@Override
	public int actualizarConsumo(String userdb, Consumo consumo) {
		consumoDao.actualizarConsumo(userdb, consumo);
		return 0;
	}

}
