package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.DiaHorarioServicio;

public class DiaHorarioServicioRowMapper implements RowMapper<DiaHorarioServicio> {

	@Override
	public DiaHorarioServicio mapRow(ResultSet rs, int rowNum) throws SQLException {
		DiaHorarioServicio diaHorarioServicio = new DiaHorarioServicio();
		diaHorarioServicio.setCodigo(rs.getInt("rds_codigo"));
		diaHorarioServicio.setHorarioServicio(new HorarioServicioRowMapper().mapRow(rs, rowNum));
		diaHorarioServicio.setDia(new DiaRowMapper().mapRow(rs, rowNum));
		diaHorarioServicio.setEstado(rs.getInt("rds_estado"));
		return diaHorarioServicio;
	}

}
