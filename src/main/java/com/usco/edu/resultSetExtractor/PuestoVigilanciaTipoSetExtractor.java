package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.PuestoVigilanciaTipo;
import com.usco.edu.rowMapper.PuestoVigilanciaTipoRowMapper;

public class PuestoVigilanciaTipoSetExtractor implements ResultSetExtractor<List<PuestoVigilanciaTipo>> {
	
	@Override
	public List<PuestoVigilanciaTipo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<PuestoVigilanciaTipo> list = new ArrayList<PuestoVigilanciaTipo>();
		while (rs.next()) {
			list.add(new PuestoVigilanciaTipoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}
}
