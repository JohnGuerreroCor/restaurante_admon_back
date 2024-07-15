package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.ProgramaCarnet;

public class ProgramaCarnetRowMapper implements RowMapper<ProgramaCarnet> {
	
	@Override
	public ProgramaCarnet mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramaCarnet programa = new ProgramaCarnet();
		programa.setCodigo(rs.getInt("pro_codigo"));
		programa.setCodigoUaa(rs.getInt("uaa_codigo"));
		programa.setNombrePrograma(rs.getString("uaa_nombre"));
		programa.setNombreCorto(rs.getString("uaa_nombre_corto"));
		programa.setSede(rs.getString("sed_nombre"));
		return programa;
	}

}