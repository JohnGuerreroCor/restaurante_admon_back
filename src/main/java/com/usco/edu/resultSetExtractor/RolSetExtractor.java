package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Rol;
import com.usco.edu.rowMapper.RolRowMapper;

public class RolSetExtractor implements ResultSetExtractor<List<Rol>> {
	
	@Override
	public List<Rol> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Rol> list = new ArrayList<Rol>();
		while (rs.next()) {
			list.add(new RolRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}