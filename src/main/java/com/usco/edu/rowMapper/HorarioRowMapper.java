package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Horario;

public class HorarioRowMapper implements RowMapper<Horario>{

	@Override
	public Horario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Horario horario = new Horario();
		horario.setCodigo(rs.getInt("hpv_codigo"));
		horario.setPuestoVigilancia(new PuestoVigilanciaRowMapper().mapRow(rs, rowNum));
		horario.setHoraApertura(rs.getString("hpv_hora_apertura"));
		horario.setHoraCierre(rs.getString("hpv_hora_cierre"));
		horario.setFechaCreacion(rs.getDate("hpv_fecha_creacion"));
		horario.setFechaCierre(rs.getDate("hpv_fecha_cierre"));
		horario.setDia(new DiaRowMapper().mapRow(rs, rowNum));
		horario.setEstado(rs.getInt("hpv_estado"));
		return horario;
	}
}