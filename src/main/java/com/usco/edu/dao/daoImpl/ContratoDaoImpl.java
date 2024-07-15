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

import com.usco.edu.dao.IContratoDao;
import com.usco.edu.entities.Contrato;
import com.usco.edu.resultSetExtractor.ContratoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class ContratoDaoImpl implements IContratoDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Contrato> obtenerContratos(String userdb) {
		String sql = "SELECT *  FROM sibusco.restaurante_contrato rec "
				+ "INNER JOIN sibusco.restaurante_tipo_contrato rtc ON rec.rtc_codigo = rtc.rtc_codigo "
				+ "INNER JOIN dbo.uaa u ON rec.rco_uaa_codigo = u.uaa_codigo "
				+ "WHERE rec.rco_estado <> 0 ";
		return jdbcTemplate.query(sql, new ContratoSetExtractor());
	}

	@Override
	public List<Contrato> obtenerContrato(String userdb, int codigo) {
		String sql = "SELECT *  FROM sibusco.restaurante_contrato rec "
				+ "INNER JOIN sibusco.restaurante_tipo_contrato rtc ON rec.rtc_codigo = rtc.rtc_codigo "
				+ "INNER JOIN dbo.uaa u ON rec.rco_uaa_codigo = u.uaa_codigo " + "WHERE rec.rco_codigo = " + codigo
				+ "";

		return jdbcTemplate.query(sql, new ContratoSetExtractor());
	}

	/**
	@Override
	public List<Contrato> obtenerContratosByVigencia(String userdb, int codigoUaa, String fecha) {
		String sql = "SELECT * FROM sibusco.restaurante_contrato rc "
				+ "INNER JOIN sibusco.restaurante_tipo_contrato rtc ON rtc.rtc_codigo  = rc.rtc_codigo "
				+ "INNER JOIN dbo.uaa u ON u.uaa_codigo = rc.rco_uaa_codigo "
				+ "WHERE rc.rco_uaa_codigo = " + codigoUaa + " "
				+ "AND CONVERT(DATE," + "'" + fecha + "'" + ") BETWEEN rc.rco_fecha_inicial AND rc.rco_fecha_final;";

		return jdbcTemplate.query(sql, new ContratoSetExtractor());
	}
**/
	@Override
	public int crearContrato(String userdb, Contrato contrato) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "IF NOT EXISTS ("
				+ "    SELECT 1"
				+ "    FROM sibusco.restaurante_contrato existing "
				+ "    WHERE :fechaFinal >= existing.rco_fecha_inicial AND :fechaInicial <= existing.rco_fecha_final AND :uaa = existing.rco_uaa_codigo "
				+ "    AND existing.rco_estado = 1 "
				+ ") "
				+ "BEGIN "
				+ "    INSERT INTO sibusco.restaurante_contrato "
				+ "        (rtc_codigo, rco_fecha_inicial, rco_fecha_final, rco_valor_contrato, rco_subsidio_desayuno, rco_subsidio_almuerzo, rco_subsidio_cena, rco_pagado_estudiante_desayuno, rco_pagado_estudiante_almuerzo, rco_pagado_estudiante_cena, rco_cantidad_desayunos, rco_cantidad_almuerzos, rco_cantidad_cenas, rco_uaa_codigo, rco_estado) "
				+ "    VALUES "
				+ "        (:tipoContrato, :fechaInicial, :fechaFinal, :valorContrato, :subsidioDesayuno, :subsidioAlmuerzo, :subsidioCena, :pagoEstudianteDesayuno, :pagoEstudianteAlmuerzo, :pagoEstudianteCena, :cantidadDesayunos, :cantidadAlmuerzos, :cantidadCenas, :uaa, :estado) "
				+ "END;";
		
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("tipoContrato", contrato.getTipoContrato().getCodigo());
			parameter.addValue("fechaInicial", contrato.getFechaInicial());
			parameter.addValue("fechaFinal", contrato.getFechaFinal());
			parameter.addValue("valorContrato", contrato.getValorContrato());
			parameter.addValue("subsidioDesayuno", contrato.getSubsidioDesayuno());
			parameter.addValue("subsidioAlmuerzo", contrato.getSubsidioAlmuerzo());
			parameter.addValue("subsidioCena", contrato.getSubsidioCena());
			parameter.addValue("pagoEstudianteDesayuno", contrato.getPagoEstudianteDesayuno());
			parameter.addValue("pagoEstudianteAlmuerzo", contrato.getPagoEstudianteAlmuerzo());
			parameter.addValue("pagoEstudianteCena", contrato.getPagoEstudianteCena());
			parameter.addValue("cantidadDesayunos", contrato.getCantidadDesayunos());
			parameter.addValue("cantidadAlmuerzos", contrato.getCantidadAlmuerzos());
			parameter.addValue("cantidadCenas", contrato.getCantidadCenas());
			parameter.addValue("uaa", contrato.getDependencia().getCodigo());
			parameter.addValue("estado", contrato.getEstado());

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
	public int actualizarContrato(String userdb, Contrato contrato) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE sibusco.restaurante_contrato "
				+ "SET rtc_codigo=:tipoContrato, rco_fecha_inicial=:fechaInicial, rco_fecha_final=:fechaFinal, rco_valor_contrato=:valorContrato, rco_subsidio_desayuno=:subsidioDesayuno, rco_subsidio_almuerzo=:subsidioAlmuerzo, rco_subsidio_cena=:subsidioCena, rco_pagado_estudiante_desayuno=:pagoEstudianteDesayuno, rco_pagado_estudiante_almuerzo=:pagoEstudianteAlmuerzo, rco_pagado_estudiante_cena=:pagoEstudianteCena, rco_cantidad_desayunos=:cantidadDesayunos, rco_cantidad_almuerzos=:cantidadAlmuerzos, rco_cantidad_cenas=:cantidadCenas, rco_uaa_codigo=:uaa, rco_estado=:estado "
				+ "WHERE rco_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("codigo", contrato.getCodigo());
			parameter.addValue("tipoContrato", contrato.getTipoContrato().getCodigo());
			parameter.addValue("fechaInicial", contrato.getFechaInicial());
			parameter.addValue("fechaFinal", contrato.getFechaFinal());
			parameter.addValue("valorContrato", contrato.getValorContrato());
			parameter.addValue("subsidioDesayuno", contrato.getSubsidioDesayuno());
			parameter.addValue("subsidioAlmuerzo", contrato.getSubsidioAlmuerzo());
			parameter.addValue("subsidioCena", contrato.getSubsidioCena());
			parameter.addValue("pagoEstudianteDesayuno", contrato.getPagoEstudianteDesayuno());
			parameter.addValue("pagoEstudianteAlmuerzo", contrato.getPagoEstudianteAlmuerzo());
			parameter.addValue("pagoEstudianteCena", contrato.getPagoEstudianteCena());
			parameter.addValue("cantidadDesayunos", contrato.getCantidadDesayunos());
			parameter.addValue("cantidadAlmuerzos", contrato.getCantidadAlmuerzos());
			parameter.addValue("cantidadCenas", contrato.getCantidadCenas());
			parameter.addValue("uaa", contrato.getDependencia().getCodigo());
			parameter.addValue("estado", contrato.getEstado());

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
