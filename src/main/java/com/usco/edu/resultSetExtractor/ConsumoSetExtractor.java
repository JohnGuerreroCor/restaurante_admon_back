package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Consumo;
import com.usco.edu.rowMapper.ConsumoRowMapper;

public class ConsumoSetExtractor implements ResultSetExtractor<List<Consumo>> {

	@Override
	public List<Consumo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Consumo> list = new ArrayList<Consumo>();
		while (rs.next()) {
			list.add(new ConsumoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
