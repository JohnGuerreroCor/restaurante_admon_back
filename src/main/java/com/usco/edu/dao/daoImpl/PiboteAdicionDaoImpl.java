package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPiboteAdicionDao;
import com.usco.edu.entities.PiboteAdicion;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class PiboteAdicionDaoImpl implements IPiboteAdicionDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public int crearPibote(String userdb, PiboteAdicion piboteAdicion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO sibusco.restaurante_pibote_adicion "
				+ "(rco_codigo_general, rco_codigo_adicion, rpa_estado) " 
				+ "VALUES(:contratoGeneral, :contratoAdicion, :estado);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("contratoGeneral", piboteAdicion.getCodigoGeneral());
			parameter.addValue("contratoAdicion", piboteAdicion.getCodigoAdicion());
			parameter.addValue("estado", piboteAdicion.getEstado());

			jdbc.update(sql, parameter, keyHolder);
			return keyHolder.getKey().intValue();

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public int actualizarPibote(String userdb, PiboteAdicion piboteAdicion) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE sibusco.restaurante_pibote_adicion rpa "
				+ "SET rpa.rpa_estado=:estado "
				+ "WHERE rpa.rco_codigo_general = :contratoGeneral "
				+ "AND rpa.rco_codigo_adicion = :contratoAdicion;";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("contratoGeneral", piboteAdicion.getCodigoGeneral());
			parameter.addValue("contratoAdicion", piboteAdicion.getCodigoAdicion());
			parameter.addValue("estado", piboteAdicion.getEstado());
			
			return jdbc.update(sql, parameter);
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void cerrarConexion(Connection con) {
		if (con == null)
			return;

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
