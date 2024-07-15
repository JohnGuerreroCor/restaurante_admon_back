package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Estamento;
import com.usco.edu.rowMapper.EstamentoRowMapper;

public class EstamentoSetExtractor implements ResultSetExtractor<List<Estamento>> {
	
	@Override
	public List<Estamento> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Estamento> list = new ArrayList<Estamento>();
		while (rs.next()) {
			list.add(new EstamentoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
