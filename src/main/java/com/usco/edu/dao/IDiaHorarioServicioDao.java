package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.DiaHorarioServicio;

public interface IDiaHorarioServicioDao {

	public List<DiaHorarioServicio> obtenerDiasHorarioServicio(String userdb);

	public int actualizarDiaHorarioServicio(String userdb, DiaHorarioServicio diaHorarioServicio);
}
