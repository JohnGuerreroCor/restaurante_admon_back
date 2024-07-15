package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Firma;

public class FirmaRowMapper implements RowMapper<Firma> {
	
	@Override
	public Firma mapRow(ResultSet rs, int rowNum) throws SQLException {
		Firma firma = new Firma();
		firma.setCodigo(rs.getInt("fid_codigo"));
		firma.setUaaPersonalCodigo(rs.getInt("uap_codigo"));
		firma.setNombreFirma(rs.getString("fid_nombre"));
		firma.setFechaInicio(rs.getDate("fid_fecha_creacion"));
		firma.setFechaFin(rs.getDate("fid_fecha_fin"));
		firma.setEstado(rs.getInt("fid_estado"));
		firma.setRuta(rs.getString("fid_ruta"));
		firma.setPersona(new PersonaCarnetRowMapper().mapRow(rs, rowNum));
		return firma;
	}

}
