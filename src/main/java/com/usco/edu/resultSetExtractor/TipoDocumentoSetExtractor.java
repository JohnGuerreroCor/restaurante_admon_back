package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.TipoDocumento;
import com.usco.edu.rowMapper.TipoDocumentoRowMapper;


public class TipoDocumentoSetExtractor implements ResultSetExtractor<List<TipoDocumento>> {
	
	@Override
	public List<TipoDocumento> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<TipoDocumento> list = new ArrayList<TipoDocumento>();
		while (rs.next()) {
			list.add(new TipoDocumentoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
