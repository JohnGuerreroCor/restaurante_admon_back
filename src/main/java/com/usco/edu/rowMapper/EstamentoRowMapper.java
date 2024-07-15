package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Estamento;

public class EstamentoRowMapper implements RowMapper<Estamento>  {
	
	@Override
	public Estamento mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estamento estamento = new Estamento();
		estamento.setCodigo(rs.getInt("tus_codigo"));
		estamento.setNombre(rs.getString("tus_nombre"));
		return estamento;
	}

}
