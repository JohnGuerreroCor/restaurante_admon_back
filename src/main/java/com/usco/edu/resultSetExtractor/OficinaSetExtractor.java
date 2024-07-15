package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Oficina;
import com.usco.edu.rowMapper.OficinaRowMapper;

public class OficinaSetExtractor implements ResultSetExtractor<List<Oficina>> {
	
	@Override
	public List<Oficina> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Oficina> list = new ArrayList<Oficina>();
		while (rs.next()) {
			list.add(new OficinaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
