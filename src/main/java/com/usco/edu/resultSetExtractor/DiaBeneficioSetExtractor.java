package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.DiaBeneficio;
import com.usco.edu.rowMapper.DiaBeneficioRowMapper;

public class DiaBeneficioSetExtractor implements ResultSetExtractor<List<DiaBeneficio>> {

	@Override
	public List<DiaBeneficio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<DiaBeneficio> list = new ArrayList<DiaBeneficio>();
		while (rs.next()) {
			list.add(new DiaBeneficioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
