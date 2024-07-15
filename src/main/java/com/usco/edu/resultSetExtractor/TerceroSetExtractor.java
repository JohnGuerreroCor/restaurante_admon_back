package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Tercero;
import com.usco.edu.rowMapper.TerceroRowMapper;

public class TerceroSetExtractor implements ResultSetExtractor<List<Tercero>> {
	
	@Override
	public List<Tercero> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Tercero> list = new ArrayList<Tercero>();
		while (rs.next()) {
			list.add(new TerceroRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
