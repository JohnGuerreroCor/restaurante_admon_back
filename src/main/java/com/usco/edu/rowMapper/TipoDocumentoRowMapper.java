package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.TipoDocumento;


public class TipoDocumentoRowMapper implements RowMapper<TipoDocumento> {
	
	@Override
	public TipoDocumento mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoDocumento tipoDocumento = new TipoDocumento();
		tipoDocumento.setCodigo(rs.getInt("tii_codigo"));
		tipoDocumento.setTipoNombre(rs.getString("tii_nombre"));
		tipoDocumento.setTipoNombreCorto(rs.getString("tii_nombre_corto"));
		tipoDocumento.setSnies(rs.getString("snies_codigo"));
		
		return tipoDocumento;
	}

}
