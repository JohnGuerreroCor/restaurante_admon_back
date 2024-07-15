package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IDiaBeneficioDao;
import com.usco.edu.entities.DiaBeneficio;
import com.usco.edu.service.IDiaBeneficioService;

@Service
public class DiaBeneficioServiceImpl implements IDiaBeneficioService {

	@Autowired
	private IDiaBeneficioDao diaBeneficio;

	@Override
	public List<DiaBeneficio> obtenerDiaBeneficio(String userdb, int idGrupoGabu) {
		System.out.println("entramos correctamente obtener-diasBeneficio " + userdb + " ID GRUPO GABU: " + idGrupoGabu);
		return diaBeneficio.obtenerDiaBeneficio(userdb, idGrupoGabu);
	}

	@Override
	public List<DiaBeneficio> obtenerDiasBeneficio(String userdb) {
		return diaBeneficio.obtenerDiasBeneficio(userdb);
	}

	
	@Override
	public int actualizarDiaBeneficio(String userdb, DiaBeneficio diasBeneficio) {
		System.out.println("entramos correctamente actualizar-diaBeneficio " + userdb);
		return diaBeneficio.actualizarDiaBeneficio(userdb, diasBeneficio);
	}

	@Override
	public int crearDiaBeneficio(String userdb, DiaBeneficio diasBeneficio) {
		System.out.println("entramos correctamente crear-diaBeneficio " + userdb);
		System.out.println(diasBeneficio);
		return diaBeneficio.crearDiaBeneficio(userdb, diasBeneficio);
	}

	@Override
	public int crearDiasBeneficio(String username, List<DiaBeneficio> diasBeneficio) {
		return diaBeneficio.crearDiasBeneficio(username, diasBeneficio);
	}

}
