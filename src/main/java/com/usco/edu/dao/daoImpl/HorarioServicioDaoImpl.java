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

import com.usco.edu.dao.IHorarioServicioDao;
import com.usco.edu.entities.HorarioServicio;
import com.usco.edu.resultSetExtractor.HorarioServicioSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class HorarioServicioDaoImpl implements IHorarioServicioDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<HorarioServicio> obtenerHorariosServicio(String userdb) {
		String sql = "SELECT * FROM sibusco.restaurante_horario_servicio rhs "
				+ "LEFT JOIN sibusco.restaurante_tipo_servicio rts ON rts.rts_codigo = rhs.rhs_codigo "
				+ "LEFT JOIN dbo.uaa ua ON ua.uaa_codigo = rhs.rhs_uaa_codigo";

		return jdbcTemplate.query(sql, new HorarioServicioSetExtractor());
	}

	@Override
	public List<HorarioServicio> obtenerHorarioServicio(String userdb, int codigo) {
		String sql = "SELECT * FROM sibusco.restaurante_horario_servicio rhs "
				+ "INNER JOIN sibusco.restaurante_tipo_servicio rts ON rts.rts_codigo = rhs.rhs_codigo "
				+ "WHERE rec.rco_codigo = " + codigo + "";
		return jdbcTemplate.query(sql, new HorarioServicioSetExtractor());
	}

	@Override
	public int crearHorarioServicio(String userdb, HorarioServicio horarioServicio) {
	    String sql = "INSERT INTO sibusco.restaurante_horario_servicio "
	            + "(rhs_hora_inicio_venta, rhs_hora_fin_venta, rhs_hora_inicio_atencion, rhs_hora_fin_atencion, rts_codigo, rhs_uaa_codigo, rhs_estado, rhs_observacion_estado, rhs_fecha_estado, rhs_cantidad_comidas, rhs_cantidad_tiquetes) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        connection.setAutoCommit(false);

	        pstmt.setTime(1, horarioServicio.getHoraIncioVenta());
	        pstmt.setTime(2, horarioServicio.getHoraFinVenta());
	        pstmt.setTime(3, horarioServicio.getHoraInicioAtencion());
	        pstmt.setTime(4, horarioServicio.getHoraFinAtencion());
	        pstmt.setInt(5, horarioServicio.getTipoServicio().getCodigo());
	        pstmt.setLong(6, horarioServicio.getUaa().getCodigo());
	        pstmt.setInt(7, horarioServicio.getEstado());
	        pstmt.setString(8, horarioServicio.getObservacionEstado());
	        pstmt.setDate(9, horarioServicio.getFechaEstado());
	        pstmt.setInt(10, horarioServicio.getCantidadComidas());
	        pstmt.setInt(11, horarioServicio.getCantidadTiquetes());

	        int rowsAffected = pstmt.executeUpdate();

	        try (ResultSet rs = pstmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                int generatedId = rs.getInt(1);
	                connection.commit(); 
	                return generatedId; 
	            } else {
	                connection.rollback(); 
	                return 0;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}

	@Override
	public int actualizarHorarioServicio(String userdb, HorarioServicio horarioServicio) {
	    String sql = "UPDATE sibusco.restaurante_horario_servicio "
	            + "SET rhs_hora_inicio_venta=?, "
	            + "rhs_hora_fin_venta=?, "
	            + "rhs_hora_inicio_atencion=?, "
	            + "rhs_hora_fin_atencion=?, "
	            + "rhs_uaa_codigo=?, "
	            + "rhs_estado=?, "
	            + "rhs_observacion_estado=?, "
	            + "rhs_fecha_estado=?, "
	            + "rhs_cantidad_comidas=?, "
	            + "rhs_cantidad_ventas_permitidas=?, "
	            + "rhs_cantidad_tiquetes=? "
	            + "WHERE rhs_codigo=?";

	    try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        connection.setAutoCommit(false);

	        pstmt.setTime(1, horarioServicio.getHoraIncioVenta());
	        pstmt.setTime(2, horarioServicio.getHoraFinVenta());
	        pstmt.setTime(3, horarioServicio.getHoraInicioAtencion());
	        pstmt.setTime(4, horarioServicio.getHoraFinAtencion());
	        pstmt.setLong(5, horarioServicio.getUaa().getCodigo());
	        pstmt.setInt(6, horarioServicio.getEstado());
	        pstmt.setString(7, horarioServicio.getObservacionEstado());
	        pstmt.setDate(8, horarioServicio.getFechaEstado());
	        pstmt.setInt(9, horarioServicio.getCantidadComidas());
	        pstmt.setInt(10, horarioServicio.getCantidadVentas());
	        pstmt.setInt(11, horarioServicio.getCantidadTiquetes());
	        pstmt.setInt(12, horarioServicio.getCodigo());

	        int rowsAffected = pstmt.executeUpdate();
	        connection.commit();
	        return rowsAffected;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

}
