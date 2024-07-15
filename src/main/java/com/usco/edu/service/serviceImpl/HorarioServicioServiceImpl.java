package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IHorarioServicioDao;
import com.usco.edu.entities.HorarioServicio;
import com.usco.edu.service.IHorarioServicioService;

@Service
public class HorarioServicioServiceImpl implements IHorarioServicioService {

	@Autowired
	private IHorarioServicioDao horarioServicioDao;

	@Override
	public List<HorarioServicio> obtenerHorariosServicio(String userdb) {
		return horarioServicioDao.obtenerHorariosServicio(userdb);
	}

	@Override
	public List<HorarioServicio> obtenerHorarioServicio(String userdb, int codigo) {
		return horarioServicioDao.obtenerHorarioServicio(userdb, codigo);
	}

	@Override
	public int crearHorarioServicio(String userdb, HorarioServicio horarioServicio) {
		return horarioServicioDao.crearHorarioServicio(userdb, horarioServicio);
	}

	@Override
	public int actualizarHorarioServicio(String userdb, HorarioServicio horarioServicio) {
		return horarioServicioDao.actualizarHorarioServicio(userdb, horarioServicio);
	}

}
