package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.PuestoVigilancia;

public class PuestoVigilanciaRowMapper implements RowMapper<PuestoVigilancia>{

	@Override
	public PuestoVigilancia mapRow(ResultSet rs, int rowNum) throws SQLException {
		PuestoVigilancia puestoVigilancia = new PuestoVigilancia();
		puestoVigilancia.setCodigo(rs.getInt("puv_codigo"));
		puestoVigilancia.setSede(new SedeRowMapper().mapRow(rs, rowNum));
		puestoVigilancia.setSubsede(new SubSedeRowMapper().mapRow(rs, rowNum));
		puestoVigilancia.setBloque(new BloqueRowMapper().mapRow(rs, rowNum));
		puestoVigilancia.setNombre(rs.getString("puv_nombre"));
		puestoVigilancia.setFechaCreacion(rs.getDate("puv_fecha_creacion"));
		puestoVigilancia.setFechaCierre(rs.getDate("puv_fecha_cierre"));
		puestoVigilancia.setCupoCarro(rs.getInt("puv_cupo_carro"));
		puestoVigilancia.setCupoMoto(rs.getInt("puv_cupo_moto"));
		puestoVigilancia.setCupoBicicleta(rs.getInt("puv_cupo_bicicleta"));
		puestoVigilancia.setEstado(rs.getInt("puv_estado"));
		puestoVigilancia.setTipoPuesto(new PuestoVigilanciaTipoRowMapper().mapRow(rs, rowNum));
		return puestoVigilancia;
	}
}
