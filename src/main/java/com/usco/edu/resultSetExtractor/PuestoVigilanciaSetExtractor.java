package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.PuestoVigilancia;
import com.usco.edu.rowMapper.PuestoVigilanciaRowMapper;

public class PuestoVigilanciaSetExtractor implements ResultSetExtractor<List<PuestoVigilancia>> {
	
	@Override
	public List<PuestoVigilancia> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<PuestoVigilancia> list = new ArrayList<PuestoVigilancia>();
		while (rs.next()) {
			list.add(new PuestoVigilanciaRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}
}
