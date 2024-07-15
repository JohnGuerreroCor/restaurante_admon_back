package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Bloque;
import com.usco.edu.rowMapper.BloqueRowMapper;

public class BloqueSetExtractor implements ResultSetExtractor<List<Bloque>>{

	@Override
	public List<Bloque> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Bloque> list = new ArrayList<Bloque>();
		while (rs.next()) {
			list.add(new BloqueRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
