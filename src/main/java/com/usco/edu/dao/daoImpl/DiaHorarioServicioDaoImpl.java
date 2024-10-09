package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IDiaHorarioServicioDao;
import com.usco.edu.entities.DiaHorarioServicio;
import com.usco.edu.resultSetExtractor.DiaHorarioServicioSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class DiaHorarioServicioDaoImpl implements IDiaHorarioServicioDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;
	

	@Override
	public List<DiaHorarioServicio> obtenerDiasHorarioServicio(String userdb) {
		String sql = "SELECT * FROM sibusco.restaurante_dias_horario_servicio rdhs "
				+ "LEFT JOIN sibusco.restaurante_horario_servicio rhs ON rhs.rhs_codigo = rdhs.rhs_codigo "
				+ "LEFT JOIN sibusco.restaurante_tipo_servicio rts ON rts.rts_codigo = rhs.rts_codigo "
				+ "LEFT JOIN dbo.dia d ON d.dia_codigo = rdhs.dia_codigo "
				+ "LEFT JOIN dbo.uaa u ON u.uaa_codigo = rhs.rhs_uaa_codigo";

		return jdbcTemplate.query(sql, new DiaHorarioServicioSetExtractor());
	}

	@Override
	public int actualizarDiaHorarioServicio(String userdb, DiaHorarioServicio diaHorarioServicio) {
	    String sql = "UPDATE sibusco.restaurante_dias_horario_servicio "
	            + "SET rds_estado=? "
	            + "WHERE rds_codigo=?";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        connection.setAutoCommit(false);

	        pstmt.setInt(1, diaHorarioServicio.getEstado());
	        pstmt.setInt(2, diaHorarioServicio.getCodigo());
	        int rowsAffected = pstmt.executeUpdate();
	        connection.commit();
	        return rowsAffected;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

}
