package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.SedeCarnet;
import com.usco.edu.rowMapper.SedeCarnetRowMapper;

public class SedeCarnetSetExtractor implements ResultSetExtractor<List<SedeCarnet>>{

	@Override
	public List<SedeCarnet> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<SedeCarnet> list = new ArrayList<SedeCarnet>();
		while (rs.next()) {
			list.add(new SedeCarnetRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
