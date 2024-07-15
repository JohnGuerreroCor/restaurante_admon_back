package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Oficina;

public class OficinaRowMapper implements RowMapper<Oficina> {
	
	@Override
	public Oficina mapRow(ResultSet rs, int rowNum) throws SQLException {
		Oficina oficina = new Oficina();
		oficina.setCodigo(rs.getInt("uaa_codigo"));
		oficina.setUaaNombre(rs.getString("uaa_nombre"));
		oficina.setUaaTipo(rs.getInt("uat_codigo"));
		oficina.setDependencia(rs.getInt("uaa_dependencia"));
		oficina.setSedeCodigo(rs.getInt("sed_codigo"));
		return oficina;
	}

}
