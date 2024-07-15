package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Contrato;
import com.usco.edu.rowMapper.ContratoRowMapper;

public class ContratoSetExtractor implements ResultSetExtractor<List<Contrato>> {

	@Override
	public List<Contrato> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Contrato> list = new ArrayList<Contrato>();
		while (rs.next()) {
			list.add(new ContratoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
