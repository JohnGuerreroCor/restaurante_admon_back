package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.DiaHorarioServicio;
import com.usco.edu.rowMapper.DiaHorarioServicioRowMapper;

public class DiaHorarioServicioSetExtractor implements ResultSetExtractor<List<DiaHorarioServicio>> {

	@Override
	public List<DiaHorarioServicio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<DiaHorarioServicio> list = new ArrayList<DiaHorarioServicio>();
		while (rs.next()) {
			list.add(new DiaHorarioServicioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
