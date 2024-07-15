package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Estudiante;
import com.usco.edu.rowMapper.EstudianteRowMapper;

public class EstudianteSetExtractor implements ResultSetExtractor<List<Estudiante>> {
	
	@Override
	public List<Estudiante> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Estudiante> list = new ArrayList<Estudiante>();
		while (rs.next()) {
			list.add(new EstudianteRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
