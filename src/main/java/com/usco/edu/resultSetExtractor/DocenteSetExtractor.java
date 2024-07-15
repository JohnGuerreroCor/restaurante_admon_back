package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Docente;
import com.usco.edu.rowMapper.DocenteRowMapper;

public class DocenteSetExtractor implements ResultSetExtractor<List<Docente>> {
	
	@Override
	public List<Docente> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Docente> list = new ArrayList<Docente>();
		while (rs.next()) {
			list.add(new DocenteRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
