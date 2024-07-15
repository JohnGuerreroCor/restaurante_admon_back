package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.TipoGabu;

public class TipoGabuRowMapper implements RowMapper<TipoGabu> {

	@Override
	public TipoGabu mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoGabu tipoGabu = new TipoGabu();
		tipoGabu.setCodigo(rs.getInt("rtg_codigo"));
		tipoGabu.setNombre(rs.getString("rtg_nombre"));
		tipoGabu.setEstado(rs.getInt("rtg_estado"));
		return tipoGabu;
	}

}
