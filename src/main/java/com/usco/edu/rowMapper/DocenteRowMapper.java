package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Docente;

public class DocenteRowMapper implements RowMapper<Docente> {
	
	@Override
	public Docente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Docente docente = new Docente();
		docente.setCodigo(rs.getInt("per_codigo"));
		docente.setVinculacionCodigo(rs.getInt("vin_codigo"));
		docente.setVinculacionNombre(rs.getString("vin_nombre"));
		docente.setVinculacionFechaInicio(rs.getDate("uap_fecha_inicio"));
		docente.setVinculacionFechaFin(rs.getDate("uap_fecha_fin"));
		docente.setNombrePrograma(rs.getString("uaa_nombre"));
		docente.setSede(rs.getString("sed_nombre"));
		docente.setPersona(new PersonaCarnetRowMapper().mapRow(rs, rowNum));
		return docente;
	}

}