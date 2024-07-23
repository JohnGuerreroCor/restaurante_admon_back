package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IAdministrativoCarnetDao;
import com.usco.edu.entities.Administrativo;
import com.usco.edu.resultSetExtractor.AdministrativoSetExtractor;

@Repository
public class AdministrativoCarnetDaoImpl implements IAdministrativoCarnetDao {
	
	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Administrativo> findByIdentificacion(String id, String userdb) {

		String sql = "Exec vinculacion_actual '" + id + "', 0";
		return jdbcTemplate.query(sql, new AdministrativoSetExtractor());
		
	}

}
