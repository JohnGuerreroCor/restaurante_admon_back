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

import com.usco.edu.dao.IDiaHorarioServicioDao;
import com.usco.edu.entities.DiaHorarioServicio;
import com.usco.edu.resultSetExtractor.DiaHorarioServicioSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class DiaHorarioServicioDaoImpl implements IDiaHorarioServicioDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
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
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE sibusco.restaurante_dias_horario_servicio "
				+ "SET rds_estado=:estado "
				+ "WHERE rds_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("codigo", diaHorarioServicio.getCodigo());
			parameter.addValue("horarioServicio", diaHorarioServicio.getHorarioServicio().getCodigo());
			parameter.addValue("dia", diaHorarioServicio.getDia().getCodigo());
			parameter.addValue("estado", diaHorarioServicio.getEstado());

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
