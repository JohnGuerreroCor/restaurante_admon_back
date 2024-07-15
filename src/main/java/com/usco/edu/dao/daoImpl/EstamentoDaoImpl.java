package com.usco.edu.dao.daoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IEstamentoDao;
import com.usco.edu.entities.Estamento;
import com.usco.edu.resultSetExtractor.EstamentoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class EstamentoDaoImpl implements IEstamentoDao {
	

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Estamento> estamentos(String userdb) {

		String sql = "select * from usuario_tipo where tus_estado = 1";
		return jdbcTemplate.query(sql, new EstamentoSetExtractor());

	}

}
