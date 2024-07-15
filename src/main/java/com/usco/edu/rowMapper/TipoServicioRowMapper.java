package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.TipoServicio;

public class TipoServicioRowMapper implements RowMapper<TipoServicio> {

	@Override
	public TipoServicio mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoServicio tipoServicio = new TipoServicio();
		tipoServicio.setCodigo(rs.getInt("rts_codigo"));
		tipoServicio.setNombre(rs.getString("rts_nombre"));
		tipoServicio.setEstado(rs.getInt("rts_estado"));
		return tipoServicio;
	}

}
