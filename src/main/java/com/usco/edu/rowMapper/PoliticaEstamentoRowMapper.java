package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.PoliticaEstamento;

public class PoliticaEstamentoRowMapper implements RowMapper<PoliticaEstamento>  {
	
	@Override
	public PoliticaEstamento mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PoliticaEstamento politicaEstamento = new PoliticaEstamento();
		politicaEstamento.setCodigo(rs.getInt("poe_codigo"));
		politicaEstamento.setEstamento(new EstamentoRowMapper().mapRow(rs, rowNum));
		politicaEstamento.setFechaCreacion(rs.getDate("poe_fecha_creacion"));
		politicaEstamento.setFechaModificacion(rs.getDate("poe_fecha_modificacion"));
		politicaEstamento.setDescripcion(rs.getString("poe_descripcion"));
		return politicaEstamento;
		
	}

}
