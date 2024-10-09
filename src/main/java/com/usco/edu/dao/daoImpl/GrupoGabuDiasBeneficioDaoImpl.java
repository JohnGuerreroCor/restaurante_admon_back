package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IGrupoGabuDiasBeneficioDao;
import com.usco.edu.entities.GrupoGabuDiasBeneficio;
import com.usco.edu.resultSetExtractor.GrupoGabuDiasBeneficioSetExtractor;

@Repository
public class GrupoGabuDiasBeneficioDaoImpl implements IGrupoGabuDiasBeneficioDao {

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<GrupoGabuDiasBeneficio> obtenerGabus(String userdb) {
		
		String sql = "SELECT "
			   + "rgg.rgg_codigo AS GrupoGabuCodigo, "
			   + "rgg.rtg_codigo AS TipoGabuCodigo, "
			   + "rgg.per_codigo AS PersonaCodigo, "
			   + "rgg.rgg_usuario AS UsuarioCodigo, "
			   + "rgg.uaa_codigo AS UaaCodigo, "
			   + "rgg.rgg_vigencia AS Vigencia, "
			   + "rdb.rdb_codigo AS DiasBeneficioCodigo, "
			   + "rdb.dia_codigo AS DiaCodigo "
			   + "FROM "
			   + "sibusco.restaurante_grupo_gabu AS rgg "
			   + "LEFT JOIN "
			   + "sibusco.restaurante_dias_beneficio AS rdb ON rgg.rgg_codigo = rdb.rgg_codigo;";

		return jdbcTemplate.query(sql, new GrupoGabuDiasBeneficioSetExtractor());
	}

}
