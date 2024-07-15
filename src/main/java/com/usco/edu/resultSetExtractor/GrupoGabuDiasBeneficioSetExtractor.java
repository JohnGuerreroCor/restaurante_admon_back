package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;

import com.usco.edu.entities.GrupoGabuDiasBeneficio;
import com.usco.edu.rowMapper.GrupoGabuDiasBeneficioRowMapper;

public class GrupoGabuDiasBeneficioSetExtractor implements ResultSetExtractor<List<GrupoGabuDiasBeneficio>> {

	@Override
	@Nullable
	public List<GrupoGabuDiasBeneficio> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<GrupoGabuDiasBeneficio> list = new ArrayList<GrupoGabuDiasBeneficio>();
		while (rs.next()) {
			list.add(new GrupoGabuDiasBeneficioRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}
