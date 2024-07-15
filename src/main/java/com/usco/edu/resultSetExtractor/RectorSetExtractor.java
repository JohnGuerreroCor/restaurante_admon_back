package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Rector;
import com.usco.edu.rowMapper.RectorRowMapper;

public class RectorSetExtractor implements ResultSetExtractor<List<Rector>> {
	
	@Override
	public List<Rector> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Rector> list = new ArrayList<Rector>();
		while (rs.next()) {
			list.add(new RectorRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
