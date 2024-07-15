package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Graduado;

public class GraduadoRowMapper implements RowMapper<Graduado> {
	
	@Override
	public Graduado mapRow(ResultSet rs, int rowNum) throws SQLException {
		Graduado graduado = new Graduado();
		graduado.setCodigo(rs.getString("est_codigo"));
		graduado.setFechaGrado(rs.getDate("gra_fecha"));
		graduado.setCodigoPlan(rs.getInt("pla_codigo"));
		graduado.setPersona(new PersonaCarnetRowMapper().mapRow(rs, rowNum));
		graduado.setPrograma(new ProgramaCarnetRowMapper().mapRow(rs, rowNum));
		return graduado;
	}

}