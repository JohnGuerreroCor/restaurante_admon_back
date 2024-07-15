package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.GrupoGabu;

public class GrupoGabuRowMapper implements RowMapper<GrupoGabu> {

	@Override
	public GrupoGabu mapRow(ResultSet rs, int rowNum) throws SQLException {
		GrupoGabu grupoGabu = new GrupoGabu();
		grupoGabu.setCodigo(rs.getInt("rgg_codigo"));
		grupoGabu.setTipoGabu(new TipoGabuRowMapper().mapRow(rs, rowNum));
		grupoGabu.setPersona(new PersonaRowMapper().mapRow(rs, rowNum));
		grupoGabu.setCodigoEstudiante(rs.getLong("est_codigo"));
		grupoGabu.setIdentificacion(rs.getLong("per_identificacion"));
		grupoGabu.setPrograma(rs.getString("uaa_nombre_corto"));
		grupoGabu.setVigencia(rs.getDate("rgg_vigencia"));
		grupoGabu.setEstado(rs.getInt("rgg_estado"));
		grupoGabu.setDiaBeneficio(new DiaBeneficioRowMapper().mapRow(rs, rowNum));
		return grupoGabu;
	}

}
