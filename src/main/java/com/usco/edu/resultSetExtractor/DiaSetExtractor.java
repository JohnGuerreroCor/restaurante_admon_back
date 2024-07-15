package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Dia;
import com.usco.edu.rowMapper.DiaRowMapper;

public class DiaSetExtractor implements ResultSetExtractor<List<Dia>>{

	@Override
	public List<Dia> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Dia> list = new ArrayList<Dia>();
		while (rs.next()) {
			list.add(new DiaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
