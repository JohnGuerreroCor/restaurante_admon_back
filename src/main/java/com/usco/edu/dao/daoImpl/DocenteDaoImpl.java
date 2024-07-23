package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IDocenteDao;
import com.usco.edu.entities.Docente;
import com.usco.edu.resultSetExtractor.DocenteSetExtractor;

@Repository
public class DocenteDaoImpl implements IDocenteDao {
	
	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Docente> findByIdentificacion(String id, String userdb) {

		String sql = "Select top 1 *, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), p.per_fecha_nacimiento ,112) as int) ) / 10000) as edad from docente_vinculacion dv "
				+ "inner join persona p on dv.per_codigo = p.per_codigo "
				+ "inner join grupo_sanguineo gs on p.grs_codigo = gs.grs_codigo "
				+ "inner join tipo_id ti on p.tii_codigo = ti.tii_codigo "
				+ "inner join uaa u on dv.uaa_codigo = u.uaa_codigo "
				+ "inner join sede s on u.sed_codigo = s.sed_codigo "
				+ "where dv.per_identificacion  = '" + id + "' order by dv.uap_codigo asc";
		return jdbcTemplate.query(sql, new DocenteSetExtractor());
	}

}
