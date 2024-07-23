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

import com.usco.edu.dao.IConsumoDao;
import com.usco.edu.entities.Consumo;
import com.usco.edu.resultSetExtractor.ConsumoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class ConsumoDaoImpl implements IConsumoDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Consumo> obtenerConsumos(String userdb) {
		String sql = "SELECT *  FROM sibusco.restaurante_consumo rcn "
				+ "INNER JOIN sibusco.restaurante_venta rve ON rcn.rve_codigo = rve.rve_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_servicio rts ON rve.rts_codigo = rts.rts_codigo "
				+ "INNER JOIN sibusco.restaurante_contrato rco ON rve.rco_codigo = rco.rco_codigo";
		return jdbcTemplate.query(sql, new ConsumoSetExtractor());
	}

	@Override
	public List<Consumo> obtenerConsumo(String userdb, int codigo) {
		String sql = "SELECT *  FROM sibusco.restaurante_consumo rcn "
				+ "INNER JOIN sibusco.restaurante_venta rve ON rcn.rve_codigo = rve.rve_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_servicio rts ON rve.rts_codigo = rts.rts_codigo "
				+ "INNER JOIN sibusco.restaurante_contrato rco ON rve.rco_codigo = rco.rco_codigo"
				+ "WHERE rcn.rcn_codigo = " + codigo + "";

		return jdbcTemplate.query(sql, new ConsumoSetExtractor());
	}

	@Override
	public int crearConsumo(String userdb, Consumo consumo) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO sibusco.restaurante_consumo "
				+ "(per_codigo, rve_codigo, rts_codigo, rco_codigo, rcn_fecha_hora, uaa_codigo, rcn_usuario, rcn_estado) "
				+ "VALUES(:persona, :venta, :tipoServicio, :contrato, :fechaHora , :uaa, :usuario, :estado );";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("persona", consumo.getPersona().getCodigo());
			parameter.addValue("venta", consumo.getVenta().getCodigo());
			parameter.addValue("tipoServicio", consumo.getTipoServicio().getCodigo());
			parameter.addValue("contrato", consumo.getContrato().getCodigo());
			parameter.addValue("fechaHora", consumo.getFechaHora());
			parameter.addValue("uaa", consumo.getUaa().getCodigo());
			parameter.addValue("usuario", consumo.getUsuario().getCodigo());
			parameter.addValue("estado", consumo.getEstado());

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
	public int actualizarConsumo(String userdb, Consumo consumo) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE sibusco.restaurante_consumo "
				+ "SET per_codigo=:persona, rve_codigo=:venta, rts_codigo=:tipoServicio, rco_codigo=:contrato, rcn_fecha_hora=:fechaHora, uaa_codigo=:uaa, rcn_usuario=:usuario, rcn_estado=:estado "
				+ "WHERE rcn_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("codigo", consumo.getCodigo());
			parameter.addValue("persona", consumo.getPersona().getCodigo());
			parameter.addValue("venta", consumo.getVenta().getCodigo());
			parameter.addValue("tipoServicio", consumo.getTipoServicio().getCodigo());
			parameter.addValue("contrato", consumo.getContrato().getCodigo());
			parameter.addValue("fechaHora", consumo.getFechaHora());
			parameter.addValue("uaa", consumo.getUaa().getCodigo());
			parameter.addValue("usuario", consumo.getUsuario().getCodigo());
			parameter.addValue("estado", consumo.getEstado());

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
