package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Periodo;

import com.usco.edu.rowMapper.PeriodoRowMapper;

public class PeriodoSetExtractor implements ResultSetExtractor<List<Periodo>> {

	@Override
	public List<Periodo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Periodo> list = new ArrayList<Periodo>();
		while (rs.next()) {
			list.add(new PeriodoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
