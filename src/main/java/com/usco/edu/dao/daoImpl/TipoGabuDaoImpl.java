package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ITipoGabuDao;
import com.usco.edu.entities.TipoGabu;
import com.usco.edu.resultSetExtractor.TipoGabuSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class TipoGabuDaoImpl implements ITipoGabuDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<TipoGabu> obtenerTiposGabu(String userdb) {
		String sql = "SELECT * FROM sibusco.restaurante_tipo_gabu";
		return jdbcTemplate.query(sql, new TipoGabuSetExtractor());
	}

	@Override
	public int actualizarTipoGabu(String userdb, TipoGabu tipoGabu) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE sibusco.restaurante_tipo_gabu " + "SET rtg_nombre=:nombre, rtg_estado=:estado "
				+ "WHERE rtg_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("codigo", tipoGabu.getCodigo());
			parameter.addValue("nombre", tipoGabu.getNombre());
			parameter.addValue("estado", tipoGabu.getEstado());

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

	@Override
	public int crearTipoGabu(String userdb, TipoGabu tipoGabu) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO sibusco.restaurante_tipo_gabu " + "(rtg_nombre) " + "VALUES(:nombre);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("nombre", tipoGabu.getNombre());

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
