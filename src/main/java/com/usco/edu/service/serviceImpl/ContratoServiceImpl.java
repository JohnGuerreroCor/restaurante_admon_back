package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IContratoDao;
import com.usco.edu.entities.Contrato;
import com.usco.edu.service.IContratoService;

@Service
public class ContratoServiceImpl implements IContratoService {

	@Autowired
	private IContratoDao ContratoDao;

	@Override
	public List<Contrato> obtenerContratos(String userdb) {
		System.out.println("entramos correctamente obtener-contratos " + userdb);
		return ContratoDao.obtenerContratos(userdb);
	}

	@Override
	public List<Contrato> obtenerContrato(String userdb, int codigo) {
		System.out.println("entramos correctamente obtener-contrato " + userdb);
		return ContratoDao.obtenerContrato(userdb, codigo);
	}

	@Override
	public int crearContrato(String userdb, Contrato contrato) {
		System.out.println("entramos correctamente crear-contrato " + userdb);
		System.out.println(contrato);
		return ContratoDao.crearContrato(userdb, contrato);
	}

	@Override
	public int actualizarContrato(String userdb, Contrato contrato) {
		System.out.println("entramos correctamente actualizar-contrato " + userdb);
		System.out.println(contrato);
		return ContratoDao.actualizarContrato(userdb, contrato);
	}

}
