package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.usco.edu.entities.Dependencia;

public class DependenciaRowMapper implements RowMapper<Dependencia> {

	@Override
	@Nullable
	public Dependencia mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dependencia dependencia = new Dependencia();
		dependencia.setCodigo(rs.getInt("uaa_codigo"));
		dependencia.setNombre(rs.getString("uaa_nombre"));
		dependencia.setCorreo(rs.getString("uaa_email"));
		dependencia.setPagina(rs.getString("uaa_pagina"));
		dependencia.setDireccion(rs.getString("uaa_direccion"));
		dependencia.setTelefono(rs.getString("uaa_telefono"));
		dependencia.setJefe(rs.getString("uaa_jefe"));
		return dependencia;
	}

}
