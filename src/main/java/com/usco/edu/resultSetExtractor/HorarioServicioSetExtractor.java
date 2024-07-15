package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.HorarioServicio;
import com.usco.edu.rowMapper.HorarioServicioRowMapper;

public class HorarioServicioSetExtractor implements ResultSetExtractor<List<HorarioServicio>> {

	@Override
	public List<HorarioServicio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<HorarioServicio> list = new ArrayList<HorarioServicio>();
		while (rs.next()) {
			list.add(new HorarioServicioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
