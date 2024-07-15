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
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IFirmaDigitalDao;
import com.usco.edu.entities.Rector;
import com.usco.edu.entities.Firma;
import com.usco.edu.resultSetExtractor.FirmaSetExtractor;
import com.usco.edu.resultSetExtractor.RectorSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class FirmaDigitalDaoImpl implements IFirmaDigitalDao {
	
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("NamedJDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<Rector> buscarRectorActual(String userdb) {
		
		String sql = "select *, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), p.per_fecha_nacimiento ,112) as int) ) / 10000) as edad from uaa_personal up "
				+ "inner join persona p on up.per_codigo = p.per_codigo "
				+ "left join tipo_id ti on p.tii_codigo = ti.tii_codigo "
				+ "left join grupo_sanguineo gs on p.grs_codigo = gs.grs_codigo "
				+ "inner join uaa_cargo uc on up.uac_codigo = uc.uac_codigo "
				+ "inner join cargo c on uc.uac_cargo = c.car_codigo "
				+ "inner join uaa u on up.uaa_codigo = u.uaa_codigo "
				+ "where up.uac_codigo = 1 and uap_fecha_fin is null";
		
		return jdbcTemplate.query(sql, new RectorSetExtractor());
		
	}
	
	@Override
	public List<Firma> buscarFirmaActiva(String userdb) {
		
		String sql = "select *, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), p.per_fecha_nacimiento ,112) as int) ) / 10000) as edad from carnetizacion.firma_digital fd "
				+ "inner join persona p on fd.per_codigo = p.per_codigo "
				+ "left join tipo_id ti on p.tii_codigo = ti.tii_codigo "
				+ "left join grupo_sanguineo gs on p.grs_codigo = gs.grs_codigo "
				+ "where fd.fid_estado = 1 and fid_fecha_fin is null ";
		
		return jdbcTemplate.query(sql, new FirmaSetExtractor());
		
	}
	
	@Override
	public List<Firma> buscarFirma(String userdb) {
		
		String sql = "select *, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), p.per_fecha_nacimiento ,112) as int) ) / 10000) as edad from carnetizacion.firma_digital fd "
				+ "inner join persona p on fd.per_codigo = p.per_codigo "
				+ "left join tipo_id ti on p.tii_codigo = ti.tii_codigo "
				+ "left join grupo_sanguineo gs on p.grs_codigo = gs.grs_codigo order by fd.fid_codigo desc";
		
		return jdbcTemplate.query(sql, new FirmaSetExtractor());
		
	}

	@Override
	public void registrarFirma(Firma firma, String userdb) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "INSERT INTO carnetizacion.firma_digital (per_codigo, uap_codigo, fid_nombre, fid_fecha_fin, fid_ruta) VALUES(:perCodigo, :uapCodigo, :nombreFirma, :fechaFin, :ruta );";
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("perCodigo", firma.getPersona().getCodigo());
			parameter.addValue("uapCodigo", firma.getUaaPersonalCodigo());
			parameter.addValue("nombreFirma", firma.getNombreFirma());
			parameter.addValue("fechaFin", firma.getFechaFin(), Types.DATE);
			parameter.addValue("ruta", firma.getRuta());

			jdbc.update(sql, parameter);
			System.out.println("PASA POR CREATE");

		} catch (Exception e) {

			e.printStackTrace();

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
	public void actualizarFirmaEstado(Firma firma, String userdb) {
		
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE academia3000_john.carnetizacion.firma_digital "
				+ "SET fid_fecha_fin=:fechaFin, fid_estado=:estado "
				+ "WHERE fid_codigo=:codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("codigo", firma.getCodigo());
			parameter.addValue("fechaFin", firma.getFechaFin());
			parameter.addValue("estado", firma.getEstado());

			jdbc.update(sql, parameter);
		} catch (Exception e) {

			e.printStackTrace();

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
