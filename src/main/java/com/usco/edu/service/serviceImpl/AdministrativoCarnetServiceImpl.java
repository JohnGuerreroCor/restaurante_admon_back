package com.usco.edu.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IAdministrativoCarnetDao;
import com.usco.edu.entities.Administrativo;
import com.usco.edu.service.IAdministrativoCarnetService;

@Service
public class AdministrativoCarnetServiceImpl implements IAdministrativoCarnetService {
	
	@Autowired
	private IAdministrativoCarnetDao administrativoCarnetDao;

	@Override
	public List<Administrativo> findByIdentificacion(String id, String userdb) {
		
		return administrativoCarnetDao.findByIdentificacion(id, userdb);
		
	}

}
