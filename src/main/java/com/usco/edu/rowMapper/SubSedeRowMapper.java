package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.SubSede;

public class SubSedeRowMapper implements RowMapper<SubSede>{

	@Override
	public SubSede mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubSede subSede = new SubSede();
		subSede.setCodigo(rs.getInt("sus_codigo"));
		subSede.setSubSedeNombre(rs.getString("sus_nombre"));
		subSede.setSedeCodigo(rs.getInt("sed_codigo"));
		subSede.setSubSedeDireccion(rs.getString("sus_direccion"));
		return subSede;
	}
}
