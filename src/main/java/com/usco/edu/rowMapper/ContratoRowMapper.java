package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Contrato;

public class ContratoRowMapper implements RowMapper<Contrato> {

	@Override
	public Contrato mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contrato contrato = new Contrato();
		contrato.setCodigo(rs.getInt("rco_codigo"));
		contrato.setTipoContrato(new TipoContratoRowMapper().mapRow(rs, rowNum));
		contrato.setFechaInicial(rs.getDate("rco_fecha_inicial"));
		contrato.setFechaFinal(rs.getDate("rco_fecha_final"));
		contrato.setValorContrato(rs.getInt("rco_valor_contrato"));
		contrato.setSubsidioDesayuno(rs.getInt("rco_subsidio_desayuno"));
		contrato.setSubsidioAlmuerzo(rs.getInt("rco_subsidio_almuerzo"));
		contrato.setSubsidioCena(rs.getInt("rco_subsidio_cena"));
		contrato.setPagoEstudianteDesayuno(rs.getInt("rco_pagado_estudiante_desayuno"));
		contrato.setPagoEstudianteAlmuerzo(rs.getInt("rco_pagado_estudiante_almuerzo"));
		contrato.setPagoEstudianteCena(rs.getInt("rco_pagado_estudiante_cena"));
		contrato.setCantidadDesayunos(rs.getInt("rco_cantidad_desayunos"));
		contrato.setCantidadAlmuerzos(rs.getInt("rco_cantidad_almuerzos"));
		contrato.setCantidadCenas(rs.getInt("rco_cantidad_cenas"));
		contrato.setDependencia(new DependenciaRowMapper().mapRow(rs, rowNum));
		contrato.setEstado(rs.getInt("rco_estado"));

		return contrato;
	}

}
