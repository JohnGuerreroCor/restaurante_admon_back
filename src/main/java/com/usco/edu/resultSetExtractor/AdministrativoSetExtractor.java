package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Administrativo;
import com.usco.edu.rowMapper.AdministrativoRowMapper;

public class AdministrativoSetExtractor implements ResultSetExtractor<List<Administrativo>> {
	
	@Override
	public List<Administrativo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Administrativo> list = new ArrayList<Administrativo>();
		while (rs.next()) {
			list.add(new AdministrativoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
