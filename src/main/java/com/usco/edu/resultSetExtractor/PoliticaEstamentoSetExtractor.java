package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.PoliticaEstamento;
import com.usco.edu.rowMapper.PoliticaEstamentoRowMapper;

public class PoliticaEstamentoSetExtractor implements ResultSetExtractor<List<PoliticaEstamento>> {
	
	@Override
	public List<PoliticaEstamento> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<PoliticaEstamento> list = new ArrayList<PoliticaEstamento>();
		while (rs.next()) {
			list.add(new PoliticaEstamentoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
