package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Firma;
import com.usco.edu.rowMapper.FirmaRowMapper;

public class FirmaSetExtractor implements ResultSetExtractor<List<Firma>> {
	
	@Override
	public List<Firma> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Firma> list = new ArrayList<Firma>();
		while (rs.next()) {
			list.add(new FirmaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
