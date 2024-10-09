package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IDiaBeneficioDao;
import com.usco.edu.entities.DiaBeneficio;
import com.usco.edu.resultSetExtractor.DiaBeneficioSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class DiaBeneficioDaoImpl implements IDiaBeneficioDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<DiaBeneficio> obtenerDiaBeneficio(String userdb, int idGrupoGabu) {
		String sql = "SELECT * FROM sibusco.restaurante_dias_beneficio rdb "
				+ "INNER JOIN sibusco.restaurante_grupo_gabu rgg on rdb.rgg_codigo = rgg.rgg_codigo "
				+ "INNER JOIN dbo.dia d on rdb.dia_codigo  = d.dia_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_servicio rts on rdb.rts_codigo = rts.rts_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_gabu rtg ON rgg.rtg_codigo = rtg.rtg_codigo "
				+ "INNER JOIN dbo.persona p on rgg.per_codigo = p.per_codigo " + "WHERE rdb.rgg_codigo = "
				+ idGrupoGabu;
		return jdbcTemplate.query(sql, new DiaBeneficioSetExtractor());
	}

	@Override
	public List<DiaBeneficio> obtenerDiasBeneficio(String userdb) {
		String sql = "SELECT * FROM sibusco.restaurante_dias_beneficio rdb "
				+ "INNER JOIN sibusco.restaurante_grupo_gabu rgg on rdb.rgg_codigo = rgg.rgg_codigo "
				+ "INNER JOIN dbo.dia d on rdb.dia_codigo  = d.dia_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_servicio rts on rdb.rts_codigo = rts.rts_codigo "
				+ "INNER JOIN sibusco.restaurante_tipo_gabu rtg ON rgg.rtg_codigo = rtg.rtg_codigo "
				+ "INNER JOIN dbo.persona p on rgg.per_codigo = p.per_codigo ";
		return jdbcTemplate.query(sql, new DiaBeneficioSetExtractor());
	}

	@Override
	public int actualizarDiaBeneficio(String userdb, DiaBeneficio diasBeneficio) {
	    String sql = "UPDATE sibusco.restaurante_dias_beneficio "
	            + "SET rdb_estado=? "
	            + "WHERE rgg_codigo=? "
	            + "AND dia_codigo=? "
	            + "AND rts_codigo=?";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        connection.setAutoCommit(false);

	        pstmt.setInt(1, diasBeneficio.getEstado());
	        pstmt.setInt(2, diasBeneficio.getCodigoGrupoGabu());
	        pstmt.setInt(3, diasBeneficio.getDia().getCodigo());
	        pstmt.setInt(4, diasBeneficio.getTipoServicio().getCodigo());

	        int rowsAffected = pstmt.executeUpdate();
	        connection.commit();
	        return rowsAffected;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
	    }
	}


	@Override
	public int crearDiaBeneficio(String userdb, DiaBeneficio diasBeneficio) {
	    String sql = "INSERT INTO sibusco.restaurante_dias_beneficio "
	            + "(rgg_codigo, dia_codigo, rdb_estado, rts_codigo) "
	            + "VALUES (?, ?, ?, ?)";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        connection.setAutoCommit(false);

	        pstmt.setInt(1, diasBeneficio.getCodigoGrupoGabu());
	        pstmt.setInt(2, diasBeneficio.getDia().getCodigo());
	        pstmt.setInt(3, diasBeneficio.getEstado());
	        pstmt.setInt(4, diasBeneficio.getTipoServicio().getCodigo());

	        pstmt.executeUpdate();
	        connection.commit();

	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            } else {
	                return 0;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

	@Override
	public int crearDiasBeneficio(String userdb, List<DiaBeneficio> diasBeneficio) {
	    String sql = "INSERT INTO sibusco.restaurante_dias_beneficio "
	            + "(rgg_codigo, dia_codigo, rdb_estado, rts_codigo) "
	            + "VALUES (?, ?, ?, ?)";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        connection.setAutoCommit(false);

	        int totalInserted = 0;

	        for (DiaBeneficio diaBeneficio : diasBeneficio) {
	            pstmt.setInt(1, diaBeneficio.getCodigoGrupoGabu());
	            pstmt.setInt(2, diaBeneficio.getDia().getCodigo());
	            pstmt.setInt(3, diaBeneficio.getEstado());
	            pstmt.setInt(4, diaBeneficio.getTipoServicio().getCodigo());
	            pstmt.addBatch();
	        }

	        int[] updateCounts = pstmt.executeBatch(); 
	        totalInserted = Arrays.stream(updateCounts).sum(); 

	        connection.commit(); 

	        return totalInserted;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

}
