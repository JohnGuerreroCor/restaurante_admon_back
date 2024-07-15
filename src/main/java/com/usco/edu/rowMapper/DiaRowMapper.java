package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Dia;

public class DiaRowMapper implements RowMapper<Dia>{

	@Override
	public Dia mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dia dia = new Dia();
		dia.setCodigo(rs.getInt("dia_codigo"));
		dia.setNombre(rs.getString("dia_nombre"));
		dia.setPosicion(rs.getInt("dia_posicion_semana"));
		return dia;
	}
}
