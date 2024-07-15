package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.TipoContrato;
import com.usco.edu.rowMapper.TipoContratoRowMapper;

public class TipoContratoSetExtractor implements ResultSetExtractor<List<TipoContrato>> {

	@Override
	public List<TipoContrato> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<TipoContrato> list = new ArrayList<TipoContrato>();
		while (rs.next()) {
			list.add(new TipoContratoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
