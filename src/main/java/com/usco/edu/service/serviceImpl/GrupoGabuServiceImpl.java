package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IGrupoGabuDao;
import com.usco.edu.entities.GrupoGabu;
import com.usco.edu.entities.GrupoGabuDiasBeneficio;
import com.usco.edu.service.IGrupoGabuService;

@Service
public class GrupoGabuServiceImpl implements IGrupoGabuService {

	@Autowired
	private IGrupoGabuDao grupoGabuDao;

	@Override
	public List<GrupoGabu> obtenerGrupoGabus(String userdb) {
		System.out.println("entramos correctamente obtener-grupoGabus " + userdb);
		return grupoGabuDao.obtenerGrupoGabus(userdb);
	}

	@Override
	public List<GrupoGabu> obtenerGrupoGabu(String userdb, int codigo) {
		System.out.println("entramos correctamente obtener-grupoGabu " + userdb);
		return grupoGabuDao.obtenerGrupoGabu(userdb, codigo);
	}

	@Override
	public int crearGrupoGabu(String userdb, GrupoGabu grupoGabu) {
		System.out.println("entramos correctamente crear-grupoGabu " + userdb);
		System.out.println(grupoGabu);
		System.out.println("---------------------------------------------");
		System.out.println(grupoGabu.getTipoGabu().getCodigo());
		System.out.println(grupoGabu.getPersona().getCodigo());
		System.out.println(grupoGabu.getVigencia());
		System.out.println(grupoGabu.getEstado());
		System.out.println("---------------------------------------------");
		return grupoGabuDao.crearGrupoGabu(userdb, grupoGabu);
	}

	@Override
	public int actualizarGrupoGabu(String userdb, GrupoGabu grupoGabu) {
		System.out.println("entramos correctamente actualizar-grupoGabu " + userdb);
		grupoGabu.toString();
		return grupoGabuDao.actualizarGrupoGabu(userdb, grupoGabu);
	}

}
