package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Graduado;
import com.usco.edu.rowMapper.GraduadoRowMapper;

public class GraduadoSetExtractor implements ResultSetExtractor<List<Graduado>> {
	
	@Override
	public List<Graduado> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Graduado> list = new ArrayList<Graduado>();
		while (rs.next()) {
			list.add(new GraduadoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
