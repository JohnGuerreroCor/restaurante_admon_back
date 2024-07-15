package com.usco.edu.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ITipoDocumentoDao;
import com.usco.edu.entities.TipoDocumento;
import com.usco.edu.service.ITipoDocumentoService;

@Service
public class TipoDucumentoServiceImpl implements ITipoDocumentoService {
	
	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;

	@Override
	public List<TipoDocumento> obtenerTiposDocumentos(String userdb) {
		
		return tipoDocumentoDao.obtenerTiposDocumentos(userdb);
		
	}

}
