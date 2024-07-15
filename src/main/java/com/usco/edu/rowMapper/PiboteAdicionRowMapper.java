package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.usco.edu.entities.PiboteAdicion;

public class PiboteAdicionRowMapper implements RowMapper<PiboteAdicion> {

	@Override
	@Nullable
	public PiboteAdicion mapRow(ResultSet rs, int rowNum) throws SQLException {
		PiboteAdicion piboteAdicion = new PiboteAdicion();
		piboteAdicion.setCodigo(rs.getInt("rpa_codigo"));
		piboteAdicion.setCodigoGeneral(rs.getInt("rco_codigo_general"));
		piboteAdicion.setCodigoAdicion(rs.getInt("rco_codigo_adicion"));
		piboteAdicion.setEstado(rs.getInt("rpa_estado"));
		return piboteAdicion;
	}

}
