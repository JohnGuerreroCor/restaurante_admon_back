package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Administrativo;

public class AdministrativoRowMapper implements RowMapper<Administrativo> {
	
	@Override
	public Administrativo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Administrativo administrativo = new Administrativo();
		administrativo.setCodigo(rs.getInt("per_codigo"));
		administrativo.setIdentificacion(rs.getString("per_identificacion"));
		administrativo.setCodigoPersona(rs.getInt("per_codigo"));
		administrativo.setVinculacionCodigo(rs.getInt("vin_codigo"));
		administrativo.setVinculacionNombre(rs.getString("vin_nombre"));
		administrativo.setCargoNombre(rs.getString("car_nombre"));
		administrativo.setUaaDependencia(rs.getInt("uaa_dependencia"));
		administrativo.setVinculacionFechaInicio(rs.getDate("uap_fecha_inicio"));
		administrativo.setVinculacionFechaFin(rs.getDate("uap_fecha_fin"));
		administrativo.setNombrePrograma(rs.getString("uaa_nombre_corto"));
		return administrativo;
	}

}