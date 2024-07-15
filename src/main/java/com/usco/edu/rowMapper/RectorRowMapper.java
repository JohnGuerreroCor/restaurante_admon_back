package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Rector;

public class RectorRowMapper implements RowMapper<Rector>  {
	
	@Override
	public Rector mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Rector rector = new Rector();
		rector.setUaaPersonalCodigo(rs.getInt("uap_codigo"));
		rector.setUaaCodigo(rs.getInt("uaa_codigo"));
		rector.setUaaNombre(rs.getString("uaa_nombre"));
		rector.setFechaInicio(rs.getDate("uap_fecha_inicio"));
		rector.setFechaFin(rs.getDate("uap_fecha_fin"));
		rector.setUaaCargoCodigo(rs.getInt("uac_codigo"));
		rector.setUaaCargoNombre(rs.getString("car_nombre"));
		rector.setUaaEmail(rs.getString("uaa_email"));
		rector.setPersona(new PersonaCarnetRowMapper().mapRow(rs, rowNum));
		return rector;
		
	}

}
