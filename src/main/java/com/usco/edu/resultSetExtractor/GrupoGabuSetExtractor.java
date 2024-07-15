package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.GrupoGabu;
import com.usco.edu.rowMapper.GrupoGabuRowMapper;

public class GrupoGabuSetExtractor implements ResultSetExtractor<List<GrupoGabu>> {

	@Override
	public List<GrupoGabu> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<GrupoGabu> list = new ArrayList<GrupoGabu>();
		while (rs.next()) {
			list.add(new GrupoGabuRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
