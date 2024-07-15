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
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPoliticaDao;
import com.usco.edu.entities.PoliticaEstamento;
import com.usco.edu.resultSetExtractor.PoliticaEstamentoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class PoliticaDaoImpl implements IPoliticaDao {
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<PoliticaEstamento> obtenerPoliticaEstamento(String userdb) {
		String sql = "select * from carnetizacion.politica_estamento pe "
				+ "inner join dbo.usuario_tipo u on pe.tus_codigo = u.tus_codigo";
		return jdbcTemplate.query(sql, new PoliticaEstamentoSetExtractor());
	}
	
	@Override
	public List<PoliticaEstamento> obtenerPoliticaPorCodigoEstamento(int codigo, String userdb) {
		String sql = "select * from carnetizacion.politica_estamento pe "
				+ "inner join dbo.usuario_tipo u on pe.tus_codigo = u.tus_codigo "
				+ "where pe.tus_codigo = " + codigo + "";
		return jdbcTemplate.query(sql, new PoliticaEstamentoSetExtractor());
	}

	@Override
	public int actualizar(String userdb, PoliticaEstamento politica) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE carnetizacion.politica_estamento "
				+ "SET poe_fecha_modificacion=:fechaModificacion, poe_descripcion=:descripcion "
				+ "WHERE poe_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("codigo", politica.getCodigo());
			parameter.addValue("fechaModificacion", politica.getFechaModificacion());
			parameter.addValue("descripcion", politica.getDescripcion());

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
