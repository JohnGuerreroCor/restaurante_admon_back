package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.TipoServicio;
import com.usco.edu.rowMapper.TipoServicioRowMapper;

public class TipoServicioSetExtractor implements ResultSetExtractor<List<TipoServicio>> {

	@Override
	public List<TipoServicio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<TipoServicio> list = new ArrayList<TipoServicio>();
		while (rs.next()) {
			list.add(new TipoServicioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
