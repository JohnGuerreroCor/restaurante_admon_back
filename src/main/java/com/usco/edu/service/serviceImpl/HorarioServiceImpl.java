package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IHorarioDao;
import com.usco.edu.entities.Dia;
import com.usco.edu.entities.Hora;
import com.usco.edu.entities.Horario;
import com.usco.edu.service.IHorarioService;

@Service
public class HorarioServiceImpl implements IHorarioService {
	
	@Autowired
	private IHorarioDao horarioDao;
	
	@Override
	public List<Dia> obtenerDias(String userdb) {
		
		return horarioDao.obtenerDias(userdb);
		
	}

	@Override
	public List<Hora> obtenerHoras(String userdb) {
		
		return horarioDao.obtenerHoras(userdb);
		
	}
	
	@Override
	public List<Horario> obtenerHorarios(String userdb) {
		
		return horarioDao.obtenerHorarios(userdb);
		
	}

	@Override
	public int registrar(String userdb, Horario horario) {
		
		return horarioDao.registrar(userdb, horario);
		
	}

	@Override
	public int actualizar(String userdb, Horario horario) {
		
		return horarioDao.actualizar(userdb, horario);
		
	}

}
