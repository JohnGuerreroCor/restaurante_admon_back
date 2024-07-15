package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Dia;
import com.usco.edu.entities.Hora;
import com.usco.edu.entities.Horario;

public interface IHorarioDao {
	
	public List<Dia> obtenerDias(String userdb);
	
	public List<Hora> obtenerHoras(String userdb);
	
	public List<Horario> obtenerHorarios(String userdb);
	
	public int registrar(String userdb, Horario horario);
	
	int actualizar(String userdb, Horario horario);

}
