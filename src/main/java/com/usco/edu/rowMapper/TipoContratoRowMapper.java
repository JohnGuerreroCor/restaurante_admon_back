package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.TipoContrato;

public class TipoContratoRowMapper implements RowMapper<TipoContrato> {

	@Override
	public TipoContrato mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoContrato tipoContrato = new TipoContrato();
		tipoContrato.setCodigo(rs.getInt("rtc_codigo"));
		tipoContrato.setNombre(rs.getString("rtc_nombre"));
		tipoContrato.setDescripcion(rs.getString("rtc_descripcion"));
		tipoContrato.setEstado(rs.getInt("rtc_estado"));
		return tipoContrato;
		
	}

}
