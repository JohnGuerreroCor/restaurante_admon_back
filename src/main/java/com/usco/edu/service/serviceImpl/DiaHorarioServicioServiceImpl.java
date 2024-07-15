package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IDiaHorarioServicioDao;
import com.usco.edu.entities.DiaHorarioServicio;
import com.usco.edu.service.IDiaHorarioServicioService;

@Service
public class DiaHorarioServicioServiceImpl implements IDiaHorarioServicioService {

	@Autowired
	private IDiaHorarioServicioDao diaHorarioServicioDao;

	@Override
	public List<DiaHorarioServicio> obtenerDiasHorarioServicio(String userdb) {
		return diaHorarioServicioDao.obtenerDiasHorarioServicio(userdb);
	}

	@Override
	public int actualizarDiaHorarioServicio(String userdb, DiaHorarioServicio diaHorarioServicio) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("no connecticut");
		System.out.println(diaHorarioServicio);
		return diaHorarioServicioDao.actualizarDiaHorarioServicio(userdb, diaHorarioServicio);
	}

}
