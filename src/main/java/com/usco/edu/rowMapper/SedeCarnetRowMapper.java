package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.SedeCarnet;


public class SedeCarnetRowMapper implements RowMapper<SedeCarnet>{

	@Override
	public SedeCarnet mapRow(ResultSet rs, int rowNum) throws SQLException {
		SedeCarnet sede = new SedeCarnet();
		sede.setCodigo(rs.getInt("sed_codigo"));
		sede.setSedeNombre(rs.getString("sed_nombre"));
		sede.setSedeNombreCorto(rs.getString("SedNombre_corto"));
		sede.setMunicipioCodigo(rs.getInt("mun_codigo"));
		return sede;
	}

}
