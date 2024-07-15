package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.usco.edu.entities.GrupoGabuDiasBeneficio;

public class GrupoGabuDiasBeneficioRowMapper implements RowMapper<GrupoGabuDiasBeneficio> {

	@Override
	@Nullable
	public GrupoGabuDiasBeneficio mapRow(ResultSet rs, int rowNum) throws SQLException {
		GrupoGabuDiasBeneficio grupoGabuDiasBeneficio = new GrupoGabuDiasBeneficio();
		grupoGabuDiasBeneficio.setCodigo(rs.getInt("rgg_codigo"));
		grupoGabuDiasBeneficio.setTipoGabu(new TipoGabuRowMapper().mapRow(rs, rowNum));
		grupoGabuDiasBeneficio.setPersona(new PersonaRowMapper().mapRow(rs, rowNum));
		grupoGabuDiasBeneficio.setVigencia(rs.getDate("rgg_vigencia"));
		grupoGabuDiasBeneficio.setDiasBeneficioCodigo(rs.getInt("rdb_codigo"));
		grupoGabuDiasBeneficio.setDiaCodigo(new DiaRowMapper().mapRow(rs, rowNum));
		grupoGabuDiasBeneficio.setEstado(rs.getInt("rgg_estado"));
		return grupoGabuDiasBeneficio;
	}

}
