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

import com.usco.edu.dao.ITerceroDao;
import com.usco.edu.entities.Tercero;
import com.usco.edu.resultSetExtractor.TerceroSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class TerceroDaoImpl implements ITerceroDao {
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Tercero> obtenerTerceroId(String id, String userdb) {

		String sql = "select * from dbo.tercero t where t.ter_identificacion = '" + id + "' and t.ter_borrado = 0";
		return jdbcTemplate.query(sql, new TerceroSetExtractor());
	}


	@Override
	public int registrar(String userdb, Tercero tercero) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO dbo.tercero "
				+ "(tii_codigo, ter_identificacion, ter_nombre, ter_email, ter_apellido1, ter_apellido2, ter_nombre1, ter_nombre2, ter_borrado, ter_fecha) "
				+ "VALUES(:tipoDocumento, :identificacion, :nombreCompleto, :email, :apellido1, :apellido2, :nombre1, :nombre2, :estado, :fechaRegistro);";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("tipoDocumento", tercero.getTipoDocumento());
			parameter.addValue("identificacion", tercero.getIdentificacion());
			parameter.addValue("nombreCompleto", tercero.getNombreCompleto());
			parameter.addValue("email", tercero.getEmail());
			parameter.addValue("apellido1", tercero.getApellido1());
			parameter.addValue("apellido2", tercero.getApellido2());
			parameter.addValue("nombre1", tercero.getNombre1());
			parameter.addValue("nombre2", tercero.getNombre2());
			parameter.addValue("estado", tercero.getEstado());
			parameter.addValue("fechaRegistro", tercero.getFechaRegistro());

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
	public int actualizar(String userdb, Tercero tercero) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE dbo.tercero "
				+ "SET ter_email=:email "
				+ "WHERE ter_codigo=:codigo;";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("codigo", tercero.getCodigo());
			parameter.addValue("email", tercero.getEmail());

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