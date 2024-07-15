package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IRolDao;
import com.usco.edu.entities.Rol;
import com.usco.edu.service.IRolService;

@Service
public class RolServiceImpl implements IRolService {
	
	@Autowired
	private IRolDao rolDao;

	@Override
	public List<Rol> rol(String userdb) {
		return rolDao.rol(userdb);
	}
	
	@Override
	public List<Rol> rolByEstamento(String userdb, String estamento){
		return rolDao.rolByEstamento(userdb, estamento);
	}

	@Override
	public int createRol(String userdb, Rol r) {

		return rolDao.createRol(userdb, r);

	}

	@Override
	public int updateRol(String userdb, Rol r) {

		return rolDao.updateRol(userdb, r);

	}

}
