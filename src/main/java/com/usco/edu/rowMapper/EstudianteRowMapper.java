package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Estudiante;

public class EstudianteRowMapper implements RowMapper<Estudiante> {
	
	@Override
	public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estudiante estudiante = new Estudiante();
		estudiante.setCodigo(rs.getString("est_codigo"));
		estudiante.setCodigoPrograma(rs.getInt("pro_codigo"));
		estudiante.setFechaIngreso(rs.getDate("est_fecha_ingreso"));
		estudiante.setCodigoInscripcion(rs.getInt("ins_codigo"));
		estudiante.setEstadoEgresado(rs.getInt("est_registro_egresado"));
		estudiante.setPersona(new PersonaCarnetRowMapper().mapRow(rs, rowNum));
		estudiante.setPrograma(new ProgramaCarnetRowMapper().mapRow(rs, rowNum));
		return estudiante;
	}

}