package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.HorarioServicio;

public class HorarioServicioRowMapper implements RowMapper<HorarioServicio> {

	@Override
	public HorarioServicio mapRow(ResultSet rs, int rowNum) throws SQLException {
		HorarioServicio horarioServicio = new HorarioServicio();
		horarioServicio.setCodigo(rs.getInt("rhs_codigo"));
		horarioServicio.setHoraIncioVenta(rs.getTime("rhs_hora_inicio_venta"));
		horarioServicio.setHoraFinVenta(rs.getTime("rhs_hora_fin_venta"));
		horarioServicio.setHoraInicioAtencion(rs.getTime("rhs_hora_inicio_atencion"));
		horarioServicio.setHoraFinAtencion(rs.getTime("rhs_hora_fin_atencion"));
		horarioServicio.setTipoServicio(new TipoServicioRowMapper().mapRow(rs, rowNum));
		horarioServicio.setUaa(new DependenciaRowMapper().mapRow(rs, rowNum));
		horarioServicio.setEstado(rs.getInt("rhs_estado"));
		horarioServicio.setObservacionEstado(rs.getString("rhs_observacion_estado"));
		horarioServicio.setFechaEstado(rs.getDate("rhs_fecha_estado"));
		horarioServicio.setCantidadComidas(rs.getInt("rhs_cantidad_comidas"));
		horarioServicio.setCantidadVentas(rs.getInt("rhs_cantidad_ventas_permitidas"));
		horarioServicio.setCantidadTiquetes(rs.getInt("rhs_cantidad_tiquetes"));
		return horarioServicio;
	}

}
