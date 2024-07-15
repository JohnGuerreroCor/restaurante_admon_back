package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IDiaBeneficioDao;
import com.usco.edu.entities.DiaBeneficio;
import com.usco.edu.resultSetExtractor.DiaBeneficioSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class DiaBeneficioDaoImpl implements IDiaBeneficioDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
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
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE sibusco.restaurante_dias_beneficio " + "SET rdb_estado=:estado "
				+ "WHERE rgg_codigo=:codigoGrupoGabu " + "AND dia_codigo=:diaCodigo " + "AND rts_codigo=:tipoServicio ";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("estado", diasBeneficio.getEstado());
			parameter.addValue("codigoGrupoGabu", diasBeneficio.getCodigoGrupoGabu());
			parameter.addValue("diaCodigo", diasBeneficio.getDia().getCodigo());
			parameter.addValue("tipoServicio", diasBeneficio.getTipoServicio().getCodigo());

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

	@Override
	public int crearDiaBeneficio(String userdb, DiaBeneficio diasBeneficio) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO sibusco.restaurante_dias_beneficio "
				+ "(rgg_codigo, dia_codigo, rdb_estado, rts_codigo) "
				+ "VALUES(:grupoGabu, :dia, :estado, :tipoServicio);";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			// parameter.addValue("grupoGabu", diasBeneficio.getGrupoGabu().getCodigo());
			parameter.addValue("grupoGabu", diasBeneficio.getCodigoGrupoGabu());
			parameter.addValue("dia", diasBeneficio.getDia().getCodigo());
			parameter.addValue("estado", diasBeneficio.getEstado());
			parameter.addValue("tipoServicio", diasBeneficio.getTipoServicio().getCodigo());

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

	@Override
	public int crearDiasBeneficio(String userdb, List<DiaBeneficio> diasBeneficio) {
	    DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
	    NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

	    String sql = "INSERT INTO sibusco.restaurante_dias_beneficio "
	            + "(rgg_codigo, dia_codigo, rdb_estado, rts_codigo) "
	            + "VALUES(:grupoGabu, :dia, :estado, :tipoServicio)";

	    try {
	        SqlParameterSource[] batchParams = diasBeneficio.stream()
	                .map(diaBeneficio -> new MapSqlParameterSource()
	                        .addValue("grupoGabu", diaBeneficio.getCodigoGrupoGabu())
	                        .addValue("dia", diaBeneficio.getDia().getCodigo())
	                        .addValue("estado", diaBeneficio.getEstado())
	                        .addValue("tipoServicio", diaBeneficio.getTipoServicio().getCodigo()))
	                .toArray(SqlParameterSource[]::new);

	        int[] updateCounts = jdbc.batchUpdate(sql, batchParams);
	        int totalInserted = Arrays.stream(updateCounts).sum();

	        return totalInserted;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    } finally {
	        try {
	            cerrarConexion(dataSource.getConnection());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}
