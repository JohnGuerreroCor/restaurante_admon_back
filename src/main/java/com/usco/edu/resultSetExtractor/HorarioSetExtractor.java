package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Horario;
import com.usco.edu.rowMapper.HorarioRowMapper;

public class HorarioSetExtractor implements ResultSetExtractor<List<Horario>>{

	@Override
	public List<Horario> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Horario> list = new ArrayList<Horario>();
		while (rs.next()) {
			list.add(new HorarioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}