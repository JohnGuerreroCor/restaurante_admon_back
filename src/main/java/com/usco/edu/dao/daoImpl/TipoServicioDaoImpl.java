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

import com.usco.edu.dao.ITipoServicioDao;
import com.usco.edu.entities.TipoServicio;
import com.usco.edu.resultSetExtractor.TipoServicioSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class TipoServicioDaoImpl implements ITipoServicioDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<TipoServicio> obtenerTiposServicio(String userdb) {
		String sql = "SELECT * FROM sibusco.restaurante_tipo_servicio";
		return jdbcTemplate.query(sql, new TipoServicioSetExtractor());
	}

	@Override
	public int actualizarTipoServicio(String userdb, TipoServicio tipoServicio) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE sibusco.restaurante_tipo_servicio " + "SET rts_nombre=:nombre, rts_estado=:estado "
				+ "WHERE rts_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("codigo", tipoServicio.getCodigo());
			parameter.addValue("nombre", tipoServicio.getNombre());
			parameter.addValue("estado", tipoServicio.getEstado());

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
	public int crearTipoServicio(String userdb, TipoServicio tipoServicio) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO sibusco.restaurante_tipo_servicio " + "(rts_nombre) " + "VALUES(:nombre);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("nombre", tipoServicio.getNombre());

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
