package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Hora;

public class HoraRowMapper implements RowMapper<Hora>{

	@Override
	public Hora mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hora hora = new Hora();
		hora.setCodigo(rs.getInt("hra_codigo"));
		hora.setNombre(rs.getString("hra_nombre"));
		hora.setHoraInicioNacional(rs.getString("hra_inicio"));
		hora.setHoraFinNacional(rs.getString("hra_fin"));
		hora.setHoraInicioInternacionaol(rs.getString("hra_inicio_24h"));
		hora.setHoraFinInternacional(rs.getString("hra_fin_24h"));
		return hora;
	}
}
