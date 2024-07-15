package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.PuestoVigilanciaTipo;

public class PuestoVigilanciaTipoRowMapper implements RowMapper<PuestoVigilanciaTipo>{

	@Override
	public PuestoVigilanciaTipo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PuestoVigilanciaTipo tipoPuesto = new PuestoVigilanciaTipo();
		tipoPuesto.setCodigo(rs.getInt("pvt_codigo"));
		tipoPuesto.setNombre(rs.getString("pvt_nombre"));
		tipoPuesto.setEstado(rs.getInt("pvt_estado"));
		return tipoPuesto;
		
	}
}
