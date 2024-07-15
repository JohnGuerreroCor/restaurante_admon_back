package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;

import com.usco.edu.entities.Dependencia;
import com.usco.edu.rowMapper.DependenciaRowMapper;

public class DependenciaSetExtractor implements ResultSetExtractor<List<Dependencia>> {

	@Override
	@Nullable
	public List<Dependencia> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Dependencia> list = new ArrayList<Dependencia>();
		while (rs.next()) {
			list.add(new DependenciaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
