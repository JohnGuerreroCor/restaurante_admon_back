package com.usco.edu.service.serviceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IDocenteDao;
import com.usco.edu.entities.Docente;
import com.usco.edu.service.IDocenteService;

@Service
public class DocenterServiceImpl implements IDocenteService {
	
	@Autowired
	private IDocenteDao docenteDao;

	@Override
	public List<Docente> findByIdentificacion(String id, String userdb) {
		return docenteDao.findByIdentificacion(id, userdb);
	}

}
