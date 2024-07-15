package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ITipoContratoDao;
import com.usco.edu.entities.TipoContrato;
import com.usco.edu.service.ITipoContratoService;

@Service
public class TipoContratoServiceImpl implements ITipoContratoService {
	
	@Autowired
	private ITipoContratoDao tipoContratoDao;

	@Override
	public List<TipoContrato> obtenerTiposContrato(String userdb) {
		
		System.out.println("entramos correctamente obtener-tiposContrato " + userdb);
		return tipoContratoDao.obtenerTiposContrato(userdb);
	}

	@Override
	public int actualizarTipoContrato(String userdb, TipoContrato tipoContrato) {
		
		System.out.println("entramos correctamente actualizar tipo contrato " + userdb  + " " + tipoContrato);
		return tipoContratoDao.actualizarTipoContrato(userdb, tipoContrato);
	}

	@Override
	public int crearTipoContrato(String userdb, TipoContrato tipoContrato) {
		
		System.out.println("entramos correctamente crear tipo contrato " + userdb  + " " + tipoContrato);
		return tipoContratoDao.crearTipoContrato(userdb, tipoContrato);
	}

}
