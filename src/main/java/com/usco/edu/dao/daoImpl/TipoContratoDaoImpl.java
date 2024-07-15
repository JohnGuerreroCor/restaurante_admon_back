package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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

import com.usco.edu.dao.ITipoContratoDao;
import com.usco.edu.entities.TipoContrato;
import com.usco.edu.util.AuditoriaJdbcTemplate;
import com.usco.edu.resultSetExtractor.TipoContratoSetExtractor;

@Repository
public class TipoContratoDaoImpl implements ITipoContratoDao {
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public List<TipoContrato> obtenerTiposContrato(String userdb) {
		String sql ="SELECT * FROM sibusco.restaurante_tipo_contrato";
		return jdbcTemplate.query(sql, new TipoContratoSetExtractor());
	}

	@Override
	public int actualizarTipoContrato(String userdb, TipoContrato tipoContrato) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE sibusco.restaurante_tipo_contrato "
				+ "SET rtc_nombre=:nombre, rtc_descripcion=:descripcion, rtc_estado=:estado "
				+ "WHERE rtc_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("codigo", tipoContrato.getCodigo());
			parameter.addValue("nombre", tipoContrato.getNombre());
			parameter.addValue("descripcion", tipoContrato.getDescripcion());
			parameter.addValue("estado", tipoContrato.getEstado());

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
	public int crearTipoContrato(String userdb, TipoContrato tipoContrato) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO sibusco.restaurante_tipo_contrato "
				+ "(rtc_nombre, rtc_descripcion) "
				+ "VALUES(:nombre, :descripcion);";
		
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
		
			//parameter.addValue("codigo", tipoContrato.getCodigo());
			parameter.addValue("nombre", tipoContrato.getNombre());
			parameter.addValue("descripcion", tipoContrato.getDescripcion());
			//parameter.addValue("estado", tipoContrato.getEstado());

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
