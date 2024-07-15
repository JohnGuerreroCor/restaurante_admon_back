package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.DiaBeneficio;

public class DiaBeneficioRowMapper implements RowMapper<DiaBeneficio> {

	@Override
	public DiaBeneficio mapRow(ResultSet rs, int rowNum) throws SQLException {
		DiaBeneficio diaBeneficio = new DiaBeneficio();
		diaBeneficio.setCodigo(rs.getInt("rdb_codigo"));
		diaBeneficio.setCodigoGrupoGabu(rs.getInt("rgg_codigo"));
		diaBeneficio.setDia(new DiaRowMapper().mapRow(rs, rowNum));
		diaBeneficio.setEstado(rs.getInt("rdb_estado"));
		diaBeneficio.setTipoServicio(new TipoServicioRowMapper().mapRow(rs, rowNum));
		return diaBeneficio;
	}

}
