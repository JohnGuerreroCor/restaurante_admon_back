package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Hora;
import com.usco.edu.rowMapper.HoraRowMapper;

public class HoraSetExtractor implements ResultSetExtractor<List<Hora>>{

	@Override
	public List<Hora> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Hora> list = new ArrayList<Hora>();
		while (rs.next()) {
			list.add(new HoraRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
