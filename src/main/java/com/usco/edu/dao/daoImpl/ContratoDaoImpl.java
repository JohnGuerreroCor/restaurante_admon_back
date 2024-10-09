package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IContratoDao;
import com.usco.edu.entities.Contrato;
import com.usco.edu.resultSetExtractor.ContratoSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class ContratoDaoImpl implements IContratoDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplateConsulta")
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
	    String sql = "IF NOT EXISTS ("
	            + "    SELECT 1 "
	            + "    FROM sibusco.restaurante_contrato existing "
	            + "    WHERE ? >= existing.rco_fecha_inicial AND ? <= existing.rco_fecha_final AND ? = existing.rco_uaa_codigo "
	            + "    AND existing.rco_estado = 1 "
	            + ") "
	            + "BEGIN "
	            + "    INSERT INTO sibusco.restaurante_contrato "
	            + "        (rtc_codigo, rco_fecha_inicial, rco_fecha_final, rco_valor_contrato, rco_subsidio_desayuno, rco_subsidio_almuerzo, rco_subsidio_cena, rco_pagado_estudiante_desayuno, rco_pagado_estudiante_almuerzo, rco_pagado_estudiante_cena, rco_cantidad_desayunos, rco_cantidad_almuerzos, rco_cantidad_cenas, rco_uaa_codigo, rco_estado) "
	            + "    VALUES "
	            + "        (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); "
	            + "END";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        connection.setAutoCommit(false);


	        pstmt.setDate(1, contrato.getFechaFinal());
	        pstmt.setDate(2, contrato.getFechaInicial());
	        pstmt.setLong(3, contrato.getDependencia().getCodigo());
	        pstmt.setInt(4, contrato.getTipoContrato().getCodigo());
	        pstmt.setDate(5, contrato.getFechaInicial());
	        pstmt.setDate(6, contrato.getFechaFinal());
	        pstmt.setInt(7, contrato.getValorContrato());
	        pstmt.setInt(8, contrato.getSubsidioDesayuno());
	        pstmt.setInt(9, contrato.getSubsidioAlmuerzo());
	        pstmt.setInt(10, contrato.getSubsidioCena());
	        pstmt.setInt(11, contrato.getPagoEstudianteDesayuno());
	        pstmt.setInt(12, contrato.getPagoEstudianteAlmuerzo());
	        pstmt.setInt(13, contrato.getPagoEstudianteCena());
	        pstmt.setInt(14, contrato.getCantidadDesayunos());
	        pstmt.setInt(15, contrato.getCantidadAlmuerzos());
	        pstmt.setInt(16, contrato.getCantidadCenas());
	        pstmt.setLong(17, contrato.getDependencia().getCodigo());
	        pstmt.setInt(18, contrato.getEstado());

	        pstmt.executeUpdate();

	        try (ResultSet rs = pstmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                int generatedKey = rs.getInt(1); 
	                connection.commit(); 
	                return generatedKey; 
	            }
	        }

	        connection.commit(); 
	        return 0; 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

	
	@Override
	public int actualizarContrato(String userdb, Contrato contrato) {
	    String sql = "UPDATE sibusco.restaurante_contrato "
	            + "SET rtc_codigo = ?, rco_fecha_inicial = ?, rco_fecha_final = ?, rco_valor_contrato = ?, "
	            + "rco_subsidio_desayuno = ?, rco_subsidio_almuerzo = ?, rco_subsidio_cena = ?, "
	            + "rco_pagado_estudiante_desayuno = ?, rco_pagado_estudiante_almuerzo = ?, rco_pagado_estudiante_cena = ?, "
	            + "rco_cantidad_desayunos = ?, rco_cantidad_almuerzos = ?, rco_cantidad_cenas = ?, rco_uaa_codigo = ?, "
	            + "rco_estado = ? "
	            + "WHERE rco_codigo = ?";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        connection.setAutoCommit(false); 

	        pstmt.setInt(1, contrato.getTipoContrato().getCodigo());
	        pstmt.setDate(2, contrato.getFechaInicial());
	        pstmt.setDate(3, contrato.getFechaFinal());
	        pstmt.setInt(4, contrato.getValorContrato());
	        pstmt.setInt(5, contrato.getSubsidioDesayuno());
	        pstmt.setInt(6, contrato.getSubsidioAlmuerzo());
	        pstmt.setInt(7, contrato.getSubsidioCena());
	        pstmt.setInt(8, contrato.getPagoEstudianteDesayuno());
	        pstmt.setInt(9, contrato.getPagoEstudianteAlmuerzo());
	        pstmt.setInt(10, contrato.getPagoEstudianteCena());
	        pstmt.setInt(11, contrato.getCantidadDesayunos());
	        pstmt.setInt(12, contrato.getCantidadAlmuerzos());
	        pstmt.setInt(13, contrato.getCantidadCenas());
	        pstmt.setLong(14, contrato.getDependencia().getCodigo());
	        pstmt.setInt(15, contrato.getEstado());
	        pstmt.setInt(16, contrato.getCodigo());


	        int rowsAffected = pstmt.executeUpdate();

	        connection.commit(); 
	        return rowsAffected; 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}
}
