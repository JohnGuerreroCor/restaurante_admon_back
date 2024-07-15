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

import com.usco.edu.dao.IHorarioDao;
import com.usco.edu.entities.Dia;
import com.usco.edu.entities.Hora;
import com.usco.edu.entities.Horario;
import com.usco.edu.resultSetExtractor.DiaSetExtractor;
import com.usco.edu.resultSetExtractor.HoraSetExtractor;
import com.usco.edu.resultSetExtractor.HorarioSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class HorarioDaoImpl implements IHorarioDao {
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Dia> obtenerDias(String userdb) {
		
		String sql = "select d.dia_codigo, RTRIM(d.dia_nombre) as dia_nombre, d.dia_posicion_semana from dbo.dia d where d.dia_codigo != 8";
		return jdbcTemplate.query(sql, new DiaSetExtractor());
		
	}

	@Override
	public List<Hora> obtenerHoras(String userdb) {
		
		String sql = "select * from dbo.hora h ";
		return jdbcTemplate.query(sql, new HoraSetExtractor());
		
	}
	
	@Override
	public List<Horario> obtenerHorarios(String userdb) {
		
		String sql = "select * from carnetizacion.horario_puesto_vigilancia hpv "
				+ "inner join carnetizacion.puesto_vigilancia pv on hpv.puv_codigo = pv.puv_codigo "
				+ "inner join sede s on pv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on pv.sus_codigo = ss.sus_codigo "
				+ "inner join bloque b on pv.blo_codigo = b.blo_codigo "
				+ "inner join carnetizacion.puesto_vigilancia_tipo pvt on pv.pvt_codigo = pvt.pvt_codigo "
				+ "inner join dia d on hpv.dia_codigo = d.dia_codigo "
				+ "ORDER BY hpv.puv_codigo, hpv.dia_codigo";
		return jdbcTemplate.query(sql, new HorarioSetExtractor());
		
	}

	@Override
	public int registrar(String userdb, Horario horario) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO carnetizacion.horario_puesto_vigilancia "
				+ "(puv_codigo, hpv_hora_apertura, hpv_hora_cierre, dia_codigo) "
				+ "VALUES(:puesto, :horaApertura, :horaCierre, :dia);";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("puesto", horario.getPuestoVigilancia().getCodigo());
			parameter.addValue("horaApertura", horario.getHoraApertura());
			parameter.addValue("horaCierre", horario.getHoraCierre());
			parameter.addValue("dia", horario.getDia().getCodigo());

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
	public int actualizar(String userdb, Horario horario) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE carnetizacion.horario_puesto_vigilancia "
				+ "SET puv_codigo=:puesto, hpv_hora_apertura=:horaApertura, hpv_hora_cierre=:horaCierre, hpv_fecha_creacion=:bloqueCodigo, "
				+ "puv_fecha_cierre=:fechaCierre, dia_codigo=:dia, hpv_estado=:estado "
				+ "WHERE hpv_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("codigo", horario.getCodigo());
			parameter.addValue("puesto", horario.getPuestoVigilancia().getCodigo());
			parameter.addValue("horaApertura", horario.getHoraApertura());
			parameter.addValue("horaCierre", horario.getHoraCierre());
			parameter.addValue("fechaCierre", horario.getFechaCierre(), Types.DATE);
			parameter.addValue("dia", horario.getDia().getCodigo());
			parameter.addValue("estado", horario.getEstado());

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

