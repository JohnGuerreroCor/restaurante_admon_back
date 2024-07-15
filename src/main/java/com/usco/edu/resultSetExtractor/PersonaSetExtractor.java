package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.PersonaCarnet;
import com.usco.edu.rowMapper.PersonaCarnetRowMapper;

public class PersonaSetExtractor implements ResultSetExtractor<List<PersonaCarnet>> {
	
	@Override
	public List<PersonaCarnet> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<PersonaCarnet> list = new ArrayList<PersonaCarnet>();
		while (rs.next()) {
			list.add(new PersonaCarnetRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
