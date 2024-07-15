package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.TipoGabu;
import com.usco.edu.rowMapper.TipoGabuRowMapper;

public class TipoGabuSetExtractor implements ResultSetExtractor<List<TipoGabu>> {

	@Override
	public List<TipoGabu> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<TipoGabu> list = new ArrayList<TipoGabu>();
		while (rs.next()) {
			list.add(new TipoGabuRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
