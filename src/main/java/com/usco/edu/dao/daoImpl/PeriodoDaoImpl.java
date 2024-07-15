package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IPeriodoDao;
import com.usco.edu.entities.Periodo;
import com.usco.edu.resultSetExtractor.PeriodoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class PeriodoDaoImpl implements IPeriodoDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Periodo> obtenerPeriodos(String userdb) {
		String sql = "SELECT * FROM dbo.periodo";
		return jdbcTemplate.query(sql, new PeriodoSetExtractor());
	}

	@Override
	public List<Periodo> obtenerPeriodo(String userdb, int codigo) {

		String sql = "SELECT * FROM dbo.periodo per " + "WHERE per.per_codigo = " + codigo + "";

		return jdbcTemplate.query(sql, new PeriodoSetExtractor());

	}

}
