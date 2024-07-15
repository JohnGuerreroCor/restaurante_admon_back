package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.DiaHorarioServicio;

public interface IDiaHorarioServicioService {

	public List<DiaHorarioServicio> obtenerDiasHorarioServicio(String userdb);

	public int actualizarDiaHorarioServicio(String userdb, DiaHorarioServicio diaHorarioServicio);

}
