package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Rol;

public class RolRowMapper implements RowMapper<Rol> {
	
	@Override
	public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
		Rol rolComiteAuto = new Rol();
		rolComiteAuto.setCodigo(rs.getInt("rca_codigo"));
		rolComiteAuto.setNombre(rs.getString("rca_nombre"));
		rolComiteAuto.setEstamento(rs.getString("rca_estamento"));
		rolComiteAuto.setDescripcion(rs.getString("rca_descripcion"));
		rolComiteAuto.setEstado(rs.getInt("rca_estado"));
		return rolComiteAuto;
	}

}
