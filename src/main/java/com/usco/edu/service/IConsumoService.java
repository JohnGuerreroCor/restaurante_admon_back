package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Consumo;

public interface IConsumoService {

	public List<Consumo> obtenerConsumos(String userdb);

	public List<Consumo> obtenerConsumo(String userdb, int codigo);

	public int crearConsumo(String userdb, Consumo consumo);

	public int actualizarConsumo(String userdb, Consumo consumo);

}
