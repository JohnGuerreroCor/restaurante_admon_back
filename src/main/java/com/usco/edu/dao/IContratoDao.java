package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Contrato;

public interface IContratoDao {

	public List<Contrato> obtenerContratos(String userdb);

	public List<Contrato> obtenerContrato(String userdb, int codigo);

	public int crearContrato(String userdb, Contrato contrato);

	public int actualizarContrato(String userdb, Contrato contrato);
}
