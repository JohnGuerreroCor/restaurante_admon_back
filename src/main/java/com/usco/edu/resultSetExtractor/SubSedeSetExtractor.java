package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.SubSede;
import com.usco.edu.rowMapper.SubSedeRowMapper;

public class SubSedeSetExtractor implements ResultSetExtractor<List<SubSede>>{

	@Override
	public List<SubSede> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<SubSede> list = new ArrayList<SubSede>();
		while (rs.next()) {
			list.add(new SubSedeRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		
		return list;
	}

}
