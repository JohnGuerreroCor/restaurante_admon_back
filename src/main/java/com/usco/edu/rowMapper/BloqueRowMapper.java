package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Bloque;

public class BloqueRowMapper implements RowMapper<Bloque>{

	@Override
	public Bloque mapRow(ResultSet rs, int rowNum) throws SQLException {
		Bloque bloque = new Bloque();
		bloque.setCodigo(rs.getInt("blo_codigo"));
		bloque.setBloqueNombre(rs.getString("blo_nombre"));
		bloque.setSubSedeCodigo(rs.getInt("sus_codigo"));
		return bloque;
	}
}
