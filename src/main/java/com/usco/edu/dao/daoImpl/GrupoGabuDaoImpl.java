package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IGrupoGabuDao;
import com.usco.edu.entities.GrupoGabu;
import com.usco.edu.resultSetExtractor.GrupoGabuSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class GrupoGabuDaoImpl implements IGrupoGabuDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public List<GrupoGabu> obtenerGrupoGabus(String userdb) {
		 
		String sql = "SELECT * FROM sibusco.restaurante_grupo_gabu rgg "
				+ "INNER JOIN sibusco.restaurante_dias_beneficio rdb ON rgg.rgg_codigo = rdb.rgg_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_gabu rtg ON rgg.rtg_codigo = rtg.rtg_codigo "
				+ "INNER JOIN dbo.persona p on rgg.per_codigo = p.per_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_servicio rts ON rdb.rts_codigo = rts.rts_codigo "
				+ "INNER JOIN dbo.estudiante e ON rgg.est_codigo = e.est_codigo "
				+ "INNER JOIN dbo.programa pr ON e.pro_codigo = pr.pro_codigo "
				+ "INNER JOIN dbo.uaa u ON pr.uaa_codigo = u.uaa_codigo "
				+ "INNER JOIN dbo.dia d on rdb.dia_codigo = d.dia_codigo "
				+ "WHERE rgg.rgg_estado = 1 "
				+ "AND rgg.rgg_vigencia >= CONVERT(DATE, GETDATE());";

		return jdbcTemplate.query(sql, new GrupoGabuSetExtractor());
	}

	@Override
	public List<GrupoGabu> obtenerGrupoGabu(String userdb, int codigo) {
		String sql = "SELECT TOP 1 * FROM sibusco.restaurante_grupo_gabu rgg "
				+ "INNER JOIN sibusco.restaurante_dias_beneficio rdb ON rgg.rgg_codigo = rdb.rgg_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_gabu rtg ON rgg.rtg_codigo = rtg.rtg_codigo "
				+ "INNER JOIN dbo.persona p on rgg.per_codigo = p.per_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_servicio rts ON rdb.rts_codigo = rts.rts_codigo "
				+ "INNER JOIN dbo.estudiante e ON rgg.est_codigo = e.est_codigo "
				+ "INNER JOIN dbo.programa pr ON e.pro_codigo = pr.pro_codigo "
				+ "INNER JOIN dbo.uaa u ON pr.uaa_codigo = u.uaa_codigo "
				+ "INNER JOIN dbo.dia d on rdb.dia_codigo = d.dia_codigo "
				+ "WHERE rgg.rgg_codigo = " + codigo + "";
		return jdbcTemplate.query(sql, new GrupoGabuSetExtractor());
	}

	@Override
	public int crearGrupoGabu(String userdb, GrupoGabu grupoGabu) {
	    String sql = "DECLARE @exists INT; " +
	                 "SELECT @exists = (SELECT COUNT(1) FROM sibusco.restaurante_grupo_gabu " +
	                 "                  WHERE per_codigo = ? AND rgg_vigencia <= ?); " +
	                 "IF (@exists = 0) " +
	                 "BEGIN " +
	                 "    INSERT INTO sibusco.restaurante_grupo_gabu (rtg_codigo, per_codigo, rgg_vigencia, rgg_estado, est_codigo) " +
	                 "    VALUES (?, ?, ?, ?, ?); " +
	                 "    SELECT SCOPE_IDENTITY(); " +
	                 "END";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        connection.setAutoCommit(false);


	        pstmt.setLong(1, grupoGabu.getPersona().getCodigo());
	        pstmt.setDate(2, grupoGabu.getVigencia());
	        pstmt.setInt(3, grupoGabu.getTipoGabu().getCodigo());
	        pstmt.setLong(4, grupoGabu.getPersona().getCodigo());
	        pstmt.setDate(5, grupoGabu.getVigencia());
	        pstmt.setInt(6, grupoGabu.getEstado());
	        pstmt.setLong(7, grupoGabu.getCodigoEstudiante());


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
	public int actualizarGrupoGabu(String userdb, GrupoGabu grupoGabu) {
	    String sql = "UPDATE sibusco.restaurante_grupo_gabu " +
	                 "SET rtg_codigo = ?, per_codigo = ?, rgg_vigencia = ?, rgg_estado = ? " +
	                 "WHERE rgg_codigo = ? " +
	                 "AND NOT EXISTS ( " +
	                 "    SELECT 1 FROM sibusco.restaurante_grupo_gabu rgg " +
	                 "    WHERE rgg.per_codigo = ? " +
	                 "    AND rgg.rgg_vigencia <= ? " +
	                 "    AND rgg.rgg_codigo <> ? " +
	                 ")";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        connection.setAutoCommit(false);

	        pstmt.setInt(1, grupoGabu.getTipoGabu().getCodigo());
	        pstmt.setLong(2, grupoGabu.getPersona().getCodigo());
	        pstmt.setDate(3, grupoGabu.getVigencia());
	        pstmt.setInt(4, grupoGabu.getEstado());
	        pstmt.setInt(5, grupoGabu.getCodigo());
	        pstmt.setLong(6, grupoGabu.getPersona().getCodigo());
	        pstmt.setDate(7, grupoGabu.getVigencia());
	        pstmt.setInt(8, grupoGabu.getCodigo());

	        int updatedRows = pstmt.executeUpdate();

	        connection.commit(); 
	        return updatedRows; 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

}
