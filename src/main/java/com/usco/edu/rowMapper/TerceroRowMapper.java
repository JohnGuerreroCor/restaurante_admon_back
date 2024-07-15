package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Tercero;


public class TerceroRowMapper implements RowMapper<Tercero> {
	
	@Override
	public Tercero mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tercero tercero = new Tercero();
		tercero.setCodigo(rs.getInt("ter_codigo"));
		tercero.setTipoDocumento(rs.getInt("tii_codigo"));
		tercero.setIdentificacion(rs.getString("ter_identificacion"));
		tercero.setNombreCompleto(rs.getString("ter_nombre"));
		tercero.setNombre1(rs.getString("ter_nombre1"));
		tercero.setNombre2(rs.getString("ter_nombre2"));
		tercero.setApellido1(rs.getString("ter_apellido1"));
		tercero.setApellido2(rs.getString("ter_apellido2"));
		tercero.setEmail(rs.getString("ter_email"));
		tercero.setEstado(rs.getString("ter_borrado"));
		tercero.setFechaRegistro(rs.getDate("ter_fecha"));
		
		return tercero;
	}

}