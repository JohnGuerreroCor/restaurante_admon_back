package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.ITipoDocumentoDao;
import com.usco.edu.entities.TipoDocumento;
import com.usco.edu.resultSetExtractor.TipoDocumentoSetExtractor;

@Repository
public class TipoDocumentoDaoImpl implements ITipoDocumentoDao {
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	

	@Override
	public List<TipoDocumento> obtenerTiposDocumentos(String userdb) {

		String sql = "select * from dbo.tipo_id ti where ti.tii_estado = 1 order by ti.tii_num_caracteres desc";
		return jdbcTemplate.query(sql, new TipoDocumentoSetExtractor());
	}

}
