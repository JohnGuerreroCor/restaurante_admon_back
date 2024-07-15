package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.usco.edu.entities.Consumo;

public class ConsumoRowMapper implements RowMapper<Consumo> {

	@Override
	@Nullable
	public Consumo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Consumo consumo = new Consumo();
		consumo.setCodigo(rs.getInt("rcn_codigo"));
		consumo.setPersona(new PersonaRowMapper().mapRow(rs, rowNum));
		consumo.setVenta(new VentaRowMapper().mapRow(rs, rowNum));
		consumo.setTipoServicio(new TipoServicioRowMapper().mapRow(rs, rowNum));
		consumo.setContrato(new ContratoRowMapper().mapRow(rs, rowNum));
		consumo.setFechaHora(rs.getTimestamp("rcn_fecha_hora"));
		consumo.setUaa(new UaaRowMapper().mapRow(rs, rowNum));
		consumo.setUsuario(new PersonaRowMapper().mapRow(rs, rowNum));
		consumo.setEstado(rs.getInt("rcn_estado"));
		return consumo;
	}

}
