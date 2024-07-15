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

import com.usco.edu.dao.IRolDao;
import com.usco.edu.entities.Rol;
import com.usco.edu.resultSetExtractor.RolSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class RolDaoImpl implements IRolDao {
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<Rol> rol(String userdb) {
		String sql = "select * from acreditacion.rol_comite_autoevaluacion where rca_estado = 1 ORDER by rca_estamento";
		return jdbcTemplate.query(sql, new RolSetExtractor());
	}
	
	@Override
	public List<Rol> rolByEstamento(String userdb, String estamento) {
		String sql = "select * from acreditacion.rol_comite_autoevaluacion where rca_estamento = '" + estamento  + "' and rca_estado = 1 ORDER by rca_codigo";
		return jdbcTemplate.query(sql, new RolSetExtractor());
	}


	@Override
	public int createRol(String userdb, Rol r) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO acreditacion.rol_comite_autoevaluacion (rca_nombre, rca_estamento, rca_descripcion) VALUES(:nombre,:estamento,:descripcion);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", r.getNombre());
			parameter.addValue("estamento", r.getEstamento());
			parameter.addValue("descripcion", r.getDescripcion());
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
	public int updateRol(String userdb, Rol r) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		String sql = "UPDATE acreditacion.rol_comite_autoevaluacion SET rca_nombre=:nombre, rca_estamento=:estamento, rca_descripcion=:descripcion, rca_estado=:estado WHERE rca_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", r.getCodigo());
			parameter.addValue("nombre", r.getNombre());
			parameter.addValue("estamento", r.getEstamento());
			parameter.addValue("descripcion", r.getDescripcion());
			parameter.addValue("estado", r.getEstado());

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
