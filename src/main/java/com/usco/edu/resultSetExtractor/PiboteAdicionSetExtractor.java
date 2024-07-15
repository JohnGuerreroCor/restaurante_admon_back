package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;

import com.usco.edu.entities.PiboteAdicion;
import com.usco.edu.rowMapper.PiboteAdicionRowMapper;

public class PiboteAdicionSetExtractor implements ResultSetExtractor<List<PiboteAdicion>> {

	@Override
	@Nullable
	public List<PiboteAdicion> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<PiboteAdicion> list = new ArrayList<PiboteAdicion>();
		while (rs.next()) {
			list.add(new PiboteAdicionRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
